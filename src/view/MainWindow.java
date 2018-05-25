package view;

import comunications.Request;
import java.awt.Toolkit;
import controller.Event;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame{

	private JButton btnRequest;
	private JTextField txAlert;
	private JComboBox<Request> cbxRequest;
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Client Side";
	private static final String BTN_TEXT = "Send Request";
	private ImageIcon LOGO = new ImageIcon(getClass().getResource("/img/logo.png"));
	
	public MainWindow(ActionListener controller) {
		setTitle(TITLE);
		setSize(500, 500);
		setIconImage(LOGO.getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		
		cbxRequest = new JComboBox<>(Request.values());
		cbxRequest.setBorder(BorderFactory.createTitledBorder("Request"));
		cbxRequest.setFont(cbxRequest.getFont().deriveFont(16.f));
		add(cbxRequest, BorderLayout.NORTH);
		
		txAlert = new JTextField();
		txAlert.setBorder(BorderFactory.createTitledBorder("Word Number"));
		txAlert.setFont(txAlert.getFont().deriveFont(20.f));
		add(txAlert, BorderLayout.CENTER);
		
		btnRequest = new JButton(BTN_TEXT);
		btnRequest.addActionListener(controller);
		btnRequest.setActionCommand(Event.SEND.name());
		btnRequest.setFont(btnRequest.getFont().deriveFont(16.f));
		add(btnRequest, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public void updateAlertTable() {
		
	}
	
	public String getAlert() {
		return txAlert.getText();
	}
	
	public Request getRequest() {
		return ((Request) cbxRequest.getSelectedItem());
	}
}