package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.command.ActionCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddPromoCodeCommand implements ActionCommand {

    private final static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}