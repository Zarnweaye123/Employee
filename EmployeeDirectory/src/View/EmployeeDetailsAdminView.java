package View;

import javax.swing.*;
import java.awt.*;

public class EmployeeDetailsAdminView extends JPanel {
	 
    private static final long serialVersionUID = 1L;
    private static JTextField txtName;
    private static JTextField txtEmail;
    private static JTextField txtPhone;
    private static JTextField txtHiringDate;
    private static JTextField txtDepartment;
    private static JTextField txtJobTitle;
 
	public EmployeeDetailsAdminView() {
		setLayout(null);
 
		JLabel lblHeader = new JLabel("Employee Details");
		lblHeader.setBounds(38, 10, 300, 30);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 22));
		add(lblHeader);
 
		setPreferredSize(new Dimension(669, 692));
		setBackground(new Color(245, 245, 245));
 
		JPanel panel = new JPanel();
		panel.setBounds(58, 53, 567, 629);
		add(panel);
		panel.setLayout(null);
 
		JLabel lbName = new JLabel("Name");
		lbName.setFont(new Font("Arial", Font.PLAIN, 14));
		lbName.setBounds(31, 10, 73, 20);
		panel.add(lbName);
 
		txtName = new JTextField();
		txtName.setBounds(31, 35, 508, 25);
		panel.add(txtName);
 
		JLabel lbPhone = new JLabel("Phone Number");
		lbPhone.setFont(new Font("Arial", Font.PLAIN, 14));
		lbPhone.setBounds(31, 70, 100, 20);
		panel.add(lbPhone);
 
		txtPhone = new JTextField();
		txtPhone.setBounds(31, 100, 508, 25);
		panel.add(txtPhone);
 
		JLabel lbEmail = new JLabel("Email");
		lbEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		lbEmail.setBounds(31, 135, 73, 20);
		panel.add(lbEmail);
 
		txtEmail = new JTextField();
		txtEmail.setBounds(31, 170, 508, 25);
		panel.add(txtEmail);
 
		JLabel lbHiringDate = new JLabel("Hiring Date");
		lbHiringDate.setFont(new Font("Arial", Font.PLAIN, 14));
		lbHiringDate.setBounds(31, 205, 100, 20);
		panel.add(lbHiringDate);
 
		txtHiringDate = new JTextField();
		txtHiringDate.setBounds(31, 235, 508, 25);
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
 
		JLabel lbDepartment = new JLabel("Department");
		lbDepartment.setFont(new Font("Arial", Font.PLAIN, 14));
		lbDepartment.setBounds(31, 439, 100, 20);
		panel.add(lbDepartment);
 
		txtDepartment = new JTextField();
		txtDepartment.setFont(new Font("Arial", Font.PLAIN, 14));
		txtDepartment.setBounds(31, 469, 508, 25);
		panel.add(txtDepartment);
 
		JLabel lbJobTitle = new JLabel("Job Title");
		lbJobTitle.setFont(new Font("Arial", Font.PLAIN, 14));
		lbJobTitle.setBounds(31, 504, 100, 20);
		panel.add(lbJobTitle);
 
		txtJobTitle = new JTextField();
		txtJobTitle.setBounds(31, 534, 508, 25);
		panel.add(txtJobTitle);
 
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(Color.RED);
		btnSave.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSave.setBounds(31, 577, 85, 30);
		panel.add(btnSave);
 
		JLabel lbIsManager = new JLabel("Is Manager");
		lbIsManager.setFont(new Font("Arial", Font.PLAIN, 14));
		lbIsManager.setBounds(31, 275, 100, 20);
		panel.add(lbIsManager);
 
		JLabel lbIsActive = new JLabel("Is Active");
		lbIsActive.setFont(new Font("Arial", Font.PLAIN, 14));
		lbIsActive.setBounds(31, 332, 73, 13);
		panel.add(lbIsActive);
 
		JLabel lbIsAgreement = new JLabel("Is Agreement");
		lbIsAgreement.setFont(new Font("Arial", Font.PLAIN, 14));
		lbIsAgreement.setBounds(31, 382, 100, 20);
		panel.add(lbIsAgreement);
	}
 
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("EmployeeDetailsAdminView");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setContentPane(new JScrollPane(new EmployeeDetailsAdminView()));
			frame.setSize(1000, 750);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}
