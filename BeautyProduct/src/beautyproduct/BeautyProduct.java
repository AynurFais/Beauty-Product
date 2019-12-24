package beautyproduct;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BeautyProduct {


    ArrayList<Admin> adminlist = new ArrayList<Admin>();
    String header[] = new String[]{"ProductID", "Name", "Type", "KKM ID", "Stock"};
    DefaultTableModel dtm = new DefaultTableModel(header, 1);

    BeautyProduct() {

        JFrame frame = new JFrame("Beauty Product Registration System ");
        frame.setSize(700, 400);

        JLabel jlabel = new JLabel("Name");
        jlabel.setBounds(20, 10, 90, 20);
        frame.add(jlabel);

        JLabel jlabela = new JLabel("Product ID");
        jlabela.setBounds(20, 30, 90, 20);
        frame.add(jlabela);

        JLabel jlabelb = new JLabel("Type");
        jlabelb.setBounds(20, 50, 90, 20);
        frame.add(jlabelb);
        
        JLabel jlabelc = new JLabel("KKM ID");
        jlabelc.setBounds(350, 10, 90, 20);
        frame.add(jlabelc);
        
        JLabel jlabeld = new JLabel("Stock");
        jlabeld.setBounds(350, 30, 90, 20);
        frame.add(jlabeld);

        JTextField jtfname = new JTextField();
        jtfname.setBounds(110, 10, 200, 20);
        frame.add(jtfname);

        JTextField jtfproductID = new JTextField();
        jtfproductID.setBounds(110, 30, 200, 20);
        frame.add(jtfproductID);

        JTextField jtftype = new JTextField();
        jtftype.setBounds(110, 50, 200, 20);
        frame.add(jtftype);
        
        JTextField jtfkkmID = new JTextField();
        jtfkkmID.setBounds(440, 10, 150, 20);
        frame.add(jtfkkmID);
        
        JTextField jtfstock = new JTextField();
        jtfstock.setBounds(440, 30, 150, 20);
        frame.add(jtfstock);

        JButton jbuttoninsert = new JButton("INSERT");
        jbuttoninsert.setBounds(90, 90, 90, 30);
        frame.add(jbuttoninsert);

        JButton jbuttondelete = new JButton("DELETE");
        jbuttondelete.setBounds(190, 90, 90, 30);
        frame.add(jbuttondelete);

        JButton jbuttonsearch = new JButton("SEARCH");
        jbuttonsearch.setBounds(290, 90, 90, 30);
        frame.add(jbuttonsearch);
        
        JButton jbuttonupdate = new JButton("UPDATE");
        jbuttonupdate.setBounds(390, 90, 90, 30);
        frame.add(jbuttonupdate);

        JButton jbuttonexit = new JButton("EXIT");
        jbuttonexit.setBounds(490, 90, 90, 30);
        frame.add(jbuttonexit);

        //table creation
        JTable jtable = new JTable();
        jtable.setBounds(100, 200, 400, 400);
        frame.add(jtable);
        jtable.setModel(dtm);
        JScrollPane scrollPane = new JScrollPane(jtable);
        scrollPane.setBounds(20, 150, 650, 180);
        frame.add(scrollPane);
        jtable.getColumnModel().getColumn(0).setPreferredWidth(100);
        jtable.getColumnModel().getColumn(1).setPreferredWidth(200);
        jtable.getColumnModel().getColumn(2).setPreferredWidth(300);
        jtable.getColumnModel().getColumn(2).setPreferredWidth(300);
        jtable.getColumnModel().getColumn(2).setPreferredWidth(300);

        jbuttoninsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String name = jtfname.getText().toUpperCase();
                String productID = jtfproductID.getText();
                String type = jtftype.getText().toUpperCase();
                String kkmID = jtfkkmID.getText();
                String stock = jtfstock.getText();
                if (name.length() < 5) {
                    JOptionPane.showMessageDialog(frame, "Owner Name should contain more than 5 char!!!");
                    return;
                }
                if (!isInteger(productID)) {
                    JOptionPane.showMessageDialog(frame, "Product ID should only contain integer!!!");
                    return;
                }

               Admin admin = new Admin(name, productID, type, kkmID, stock);
                adminlist.add(admin);//create object list array
                writeData();
            }
        });

        jbuttondelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String productID = JOptionPane.showInputDialog("Product ID number to delete?");
                if (productID != null) {
                    System.out.println("not null");
                    for (int i = 0; i < adminlist.size(); i++) {
                        if (adminlist.get(i).getProductID().equalsIgnoreCase(productID)) {
                            adminlist.remove(i);
                        }
                    }
                    writeData();
                }
            }
        });

        jbuttonsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String productID = JOptionPane.showInputDialog("Enter Product ID Number?");
                if (productID != null) {
                    for (int i = 0; i < adminlist.size(); i++) {
                        if (adminlist.get(i).getProductID().equalsIgnoreCase(productID)) {
                            JOptionPane.showMessageDialog(frame, "Found!!!");
                            jtfproductID.setText(adminlist.get(i).getProductID());
                            jtfname.setText(adminlist.get(i).getName());
                            jtftype.setText(adminlist.get(i).getType());
                            jtfkkmID.setText(adminlist.get(i).getKkmID());
                            jtfstock.setText(adminlist.get(i).getStock());
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Not Found!!!");
                }
            }
            
        });
        
        jbuttonupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String name = jtfname.getText();
                String productID = jtfproductID.getText();
                String type = jtftype.getText();
                String kkmID = jtfkkmID.getText();
                String stock = jtfstock.getText();
                if (productID != null) {
                    for (int i = 0; i < adminlist.size(); i++) {
                        if (adminlist.get(i).getProductID().equalsIgnoreCase(productID)) {
                            adminlist.get(i).setName(name);
                            adminlist.get(i).setType(type);
                            adminlist.get(i).setKkmID(kkmID);
                            adminlist.get(i).setStock(stock);
                            JOptionPane.showMessageDialog(frame, "Updated!!!");
                        }
                    }
                }
                writeData();
            }
        });
        jbuttonexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.setVisible(false); //you can't see me!
                frame.dispose();
            }
        });

        readData();
        jtable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jtable.getSelectedRow();
                jtfproductID.setText(dtm.getValueAt(row, 0).toString());
                jtfname.setText(dtm.getValueAt(row, 1).toString());
                jtftype.setText(dtm.getValueAt(row, 2).toString());
                jtfkkmID.setText(dtm.getValueAt(row, 3).toString());
                jtfstock.setText(dtm.getValueAt(row, 4).toString());
            }
        });

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BeautyProduct beautyproduct = new BeautyProduct();

    }

    void readData() { //read data from "data.txt" and display on table
        try {
            File file = new File("data.txt"); //create file
            file.createNewFile();//if not exit
            FileReader f = new FileReader("data.txt");
            StringBuffer sb = new StringBuffer();
            while (f.ready()) {
                char c = (char) f.read();
                if (c == '-') {
                    System.out.println(sb);
                    String adminarray[] = sb.toString().split(",");
                    Admin admin = new Admin(adminarray[0], adminarray[1], adminarray[2], adminarray[3],adminarray[4]);
                    adminlist.add(admin);
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
            dtm.setRowCount(0); //update table
            for (int i = 0; i < adminlist.size(); i++) {//populate table using object list
                Object[] objs = {adminlist.get(i).getProductID(), adminlist.get(i).getName(), adminlist.get(i).getType(), adminlist.get(i).getKkmID(), adminlist.get(i).getStock()};
                dtm.addRow(objs);
            }
        } catch (IOException e) {
        }
    }

    private void writeData() { //write data to file "data.txt"
        try (FileWriter f = new FileWriter("data.txt")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < adminlist.size(); i++) {
                sb.append(adminlist.get(i).getName() + "," + adminlist.get(i).getProductID() + "," + adminlist.get(i).getType() + "," + adminlist.get(i).getKkmID() + "," + adminlist.get(i).getStock() + "-");
            }
            f.write(sb.toString());
            f.close();
        } catch (IOException e) {
            return;
        }
        dtm.setRowCount(0); //update table content
        for (int i = 0; i < adminlist.size(); i++) {//populate table using object list
            Object[] objs = {adminlist.get(i).getProductID(), adminlist.get(i).getName(), adminlist.get(i).getType(), adminlist.get(i).getKkmID(), adminlist.get(i).getStock()};
            dtm.addRow(objs);
        }
    }

    public boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
