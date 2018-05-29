package view;

import java.awt.Toolkit;
import controller.Event;
import model.AlertTropel;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import comunications.Request;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends JFrame{

	private JTable table;
	private JButton btnRequest;
	private JTextField txAlert;
	private DefaultTableModel model;
	private JComboBox<Request> cbxRequest;
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Client Side";
	private static final String BTN_TEXT = "Send Request";
	private static final String[] COLUMN_NAME = new String[]{"Alert Date","Info"};
	private ImageIcon LOGO = new ImageIcon(getClass().getResource("/img/logo.png"));
	
	public MainWindow(ActionListener controller) {
		setTitle(TITLE);
		setSize(500, 500);
		setIconImage(LOGO.getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		
		cbxRequest = new JComboBox<>(Request.values());
		cbxRequest.setBorder(BorderFactory.createTitledBorder("Request"));
		cbxRequest.setFont(cbxRequest.getFont().deriveFont(16.f));
		add(cbxRequest);
		
		txAlert = new JTextField();
		txAlert.setBorder(BorderFactory.createTitledBorder("Alert Text"));
		txAlert.setFont(txAlert.getFont().deriveFont(20.f));
		add(txAlert);
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(COLUMN_NAME);
		
		table = new JTable(model);
		add(new JScrollPane(table));
		
		btnRequest = new JButton(BTN_TEXT);
		btnRequest.addActionListener(controller);
		btnRequest.setActionCommand(Event.SEND.name());
		btnRequest.setFont(btnRequest.getFont().deriveFont(16.f));
		add(btnRequest);
	}
	
	public void paintTable(AlertTropel alert) {
			System.out.println(alert.getAlertMoment());
			model.addRow(alert.getAttributeList());
	}
	
	public void paintTable(String[] alert) {
		model.addRow(alert);
	}
	
	public String getAlert() {
		if(txAlert.getText() != null) {
			return txAlert.getText();			
		}else {
			return "-";
		}
	}
	
	public Request getRequest() {
		return ((Request) cbxRequest.getSelectedItem());
	}
}