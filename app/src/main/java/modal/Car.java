package modal;

import interfaces.IVehicle;

/**
 * @author lin
 * @date 18/6/28
 * @license Copyright (c) 2016 那镁克
 */

public class Car implements IVehicle {
    @Override
    public void start() {
        System.out.println("start");
    }

    @Override
    public void forward() {
        System.out.println("forward");
    }

    @Override
    public void reverse() {
        System.out.println("reverse");
    }

    @Override
    public void stop() {
        System.out.println("stop");
    }
}
