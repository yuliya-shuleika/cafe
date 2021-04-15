package com.yuliana.cafe.tag;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class CountPriceTag extends TagSupport {

    private static final Logger logger = LogManager.getLogger();
    private static final String PRICE_FORMAT = "#0.00";
    private double price;
    private int discount;

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public int doStartTag() {
        double totalPrice = price - (price * discount / 100);
        NumberFormat formatter = new DecimalFormat(PRICE_FORMAT);
        String priceOutput = formatter.format(totalPrice);
        JspWriter out = pageContext.getOut();
        try {
            out.write("<p>" + priceOutput + "</p>");
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
        }
        return SKIP_BODY;
    }
}
