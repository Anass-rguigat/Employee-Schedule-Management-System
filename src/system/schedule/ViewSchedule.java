package system.schedule;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewSchedule extends JFrame implements ActionListener {
    JTable table;
    JButton print, back;
    Choice choiceSCH;

    public ViewSchedule() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(0, 0, 0);
                Color color2 = new Color(121, 121, 121);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, w, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        panel.setLayout(null);
        getContentPane().add(panel);

        choiceSCH = new Choice();

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from schedule");

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("Day");
            model.addColumn("Start Time");
            model.addColumn("End Time");
            model.addColumn("Sector");

            while (resultSet.next()) {
                String day = resultSet.getString("day");
                String startTime = resultSet.getString("time_from");
                String endTime = resultSet.getString("time_to");
                String sector = resultSet.getString("sector");
                model.addRow(new Object[]{day, startTime, endTime, sector});
            }

            table = new JTable(model);
            table.setFillsViewportHeight(true);

            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String day = (String) table.getValueAt(selectedRow, 0);
                            String startTime = (String) table.getValueAt(selectedRow, 1);
                            String endTime = (String) table.getValueAt(selectedRow, 2);
                            String sector = (String) table.getValueAt(selectedRow, 3);
                            choiceSCH.select(day);
                            new UpdateSchedule(day, startTime, endTime, sector);
                        }
                    }
                }
            });

            JScrollPane jp = new JScrollPane(table);
            jp.setBounds(0, 100, 900, 600);
            panel.add(jp);

            print = new JButton("Print");
            print.setBounds(120, 70, 80, 20);
            print.addActionListener(this);
            panel.add(print);

            back = new JButton("Back");
            back.setBounds(220, 70, 80, 20);
            back.addActionListener(this);
            panel.add(back);

        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == print) {
            try {
                boolean complete = table.print();
                if (complete) {
                    JOptionPane.showMessageDialog(this, "Printing successful", "Print", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Printing canceled or failed", "Print", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception E) {
                E.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error while printing", "Print", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            new Main_class();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ViewSchedule();
    }
}
