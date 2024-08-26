package airlinemanagement;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.sql.*;
import javax.swing.JScrollPane;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JourneyDetails extends JFrame implements ActionListener {
    JTable table;
    JTextField pnr;
    JButton show;

    public JourneyDetails() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel lblpnr = new JLabel("PNR Details");
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpnr.setBounds(50, 50, 100, 25);
        add(lblpnr);

        pnr = new JTextField();
        pnr.setBounds(160, 50, 120, 25);
        add(pnr);

        show = new JButton("Show");
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
        show.setBounds(290, 50, 120, 25);
        show.addActionListener(this);
        add(show);

        table = new JTable();

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 800, 150);
        add(jsp);

        setSize(800, 600);
        setLocation(400, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String pnrText = pnr.getText();

        if (pnrText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a PNR number");
            return;
        }

        try {
            Conn conn = new Conn();
            String query = "select * from reservation where PNR = ?";
            PreparedStatement pstmt = conn.c.prepareStatement(query);
            pstmt.setString(1, pnrText);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found");
                return;
            }

            table.setModel(DbUtils.resultSetToTableModel(rs));

            rs.close();
            pstmt.close();
            conn.c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JourneyDetails();
    }
}
