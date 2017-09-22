/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Sep 22, 2017 5:12:28 PM                     ---
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
	/** Qualifier of the <code>SAPInbound.inputContentXML</code> attribute **/
	public static final String INPUTCONTENTXML = "inputContentXML";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(ORDERNUMBER, AttributeMode.INITIAL);
		tmp.put(INPUTCONTENTXML, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPInbound.inputContentXML</code> attribute.
	 * @return the inputContentXML
	 */
	public String getInputContentXML(final SessionContext ctx)
	{
		return (String)getProperty( ctx, INPUTCONTENTXML);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPInbound.inputContentXML</code> attribute.
	 * @return the inputContentXML
	 */
	public String getInputContentXML()
	{
		return getInputContentXML( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SAPInbound.inputContentXML</code> attribute. 
	 * @param value the inputContentXML
	 */
	public void setInputContentXML(final SessionContext ctx, final String value)
	{
		setProperty(ctx, INPUTCONTENTXML,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SAPInbound.inputContentXML</code> attribute. 
	 * @param value the inputContentXML
	 */
	public void setInputContentXML(final String value)
	{
		setInputContentXML( getSession().getSessionContext(), value );
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
