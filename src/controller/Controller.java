package controller;

import view.MainWindow;
import java.io.IOException;

import javax.swing.Timer;

import comunications.Client;
import comunications.Request;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{

	private Timer timer;
	private Client client;
	private MainWindow window;
	
	public Controller() {
		try {
			client = new Client();
			window = new MainWindow(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		tableViewManager();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(Event.valueOf(e.getActionCommand())) {
		case SEND:
			send();
			break;
		default:
			break;
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
				// TODO Auto-generated method stub
			}
		});
		
	}

	public static void main(String[] args) {
		new Controller();
	}
}