package com.design.pattern.command;

/**
 * Created by Administrator on 2017/10/24.
 */
public class LightOffCommand implements Command {
    Light light;
    public LightOffCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}
