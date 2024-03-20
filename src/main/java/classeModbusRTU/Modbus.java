package classeModbusRTU;

import jssc.SerialPortException;
import liaisonSerie.LiaisonSerie;

public class Modbus extends LiaisonSerie {
    Byte numeroEsclave = 0;
    Crc16 crc16 = new Crc16();

    public Modbus() {
    }

    public Modbus(Byte numeroEsclave) {
        this.numeroEsclave = numeroEsclave;
    }

    public byte [] intDeuxBytes (int nombre){
        byte [] nombreDeuxOctets = new byte[2];
        nombreDeuxOctets[0] = (byte) ((nombre & 0xFF00)>> 8);
        nombreDeuxOctets [1] = (byte) ((nombre & 0xFF));
        return nombreDeuxOctets;
    }
    public void connecterEsclave (String port, int vitesse, int donnee, int parite, int stop) throws SerialPortException {
        super.initCom(port);
        super.configurerParametres(vitesse, donnee, parite, stop);
    }
    public float lectureColis (int registre, int bloc){

        byte[] adresse = intDeuxBytes(registre);
        byte msbAdresse = adresse[0];
        byte lsbAdresse = adresse[1];

        byte [] longeur = intDeuxBytes(bloc);
        byte msbLongeur = longeur[0];
        byte lsbLongeur = longeur[1];

        byte [] tabSansCrc16 = {numeroEsclave, (byte) 0x03,msbAdresse, lsbAdresse, msbLongeur, lsbLongeur};
        byte [] tabCrc16 = intDeuxBytes(crc16.calculCrc16(tabSansCrc16));
        byte msbCrc16 = tabCrc16[0];
        byte lsbCrc16 = tabCrc16[1];
        byte [] tabAvecCrc16 = {numeroEsclave, (byte) 0x03,msbAdresse, lsbAdresse, msbLongeur, lsbLongeur, msbCrc16, lsbCrc16};
        super.ecrire(tabAvecCrc16);
        return 0f;
    }
}
