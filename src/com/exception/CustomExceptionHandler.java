package com.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

public class CustomExceptionHandler extends ExceptionHandler {

    final static Logger logger =
        Logger.getLogger(CustomExceptionHandler.class);

    @Override
    public ActionForward execute(Exception ex, ExceptionConfig ae,
                                 ActionMapping mapping,
                                 ActionForm formInstance,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException {

        //log the error message
        logger.error(ex);
        return super.execute(ex, ae, mapping, formInstance, request, response);
    }

    public CustomExceptionHandler() {
        super();
    }
}
