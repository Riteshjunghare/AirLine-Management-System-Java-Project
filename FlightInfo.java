package airlinemanagement;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.sql.*;
import javax.swing.JScrollPane;
import net.proteanit.sql.DbUtils;

public class FlightInfo extends JFrame {

    public FlightInfo() {

        getContentPane().setBackground(Color.white);
        setLayout(null);

        JTable table = new JTable();

        Conn conn = new Conn();
        ResultSet rs = null;
        try {
            rs = conn.s.executeQuery("SELECT * FROM flight");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                conn.closeConnection();  // Close the connection and statement
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 0, 800, 500);
        add(jsp);

        setSize(800, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FlightInfo();
    }
}
