package com.hoppinzq.service.core;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 初始化网关及指定请求 URL:http://localhost:${port}/hoppinzq?xxx
 * 可以使用@WebServlet注解自动注册
 * 这里我采用了开关的方式去声明是否开启网关（即注册该servlet）
 * @author:ZhangQi
 */
//@WebServlet(urlPatterns = "/hoppinzq")
@MultipartConfig//标识Servlet支持文件上传
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
        Request request1=(Request)request;
        ContextHandler contextHandler = request1.getContext().getContextHandler();
        contextHandler.setMaxFormContentSize(600000);
        apiHandler.handle(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Request request1=(Request)request;
        ContextHandler contextHandler = request1.getContext().getContextHandler();
        contextHandler.setMaxFormContentSize(600000);
        apiHandler.handle(request,response);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
