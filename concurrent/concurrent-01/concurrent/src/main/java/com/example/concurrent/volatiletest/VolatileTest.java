package com.example.concurrent.volatiletest;

public class VolatileTest {

    public static void main(String[] args) throws InterruptedException {

        VolatileTest test = new VolatileTest();
        VolatileSample sample = test.new VolatileSample();

        //VolatileSample sample = new VolatileSample();

        Thread t1 = new Thread(() -> {
            sample.done(0);
        });

        Thread.sleep(500);
        Thread t2 = new Thread(() -> {
            sample.shutdowm();
        });

        t1.start();
        Thread.sleep(500);
        t2.start();
        t2.join();
        t1.join();

    }

    private class VolatileSample {
        boolean active = true;//volatile 状态标志变量

        public void done(long time) {

            while (active) {
                if (time > 0) {
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //发现System.out.println()无法验证volatile的可见性
                //System.out.println(Thread.currentThread().getName() + "：active = " + active);
                //System.out.println(Thread.currentThread().getName() + "：do something");
                Object object = new Object();
            }

        }

        public void shutdowm() {
                active = false;
                System.out.println("shutdown ...");
        }
    }


}
