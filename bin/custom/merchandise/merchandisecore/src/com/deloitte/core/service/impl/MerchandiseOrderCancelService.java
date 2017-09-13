/**
 *
 */
package com.deloitte.core.service.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;

import java.util.List;

import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender.RemoveSoapHeadersInterceptor;

import com.deloitte.core.dao.MerchandiseOrderDao;
import com.deloitte.core.service.CustomOrderCancelService;
import com.deloitte.sap.SI_OrderCancellation_Out_SyncServiceStub.DT_OrderCancellation_Request;
import com.deloitte.sap.SI_OrderCancellation_Out_SyncServiceStub.MT_OrderCancellation_Request;
import com.deloitte.sap.SI_OrderCancellation_Out_SyncServiceStub.MT_OrderCancellation_Response;
import com.deloitte.sap.SI_OrderCancellation_Out_SyncServiceStub.OrderDetail_type0;


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
		final MT_OrderCancellation_Request orderCancelRequest = new MT_OrderCancellation_Request();
		final DT_OrderCancellation_Request dt_OrderCancel = new DT_OrderCancellation_Request();
		final List<AbstractOrderEntryModel> entries = order.getEntries();
		final OrderDetail_type0[] orderDetailArr = new OrderDetail_type0[entries.size()];
		int index = 0;

		for (final AbstractOrderEntryModel entry : entries)
		{
			final OrderDetail_type0 orderDetail = new OrderDetail_type0();
			orderDetailArr[index++] = orderDetail;
			orderDetail.setOrderNo(order.getCode());
			orderDetail.setNote("Order cancelled by user.");

			orderDetail.setItemNo(entry.getEntryNumber().toString());
			orderDetail.setRejectionCode("R");
		}

		dt_OrderCancel.setOrderDetail(orderDetailArr);
		orderCancelRequest.setMT_OrderCancellation_Request(dt_OrderCancel);

		final HttpComponentsMessageSender messageSender = (HttpComponentsMessageSender) webServiceTemplateSAP
				.getMessageSenders()[0];
		((DefaultHttpClient) messageSender.getHttpClient()).addRequestInterceptor(new RemoveSoapHeadersInterceptor(), 0);
		webServiceTemplateSAP.setMessageSender(messageSender);
		final MT_OrderCancellation_Response response = (MT_OrderCancellation_Response) webServiceTemplateSAP
				.marshalSendAndReceive(orderCancelRequest);

		if (response != null)
		{
			response.getMT_OrderCancellation_Response();
		}
		return true;
	}

}