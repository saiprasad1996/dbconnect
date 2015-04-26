package saidb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import jssc.SerialPortException;

public class Main {

	static DBConnect db = new DBConnect();
	static SerialCom sc = new SerialCom();
	private static BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in));
	private static String port;

	public static void main(String args[]) {

		try {

			db.connect();

			// db.fetchDataFromDB();
			// String cmdstr = db.getString();
			//sc.setPort("COM4");
			// Menu driven
			menu();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SerialPortException e) {
		
			System.out.println("\nThe selected port is busy select another port.");
			return;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Exiting..");
			System.exit(0);
		}

	}

	static void menu() throws IOException, SerialPortException, SQLException {
		while (true) {
			int ch;
			String portlistarr[];
			System.out.println("\n~~~Menu~~~");
			System.out.println("\n1-Send command using default port "
					+ sc.getport());
			System.out.println("2-Set port and send data");
			System.out.println("3-List out the ports"
					+ "\n4-Show data from database"
					+ "\n5-Send data from database to Serial port "
					+ sc.getport());
			System.out.println("\nPress any key to Exit");
			System.out.println("\n\nEnter your choice : ");
			ch = Integer.parseInt(br.readLine());
			String cmdstr;
			switch (ch) {
			case 1:

				System.out.println("Enter the data to send through COM PORT");
				br = new BufferedReader(new InputStreamReader(System.in));
				cmdstr = br.readLine();
				sc.sendcmd(cmdstr);
				break;

			case 2:
				System.out.println("\nList of Ports ");
				portlistarr = sc.getPortList();
				for (String string : portlistarr) {
					System.out.println("\n" + string);
				}
				System.out.println("\nEnter the port to set");
				port = br.readLine();
				sc.setPort(port);
				System.out.println("Enter data to send through " + port);
				br = new BufferedReader(new InputStreamReader(System.in));
				cmdstr = br.readLine();
				sc.sendcmd(cmdstr);
				break;
			case 3:
				System.out.println("\nList of Ports ");
				portlistarr = sc.getPortList();
				for (String string : portlistarr) {
					System.out.println("\n" + string);
				}
				break;
			case 4:
				db.fetchDataFromDB();

				break;
			case 5:
				db.fetchDataFromDB();
				cmdstr = db.getString();
				sc.sendcmd(cmdstr);
				break;
			default:
				System.out.println("Exiting..");
				System.exit(0);
				break;
			}
		}

	}

	void execute() {

	}

}
