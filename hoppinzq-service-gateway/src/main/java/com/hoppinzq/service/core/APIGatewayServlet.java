package com.hoppinzq.service.core;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 初始化网关及指定请求 URL:http://localhost:${port}/hoppinzq?xxx
 * @author:ZhangQi
 */
@WebServlet(urlPatterns = "/hoppinzq")
public class APIGatewayServlet extends HttpServlet {
    private static final long serialVersionUID=1L;
    ApplicationContext context;
    private ApiGatewayHand apiHandler;

    @Override
    public void init() throws ServletException {
        super.init();
        context= WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        apiHandler=context.getBean(ApiGatewayHand.class);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        apiHandler.handle(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        apiHandler.handle(request,response);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
