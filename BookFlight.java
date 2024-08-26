package airlinemanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class BookFlight extends JFrame implements ActionListener {

    JTextField tfadhar;
    JLabel tfnationality, tfaddress, tfname, labelgender, lblfcode, labelfname;
    JButton bookflight, fetchButton, flight;
    Choice source, destination;
    JDateChooser dcdate;

    public BookFlight() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel lbladhar = new JLabel("Aadhar Number");
        lbladhar.setBounds(60, 80, 150, 25);
        lbladhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladhar);

        tfadhar = new JTextField();
        tfadhar.setBounds(220, 80, 150, 25);
        add(tfadhar);

        fetchButton = new JButton("Fetch User");
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.setBackground(Color.black);
        fetchButton.setForeground(Color.white);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);

        tfname = new JLabel();
        tfname.setBounds(220, 130, 150, 25);
        add(tfname);

        JLabel lbltfnationality = new JLabel("Nationality");
        lbltfnationality.setBounds(60, 180, 150, 25);
        lbltfnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbltfnationality);

        tfnationality = new JLabel();
        tfnationality.setBounds(220, 180, 150, 25);
        add(tfnationality);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);

        tfaddress = new JLabel();
        tfaddress.setBounds(220, 230, 150, 25);
        add(tfaddress);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);

        labelgender = new JLabel();
        labelgender.setBounds(220, 280, 150, 25);
        add(labelgender);

        JLabel lblsource = new JLabel("Source");
        lblsource.setBounds(60, 330, 150, 25);
        lblsource.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblsource);

        source = new Choice();
        source.setBounds(220, 330, 150, 25);
        add(source);

        JLabel lbldest = new JLabel("Destination");
        lbldest.setBounds(60, 380, 150, 25);
        lbldest.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldest);

        destination = new Choice();
        destination.setBounds(220, 380, 150, 25);
        add(destination);

        try {
            Conn c = new Conn();
            String query = "select * from flight";
            ResultSet rs = c.s.executeQuery(query);

            while (rs.next()) {
                source.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        flight = new JButton("Fetch Flight");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(380, 380, 120, 25);
        flight.addActionListener(this);
        add(flight);

        JLabel lblfnameLabel = new JLabel("Flight Name");
        lblfnameLabel.setBounds(60, 430, 150, 25);
        lblfnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfnameLabel);

        labelfname = new JLabel();
        labelfname.setBounds(220, 430, 150, 25);
        add(labelfname);

        JLabel lblfcodeLabel = new JLabel("Flight Code");
        lblfcodeLabel.setBounds(60, 480, 150, 25);
        lblfcodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfcodeLabel);

        lblfcode = new JLabel();
        lblfcode.setBounds(220, 480, 150, 25);
        add(lblfcode);

        JLabel lbldate = new JLabel("Date Of Travel");
        lbldate.setBounds(60, 530, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldate);

        dcdate = new JDateChooser();
        dcdate.setBounds(220, 530, 150, 25);
        add(dcdate);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagement/icons/details.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(550, 80, 500, 410);
        add(lblimage);

        bookflight = new JButton("Book Flight");
        bookflight.setBackground(Color.BLACK);
        bookflight.setForeground(Color.WHITE);
        bookflight.setBounds(220, 580, 150, 25);
        bookflight.addActionListener(this);
        add(bookflight);

        setSize(1100, 700);
        setLocation(200, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String adhar = tfadhar.getText();

            try {
                Conn conn = new Conn();
                String query = "select * from passenger where aadhar= '" + adhar + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    tfname.setText(rs.getString("name"));
                    tfnationality.setText(rs.getString("nationality"));
                    tfaddress.setText(rs.getString("Address"));
                    labelgender.setText(rs.getString("gender"));

                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct aadhar Number");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == flight) {
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();

            try {
                Conn conn = new Conn();
                String query = "select * from flight where source= '" + src + "' and destination = '" + dest + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    labelfname.setText(rs.getString("f_name"));
                    lblfcode.setText(rs.getString("f_code"));

                } else {
                    JOptionPane.showMessageDialog(null, "No flight Found");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == bookflight) {
            Random random = new Random();
            String name = tfname.getText();
            String aadhar = tfadhar.getText();
            String nationality = tfnationality.getText();
            String flightname = labelfname.getText();
            String flightcode = lblfcode.getText();
            String src = source.getSelectedItem();
            String des = destination.getSelectedItem();
            String ddate = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();

            try {
                Conn conn = new Conn();
                String query = "insert into reservation values('PNR-" + random.nextInt(1000000) + "','TIC-" + random.nextInt(10000) + "', '" + name + "', '" + aadhar + "','" + nationality + "','" + flightname + "','" + flightcode + "','" + src + "','" + des + "','" + ddate + "')";

                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Ticket Booked Successfully");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}
