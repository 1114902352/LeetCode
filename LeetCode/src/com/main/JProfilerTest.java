package com.main;

import java.util.ArrayList;
import java.util.List;

public class JProfilerTest{
    public static void main(String[] args) throws InterruptedException {
        List<HelloWorld> list = new ArrayList<>();
        for(int i=1; i<10000; i++) {
            list.add(new HelloWorld());
            Thread.sleep(100); // 休眠100毫秒
        }
    }
}

class HelloWorld {
    public HelloWorld() {
        System.out.println("Hello Jayzee!");
    }
}
