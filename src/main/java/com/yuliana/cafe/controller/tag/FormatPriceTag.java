package com.yuliana.cafe.controller.tag;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;

/**
 * Custom tag that formats dish's price.
 *
 * @author Yulia Shuleiko
 */
public class FormatPriceTag extends TagSupport {

    private static final Logger logger = LogManager.getLogger();
    private static final String PRICE_FORMAT = "%.2f";
    private double price;

    /**
     * Setter method for the price.
     *
     * @param price price of the dish
     */
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int doStartTag() {
        String priceOutput = String.format(Locale.US, PRICE_FORMAT, price);
        logger.log(Level.ERROR, priceOutput);
        JspWriter out = pageContext.getOut();
        try {
            out.write(priceOutput);
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
        }
        return SKIP_BODY;
    }
}