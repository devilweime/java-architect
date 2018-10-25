package com.my.example.jdk;

public class MainTest {

    public static void main(String[] args) {
        Person person = new Myself();
        Person proxy = (Person) HuangNiu.getInstance(person);
        proxy.bugIphone();
    }
}
