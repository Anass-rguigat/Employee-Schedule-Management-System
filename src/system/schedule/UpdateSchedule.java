package system.schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSchedule extends JFrame implements ActionListener {
    String day, startTime, endTime, sector;
    JComboBox<String> dayComboBox, timeFromComboBox, timeToComboBox, sectorComboBox;
    JButton updateButton, backButton;

    public UpdateSchedule(String day, String startTime, String endTime, String sector) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sector = sector;

        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(0, 0, 0);
                Color color2 = new Color(121, 121, 121);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        gradientPanel.setLayout(null);
        getContentPane().add(gradientPanel);

        JLabel heading = new JLabel("Edit Employee Schedule");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        heading.setForeground(Color.WHITE);
        gradientPanel.add(heading);

        JLabel dayLabel = new JLabel("Day");
        dayLabel.setBounds(280, 200, 150, 30);
        dayLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        dayLabel.setForeground(Color.WHITE);
        gradientPanel.add(dayLabel);

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayComboBox = new JComboBox<>(daysOfWeek);
        dayComboBox.setBackground(Color.WHITE);
        dayComboBox.setBounds(480, 200, 150, 30);
        dayComboBox.setSelectedItem(day);
        gradientPanel.add(dayComboBox);

        JLabel timeFromLabel = new JLabel("Time from");
        timeFromLabel.setBounds(280, 250, 150, 30);
        timeFromLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        timeFromLabel.setForeground(Color.WHITE); // Set text color to white
        gradientPanel.add(timeFromLabel);

        String[] timeFromItems = {"8:30", "10:30", "12:30", "2:30"};
        timeFromComboBox = new JComboBox<>(timeFromItems);
        timeFromComboBox.setBackground(Color.WHITE);
        timeFromComboBox.setBounds(480, 250, 150, 30);
        timeFromComboBox.setSelectedItem(startTime);
        gradientPanel.add(timeFromComboBox);

        JLabel timeToLabel = new JLabel("Time to");
        timeToLabel.setBounds(280, 300, 150, 30);
        timeToLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        timeToLabel.setForeground(Color.WHITE);
        gradientPanel.add(timeToLabel);

        String[] timeToItems = {"10:00", "12:00", "2:00", "4:00"};
        timeToComboBox = new JComboBox<>(timeToItems);
        timeToComboBox.setBackground(Color.WHITE);
        timeToComboBox.setBounds(480, 300, 150, 30);
        timeToComboBox.setSelectedItem(endTime);
        gradientPanel.add(timeToComboBox);

        JLabel sectorLabel = new JLabel("Sector");
        sectorLabel.setBounds(280, 350, 150, 30);
        sectorLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        sectorLabel.setForeground(Color.WHITE);
        gradientPanel.add(sectorLabel);

        String[] sectors = {"Sector A", "Sector B", "Sector C", "Sector D"};
        sectorComboBox = new JComboBox<>(sectors);
        sectorComboBox.setBackground(Color.WHITE);
        sectorComboBox.setBounds(480, 350, 150, 30);
        sectorComboBox.setSelectedItem(sector);
        gradientPanel.add(sectorComboBox);

        updateButton = new JButton("UPDATE");
        updateButton.setBounds(450, 550, 150, 40);
        updateButton.setBackground(Color.BLACK);
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(this);
        gradientPanel.add(updateButton);

        backButton = new JButton("BACK");
        backButton.setBounds(250, 550, 150, 40);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        gradientPanel.add(backButton);

        setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String newDay = (String) dayComboBox.getSelectedItem();
            String newStartTime = (String) timeFromComboBox.getSelectedItem();
            String newEndTime = (String) timeToComboBox.getSelectedItem();
            String newSector = (String) sectorComboBox.getSelectedItem();

            try {
                conn c = new conn();
                String query = "update schedule set day = '" + newDay + "', time_from = '" + newStartTime + "', time_to = '" + newEndTime + "', sector = '" + newSector + "' where day = '" + day + "'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Schedule updated successfully");
                setVisible(false);
                new ViewSchedule();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating schedule", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
            new ViewSchedule();
        }
    }

    public static void main(String[] args) {
        new UpdateSchedule("", "", "", "");
    }
}
