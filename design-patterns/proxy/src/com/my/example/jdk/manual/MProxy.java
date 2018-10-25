package com.my.example.jdk.manual;


import jdk.internal.dynalink.beans.StaticClass;

public class MProxy {


    private final static String ln="\r\n";

    private MInvocationHandler h;

    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, MInvocationHandler h) throws IllegalArgumentException {


        return null;
    }



    private String generateJava(Class<?> interfaces){

        StringBuffer javaStr = new StringBuffer();
        javaStr.append("package com.my.example.jdk.manual;"+ln);
        javaStr.append("import com.my.example.jdk.Person;"+ln);
        javaStr.append("import java.lang.reflect.Method;"+ln);
        javaStr.append("public class MProxy implements Person {"+ln);
        javaStr.append("");

        //生成核心方法
        //核心调用到反射接口的h.invoke
        return  null;

    }

}
