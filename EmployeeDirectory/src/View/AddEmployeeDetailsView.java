package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import com.toedter.calendar.JDateChooser;

import Config.Checking;
import Controller.EmployeeDetailAdminController;
import Controller.EmployeeDetailsController;
import Model.EmployeeDetailAdminModel;

public class AddEmployeeDetailsView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JTextField txtName;
    private static JTextField txtPhoneNumber;
    private static JTextField txtEmail;
    private JTextField txtPassword;
    private JComboBox<String> cboDepartment;
    private JComboBox<String> cboJobTitle;

    private JCheckBox chkIsActive;
    private JCheckBox chkIsAgreement;
    private JCheckBox chkIsManager;
    private JDateChooser dateChooser;
    
    private String employeeId; // Hidden employee id stored here
    
    public AddEmployeeDetailsView() {
        setLayout(null);

        JLabel lblHeader = new JLabel("Employee Details");
        lblHeader.setBounds(40, 20, 300, 30);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblHeader);

        setPreferredSize(new Dimension(1000, 753));
        setBackground(new Color(245, 245, 245));
        
        JPanel panel = new JPanel();
        panel.setBounds(56, 89, 419, 653);
        add(panel);
        panel.setLayout(null);
        
        // Generate and store the employee ID but DO NOT show it on the UI
        employeeId = generateNextId();
        System.out.println("Generated Employee ID: " + employeeId); // debug
        
        JLabel lblName = new JLabel("<html>Name <span style='color:red;'>*</span></html>");
        lblName.setBounds(10, 20, 54, 13);
        panel.add(lblName);
                
        txtName = new JTextField();
        txtName.setBounds(10, 46, 300, 25);
        panel.add(txtName);
        txtName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String input = txtName.getText();
                if (!input.matches("[a-zA-Z\\s]*")) {
                    txtName.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    txtName.setBorder(UIManager.getBorder("TextField.border"));
                }
            }
        });

        JLabel lblEmail = new JLabel("<html>Email <span style='color:red;'>*</span></html>");
        lblEmail.setBounds(10, 87, 100, 20);
        panel.add(lblEmail);
       
        txtEmail = new JTextField();
        txtEmail.setBounds(10, 117, 300, 25);
        panel.add(txtEmail);
      
        txtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String input = txtEmail.getText();
                String emailRegex = "^[a-zA-Z0-9._%+-]+\\d+[a-zA-Z0-9._%+-]*@gmail\\.com$";
                if (!input.matches(emailRegex)) {
                    txtEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    txtEmail.setBorder(UIManager.getBorder("TextField.border"));
                }
            }
        });
                                        
        JLabel lblPhone = new JLabel("<html>Phone Number <span style='color:red;'>*</span></html>");
        lblPhone.setBounds(10, 152, 100, 20);
        panel.add(lblPhone);
                                                
        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setBounds(10, 182, 300, 25);
        panel.add(txtPhoneNumber);
        
        txtPhoneNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String input = txtPhoneNumber.getText();
                String phoneRegex = "^09\\d{9}$"; 
                if (!input.matches(phoneRegex)) {
                    txtPhoneNumber.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    txtPhoneNumber.setBorder(UIManager.getBorder("TextField.border"));
                }
            }
        });
                                                        
        JLabel lblHiringDate = new JLabel("<html>Hiring Date <span style='color:red;'>*</span></html>");
        lblHiringDate.setBounds(10, 217, 100, 20);
        panel.add(lblHiringDate);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(10, 248, 300, 20);
        panel.add(dateChooser);
           
        Calendar calendar = Calendar.getInstance();
        dateChooser.setMaxSelectableDate(calendar.getTime());

        JLabel lblIsActive = new JLabel("Is Active");
        lblIsActive.setBounds(10, 279, 100, 20);
        panel.add(lblIsActive);
                                                                                
        chkIsActive = new JCheckBox();
        chkIsActive.setBounds(10, 295, 25, 25);
        panel.add(chkIsActive);
        chkIsActive.setBackground(Color.WHITE);
                                                                                        
        JLabel lblIsAgreement = new JLabel("Is Agreement");
        lblIsAgreement.setBounds(10, 321, 100, 20);
        panel.add(lblIsAgreement);
                                                                                                
        chkIsAgreement = new JCheckBox();
        chkIsAgreement.setBounds(10, 337, 25, 25);
        panel.add(chkIsAgreement);
        chkIsAgreement.setBackground(Color.WHITE);
       
        JLabel lblIsManager = new JLabel("Is Manager");
        lblIsManager.setBounds(10, 365, 100, 20);
        panel.add(lblIsManager);
                                                                                                                
        chkIsManager = new JCheckBox();
        chkIsManager.setBounds(10, 384, 25, 25);
        panel.add(chkIsManager);
        chkIsManager.setBackground(Color.WHITE);
        
        JLabel lblDepartment = new JLabel("Department");
        lblDepartment.setBounds(10, 416, 100, 20);
        panel.add(lblDepartment);
        
        cboDepartment = new JComboBox<>();
        cboDepartment.setBounds(10, 446, 300, 25);
        panel.add(cboDepartment);
        
        JLabel lblJobTitle = new JLabel("Job Title");
        lblJobTitle.setBounds(10, 482, 100, 20);
        panel.add(lblJobTitle);
        
        cboJobTitle = new JComboBox<>();
        cboJobTitle.setBounds(10, 513, 300, 25);
        panel.add(cboJobTitle);
        
        JLabel lblPassword = new JLabel("<html>Password <span style='color:red;'>*</span></html>");
        lblPassword.setBounds(10, 560, 100, 14);
        panel.add(lblPassword);
        
        txtPassword = new JTextField();
        txtPassword.setBounds(10, 580, 300, 25);
        panel.add(txtPassword);
       
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String input = txtPassword.getText();
                String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
                if (!input.matches(passwordRegex)) {
                    txtPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    txtPassword.setBorder(UIManager.getBorder("TextField.border"));
                }
            }
        });
        
        JButton btnSave = new JButton("Save");
        btnSave.setBounds(23, 612, 100, 30);
        panel.add(btnSave);
        
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EmployeeDetailAdminModel pm = new EmployeeDetailAdminModel();
                EmployeeDetailAdminController pc = new EmployeeDetailAdminController();

                if (txtName.getText().trim().isEmpty() ||
                    txtEmail.getText().trim().isEmpty() ||
                    txtPhoneNumber.getText().trim().isEmpty() ||
                    dateChooser.getDate() == null || 
                    cboDepartment.getSelectedItem() == null ||
                    cboJobTitle.getSelectedItem() == null ||
                    txtPassword.getText().trim().isEmpty()) {
                    
                    JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                pm.setEmp_id(employeeId);
                pm.setEmpName(txtName.getText().trim());
                pm.setEmail(txtEmail.getText().trim());
                pm.setPhone(txtPhoneNumber.getText().trim());
                pm.setHiringDate(new java.sql.Date(dateChooser.getDate().getTime()));
                pm.setDep_id((String) cboDepartment.getSelectedItem());
                pm.setPos_id((String) cboJobTitle.getSelectedItem());
                pm.setManager(chkIsManager.isSelected());
                pm.setActive(chkIsActive.isSelected());
                pm.setAgreement(chkIsAgreement.isSelected());
                pm.setPassword(txtPassword.getText().trim());

                if (Checking.IsValidName(pm.getEmpName()) ||
                    !Checking.IsEmailformat(pm.getEmail()) ||
                    !Checking.isPhoneNo(pm.getPhone())) {
                    
                    JOptionPane.showMessageDialog(null, "Invalid related field", "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int rs = pc.insert(pm);
                    if (rs == 1) {
                        JOptionPane.showMessageDialog(null, "Save Successfully", "Successfully", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        populateComboBoxes();
    }
    
    private void populateComboBoxes() {
        try {
            EmployeeDetailAdminController pc = new EmployeeDetailAdminController();
            
            // Clear existing items (if any)
            cboDepartment.removeAllItems();
            cboJobTitle.removeAllItems();
            
            // Add "Select" option
            cboDepartment.addItem("Select Department");
            cboJobTitle.addItem("Select Job Title");
            // Populate departments
            List<String> departments = pc.getDepartments();
            for (String dept : departments) {
                cboDepartment.addItem(dept);
            }
            // Populate job titles
            List<String> jobTitles = pc.getJobTitles();
            for (String job : jobTitles) {
                cboJobTitle.addItem(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching combobox data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private String generateNextId() {
        // Placeholder implementation; replace with actual logic
        String lastId = getLastEmployeeIdFromDatabase();
        if (lastId == null) {
            return "E001";
        } else {
            int idNum = Integer.parseInt(lastId.substring(1)) + 1;
            return String.format("E%03d", idNum);
        }
    }

    private String getLastEmployeeIdFromDatabase() {
        // Mock implementation for example purpose
        return "E003";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("AddEmployeeDetails");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new JScrollPane(new AddEmployeeDetailsView()));
            frame.setSize(1000, 750);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

