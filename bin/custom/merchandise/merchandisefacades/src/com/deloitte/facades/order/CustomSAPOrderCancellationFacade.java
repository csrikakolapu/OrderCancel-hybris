/**
 *
 */
package com.deloitte.facades.order;

import de.hybris.merchandise.facades.order.data.OrderCancelResultData;


/**
 * @author csrikakolapu
 *
 */
public interface CustomSAPOrderCancellationFacade
{
	OrderCancelResultData cancelOrder(String orderCode);
}
