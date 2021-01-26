package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TranslateCommand implements ActionCommand {

    private static final String LANG_ATTRIBUTE = "lang";
    private static final String LANG_EN = "en";
    private static final String LANG_RU = "ru";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute(LANG_ATTRIBUTE);
        if(lang != null) {
            if (lang.equals(LANG_EN)) {
                session.setAttribute(LANG_ATTRIBUTE, LANG_RU);
            } else {
                session.setAttribute(LANG_ATTRIBUTE, LANG_EN);
            }
        }else {
            session.setAttribute(LANG_ATTRIBUTE, LANG_EN);
        }
        String page = "/jsp/login.jsp";
        return page;
    }
}
