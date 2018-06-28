package modal;

import interfaces.IVehicle;

/**
 * @author lin
 * @date 18/6/28
 * @license Copyright (c) 2016 那镁克
 */

public class VehicleProxy implements IVehicle {
    private IVehicle v;

    public VehicleProxy(IVehicle v) {
        this.v = v;
    }

    @Override
    public void start() {
        System.out.println("VehicleProxy: Begin of start()");
        v.start();
        System.out.println("VehicleProxy: End of start()");
    }

    @Override
    public void forward() {
        System.out.println("VehicleProxy: Begin of forward()");
        v.forward();
        System.out.println("VehicleProxy: End of forward()");
    }

    @Override
    public void reverse() {
        System.out.println("VehicleProxy: Begin of reverse()");
        v.reverse();
        System.out.println("VehicleProxy: End of reverse()");
    }

    @Override
    public void stop() {
        System.out.println("VehicleProxy: Begin of stop()");
        v.stop();
        System.out.println("VehicleProxy: End of stop()");
    }
}
