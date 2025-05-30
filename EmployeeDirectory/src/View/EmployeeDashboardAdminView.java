package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class EmployeeDashboardAdminView extends JPanel {
    private JTextField textField;
    private JTextField textField_1;
    private AdminNavBar parentFrame;
    
    public EmployeeDashboardAdminView(AdminNavBar parentFrame) {
        this.parentFrame = parentFrame;
        
        JButton detailButton = new JButton("Go to Employee Detail");
        detailButton.setBounds(378, 33, 150, 23);
        detailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.getCardLayout().show(parentFrame.getContentPanel(), "EmployeeDetails");
                parentFrame.setActiveNav("Employee Details");
            }
        });
        
        setLayout(null);
        add(detailButton);
        
        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(67, 106, 69, 14);
        add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBounds(40, 131, 306, 20);
        add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Phone");
        lblNewLabel_1.setBounds(67, 172, 49, 14);
        add(lblNewLabel_1);
        
        textField_1 = new JTextField();
        textField_1.setBounds(40, 193, 306, 20);
        add(textField_1);
        textField_1.setColumns(10);
    }
    
    
}