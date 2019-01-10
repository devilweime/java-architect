package com.example.demojvm.controller;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.font.CreatedFontTracker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @GetMapping(value = {"/","hello"})
    public String hello(){
        return "hello world!";
    }

    @GetMapping("/cpu")
    public void cpu(){
        while (true){

        }
    }

    @GetMapping("/memory")
    public void memory(){
        while (true){
            Object object = new Object();
        }
    }

    @GetMapping("/head-oom")
    public void headOom(){
        List<byte[]> list = new ArrayList<>();
        while (true){
           list.add(new byte[1024]);
        }
    }

    @GetMapping("/stack-soe")
    public void stackStackOverflowError(){
       JVMStackOverflowError stackOverflowError = new JVMStackOverflowError();
       try {
           stackOverflowError.stackLeak();
       }catch (Throwable throwable){
           throwable.printStackTrace();
           System.out.println("stackLength:"+stackOverflowError.stackLength);
       }
    }


    @GetMapping("/stack-oom")
    public void stackOutOfMemoryError(){

        while (true){
            new Thread(()->{
                while (true){
                    //方法不出栈
                }
            }).start();
        }
    }

    @GetMapping("/runtime-constant-pool-oom")
    public void runtimeConstantPoolOutOfMemoryError(){
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }


    @GetMapping("/method-area-oom")
    public void methodAreaOutOfMemoryError(){
        JVMMethodAreaOutOfMemoryError methodAreaOutOfMemoryError = new JVMMethodAreaOutOfMemoryError();
        methodAreaOutOfMemoryError.createClass();
    }

    private  class JVMStackOverflowError{

        private int stackLength =1;

        public void  stackLeak(){
            stackLength ++;
            stackLeak();
        }
    }

    private class JVMMethodAreaOutOfMemoryError{
        public void createClass(){
            while (true){
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(this.getClass());
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, objects);
                    }
                });
                enhancer.create();

            }
        }

    }
}
