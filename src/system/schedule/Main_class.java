package system.schedule;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
public class Main_class extends JFrame {
    Main_class(){

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120,630,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0,0,1120,630);
        add(img);

        JLabel heading = new JLabel("Time Management System");
        heading.setBounds(410,155,400,40);
        heading.setFont(new Font("Raleway",Font.BOLD,25));
        img.add(heading);

        JButton add = new JButton("Add  New Schedule");
        add.setBounds(380,270,150,40);
        add.setForeground(Color.WHITE);
        add.setBackground(Color.black);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddSchedule();
                setVisible(false);
            }
        });
        img.add(add);

        JButton view = new JButton("View Schedule");
        view.setBounds(590,270,150,40);
        view.setForeground(Color.WHITE);
        view.setBackground(Color.black);
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewSchedule();
                setVisible(false);
            }
        });
        img.add(view);

        JButton rem = new JButton("Remove Schedule");
        rem.setBounds(480,370,150,40);
        rem.setForeground(Color.WHITE);
        rem.setBackground(Color.black);
        rem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveSchedule();
                setVisible(false);
            }
        });
        img.add(rem);

        setSize(1120,630);
        setLocation(250,100);
        setLayout(null);
        setVisible(true);

    }
    public static void main(String[] args) {
        new Main_class();
    }
}

