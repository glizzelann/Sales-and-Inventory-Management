import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

public class myMethods {

	public static class FieldNames {
		public static final String USERNAME = "user_username";
		public static final String PASSWORD = "user_password";
		public static final String LEVEL = "user_level";
		public static final String EMP_ID = "Emp_ID";
		public static final String INVALID_ATTEMPTS = "InvalidAttempts";
		public static final String REVOKED_TIMESTAMP = "RevokedTimestamp";

	}

	public static class LevelType {
		public static final String ADMIN = "Admin";
		public static final String EMPLOYEE = "Employee";
		public static final String SUPER_ADMIN = "Super Admin";
	}

	public static class adminMethods {
		public static void loginHistory_table() {
			Connection con = PointOfSales.getConnect();
			try {
				String sql = "Select historyID as 'ID', userName as 'Username',user_Name as 'Name', loginDate as 'Login Date' from loginhistory";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				AdminAccess.tbl_loginHistory.setModel(DbUtils.resultSetToTableModel(rs));
				TableColumnModel model1 = AdminAccess.tbl_loginHistory.getColumnModel();
				model1.getColumn(0).setPreferredWidth(20);
				model1.getColumn(1).setPreferredWidth(150);
				model1.getColumn(2).setPreferredWidth(100);
				model1.getColumn(3).setPreferredWidth(100);
				
			} catch (Exception io) {
				JOptionPane.showMessageDialog(null, io);
				io.printStackTrace();
			}
		}

		public static void user_table() {
			Connection con = PointOfSales.getConnect();
			try {
				String sql = " Select user_id as 'Employee ID',user_Fname as 'First Name' , user_lnamel as ' Last Name',user_username as 'Username', user_password as 'Pssword', user_level as 'User Level', user_status as 'Status',accessCode as 'Access Code' from user_accnt order by user_id";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				AdminAccess.tbl_user.setModel(DbUtils.resultSetToTableModel(rs));
				TableColumnModel model1 = AdminAccess.tbl_user.getColumnModel();
				model1.getColumn(0).setPreferredWidth(20);
				model1.getColumn(1).setPreferredWidth(150);
				model1.getColumn(2).setPreferredWidth(150);
				model1.getColumn(3).setPreferredWidth(150);
				model1.getColumn(4).setPreferredWidth(150);
				model1.getColumn(5).setPreferredWidth(100);
				model1.getColumn(6).setPreferredWidth(100);
				model1.getColumn(7).setPreferredWidth(90);

			} catch (Exception io) {
				JOptionPane.showMessageDialog(null, io);
				io.printStackTrace();

			}
		}

		public static void classname() {
			Thread clock = new Thread() {
				public void run() {
					// int ap;
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
							AdminAccess.txt_aps.setText(" AM");
							AdminAccess.txt_aps.setVisible(false);
						} else {
							AdminAccess.txt_aps.setText(" PM");
							AdminAccess.txt_aps.setVisible(false);
						}
						AdminAccess.txt_time
								.setText(" " + hour + ":" + minute + ":" + sec + AdminAccess.txt_aps.getText());
						AdminAccess.txt_date.setText(" " + day + "-" + month + "-" + yr);

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

		public static void productInventory_table() {
			Connection con = PointOfSales.getConnect();
			try {
				String sql = " Select ProductCode as 'Product Code',ProductName as 'Product Name' , Stock as ' Quantity',Price as 'Price' from productinventory order by ProductCode";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				AdminAccess.tbl_productInventory.setModel(DbUtils.resultSetToTableModel(rs));
				TableColumnModel model1 = AdminAccess.tbl_productInventory.getColumnModel();
				model1.getColumn(0).setPreferredWidth(20);
				model1.getColumn(1).setPreferredWidth(250);
				model1.getColumn(2).setPreferredWidth(50);
				model1.getColumn(3).setPreferredWidth(100);
			} catch (Exception io) {
				JOptionPane.showMessageDialog(null, io);
				io.printStackTrace();

			}
		}

		public static void table_warning() {
			Connection con = PointOfSales.getConnect();
			try {

				String asd = "Select ProductCode as 'Product Code',ProductName as 'Product Name',Stock as 'Remanining Stocks' from productinventory where Stock <=10 and Stock >0";
				PreparedStatement pst3 = con.prepareStatement(asd);
				ResultSet rs = pst3.executeQuery();
				AdminAccess.tbl_warning.setModel(DbUtils.resultSetToTableModel(rs));
				TableColumnModel model1 = AdminAccess.tbl_warning.getColumnModel();
				model1.getColumn(0).setPreferredWidth(20);
				model1.getColumn(1).setPreferredWidth(250);
				model1.getColumn(2).setPreferredWidth(50);
				
			} catch (Exception io) {
				JOptionPane.showMessageDialog(null, io);
				io.printStackTrace();

			}
		}

		public static void sales_table() {
			Connection con = PointOfSales.getConnect();
			try {
				String sql = " Select Prod_code as 'Product Code',Prod_name as 'Product Name',Product_Sold as 'Product Sold', date as 'Sales Date' from sales order by Prod_Code";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				AdminAccess.table_reports.setModel(DbUtils.resultSetToTableModel(rs));
				TableColumnModel model1 = AdminAccess.table_reports.getColumnModel();
				model1.getColumn(0).setPreferredWidth(20);
				model1.getColumn(1).setPreferredWidth(250);
				model1.getColumn(2).setPreferredWidth(50);
				model1.getColumn(3).setPreferredWidth(100);
			} catch (Exception io) {
				JOptionPane.showMessageDialog(null, io);
				io.printStackTrace();

			}
		}

		public static void All_table() {
			Connection con = PointOfSales.getConnect();
			try {
				String sql = " Select ProductCode as 'Product Code',ProductName as 'Product Name' , Stock as ' Quantity',ProductSold as 'Sold',Price as 'Price', pullOut as 'Pull -out', reason as 'Reason' from productinventory order by ProductCode";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				AdminAccess.table_reports.setModel(DbUtils.resultSetToTableModel(rs));
				TableColumnModel model1 = AdminAccess.table_reports.getColumnModel();
				model1.getColumn(0).setPreferredWidth(20);
				model1.getColumn(1).setPreferredWidth(250);
				model1.getColumn(2).setPreferredWidth(50);
				model1.getColumn(3).setPreferredWidth(50);
				model1.getColumn(4).setPreferredWidth(50);
				model1.getColumn(5).setPreferredWidth(50);
				model1.getColumn(6).setPreferredWidth(50);
				
			} catch (Exception io) {
				
				JOptionPane.showMessageDialog(null, io);
				io.printStackTrace();

			}
		}

		public static void pullOut_table() {
			Connection con = PointOfSales.getConnect();
			try {
				String sql = " Select ProductCode as 'Product Code',ProductName as 'Product Name' , pullOut as 'Pull-out', reason as 'Reason' from productinventory order by ProductCode";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				AdminAccess.table_reports.setModel(DbUtils.resultSetToTableModel(rs));
				TableColumnModel model1 = AdminAccess.table_reports.getColumnModel();
				model1.getColumn(0).setPreferredWidth(20);
				model1.getColumn(1).setPreferredWidth(250);
				model1.getColumn(2).setPreferredWidth(50);
				model1.getColumn(3).setPreferredWidth(100);
			} catch (Exception io) {
				JOptionPane.showMessageDialog(null, io);
				io.printStackTrace();

			}
		}

		public static void stocksreport_table() {
			Connection con = PointOfSales.getConnect();
			try {
				String sql = " Select ProductCode as 'Product Code',ProductName as 'Product Name' , Stock as ' Stock' from productinventory order by productCode";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				AdminAccess.table_reports.setModel(DbUtils.resultSetToTableModel(rs));
				TableColumnModel model1 = AdminAccess.table_reports.getColumnModel();
				model1.getColumn(0).setPreferredWidth(20);
				model1.getColumn(1).setPreferredWidth(250);
				model1.getColumn(2).setPreferredWidth(50);

			} catch (Exception io) {
				JOptionPane.showMessageDialog(null, io);
				io.printStackTrace();

			}
		}

	}

	public static class superAdmin {

		public static void sales_table() {
			Connection con = PointOfSales.getConnect();
			try {
				String sql = " Select Prod_code as 'Product Code',Prod_name as 'Product Name',Product_Sold as 'Product Sold', date as 'Sales Date' from sales order by Prod_Code";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				SuperAdminAccess.table_reports.setModel(DbUtils.resultSetToTableModel(rs));
				TableColumnModel model1 = SuperAdminAccess.table_reports.getColumnModel();
				model1.getColumn(0).setPreferredWidth(20);
				model1.getColumn(1).setPreferredWidth(250);
				model1.getColumn(2).setPreferredWidth(50);
				model1.getColumn(3).setPreferredWidth(100);
				
			} catch (Exception io) {
				JOptionPane.showMessageDialog(null, io);
				io.printStackTrace();

			}
		}
		
			public static void loginHistory_table() {
				Connection con = PointOfSales.getConnect();
				try {
					String sql = "Select historyID as 'ID', userName as 'Username', user_Name as 'Name', loginDate as 'Login Date' from loginhistory";
					PreparedStatement pst = con.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					SuperAdminAccess.tbl_loginHistory.setModel(DbUtils.resultSetToTableModel(rs));
					TableColumnModel model1 = SuperAdminAccess.tbl_loginHistory.getColumnModel();
					model1.getColumn(0).setPreferredWidth(20);
					model1.getColumn(1).setPreferredWidth(150);
					model1.getColumn(2).setPreferredWidth(150);
					model1.getColumn(3).setPreferredWidth(70);
					
				} catch (Exception io) {
					JOptionPane.showMessageDialog(null, io);
					io.printStackTrace();
				}
			}

		public static void All_table() {
			Connection con = PointOfSales.getConnect();
			try {
				String sql = " Select ProductCode as 'Product Code',ProductName as 'Product Name' , Stock as ' Quantity',ProductSold as 'Sold',Price as 'Price', pullOut as 'Pull -out', reason as 'Reason' from productinventory order by ProductCode";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				SuperAdminAccess.table_reports.setModel(DbUtils.resultSetToTableModel(rs));
				TableColumnModel model1 = SuperAdminAccess.table_reports.getColumnModel();
				model1.getColumn(0).setPreferredWidth(20);
				model1.getColumn(1).setPreferredWidth(250);
				model1.getColumn(2).setPreferredWidth(50);
				model1.getColumn(3).setPreferredWidth(50);
				model1.getColumn(4).setPreferredWidth(50);
				model1.getColumn(5).setPreferredWidth(50);
				model1.getColumn(6).setPreferredWidth(100);
				
			} catch (Exception io) {
				JOptionPane.showMessageDialog(null, io);
				io.printStackTrace();

			}
		}

		public static void pullOut_table() {
			Connection con = PointOfSales.getConnect();
			try {
				String sql = " Select ProductCode as 'Product Code',ProductName as 'Product Name' , pullOut as 'Pull-out', reason as 'Reason' from productinventory order by ProductCode";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				SuperAdminAccess.table_reports.setModel(DbUtils.resultSetToTableModel(rs));
				TableColumnModel model1 = SuperAdminAccess.table_reports.getColumnModel();
				model1.getColumn(0).setPreferredWidth(20);
				model1.getColumn(1).setPreferredWidth(250);
				model1.getColumn(2).setPreferredWidth(50);
				model1.getColumn(3).setPreferredWidth(100);
			} catch (Exception io) {
				JOptionPane.showMessageDialog(null, io);
				io.printStackTrace();

			}
		}

		public static void stocksreport_table() {
			Connection con = PointOfSales.getConnect();
			try {
				String sql = " Select ProductCode as 'Product Code',ProductName as 'Product Name' , Stock as ' Quantity' from productinventory order by ProductCode";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				SuperAdminAccess.table_reports.setModel(DbUtils.resultSetToTableModel(rs));
				TableColumnModel model1 = SuperAdminAccess.table_reports.getColumnModel();
				model1.getColumn(0).setPreferredWidth(20);
				model1.getColumn(1).setPreferredWidth(250);
				model1.getColumn(2).setPreferredWidth(50);

			} catch (Exception io) {
				JOptionPane.showMessageDialog(null, io);
				io.printStackTrace();

			}
		}
		public static void superAdminClock() {
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
							SuperAdminAccess.txt_aps.setText(" AM");
							SuperAdminAccess.txt_aps.setVisible(false);
						} else {
							SuperAdminAccess.txt_aps.setText(" PM");
							SuperAdminAccess.txt_aps.setVisible(false);
						}

						SuperAdminAccess.txt_time.setText(" " + hour + ":" + minute + ":" + sec + SuperAdminAccess.txt_aps.getText());
						SuperAdminAccess.txt_date.setText(" " + day + "-" + month + "-" + yr);

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

	}

}
