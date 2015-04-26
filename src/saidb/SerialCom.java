package saidb;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class SerialCom {
	private static SerialPort serialPort;

	String port;

	public static void main(String args[]) {

		try {
			System.out.println("Port Opened : " + serialPort.openPort());
			System.out.println("Params setted : "
					+ serialPort.setParams(9600, 8, 1, 0));
			System.out.println("Data Successfully written to the port : "
					+ serialPort.writeBytes("\nJagannath".getBytes()));

			System.out.println("Port Closed : " + serialPort.closePort());

		} catch (SerialPortException ex) {
			ex.printStackTrace();
		}
	}

	void sendcmd(String cmd) throws SerialPortException {
		serialPort.writeBytes(cmd.getBytes());
		System.out.println("\nData sent successfully");
	}

	void setPort(String port) throws SerialPortException {
		this.port = port;
		serialPort = new SerialPort(this.port);
		serialPort.openPort();
		serialPort.setParams(9600, 8, 1, 0);
	}

	String getport() {
		return this.port;
	}

	String[] getPortList() {
		return SerialPortList.getPortNames();
	}
}
