package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmpDashboardAdminView extends JFrame {

	private JPanel contentPanel;
	private CardLayout cardLayout;
	private Map<String, JPanel> panels = new LinkedHashMap<>();
	private Map<String, JLabel> navLabels = new LinkedHashMap<>();
	private Color normalColor = Color.WHITE;
	private Color hoverColor = new Color(0, 0, 0); // Black
	private Color activeColor = new Color(34, 139, 34); // Forest Green

	private JLabel activeLabel = null;
	private JTextField textField;

	public EmpDashboardAdminView() {
		setTitle("Employee Directory");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(995, 700);
		setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel menuBar = createMenuBar();
		mainPanel.add(menuBar, BorderLayout.NORTH);

		cardLayout = new CardLayout();
		contentPanel = new JPanel(cardLayout);
		contentPanel.setBackground(Color.WHITE);
		createPanels();

		for (Map.Entry<String, JPanel> entry : panels.entrySet()) {
			contentPanel.add(entry.getValue(), entry.getKey());
		}

		cardLayout.show(contentPanel, "EmployeeDashboard");
		setActiveNav("Employee Dashboard");

		mainPanel.add(contentPanel, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPanel.add(panel, "name_3002885783000");
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(142, 83, 456, 32);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(625, 88, 85, 21);
		panel.add(btnNewButton);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(Color.RED);
		btnAdd.setBounds(738, 88, 85, 21);
		panel.add(btnAdd);
		getContentPane().add(mainPanel);
	}

	private JPanel createMenuBar() {
		JPanel menuBar = new JPanel(new BorderLayout());
		menuBar.setBackground(new Color(230, 230, 250));
		menuBar.setPreferredSize(new Dimension(getWidth(), 50));
		menuBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

		// Left: logo + system name
		JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		leftPanel.setOpaque(false);

		JLabel menuIcon = new JLabel("\u2630");
		menuIcon.setFont(new Font("Arial", Font.PLAIN, 20));
		menuIcon.setForeground(Color.WHITE);

		JLabel systemName = new JLabel("Employee Directory");
		systemName.setFont(new Font("Arial", Font.BOLD, 16));
		systemName.setForeground(Color.WHITE);

		leftPanel.add(menuIcon);
		leftPanel.add(systemName);

		// Center: navigation labels
		JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
		navPanel.setOpaque(false);

		String[] menuItems = { "Employee Dashboard", "Employee Detail", "Admin Dashboard", "Add Admin" };

		for (String item : menuItems) {
			JLabel navLabel = createNavLabel(item);
			navLabels.put(item, navLabel);
			navPanel.add(navLabel);
		}

		// Right: logout button
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		rightPanel.setOpaque(false);

		JButton logoutButton = new JButton("Logout");
		logoutButton.setFocusPainted(false);
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setBackground(new Color(220, 53, 69)); // Bootstrap Danger color
		logoutButton.setFont(new Font("Arial", Font.PLAIN, 14));
		logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		logoutButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

		// Button hover effect
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				logoutButton.setBackground(new Color(200, 35, 50));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				logoutButton.setBackground(new Color(220, 53, 69));
			}
		});

		logoutButton.addActionListener(e -> {
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				dispose(); // or redirect to login form
				System.exit(0);
			}
		});

		rightPanel.add(logoutButton);

		menuBar.add(leftPanel, BorderLayout.WEST);
		menuBar.add(navPanel, BorderLayout.CENTER);
		menuBar.add(rightPanel, BorderLayout.EAST);

		return menuBar;
	}

	private JLabel createNavLabel(String name) {
		JLabel label = new JLabel(name);
		label.setFont(new Font("Arial", Font.PLAIN, 14));
		label.setForeground(normalColor);
		label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		label.setOpaque(false);
		label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (label != activeLabel) {
					label.setForeground(hoverColor);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (label != activeLabel) {
					label.setForeground(normalColor);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setActiveNav(name);
				cardLayout.show(contentPanel, name.replace(" ", ""));
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
			activeLabel.setForeground(activeColor);
		}
	}

	private void createPanels() {
		panels.put("EmployeeDashboard", createEmployeeDashboard());
		panels.put("EmployeeDetail", panelWithLabel("Employee Detail"));
		panels.put("AdminDashboard", panelWithLabel("Admin Dashboard"));
		panels.put("AddAdmin", panelWithLabel("Add Admin"));
	}

	private JPanel panelWithLabel(String text) {
		JPanel panel = new JPanel();
		panel.add(new JLabel(text));
		return panel;
	}

	private JPanel createEmployeeDashboard() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);

		// Top panel: Search field + buttons
		JPanel topPanel = new JPanel(null); // Absolute positioning
		topPanel.setPreferredSize(new Dimension(995, 60));
		topPanel.setBackground(Color.WHITE);

		textField = new JTextField();
		textField.setBounds(142, 15, 456, 32);
		topPanel.add(textField);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(625, 20, 85, 21);
		topPanel.add(btnNewButton);

		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(Color.RED);
		btnAdd.setBounds(738, 20, 85, 21);
		topPanel.add(btnAdd);

		// Employee Grid Panel
		JPanel gridPanel = new JPanel(new GridLayout(0, 1, 20, 10)); // 1 column
		gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		gridPanel.setBackground(Color.WHITE);

		// Scroll Pane for grid
		JScrollPane scrollPane = new JScrollPane(gridPanel);
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		// Sample employee data
		String[][] employees = { { "John Doe", "HR" }, { "Jane Smith", "IT" }, { "Alice Johnson", "Finance" },
				{ "Bob Lee", "Marketing" }, { "Emily Davis", "Operations" } };

		for (String[] emp : employees) {
			JPanel empPanel = new JPanel(new BorderLayout());
			empPanel.setBackground(Color.WHITE);
			empPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			empPanel.setPreferredSize(new Dimension(300, 60));

			JPanel topRow = new JPanel(new BorderLayout());
			topRow.setBackground(Color.WHITE);

			JLabel nameLabel = new JLabel("<html><a href=''>" + emp[0] + "</a></html>");
			nameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			nameLabel.setForeground(Color.BLUE);
			nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
			nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));

			nameLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setActiveNav("Employee Detail");
					cardLayout.show(contentPanel, "EmployeeDetail");

					JPanel detailPanel = panels.get("EmployeeDetail");
					detailPanel.removeAll();
					detailPanel.add(new JLabel("Details for: " + emp[0]));
					detailPanel.revalidate();
					detailPanel.repaint();
				}
			});

			// Load icon image safely
			ImageIcon deleteIcon = null;
			try {
				deleteIcon = new ImageIcon(getClass().getResource("D:/AdvanceJava/EmployeeDirectory/delete.png"));
			} catch (Exception ex) {
				System.err.println("Delete icon not found");
			}

			JButton deleteBtn = new JButton();
			if (deleteIcon != null) {
				Image scaledImage = deleteIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
				deleteBtn.setIcon(new ImageIcon(scaledImage));
			} else {
				deleteBtn.setText("X");
			}

			deleteBtn.setContentAreaFilled(false);
			deleteBtn.setBorderPainted(false);
			deleteBtn.setFocusPainted(false);
			deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			deleteBtn.setToolTipText("Delete");

			deleteBtn.addActionListener(e -> {
				int confirm = JOptionPane.showConfirmDialog(null, "Delete " + emp[0] + "?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					gridPanel.remove(empPanel);
					gridPanel.revalidate();
					gridPanel.repaint();
				}
			});

			topRow.add(nameLabel, BorderLayout.WEST);
			topRow.add(deleteBtn, BorderLayout.EAST);

			JLabel deptLabel = new JLabel(emp[1]);
			deptLabel.setFont(new Font("Arial", Font.PLAIN, 14));
			deptLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));

			empPanel.add(topRow, BorderLayout.CENTER);
			gridPanel.add(empPanel);
			gridPanel.add(deptLabel);
		}

		JScrollPane scrollPane1 = new JScrollPane(gridPanel);
		scrollPane1.setBorder(null);

		// Combine top and scrollable grid into main panel
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(scrollPane1, BorderLayout.CENTER);

		return mainPanel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			EmpDashboardAdminView view = new EmpDashboardAdminView();
			view.setVisible(true);
		});
	}
}
