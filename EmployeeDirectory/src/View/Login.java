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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Config.DBConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUserName;
    private JTextField txtPassword;
    private JLabel lblMessage;

    /**
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
    public Login() {
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
        lblNewLabel.setBounds(228, 120, 121, 21);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Username");
        lblNewLabel_1.setBounds(102, 184, 90, 21);
        contentPane.add(lblNewLabel_1);

        txtUserName = new JTextField();
        txtUserName.setBounds(226, 185, 178, 28);
        contentPane.add(txtUserName);
        txtUserName.setColumns(10);

        // Add KeyListener to ensure only letters are entered in the username field
       
        JLabel lblNewLabel_2 = new JLabel("Password");
        lblNewLabel_2.setBounds(102, 255, 90, 13);
        contentPane.add(lblNewLabel_2);

        txtPassword = new JTextField();
        txtPassword.setBounds(228, 249, 176, 28);
        contentPane.add(txtPassword);
        txtPassword.setColumns(10);

        JButton btnLogin = new JButton("Login");
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

                    // First, check if the username exists in the database
                    String checkUserSQL = "SELECT * FROM empdirectory.admin WHERE admName = ?";
                    PreparedStatement pstCheckUser = conn.prepareStatement(checkUserSQL);
                    pstCheckUser.setString(1, username);
                    ResultSet rsUserCheck = pstCheckUser.executeQuery();

                    // If username doesn't exist
                    if (!rsUserCheck.next()) {
                        lblMessage.setText("Incorrect username!");
                        return;
                    }

                    // Now, check the password if the username exists
                    String sql = "SELECT * FROM empdirectory.admin WHERE admName = ? AND password = ?";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setString(1, username);
                    pst.setString(2, password);

                    ResultSet rs = pst.executeQuery();

                    // If both username and password are correct
                    if (rs.next()) {
                        lblMessage.setText("Login Successful!");

                        // Open the AdminNavBar window
                        new AdminNavBar().setVisible(true);
                        dispose(); // Close the login form
                    } else {
                        // If username exists but password doesn't match
                        lblMessage.setText("Incorrect password!");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    lblMessage.setText("Database connection error!");
                }
            }
        });

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
