package View;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Config.DBConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUserName;
    private JTextField txtPassword;
    private JLabel lblMessage;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        // [Previous initialization code remains the same until the btnLogin action listener]
    	setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 651, 476);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Load and scale the image
        ImageIcon icon = new ImageIcon("C:\\OJTProject\\image\\icon.jfif");
        Image img = icon.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);

        // Add image label
        JLabel lblImage = new JLabel(scaledIcon);
        lblImage.setBounds(250, 20, 120, 100);
        contentPane.add(lblImage);

        JLabel lblNewLabel = new JLabel("EmployeeDirectory");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 22));
        lblNewLabel.setBounds(228, 120, 276, 21);
        contentPane.add(lblNewLabel);

        JLabel lbUserName = new JLabel("Username");
        lbUserName.setFont(new Font("Arial", Font.PLAIN, 13));
        lbUserName.setBounds(102, 184, 90, 21);
        contentPane.add(lbUserName);

        txtUserName = new JTextField();
        txtUserName.setBounds(226, 185, 178, 28);
        contentPane.add(txtUserName);
        txtUserName.setColumns(10);
        txtUserName.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                // Get the character typed
                char typedChar = e.getKeyChar();

                // Check if the typed character is a digit
                if (Character.isDigit(typedChar)) {
                    // Consume the event to prevent the digit from being entered
                    e.consume();
                    lblMessage.setText("Username cannot contain digits.");
                }
            }
        });

        // Add KeyListener to ensure only letters are entered in the username field
       
        JLabel lblNewLabel_2 = new JLabel("Password");
        lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 13));
        lblNewLabel_2.setBounds(102, 255, 90, 13);
        contentPane.add(lblNewLabel_2);

        txtPassword = new JTextField();
        txtPassword.setBounds(228, 249, 176, 28);
        contentPane.add(txtPassword);
        txtPassword.setColumns(10);

    	
    	
    	
    	
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 13));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtUserName.getText().trim();
                String password = txtPassword.getText().trim();

                // Reset the error message if fields are filled
                lblMessage.setText("");

                // If both fields are empty
                if (username.isEmpty() && password.isEmpty()) {
                    lblMessage.setText("Invalid username and password!");
                    return;
                }

                // If username is filled but password is empty
                if (!username.isEmpty() && password.isEmpty()) {
                    lblMessage.setText("Invalid Password");
                    return;
                }

                try {
                    // Connect to the database
                    DBConfig db = new DBConfig();
                    Connection conn = db.getConnection();

                    // First check if it's an admin
                    String adminSQL = "SELECT * FROM empdirectory.admin WHERE admName = ? AND password = ?";
                    PreparedStatement adminStmt = conn.prepareStatement(adminSQL);
                    adminStmt.setString(1, username);
                    adminStmt.setString(2, password);
                    ResultSet adminRs = adminStmt.executeQuery();

                    if (adminRs.next()) {
                        lblMessage.setText("Admin Login Successful!");
                        new AdminNavBar().setVisible(true);
                        dispose();
                        return;
                    }

                    // If not admin, check if it's an employee
                    String employeeSQL = "SELECT * FROM empdirectory.employee WHERE empName = ? AND password = ?";
                    PreparedStatement employeeStmt = conn.prepareStatement(employeeSQL);
                    employeeStmt.setString(1, username);
                    employeeStmt.setString(2, password);
                    ResultSet employeeRs = employeeStmt.executeQuery();

                    if (employeeRs.next()) {
                        lblMessage.setText("Employee Login Successful!");
                        new EmployeeNavBar().setVisible(true);
                        dispose();
                        return;
                    }

                    // If neither admin nor employee matched
                    // Check if username exists in either table
                    String checkAdminSQL = "SELECT * FROM empdirectory.admin WHERE admName = ?";
                    PreparedStatement checkAdminStmt = conn.prepareStatement(checkAdminSQL);
                    checkAdminStmt.setString(1, username);
                    ResultSet checkAdminRs = checkAdminStmt.executeQuery();

                    String checkEmployeeSQL = "SELECT * FROM empdirectory.employee WHERE empName = ?";
                    PreparedStatement checkEmployeeStmt = conn.prepareStatement(checkEmployeeSQL);
                    checkEmployeeStmt.setString(1, username);
                    ResultSet checkEmployeeRs = checkEmployeeStmt.executeQuery();

                    if (!checkAdminRs.next() && !checkEmployeeRs.next()) {
                        lblMessage.setText("Incorrect username and password!");
                    } else {
                        lblMessage.setText("Incorrect password!");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    lblMessage.setText("Database connection error!");
                }
            }
        });

        // [Rest of the code remains the same]
        btnLogin.setBounds(268, 361, 91, 21);
        contentPane.add(btnLogin);

        // Label to show messages (login result)
        lblMessage = new JLabel("");
        lblMessage.setBounds(226, 305, 204, 28);
        contentPane.add(lblMessage);

        // Clear message when the user starts typing in the fields
        txtUserName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                lblMessage.setText(""); // Clear message when user focuses on username field
            }
        });

        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                lblMessage.setText(""); // Clear message when user focuses on password field
            }
        });
    }
}