package com.my.example.jdk.manual;

import com.my.example.jdk.Myself;
import com.my.example.jdk.Person;

public class MainTest {

    public static void main(String[] args) {

        Person person = new Myself();
        Person proxy = (Person) MHuangNiu.getInstance(person);
        proxy.bugIphone();

    }
}
