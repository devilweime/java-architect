package com.my.example.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HuangNiu  implements InvocationHandler {


    private Person person;

    public  static  Object getInstance( Person target){
        HuangNiu huangNiu = new HuangNiu(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),huangNiu);
    }

    public HuangNiu(Person person) {
        this.person = person;
    }

    @Override
    /**
     * @proxy 生成的代理对象
     *
     * @method 代理调用到的接口方法
     *
     * @args 方法参数
     *
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("黄牛：今晚我就去Iphone店通宵排队");
        method.invoke(person,args);
        System.out.println("黄牛：又挣了几百块钱");

        //检查新生成代理类的字节码
        byte[] bytes = ProxyGenerator.generateProxyClass("$proxy", proxy.getClass().getInterfaces());
        FileOutputStream outputStream = new FileOutputStream("$proxy.class");
        outputStream.write(bytes);
        outputStream.close();

        return null;
    }
}
