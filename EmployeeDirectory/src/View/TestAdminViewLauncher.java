package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class TestAdminViewLauncher {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String name;
            while ((name = reader.readLine()) != null) {
                final String employeeName = name; // ✅ Final copy for correct MouseListener behavior
                JLabel label = new JLabel("<html><a href=''>" + employeeName + "</a></html>");
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
                label.setForeground(Color.BLUE);
                listPanel.add(label);

                label.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        JFrame detailFrame = new JFrame("Admin Details");
                        AdminDetailView detailView = new AdminDetailView();
                        detailView.loadAdminDetail(employeeName);
                        detailFrame.setContentPane(detailView);
                        detailFrame.setSize(600, 700);
                        detailFrame.setLocationRelativeTo(null);
                        detailFrame.setVisible(true);
                    }
                });
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading employee list: " + e.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
