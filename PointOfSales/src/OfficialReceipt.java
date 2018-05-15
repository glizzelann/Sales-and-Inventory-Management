import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

public class OfficialReceipt {

	private JFrame frmOfficialReceipt;

	/**
	 * Launch the application.
	 */
	private JTextArea textArea;
	
	public static void main(String[] args) {
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public OfficialReceipt() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmOfficialReceipt= new JFrame();
		frmOfficialReceipt.addWindowListener(new WindowAdapter() {
			Connection con = PointOfSales.getConnect();
			@Override
			
			public void windowOpened(WindowEvent arg0) {
				
			}
		});
		frmOfficialReceipt.setBounds(100, 100, 453, 772);
		frmOfficialReceipt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmOfficialReceipt.getContentPane().setLayout(null);
		
		JLabel lblVapeCityManila = new JLabel("Vape City Manila");
		lblVapeCityManila.setFont(new Font("Arial Narrow", Font.PLAIN, 27));
		lblVapeCityManila.setBounds(119, 16, 236, 32);
		frmOfficialReceipt.getContentPane().add(lblVapeCityManila);
		
		JLabel lblStaAnnaManila = new JLabel("Sta. Anna, Manila");
		lblStaAnnaManila.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblStaAnnaManila.setBounds(119, 49, 160, 32);
		frmOfficialReceipt.getContentPane().add(lblStaAnnaManila);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Arial Narrow", Font.PLAIN, 18));
		textArea.setBounds(0, 109, 431, 607);
		frmOfficialReceipt.getContentPane().add(textArea);
		
	}

	public JTextArea getTextArea() {
		return textArea;
	}
	public Window getFrame() {
		// TODO Auto-generated method stub
		return frmOfficialReceipt;
	}
	
	public void setWindowVisible(){
		this.frmOfficialReceipt.setVisible(true);
	}
}
