package com.design.pattern.command;

/**
 * Created by Administrator on 2017/10/24.
 */
public class Stereo {
    private String location;
    public Stereo(String location) {
        this.location = location;
    }

    public void on(){
        System.out.println("音响开");
    }
    public void setCD(){
        System.out.println("放入CD");
    }
    public void setVolume(int volume){
        System.out.println("调整音量到"+volume);
    }
}
