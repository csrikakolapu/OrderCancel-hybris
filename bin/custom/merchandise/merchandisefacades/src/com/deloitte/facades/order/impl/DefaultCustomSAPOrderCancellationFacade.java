/**
 *
 */
package com.deloitte.facades.order.impl;

import de.hybris.merchandise.facades.order.data.OrderCancelResultData;
import de.hybris.platform.constants.GeneratedCoreConstants.Enumerations.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.Config;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.deloitte.core.service.CustomOrderCancelService;
import com.deloitte.facades.order.CustomSAPOrderCancellationFacade;


/**
 * @author csrikakolapu
 *
 */
public class DefaultCustomSAPOrderCancellationFacade implements CustomSAPOrderCancellationFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultCustomSAPOrderCancellationFacade.class);

	@Autowired
	private ModelService modelService;

	@Autowired
	private CustomOrderCancelService orderCancelService;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.deloitte.facades.order.CustomSAPOrderCancellationFacade#cancelOrder(java.lang.String)
	 */
	@Override
	public OrderCancelResultData cancelOrder(final String orderCode)
	{
		final OrderCancelResultData result = new OrderCancelResultData();


		if (Config.getBoolean("sap.order.cancel", false))
		{
			final OrderModel orderModel = orderCancelService.getOrderByCode(orderCode);
			try
			{
				final List<String> errorMessages = new ArrayList<String>();
				final boolean status = orderCancelService.cancelOrderInSAP(orderModel, errorMessages);
				if (status)
				{
					orderModel.setStatus(OrderStatus.CANCELLED);
					modelService.save(orderModel);
				}
				else
				{
					LOG.error(errorMessages);
				}


			}
			catch (final Exception e)
			{
				e.printStackTrace();
			}
		}

		result.setOrderId(orderCode);
		result.setSuccess(true);

		return result;
	}

}
