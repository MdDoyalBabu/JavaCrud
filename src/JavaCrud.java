import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JavaCrud {
    private JPanel Main;
    private JTextField textName;
    private JTextField textPrice;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField textProductID;
    private JTextField textQty;
    private JButton searchButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaCrud");
        frame.setContentPane(new JavaCrud().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection con;
    PreparedStatement pst;

    public JavaCrud() {
        Connect();;

        // data save button

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name,price,qty;


                name=textName.getText();
                price=textPrice.getText();
                qty=textQty.getText();


                try {

                    pst=con.prepareStatement("insert into products(pname,price,qty)values(?,?,?)");
                    pst.setString(1,name);
                    pst.setString(2,price);
                    pst.setString(3,qty);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Record Added !!!!!");

                    textName.setText("");
                    textPrice.setText("");
                    textQty.setText("");
                    textName.requestFocus();


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });


        // search button

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {

                    String pid=textProductID.getText();

                    pst=con.prepareStatement("select pname,price,qty from products where pid=?");
                    pst.setString(1,pid);
                    ResultSet rs=pst.executeQuery();

                    if (rs.next()==true){


                        String name=rs.getString(1);
                        String price=rs.getString(2);
                        String qty=rs.getString(3);

                        textName.setText(name);
                        textPrice.setText(price);
                        textQty.setText(qty);


                    }else {
                        textName.setText("");
                        textPrice.setText("");
                        textQty.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid your Product ID");
                    }


                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        // update button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name,price,qty,pid;


                name=textName.getText();
                price=textPrice.getText();
                qty=textQty.getText();
                pid=textProductID.getText();

                try {

                    pst=con.prepareStatement("update products set pname=?,price=?,qty=? where pid=?");

                    pst.setString(1,name);
                    pst.setString(2,price);
                    pst.setString(3,qty);
                    pst.setString(4,pid);

                    pst.executeUpdate();


                    JOptionPane.showMessageDialog(null,"Updated Successful");


                    textName.setText("");
                    textPrice.setText("");
                    textQty.setText("");
                    textName.requestFocus();
                    textProductID.setText("");





                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });


        // delete button

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String bid;

                bid=textProductID.getText();

                try {
                    pst=con.prepareStatement("delete from products where pid=?");

                    pst.setString(1,bid);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Your Data Deleted !!!!!");


                    textName.setText("");
                    textPrice.setText("");
                    textQty.setText("");
                    textName.requestFocus();
                    textProductID.setText("");


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });
    }



    public void Connect(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost/gbproducts","root","");
            System.out.println("Alhamdulillah Connection is Success");


          /*  Statement statement=con.createStatement();

            ResultSet rs=statement.executeQuery("select * Max(cust_id) from customer");
            rs.next();

            rs.getArray("Max(cust_id");

            if (rs.getArray("Max(cust_id")==null){
                jLabel9.setText("cs1000");
            }else {
                Long id=Long.parseLong(String.valueOf(rs.getString("Max(cust_id)").substring(2, Integer.parseInt(rs.getString("Max(cust_id)"))).length()));
            }


           */


        }catch (ClassNotFoundException ex){

            ex.printStackTrace();
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
