package comunications;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {

	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean stop;

	public Client(String adress, String port) throws IOException {
		this.connection = new Socket(adress, Integer.parseInt(port));
		input = new DataInputStream(connection.getInputStream());
		output = new DataOutputStream(connection.getOutputStream());
		start();
	}

	@Override
	public void run() {
		while (!stop) {
			String response;
			try {
				response = input.readUTF();
				if (response != null) {
					manageResponse(response);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void manageResponse(String response) throws IOException {
		switch (Response.valueOf(response)) {
		case ALERT:
			consumeAlertService();
			break;
		case ALERT_WITH_TEXT:
			consumeAlertWithTextService();
			break;
		default:
			break;
		}
	}

	public String[] consumeAlertWithTextService() throws IOException {
		return new String[] {input.readUTF(), input.readUTF()};
	}

	public String consumeAlertService() throws IOException {
		return input.readUTF();
	}

	public void requestAlertWithText(String text) throws IOException {
		output.writeUTF(Request.ALERT_WITH_TEXT.toString());
		output.writeUTF(text);
	}
	
	public void requestAlert() throws IOException {
		output.writeUTF(Request.ALERT.toString());
	}
}