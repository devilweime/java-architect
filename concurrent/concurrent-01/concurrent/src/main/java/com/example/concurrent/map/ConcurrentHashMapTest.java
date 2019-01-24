package com.example.concurrent.map;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class ConcurrentHashMapTest {

    private  final static ConcurrentHashMap<String,Integer> concurrentHashMap = new ConcurrentHashMap();
    private final static Object lock = new Object();
    private final static Set<Integer> length = new HashSet<>();
    private static int inventory;

    public static void main(String[] args) throws InterruptedException {
        concurrentHashMap.put("inventory", 0);
        inventory = concurrentHashMap.get("inventory");
        int times = 1000;
        CountDownLatch latch = new CountDownLatch(times);
        for (int i=0; i < times ;i++){
            new Thread(()->{
                //System.out.println("改变前："+inventory);
                inventory = inventory+1;
                synchronized (lock){
                    length.add(inventory);
                    //System.out.println("改变后："+inventory);
                }
                latch.countDown();
            }).start();
        }
        //Thread.sleep(2000);
        latch.await();
        System.out.println("条数："+length.size());
    }

}
