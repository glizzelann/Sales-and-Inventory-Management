import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;
import javax.swing.JComboBox;
import javax.swing.JButton;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import com.toedter.calendar.JDateChooser;

public class EmployeeAccess {

	private JFrame frmVcmSalesAnd;
	private static JTable tbl_warning1;
	private JTextField txt_productcode;
	private JTextField txt_price;
	private JTextField txt_productQuantity;
	private JTextField txt_subtotal;
	private JTextField txt_totalAmount;
	private JTextField txt_amountgive;
	private JTextField txt_change;
	private JTable tbl_summary;
	private JTextField txt_accessCode;
	private JTable table_reports;
	private JTextField txt_stocksonhand;
	DefaultTableModel mod;
	private static JTextField txt_time1;
	private static JTextField txt_date1;
	private static JTextField txt_aps1;
	public static JTextField txt_employee;
	ResultSet result;
	OfficialReceipt officialReceipt = new OfficialReceipt();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeAccess window = new EmployeeAccess();
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
	public EmployeeAccess() {
		initialize();
		classnameEmployee();
		table_warningemployee();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void table_warningemployee() {
		Connection con = PointOfSales.getConnect();
		try {

			String asd = "Select ProductCode as 'Product Code',ProductName as 'Product Name',Stock as 'Remanining Stocks' from productinventory where Stock <=10 and Stock >0";
			PreparedStatement pst3 = con.prepareStatement(asd);
			ResultSet rs = pst3.executeQuery();
			tbl_warning1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception io) {
			JOptionPane.showMessageDialog(null, io);
			io.printStackTrace();

		}
	}
	public void sales_table() {
		Connection con = PointOfSales.getConnect();
		try {
			String sql = " Select Prod_code as 'Product Code',Prod_name as 'Product Name',Product_Sold as 'Product Sold', date as 'Sales Date' from sales order by Prod_Code";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			table_reports.setModel(DbUtils.resultSetToTableModel(rs));
			TableColumnModel model1 =table_reports.getColumnModel();
			model1.getColumn(0).setPreferredWidth(20);
			model1.getColumn(1).setPreferredWidth(250);
			model1.getColumn(2).setPreferredWidth(50);
			model1.getColumn(3).setPreferredWidth(100);
		} catch (Exception io) {
			JOptionPane.showMessageDialog(null, io);
			io.printStackTrace();

		}
	}

	public void All_table() {
		Connection con = PointOfSales.getConnect();
		try {
			String sql = " Select ProductCode as 'Product Code',ProductName as 'Product Name' , Stock as ' Quantity',Price as 'Price', pullOut as 'Pull -out', reason as 'Reason' from productinventory order by ProductCode";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			table_reports.setModel(DbUtils.resultSetToTableModel(rs));
			TableColumnModel model1 =table_reports.getColumnModel();
			model1.getColumn(0).setPreferredWidth(20);
			model1.getColumn(1).setPreferredWidth(250);
			model1.getColumn(2).setPreferredWidth(50);
			model1.getColumn(3).setPreferredWidth(100);
		} catch (Exception io) {
			JOptionPane.showMessageDialog(null, io);
			io.printStackTrace();

		}
	}

	public void pullOut_table() {
		Connection con = PointOfSales.getConnect();
		try {
			String sql = " Select ProductCode as 'Product Code',ProductName as 'Product Name' , pullOut as 'Pull-out', reason as 'Reason' from productinventory order by ProductCode";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			table_reports.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception io) {
			JOptionPane.showMessageDialog(null, io);
			io.printStackTrace();

		}
	}

	public void stocksreport_table() {
		Connection con = PointOfSales.getConnect();
		try {
			String sql = " Select ProductCode as 'Product Code',ProductName as 'Product Name' , Stock as ' Quantity' from productinventory order by ProductCode";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			table_reports.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception io) {
			JOptionPane.showMessageDialog(null, io);
			io.printStackTrace();

		}
	}

	private static void classnameEmployee() {
		Thread clock = new Thread() {
			public void run() {
				int ap;
				for (int x = 0; x < 5;) {

					Calendar c = new GregorianCalendar();
					int day = c.get(Calendar.DAY_OF_MONTH);
					int month = c.get(Calendar.MONTH);
					month++;
					int yr = c.get(Calendar.YEAR);
					int hour = c.get(Calendar.HOUR);
					int minute = c.get(Calendar.MINUTE);
					int sec = c.get(Calendar.SECOND);
					int ampm = c.get(Calendar.AM_PM);
					if (ampm == 0) {
						txt_aps1.setText(" AM");
						txt_aps1.setVisible(false);
					} else {
						txt_aps1.setText(" PM");
						txt_aps1.setVisible(false);
					}
					txt_time1.setText(" " + hour + ":" + minute + ":" + sec + txt_aps1.getText());
					txt_date1.setText(" " + day + "-" + month + "-" + yr);

				}

				try {
					sleep(1000);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		};
		clock.start();
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
		frmVcmSalesAnd.setBackground(new Color(255, 255, 204));
		frmVcmSalesAnd.setBounds(100, 100, 1960, 1880);
		frmVcmSalesAnd.setLocationRelativeTo(null);
		frmVcmSalesAnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVcmSalesAnd.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
						if(tabbedPane.getSelectedIndex()==2)
						{
							frmVcmSalesAnd.dispose();
							new Login().getFrame().setVisible(true);
						}
					}
				});
			
		tabbedPane.setBackground(new Color(255, 255, 204));
		tabbedPane.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		tabbedPane.setBounds(10, 159, 1887, 848);
		frmVcmSalesAnd.getContentPane().add(tabbedPane);

		JPanel transactions = new JPanel();
		transactions.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Transactions", null, transactions, null);
		transactions.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 16, 762, 163);
		transactions.add(scrollPane);

		tbl_warning1 = new JTable();
		tbl_warning1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tbl_warning1.setRowHeight(35);
		tbl_warning1.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		scrollPane.setViewportView(tbl_warning1);

		JLabel lblProductCode = new JLabel("Product Code");
		lblProductCode.setForeground(new Color(255, 255, 204));
		lblProductCode.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblProductCode.setBounds(49, 278, 186, 30);
		transactions.add(lblProductCode);

		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setForeground(new Color(255, 255, 204));
		lblProductName.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblProductName.setBounds(49, 332, 186, 30);
		transactions.add(lblProductName);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(255, 255, 204));
		lblPrice.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblPrice.setBounds(49, 393, 186, 30);
		transactions.add(lblPrice);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(new Color(255, 255, 204));
		lblQuantity.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblQuantity.setBounds(49, 454, 186, 30);
		transactions.add(lblQuantity);

		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setForeground(new Color(255, 255, 204));
		lblSubtotal.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblSubtotal.setBounds(49, 505, 186, 30);
		transactions.add(lblSubtotal);

		txt_productcode = new JTextField();
		txt_productcode.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		txt_productcode.setBounds(284, 283, 311, 30);
		transactions.add(txt_productcode);
		txt_productcode.setColumns(10);

		JComboBox cmb_productName = new JComboBox();
		cmb_productName.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		cmb_productName.setModel(new DefaultComboBoxModel(new String[] { "Select Item" }));

		cmb_productName.setBounds(284, 337, 311, 30);
		transactions.add(cmb_productName);

		txt_price = new JTextField();
		txt_price.setText("0");
		txt_price.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		txt_price.setBounds(284, 398, 311, 30);
		transactions.add(txt_price);
		txt_price.setColumns(10);

		txt_productQuantity = new JTextField();
		txt_productQuantity.setText("0");
		
		txt_productQuantity.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		txt_productQuantity.setBounds(284, 459, 311, 30);
		transactions.add(txt_productQuantity);
		txt_productQuantity.setColumns(10);

		txt_subtotal = new JTextField();
		txt_subtotal.setText("0");
		txt_subtotal.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		txt_subtotal.setBounds(284, 510, 311, 30);
		transactions.add(txt_subtotal);
		txt_subtotal.setColumns(10);

		JButton btnAddToCart = new JButton("ADD TO CART");

		btnAddToCart.setFont(new Font("Bookman Old Style", Font.PLAIN, 26));
		btnAddToCart.setBounds(189, 634, 277, 43);
		transactions.add(btnAddToCart);

		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setForeground(new Color(255, 255, 204));
		lblTotalAmount.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblTotalAmount.setBounds(640, 278, 171, 30);
		transactions.add(lblTotalAmount);

		txt_totalAmount = new JTextField();
		txt_totalAmount.setText("0");
		txt_totalAmount.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		txt_totalAmount.setEditable(false);
		txt_totalAmount.setBounds(640, 324, 237, 30);
		transactions.add(txt_totalAmount);
		txt_totalAmount.setColumns(10);

		JLabel lblAmountGive = new JLabel("Amount Give");
		lblAmountGive.setForeground(new Color(255, 255, 204));
		lblAmountGive.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblAmountGive.setBounds(640, 370, 171, 30);
		transactions.add(lblAmountGive);

		txt_amountgive = new JTextField();
		
		txt_amountgive.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		txt_amountgive.setBounds(640, 416, 237, 30);
		transactions.add(txt_amountgive);
		txt_amountgive.setColumns(10);

		JLabel lblChange = new JLabel("Change");
		lblChange.setForeground(new Color(255, 255, 204));
		lblChange.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblChange.setBounds(640, 459, 171, 30);
		transactions.add(lblChange);

		txt_change = new JTextField();
		txt_change.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		txt_change.setEditable(false);
		txt_change.setBounds(640, 508, 237, 30);
		transactions.add(txt_change);
		txt_change.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		
		btnSubmit.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		btnSubmit.setBounds(652, 638, 159, 35);
		transactions.add(btnSubmit);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setAutoscrolls(true);
		scrollPane_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		scrollPane_1.setBounds(926, 195, 941, 427);
		transactions.add(scrollPane_1);

		tbl_summary = new JTable();
		tbl_summary.setAlignmentX(Component.RIGHT_ALIGNMENT);
		tbl_summary.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tbl_summary.setRowHeight(35);
		tbl_summary.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));

		scrollPane_1.setViewportView(tbl_summary);

		JButton btnRemove = new JButton("Remove");
		btnRemove.setVisible(false);
		btnRemove.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		btnRemove.setBounds(923, 638, 171, 35);
		transactions.add(btnRemove);

		txt_accessCode = new JTextField();
		txt_accessCode.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		txt_accessCode.setVisible(false);
		txt_accessCode.setBounds(1109, 638, 229, 35);
		transactions.add(txt_accessCode);
		txt_accessCode.setColumns(10);

		txt_stocksonhand = new JTextField();
		txt_stocksonhand.setBackground(new Color(0, 0, 0));
		txt_stocksonhand.setEditable(false);
		txt_stocksonhand.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_stocksonhand.setBounds(284, 237, 93, 30);
		transactions.add(txt_stocksonhand);
		txt_stocksonhand.setColumns(10);

		JLabel lblStocks = new JLabel("Stocks");
		lblStocks.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblStocks.setForeground(new Color(255, 255, 204));
		lblStocks.setBounds(49, 232, 106, 30);
		transactions.add(lblStocks);

		JPanel reports = new JPanel();
		reports.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Reports", null, reports, null);
		reports.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		scrollPane_2.setBounds(576, 91, 1270, 598);
		reports.add(scrollPane_2);

		table_reports = new JTable(){
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
		    	else if (col==4)
		    		return renderRight;
		    	else
		    		return renderLeft;
		    }
		};
		table_reports.setRowHeight(25);
		table_reports.setFont(new Font("Bookman Old Style", Font.PLAIN, 21));
		scrollPane_2.setViewportView(table_reports);

		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setForeground(new Color(255, 255, 204));
		lblStartDate.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblStartDate.setBounds(15, 166, 158, 35);
		reports.add(lblStartDate);

		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setForeground(new Color(255, 255, 204));
		lblEndDate.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblEndDate.setBounds(15, 247, 158, 35);
		reports.add(lblEndDate);
		
		JComboBox cmb_reports = new JComboBox();
		cmb_reports.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		
		cmb_reports.setModel(new DefaultComboBoxModel(new String[] {"All", "Stocks", "Sales", "Pulled-out"}));
		cmb_reports.setBounds(15, 91, 214, 35);
		reports.add(cmb_reports);
		
		JButton btnPrint = new JButton("Print");
		
		btnPrint.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		btnPrint.setBounds(127, 420, 199, 35);
		reports.add(btnPrint);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("MM-dd-yyyy");
		dateChooser.setBounds(210, 166, 233, 35);
		reports.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("MM-dd-yyyy");
		dateChooser_1.setBounds(210, 247, 233, 35);
		reports.add(dateChooser_1);
		
		JButton btnShow = new JButton("Show");
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
		btnShow.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		btnShow.setBounds(127, 341, 199, 35);
		reports.add(btnShow);
		
		JTabbedPane Logout = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Logout", null, Logout, null);

		JLabel lblEmployeesAccess = new JLabel("Employee's Access");
		lblEmployeesAccess.setBackground(new Color(255, 255, 204));
		lblEmployeesAccess.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblEmployeesAccess.setBounds(15, 67, 250, 39);
		frmVcmSalesAnd.getContentPane().add(lblEmployeesAccess);

		JLabel lblVapeCityManila = new JLabel("VAPE CITY MANILA");
		lblVapeCityManila.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
		lblVapeCityManila.setBounds(645, 6, 474, 52);
		frmVcmSalesAnd.getContentPane().add(lblVapeCityManila);

		JLabel lblIconHeader = new JLabel("");
		lblIconHeader.setBounds(15, 16, 134, 86);
		frmVcmSalesAnd.getContentPane().add(lblIconHeader);

		txt_time1 = new JTextField();
		txt_time1.setEditable(false);
		txt_time1.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_time1.setBackground(new Color(255, 255, 204));
		txt_time1.setBounds(1477, 16, 224, 30);
		frmVcmSalesAnd.getContentPane().add(txt_time1);
		txt_time1.setColumns(10);

		txt_date1 = new JTextField();
		txt_date1.setEditable(false);
		txt_date1.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_date1.setBackground(new Color(255, 255, 204));
		txt_date1.setBounds(1477, 62, 224, 30);
		frmVcmSalesAnd.getContentPane().add(txt_date1);
		txt_date1.setColumns(10);

		txt_aps1 = new JTextField();
		txt_aps1.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_aps1.setBackground(new Color(255, 255, 204));
		txt_aps1.setBounds(1727, 23, 146, 26);
		frmVcmSalesAnd.getContentPane().add(txt_aps1);
		txt_aps1.setColumns(10);
		
		txt_employee = new JTextField();
		txt_employee.setEditable(false);
		txt_employee.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txt_employee.setBackground(new Color(255, 255, 204));
		txt_employee.setBounds(15, 122, 307, 30);
		frmVcmSalesAnd.getContentPane().add(txt_employee);
		txt_employee.setColumns(10);

		JLabel lbl_employeeHeader = new JLabel("");
		lbl_employeeHeader.setBackground(new Color(255, 255, 204));
		lbl_employeeHeader.setBounds(0, 0, 1912, 165);
		frmVcmSalesAnd.getContentPane().add(lbl_employeeHeader);

		cmb_productName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet result;
				try {

					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/admin?AutoReconnect=true&useSSL=false", "root", "1234");
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

						txt_productcode.setText(result.getString("ProductCode"));

						txt_price.setText(result.getString("Price"));
						txt_stocksonhand.setText(result.getString("Stock"));
					} else {
						JOptionPane.showMessageDialog(null, "Out of Stocks!");
						txt_productcode.setText(" ");

						txt_price.setText("0");
					}

				} catch (Exception io) {
					JOptionPane.showMessageDialog(null, io);
					io.printStackTrace();
				}
			}
		});
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					

						int aiyan = 0;

						String subT = txt_subtotal.getText();
						double subTo = Double.parseDouble(subT);

						String oTotal = txt_totalAmount.getText();
						double total = Double.parseDouble(oTotal);

						double finalTotal = subTo + total;
						String fTotal = Double.toString(finalTotal);
						txt_totalAmount.setText(fTotal);

						String prodID = txt_productcode.getText().trim();
						String prodname = cmb_productName.getSelectedItem().toString().trim();
						String prodPrice = txt_price.getText().trim();
						String prodQuantity = txt_productQuantity.getText().trim();
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

							pst1.setString(1, txt_productcode.getText());
							pst1.setString(2, cmb_productName.getSelectedItem().toString());
							pst1.setString(3, txt_price.getText());
							pst1.setString(4, txt_productQuantity.getText());
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
							pst.setString(1, txt_productcode.getText());
							ResultSet rs1 = pst.executeQuery();
							if (rs1.next()) {

								String stock = txt_productQuantity.getText();
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
								pre.setString(1, txt_productcode.getText());
								pre.setString(2, cmb_productName.getSelectedItem().toString());
								pre.setString(3, txt_price.getText());
								pre.setString(4, strFinalSold);
								pre.setString(5, sdate);
								pre.execute();

								String sql1 = "update productinventory set Stock = ? where ProductCode=?";
								PreparedStatement pst2 = PointOfSales.getConnect().prepareStatement(sql1);
								pst2.setString(1, strFinalStocks);
								pst2.setString(2, txt_productcode.getText());
								pst2.executeUpdate();
							}
						} catch (Exception g) {
							JOptionPane.showMessageDialog(null, g);
							g.printStackTrace();
						}
						try {

							String sql2 = "Insert into transactions (productCode,prodName,quantity,Amount,transaction_date)values(?,?,?,?,?)";
							PreparedStatement pst3 = PointOfSales.getConnect().prepareStatement(sql2);
							pst3.setString(1, txt_productcode.getText());
							pst3.setString(2, cmb_productName.getSelectedItem().toString());
							pst3.setString(3, txt_productQuantity.getText());
							pst3.setString(5, sdate);
							pst3.setString(4, txt_subtotal.getText());
							pst3.execute();

						} catch (Exception ab) {
							JOptionPane.showMessageDialog(null, ab);
							ab.printStackTrace();
						}
						
						String appendprodname = cmb_productName.getSelectedItem().toString();
						String appendprodPrice = txt_price.getText();
						String appendprodQuantity = txt_productQuantity.getText();
						String appendprodSubtotal = txt_subtotal.getText();

						officialReceipt.getTextArea().append("\n" + appendprodname + "\n " + " \t   " + "   @   " + "Php " + appendprodPrice
								+" "+" "+ "x"+" "+" " + appendprodQuantity + "\t" + "Php " + appendprodSubtotal + "\n");
					
				} catch (Exception r) {
					JOptionPane.showMessageDialog(null, r);
					r.printStackTrace();
				}

				txt_productcode.setText(null);
				// txt_productName.setText(null);
				txt_price.setText("0");
				txt_subtotal.setText("0");

				txt_productQuantity.setText("0");
				txt_stocksonhand.setText(null);
				
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
				txt_productcode.setText(id);
				cmb_productName.setToolTipText(name);
				txt_price.setText(price);
				txt_productQuantity.setText(quantity);
				txt_subtotal.setText(subtotal);
				btnRemove.setVisible(true);
			}
		});
		txt_accessCode.addKeyListener(new KeyAdapter() {
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

								txt_productcode.setText(null);
								// txt_productName.setText(null);
								txt_price.setText("0");
								txt_productQuantity.setText(null);
								txt_subtotal.setText("0");

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
		cmb_reports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((cmb_reports.getSelectedItem().toString()).equals("Sales")) {
					sales_table();

				} else if ((cmb_reports.getSelectedItem().toString()).equals("Stocks")) {
					stocksreport_table();

				} else if ((cmb_reports.getSelectedItem().toString()).equals("Pulled-out")) {
					pullOut_table();
				} else if ((cmb_reports.getSelectedItem().toString()).equals("All")) {
					All_table();
				}
			}
		});
		txt_productQuantity.addKeyListener(new KeyAdapter() {
			String ab;
			@Override
			
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c))) {
					e.consume();
				}
				try {
					
					if (!txt_price.equals("")) {
						String price = txt_price.getText();
						double price1 = Double.parseDouble(price);
						String quantity = txt_productQuantity.getText();
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
		Timestamp dateNow = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String s = sdf.format(dateNow);
		String sdate = String.valueOf(s);
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
					officialReceipt.getTextArea().append("\n"+"Vat:" + "\t" + "\t" + "\t" + "Php  " + vata);
					officialReceipt.getTextArea().append("\n"+"Vatable:" + "\t" + "\t" + "\t" + "Php  " + vatable);
					officialReceipt.getTextArea().append("\n"+"\n"+"\n" + "\t" + " "+" "+ sdate + "\n");
					officialReceipt.getTextArea().append("\t" + "Thank you for shopping!" + "\n");
				}
			}
		});
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String glizzzel = cmb_reports.getSelectedItem().toString();
		        if (glizzzel.equals("Sales")) {
		            MessageFormat header = new MessageFormat("Vape City Manila Sales Report");
		            MessageFormat footer = new MessageFormat("Page{0, number, integer} " + "\n" + "Prepared by: "  +"\t"+ txt_employee.getText());
		            try {
		                table_reports.print(JTable.PrintMode.FIT_WIDTH, header, footer);
		            } catch (Exception ac) {
		                System.err.format("Cannot print %s%n", ac.getMessage());
		                JOptionPane.showMessageDialog(null, ac);
		                ac.printStackTrace();
		            }
		        } else if (glizzzel.equals("Stocks")) {
		            MessageFormat header = new MessageFormat("Vape City Manila Stocks Report" + "\n" + "Prepared by: " +"\t"+ txt_employee.getText());
		            MessageFormat footer = new MessageFormat("Page{0, number, integer}");
		            try {
		                table_reports.print(JTable.PrintMode.FIT_WIDTH, header, footer);
		            } catch (Exception ac) {
		                System.err.format("Cannot print %s%n", ac.getMessage());
		                JOptionPane.showMessageDialog(null, ac);
		                ac.printStackTrace();
		            }
		        }else if (glizzzel.equals("Pulled-out")) {
		            MessageFormat header = new MessageFormat("Vape City Manila Pulled-out Products Report" + "\n" + "Prepared by: " +"\t"+ txt_employee.getText());
		            MessageFormat footer = new MessageFormat("Page{0, number, integer}");
		            try {
		                table_reports.print(JTable.PrintMode.FIT_WIDTH, header, footer);
		            } catch (Exception ac) {
		                System.err.format("Cannot print %s%n", ac.getMessage());
		                JOptionPane.showMessageDialog(null, ac);
		                ac.printStackTrace();
		            }
		        }else if (glizzzel.equals("All")) {
		            MessageFormat header = new MessageFormat("Vape City Manila Report" + "\n" + "Prepared by: " +"\t"+ txt_employee.getText());
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
}
