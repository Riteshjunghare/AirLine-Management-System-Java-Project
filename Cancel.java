package airlinemanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class Cancel extends JFrame implements ActionListener {

    JTextField tfpnr;
    JLabel cancellation, lblfcode, tfname, lbldate;
    JButton fetchButton, cancelButton;
    
    Random random = new Random();

    public Cancel() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Cancellation");
        heading.setBounds(180, 20, 250, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagement/icons/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470, 120, 250, 250);
        add(image);

        JLabel lblpnr = new JLabel("PNR Number");
        lblpnr.setBounds(60, 80, 150, 25);
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblpnr);

        tfpnr = new JTextField();
        tfpnr.setBounds(220, 80, 150, 25);
        add(tfpnr);

        fetchButton = new JButton("Show Details");
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblname = new JLabel("Aadhar No");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);

        tfname = new JLabel();
        tfname.setBounds(220, 130, 150, 25);
        add(tfname);

        JLabel lblcancellation = new JLabel("Cancellation No");
        lblcancellation.setBounds(60, 180, 150, 25);
        lblcancellation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcancellation);

        cancellation = new JLabel("" + random.nextInt(1000000));
        cancellation.setBounds(220, 180, 150, 25);
        add(cancellation);

        JLabel lblcode = new JLabel("Flight Code");
        lblcode.setBounds(60, 230, 150, 25);
        lblcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcode);

        lblfcode = new JLabel();
        lblfcode.setBounds(220, 230, 150, 25);
        add(lblfcode);

        JLabel lbldateLabel = new JLabel("Date");
        lbldateLabel.setBounds(60, 280, 150, 25);
        lbldateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldateLabel);

        lbldate = new JLabel();
        lbldate.setBounds(220, 280, 150, 25);
        add(lbldate);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBounds(220, 330, 120, 25);
        cancelButton.addActionListener(this);
        add(cancelButton);

        setSize(800, 450);
        setLocation(350, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String pnr = tfpnr.getText();

            try {
                Conn conn = new Conn();
                String query = "select * from reservation where PNR = '" + pnr + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    tfname.setText(rs.getString("aadhar"));
                    lblfcode.setText(rs.getString("flightcode"));
                    lbldate.setText(rs.getString("ddate"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct PNR Number");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancelButton) {
            String pnr = tfpnr.getText();
            String aadhar = tfname.getText();
            String cancelno = cancellation.getText();
            String fcode = lblfcode.getText();
            String date = lbldate.getText();

            try {
                Conn conn = new Conn();
                String query = "insert into cancel values('" + pnr + "', '" + aadhar + "', '" + cancelno + "', '" + fcode + "', '" + date + "')";
                conn.s.executeUpdate(query);
                
                String deleteQuery = "delete from reservation where PNR = '" + pnr + "'";
                conn.s.executeUpdate(deleteQuery);

                JOptionPane.showMessageDialog(null, "Ticket Canceled");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Cancel();
    }
}
