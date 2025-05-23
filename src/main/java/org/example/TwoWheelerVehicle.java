package org.example;

public class TwoWheelerVehicle implements Vehicle {
    String type;

    public TwoWheelerVehicle() {
        super();
    }

    @Override
    public void printObjectNameAndType() {
        System.out.println("created Object : " + this.ObjectNAME + ", type : " + type);
    }
}
