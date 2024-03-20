package fr.btsciel;

import classeModbusRTU.Modbus;
import jssc.SerialPortException;

public class Main {

     static Modbus modbus;

    public static void main(String[] args) throws SerialPortException {
        System.out.println("le n° de l'esclave svp");
        modbus = new Modbus(In.readByte());
        System.out.println("le com svp");
        modbus.connecterEsclave(In.readString(),9600,0,0,1);
        modbus.lectureColis(8192,2);

    }
}