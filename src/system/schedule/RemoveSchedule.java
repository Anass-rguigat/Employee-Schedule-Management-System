package system.schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class RemoveSchedule extends JFrame implements ActionListener {
    Choice choiceSHPID;
    JComboBox<String> dayComboBox, timeFromComboBox, timeToComboBox, sectorComboBox;
    JButton delete, back;

    RemoveSchedule() {
        JPanel panel = new JPanel() {
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
        panel.setLayout(null);
        getContentPane().add(panel);

        JLabel label = new JLabel("Schedule ID");
        label.setBounds(50, 50, 100, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        label.setForeground(Color.WHITE);
        panel.add(label);

        choiceSHPID = new Choice();
        choiceSHPID.setBounds(200, 50, 150, 30);
        panel.add(choiceSHPID);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from schedule");
            while (resultSet.next()) {
                choiceSHPID.add(resultSet.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel dayLabel = new JLabel("Day");
        dayLabel.setBounds(50,100,100,30);
        dayLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        dayLabel.setForeground(Color.WHITE); // Set text color to white
        panel.add(dayLabel);

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayComboBox = new JComboBox<>(daysOfWeek);
        dayComboBox.setBackground(new Color(255, 255, 255));
        dayComboBox.setBounds(200,100,100,30);
        panel.add(dayComboBox);

        JLabel timeFromLabel = new JLabel("Time from");
        timeFromLabel.setBounds(50,150,100,30);
        timeFromLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        timeFromLabel.setForeground(Color.WHITE); // Set text color to white
        panel.add(timeFromLabel);

        String[] timeFromItems = {"8:30", "10:30", "12:30", "2:30"};
        timeFromComboBox = new JComboBox<>(timeFromItems);
        timeFromComboBox.setBackground(new Color(255, 255, 255));
        timeFromComboBox.setBounds(200,150,100,30);
        panel.add(timeFromComboBox);

        JLabel timeToLabel = new JLabel("Time to");
        timeToLabel.setBounds(50,200,100,30);
        timeToLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        timeToLabel.setForeground(Color.WHITE); // Set text color to white
        panel.add(timeToLabel);

        String[] timeToItems = {"10:00", "12:00", "2:00", "4:00"};
        timeToComboBox = new JComboBox<>(timeToItems);
        timeToComboBox.setBackground(new Color(255, 255, 255));
        timeToComboBox.setBounds(200,200,100,30);
        panel.add(timeToComboBox);

        JLabel sectorLabel = new JLabel("Sector");
        sectorLabel.setBounds(50,250,100,30);
        sectorLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        sectorLabel.setForeground(Color.WHITE);
        panel.add(sectorLabel);

        String[] sectors = {"Sector A", "Sector B", "Sector C", "Sector D"};
        sectorComboBox = new JComboBox<>(sectors);
        sectorComboBox.setBackground(new Color(255, 255, 255));
        sectorComboBox.setBounds(200,250,100,30);
        panel.add(sectorComboBox);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from schedule where id = '" + choiceSHPID.getSelectedItem() + "'");
            if (resultSet.next()) {
                dayComboBox.setSelectedItem(resultSet.getString("day"));
                timeFromComboBox.setSelectedItem(resultSet.getString("time_from"));
                timeToComboBox.setSelectedItem(resultSet.getString("time_to"));
                sectorComboBox.setSelectedItem(resultSet.getString("sector"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        choiceSHPID.addItemListener(e -> {
            try {
                conn c = new conn();
                ResultSet resultSet = c.statement.executeQuery("select * from schedule where id = '" + choiceSHPID.getSelectedItem() + "'");
                if (resultSet.next()) {
                    dayComboBox.setSelectedItem(resultSet.getString("day"));
                    timeFromComboBox.setSelectedItem(resultSet.getString("time_from"));
                    timeToComboBox.setSelectedItem(resultSet.getString("time_to"));
                    sectorComboBox.setSelectedItem(resultSet.getString("sector"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100, 30);
        delete.setBackground(Color.black);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        panel.add(delete);

        back = new JButton("Back");
        back.setBounds(220, 300, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        panel.add(back);

        setSize(400, 400);
        setLocationRelativeTo(null);
        panel.setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            try {
                conn c = new conn();
                String query = "delete from schedule where id = '" + choiceSHPID.getSelectedItem() + "'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Schedule Deleted Successfully");
                setVisible(false);
                new Main_class();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        new RemoveSchedule();
    }
}
