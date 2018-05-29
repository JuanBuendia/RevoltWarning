package controller;

import view.MainWindow;
import model.AlertTropel;
import javax.swing.Timer;
import java.io.IOException;
import comunications.Client;
import comunications.Request;
import view.PortAndAdressDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{

	private Timer timer;
	private Client client;
	private MainWindow window;
	private PortAndAdressDialog dialog;
	
	public Controller() {
		window = new MainWindow(this);
		dialog = new PortAndAdressDialog(window, this);
		tableViewManager();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(Event.valueOf(e.getActionCommand())) {
		case SEND:
			send();
		case CONNECT:
			connect();
			break;
		default:
			break;
		}
	}

	private void connect() {
		dialog.setVisible(false);
		try {
			client = new Client(dialog.getAdress(), dialog.getPort());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void send() {
		if(window.getRequest().equals(Request.ALERT_WITH_TEXT)) {
			try {
				client.requestAlertWithText(window.getAlert());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				client.requestAlert();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void tableViewManager() {
		timer = new Timer(5000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(client.consumeAlertService() != null) {
						window.paintTable(new AlertTropel("-", client.consumeAlertService()));
						window.repaint();
					}else if(client.consumeAlertWithTextService() != null) {
						window.paintTable(client.consumeAlertWithTextService());
						window.repaint();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public void stopTimer() {
		timer.stop();
	}

	public static void main(String[] args) {
		new Controller();
	}
}