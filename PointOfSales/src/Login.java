import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class Login {

	private JFrame frmLogin;
	private JTextField txt_username;
	private JPasswordField password;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		byte[] input;
		byte[] keyBytes = "12345678".getBytes();
		byte[] ivBytes = "input123".getBytes();
			SecretKeySpec key = new SecretKeySpec(keyBytes,"DES");
			IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
			Cipher cipher;
			byte[] cipherText;
			int ctLength;
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.getContentPane().setBackground(new Color(0, 0, 0));
		frmLogin.addWindowListener(new WindowAdapter() {
			Connection con = PointOfSales.getConnect();

			public void windowOpened(WindowEvent arg0) {
			}
		});
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 750, 500);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBackground(new Color(255, 255, 204));
		label.setBounds(0, 0, 744, 52);
		frmLogin.getContentPane().add(label);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(255, 255, 204));
		lblUsername.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		lblUsername.setBounds(197, 185, 151, 20);
		frmLogin.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 255, 204));
		lblPassword.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		lblPassword.setBounds(197, 259, 151, 20);
		frmLogin.getContentPane().add(lblPassword);

		txt_username = new JTextField();
		txt_username.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		txt_username.setBounds(363, 182, 176, 26);
		frmLogin.getContentPane().add(txt_username);
		txt_username.setColumns(10);

		password = new JPasswordField();
		password.addKeyListener(new KeyAdapter() {
			AdminAccess ac = new AdminAccess();
			EmployeeAccess ab = new EmployeeAccess();

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == arg0.VK_ENTER) {
					if (txt_username.getText().isEmpty() || (password.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "username and passowrd must not be empty");
						// unused
					} else {
						try {
							String uname = txt_username.getText();
							String pass = password.getText();
							Timestamp dateNow = new Timestamp(System.currentTimeMillis());
							SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
							String s = sdf.format(dateNow);
							String sdate = String.valueOf(s);
							

							String sql1 = "Select * from user_accnt where user_username = ? ";

							pst = PointOfSales.getConnect().prepareStatement(sql1);
							pst.setString(1, txt_username.getText());
							
							rs = pst.executeQuery();
							if (rs.next()) { // Username exist
								
								
								if (!MyDateTimeUtil.isRevokedTimeLessThan30Mins(
										rs.getString(myMethods.FieldNames.REVOKED_TIMESTAMP))) {
									// Can login now again greater than 24 hours
									String invalidattemptsUpdate = "Update user_accnt set InvalidAttempts = ?, RevokedTimestamp=? where user_username=? ";
									PreparedStatement abc = PointOfSales.getConnect()
											.prepareStatement(invalidattemptsUpdate);
									abc.setInt(1, 0);
									abc.setString(2, "");
									abc.setString(3, uname);
									abc.executeUpdate();
									

									String sql2 = "Select * from user_accnt where user_username = ? and user_password = ?";

									pst = PointOfSales.getConnect().prepareStatement(sql2);
									pst.setString(1, uname);
									pst.setString(2, pass);
									if (pass.equals(rs.getString("user_password"))) {
										ResultSet rs2 = pst.executeQuery();
										if (rs2.next()) { // User access valid

											if (rs2.getString(myMethods.FieldNames.LEVEL)
													.equalsIgnoreCase(myMethods.LevelType.ADMIN)
													&& rs.getString("user_status").equals("Active")) {
											if (rs2.getString(myMethods.FieldNames.LEVEL).equalsIgnoreCase("Admin")) {

												ac.getFrame().setVisible(true);
												String lname = rs2.getString("user_lnamel");
												String fname = rs2.getString("user_Fname");
												AdminAccess.lbl_admin.setText(lname + ", " + fname);
												frmLogin.dispose();
												
												
												String sql = "insert into loginhistory (userName,user_Name,loginDate)values(?,?,?)";
												String strname = lname +", " + fname;
												PreparedStatement pst = PointOfSales.getConnect().prepareStatement(sql);
												pst.setString(1, txt_username.getText());
												pst.setString(2, strname);
												pst.setString(3, sdate);
												pst.execute();
												
												
												
											}
										} else if (rs2.getString(myMethods.FieldNames.LEVEL)
												.equalsIgnoreCase(myMethods.LevelType.SUPER_ADMIN)
												&& rs.getString("user_status").equals("Active")) {
											SuperAdminAccess rg = new SuperAdminAccess();
											rg.getFrame().setVisible(true);
											String lname = rs2.getString("user_lnamel");
											String fname = rs2.getString("user_Fname");
											SuperAdminAccess.txt_superAdmin.setText(lname + ", " + fname);
											frmLogin.dispose();
											
											String sql = "insert into loginhistory (userName,user_Name,loginDate)values(?,?,?)";
											String strname = lname +", " + fname;
											PreparedStatement pst = PointOfSales.getConnect().prepareStatement(sql);
											pst.setString(1, txt_username.getText());
											pst.setString(2, strname);
											pst.setString(3, sdate);
											pst.execute();
										} else {
											if (rs2.getString(myMethods.FieldNames.LEVEL)
													.equalsIgnoreCase(myMethods.LevelType.EMPLOYEE)
													&& rs.getString("user_status").equals("Active")) {

												ab.getFrame().setVisible(true);
												String lname1 = rs2.getString("user_lnamel");
												String fname1 = rs2.getString("user_Fname");
												EmployeeAccess.txt_employee.setText(lname1 + ", " + fname1);
												frmLogin.dispose();
												String sql = "insert into loginhistory (userName,user_Name,loginDate)values(?,?,?)";
												String strname = lname1 +", " + fname1;
												PreparedStatement pst = PointOfSales.getConnect().prepareStatement(sql);
												pst.setString(1, txt_username.getText());
												pst.setString(2,  strname);
												pst.setString(3, sdate);
												pst.execute();

											}
										}
									}

									} else {
										System.out.println("Password invalid...");
										int attempts = rs.getInt(myMethods.FieldNames.INVALID_ATTEMPTS);
										if (attempts < 3) {
											String sqlUpdate = "Update user_accnt set InvalidAttempts = ?, RevokedTimestamp = ? where user_username = ?";
											PreparedStatement ps = PointOfSales.getConnect()
													.prepareStatement(sqlUpdate);
											attempts++;
											ps.setInt(1, attempts);
											ps.setString(3, uname);

											if (attempts == 3) {
												ps.setString(2, String.valueOf(System.currentTimeMillis()));
											} else {
												ps.setString(2, "");
											}

											int rowAffected = ps.executeUpdate();

											if (rowAffected == 0)
												System.out.println("Updating attemps did not take effect....");

											if (attempts < 3) {
												JOptionPane.showMessageDialog(null,
														"Invalid Password! Invalid attempts " + attempts);
											} else {
												JOptionPane.showMessageDialog(null,
														"Invalid Password! You've reached the max attempts. You can try again after few minutes.");
												System.exit(0);
											}
										} else {
											JOptionPane.showMessageDialog(null,
													"Invalid Password! You've reached the max attempts. You can try again after 24 hours.");
											System.exit(0);
										}
									}

								} else {
									// Cannot login now less than 24 hours
									JOptionPane.showMessageDialog(null, "Cannot Access! try Again after few minutes");
								}

							} else {

								try {
									JOptionPane.showMessageDialog(null, "Invalid Username and Password");

								} catch (Exception io) {
									JOptionPane.showMessageDialog(null, io);
									io.printStackTrace();
								}

							}
							

						} catch (Exception ab) {
							JOptionPane.showMessageDialog(null, ab);
							ab.printStackTrace();
						}
					}
					

				}
			}
		});

		password.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		password.setBounds(363, 256, 176, 26);
		frmLogin.getContentPane().add(password);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Timestamp dateNow = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				String s = sdf.format(dateNow);
				String sdate = String.valueOf(s);
				AdminAccess adminaccess1 = new AdminAccess();
				EmployeeAccess ab = new EmployeeAccess();
				if (txt_username.getText().isEmpty() || (password.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "username and passowrd must not be empty");
					// unused
				} else {
					try {
						String uname = txt_username.getText();
						String pass = password.getText();
						

						String sql1 = "Select * from user_accnt where user_username = ? ";

						pst = PointOfSales.getConnect().prepareStatement(sql1);
						pst.setString(1, txt_username.getText());

						rs = pst.executeQuery();
						if (rs.next()) { // Username exist

							if (!MyDateTimeUtil.isRevokedTimeLessThan30Mins(
									rs.getString(myMethods.FieldNames.REVOKED_TIMESTAMP))) {
								// Can login now again greater than 24 hours
								String invalidattemptsUpdate = "Update user_accnt set InvalidAttempts = ?, RevokedTimestamp=? where user_username=? ";
								PreparedStatement abc = PointOfSales.getConnect()
										.prepareStatement(invalidattemptsUpdate);
								abc.setInt(1, 0);
								abc.setString(2, "");
								abc.setString(3, uname);
								abc.executeUpdate();

								String sql2 = "Select * from user_accnt where user_username = ? and user_password = ?";

								pst = PointOfSales.getConnect().prepareStatement(sql2);
								pst.setString(1, uname);
								pst.setString(2, pass);
								if (pass.equals(rs.getString("user_password"))) {
									ResultSet rs2 = pst.executeQuery();
									if (rs2.next()) { // User access valid

										if (rs2.getString(myMethods.FieldNames.LEVEL)
												.equalsIgnoreCase(myMethods.LevelType.ADMIN)
												&& rs.getString("user_status").equals("Active")) {
										if (rs2.getString(myMethods.FieldNames.LEVEL).equalsIgnoreCase("Admin")) {

											adminaccess1.getFrame().setVisible(true);
											String lname = rs2.getString("user_lnamel");
											String fname = rs2.getString("user_Fname");
											AdminAccess.lbl_admin.setText(lname + ", " + fname);
											frmLogin.dispose();
											
											
											String sql = "insert into loginhistory (userName,user_Name,loginDate)values(?,?,?)";
											String strname = lname +", " + fname;
											PreparedStatement pst = PointOfSales.getConnect().prepareStatement(sql);
											pst.setString(1, txt_username.getText());
											pst.setString(2, strname);
											pst.setString(3, sdate);
											pst.execute();
											
											
											
										}
									} else if (rs2.getString(myMethods.FieldNames.LEVEL)
											.equalsIgnoreCase(myMethods.LevelType.SUPER_ADMIN)
											&& rs.getString("user_status").equals("Active")) {
										SuperAdminAccess rg = new SuperAdminAccess();
										rg.getFrame().setVisible(true);
										String lname = rs2.getString("user_lnamel");
										String fname = rs2.getString("user_Fname");
										SuperAdminAccess.txt_superAdmin.setText(lname + ", " + fname);
										frmLogin.dispose();
										
										String sql = "insert into loginhistory (userName,user_Name,loginDate)values(?,?,?)";
										String strname = lname +", " + fname;
										PreparedStatement pst = PointOfSales.getConnect().prepareStatement(sql);
										pst.setString(1, txt_username.getText());
										pst.setString(2, strname);
										pst.setString(3, sdate);
										pst.execute();
									} else {
										if (rs2.getString(myMethods.FieldNames.LEVEL)
												.equalsIgnoreCase(myMethods.LevelType.EMPLOYEE)
												&& rs.getString("user_status").equals("Active")) {

											ab.getFrame().setVisible(true);
											String lname1 = rs2.getString("user_lnamel");
											String fname1 = rs2.getString("user_Fname");
											EmployeeAccess.txt_employee.setText(lname1 + ", " + fname1);
											frmLogin.dispose();
											String sql = "insert into loginhistory (userName,user_Name,loginDate)values(?,?,?)";
											String strname = lname1 +", " + fname1;
											PreparedStatement pst = PointOfSales.getConnect().prepareStatement(sql);
											pst.setString(1, txt_username.getText());
											pst.setString(2,  strname);
											pst.setString(3, sdate);
											pst.execute();

										}
									}
								}

								} else {
									System.out.println("Password invalid...");
									int attempts = rs.getInt(myMethods.FieldNames.INVALID_ATTEMPTS);
									if (attempts < 3) {
										String sqlUpdate = "Update user_accnt set InvalidAttempts = ?, RevokedTimestamp = ? where user_username = ?";
										PreparedStatement ps = PointOfSales.getConnect()
												.prepareStatement(sqlUpdate);
										attempts++;
										ps.setInt(1, attempts);
										ps.setString(3, uname);

										if (attempts == 3) {
											ps.setString(2, String.valueOf(System.currentTimeMillis()));
										} else {
											ps.setString(2, "");
										}

										int rowAffected = ps.executeUpdate();

										if (rowAffected == 0)
											System.out.println("Updating attemps did not take effect....");

										if (attempts < 3) {
											JOptionPane.showMessageDialog(null,
													"Invalid Password! Invalid attempts " + attempts);
										} else {
											JOptionPane.showMessageDialog(null,
													"Invalid Password! You've reached the max attempts. You can try again after few minutes.");
											System.exit(0);
										}
									} else {
										JOptionPane.showMessageDialog(null,
												"Invalid Password! You've reached the max attempts. You can try again after 24 hours.");
										System.exit(0);
									}
								}

							} else {
								// Cannot login now less than 24 hours
								JOptionPane.showMessageDialog(null, "Cannot Access! try Again after few minutes");
							}

							

						} else {

							try {
								JOptionPane.showMessageDialog(null, "Invalid Username and Password");

							} catch (Exception io) {
								JOptionPane.showMessageDialog(null, io);
								io.printStackTrace();
							}

						}

					} catch (Exception abc) {
						JOptionPane.showMessageDialog(null, abc);
						abc.printStackTrace();
					}
				}

			}
		
	});
		btnLogin.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		btnLogin.setBounds(424, 319, 115, 29);
		frmLogin.getContentPane().add(btnLogin);

		JLabel lblNewLabel = new JLabel("VAPE CITY MANILA");
		lblNewLabel.setForeground(new Color(255, 255, 204));
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 44));
		lblNewLabel.setBounds(133, 90, 526, 52);
		frmLogin.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(255, 255, 204));
		textField_1.setBounds(0, 399, 744, 61);
		frmLogin.getContentPane().add(textField_1);
	}

	public Window getFrame() {
		// TODO Auto-generated method stub
		return frmLogin;
	}
}
