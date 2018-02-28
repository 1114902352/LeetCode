package com.design.pattern.command;

/**
 * Created by Administrator on 2017/10/24.
 */
public class Light {
    private String location;
    public Light(String location){
        this.location = location;
    }
    public void on(){
        System.out.println(location+"灯亮");
    }
    public void off(){
        System.out.println(location+"灯灭");
    }
}
