package view;

import controller.Event;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;

public class PortAndAdressDialog extends JDialog{

	private JTextField txPort;
	private JButton btnConnect;
	private JTextField txAdress;
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_PORT = "3000";
	private static final String BUTTON_TEXT = "Connect";
	private static final String DEFAULT_ADRESS = "localhost";

	public PortAndAdressDialog(JFrame frame, ActionListener listener) {
		super(frame, true);
		setSize(300, 300);
		setLocationRelativeTo(frame);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		txPort = new JTextField(DEFAULT_ADRESS);
		txPort.setBorder(BorderFactory.createTitledBorder("Puerto:"));
		add(txPort);
		
		txAdress = new JTextField(DEFAULT_PORT);
		txAdress.setBorder(BorderFactory.createTitledBorder("Direccion:"));
		add(txAdress);
		
		btnConnect = new JButton(BUTTON_TEXT);
		btnConnect.addActionListener(listener);
		btnConnect.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnConnect.setActionCommand(Event.CONNECT.toString());
		btnConnect.setFont(btnConnect.getFont().deriveFont(18.f));
		add(btnConnect);
		
		setVisible(true);
	}
	
	public String getAdress() {
		return txAdress.getText();
	}
	
	public String getPort() {
		return txPort.getText();
	}
}