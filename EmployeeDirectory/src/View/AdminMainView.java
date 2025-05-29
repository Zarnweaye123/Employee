package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import View.EmployeeDetailsAdminView;

public class AdminMainView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private CardLayout cardlayout;
	private Map<String, JPanel> panels = new LinkedHashMap<>();
	private Map<String, JLabel> navLabels = new LinkedHashMap<>();
	private JPanel mainPanel;
	 
	private JLabel activeLabel = null;
	
	private Color normalColor = Color.WHITE;
	private Color hoverColor = Color.BLACK;
	private Color activeColor = new Color(34,139,34);
	 
	public AdminMainView() {
		setTitle("Employee Deo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
			 
		JPanel mainPanel = new JPanel(new BorderLayout());   
		JPanel menuBar = createMenuBar();   
		mainPanel.add(menuBar, BorderLayout.NORTH);   
		cardlayout = new CardLayout();
		contentPanel = new JPanel(cardlayout);
		createPanels();
		
		// Add the panels to contentPanel with their names as keys
		for (Map.Entry<String, JPanel> entry : panels.entrySet()) {
			contentPanel.add(entry.getValue(), entry.getKey());
		}
		
		cardlayout.show(contentPanel, "Employee Dashboard");
		setActiveNav("Employee Dashboard");
		mainPanel.add(contentPanel, BorderLayout.CENTER);  
		
		// Add mainPanel to the content pane with BorderLayout
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}
	
	private JPanel createMenuBar() {
		JPanel menuBar = new JPanel(new BorderLayout());
		menuBar.setBackground(new Color(64, 224, 208));  
		menuBar.setPreferredSize(new Dimension(getWidth(), 50));
		menuBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

		JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, -5));
		leftPanel.setOpaque(false);

		try {
			ImageIcon homeIcon = new ImageIcon("C:\\OJTProject\\EmployeeDirectotry\\image\\home.png");

			Image img = homeIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		JLabel iconLabel = new JLabel(new ImageIcon(img));
		iconLabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
		leftPanel.add(iconLabel);
		}catch(Exception e) {
			System.out.println("File not found");
		} 
		
		JLabel systemName = new JLabel("Employee Directory");
		systemName.setFont(new Font("Arial", Font.BOLD, 16));
		systemName.setForeground(Color.WHITE);
		leftPanel.add(systemName);

		JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 8));  
		 
		
		navPanel.setOpaque(false);

		 
		
		String[] menuItems = {
			"Employee Dashboard",
			"Employee Details",
			"Admin Dashboard",
			"Add Admin"
		};

		for (String item : menuItems) {
			JLabel navLabel = createNavLabel(item);
			navLabels.put(item, navLabel);
			navPanel.add(navLabel);
		}

		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		rightPanel.setOpaque(false);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int confirm = JOptionPane.showConfirmDialog(null, "Do You want to Logout?","Logout",JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION) {
					dispose();
					System.exit(0);
				}
				
				
			}
		});
		btnLogout.setFocusPainted(false);
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBackground(new Color(220, 53, 69));   
		btnLogout.setFont(new Font("Arial", Font.BOLD, 16));
		btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogout.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

		rightPanel.add(btnLogout);

		menuBar.add(leftPanel, BorderLayout.WEST);
		menuBar.add(navPanel, BorderLayout.CENTER);   
		menuBar.add(rightPanel, BorderLayout.EAST);   

		return menuBar;
	}

	private JLabel createNavLabel(String name) {
		JLabel label = new JLabel(name);
		label.setFont(new Font("Arial", Font.BOLD, 14));
		label.setForeground(normalColor);
		label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(label != activeLabel) {
						label.setForeground(hoverColor);
			}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(label != activeLabel) {
					label.setForeground(normalColor);
			}
			}
 			@Override
			public void mouseClicked(MouseEvent e) {
				setActiveNav(name);
				cardlayout.show(contentPanel, name.replace("",""));
				
			}
		});		


		return label;
	}

	private void setActiveNav(String name) {
		if (activeLabel != null) {
			activeLabel.setForeground(normalColor);   
		}
		activeLabel = navLabels.get(name);
		if (activeLabel != null) {
			activeLabel.setForeground(Color.GREEN);   
		}
	}

	private void createPanels() {
	    panels.put("Employee Dashboard", panelWithLabel("Employee Dashboard"));

	    // Create an instance of EmployeeDetailsAdminView and call createEmpDetail() on it
	   // EmployeeDetailsAdminView empDetailsView = new EmployeeDetailsAdminView();
	   // panels.put("Employee Details", empDetailsView.createEmpDetail());
	    EmployeeDetailsAdminView empDetailsPanel = new EmployeeDetailsAdminView();
	    panels.put("Employee Details", empDetailsPanel);

	    panels.put("Admin Dashboard", panelWithLabel("Admin Dashboard"));
	    panels.put("Add Admin", panelWithLabel("Add Admin"));
	}

	private JPanel panelWithLabel(String text) {
		JPanel panel = new JPanel();
		panel.add(new JLabel(text));
		return panel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AdminMainView frame = new AdminMainView();
				frame.setVisible(true); 
			}
		});
	}
}
