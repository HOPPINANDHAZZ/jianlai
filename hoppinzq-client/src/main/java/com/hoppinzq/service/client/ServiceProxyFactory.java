package com.hoppinzq.service.client;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Proxy;

/**
 * 为客户端创建服务代理的工厂，需提供要访问的接口类和公开服务地址并将结果强制转换到接口类
 * 有些服务需要提供凭证来进行身份验证和鉴权
 */
public class ServiceProxyFactory {
    public static int streamBufferSize = 16384;


    /**
     * @param serviceInterface 要实现的服务接口
     * @param serviceURI 远程服务的URI
     * @return 给定服务接口的服务代理
     */
    public static <T> T createProxy(Class<? extends T> serviceInterface, String serviceURI) {
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class[] { serviceInterface }, new MethodInvocationHandler(serviceURI));
    }

    /**
     * @param serviceInterface 要实现的服务接口
     * @param serviceURI 远程服务的URI
     * @param credentials 用于身份验证的凭据
     * @return
     */
    public static <T> T createProxy(Class<? extends T> serviceInterface, String serviceURI, Serializable credentials) {
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class[] { serviceInterface }, new MethodInvocationHandler(serviceURI, credentials));
    }

    /**
     * @param serviceInterface 要实现的服务接口
     * @param methodInvocationHandler 已实例化的MethodInvocationHandler
     * @return
     */
    public static <T> T createProxy(Class<? extends T> serviceInterface, MethodInvocationHandler methodInvocationHandler) {
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class[] { serviceInterface }, methodInvocationHandler);
    }

    /**
     * 为服务代理设置凭证
     * @param proxy 使用createProxy()创建的代理
     * @param credentials 可序列化凭据对象
     */
    public static void setCredentials(Object proxy, Serializable credentials) {
        MethodInvocationHandler methodInvocationHandler = (MethodInvocationHandler) Proxy.getInvocationHandler(proxy);
        methodInvocationHandler.setCredentials(credentials);
    }

    /**
     * 为服务代理设置远程服务的URI
     * @param proxy 使用createProxy()创建的代理
     * @param serviceURI 远程服务的URI
     */
    public static void setServiceURI(Object proxy, String serviceURI) {
        MethodInvocationHandler methodInvocationHandler = (MethodInvocationHandler) Proxy.getInvocationHandler(proxy);
        methodInvocationHandler.setServiceURI(serviceURI);
    }

}