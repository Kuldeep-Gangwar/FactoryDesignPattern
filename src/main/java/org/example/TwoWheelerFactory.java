package org.example;

public class TwoWheelerFactory implements  VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        TwoWheelerVehicle twoWheelerVehicle = new TwoWheelerVehicle();
        twoWheelerVehicle.type="Two wheeler ";
        return twoWheelerVehicle;
    }
}
