package com.example.demojvm.controller;

import com.example.demojvm.DeadLock;
import com.example.demojvm.entiy.User;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {


    private  final  static String LN = "\n";

    @GetMapping(value = {"/","hello"})
    public String hello(){
        return "hello world!";
    }

    @GetMapping("/cpu")
    public void cpu(){
        while (true){

        }
    }


    @GetMapping("/io")
    public void io(){
        User user = new User();
        user.setAge(18);
        user.setName("wgw");
        int index = 1 ;
        ObjectOutputStream objectOut = null;
        while (true){
            try {

                URL resource = this.getClass().getClassLoader().getResource("");
                String path = resource.getPath()+"/io_test/";
                File pathDir = new File(path);
                if (!pathDir.exists()){
                    pathDir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path+"/obj_"+index+".txt");
                objectOut = new ObjectOutputStream(fos);
                objectOut.writeObject(user);
                //index++;
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (objectOut != null){
                    try {
                        objectOut.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    @GetMapping("/memory")
    public void memory(){
        while (true){
            Object object = new Object();
        }
    }

    @GetMapping("/memory-leak")
    public void memoryLeak(){

        while (true){
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(new byte[1024*1024]);
                InputStream inputStream = new BufferedInputStream(bis);


            } catch (Exception e) {
                e.printStackTrace();
            }finally {

            }

        }
    }

    @GetMapping("/dead-lock")
    public void deadLock(){
       new DeadLock().run();
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


    @GetMapping("/java-conf")
    public String javaConf(){
        StringBuffer sb = new StringBuffer();
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        sb.append("堆内存信息："+memoryMXBean.getHeapMemoryUsage());
        sb.append(LN);
        sb.append("方法去区内存信息："+memoryMXBean.getNonHeapMemoryUsage());
        sb.append(LN);

        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        List<String> inputArguments = runtimeMXBean.getInputArguments();
        sb.append("#########################运行时设置的JVM参数##########################");
        sb.append(LN);
        sb.append(inputArguments);
        sb.append(LN);

        sb.append("#########################运行时内存情况##########################");
        sb.append(LN);
        sb.append("总的内存量["+Runtime.getRuntime().totalMemory()+"]");
        sb.append(LN);
        sb.append("空闲的内存量["+Runtime.getRuntime().freeMemory()+"]");
        sb.append(LN);
        sb.append("最大的内存量["+Runtime.getRuntime().maxMemory()+"]");

        System.out.println(sb);
        return sb.toString();
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
