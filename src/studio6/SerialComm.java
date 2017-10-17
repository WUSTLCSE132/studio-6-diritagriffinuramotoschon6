package studio6;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	
	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method from Studio 5
	public void writeByte(byte m) {
		try {
			port.writeByte(m);
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
		if(debug) {
			//https://stackoverflow.com/questions/1748021/how-to-print-bytes-in-hexadecimal-using-system-out-println
			System.out.println(String.format("0x%02X", m));
		}
	}
	
	// TODO: Add available() method
	public boolean available() {
		try {
			if(port.getInputBufferBytesCount() > 0) {
				return true;
			}
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	// TODO: Add readByte() method	
	public byte readByte() {
		byte b = 0;
		
		try {
			b = port.readBytes(1)[0];
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(debug) {
			System.out.println(String.format("0x%02X", b));
			return b;
		}else {
			return b;
		}
	}
	
	// TODO: Add a main() method
	public static void main(String[] args) throws SerialPortException {
		SerialComm comm = new SerialComm("COM4");
		comm.setDebug(true);
		while(true) {
			if(comm.available()) {
				byte b = comm.readByte();
				System.out.println((char)b);
			}
		}
	}
}
