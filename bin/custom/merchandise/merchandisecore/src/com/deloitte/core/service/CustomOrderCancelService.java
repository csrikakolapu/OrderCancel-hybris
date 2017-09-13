/**
 *
 */
package com.deloitte.core.service;

import de.hybris.platform.core.model.order.OrderModel;


/**
 * @author csrikakolapu
 *
 */
public interface CustomOrderCancelService
{
	public boolean isOrderCancelable();

	public OrderModel getOrderByCode(String orderCode);

	/**
	 * @param order
	 * @return
	 */
	boolean cancelOrderInSAP(OrderModel order);


}
