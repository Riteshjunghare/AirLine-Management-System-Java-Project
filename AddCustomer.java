package airlinemanagement;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
    
    public class AddCustomer extends JFrame implements ActionListener{
        
        JTextField tfname, tfphone, tfadhar, tfnationality, tfaddress;
        JRadioButton rdmale, rdfemale;
        
        public AddCustomer(){
            getContentPane().setBackground(Color.WHITE);
            setLayout(null);
            
            JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
            heading.setBounds(220, 20, 500, 35);
            heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
            heading.setForeground(Color.BLUE);
            add(heading);
            
            JLabel lblname = new JLabel("NAME");
            lblname.setBounds(60, 80, 150, 25);
            lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
            add(lblname);
            
            tfname = new JTextField();
            tfname.setBounds(220, 80, 150, 25);
            add(tfname);
            
            JLabel lbltfnationality = new JLabel("Nationality");
            lbltfnationality.setBounds(60, 130, 150, 25);
            lbltfnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
            add(lbltfnationality);
            
            tfnationality  = new JTextField();
            tfnationality .setBounds(220, 130, 150, 25);
            add(tfnationality );
            
            JLabel lbladhar = new JLabel("Aadhar Number");
            lbladhar.setBounds(60, 180, 150, 25);
            lbladhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
            add(lbladhar);
            
            tfadhar  = new JTextField();
            tfadhar.setBounds(220, 180, 150, 25);
            add(tfadhar );
            
            JLabel lbladdress = new JLabel("Address");
            lbladdress.setBounds(60, 230, 150, 25);
            lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
            add(lbladdress);
            
            tfaddress = new JTextField();
            tfaddress.setBounds(220, 230, 150, 25);
            add(tfaddress );
            
            JLabel lblgender = new JLabel("Gender");
            lblgender.setBounds(60, 280, 150, 25);
            lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
            add(lblgender);
            
            ButtonGroup genderGroup = new ButtonGroup();
            
            rdmale = new JRadioButton("male");
            rdmale.setBounds(220, 280, 70, 25);
            rdmale.setBackground(Color.WHITE);
            add(rdmale);
            
            rdfemale = new JRadioButton("Female");
            rdfemale.setBounds(300, 280, 70, 25);
            rdfemale.setBackground(Color.WHITE);
            add(rdfemale);
            
            genderGroup.add(rdmale);
            genderGroup.add(rdfemale);
            
            JLabel lblphone = new JLabel("Phone Number");
            lblphone.setBounds(60, 330, 150, 25);
            lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
            add(lblphone);
            
            tfphone = new JTextField();
            tfphone.setBounds(220, 330, 150, 25);
            add(tfphone );
            
            JButton save = new JButton("Save");
            save.setBackground(Color.BLACK);
            save.setForeground(Color.WHITE);
            save.setBounds(220,380,150,30);
            save.addActionListener(this);
            add(save);
            
            
            ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("airlinemanagement/icons/emp.png"));
            JLabel lblimage = new JLabel(image);
            lblimage.setBounds(450, 80, 280, 400);
            add(lblimage);
            
             setSize(900, 600);
             setLocation(300, 150);
             setVisible(true);
        
        } 
        
        public void actionPerformed(ActionEvent ae){
        String adhar = tfadhar.getText();
        String nationality = tfnationality.getText();
        String phone = tfphone.getText();
        String address = tfaddress.getText();
        String name = tfname.getText();
        String gender = null;
        if(rdmale.isSelected()){
        gender = "male";
        }else{
         gender = "Female";
        }
        try{
            Conn conn = new Conn();
            String query = "insert into passenger values('"+name+"', '"+nationality+"', '"+phone+"', '"+address+"', '"+adhar+"', '"+gender+"')";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");


            
        }catch(Exception e){
          e.printStackTrace();
        }
        }
        
        public static void main(String[] args){
            new AddCustomer();
           
        }
    
    }
    

