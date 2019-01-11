package com.example.demojvm;

import java.io.FileOutputStream;

public class FileTest {
    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("iot_test/test.txt");
            fos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

}
