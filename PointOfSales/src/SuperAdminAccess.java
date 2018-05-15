import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;

public class SuperAdminAccess {

	private static JFrame frmVcmSalesAnd;
	private JTable tbl_user;
	private JTextField txt_userID;
	private JTextField txt_lastname;
	private JTextField txt_firstname;
	private JTextField txt_username;
	private JPasswordField pswd_userPassword;
	private JTable tbl_productInventory;
	private JTextField txt_productcode;
	private JTextField txt_productName;
	private JTextField txt_price;
	private JTextField txt_productQuantity;
	static JTextField txt_superAdmin;
	public static JTextField txt_time;
	public static JTextField txt_date;
	public static JTextField txt_aps;
	private JTable tbl_warning;
	private JTextField txt_productCode;
	private JTextField txt_productPrice;
	private JTextField txt_quantity;
	private JTextField txt_subtotal;
	private JTextField txt_totalAmount;
	private JTextField txt_amountgive;
	private JTextField txt_change;
	private JTable tbl_summary;
	private JTextField txt_accesscode;
	static JTable table_reports;
	static JTable tbl_loginHistory;
	private JTextField txt_accessCode;
	private JTextField txt_stocksonhand;
	static JLabel lbl_time;
	static JLabel lbl_date;
	DefaultTableModel mod;
	ResultSet result;
	OfficialReceipt officialReceipt = new OfficialReceipt();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuperAdminAccess window = new SuperAdminAccess();
					window.frmVcmSalesAnd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SuperAdminAccess() {
		initialize();

		myMethods.superAdmin.superAdminClock();
		superAdmintable_warning();
		superAdminproductInventory_table();
		user_table();
		myMethods.superAdmin.loginHistory_table();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void superAdmintable_warning() {

		try {

			String asd = "Select ProductCode as 'Product Code',ProductName as 'Product Name',Stock as 'Remanining Stocks' from productinventory where Stock <=10 and Stock >0";
			PreparedStatement pst3 = PointOfSales.getConnect().prepareStatement(asd);
			ResultSet rs = pst3.executeQuery();
			tbl_warning.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception io) {
			JOptionPane.showMessageDialog(null, io);
			io.printStackTrace();

		}
	}

	private void superAdminproductInventory_table() {

		try {
			String sql = " Select ProductCode as 'Product Code',ProductName as 'Product Name' , Stock as ' Quantity',Price as 'Price' from productinventory order by ProductCode";
			PreparedStatement pst = PointOfSales.getConnect().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			tbl_productInventory.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception io) {
			JOptionPane.showMessageDialog(null, io);
			io.printStackTrace();

		}
	}

	private void user_table() {

		try {
			String sql = " Select user_id as 'Employee ID',user_Fname as 'First Name' , user_lnamel as ' Last Name',user_username as 'Username', user_password as 'Pssword', user_level as 'User Level', user_status as 'Status', accessCode as 'Access Code' from user_accnt order by user_id";
			PreparedStatement pst = PointOfSales.getConnect().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			tbl_user.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception io) {
			JOptionPane.showMessageDialog(null, io);
			io.printStackTrace();

		}
	}

	private void initialize() {
		frmVcmSalesAnd = new JFrame();
		frmVcmSalesAnd.setTitle("VCM Sales and Inventory");
		frmVcmSalesAnd.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				mod = new DefaultTableModel();
				tbl_summary.setModel(mod);
				mod.addColumn("Product Code");
				mod.addColumn("Product Name");
				mod.addColumn("Price");
				mod.addColumn("Quantity");
				mod.addColumn("Subtotal");

			}
		});
		frmVcmSalesAnd.getContentPane().setBackground(new Color(255, 255, 204));
		frmVcmSalesAnd.setBounds(100, 100, 1960, 1880);
		frmVcmSalesAnd.setLocationRelativeTo(null);
		frmVcmSalesAnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVcmSalesAnd.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tabbedPane.getSelectedIndex() == 5){
					frmVcmSalesAnd.dispose();
					new Login().getFrame().setVisible(true);
				}
			}
		});
		
			
		tabbedPane.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		tabbedPane.setBounds(0, 175, 1887, 800);
		frmVcmSalesAnd.getContentPane().add(tabbedPane);

		JPanel UserAccnt = new JPanel();
		UserAccnt.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("User Maintenance", null, UserAccnt, null);
		UserAccnt.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(255, 255, 204));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setAutoscrolls(true);
		scrollPane.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		scrollPane.setBounds(15, 65, 1392, 670);
		UserAccnt.add(scrollPane);

		tbl_user = new JTable();
		tbl_user.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tbl_user.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		tbl_user.setRowHeight(35);

		scrollPane.setViewportView(tbl_user);

		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setForeground(new Color(255, 255, 204));
		lblUserId.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblUserId.setBounds(1438, 165, 163, 20);
		UserAccnt.add(lblUserId);

		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setForeground(new Color(255, 255, 204));
		lblLastName.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblLastName.setBounds(1438, 223, 163, 20);
		UserAccnt.add(lblLastName);

		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setForeground(new Color(255, 255, 204));
		lblFirstName.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblFirstName.setBounds(1438, 275, 163, 20);
		UserAccnt.add(lblFirstName);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(255, 255, 204));
		lblUsername.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblUsername.setBounds(1438, 323, 163, 20);
		UserAccnt.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 255, 204));
		lblPassword.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblPassword.setBounds(1438, 371, 163, 20);
		UserAccnt.add(lblPassword);

		JLabel lblUserLevel = new JLabel("User Level");
		lblUserLevel.setForeground(new Color(255, 255, 204));
		lblUserLevel.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblUserLevel.setBounds(1438, 424, 163, 20);
		UserAccnt.add(lblUserLevel);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(new Color(255, 255, 204));
		lblStatus.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblStatus.setBounds(1438, 474, 163, 20);
		UserAccnt.add(lblStatus);

		txt_userID = new JTextField();
		txt_userID.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		txt_userID.setEditable(false);
		txt_userID.setBounds(1629, 165, 217, 30);
		UserAccnt.add(txt_userID);
		txt_userID.setColumns(10);

		txt_lastname = new JTextField();
		txt_lastname.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		txt_lastname.setEditable(false);
		txt_lastname.setBounds(1629, 223, 217, 30);
		UserAccnt.add(txt_lastname);
		txt_lastname.setColumns(10);

		txt_firstname = new JTextField();
		txt_firstname.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		txt_firstname.setEditable(false);
		txt_firstname.setBounds(1629, 275, 217, 30);
		UserAccnt.add(txt_firstname);
		txt_firstname.setColumns(10);

		txt_username = new JTextField();
		txt_username.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		txt_username.setEditable(false);
		txt_username.setBounds(1629, 323, 217, 30);
		UserAccnt.add(txt_username);
		txt_username.setColumns(10);

		pswd_userPassword = new JPasswordField();
		pswd_userPassword.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		pswd_userPassword.setEditable(false);
		pswd_userPassword.setBounds(1629, 371, 217, 30);
		UserAccnt.add(pswd_userPassword);

		JComboBox cmb_userLevel = new JComboBox();
		
		cmb_userLevel.setModel(new DefaultComboBoxModel(new String[] { "Admin", "Employee" }));
		cmb_userLevel.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		cmb_userLevel.setEnabled(false);
		cmb_userLevel.setBounds(1629, 423, 217, 30);
		UserAccnt.add(cmb_userLevel);

		JComboBox cmb_userStatus = new JComboBox();
		cmb_userStatus.setModel(new DefaultComboBoxModel(new String[] { "Active", "Inactive" }));
		cmb_userStatus.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		cmb_userStatus.enable(false);
		cmb_userStatus.setBounds(1629, 474, 217, 30);
		UserAccnt.add(cmb_userStatus);

		JButton btnEditUser = new JButton("Edit");

		btnEditUser.hide();
		btnEditUser.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		btnEditUser.setBounds(1697, 606, 149, 35);
		UserAccnt.add(btnEditUser);

		JButton btnUpdateUser = new JButton("Update");
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnUpdateUser.hide();
		btnUpdateUser.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		btnUpdateUser.setBounds(1438, 651, 163, 35);
		UserAccnt.add(btnUpdateUser);

		JButton btnCancelUser = new JButton("Cancel");

		btnCancelUser.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		btnCancelUser.setBounds(1697, 651, 149, 35);
		UserAccnt.add(btnCancelUser);
		JButton btnNew = new JButton("New");

		JButton btnSaveUser = new JButton("Save");

		btnSaveUser.hide();
		btnSaveUser.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		btnSaveUser.setBounds(1438, 606, 163, 35);
		UserAccnt.add(btnSaveUser);
		btnNew.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		btnNew.setBounds(1438, 65, 115, 35);
		UserAccnt.add(btnNew);

		txt_accessCode = new JTextField();
		txt_accessCode.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		txt_accessCode.setEditable(false);
		txt_accessCode.setBounds(1629, 531, 163, 30);
		txt_accessCode.setVisible(false);
		UserAccnt.add(txt_accessCode);
		txt_accessCode.setColumns(10);

		JButton btnAccessCode = new JButton("Access Code");
		btnAccessCode.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		btnAccessCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Random orgCodeGen = new Random();

				int orgzAccCode = orgCodeGen.nextInt();

				while (true) {
					orgzAccCode = orgCodeGen.nextInt();
					if (orgzAccCode > 10000 && orgzAccCode < 99999) {
						break;
					}
				}

				txt_accessCode.setText(String.valueOf(orgzAccCode));
			}
		});
		btnAccessCode.setVisible(false);
		btnAccessCode.setBounds(1438, 532, 176, 29);
		UserAccnt.add(btnAccessCode);

		btnCancelUser.setVisible(false);

		btnSaveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = PointOfSales.getConnect();

				String userlevel = cmb_userLevel.getSelectedItem().toString();
				String userStatus = cmb_userStatus.getSelectedItem().toString();

				try {
					if (userlevel == "Admin"){
						btnAccessCode.setEnabled(true);
					
					}else{
						btnAccessCode.setEnabled(false);
					}

					if (txt_userID.getText().isEmpty() && (pswd_userPassword.getText().isEmpty())
							&& (txt_lastname.getText().isEmpty()) && (txt_firstname.getText().isEmpty())
							&& (txt_username.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Fields must not be empty");
						// unused
					} else if (!(txt_userID.getText().isEmpty()) && (txt_lastname.getText().isEmpty())
							&& (txt_firstname.getText().isEmpty()) && (txt_username.getText().isEmpty())
							&& (pswd_userPassword.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userID.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& (txt_firstname.getText().isEmpty()) && (txt_username.getText().isEmpty())
							&& (pswd_userPassword.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userID.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& !(txt_firstname.getText().isEmpty()) && (txt_username.getText().isEmpty())
							&& (pswd_userPassword.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userID.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& !(txt_firstname.getText().isEmpty()) && !(txt_username.getText().isEmpty())
							&& (pswd_userPassword.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userID.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& !(txt_firstname.getText().isEmpty()) && !(txt_username.getText().isEmpty())
							&& !(pswd_userPassword.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userID.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& !(txt_firstname.getText().isEmpty()) && !(txt_username.getText().isEmpty())
							&& !(pswd_userPassword.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else {
						String sql = ("Insert into user_accnt (user_lnamel,user_Fname,user_username,user_password,user_level,user_status,accessCode)values(?,?,?,?,?,?,?)");

						PreparedStatement pst = PointOfSales.getConnect().prepareStatement(sql);

						pst.setString(1, txt_lastname.getText());
						pst.setString(2, txt_firstname.getText());
						pst.setString(3, txt_username.getText());
						pst.setString(4, pswd_userPassword.getText());
						pst.setString(5, userlevel);
						pst.setString(6, userStatus);
						pst.setString(7, txt_accessCode.getText());
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
				pswd_userPassword.setEditable(false);

				txt_userID.setText("NEW");
				txt_lastname.setText(null);
				txt_firstname.setText(null);
				txt_username.setText(null);
				pswd_userPassword.setText(null);

				btnSaveUser.hide();
				btnEditUser.hide();
				btnCancelUser.hide();
				btnUpdateUser.hide();
				user_table();

			}
		});
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = PointOfSales.getConnect();

				String userstatus = cmb_userStatus.getSelectedItem().toString();
				String userlevel = cmb_userLevel.getSelectedItem().toString();

				try {

					if (txt_userID.getText().isEmpty() && (pswd_userPassword.getText().isEmpty())
							&& (txt_lastname.getText().isEmpty()) && (txt_firstname.getText().isEmpty())
							&& (txt_username.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Fields must not be empty");
						// unused
					} else if (!(txt_userID.getText().isEmpty()) && (txt_lastname.getText().isEmpty())
							&& (txt_firstname.getText().isEmpty()) && (txt_username.getText().isEmpty())
							&& (pswd_userPassword.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userID.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& (txt_firstname.getText().isEmpty()) && (txt_username.getText().isEmpty())
							&& (pswd_userPassword.getPassword().toString().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userID.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& !(txt_firstname.getText().isEmpty()) && (txt_username.getText().isEmpty())
							&& (pswd_userPassword.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");
					} else if (!(txt_userID.getText().isEmpty()) && !(txt_lastname.getText().isEmpty())
							&& !(txt_firstname.getText().isEmpty()) && !(txt_username.getText().isEmpty())
							&& (pswd_userPassword.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Other Fields are Empty!");

					} else {

						String updateProduct1 = "update user_accnt set user_username = ?,user_password = ?, user_status =?, user_level=? where user_id = ?";
						PreparedStatement ps1 = PointOfSales.getConnect().prepareStatement(updateProduct1);

						ps1.setString(1, txt_username.getText());
						ps1.setString(2, pswd_userPassword.getText());
						ps1.setString(3, userstatus);
						ps1.setString(4, userlevel);
						ps1.setString(5, txt_userID.getText());
						ps1.executeUpdate();

						JOptionPane.showMessageDialog(null, "User information Updated!");

					}
					txt_userID.setText(null);
					txt_lastname.setEditable(false);
					txt_firstname.setEditable(false);
					txt_username.setEditable(false);
					pswd_userPassword.setEditable(false);

					txt_lastname.setText(null);
					txt_firstname.setText(null);
					txt_username.setText(null);
					pswd_userPassword.setText(null);

					btnCancelUser.setVisible(false);
					btnSaveUser.setVisible(false);
					btnUpdateUser.setVisible(false);
					btnEditUser.setVisible(false);
					user_table();

				} catch (Exception io) {
					JOptionPane.showMessageDialog(null, io);
					io.printStackTrace();
				}

			}
		});
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				btnSaveUser.setVisible(true);
				btnSaveUser.setEnabled(true);
				btnEditUser.setVisible(true);
				btnEditUser.setEnabled(false);
				btnUpdateUser.setVisible(true);
				btnUpdateUser.setEnabled(false);
				btnCancelUser.setVisible(true);
				btnCancelUser.setEnabled(true);

				txt_userID.setEditable(true);
				txt_lastname.setEditable(true);
				txt_firstname.setEditable(true);
				txt_username.setEditable(true);
				cmb_userLevel.setEnabled(true);
				cmb_userStatus.setEnabled(true);
				pswd_userPassword.setEditable(true);
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
				if(userLevel.equals("Super Admin")){
					btnEditUser.setEnabled(false);
				}else{
					
				btnEditUser.setEnabled(true);
				btnEditUser.setVisible(true);
				btnUpdateUser.setVisible(true);
				btnSaveUser.setVisible(true);
				btnSaveUser.setEnabled(false);
				btnCancelUser.setVisible(true);
				btnNew.setEnabled(false);

				txt_userID.setText(userId);
				txt_lastname.setText(userLastName);
				txt_firstname.setText(userFirstName);
				txt_username.setText(userUsername);
				pswd_userPassword.setText(userPassword);
				cmb_userLevel.setToolTipText(userLevel);
				cmb_userStatus.setToolTipText(userStatus);
				txt_userID.setEditable(false);
				txt_lastname.setEditable(false);
				txt_firstname.setEditable(false);
				txt_username.setEditable(false);
				pswd_userPassword.setEditable(false);
				cmb_userLevel.setEnabled(false);
				cmb_userStatus.setEnabled(false);
				}

			}
		});
		btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt_lastname.setEditable(true);
				txt_firstname.setEditable(true);
				txt_username.setEditable(true);
				pswd_userPassword.setEditable(true);
				cmb_userStatus.setEnabled(true);
				cmb_userLevel.setEnabled(true);
			}
		});

		JPanel productInventory = new JPanel();
		productInventory.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Product Inventory", null, productInventory, null);
		productInventory.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		scrollPane_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		scrollPane_1.setAutoscrolls(true);
		scrollPane_1.setBounds(34, 39, 1241, 654);
		productInventory.add(scrollPane_1);

		tbl_productInventory = new JTable() {
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
				else if (col == 3)
					return renderRight;
				else
					return renderLeft;
			}
		};
		
		tbl_productInventory.setRowHeight(35);
		tbl_productInventory.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		tbl_productInventory.setAlignmentX(Component.RIGHT_ALIGNMENT);
		scrollPane_1.setViewportView(tbl_productInventory);

		JButton btnNewproduct = new JButton("New");
		

		btnNewproduct.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		btnNewproduct.setBounds(1290, 96, 115, 35);
		productInventory.add(btnNewproduct);

		JLabel lblProductCode = new JLabel("Product Code");
		lblProductCode.setForeground(new Color(255, 255, 204));
		lblProductCode.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblProductCode.setBounds(1297, 174, 186, 20);
		productInventory.add(lblProductCode);

		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setForeground(new Color(255, 255, 204));
		lblProductName.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblProductName.setBounds(1297, 235, 186, 20);
		productInventory.add(lblProductName);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(255, 255, 204));
		lblPrice.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblPrice.setBounds(1297, 297, 186, 20);
		productInventory.add(lblPrice);

		JLabel lblStocks = new JLabel("Stocks");
		lblStocks.setForeground(new Color(255, 255, 204));
		lblStocks.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblStocks.setBounds(1297, 362, 186, 20);
		productInventory.add(lblStocks);

		txt_productcode = new JTextField();
		txt_productcode.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txt_productcode.setEditable(false);
		txt_productcode.setBounds(1511, 174, 318, 30);
		productInventory.add(txt_productcode);
		txt_productcode.setColumns(10);

		txt_productName = new JTextField();
		txt_productName.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txt_productName.setEditable(false);
		txt_productName.setBounds(1511, 235, 318, 30);
		productInventory.add(txt_productName);
		txt_productName.setColumns(10);

		txt_price = new JTextField();
		txt_price.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txt_price.setEditable(false);
		txt_price.setBounds(1511, 297, 318, 30);
		productInventory.add(txt_price);
		txt_price.setColumns(10);

		txt_productQuantity = new JTextField();
		txt_productQuantity.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txt_productQuantity.setEditable(false);
		txt_productQuantity.setBounds(1511, 362, 318, 30);
		productInventory.add(txt_productQuantity);
		txt_productQuantity.setColumns(10);

		JButton btn_saveProduct = new JButton("Save");
		
		btn_saveProduct.setVisible(false);
		btn_saveProduct.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		btn_saveProduct.setBounds(1370, 462, 152, 35);
		productInventory.add(btn_saveProduct);

		JButton btn_editProduct = new JButton("Edit");

		btn_editProduct.hide();
		btn_editProduct.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		btn_editProduct.setBounds(1634, 462, 137, 35);
		productInventory.add(btn_editProduct);

		JButton btn_updateProduct = new JButton("Update");

		btn_updateProduct.hide();
		btn_updateProduct.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		btn_updateProduct.setBounds(1370, 519, 152, 35);
		productInventory.add(btn_updateProduct);

		JButton btn_cancelProduct = new JButton("Cancel");
		btn_cancelProduct.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		btn_cancelProduct.setBounds(1634, 522, 137, 35);
		btn_cancelProduct.setVisible(false);
		productInventory.add(btn_cancelProduct);

		JPanel report = new JPanel();
		report.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Reports", null, report, null);
		report.setLayout(null);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(430, 70, 1409, 571);
		report.add(scrollPane_4);

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
				else
					return renderLeft;
			}
		};
		
		table_reports.setRowHeight(35);
		table_reports.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		
		
		scrollPane_4.setViewportView(table_reports);

		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setForeground(new Color(255, 255, 204));
		lblStartDate.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblStartDate.setBounds(39, 101, 169, 30);
		report.add(lblStartDate);

		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setForeground(new Color(255, 255, 204));
		lblEndDate.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblEndDate.setBounds(39, 176, 169, 30);
		report.add(lblEndDate);

		JButton btnPrint = new JButton("Print");

		btnPrint.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		btnPrint.setBounds(118, 346, 184, 46);
		report.add(btnPrint);

		JComboBox cmb_reports = new JComboBox();

		cmb_reports.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		cmb_reports.setModel(new DefaultComboBoxModel(new String[] { "All", "Stocks", "Sales", "Pulled-out" }));
		cmb_reports.setBounds(39, 45, 275, 30);
		report.add(cmb_reports);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("MM-dd-yyyy");
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 22));
		dateChooser.setBounds(223, 101, 184, 30);
		report.add(dateChooser);

		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("MM-dd-yyyy");
		dateChooser_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		dateChooser_1.setBounds(223, 176, 184, 30);
		report.add(dateChooser_1);

		JButton btnShow = new JButton("Show");
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		btnShow.setBounds(118, 259, 184, 41);
		report.add(btnShow);
		cmb_reports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((cmb_reports.getSelectedItem().toString()).equals("Sales")) {
					myMethods.superAdmin.sales_table();

				} else if ((cmb_reports.getSelectedItem().toString()).equals("Stocks")) {
					myMethods.superAdmin.stocksreport_table();

				} else if ((cmb_reports.getSelectedItem().toString()).equals("Pulled-out")) {
					myMethods.superAdmin.pullOut_table();
				} else if ((cmb_reports.getSelectedItem().toString()).equals("All")) {
					myMethods.superAdmin.All_table();
				}
			}
		});
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String glizzzel = cmb_reports.getSelectedItem().toString();
				if (glizzzel.equals("Sales")) {
					MessageFormat header = new MessageFormat("Vape City Manila Sales Report");
					MessageFormat footer = new MessageFormat(
							"Page{0, number, integer} " + "\n" + "Prepared by: " + "\t" + txt_superAdmin.getText());
					try {
						table_reports.print(JTable.PrintMode.FIT_WIDTH, header, footer);
					} catch (Exception ac) {
						System.err.format("Cannot print %s%n", ac.getMessage());
						JOptionPane.showMessageDialog(null, ac);
						ac.printStackTrace();
					}
				} else if (glizzzel.equals("Stocks")) {
					MessageFormat header = new MessageFormat("Vape City Manila Stocks Report" + "\n" + "Prepared by: "
							+ "\t" + txt_superAdmin.getText());
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
							+ "Prepared by: " + "\t" + txt_superAdmin.getText());
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
							"Vape City Manila Report" + "\n"+"\n" + "Prepared by: " + "\t" + txt_superAdmin.getText());
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

		JPanel transaction = new JPanel();
		transaction.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Transactions", null, transaction, null);
		transaction.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		scrollPane_2.setAutoscrolls(true);
		scrollPane_2.setBounds(39, 37, 696, 169);
		transaction.add(scrollPane_2);

		tbl_warning = new JTable();
		tbl_warning.setRowHeight(35);
		tbl_warning.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		scrollPane_2.setViewportView(tbl_warning);

		JLabel lblProductCode_1 = new JLabel("Product Code");
		lblProductCode_1.setForeground(new Color(255, 255, 204));
		lblProductCode_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblProductCode_1.setBounds(46, 280, 169, 30);
		transaction.add(lblProductCode_1);

		JLabel lblProductName_1 = new JLabel("Product Name");
		lblProductName_1.setForeground(new Color(255, 255, 204));
		lblProductName_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblProductName_1.setBounds(46, 325, 169, 30);
		transaction.add(lblProductName_1);

		JLabel lblPrice_1 = new JLabel("Price");
		lblPrice_1.setForeground(new Color(255, 255, 204));
		lblPrice_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblPrice_1.setBounds(46, 375, 169, 30);
		transaction.add(lblPrice_1);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(new Color(255, 255, 204));
		lblQuantity.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblQuantity.setBounds(46, 424, 169, 30);
		transaction.add(lblQuantity);

		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setForeground(new Color(255, 255, 204));
		lblSubtotal.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblSubtotal.setBounds(46, 475, 169, 30);
		transaction.add(lblSubtotal);

		txt_productCode = new JTextField();
		// textField_4.setEditable(false);
		txt_productCode.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_productCode.setBounds(230, 285, 339, 30);
		transaction.add(txt_productCode);
		txt_productCode.setColumns(10);

		txt_productPrice = new JTextField();
		// textField_6.setEditable(false);
		txt_productPrice.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_productPrice.setText("0");
		txt_productPrice.setBounds(230, 380, 339, 30);
		transaction.add(txt_productPrice);
		txt_productPrice.setColumns(10);

		txt_quantity = new JTextField();

		// textField_7.setEditable(false);
		txt_quantity.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_quantity.setText("0");
		txt_quantity.setBounds(230, 429, 339, 30);
		transaction.add(txt_quantity);
		txt_quantity.setColumns(10);

		txt_subtotal = new JTextField();
		// textField_8.setEditable(false);
		txt_subtotal.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_subtotal.setText("0");
		txt_subtotal.setBounds(230, 480, 339, 30);
		transaction.add(txt_subtotal);
		txt_subtotal.setColumns(10);

		JButton btnAddToCart = new JButton("ADD TO CART");

		btnAddToCart.setFont(new Font("Bookman Old Style", Font.PLAIN, 27));
		btnAddToCart.setBounds(170, 549, 300, 45);
		transaction.add(btnAddToCart);

		JComboBox cmb_productName = new JComboBox();

		cmb_productName.setModel(new DefaultComboBoxModel(new String[] { "Select Item" }));
		cmb_productName.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		cmb_productName.setBounds(230, 330, 339, 30);
		transaction.add(cmb_productName);

		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setForeground(new Color(255, 255, 204));
		lblTotalAmount.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblTotalAmount.setBounds(622, 280, 199, 35);
		transaction.add(lblTotalAmount);

		txt_totalAmount = new JTextField();
		txt_totalAmount.setText("0");
		txt_totalAmount.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_totalAmount.setBounds(622, 324, 199, 35);
		transaction.add(txt_totalAmount);
		txt_totalAmount.setColumns(10);

		JLabel lblAmountGive = new JLabel("Amount Give");
		lblAmountGive.setForeground(new Color(255, 255, 204));
		lblAmountGive.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblAmountGive.setBounds(622, 367, 199, 35);
		transaction.add(lblAmountGive);

		txt_amountgive = new JTextField();

		txt_amountgive.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_amountgive.setBounds(622, 420, 199, 35);
		transaction.add(txt_amountgive);
		txt_amountgive.setColumns(10);

		JLabel lblChange = new JLabel("Change");
		lblChange.setForeground(new Color(255, 255, 204));
		lblChange.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblChange.setBounds(622, 469, 199, 30);
		transaction.add(lblChange);

		txt_change = new JTextField();
		txt_change.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_change.setBounds(622, 508, 199, 35);
		transaction.add(txt_change);
		txt_change.setColumns(10);

		JButton btnSubmit = new JButton("Submit");

		btnSubmit.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		btnSubmit.setBounds(622, 577, 199, 35);
		transaction.add(btnSubmit);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setAutoscrolls(true);
		scrollPane_3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		scrollPane_3.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		scrollPane_3.setBounds(888, 206, 938, 333);
		transaction.add(scrollPane_3);

		tbl_summary = new JTable();
		tbl_summary.setRowHeight(35);
		tbl_summary.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));

		scrollPane_3.setViewportView(tbl_summary);

		JButton btnRemove = new JButton("Remove");
		btnRemove.hide();
		btnRemove.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		btnRemove.setBounds(888, 577, 151, 35);
		transaction.add(btnRemove);

		txt_accesscode = new JTextField();
		txt_accesscode.setVisible(false);
		txt_accesscode.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_accesscode.setBounds(1070, 578, 236, 35);
		transaction.add(txt_accesscode);
		txt_accesscode.setColumns(10);

		txt_stocksonhand = new JTextField();
		txt_stocksonhand.setEditable(false);
		txt_stocksonhand.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		txt_stocksonhand.setForeground(new Color(255, 255, 204));
		txt_stocksonhand.setBackground(new Color(0, 0, 0));
		txt_stocksonhand.setBounds(230, 243, 339, 35);
		transaction.add(txt_stocksonhand);
		txt_stocksonhand.setColumns(10);

		JLabel lblStocks_1 = new JLabel("Stocks");
		lblStocks_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblStocks_1.setForeground(new Color(255, 255, 204));
		lblStocks_1.setBounds(46, 246, 169, 30);
		transaction.add(lblStocks_1);

		JPanel loginHistory = new JPanel();
		loginHistory.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Login History", null, loginHistory, null);
		loginHistory.setLayout(null);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		scrollPane_5.setBounds(101, 107, 1433, 643);
		loginHistory.add(scrollPane_5);

		tbl_loginHistory = new JTable();
		tbl_loginHistory.setRowHeight(30);
		tbl_loginHistory.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		scrollPane_5.setViewportView(tbl_loginHistory);

		JTabbedPane logOut = new JTabbedPane(JTabbedPane.TOP);
		logOut.addMouseListener(new MouseAdapter() {
			
		});
		tabbedPane.addTab("Logout", null, logOut, null);

		JLabel lblVapeCityManila = new JLabel("VAPE CITY MANILA");
		lblVapeCityManila.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
		lblVapeCityManila.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblVapeCityManila.setBounds(442, 16, 446, 71);
		frmVcmSalesAnd.getContentPane().add(lblVapeCityManila);

		JLabel lblAdmin = new JLabel("Super Admin");
		lblAdmin.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblAdmin.setBounds(15, 85, 169, 32);
		frmVcmSalesAnd.getContentPane().add(lblAdmin);

		txt_superAdmin = new JTextField();
		txt_superAdmin.setBackground(new Color(255, 255, 204));
		txt_superAdmin.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_superAdmin.setBounds(15, 130, 274, 29);
		frmVcmSalesAnd.getContentPane().add(txt_superAdmin);
		txt_superAdmin.setColumns(10);

		txt_time = new JTextField();
		txt_time.setEditable(false);
		txt_time.setBackground(new Color(255, 255, 204));
		txt_time.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_time.setBounds(1454, 88, 203, 29);
		frmVcmSalesAnd.getContentPane().add(txt_time);
		txt_time.setColumns(10);

		txt_date = new JTextField();
		txt_date.setEditable(false);
		txt_date.setBackground(new Color(255, 255, 204));
		txt_date.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_date.setBounds(1454, 142, 203, 29);
		frmVcmSalesAnd.getContentPane().add(txt_date);
		txt_date.setColumns(10);

		txt_aps = new JTextField();
		txt_aps.setBounds(1696, 103, 98, 26);
		frmVcmSalesAnd.getContentPane().add(txt_aps);
		txt_aps.setColumns(10);

		JLabel lbl_time = new JLabel("");
		lbl_time.setBounds(1196, 56, 203, 32);
		frmVcmSalesAnd.getContentPane().add(lbl_time);

		JLabel lbl_date = new JLabel("");
		lbl_date.setBounds(1196, 110, 203, 39);
		frmVcmSalesAnd.getContentPane().add(lbl_date);
		
		btn_editProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt_productName.setEditable(true);
				txt_price.setEditable(true);
				txt_productQuantity.setEditable(true);
				btnNewproduct.setEnabled(false);
			}
		});
		btn_cancelProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn_saveProduct.setVisible(false);
				btn_editProduct.setVisible(false);
				btn_updateProduct.setVisible(false);
				btn_cancelProduct.setVisible(false);
				txt_productcode.setEditable(false);
				txt_productName.setEditable(false);
				txt_price.setEditable(false);
				txt_productQuantity.setEditable(false);
				txt_productcode.setText(" ");
				txt_productName.setText(" ");
				txt_price.setText(" ");
				txt_productQuantity.setText(" ");
				btnNewproduct.setEnabled(true);
			}
		});
		cmb_productName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSet result;
				try {

					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/admin?AutoReconnect=true&useSSL=false", "root", "");
					Statement statement1 = con.createStatement();
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

					PreparedStatement statement = PointOfSales.getConnect().prepareStatement(query1);

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
		btn_updateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
						btn_cancelProduct.setVisible(false);
						btn_saveProduct.setVisible(false);
						btn_editProduct.setVisible(false);
						btn_updateProduct.setVisible(false);
						superAdminproductInventory_table();
					}
				} catch (Exception ab) {
					JOptionPane.showMessageDialog(null, ab);
					ab.printStackTrace();
				}

			}
		});
		
		Timestamp dateNow = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String s = sdf.format(dateNow);
		String sdate = String.valueOf(s);
		
		btnAddToCart.addActionListener(new ActionListener() {
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
						System.out.print("hello");
						pst1.setString(1, txt_productCode.getText());
						pst1.setString(2, cmb_productName.getSelectedItem().toString());
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
							String strstock = rs1.getString("Stock");
							String sold = rs1.getString("ProductSold");

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
							pre.setString(2, cmb_productName.getSelectedItem().toString());
							pre.setString(3, txt_productPrice.getText());
							pre.setString(4, strFinalSold);
							pre.setString(5, sdate);
							pre.execute();

							String sql1 = "update productinventory set Stock = ? where ProductCode=?";
							PreparedStatement pst2 = PointOfSales.getConnect().prepareStatement(sql1);
							pst2.setString(1, strFinalStocks);
							pst2.setString(2, txt_productCode.getText());
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
						pst3.setString(2, cmb_productName.getSelectedItem().toString());
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

					officialReceipt.getTextArea().append("\n" + appendprodname + "\n " + " \t   " + "   @   " + "Php" + appendprodPrice
									+ "  x  " + appendprodQuantity + "\t" + "Php" + appendprodSubtotal + "\n");

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
				superAdmintable_warning();
				superAdminproductInventory_table();
			}
		});
		tbl_summary.addMouseListener(new MouseAdapter() {
			@Override
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
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Enter Access Code!");
				txt_accesscode.setVisible(true);
			}
		});
		txt_accesscode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == arg0.VK_ENTER) {
					try {
						String strAccessCode = "Select accessCode from user_accnt where accessCode = ?";
						PreparedStatement pst = PointOfSales.getConnect().prepareStatement(strAccessCode);
						pst.setString(1, txt_accessCode.getText());
						ResultSet result = pst.executeQuery();
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
								txt_accesscode.setVisible(false);

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
		txt_quantity.addKeyListener(new KeyAdapter() {
			String ab;

			@Override
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
		txt_amountgive.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					String strtotal = txt_totalAmount.getText();
					double total = Double.parseDouble(strtotal);
					String stramount = txt_amountgive.getText();
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
					officialReceipt.getTextArea().append("\n" + "Vat:" + "\t" + "\t" + "\t" + "Php  " + vata);
					officialReceipt.getTextArea().append("\n" + "Vatable:" + "\t" + "\t" + "\t" + "Php  " + vatable);
					officialReceipt.getTextArea().append("\n" + "\n" + "\n" +"\n"+"\n"+ "\t" + " " + " " + sdate + "\n");
					officialReceipt.getTextArea().append("\t" + "Thank you for shopping!" + "\n");

				}
			}
		});
		btnCancelUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSaveUser.setVisible(false);
				btnEditUser.setVisible(false);
				btnUpdateUser.setVisible(false);
				btnCancelUser.setVisible(false);
				txt_lastname.setText(null);
				txt_firstname.setText(null);
				pswd_userPassword.setText(null);
				txt_username.setText(null);
				txt_accessCode.setVisible(false);
				btnAccessCode.setVisible(false);
				cmb_userLevel.setEnabled(false);
				cmb_userStatus.setEnabled(false);
				btnNew.setEnabled(true);
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
		Connection con=null;
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
							TableColumnModel model1 = SuperAdminAccess.table_reports.getColumnModel();
							model1.getColumn(0).setPreferredWidth(20);
							model1.getColumn(1).setPreferredWidth(250);
							model1.getColumn(2).setPreferredWidth(50);
							model1.getColumn(3).setPreferredWidth(50);
							
						} 
					} catch (Exception io) {
						JOptionPane.showMessageDialog(null, io);
						io.printStackTrace();
					}
					
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
				btn_updateProduct.setEnabled(true);
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
		btnNewproduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
			public void actionPerformed(ActionEvent arg0) {
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
		cmb_userLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					if ((cmb_userLevel.getSelectedItem().toString()).equals("Admin")) {
						txt_accessCode.setVisible(true);
						btnAccessCode.setVisible(true);

					} else {
						txt_accessCode.setVisible(false);
						btnAccessCode.setVisible(false);

					}
				}
			
		});

	}

	public JFrame getFrame() {
		return frmVcmSalesAnd;
	}
}
