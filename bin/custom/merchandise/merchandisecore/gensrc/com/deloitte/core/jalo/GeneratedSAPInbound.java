/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Sep 21, 2017 2:05:44 PM                     ---
 * ----------------------------------------------------------------
 */
package com.deloitte.core.jalo;

import com.deloitte.core.constants.MerchandiseCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.deloitte.core.jalo.SAPInbound SAPInbound}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSAPInbound extends GenericItem
{
	/** Qualifier of the <code>SAPInbound.orderNumber</code> attribute **/
	public static final String ORDERNUMBER = "orderNumber";
	/** Qualifier of the <code>SAPInbound.inputContent</code> attribute **/
	public static final String INPUTCONTENT = "inputContent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(ORDERNUMBER, AttributeMode.INITIAL);
		tmp.put(INPUTCONTENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPInbound.inputContent</code> attribute.
	 * @return the inputContent
	 */
	public String getInputContent(final SessionContext ctx)
	{
		return (String)getProperty( ctx, INPUTCONTENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPInbound.inputContent</code> attribute.
	 * @return the inputContent
	 */
	public String getInputContent()
	{
		return getInputContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SAPInbound.inputContent</code> attribute. 
	 * @param value the inputContent
	 */
	public void setInputContent(final SessionContext ctx, final String value)
	{
		setProperty(ctx, INPUTCONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SAPInbound.inputContent</code> attribute. 
	 * @param value the inputContent
	 */
	public void setInputContent(final String value)
	{
		setInputContent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPInbound.orderNumber</code> attribute.
	 * @return the orderNumber
	 */
	public String getOrderNumber(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ORDERNUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPInbound.orderNumber</code> attribute.
	 * @return the orderNumber
	 */
	public String getOrderNumber()
	{
		return getOrderNumber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SAPInbound.orderNumber</code> attribute. 
	 * @param value the orderNumber
	 */
	public void setOrderNumber(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ORDERNUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SAPInbound.orderNumber</code> attribute. 
	 * @param value the orderNumber
	 */
	public void setOrderNumber(final String value)
	{
		setOrderNumber( getSession().getSessionContext(), value );
	}
	
}
