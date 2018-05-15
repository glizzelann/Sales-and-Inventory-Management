
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class AdminAccess {

	private static JFrame frmVcmSalesAnd;
	static JLabel lbl_admin;
	static JTable tbl_user;
	public JButton btnRemove;
	private JTextField txt_userid;
	private JTextField txt_lastname;
	private JTextField txt_firstname;
	private JTextField txt_username;
	private JPasswordField passwordField;
	private JTextField txt_userlevel;
	static JTextField lblHeaderTime;
	static JTextField lblHeaderDate;
	static JTextField aps;
	static JTable tbl_productInventory;
	private JTextField txt_productcode;
	private JTextField txt_productName;
	private JTextField txt_price;
	private JTextField txt_productQuantity;
	private JTextField txt_productCode;
	private JTextField txt_productPrice;
	private JTextField txt_quantity;
	private JTextField txt_subtotal;
	private JTable tbl_summary;
	private JTextField txt_totalAmount;
	private JTextField txt_amountTendered;
	private JTextField txt_change;
	static JTextField txt_time;
	static JTextField txt_date;
	static JTextField txt_aps;
	static JTable tbl_warning;
	Connection con = null;
	ResultSet result = null;
	PreparedStatement statement = null;
	Statement statement1 = null;
	DefaultTableModel mod;
	static JTable table_reports;
	private JTextField txt_accessCode;
	private JTextField txt_stocksonhand;
	static JTable tbl_loginHistory;
	
	private OfficialReceipt officialReceipt = new OfficialReceipt();

	// Login log = new Login();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		AdminAccess window = new AdminAccess();
		window.frmVcmSalesAnd.setVisible(true);

	}

	/**
	 * Create the application.
	 */

	public AdminAccess() {
		initialize();
		myMethods.adminMethods.loginHistory_table();
		myMethods.adminMethods.classname();
		myMethods.adminMethods.user_table();
		myMethods.adminMethods.table_warning();
		myMethods.adminMethods.productInventory_table();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmVcmSalesAnd = new JFrame();
		frmVcmSalesAnd.setTitle("VCM Sales and Inventory");
		frmVcmSalesAnd.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {

				mod = new DefaultTableModel();
				TableColumnModel model = tbl_summary.getColumnModel();
				tbl_summary.setModel(mod);
				mod.addColumn("Product Code");
				mod.addColumn("Product Name");
				mod.addColumn("Price");
				mod.addColumn("Quantity");
				mod.addColumn("Subtotal");
				
			}
			
		});
		frmVcmSalesAnd.setResizable(false);
		frmVcmSalesAnd.getContentPane().setBackground(new Color(255, 255, 204));
		frmVcmSalesAnd.setBounds(100, 100, 1960, 1880);
		frmVcmSalesAnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVcmSalesAnd.getContentPane().setLayout(null);
		frmVcmSalesAnd.setLocationRelativeTo(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (tabbedPane.getSelectedIndex() == 5) {

					// String logout = "update loginhistory set logoutDate = ?
					// where "
					frmVcmSalesAnd.dispose();
					new Login().getFrame().setVisible(true);
				}

			}
		});
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		tabbedPane.setBounds(0, 154, 1902, 874);
		frmVcmSalesAnd.getContentPane().add(tabbedPane);

		JPanel userMaintenance = new JPanel();
		userMaintenance.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		userMaintenance.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("User Maintenance", null, userMaintenance, null);
		tabbedPane.setEnabledAt(0, true);
		userMaintenance.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(15, 40, 1338, 783);
		userMaintenance.add(scrollPane);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(1379, 85, 503, 601);
		userMaintenance.add(panel);
		panel.setLayout(null);

		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setForeground(new Color(255, 255, 204));
		lblUserId.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblUserId.setBounds(79, 23, 148, 20);
		panel.add(lblUserId);

		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setForeground(new Color(255, 255, 204));
		lblLastName.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblLastName.setBounds(79, 78, 148, 20);
		panel.add(lblLastName);

		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setForeground(new Color(255, 255, 204));
		lblFirstName.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblFirstName.setBounds(79, 126, 148, 20);
		panel.add(lblFirstName);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(255, 255, 204));
		lblUsername.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblUsername.setBounds(79, 174, 148, 20);
		panel.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 255, 204));
		lblPassword.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblPassword.setBounds(79, 222, 148, 20);
		panel.add(lblPassword);

		JLabel lblUserLevel = new JLabel("User level");
		lblUserLevel.setForeground(new Color(255, 255, 204));
		lblUserLevel.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblUserLevel.setBounds(79, 280, 148, 20);
		panel.add(lblUserLevel);

		txt_userid = new JTextField();
		txt_userid.setForeground(new Color(0, 0, 0));
		txt_userid.setEditable(false);
		txt_userid.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_userid.setBounds(248, 16, 246, 35);
		panel.add(txt_userid);
		txt_userid.setColumns(10);

		txt_lastname = new JTextField();
		txt_lastname.setForeground(new Color(0, 0, 0));
		txt_lastname.setEditable(false);
		txt_lastname.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_lastname.setBounds(248, 75, 246, 35);
		panel.add(txt_lastname);
		txt_lastname.setColumns(10);

		txt_firstname = new JTextField();
		txt_firstname.setForeground(new Color(0, 0, 0));
		txt_firstname.setEditable(false);
		txt_firstname.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_firstname.setBounds(248, 123, 246, 35);
		panel.add(txt_firstname);
		txt_firstname.setColumns(10);

		txt_username = new JTextField();
		txt_username.setForeground(new Color(0, 0, 0));
		txt_username.setEditable(false);
		txt_username.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_username.setBounds(248, 171, 246, 35);
		panel.add(txt_username);
		txt_username.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(0, 0, 0));
		passwordField.setEditable(false);
		passwordField.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		passwordField.setBounds(248, 222, 246, 35);
		panel.add(passwordField);

		txt_userlevel = new JTextField();
		txt_userlevel.setForeground(new Color(0, 0, 0));

		txt_userlevel.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_userlevel.setEditable(false);
		txt_userlevel.setText("Employee");
		txt_userlevel.setBounds(248, 277, 246, 35);
		panel.add(txt_userlevel);
		txt_userlevel.setColumns(10);

		JComboBox cmb_employeeStatus = new JComboBox();
		cmb_employeeStatus.setForeground(new Color(0, 0, 0));
		cmb_employeeStatus.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		cmb_employeeStatus.setModel(new DefaultComboBoxModel(new String[] { "Active", "Inactive" }));
		cmb_employeeStatus.setEditable(true);
		cmb_employeeStatus.setBounds(248, 340, 246, 29);
		panel.add(cmb_employeeStatus);

		JButton btnEditUser = new JButton("Edit");

		btnEditUser.setVisible(false);
		btnEditUser.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		btnEditUser.setBounds(130, 439, 115, 29);
		panel.add(btnEditUser);
		try{
			if ((txt_userlevel.getText()=="Admin")&&(txt_userlevel.getText()=="Super Admin")){
				btnEditUser.setEnabled(false);
			}
			else{
				btnEditUser.setEnabled(true);
			}
		}catch(Exception ab){
			ab.printStackTrace();
		}

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setVisible(false);
		btnUpdate.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		btnUpdate.setBounds(258, 439, 115, 29);
		panel.add(btnUpdate);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(new Color(255, 255, 204));
		lblStatus.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblStatus.setBounds(79, 339, 148, 20);
		panel.add(lblStatus);

		JButton btnCancelUser = new JButton("Cancel");
		btnCancelUser.setVisible(false);
		btnCancelUser.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		btnCancelUser.setBounds(388, 438, 115, 30);
		panel.add(btnCancelUser);

		JButton btnSaveUser = new JButton("Save");
		btnSaveUser.setVisible(false);
		btnSaveUser.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		btnSaveUser.setBounds(0, 438, 115, 29);
		panel.add(btnSaveUser);

		tbl_user = new JTable();
		
		tbl_user.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tbl_user.setRowHeight(25);
		tbl_user.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		scrollPane.setViewportView(tbl_user);

		JButton btnNew = new JButton("New");
		btnNew.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		btnNew.setBounds(1379, 39, 115, 29);
		userMaintenance.add(btnNew);

		JPanel productInventory = new JPanel();
		productInventory.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Product Inventory", null, productInventory, null);
		productInventory.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setAutoscrolls(true);
		scrollPane_1.setBounds(15, 65, 1287, 661);
		productInventory.add(scrollPane_1);

		tbl_productInventory = new JTable(){
			DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();
			DefaultTableCellRenderer renderLeft = new DefaultTableCellRenderer();

		    { // initializer block
		        renderRight.setHorizontalAlignment(SwingConstants.RIGHT);
		        renderLeft.setHorizontalAlignment(SwingConstants.LEFT);
		    }

		    @Override
		    public TableCellRenderer getCellRenderer (int row, int col) {
		    	if (col==2)
		    		return renderRight;
		    	else if (col==3)
		    		return renderRight;
		    	else
		    		return renderLeft;
		    }
		};

		tbl_productInventory.setAlignmentX(Component.RIGHT_ALIGNMENT);
		tbl_productInventory.setRowHeight(25);
		
		tbl_productInventory.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		scrollPane_1.setViewportView(tbl_productInventory);
		scrollPane_1.setPreferredSize(new Dimension(480, 300));
		tbl_productInventory.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		tbl_productInventory.setShowGrid(false);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setAutoscrolls(true);
		panel_1.setBounds(1317, 138, 565, 449);
		productInventory.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblProductCode = new JLabel("Product Code");
		lblProductCode.setForeground(new Color(255, 255, 204));
		lblProductCode.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblProductCode.setBounds(60, 61, 186, 20);
		panel_1.add(lblProductCode);

		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setForeground(new Color(255, 255, 204));
		lblProductName.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblProductName.setBounds(60, 114, 186, 20);
		panel_1.add(lblProductName);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(255, 255, 204));
		lblPrice.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblPrice.setBounds(60, 171, 186, 20);
		panel_1.add(lblPrice);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(new Color(255, 255, 204));
		lblQuantity.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblQuantity.setBounds(60, 225, 186, 27);
		panel_1.add(lblQuantity);

		txt_productcode = new JTextField();
		txt_productcode.setEditable(false);
		txt_productcode.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		txt_productcode.setBounds(290, 58, 235, 30);
		panel_1.add(txt_productcode);
		txt_productcode.setColumns(10);

		txt_productName = new JTextField();
		txt_productName.setEditable(false);
		txt_productName.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		txt_productName.setBounds(290, 111, 235, 30);
		panel_1.add(txt_productName);
		txt_productName.setColumns(10);

		txt_price = new JTextField();
		txt_price.setEditable(false);
		txt_price.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		txt_price.setBounds(290, 168, 235, 30);
		panel_1.add(txt_price);
		txt_price.setColumns(10);

		txt_productQuantity = new JTextField();
		txt_productQuantity.setEditable(false);
		txt_productQuantity.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		txt_productQuantity.setBounds(290, 222, 235, 30);
		panel_1.add(txt_productQuantity);
		txt_productQuantity.setColumns(10);

		JButton btn_saveProduct = new JButton("Save");
		btn_saveProduct.setVisible(false);
		btn_saveProduct.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		btn_saveProduct.setBounds(0, 308, 115, 29);
		panel_1.add(btn_saveProduct);

		JButton btn_editProduct = new JButton("Edit");
		btn_editProduct.setVisible(false);
		btn_editProduct.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		btn_editProduct.setBounds(146, 308, 115, 29);
		panel_1.add(btn_editProduct);

		JButton btn_updateProduct = new JButton("Update");
		btn_updateProduct.setVisible(false);
		btn_updateProduct.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		btn_updateProduct.setBounds(290, 308, 115, 29);
		panel_1.add(btn_updateProduct);

		JButton btn_cancelProduct = new JButton("Cancel");

		btn_cancelProduct.setVisible(false);
		btn_cancelProduct.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		btn_cancelProduct.setBounds(435, 310, 115, 29);
		panel_1.add(btn_cancelProduct);

		JButton btn_newProduct = new JButton("New");

		btn_newProduct.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		btn_newProduct.setBounds(1317, 81, 115, 29);
		productInventory.add(btn_newProduct);

		mod = new DefaultTableModel();

		JPanel transactions = new JPanel();
		transactions.setBorder(null);
		transactions.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Transactions", null, transactions, null);
		transactions.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBounds(76, 216, 624, 451);
		transactions.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblProductCode_1 = new JLabel("Product Code");
		lblProductCode_1.setForeground(new Color(255, 255, 204));
		lblProductCode_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		lblProductCode_1.setBounds(29, 49, 173, 37);
		panel_2.add(lblProductCode_1);

		JLabel lblProductName_1 = new JLabel("Product Name");
		lblProductName_1.setForeground(new Color(255, 255, 204));
		lblProductName_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		lblProductName_1.setBounds(29, 115, 173, 37);
		panel_2.add(lblProductName_1);

		JComboBox cmb_productName = new JComboBox();
		cmb_productName.setModel(new DefaultComboBoxModel(new String[] { "Select Item" }));
		cmb_productName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/admin?AutoReconnect=true&useSSL=false", "root", "1234");
					statement1 = con.createStatement();
					String s = "Select ProductName from productinventory";
					result = statement1.executeQuery(s);
					while (result.next()) {
						cmb_productName.addItem(result.getString(1));

					}

				} catch (Exception ab) {
					JOptionPane.showMessageDialog(null, ab);
					ab.printStackTrace();
				}

				String P_Name = cmb_productName.getSelectedItem().toString();
				try {

					String query1 = "Select * from productinventory where ProductName = '" + P_Name + "'&& Stock > 0";

					statement = PointOfSales.getConnect().prepareStatement(query1);

					result = statement.executeQuery();

					if (result.next()) {

						txt_productCode.setText(result.getString("ProductCode"));
						txt_productName.setText(result.getString("ProductName"));
						txt_productPrice.setText(result.getString("Price"));
						txt_stocksonhand.setText(result.getString("Stock"));
					} else {
						JOptionPane.showMessageDialog(null, "Out of Stocks!");
						txt_productCode.setText(" ");
						txt_productName.setText(" ");
						txt_productPrice.setText(" ");
					}

				} catch (Exception io) {
					JOptionPane.showMessageDialog(null, io);
					io.printStackTrace();
				}
			}
		});
		cmb_productName.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		cmb_productName.setBounds(232, 115, 377, 37);
		panel_2.add(cmb_productName);

		JLabel lblPrice_1 = new JLabel("Price");
		lblPrice_1.setForeground(new Color(255, 255, 204));
		lblPrice_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		lblPrice_1.setBounds(29, 181, 173, 37);
		panel_2.add(lblPrice_1);

		txt_productCode = new JTextField();
		txt_productCode.setEditable(false);
		txt_productCode.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		txt_productCode.setBounds(232, 49, 377, 37);
		panel_2.add(txt_productCode);
		txt_productCode.setColumns(10);

		JLabel lblQuantity_1 = new JLabel("Quantity");
		lblQuantity_1.setForeground(new Color(255, 255, 204));
		lblQuantity_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		lblQuantity_1.setBounds(29, 245, 173, 37);
		panel_2.add(lblQuantity_1);

		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setForeground(new Color(255, 255, 204));
		lblSubtotal.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		lblSubtotal.setBounds(29, 309, 173, 37);
		panel_2.add(lblSubtotal);

		txt_productPrice = new JTextField();
		txt_productPrice.setEditable(false);
		txt_productPrice.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		txt_productPrice.setBounds(232, 181, 377, 37);
		panel_2.add(txt_productPrice);
		txt_productPrice.setColumns(10);

		txt_quantity = new JTextField();
		txt_quantity.addKeyListener(new KeyAdapter() {

			String ab;

			public void keyReleased(KeyEvent arg0) {

				char c = arg0.getKeyChar();
				if (!(Character.isDigit(c))) {
					arg0.consume();
				}
				try {

					if (!txt_productPrice.equals("")) {
						String price = txt_productPrice.getText();
						double price1 = Double.parseDouble(price);
						String quantity = txt_quantity.getText();
						if (!quantity.equals("")) {
							// System.out.println("quantity = "+quantity);
							double quantity1 = Double.parseDouble(quantity);

							double subtotal = price1 * quantity1;

							ab = Double.toString(subtotal);
							// String abb = Integer.toString(subtotal);
							double abbb1 = Double.parseDouble(ab);
							double tota = subtotal + abbb1;
							String tot = Double.toString(tota);
							// String pri=String.valueOf(subtotal);
							txt_subtotal.setText(ab);
							// txt_total.setText(tot);
						}
					}
				} catch (NumberFormatException ac) {
					JOptionPane.showMessageDialog(null, ac);
					ac.printStackTrace();
				}
			}

		});
		txt_quantity.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		txt_quantity.setBounds(232, 245, 377, 37);
		panel_2.add(txt_quantity);
		txt_quantity.setColumns(10);

		txt_subtotal = new JTextField();
		txt_subtotal.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		txt_subtotal.setBounds(232, 309, 377, 37);
		panel_2.add(txt_subtotal);
		txt_subtotal.setColumns(10);
		tbl_summary = new JTable(){
			DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();
			DefaultTableCellRenderer renderLeft = new DefaultTableCellRenderer();

		    { // initializer block
		        renderRight.setHorizontalAlignment(SwingConstants.RIGHT);
		        renderLeft.setHorizontalAlignment(SwingConstants.LEFT);
		    }

		    @Override
		    public TableCellRenderer getCellRenderer (int row, int col) {
		    	if (col==2)
		    		return renderRight;
		    	else
		    		return renderLeft;
		    }
		};
		
		tbl_summary.setAlignmentX(Component.RIGHT_ALIGNMENT);
		tbl_summary.setRowHeight(35);
		tbl_summary.setModel(mod);
		tbl_summary.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

		JButton btnA = new JButton("ADD TO CART");

		btnA.setFont(new Font("Bookman Old Style", Font.PLAIN, 26));
		btnA.setBounds(137, 383, 225, 40);
		panel_2.add(btnA);

		txt_stocksonhand = new JTextField();
		txt_stocksonhand.setBackground(new Color(0, 0, 0));
		txt_stocksonhand.setForeground(new Color(255, 255, 204));
		txt_stocksonhand.setEditable(false);
		txt_stocksonhand.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		txt_stocksonhand.setBounds(232, 7, 81, 30);
		panel_2.add(txt_stocksonhand);
		txt_stocksonhand.setColumns(10);

		JLabel lblStocks = new JLabel("Stocks");
		lblStocks.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblStocks.setForeground(new Color(255, 255, 204));
		lblStocks.setBounds(29, 6, 128, 30);
		panel_2.add(lblStocks);

		JScrollPane scrollPane_2 = new JScrollPane();

		scrollPane_2.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		scrollPane_2.setAutoscrolls(true);
		scrollPane_2.setBounds(1036, 216, 794, 451);
		transactions.add(scrollPane_2);
		scrollPane_2.setViewportView(tbl_summary);

		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setForeground(new Color(255, 255, 204));
		lblTotalAmount.setFont(new Font("Bookman Old Style", Font.PLAIN, 26));
		lblTotalAmount.setBounds(733, 260, 248, 30);
		transactions.add(lblTotalAmount);

		JLabel lblAmountGive = new JLabel("Amount Give");
		lblAmountGive.setForeground(new Color(255, 255, 204));
		lblAmountGive.setFont(new Font("Bookman Old Style", Font.PLAIN, 26));
		lblAmountGive.setBounds(733, 370, 248, 20);
		transactions.add(lblAmountGive);

		JLabel lblChange = new JLabel("Change");
		lblChange.setForeground(new Color(255, 255, 204));
		lblChange.setFont(new Font("Bookman Old Style", Font.PLAIN, 26));
		lblChange.setBounds(734, 462, 248, 30);
		transactions.add(lblChange);

		txt_totalAmount = new JTextField();
		txt_totalAmount.setText("0");
		txt_totalAmount.setEditable(false);
		txt_totalAmount.setFont(new Font("Bookman Old Style", Font.PLAIN, 26));
		txt_totalAmount.setBounds(733, 306, 248, 37);
		transactions.add(txt_totalAmount);
		txt_totalAmount.setColumns(10);

		txt_amountTendered = new JTextField();

		txt_amountTendered.setFont(new Font("Bookman Old Style", Font.PLAIN, 26));
		txt_amountTendered.setBounds(734, 409, 248, 37);
		transactions.add(txt_amountTendered);
		txt_amountTendered.setColumns(10);
		txt_change = new JTextField();
		txt_change.setEditable(false);
		txt_change.setFont(new Font("Bookman Old Style", Font.PLAIN, 26));
		txt_change.setBounds(734, 508, 248, 37);
		transactions.add(txt_change);
		txt_change.setColumns(10);

		JButton btnSubmit = new JButton("Submit");

		btnSubmit.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		btnSubmit.setBounds(769, 572, 168, 30);
		transactions.add(btnSubmit);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(76, 16, 624, 189);
		transactions.add(scrollPane_3);

		tbl_warning = new JTable();
		scrollPane_3.setViewportView(tbl_warning);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Enter Access Code!");
				txt_accessCode.show();
			}
		});
		btnRemove.hide();
		btnRemove.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		btnRemove.setBounds(1036, 696, 168, 35);
		transactions.add(btnRemove);

		txt_accessCode = new JTextField();

		txt_accessCode.hide();
		txt_accessCode.setBounds(1239, 699, 182, 35);
		transactions.add(txt_accessCode);
		txt_accessCode.setColumns(10);

		JPanel reports = new JPanel();
		reports.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Reports", null, reports, null);
		reports.setLayout(null);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		scrollPane_4.setBounds(641, 129, 1193, 629);
		reports.add(scrollPane_4);

		table_reports = new JTable(){
			DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();
			DefaultTableCellRenderer renderLeft = new DefaultTableCellRenderer();

			{ // initializer block
				renderRight.setHorizontalAlignment(SwingConstants.RIGHT);
				renderLeft.setHorizontalAlignment(SwingConstants.LEFT);
			}

			@Override
			public TableCellRenderer getCellRenderer(int row, int col) {
				if (col == 2)
					return renderRight;
				else if (col == 3)
					return renderRight;
				else if (col == 4)
					return renderRight;
				else if (col == 5)
					return renderRight;
				else
					return renderLeft;
			}
		};
		
		table_reports.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table_reports.setRowHeight(30);
		table_reports.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		scrollPane_4.setViewportView(table_reports);

		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblStartDate.setForeground(new Color(255, 255, 204));
		lblStartDate.setBounds(44, 197, 157, 38);
		reports.add(lblStartDate);

		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblEndDate.setForeground(new Color(255, 255, 204));
		lblEndDate.setBounds(44, 276, 157, 38);
		reports.add(lblEndDate);

		JComboBox cmb_reports = new JComboBox();
		cmb_reports.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		cmb_reports.setModel(new DefaultComboBoxModel(new String[] { "All", "Stocks", "Sales", "Pulled-out" }));
		cmb_reports.setBounds(44, 138, 214, 38);
		reports.add(cmb_reports);

		JButton btnNewButton = new JButton("Print");

		btnNewButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		btnNewButton.setBounds(231, 422, 150, 35);
		reports.add(btnNewButton);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		dateChooser.setDateFormatString("MM-dd-yyyy");
		dateChooser.setBounds(217, 197, 278, 38);
		reports.add(dateChooser);

		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		dateChooser_1.setDateFormatString("MM-dd-yyyy");
		dateChooser_1.setBounds(216, 276, 279, 38);
		reports.add(dateChooser_1);

		JButton btnShow = new JButton("Show");

		btnShow.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		btnShow.setBounds(231, 351, 150, 35);
		reports.add(btnShow);

		JPanel loginhistory = new JPanel();
		loginhistory.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Login History", null, loginhistory, null);
		loginhistory.setLayout(null);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(124, 86, 1627, 619);
		loginhistory.add(scrollPane_5);

		tbl_loginHistory = new JTable();
		scrollPane_5.setViewportView(tbl_loginHistory);
		tbl_loginHistory.setRowHeight(35);
		tbl_loginHistory.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));

		JTabbedPane logout = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.addTab("Logout", null, logout, null);

		JLabel lblVapeCityManila = new JLabel("VAPE CITY MANILA");
		lblVapeCityManila.setBackground(new Color(0, 0, 0));
		lblVapeCityManila.setForeground(new Color(0, 0, 0));
		lblVapeCityManila.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
		lblVapeCityManila.setBounds(390, 16, 516, 44);
		frmVcmSalesAnd.getContentPane().add(lblVapeCityManila);

		JLabel lbl_userType = new JLabel("Admin");
		lbl_userType.setBackground(new Color(0, 0, 0));
		lbl_userType.setForeground(new Color(0, 0, 0));
		lbl_userType.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		lbl_userType.setBounds(260, 83, 90, 26);
		frmVcmSalesAnd.getContentPane().add(lbl_userType);

		lbl_admin = new JLabel("");
		lbl_admin.setBackground(new Color(0, 0, 0));
		lbl_admin.setForeground(new Color(0, 0, 0));
		lbl_admin.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		lbl_admin.setBounds(260, 115, 357, 34);
		frmVcmSalesAnd.getContentPane().add(lbl_admin);

		JLabel lblVCMIcon = new JLabel("");
		lblVCMIcon.setBounds(15, 16, 167, 128);
		frmVcmSalesAnd.getContentPane().add(lblVCMIcon);

		txt_time = new JTextField();
		txt_time.setBackground(new Color(255, 255, 204));
		txt_time.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_time.setForeground(new Color(0, 0, 0));
		txt_time.setEditable(false);
		txt_time.setBounds(1407, 55, 215, 26);
		frmVcmSalesAnd.getContentPane().add(txt_time);
		txt_time.setColumns(10);

		txt_date = new JTextField();
		txt_date.setBackground(new Color(255, 255, 204));
		txt_date.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_date.setForeground(new Color(0, 0, 0));
		txt_date.setEditable(false);
		txt_date.setBounds(1407, 99, 215, 26);
		frmVcmSalesAnd.getContentPane().add(txt_date);
		txt_date.setColumns(10);

		txt_aps = new JTextField();
		txt_aps.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_aps.setEditable(false);
		txt_aps.setBounds(1637, 55, 75, 26);
		frmVcmSalesAnd.getContentPane().add(txt_aps);
		txt_aps.setColumns(10);

		JLabel lblAdminHeader = new JLabel("");
		lblAdminHeader.setBackground(new Color(255, 255, 204));
		lblAdminHeader.setBounds(0, 0, 1902, 149);
		frmVcmSalesAnd.getContentPane().add(lblAdminHeader);

		btnUpdate.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				con = PointOfSales.getConnect();

				String userlevel = cmb_employeeStatus.getSelectedItem().toString();

				try {

					if (txt_userid.getText().isEmpty() && (passwordField.getText().isEmpty())
							&& (txt_lastname.getText().isEmpty()) && (txt_firstname.getText().isEmpty())
							&& (txt_username.getText().isEmpty()) && (txt_userlevel.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Fields must not be empty");
						// unused
					} else if (!(txt_userid.getText().isEmpty()) && (txt_lastname.getText().isEmpty())
							&& (txt_firstname.getText().isEmpty()) && (txt_username.getText().isEmpty())
							&& (passwordField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userid.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& (txt_firstname.getText().isEmpty()) && (txt_username.getText().isEmpty())
							&& (passwordField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userid.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& !(txt_firstname.getText().isEmpty()) && (txt_username.getText().isEmpty())
							&& (passwordField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userid.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& !(txt_firstname.getText().isEmpty()) && !(txt_username.getText().isEmpty())
							&& (passwordField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");

					} else {

						String updateProduct1 = "update user_accnt set user_username = ?,user_password = ?, user_status =? where user_id = ?";
						PreparedStatement ps1 = con.prepareStatement(updateProduct1);

						ps1.setString(1, txt_username.getText());
						ps1.setString(2, passwordField.getText());
						ps1.setString(3, userlevel);
						ps1.setString(4, txt_userid.getText());
						ps1.executeUpdate();

						JOptionPane.showMessageDialog(null, "User information Updated!");

					}
					txt_userid.setText(null);
					txt_lastname.setEditable(false);
					txt_firstname.setEditable(false);
					txt_username.setEditable(false);
					passwordField.setEditable(false);

					txt_lastname.setText(null);
					txt_firstname.setText(null);
					txt_username.setText(null);
					passwordField.setText(null);

					btnCancelUser.setVisible(false);
					btnSaveUser.setVisible(false);
					btnUpdate.setVisible(false);
					btnEditUser.setVisible(false);
					myMethods.adminMethods.user_table();

				} catch (Exception io) {
					JOptionPane.showMessageDialog(null, io);
					io.printStackTrace();
				}

			}
		});
		btnCancelUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSaveUser.setVisible(false);
				btnEditUser.setVisible(false);
				btnUpdate.setVisible(false);
				btnCancelUser.setVisible(false);
				txt_lastname.setEditable(false);
				txt_firstname.setEditable(false);
				txt_username.setEditable(false);
				passwordField.setEditable(false);
				cmb_employeeStatus.setEnabled(false);
				txt_lastname.setText(" ");
				txt_firstname.setText(" ");
				txt_username.setText(" ");
				passwordField.setText(null);
				txt_userid.setText(null);
				btnNew.setEnabled(true);
			}
		});
		
		btnEditUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				txt_lastname.setEditable(true);
				txt_firstname.setEditable(true);
				txt_username.setEditable(true);
				passwordField.setEditable(true);
				cmb_employeeStatus.setEnabled(true);
				btnNew.setEnabled(false);
			}
		});
		btnSaveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				con = PointOfSales.getConnect();

				String userStatus = cmb_employeeStatus.getSelectedItem().toString();

				try {

					if (txt_userid.getText().isEmpty() && (passwordField.getText().isEmpty())
							&& (txt_lastname.getText().isEmpty()) && (txt_firstname.getText().isEmpty())
							&& (txt_username.getText().isEmpty()) && (txt_userlevel.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Fields must not be empty");
						// unused
					} else if (!(txt_userid.getText().isEmpty()) && (txt_lastname.getText().isEmpty())
							&& (txt_firstname.getText().isEmpty()) && (txt_username.getText().isEmpty())
							&& (passwordField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userid.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& (txt_firstname.getText().isEmpty()) && (txt_username.getText().isEmpty())
							&& (passwordField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userid.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& !(txt_firstname.getText().isEmpty()) && (txt_username.getText().isEmpty())
							&& (passwordField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userid.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& !(txt_firstname.getText().isEmpty()) && !(txt_username.getText().isEmpty())
							&& (passwordField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userid.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& !(txt_firstname.getText().isEmpty()) && !(txt_username.getText().isEmpty())
							&& !(passwordField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userid.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& !(txt_firstname.getText().isEmpty()) && !(txt_username.getText().isEmpty())
							&& !(passwordField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else {
						String sql = ("Insert into user_accnt (user_lnamel,user_Fname,user_username,user_password,user_level,user_status)values(?,?,?,?,?,?,?)");

						PreparedStatement pst = PointOfSales.getConnect().prepareStatement(sql);

						pst.setString(1, txt_lastname.getText());
						pst.setString(2, txt_firstname.getText());
						pst.setString(3, txt_username.getText());
						pst.setString(4, passwordField.getText());
						pst.setString(5, txt_userlevel.getText());
						pst.setString(6, userStatus);

						pst.execute();
						JOptionPane.showMessageDialog(null, "User Added!");

					}

				} catch (Exception io) {
					JOptionPane.showMessageDialog(null, io);
					io.printStackTrace();
				}

				txt_lastname.setEditable(false);
				txt_firstname.setEditable(false);
				txt_username.setEditable(false);
				passwordField.setEditable(false);
				txt_userlevel.setEditable(false);

				txt_userid.setText("NEW");
				txt_lastname.setText(null);
				txt_firstname.setText(null);
				txt_username.setText(null);
				passwordField.setText(null);
				txt_userlevel.setText(null);
				btnCancelUser.setVisible(false);
				btnSaveUser.setVisible(false);
				btnUpdate.setVisible(false);
				btnEditUser.setVisible(false);
				myMethods.adminMethods.user_table();
			}
		});
		tbl_user.addMouseListener(new MouseAdapter() {
			@Override

			public void mouseClicked(MouseEvent arg0) {
				
				String userId = tbl_user.getValueAt(tbl_user.getSelectedRow(), 0).toString();
				String userLastName = tbl_user.getValueAt(tbl_user.getSelectedRow(), 1).toString();
				String userFirstName = tbl_user.getValueAt(tbl_user.getSelectedRow(), 2).toString();
				String userUsername = tbl_user.getValueAt(tbl_user.getSelectedRow(), 3).toString();
				String userPassword = tbl_user.getValueAt(tbl_user.getSelectedRow(), 4).toString();
				String userLevel = tbl_user.getValueAt(tbl_user.getSelectedRow(), 5).toString();
				String userStatus = tbl_user.getValueAt(tbl_user.getSelectedRow(), 6).toString();
				if ((userLevel.equals("Admin"))) {
					btnEditUser.setEnabled(false);
					
				}else if ((userLevel.equals("Super Admin"))){
					btnEditUser.setEnabled(false);
				}

				else {
				btnEditUser.setEnabled(true);
				btnEditUser.setVisible(true);
				btnUpdate.setVisible(true);
				btnSaveUser.setVisible(true);
				btnSaveUser.setEnabled(false);
				btnCancelUser.setVisible(true);

				txt_userid.setText(userId);
				txt_lastname.setText(userLastName);
				txt_firstname.setText(userFirstName);
				txt_username.setText(userUsername);
				passwordField.setText(userPassword);
				txt_userlevel.setText(userLevel);
			
				cmb_employeeStatus.setToolTipText(userStatus);
				txt_userid.setEditable(false);
				txt_lastname.setEditable(false);
				txt_firstname.setEditable(false);
				txt_username.setEditable(false);
				passwordField.setEditable(false);
				txt_userlevel.setEditable(false);
				cmb_employeeStatus.setEnabled(false);
				
					
				}

			}

		});
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSaveUser.setVisible(true);
				btnUpdate.setVisible(true);
				btnUpdate.setEnabled(false);
				btnEditUser.setVisible(true);
				btnEditUser.setEnabled(false);
				btnCancelUser.setVisible(true);
				txt_lastname.setEditable(true);
				txt_firstname.setEditable(true);
				txt_username.setEditable(true);
				passwordField.setEditable(true);

			}
		});
		txt_accessCode.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyCode() == arg0.VK_ENTER) {
					try {
						String strAccessCode = "Select accessCode from user_accnt where accessCode = ?";
						PreparedStatement pst = PointOfSales.getConnect().prepareStatement(strAccessCode);
						pst.setString(1, txt_accessCode.getText());
						result = pst.executeQuery();
						if (result.next()) {

							if (txt_accessCode.getText().equals(result.getString("accessCode"))) {
								// product_code= quantity+prod_stoc

								mod.removeRow(tbl_summary.getSelectedRow());

								txt_productCode.setText(null);
								txt_productName.setText(null);
								txt_productPrice.setText(null);
								txt_quantity.setText(null);
								txt_subtotal.setText(null);

								btnRemove.setVisible(false);
								txt_accessCode.setVisible(false);

							} else {
								JOptionPane.showMessageDialog(null, "Invalid Access Code");
							}
						}

					} catch (Exception ab) {
						JOptionPane.showMessageDialog(null, ab);
						ab.printStackTrace();

					}
				}
			}
		});
		tbl_summary.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String id = tbl_summary.getValueAt(tbl_summary.getSelectedRow(), 0).toString();
				String name = tbl_summary.getValueAt(tbl_summary.getSelectedRow(), 1).toString();
				String price = tbl_summary.getValueAt(tbl_summary.getSelectedRow(), 2).toString();
				String quantity = tbl_summary.getValueAt(tbl_summary.getSelectedRow(), 3).toString();
				String subtotal = tbl_summary.getValueAt(tbl_summary.getSelectedRow(), 4).toString();
				txt_productCode.setText(id);
				cmb_productName.setToolTipText(name);
				txt_productPrice.setText(price);
				txt_quantity.setText(quantity);
				txt_subtotal.setText(subtotal);
				btnRemove.setVisible(true);
			}
		});
		Timestamp dateNow = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String s = sdf.format(dateNow);
		String sdate = String.valueOf(s);
		btnA.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {

					int aiyan = 0;

					String subT = txt_subtotal.getText();
					double subTo = Double.parseDouble(subT);

					String oTotal = txt_totalAmount.getText();
					double total = Double.parseDouble(oTotal);

					double finalTotal = subTo + total;
					String fTotal = Double.toString(finalTotal);
					txt_totalAmount.setText(fTotal);

					String prodID = txt_productCode.getText().trim();
					String prodname = txt_productName.getText().trim();
					String prodPrice = txt_productPrice.getText().trim();
					String prodQuantity = txt_quantity.getText().trim();
					String prodSubtotal = txt_subtotal.getText().trim();
					String strRow[] = { prodID, prodname, prodPrice, prodQuantity, prodSubtotal };
					mod.addRow(strRow);
					

					Timestamp dateNow = new Timestamp(System.currentTimeMillis());
					SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
					String s = sdf.format(dateNow);
					String sdate = String.valueOf(s);

					try {
							String sql = "Insert into sales (Prod_code,Prod_Name,Prod_Price,Product_Sold,Prod_TAmount,date)values(?,?,?,?,?,?)";
							PreparedStatement pst1 = PointOfSales.getConnect().prepareStatement(sql);
							pst1.setString(1, txt_productCode.getText());
							pst1.setString(2, txt_productName.getText());
							pst1.setString(3, txt_productPrice.getText());
							pst1.setString(4, txt_quantity.getText());
							pst1.setString(6, sdate);
							pst1.setString(5, txt_subtotal.getText());
							pst1.execute();
						

					} catch (Exception io) {
						JOptionPane.showMessageDialog(null, io);
						io.printStackTrace();

					}
					try {
						String searchProduct = "select * from productinventory where ProductCode = ?";
						PreparedStatement pst = PointOfSales.getConnect().prepareStatement(searchProduct);
						pst.setString(1, txt_productCode.getText());
						ResultSet rs1 = pst.executeQuery();
						if (rs1.next()) {

							String stock = txt_quantity.getText();
							String strstock = result.getString("Stock");
							String sold = result.getString("ProductSold");

							int stock1 = Integer.parseInt(stock);
							int prodStock = Integer.parseInt(strstock);
							int prodsold = Integer.parseInt(sold);

							int fStock = prodStock - stock1;
							int fSold = prodsold + stock1;

							String strFinalStocks = String.valueOf(fStock);
							String strFinalSold = String.valueOf(fSold);

							String inventory = "Insert into inventory (prodcode,prod_name,prod_price,inventory_sold,date_sale)values(?,?,?,?,?)";
							PreparedStatement pre = PointOfSales.getConnect().prepareStatement(inventory);
							pre.setString(1, txt_productCode.getText());
							pre.setString(2, txt_productName.getText());
							pre.setString(3, txt_productPrice.getText());
							pre.setString(4, strFinalSold);
							pre.setString(5, sdate);
							pre.execute();

							String sql1 = "update productinventory set Stock = ?, ProductSold =? where ProductCode=?";
							PreparedStatement pst2 = PointOfSales.getConnect().prepareStatement(sql1);
							pst2.setString(1, strFinalStocks);
							pst2.setString(2, strFinalSold);
							pst2.setString(3, txt_productCode.getText());
							pst2.executeUpdate();
						}
					} catch (Exception g) {
						JOptionPane.showMessageDialog(null, g);
						g.printStackTrace();
					}
					try {

						String sql2 = "Insert into transactions (productCode,prodName,quantity,Amount,transaction_date)values(?,?,?,?,?)";
						PreparedStatement pst3 = PointOfSales.getConnect().prepareStatement(sql2);
						pst3.setString(1, txt_productCode.getText());
						pst3.setString(2, txt_productName.getText());
						pst3.setString(3, txt_quantity.getText());
						pst3.setString(5, sdate);
						pst3.setString(4, txt_subtotal.getText());
						pst3.execute();

					} catch (Exception ab) {
						JOptionPane.showMessageDialog(null, ab);
						ab.printStackTrace();
					}

					String appendprodname = txt_productName.getText();
					String appendprodPrice = txt_productPrice.getText();
					String appendprodQuantity = txt_quantity.getText();
					String appendprodSubtotal = txt_subtotal.getText();

					officialReceipt.getTextArea().append("\n" + appendprodname + "\n " + " \t   " + "   @   " + "Php " + appendprodPrice
									+" "+" "+ "x"+" "+" " + appendprodQuantity + "\t" + "Php " + appendprodSubtotal + "\n");

				} catch (Exception r) {
					JOptionPane.showMessageDialog(null, r);
					r.printStackTrace();
				}

				txt_productCode.setText(null);
				txt_productName.setText(null);
				txt_productPrice.setText("0");
				txt_subtotal.setText("0");

				txt_quantity.setText("0");
				txt_stocksonhand.setText(null);
				myMethods.adminMethods.table_warning();
				myMethods.adminMethods.productInventory_table();

			}
		});
		btn_newProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_saveProduct.setVisible(true);
				btn_saveProduct.setEnabled(true);
				btn_editProduct.setEnabled(false);
				btn_updateProduct.setEnabled(false);
				btn_editProduct.setVisible(true);
				btn_cancelProduct.setVisible(true);
				btn_updateProduct.setVisible(true);
				txt_productcode.setEditable(true);
				txt_productName.setEditable(true);
				txt_productQuantity.setEditable(true);
				txt_price.setEditable(true);

			}
		});
		btn_saveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if ((txt_productcode.getText().isEmpty()) && (txt_productName.getText().isEmpty())
							&& (txt_productQuantity.getText().isEmpty()) && (txt_price.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Fields must not be empty");

					} else if (!(txt_productcode.getText().isEmpty()) && (txt_productName.getText().isEmpty())
							&& (txt_productQuantity.getText().isEmpty()) && (txt_price.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");

					} else if (!(txt_productcode.getText().isEmpty()) && !(txt_productName.getText().isEmpty())
							&& (txt_productQuantity.getText().isEmpty()) && (txt_price.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other fields are Empty!");

					} else if (!(txt_productcode.getText().isEmpty()) && !(txt_productName.getText().isEmpty())
							&& !(txt_productQuantity.getText().isEmpty()) && (txt_price.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other fields are Empty!");

					} else {
						String sql1 = "Select * from productinventory";
						PreparedStatement pstate2 = PointOfSales.getConnect().prepareStatement(sql1);
						ResultSet rst1 = pstate2.executeQuery();
						if (rst1.next()) {
							if ((txt_productName.getText().equals(rst1.getString("ProductName")))) {
								JOptionPane.showMessageDialog(null, "Product name already exist");
							} else {
								String sql = "insert into productinventory (ProductName,Stock,Price)values(?,?,?)";
								PreparedStatement pstate1 = PointOfSales.getConnect().prepareStatement(sql);
								pstate1.setString(1, txt_productName.getText());
								pstate1.setString(2, txt_productQuantity.getText());
								pstate1.setString(3, txt_price.getText());
								pstate1.execute();
								JOptionPane.showMessageDialog(null, "Product Added!");
								txt_productcode.setEditable(false);
								txt_productName.setEditable(false);
								txt_productQuantity.setEditable(false);
								txt_price.setEditable(false);

								txt_productName.setText(null);
								txt_productQuantity.setText("0");
								txt_price.setText("0");

								// btn_cancel.hide();
								btn_saveProduct.hide();
								btn_editProduct.hide();
								btn_updateProduct.hide();
							}
						}
					}

				} catch (Exception gli) {
					JOptionPane.showMessageDialog(null, gli);
					gli.printStackTrace();
				}

				myMethods.adminMethods.productInventory_table();

				myMethods.adminMethods.table_warning();
			}
		});
		btn_updateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "Select * from productinventory where ProductCode = ?";
					PreparedStatement pstate1 = PointOfSales.getConnect().prepareStatement(sql);
					pstate1.setString(1, txt_productcode.getText());
					ResultSet set = pstate1.executeQuery();
					if (set.next()) {
						String strStock = set.getString("Stock");
						String strQuantity = txt_productQuantity.getText();
						int intstock = Integer.parseInt(strStock);
						int quantity = Integer.parseInt(strQuantity);
						int updatedStock = intstock + quantity;
						String finalStock = String.valueOf(updatedStock);

						String updateProduct = "Update productinventory set ProductName = ?, Price =?, Stock =? where ProductCode =?";
						PreparedStatement pstate = PointOfSales.getConnect().prepareStatement(updateProduct);
						pstate.setString(1, txt_productName.getText());
						pstate.setString(2, txt_price.getText());
						pstate.setString(3, finalStock);
						pstate.setString(4, txt_productcode.getText());
						pstate.executeUpdate();

						JOptionPane.showMessageDialog(null, "Product Updated");
						txt_productcode.setEditable(false);
						txt_productName.setEditable(false);
						txt_productQuantity.setEditable(false);
						txt_price.setEditable(false);
						txt_productcode.setText(null);
						txt_productName.setText(null);
						txt_productQuantity.setText(null);
						txt_price.setText(null);
						// btn_cancel.hide();
						btn_saveProduct.setVisible(false);
						btn_editProduct.setVisible(false);
						btn_updateProduct.setVisible(false);
						myMethods.adminMethods.productInventory_table();
					}
				} catch (Exception ab) {
					JOptionPane.showMessageDialog(null, ab);
					ab.printStackTrace();
				}

			}
		});
		btn_editProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_productName.setEditable(true);
				txt_price.setEditable(true);
				txt_productQuantity.setEditable(true);
				btn_newProduct.setEnabled(false);
			}
		});
		tbl_productInventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				String productID = tbl_productInventory.getValueAt(tbl_productInventory.getSelectedRow(), 0).toString();
				String productName = tbl_productInventory.getValueAt(tbl_productInventory.getSelectedRow(), 1)
						.toString();
				String productQuantity = tbl_productInventory.getValueAt(tbl_productInventory.getSelectedRow(), 2)
						.toString();
				String productPrice = tbl_productInventory.getValueAt(tbl_productInventory.getSelectedRow(), 3)
						.toString();

				btn_saveProduct.setVisible(true);
				btn_updateProduct.setVisible(true);
				btn_saveProduct.setEnabled(false);
				btn_editProduct.setVisible(true);
				btn_cancelProduct.setVisible(true);

				txt_productcode.setText(productID);
				txt_productName.setText(productName);
				txt_productQuantity.setText(productQuantity);
				txt_price.setText(productPrice);

				txt_productcode.setEditable(false);
				txt_productName.setEditable(false);
				txt_productQuantity.setEditable(false);
				txt_price.setEditable(false);

			}
		});
		btn_cancelProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_saveProduct.setVisible(false);
				btn_editProduct.setVisible(false);
				btn_updateProduct.setVisible(false);
				btn_cancelProduct.setVisible(false);
				txt_productcode.setEditable(false);
				txt_productName.setEditable(false);
				txt_price.setEditable(false);
				txt_productQuantity.setText(" ");
				txt_productcode.setText(" ");
				txt_productName.setText(" ");
				txt_price.setText(" ");
				txt_productQuantity.setText(" ");
				btn_newProduct.setEnabled(true);
			}
		});

		cmb_reports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if ((cmb_reports.getSelectedItem().toString()).equals("Sales")) {
					myMethods.adminMethods.sales_table();

				} else if ((cmb_reports.getSelectedItem().toString()).equals("Stocks")) {
					myMethods.adminMethods.stocksreport_table();

				} else if ((cmb_reports.getSelectedItem().toString()).equals("Pulled-out")) {
					myMethods.adminMethods.pullOut_table();
				} else if ((cmb_reports.getSelectedItem().toString()).equals("All")) {
					myMethods.adminMethods.All_table();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String glizzzel = cmb_reports.getSelectedItem().toString();
				if (glizzzel.equals("Sales")) {
					MessageFormat header = new MessageFormat("Vape City Manila Sales Report");
					MessageFormat footer = new MessageFormat(
							"Page{0, number, integer} " + "\n" + "Prepared by: " + "\t" + lbl_admin.getText());
					try {
						table_reports.print(JTable.PrintMode.FIT_WIDTH, header, footer);
					} catch (Exception ac) {
						System.err.format("Cannot print %s%n", ac.getMessage());
						JOptionPane.showMessageDialog(null, ac);
						ac.printStackTrace();
					}
				} else if (glizzzel.equals("Stocks")) {
					MessageFormat header = new MessageFormat(
							"Vape City Manila Stocks Report" + "\n" + "Prepared by: " + "\t" + lbl_admin.getText());
					MessageFormat footer = new MessageFormat("Page{0, number, integer}");
					try {
						table_reports.print(JTable.PrintMode.FIT_WIDTH, header, footer);
					} catch (Exception ac) {
						System.err.format("Cannot print %s%n", ac.getMessage());
						JOptionPane.showMessageDialog(null, ac);
						ac.printStackTrace();
					}
				} else if (glizzzel.equals("Pulled-out")) {
					MessageFormat header = new MessageFormat("Vape City Manila Pulled-out Products Report" + "\n"
							+ "Prepared by: " + "\t" + lbl_admin.getText());
					MessageFormat footer = new MessageFormat("Page{0, number, integer}");
					try {
						table_reports.print(JTable.PrintMode.FIT_WIDTH, header, footer);
					} catch (Exception ac) {
						System.err.format("Cannot print %s%n", ac.getMessage());
						JOptionPane.showMessageDialog(null, ac);
						ac.printStackTrace();
					}
				} else if (glizzzel.equals("All")) {
					MessageFormat header = new MessageFormat(
							"Vape City Manila  Report" + "\n" + "Prepared by: " + "\t" + lbl_admin.getText());
					MessageFormat footer = new MessageFormat("Page{0, number, integer}");
					try {
						table_reports.print(JTable.PrintMode.FIT_WIDTH, header, footer);
					} catch (Exception ac) {
						System.err.format("Cannot print %s%n", ac.getMessage());
						JOptionPane.showMessageDialog(null, ac);
						ac.printStackTrace();
					}
				}
			}
		});
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (cmb_reports.getSelectedItem().toString().equals("Sales")) {

						String startDate = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
						String endDate = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
						String sql = "Select Prod_code as 'Product Code',Prod_name as 'Product Name',Product_Sold as 'Product Sold', date as 'Sales Date' from sales WHERE (date BETWEEN '"
								+ startDate + "' AND '" + endDate + "') order by Prod_code";
						PreparedStatement pst = PointOfSales.getConnect().prepareStatement(sql);
						ResultSet set1 = pst.executeQuery();
						table_reports.setModel(DbUtils.resultSetToTableModel(set1));

					} else if (cmb_reports.getSelectedItem().toString().equals("Stocks")) {
						String startDate = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
						String endDate = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
						String sql = "Select ProductCode as 'Product Code',ProductName as 'Product Name' , Stock as ' Quantity' from productinventory WHERE (date BETWEEN '"
								+ startDate + "' AND '" + endDate + "') order by Prod_code";
						PreparedStatement pst = PointOfSales.getConnect().prepareStatement(sql);
						ResultSet set1 = pst.executeQuery();
						table_reports.setModel(DbUtils.resultSetToTableModel(set1));

					}

				} catch (Exception io) {
					JOptionPane.showMessageDialog(null, io);
					io.printStackTrace();
				}

			}
		});
		txt_amountTendered.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					
					
					String strtotal = txt_totalAmount.getText();
					double total = Double.parseDouble(strtotal);
					String stramount = txt_amountTendered.getText();
					double amount = Double.parseDouble(stramount);
					double change = amount - total;
					String strchange = String.valueOf(change);
					txt_change.setText(strchange);
					String vat = txt_totalAmount.getText();
					double vat1 = Double.parseDouble(vat);
					double vata = (vat1 * .12);
					System.out.print(vata);

					double vatable = (vat1 - vata);

					officialReceipt.getTextArea().append("\n" + "___________________________________________________"
							+ "\n" + "Total Amount: " + "\t" + "\t" + "\t" + "Php " + strtotal + "\n");
					officialReceipt.getTextArea().append("Amount Give :" + "\t" + "\t" + "\t" + "Php  " + stramount
							+ "\n" + "Change" + "\t" + "\t" + "\t" + "Php  " + strchange);
					officialReceipt.getTextArea().append("\n"+"Vat:" + "\t" + "\t" + "\t" + "Php  " + vata);
					officialReceipt.getTextArea().append("\n"+"Vatable:" + "\t" + "\t" + "\t" + "Php  " + vatable);
					officialReceipt.getTextArea().append("\n"+"\n"+"\n" + "\t" + " "+" "+ sdate + "\n");
					officialReceipt.getTextArea().append("\t" + "Thank you for shopping!" + "\n");
					

					
				}
			}
		});
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
				
					officialReceipt.setWindowVisible();
					PrinterJob pjob = PrinterJob.getPrinterJob();
					PageFormat preformat = pjob.defaultPage();
					preformat.setOrientation(PageFormat.PORTRAIT);
					PageFormat postformat = pjob.pageDialog(preformat);

					// If user does not hit cancel then print.
					if (preformat != postformat) {
						// Set print component
						pjob.setPrintable((Printable) officialReceipt, postformat);
						if (pjob.printDialog()) {
							try {
								pjob.print();
							} catch (PrinterException ex) {
								Logger.getLogger(officialReceipt.getClass().getName()).log(Level.SEVERE, null, ex);
							}
						}
					}

				} catch (Exception q) {
					JOptionPane.showMessageDialog(null, q);
					q.printStackTrace();
				}

				/*
				 * txt_total.setText("0"); txt_subtotal.setText("0");
				 * txt_amountgive.setText(null); txt_change.setText(null);
				 * mod.setRowCount(0);
				 */

			}
		});

	}

	public JFrame getFrame() {
		return frmVcmSalesAnd;
	}

	public static JLabel getLbl_admin() {
		return lbl_admin;
	}
}
