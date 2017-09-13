/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2017 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.deloitte.core.dao.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.daos.impl.DefaultOrderDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import com.deloitte.core.dao.MerchandiseOrderDao;


/**
 *
 */
public class DefaultMerchandiseOrderDao extends DefaultOrderDao implements MerchandiseOrderDao
{


	@Override
	public OrderModel getOrderByCode(final String orderCode)
	{
		final String query = "SELECT {PK} FROM {" + OrderModel._TYPECODE + "} WHERE {" + OrderModel.CODE + "} = '" + orderCode
				+ "'";
		final FlexibleSearchQuery fquery = new FlexibleSearchQuery(query);
		return (OrderModel) getFlexibleSearchService().searchUnique(fquery);
	}

}
