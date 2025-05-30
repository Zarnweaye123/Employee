package View;

import javax.swing.*;

import Controller.AdminDetailController;
import Controller.EmployeeDetailsController;
import Model.EmployeeDetailsModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class EmployeeDetailsAdminView extends JPanel {
	 
    private  final long serialVersionUID = 1L;
    private  JTextField txtName;
    private  JTextField txtEmail;
    private  JTextField txtPhone;
    private  JTextField txtHiringDate;
    private  JTextField txtDepartment;
    private  JTextField txtJobTitle;
    private AdminNavBar parentFrame;
    private EmployeeDetailsModel currentModel;
    private JCheckBox chkIsActive, chkIsAgreement, chkIsManager;

	public EmployeeDetailsAdminView(AdminNavBar parentFrame) {
		 this.parentFrame = parentFrame;
	        setLayout(null);
	//	setLayout(null);
 
		JLabel lblHeader = new JLabel("Employee Details");
		lblHeader.setBounds(38, 10, 300, 30);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblHeader);
 
		setPreferredSize(new Dimension(669, 686));
		setBackground(new Color(245, 245, 245));
 
		JPanel panel = new JPanel();
		panel.setBounds(58, 43, 567, 594);
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
		txtEmail.setBounds(31, 165, 508, 25);
		panel.add(txtEmail);
 
		JLabel lbHiringDate = new JLabel("Hiring Date");
		lbHiringDate.setFont(new Font("Arial", Font.PLAIN, 14));
		lbHiringDate.setBounds(31, 200, 100, 20);
		panel.add(lbHiringDate);
 
		txtHiringDate = new JTextField();
		txtHiringDate.setBounds(31, 230, 508, 25);
		panel.add(txtHiringDate);
 
		JCheckBox chkIsActive = new JCheckBox();
		chkIsActive.setFont(new Font("Arial", Font.PLAIN, 14));
		chkIsActive.setBounds(31, 341, 25, 25);
		panel.add(chkIsActive);
		chkIsActive.setBackground(Color.WHITE);
 
		JCheckBox chkIsAgreement = new JCheckBox();
		chkIsAgreement.setBounds(31, 398, 25, 25);
		panel.add(chkIsAgreement);
		chkIsAgreement.setBackground(Color.WHITE);
 
		JCheckBox chkIsManager = new JCheckBox();
		chkIsManager.setFont(new Font("Arial", Font.PLAIN, 14));
		chkIsManager.setBounds(31, 291, 25, 25);
		panel.add(chkIsManager);
		chkIsManager.setBackground(Color.WHITE);
 
		JLabel lbDepartment = new JLabel("Department");
		lbDepartment.setFont(new Font("Arial", Font.PLAIN, 14));
		lbDepartment.setBounds(31, 429, 100, 20);
		panel.add(lbDepartment);
 
		txtDepartment = new JTextField();
		txtDepartment.setFont(new Font("Arial", Font.PLAIN, 14));
		txtDepartment.setBounds(31, 459, 508, 25);
		panel.add(txtDepartment);
 
		JLabel lbJobTitle = new JLabel("Job Title");
		lbJobTitle.setFont(new Font("Arial", Font.PLAIN, 14));
		lbJobTitle.setBounds(31, 494, 100, 20);
		panel.add(lbJobTitle);
 
		txtJobTitle = new JTextField();
		txtJobTitle.setBounds(31, 518, 508, 25);
		panel.add(txtJobTitle);
 
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(Color.RED);
		btnSave.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSave.setBounds(31, 563, 73, 24);
		panel.add(btnSave);
 
		JLabel lbIsManager = new JLabel("Is Manager");
		lbIsManager.setFont(new Font("Arial", Font.PLAIN, 14));
		lbIsManager.setBounds(31, 265, 100, 20);
		panel.add(lbIsManager);
 
		JLabel lbIsActive = new JLabel("Is Active");
		lbIsActive.setFont(new Font("Arial", Font.PLAIN, 14));
		lbIsActive.setBounds(31, 322, 73, 13);
		panel.add(lbIsActive);
 
		JLabel lbIsAgreement = new JLabel("Is Agreement");
		lbIsAgreement.setFont(new Font("Arial", Font.PLAIN, 14));
		lbIsAgreement.setBounds(31, 372, 100, 20);
		panel.add(lbIsAgreement);
		
		 JButton btnBack = new JButton("Back");
		 btnBack.setBackground(Color.RED);
	        btnBack.setFont(new Font("Arial", Font.PLAIN, 14));
	        btnBack.setBounds(454, 560, 85, 27);
	        btnBack.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                parentFrame.getCardLayout().show(parentFrame.getContentPanel(), "EmployeeDashboard");
	                parentFrame.setActiveNav("Employee Dashboard");
	            }
	        });
	        panel.add(btnBack);
	}
	
	
	 private void saveEmployeeDetails() {
	        if (txtName.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPhone.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Required fields missing.");
	            return;
	        }

	        if (currentModel == null) {
	            JOptionPane.showMessageDialog(this, "No employee loaded to save.");
	            return;
	        }

	        currentModel.setName(txtName.getText());
	        currentModel.setEmail(txtEmail.getText());
	        currentModel.setPhone(txtPhone.getText());
	        currentModel.setHiringDate(txtHiringDate.getText());
	        currentModel.setActive(chkIsActive.isSelected());
	        currentModel.setAgreement(chkIsAgreement.isSelected());
	        currentModel.setManager(chkIsManager.isSelected());
	        currentModel.setDepartment(txtDepartment.getText());
	        currentModel.setJobTitle(txtJobTitle.getText());

	        EmployeeDetailsController controller = new EmployeeDetailsController(currentModel);
	        boolean saved = controller.saveAdminDetails();


	        JOptionPane.showMessageDialog(this, saved ? "Saved successfully!" : "Save failed.");
	    }

	    public void loadAdminDetail(String name) {
	        EmployeeDetailsController controller = new EmployeeDetailsController (null);
	        EmployeeDetailsModel model = controller.fetchAdminByName(name);
	        if (model != null) {
	            currentModel = model;
	            txtName.setText(model.getName());
	            txtEmail.setText(model.getEmail());
	            txtPhone.setText(model.getPhone());
	            txtHiringDate.setText(model.getHiringDate());
	            chkIsActive.setSelected(model.isActive());
	            chkIsAgreement.setSelected(model.isAgreement());
	            chkIsManager.setSelected(model.isManager());
	            txtDepartment.setText(model.getDepartment());
	            txtJobTitle.setText(model.getJobTitle());
	        } else {
	            JOptionPane.showMessageDialog(this, "Employee not found!");
	        }
	    }
	
	
	}
 
	//public static void main(String[] args) {
		//SwingUtilities.invokeLater(() -> {
		//	JFrame frame = new JFrame("EmployeeDetailsAdminView");
		//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//	frame.setContentPane(new JScrollPane(new EmployeeDetailsAdminView()));
		//	frame.setSize(1000, 750);
		//	frame.setLocationRelativeTo(null);
		////	frame.setVisible(true);
		//});
	//}

