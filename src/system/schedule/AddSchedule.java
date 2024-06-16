package system.schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSchedule extends JFrame implements ActionListener {
    JComboBox<String> dayComboBox, timeFromComboBox, timeToComboBox, sectorComboBox;
    JButton add, back;

    AddSchedule() {
        setTitle("Add Schedule Detail");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        JLabel heading = new JLabel("Add Schedule Detail");
        heading.setBounds(350, 120, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        heading.setForeground(Color.WHITE);
        panel.add(heading);

        JLabel dayLabel = new JLabel("Day");
        dayLabel.setBounds(280, 200, 150, 30);
        dayLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        dayLabel.setForeground(Color.WHITE);
        panel.add(dayLabel);

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayComboBox = new JComboBox<>(daysOfWeek);
        dayComboBox.setBackground(new Color(255, 255, 255));
        dayComboBox.setBounds(480, 200, 150, 30);
        panel.add(dayComboBox);

        JLabel timeFromLabel = new JLabel("Time from");
        timeFromLabel.setBounds(280, 250, 150, 30);
        timeFromLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        timeFromLabel.setForeground(Color.WHITE);
        panel.add(timeFromLabel);

        String[] timeFromItems = {"8:30", "10:30", "12:30", "2:30"};
        timeFromComboBox = new JComboBox<>(timeFromItems);
        timeFromComboBox.setBackground(new Color(255, 255, 255));
        timeFromComboBox.setBounds(480, 250, 150, 30);
        panel.add(timeFromComboBox);

        JLabel timeToLabel = new JLabel("Time to");
        timeToLabel.setBounds(280, 300, 150, 30);
        timeToLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        timeToLabel.setForeground(Color.WHITE);
        panel.add(timeToLabel);

        String[] timeToItems = {"10:00", "12:00", "2:00", "4:00"};
        timeToComboBox = new JComboBox<>(timeToItems);
        timeToComboBox.setBackground(new Color(255, 255, 255));
        timeToComboBox.setBounds(480, 300, 150, 30);
        panel.add(timeToComboBox);

        JLabel sectorLabel = new JLabel("Sector");
        sectorLabel.setBounds(280, 350, 150, 30);
        sectorLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        sectorLabel.setForeground(Color.WHITE);
        panel.add(sectorLabel);

        String[] sectors = {"Sector A", "Sector B", "Sector C", "Sector D"};
        sectorComboBox = new JComboBox<>(sectors);
        sectorComboBox.setBackground(new Color(255, 255, 255));
        sectorComboBox.setBounds(480, 350, 150, 30);
        panel.add(sectorComboBox);

        add = new JButton("ADD");
        add.setBounds(490, 450, 150, 40);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        panel.add(add);

        back = new JButton("BACK");
        back.setBounds(290, 450, 150, 40);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        panel.add(back);

        setContentPane(panel);


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 900;
        int height = 700;
        int x = (dim.width - width) / 2;
        int y = (dim.height - height) / 2;

        setSize(width, height);
        setLocation(x, y);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String selectedDay = (String) dayComboBox.getSelectedItem();
            String selectedTimeFrom = (String) timeFromComboBox.getSelectedItem();
            String selectedTimeTo = (String) timeToComboBox.getSelectedItem();
            String selectedSector = (String) sectorComboBox.getSelectedItem();

            try {
                conn c = new conn();
                String query = "INSERT INTO schedule (day, time_from, time_to, sector) VALUES ('" + selectedDay + "', '" + selectedTimeFrom + "', '" + selectedTimeTo + "', '" + selectedSector + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully");
                setVisible(false);
                new Main_class();

            } catch (Exception E) {
                E.printStackTrace();
            }

        } else if (e.getSource() == back) {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        new AddSchedule();
    }
}
