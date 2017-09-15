/**
 *
 */
package com.deloitte.core.service.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender.RemoveSoapHeadersInterceptor;

import com.deloitte.core.dao.MerchandiseOrderDao;
import com.deloitte.core.service.CustomOrderCancelService;

import hybris.com.sap.ordercancellationpoc.DTOrderCancellationRequest;
import hybris.com.sap.ordercancellationpoc.DTOrderCancellationRequest.OrderDetail;
import hybris.com.sap.ordercancellationpoc.DTOrderCancellationResponse;
import hybris.com.sap.ordercancellationpoc.ObjectFactory;


/**
 * @author csrikakolapu
 *
 */
public class MerchandiseOrderCancelService implements CustomOrderCancelService
{
	@Autowired
	private WebServiceTemplate webServiceTemplateSAP;


	private MerchandiseOrderDao orderDao;

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
	public boolean cancelOrderInSAP(final OrderModel order)
	{
		final ObjectFactory objFactory = new ObjectFactory();
		final DTOrderCancellationRequest orderCancelRequest = objFactory.createDTOrderCancellationRequest();
		final List<AbstractOrderEntryModel> entries = order.getEntries();
		final List<OrderDetail> orderDetailList = new ArrayList<>();


		for (final AbstractOrderEntryModel entry : entries)
		{
			final OrderDetail orderDetail = objFactory.createDTOrderCancellationRequestOrderDetail();
			orderDetailList.add(orderDetail);
			orderDetail.setOrderNo(order.getCode());
			orderDetail.setNote("Order cancelled by user.");

			orderDetail.setItemNo(entry.getEntryNumber().toString());
			orderDetail.setRejectionCode("R");
		}

		orderCancelRequest.getOrderDetail().addAll(orderDetailList);
		final HttpComponentsMessageSender messageSender = (HttpComponentsMessageSender) webServiceTemplateSAP
				.getMessageSenders()[0];
		((DefaultHttpClient) messageSender.getHttpClient()).addRequestInterceptor(new RemoveSoapHeadersInterceptor(), 0);
		webServiceTemplateSAP.setMessageSender(messageSender);
		final DTOrderCancellationResponse response = (DTOrderCancellationResponse) webServiceTemplateSAP
				.marshalSendAndReceive(orderCancelRequest);

		if (response != null)
		{
			response.getCancelStatus();
		}
		return true;

	}

}