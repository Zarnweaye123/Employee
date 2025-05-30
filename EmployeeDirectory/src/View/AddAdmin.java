package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Config.Checking;

public class AddAdmin extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JTextField txtName;
    private static JTextField txtEmail;
    private static JTextField txtPhone;
    private static JTextField txtHiringDate;

    public AddAdmin() {
        setLayout(null);

        JLabel lblHeader = new JLabel("Add Admin Details");
        lblHeader.setBounds(38, 10, 300, 30);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblHeader);

        setPreferredSize(new Dimension(769, 679));
        setBackground(new Color(245, 245, 245));

        JPanel panel = new JPanel();
        panel.setBounds(58, 53, 666, 611);
        add(panel);
        panel.setLayout(null);

        JLabel lbName = new JLabel("<html>Name <span style='color:red;'>*</span></html>");
        lbName.setFont(new Font("Arial", Font.PLAIN, 14));
        lbName.setBounds(31, 10, 73, 20);
        panel.add(lbName);
        
        txtName = new JTextField();
        txtName.setBounds(31, 35, 598, 25);
        panel.add(txtName);
            
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                // Get the character typed
                char typedChar = e.getKeyChar();
 
                // Check if the typed character is a digit
                if (Character.isDigit(typedChar)) {
                    // Consume the event to prevent the digit from being entered
                    e.consume();
//                    lblMessage.setText("Username cannot contain digits.");
                }
            }
        });

        JLabel lbPhone = new JLabel("<html>Phone Number <span style='color:red;'>*</span></html>");
        lbPhone.setFont(new Font("Arial", Font.PLAIN, 14));
        lbPhone.setBounds(31, 70, 135, 20);
        panel.add(lbPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(31, 100, 598, 25);
        panel.add(txtPhone);
        
        txtPhone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String phoneInput = txtPhone.getText();

                // Check if the phone number has exactly 11 digits and starts with "09"
                if (!Checking.isPhoneNo(phoneInput) || phoneInput.length() != 11) {
                    // Set the border color to red if the validation fails
                    txtPhone.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    // Reset the border to the default color if the validation passes
                    txtPhone.setBorder(UIManager.getBorder("TextField.border"));
                }
            }
        });

        
        JLabel lbEmail = new JLabel("<html>Email <span style='color:red;'>*</span></html>");
        lbEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        lbEmail.setBounds(31, 135, 73, 20);
        panel.add(lbEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(31, 170, 598, 25);
        panel.add(txtEmail);
        
        txtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String emailInput = txtEmail.getText();

                // Check if the email format is valid using the Checking method
                if (!Checking.IsEmailFormat(emailInput)) {
                    // Set the border color to red if the validation fails
                    txtEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    // Reset the border to the default color if the validation passes
                    txtEmail.setBorder(UIManager.getBorder("TextField.border"));
                }
            }
        });

        JLabel lbHiringDate = new JLabel("<html>Hiring Date <span style='color:red;'>*</span></html>");
        lbHiringDate.setFont(new Font("Arial", Font.PLAIN, 14));
        lbHiringDate.setBounds(31, 205, 100, 20);
        panel.add(lbHiringDate);

        txtHiringDate = new JTextField();
        txtHiringDate.setBounds(31, 235, 598, 25);
        panel.add(txtHiringDate);

        JCheckBox chkIsActive = new JCheckBox();
        chkIsActive.setFont(new Font("Arial", Font.PLAIN, 14));
        chkIsActive.setBounds(31, 351, 25, 25);
        panel.add(chkIsActive);
        chkIsActive.setBackground(Color.WHITE);

        JCheckBox chkIsAgreement = new JCheckBox();
        chkIsAgreement.setBounds(31, 408, 25, 25);
        panel.add(chkIsAgreement);
        chkIsAgreement.setBackground(Color.WHITE);

        JCheckBox chkIsManager = new JCheckBox();
        chkIsManager.setFont(new Font("Arial", Font.PLAIN, 14));
        chkIsManager.setBounds(31, 301, 25, 25);
        panel.add(chkIsManager);
        chkIsManager.setBackground(Color.WHITE);

        JLabel lbDepartment = new JLabel("<html>Department <span style='color:red;'>*</span></html>");
        lbDepartment.setFont(new Font("Arial", Font.PLAIN, 14));
        lbDepartment.setBounds(31, 439, 100, 20);
        panel.add(lbDepartment);

        JLabel lbJobTitle = new JLabel("<html>Job Title <span style='color:red;'>*</span></html>");
        lbJobTitle.setFont(new Font("Arial", Font.PLAIN, 14));
        lbJobTitle.setBounds(31, 504, 100, 20);
        panel.add(lbJobTitle);

        JButton btnSave = new JButton("Save");
        btnSave.setBackground(Color.RED);
        btnSave.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSave.setBounds(31, 563, 85, 30);
        panel.add(btnSave);

        JLabel lbIsManager = new JLabel("<html>Is Manager <span style='color:red;'>*</span></html>");
        lbIsManager.setFont(new Font("Arial", Font.PLAIN, 14));
        lbIsManager.setBounds(31, 275, 100, 20);
        panel.add(lbIsManager);

        JLabel lbIsActive = new JLabel("<html>Is Active <span style='color:red;'>*</span></html>");
        lbIsActive.setFont(new Font("Arial", Font.PLAIN, 14));
        lbIsActive.setBounds(31, 332, 73, 13);
        panel.add(lbIsActive);

        JLabel lbIsAgreement = new JLabel("<html>Is Agreement <span style='color:red;'>*</span></html>");
        lbIsAgreement.setFont(new Font("Arial", Font.PLAIN, 14));
        lbIsAgreement.setBounds(31, 382, 100, 20);
        panel.add(lbIsAgreement);
        
        JComboBox cboDepartment = new JComboBox();
        cboDepartment.setBounds(31, 469, 303, 21);
        panel.add(cboDepartment);
        
        JComboBox cboJobTitle = new JComboBox();
        cboJobTitle.setBounds(31, 532, 303, 21);
        panel.add(cboJobTitle);

        // 🔍 Validation Logic
        btnSave.addActionListener(e -> {
            String name = txtName.getText();
            String email = txtEmail.getText();
            String phone = txtPhone.getText();
            String hiringDate = txtHiringDate.getText();

            if (Checking.isEmptyOrWhitespace(name)) {
                showMessage("Name cannot be empty");
                return;
            }

            if (!Checking.IsValidName(name)) {
                showMessage("Name cannot start with a space");
                return;
            }



            if (!Checking.isValidPhoneNumber(phone)) {
                showMessage("Phone number must be 11 digits");
                return;
            }

            if (!Checking.isPhoneNo(phone)) {
                showMessage("Phone number must start with 09");
                return;
            }

            if (Checking.isEmptyOrWhitespace(hiringDate)) {
                showMessage("Hiring Date cannot be empty");
                return;
            }

           

            showMessage("All data is valid. Ready to save!");
        });
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Validation", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("AddAdmin");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new JScrollPane(new AddAdmin()));
            frame.setSize(740, 750);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
