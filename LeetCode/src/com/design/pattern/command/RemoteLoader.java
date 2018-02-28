package com.design.pattern.command;

/**
 * Created by Administrator on 2017/10/24.
 */
public class RemoteLoader {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        Stereo stereo = new Stereo("Living Room");

        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);
        StereoOnWithCDCommand stereoOnWithCD = new StereoOnWithCDCommand(stereo);

        remoteControl.setCommand(0,null,livingRoomLightOff);
        remoteControl.setCommand(1,null,kitchenLightOff);
        remoteControl.setCommand(2,stereoOnWithCD,null);

        remoteControl.offButtonWasPused(0);
        remoteControl.offButtonWasPused(1);
        remoteControl.onButtonWasPushed(2);
    }
}
