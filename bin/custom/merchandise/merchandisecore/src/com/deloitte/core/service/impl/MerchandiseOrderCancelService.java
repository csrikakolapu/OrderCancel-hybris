/**
 *
 */
package com.deloitte.core.service.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender.RemoveSoapHeadersInterceptor;

import com.deloitte.core.dao.MerchandiseOrderDao;
import com.deloitte.core.model.SAPInboundModel;
import com.deloitte.core.service.CustomOrderCancelService;
import com.deloitte.dhub.sales.salesordercancellation.MTDHUBOrderCancellation;
import com.deloitte.dhub.sales.salesordercancellation.MTDHUBOrderCancellation.OrderDetails;
import com.deloitte.dhub.sales.salesordercancellation.MTDHUBOrderCancellationResponse;
import com.deloitte.dhub.sales.salesordercancellation.MTDHUBOrderCancellationResponse.CancelStatus;
import com.deloitte.dhub.sales.salesordercancellation.ObjectFactory;



/**
 * @author csrikakolapu
 *
 */
public class MerchandiseOrderCancelService implements CustomOrderCancelService
{
	@Autowired
	private WebServiceTemplate webServiceTemplateSAP;


	private MerchandiseOrderDao orderDao;

	@Autowired
	private ModelService modelService;

	@Autowired
	private SessionService session;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.deloitte.core.service.OrderCancelService#isOrderCancelable()
	 */
	@Override
	public boolean isOrderCancelable()
	{
		// YTODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the orderDao
	 */
	public MerchandiseOrderDao getOrderDao()
	{
		return orderDao;
	}

	/**
	 * @param orderDao
	 *           the orderDao to set
	 */
	public void setOrderDao(final MerchandiseOrderDao orderDao)
	{
		this.orderDao = orderDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.deloitte.core.service.OrderCancelService#getOrderByCode(java.lang.String)
	 */
	@Override
	public OrderModel getOrderByCode(final String orderCode)
	{
		return orderDao.getOrderByCode(orderCode);
	}

	@Override
	public boolean cancelOrderInSAP(final OrderModel order, final List<String> errorMessages)
	{
		final ObjectFactory objFactory = new ObjectFactory();
		final MTDHUBOrderCancellation orderCancelRequest = objFactory.createMTDHUBOrderCancellation();
		final List<AbstractOrderEntryModel> entries = order.getEntries();
		final List<OrderDetails> orderDetailList = new ArrayList<>();


		for (final AbstractOrderEntryModel entry : entries)
		{
			final OrderDetails orderDetail = objFactory.createMTDHUBOrderCancellationOrderDetails();
			orderDetailList.add(orderDetail);
			orderDetail.setOrderNo(order.getCode());
			orderDetail.setNote("Order cancelled by user.");
			Integer orderEntryNumber = entry.getEntryNumber();
			orderEntryNumber = orderEntryNumber + 1;
			orderDetail.setItemNo(orderEntryNumber.toString());
			orderDetail.setRejectionCode("ZH");
		}

		orderCancelRequest.getOrderDetails().addAll(orderDetailList);


		final HttpComponentsMessageSender messageSender = (HttpComponentsMessageSender) webServiceTemplateSAP
				.getMessageSenders()[0];
		((DefaultHttpClient) messageSender.getHttpClient()).addRequestInterceptor(new RemoveSoapHeadersInterceptor(), 0);
		webServiceTemplateSAP.setMessageSender(messageSender);
		final MTDHUBOrderCancellationResponse response = (MTDHUBOrderCancellationResponse) webServiceTemplateSAP
				.marshalSendAndReceive(orderCancelRequest);
		if (response != null)
		{
			String xml = "";

			try
			{
				xml = XmlToStringWriter(orderCancelRequest).toString();
				final SAPInboundModel sapInbound = modelService.create(SAPInboundModel._TYPECODE);
				sapInbound.setInputContentXML(xml);
				sapInbound.setOrderNumber(order.getCode());
				xml = XmlToStringWriter(response).toString();
				sapInbound.setOutputContentXML(xml);
				modelService.save(sapInbound);
			}
			catch (final Exception e)
			{
				e.printStackTrace();
			}


			final List<CancelStatus> statusList = response.getCancelStatus();
			final List<CancelStatus> errorStatus = statusList.stream().filter(statusObj -> "E".equals(statusObj.getStatus()))
					.collect(Collectors.toList());
			errorMessages.addAll(errorStatus.stream().map(CancelStatus::getStatusMsg).collect(Collectors.toList()));
		}

		if (errorMessages.isEmpty())
		{
			return true;
		}
		else
		{
			session.setAttribute("oc-errors", errorMessages);
			return false;
		}

	}

	private StringWriter XmlToStringWriter(final Object xmlObject) throws JAXBException
	{
		final JAXBContext requestContext = JAXBContext.newInstance(xmlObject.getClass());
		final Marshaller requestMarshaller = requestContext.createMarshaller();
		requestMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		final StringWriter writer = new StringWriter();
		requestMarshaller.marshal(xmlObject, writer);
		return writer;
	}


}