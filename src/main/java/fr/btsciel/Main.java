package fr.btsciel;

import classeModbusRTU.Modbus;
import jssc.SerialPortException;

public class Main {

     static Modbus modbus;

    public static void main(String[] args){
        System.out.println("le n° de l'esclave svp");
        modbus = new Modbus(In.readByte());
        System.out.println("le com svp");
        try {
            modbus.connecterEsclave(In.readString(),9600,0,0,1);
            System.out.println(modbus.lectureColis(8192,2));
        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        }
    }
}