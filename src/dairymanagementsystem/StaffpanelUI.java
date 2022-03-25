/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dairymanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Akhil
 */
public class StaffpanelUI extends javax.swing.JFrame {

    Connection connection = null;
    public int staffID = 901;
    public String special_guy_daki;
    /**
     * Creates new form StaffpanelUI
     */
    public StaffpanelUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        ManageCustomerjPanel.setVisible(true);
        StaffInfojPanel.setVisible(false);
        ManageMilkjPanel.setVisible(false);
        BranchDetailsjPanel.setVisible(false);
        MilkTypejPanel.setVisible(false);
        BilljPanel.setVisible(false);
        TransactionjPanel.setVisible(false);
        displayCustomerdetailstoTable();
        branchIDintocustomer();
    }

    
    private void updateCustomerintoTable(String phone, String address,int custID){
        String url = " update customer set cust_phone='"+phone+"',Address='"+address+"' where cust_id="+custID+";";
        try{
            connection = ConnectionManager.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(url);
            
            int i = ps.executeUpdate();
            
            if( i> 0){
                JOptionPane.showMessageDialog(this, "Saved Successfully.");
           }
            else{
                JOptionPane.showMessageDialog(this, "Not Saved !!!");
            }
        }
        catch( Exception ex){
            System.out.println("Error in dairymanagementsystem.StaffPanelUI.updateCustomerintotable(): " + ex.getMessage());
        }
        
    }
    
    
    private void staffInfoinTable(String pass){
        String url = " update staff set st_password='"+pass+"' where st_id="+staffID+";";
        try{
            connection = ConnectionManager.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(url);
            
            int i = ps.executeUpdate();
            
            if( i> 0){
                JOptionPane.showMessageDialog(this, "Saved Successfully.");
           }
            else{
                JOptionPane.showMessageDialog(this, "Not Saved !!!");
            }
        }
        catch( Exception ex){
            System.out.println("Error in dairymanagementsystem.ManagerPanelUI.updateStafftable(): " + ex.getMessage());
        }
        
    }
    
    private void deleteCustomerfromTable(int custID){
        String url = " delete from customer where cust_id="+custID+";";
        try{
            connection = ConnectionManager.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(url);
            
            int i = ps.executeUpdate();
            
            if( i> 0){
                JOptionPane.showMessageDialog(this, " Customer Data Deleted");
           }
            
        }
        catch( Exception ex){
            System.out.println("Error in dairymanagementsystem.StaffPanelUI.deleteCustomerfromTable(): " + ex.getMessage());
        }
    }
    
    
    private void displaymilktoTable(){
        String url = " Select * from milk;";
        
        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(url);
            ResultSet rs = ps.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) MilktablejTable.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3),"Delete"});
            }
        }
        catch( Exception ex){
            System.out.println("dairymanagementsystem.StaffpanelUI.displayMilkdataintoTable()" + ex.getMessage());
        }
        
    }
    
    private void addmilkintoTable(String type, double amount){
        String url = " insert into milk(m_type,amt_per_liter) values('"+type+"',"+amount+");";
        
        try{
            connection = ConnectionManager.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(url);
            
            int i = ps.executeUpdate();
            
            if( i> 0){
                JOptionPane.showMessageDialog(this, "Milk Saved Successfully.");
           }
        }
        catch( Exception ex){
            System.out.println("Error in dairymanagementsystem.StaffPanelUI.addMilkToTable(): " + ex.getMessage());
        }
        
        
    }
    
    
//    private void StaffInfoinTable(String pass,String email){
//        String url = " update staff set st_password='"+pass+"' where st_email='"+email+"';";
//        try{
//            connection = ConnectionManager.getConnection();
//            
//            PreparedStatement ps = connection.prepareStatement(url);
//            
//            int i = ps.executeUpdate();
//            
//            if( i> 0){
//                JOptionPane.showMessageDialog(this, "Saved Successfully.");
//           }
//            else{
//                JOptionPane.showMessageDialog(this, "Not Saved !!!");
//            }
//        }
//        catch( Exception ex){
//            System.out.println("Error in dairymanagementsystem.StaffPanelUI.updateStafftable(): " + ex.getMessage());
//        }
//        
//    }
    
    
    
    private void displayCustomerdetailstoTable(){
        String url = " Select cust_id,cust_name,cust_phone,br_name,Address from customer natural join branch;";
        
        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(url);
            ResultSet rs = ps.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) CustomertablejTable.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),"Delete"});
            }
        }
        catch( Exception ex){
            System.out.println("dairymanagementsystem.StaffpanelUI.displayCustomerdataintoTable()" + ex.getMessage());
        }
    }
    
    
    private void updatemilkintoTable(double amount, int mID){
        String url = " update milk set amt_per_liter="+amount+" where m_id="+mID+";";
        try{
            connection = ConnectionManager.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(url);
            
            int i = ps.executeUpdate();
            
            if( i> 0){
                JOptionPane.showMessageDialog(this, "Updated Successfully.");
           }
            else{
                JOptionPane.showMessageDialog(this, "Not Updated !!!");
            }
        }
        catch( Exception ex){
            System.out.println("Error in dairymanagementsystem.StaffPanelUI.updateMilktable(): " + ex.getMessage());
        }
    }
    
    private void deletemilkfromTable(int mID){
        String url = " delete from milk where m_id="+mID+";";
        try{
            connection = ConnectionManager.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(url);
            
            int i = ps.executeUpdate();
            
            if( i> 0){
                JOptionPane.showMessageDialog(this, " Milk Data Deleted");
           }
            
        }
        catch( Exception ex){
            System.out.println("Error in dairymanagementsystem.StaffPanelUI.deletemilkfromTable(): " + ex.getMessage());
        }
        
    }
    
    
    
    
    private void addCustomertoTable(String name, String phone, String address, int brID){
        String url = " insert into customer(cust_name,cust_phone,br_id,Address) values('"+name+"','"+phone+"',"+brID+",'"+address+"');";
        
        try{
            connection = ConnectionManager.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(url);
            
            int i = ps.executeUpdate();
            
            if( i> 0){
                JOptionPane.showMessageDialog(this, "Saved Successfully.");
           }
        }
        catch( Exception ex){
            System.out.println("Error in dairymanagementsystem.StaffPanelUI.addCustomertoTable(): " + ex.getMessage());
        }
        
    }
    
    private void branchIDintocustomer(){
        String url = "select br_id from branch;";
        
        try{
            connection = ConnectionManager.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(url);
            
            ResultSet set = ps.executeQuery();
            
            brIDjComboBox.removeAllItems();
            while( set.next()){
                brIDjComboBox.addItem(set.getString("br_id"));
            }
            
            
        }
        catch( Exception ex){
            System.out.println("Error in dairymanagementsystem.StaffLoginUI.validateStaffLogin(): " + ex.getMessage());
        }
    }
    
    private void addCustomerIDtoBillTable(){
        String url = "select cust_id from customer;";
        
        try{
            connection = ConnectionManager.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(url);
            
            ResultSet set = ps.executeQuery();
            
            CustIDjComboBox1.removeAllItems();
            while( set.next()){
                CustIDjComboBox1.addItem(set.getString("cust_id"));
            }
            
            
        }
        catch( Exception ex){
            System.out.println("Error in dairymanagementsystem.ManagerLoginUI.validateManagerLogin(): " + ex.getMessage());
        }
        
    }
    
    private void addMilkIDtoTable(){
        
        String url = "select m_id from milk;";
        
        try{
            connection = ConnectionManager.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(url);
            
            ResultSet set = ps.executeQuery();
            
            MilkIDjComboBox.removeAllItems();
            while( set.next()){
                MilkIDjComboBox.addItem(set.getString("m_id"));
            }
            
            
        }
        catch( Exception ex){
            System.out.println("Error in dairymanagementsystem.StaffLoginUI.validateStaffLogin(): " + ex.getMessage());
        }
    }
    
    private void milkDetailsintoTable(){
        String url = " Select * from milk;";
        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(url);
            ResultSet rs = ps.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) MTypejTable.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3)});
            }
        }
        catch( Exception ex){
            System.out.println("dairymanagementsystem.StaffpanelUI.displaymilkdetailsintoTable()" + ex.getMessage());
        }
    }
    
    private String DisplayStaffProfileinTable(){

        String url = "Select st_name,st_email,st_phone from staff where st_id = "+staffID+";";
        System.out.println("dairymanagementsystem.StaffpanelUI.DisplayStaffProfileinTable()" + url);
        String st_name = "";
        String st_email = "";
        String st_phone = "";
        
        try{
            connection = ConnectionManager.getConnection();           
            PreparedStatement ps = connection.prepareStatement(url);           
//            Excecutiing and storing result
            ResultSet set = ps.executeQuery(url);
            if(set.next())
            {
                st_name = set.getString("st_name");
                st_email = set.getString("st_email");
                st_phone = set.getString("st_phone");
                Staff_namejLabel.setText(st_name);
                Staff_EmailjLabel.setText(st_email);
                Staff_phonejLabel.setText(st_phone);
                return "";
            }
        }
        catch(Exception ex){
            System.out.println("dairymanagementsystem.StaffpanelUI.DisaplayStaffProfile()");
        }
        return "No Data Found";
    }
    
    private void branchtabledetails(){
        String url = " select * from branch;";
        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(url);
            ResultSet rs = ps.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) br_tabjTable.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3)});
            }
        }
        catch( Exception ex){
            System.out.println("dairymanagementsystem.StaffpanelUI.displayBranchintoTable()" + ex.getMessage());
        }
    }
    
    private void addBillDeatailsintoTable( int custID, int milkID,double quantity){
        String url = " Insert into bill(st_id,cust_id,m_id,quantity,amount,date) values(?,?,?, ?,( select amt_per_liter*? from milk where m_id = ?),curdate());";
        
        try{
            connection = ConnectionManager.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(url);
            ps.setInt(1, staffID);
            ps.setInt(2, custID);
            ps.setInt(3, milkID);
            ps.setDouble(4, quantity);
            ps.setDouble(5, quantity);
            ps.setInt(6, milkID);
            
//            System.out.println(" Query" + ps.toString() );
            int i = ps.executeUpdate();
            
            if( i> 0){
                JOptionPane.showMessageDialog(this, "Saved Successfully.");
           }
            else{
                JOptionPane.showMessageDialog(this, "Data Entered is incorrect!!");
            }
        }
        catch( Exception ex){
            System.out.println("Error in dairymanagementsystem.ManagerPanelUI.addStaffToTable(): " + ex.getMessage());
        }
        
    }
    
    private void displaybillintotable(){
        String url = " select B.b_id, S.st_name,C.cust_name, M.m_type, B.quantity,B.amount, B.date from BILL AS B join CUSTOMER AS C ON B.cust_id= C.cust_id  JOIN STAFF AS S ON  B.st_id =S.st_id JOIN MILK AS M on B.m_id = M.m_id  ;";
        
        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(url);
            ResultSet rs = ps.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) BilltablejTable.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
            }
        }
        catch( Exception ex){
            System.out.println("dairymanagementsystem.StaffpanelUI.displayBilldataintoTable()" + ex.getMessage());
        }
        
    }
    
    private void displaytransactionintotable(){
        String url = " select b_id,st_name,cust_name,sum(amount) from bill join customer join staff on bill.cust_id=customer.cust_id and bill.st_id=staff.st_id group by cust_name;";
        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(url);
            ResultSet rs = ps.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) TransactionjTable.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
            }
        }
        catch( Exception ex){
            System.out.println("dairymanagementsystem.StaffpanelUI.displaytransactionintotable()" + ex.getMessage());
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        SignoutjLabel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ManageMilkjPanel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        MilkType = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        MilkAmtjTextField = new javax.swing.JTextField();
        Warning1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MilktablejTable = new javax.swing.JTable();
        TransactionjPanel = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TransactionjTable = new javax.swing.JTable();
        BilljPanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        CustIDjComboBox1 = new javax.swing.JComboBox<>();
        jButton10 = new javax.swing.JButton();
        MilkIDjComboBox = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        QuantityjTextField = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        BilltablejTable = new javax.swing.JTable();
        ManageCustomerjPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        CustnamejTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        brIDjComboBox = new javax.swing.JComboBox<>();
        CustAddressjTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        CustphonejTextField1 = new javax.swing.JTextField();
        Warning = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CustomertablejTable = new javax.swing.JTable();
        StaffInfojPanel = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Staff_namejLabel = new javax.swing.JLabel();
        Staff_EmailjLabel = new javax.swing.JLabel();
        Staff_phonejLabel = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        MilkTypejPanel = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        mTypejScrollPane = new javax.swing.JScrollPane();
        MTypejTable = new javax.swing.JTable();
        BranchDetailsjPanel = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        Br_TablejScrollPane = new javax.swing.JScrollPane();
        br_tabjTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(725, 408));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setForeground(new java.awt.Color(204, 204, 204));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 408));

        jButton2.setText("Manage Customer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Manage Milk");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Branch Details");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Milk Type");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Bill");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        SignoutjLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dairymanagementsystem/icons8-logout-48.png"))); // NOI18N
        SignoutjLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SignoutjLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SignoutjLabelMouseClicked(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon("D:\\Codes\\user.png")); // NOI18N
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jButton12.setText("Transaction View");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel22.setBackground(new java.awt.Color(0, 0, 0));
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Profile");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel15))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(SignoutjLabel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel22)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(1, 1, 1)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton12)
                .addGap(18, 18, 18)
                .addComponent(SignoutjLabel)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setLayout(new javax.swing.OverlayLayout(jPanel3));

        ManageMilkjPanel.setBackground(new java.awt.Color(204, 204, 255));
        ManageMilkjPanel.setLayout(new java.awt.GridLayout(2, 1));

        jPanel6.setBackground(new java.awt.Color(255, 204, 204));
        jPanel6.setMaximumSize(new java.awt.Dimension(2147483647, 200));
        jPanel6.setMinimumSize(new java.awt.Dimension(500, 200));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Manage Milk");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        jLabel2.setText("Type");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));
        jPanel6.add(MilkType, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 80, -1));

        jLabel9.setText("Amount/Liter");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));

        jButton8.setBackground(new java.awt.Color(204, 204, 204));
        jButton8.setText("ADD");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 70, -1));

        jButton9.setBackground(new java.awt.Color(204, 204, 204));
        jButton9.setText("UPDATE");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        MilkAmtjTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                MilkAmtjTextFieldFocusLost(evt);
            }
        });
        MilkAmtjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MilkAmtjTextFieldActionPerformed(evt);
            }
        });
        jPanel6.add(MilkAmtjTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 110, -1));

        Warning1.setForeground(new java.awt.Color(204, 0, 51));
        jPanel6.add(Warning1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 150, 20));

        ManageMilkjPanel.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MilktablejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Milk ID","Milk Type", "Amount/Lit","Click To Delete"
            }
        ));
        MilktablejTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MilktablejTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(MilktablejTable);

        jPanel7.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 0, 570, 221));

        ManageMilkjPanel.add(jPanel7);

        jPanel3.add(ManageMilkjPanel);

        TransactionjPanel.setLayout(new java.awt.BorderLayout());

        jPanel18.setBackground(new java.awt.Color(255, 204, 204));
        jPanel18.setPreferredSize(new java.awt.Dimension(598, 75));

        jLabel21.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        jLabel21.setText("Transaction View");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(jLabel21)
                .addContainerGap(234, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(24, 24, 24))
        );

        TransactionjPanel.add(jPanel18, java.awt.BorderLayout.PAGE_START);

        jPanel19.setBackground(new java.awt.Color(204, 204, 255));

        TransactionjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Bill ID", "Staff", "Customer", "Total Amount"
            }
        ));
        jScrollPane4.setViewportView(TransactionjTable);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        TransactionjPanel.add(jPanel19, java.awt.BorderLayout.CENTER);

        jPanel3.add(TransactionjPanel);

        BilljPanel.setBackground(new java.awt.Color(204, 204, 255));
        BilljPanel.setLayout(new java.awt.GridLayout(2, 1));

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));
        jPanel8.setMaximumSize(new java.awt.Dimension(2147483647, 200));
        jPanel8.setMinimumSize(new java.awt.Dimension(500, 200));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setText("ISSUE BILL");
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 80, -1));

        jLabel12.setText("Quantity");
        jPanel8.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 50, -1));

        jLabel13.setText("Customer ID");
        jPanel8.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        CustIDjComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CustIDjComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustIDjComboBox1ActionPerformed(evt);
            }
        });
        jPanel8.add(CustIDjComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 80, -1));

        jButton10.setBackground(new java.awt.Color(204, 204, 204));
        jButton10.setText("ADD");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 100, -1));

        MilkIDjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        MilkIDjComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MilkIDjComboBoxMouseClicked(evt);
            }
        });
        MilkIDjComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MilkIDjComboBoxActionPerformed(evt);
            }
        });
        jPanel8.add(MilkIDjComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 70, -1));

        jLabel14.setText("Milk ID");
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 50, -1));
        jPanel8.add(QuantityjTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 110, -1));

        BilljPanel.add(jPanel8);

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BilltablejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Bill ID","Staff", "Customer", "Milk Type", "Quantity","Price","Date"
            }
        ));
        BilltablejTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BilltablejTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(BilltablejTable);

        jPanel9.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 0, 600, 221));

        BilljPanel.add(jPanel9);

        jPanel3.add(BilljPanel);

        ManageCustomerjPanel.setBackground(new java.awt.Color(204, 204, 255));
        ManageCustomerjPanel.setLayout(new java.awt.GridLayout(2, 1));

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));
        jPanel4.setMaximumSize(new java.awt.Dimension(2147483647, 200));
        jPanel4.setMinimumSize(new java.awt.Dimension(500, 200));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel7.setText("Manage Customer");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 180, -1));

        jLabel1.setText("Name");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        CustnamejTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CustnamejTextFieldFocusLost(evt);
            }
        });
        jPanel4.add(CustnamejTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 100, -1));

        jLabel3.setText("Address");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, -1, -1));

        jLabel5.setText("Phone");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        jLabel6.setText("Branch ID");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        brIDjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Branch", "Branch", "Branch", "Branch" }));
        brIDjComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brIDjComboBoxActionPerformed(evt);
            }
        });
        jPanel4.add(brIDjComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));
        jPanel4.add(CustAddressjTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 180, 30));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 70, -1));

        jButton7.setBackground(new java.awt.Color(204, 204, 204));
        jButton7.setText("UPDATE");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        CustphonejTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CustphonejTextField1FocusLost(evt);
            }
        });
        jPanel4.add(CustphonejTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 110, -1));

        Warning.setForeground(new java.awt.Color(204, 0, 51));
        jPanel4.add(Warning, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 150, 20));

        ManageCustomerjPanel.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CustomertablejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID","Name", "Phone", "Branch Name", "Address","Click To Delete"
            }
        ));
        CustomertablejTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CustomertablejTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(CustomertablejTable);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 0, 570, 221));

        ManageCustomerjPanel.add(jPanel5);

        jPanel3.add(ManageCustomerjPanel);

        StaffInfojPanel.setLayout(new java.awt.BorderLayout());

        jPanel14.setBackground(new java.awt.Color(204, 204, 255));
        jPanel14.setPreferredSize(new java.awt.Dimension(598, 75));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("Staff Information");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(236, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(212, 212, 212))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel16)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        StaffInfojPanel.add(jPanel14, java.awt.BorderLayout.PAGE_START);

        jPanel15.setBackground(new java.awt.Color(255, 204, 204));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setText("PROFILE");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Name  :");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Email :");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Phone :");

        Staff_namejLabel.setText("Name");

        Staff_EmailjLabel.setText("Email");

        Staff_phonejLabel.setText("Phone");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel24.setText("Update Password");

        jButton11.setText("UPDATE");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Staff_namejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Staff_phonejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Staff_EmailjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                        .addComponent(jLabel25)
                        .addGap(83, 83, 83))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton11)
                            .addComponent(jLabel17)
                            .addComponent(jLabel24))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(Staff_namejLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(Staff_EmailjLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(Staff_phonejLabel)))
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(68, 68, 68)
                .addComponent(jLabel24)
                .addGap(23, 23, 23)
                .addComponent(jButton11)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        StaffInfojPanel.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel3.add(StaffInfojPanel);

        MilkTypejPanel.setBackground(new java.awt.Color(204, 204, 255));
        MilkTypejPanel.setLayout(new java.awt.BorderLayout());

        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel11.setBackground(new java.awt.Color(204, 204, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel11.setText("Milk Type");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(jLabel11)
                .addContainerGap(253, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(37, 37, 37))
        );

        jPanel10.add(jPanel11, java.awt.BorderLayout.PAGE_START);

        jPanel13.setBackground(new java.awt.Color(255, 204, 204));

        MTypejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Milk ID", "Milk Type", "Amount/Lit"
            }
        ));
        mTypejScrollPane.setViewportView(MTypejTable);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mTypejScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(mTypejScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel13, java.awt.BorderLayout.CENTER);

        MilkTypejPanel.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel3.add(MilkTypejPanel);

        BranchDetailsjPanel.setBackground(new java.awt.Color(255, 204, 204));
        BranchDetailsjPanel.setLayout(new java.awt.BorderLayout());

        jPanel16.setLayout(new java.awt.BorderLayout());

        jPanel17.setBackground(new java.awt.Color(204, 204, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setText("Branch Details");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jLabel4)
                .addContainerGap(224, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel16.add(jPanel17, java.awt.BorderLayout.PAGE_START);

        jPanel12.setBackground(new java.awt.Color(255, 204, 204));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        br_tabjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Branch ID", "Branch Name", "Address"
            }
        ));
        br_tabjTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                br_tabjTableMouseClicked(evt);
            }
        });
        Br_TablejScrollPane.setViewportView(br_tabjTable);

        jPanel12.add(Br_TablejScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 540, 220));

        jPanel16.add(jPanel12, java.awt.BorderLayout.CENTER);

        BranchDetailsjPanel.add(jPanel16, java.awt.BorderLayout.CENTER);

        jPanel3.add(BranchDetailsjPanel);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ManageCustomerjPanel.setVisible(true);
        ManageMilkjPanel.setVisible(false);
        BranchDetailsjPanel.setVisible(false);
        MilkTypejPanel.setVisible(false);
        BilljPanel.setVisible(false);
        StaffInfojPanel.setVisible(false);
        TransactionjPanel.setVisible(false);
        displayCustomerdetailstoTable();
        branchIDintocustomer();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ManageCustomerjPanel.setVisible(false);
        ManageMilkjPanel.setVisible(true);
        BranchDetailsjPanel.setVisible(false);
        MilkTypejPanel.setVisible(false);
        BilljPanel.setVisible(false);
        StaffInfojPanel.setVisible(false);
        TransactionjPanel.setVisible(false);
        
        displaymilktoTable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        ManageCustomerjPanel.setVisible(false);
        ManageMilkjPanel.setVisible(false);
        BranchDetailsjPanel.setVisible(true);
        MilkTypejPanel.setVisible(false);
        BilljPanel.setVisible(false);
        StaffInfojPanel.setVisible(false);
        TransactionjPanel.setVisible(false);
        branchtabledetails();
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        ManageCustomerjPanel.setVisible(false);
        ManageMilkjPanel.setVisible(false);
        BranchDetailsjPanel.setVisible(false);
        MilkTypejPanel.setVisible(true);
        BilljPanel.setVisible(false);
        StaffInfojPanel.setVisible(false);
        TransactionjPanel.setVisible(false);
        milkDetailsintoTable();
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        ManageCustomerjPanel.setVisible(false);
        ManageMilkjPanel.setVisible(false);
        BranchDetailsjPanel.setVisible(false);
        MilkTypejPanel.setVisible(false);
        BilljPanel.setVisible(true);
        StaffInfojPanel.setVisible(false);
        TransactionjPanel.setVisible(false);
        addCustomerIDtoBillTable();
        addMilkIDtoTable();
        displaybillintotable();
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void brIDjComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brIDjComboBoxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_brIDjComboBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String name = CustnamejTextField.getText();
        String address = CustAddressjTextField.getText();
        String phone = CustphonejTextField1.getText();
       
        String brID = String.valueOf(brIDjComboBox.getSelectedItem());
        int id = Integer.parseInt(brID);
        
        if(name.isEmpty() || address.isEmpty() || phone.isEmpty() || brID.isEmpty()){
            JOptionPane.showMessageDialog(this, "Empty Fields are Not Allowed");
        }
        else{
            
        addCustomertoTable(name, phone, address, id);
        displayCustomerdetailstoTable();
        }

       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String custID = JOptionPane.showInputDialog(this, "Enter Customer ID whose Phone Number to Change ");
        String phone = JOptionPane.showInputDialog(this, "Enter Phone Number to Change ");
        String address = JOptionPane.showInputDialog(this, "Enter Address to Change ");
        
        if(custID.isEmpty() || phone.isEmpty() || address.isEmpty()){
            JOptionPane.showMessageDialog(this, "Empty Fields are not accepted.!");
        }
        else{
            updateCustomerintoTable(phone, address, Integer.parseInt(custID));
        }
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void CustomertablejTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomertablejTableMouseClicked
        // TODO add your handling code here:
         int choice = JOptionPane.showConfirmDialog(this, "Want to delete", "Confirmation", JOptionPane.YES_NO_OPTION);
        System.out.println("Option :- " + choice);
        if(choice == 0){
            int column = 0;
            int row = CustomertablejTable.getSelectedRow();
            int custID = Integer.parseInt(CustomertablejTable.getModel().getValueAt(row, column).toString());
            
            deleteCustomerfromTable(custID);
          
        }
        
    }//GEN-LAST:event_CustomertablejTableMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        String type = MilkType.getText();
        double amount;
        try{
            amount = Double.parseDouble( MilkAmtjTextField.getText());
            
            if(type.isEmpty()){
              JOptionPane.showMessageDialog(this, "Enter valid Milk Type");
            }
            else{
                addmilkintoTable(type, amount);
                displaymilktoTable();
            }
        }
        catch( Exception ex){
           JOptionPane.showMessageDialog(this, "Enter amount");
        }
        
       
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        String mID = JOptionPane.showInputDialog(this, "Enter Milk ID whose Data to Change ");
        Double amount =Double.parseDouble( JOptionPane.showInputDialog(this, "Enter Milk Amount to Change "));
        
        if(mID.isEmpty() || amount.isNaN()){
            JOptionPane.showMessageDialog(this, "Empty Fields are not accepted.!");
            
        }
        else {
            updatemilkintoTable(amount, Integer.parseInt(mID));
            displaymilktoTable();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void MilktablejTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MilktablejTableMouseClicked
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(this, "Want to delete", "Confirmation", JOptionPane.YES_NO_OPTION);
        System.out.println("Option :- " + choice);
        if(choice == 0){
            int column = 0;
            int row = MilktablejTable.getSelectedRow();
            int mID = Integer.parseInt(MilktablejTable.getModel().getValueAt(row, column).toString());
            
            deletemilkfromTable(mID);
            
          
        }
    }//GEN-LAST:event_MilktablejTableMouseClicked

    private void CustIDjComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustIDjComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CustIDjComboBox1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        
        try{
        int cID = Integer.parseInt(CustIDjComboBox1.getSelectedItem().toString());
        int mID = Integer.parseInt(MilkIDjComboBox.getSelectedItem().toString());
        int quantity = Integer.parseInt(QuantityjTextField.getText());
        
        addBillDeatailsintoTable(cID,mID,quantity);
        }
        catch( Exception ex){
            JOptionPane.showMessageDialog(this, "Enter Valid Quantity");
            System.out.println("dairymanagementsystem.StaffpanelUI.jButton10ActionPerformed()" + ex.getMessage());
        }
        
        
        
        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void BilltablejTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BilltablejTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BilltablejTableMouseClicked

    private void MilkIDjComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MilkIDjComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MilkIDjComboBoxActionPerformed

    private void SignoutjLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignoutjLabelMouseClicked
        // TODO add your handling code here:
        
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirmation", JOptionPane.YES_NO_OPTION);
        System.out.println("Option :- " + choice);
        
        if(choice == 0){
            new FirstPage().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_SignoutjLabelMouseClicked

    private void br_tabjTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_br_tabjTableMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_br_tabjTableMouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
        StaffInfojPanel.setVisible(true);
        ManageCustomerjPanel.setVisible(false);
        ManageMilkjPanel.setVisible(false);
        BranchDetailsjPanel.setVisible(false);
        MilkTypejPanel.setVisible(false);
        BilljPanel.setVisible(false);
        TransactionjPanel.setVisible(false);
        DisplayStaffProfileinTable();
        
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        String pass = JOptionPane.showInputDialog(this, "Enter Password to Change ");
        
        try{
            if(pass.isEmpty()){
                JOptionPane.showMessageDialog(this, " Enter Valid Password");
            }
            else{
                 staffInfoinTable(pass);                
            }
        }catch(Exception ex){
            System.out.println("dairymanagementsystem.StaffpanelUI.jButton11ActionPerformed()");
        }
//        String email = JOptionPane.showInputDialog(this, "Enter Email ID whose Password to Change ");
//        String pass = JOptionPane.showInputDialog(this, "Enter Password to Change ");
//        
//        try{
//            if(email.isEmpty() || pass.isEmpty()){
//            JOptionPane.showMessageDialog(this, "Empty Fields are not accepted.!");
//        }
//        else{
//            StaffInfoinTable(pass, email);
//        }
//        }catch(Exception ex){
//            System.out.println("dairymanagementsystem.ManagerpanelUI.jButton6ActionPerformed()");
//        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void CustnamejTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CustnamejTextFieldFocusLost
        // TODO add your handling code here:
         String PATTERN = "^[a-zA-Z]{3,18}";
        Pattern patt = Pattern.compile(PATTERN);
//        Matcher match = patt.matcher(managerNamejTextField.getText());
        String name = CustnamejTextField.getText();

        if(! patt.matcher(name).matches()){
            JOptionPane.showMessageDialog(this, "Incorrect Name");
            Warning.setText("Naming is incorrect");
            CustnamejTextField.setText("");
        }
        else{
            Warning.setText("");
        }
    }//GEN-LAST:event_CustnamejTextFieldFocusLost

    private void CustphonejTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CustphonejTextField1FocusLost
        // TODO add your handling code here:
        String PATTERN = "[0-9]{10}";
        Pattern patt = Pattern.compile(PATTERN);
//        Matcher match = patt.matcher(managerNamejTextField.getText());
        String name = CustphonejTextField1.getText();

        if(! patt.matcher(name).matches()){
//            JOptionPane.showMessageDialog(this, "Incorrect Email");
            Warning.setText("Phone number is incorrect");
            CustphonejTextField1.setText("");
        }
        else{
            Warning.setText("");
        }
    }//GEN-LAST:event_CustphonejTextField1FocusLost

    private void MilkAmtjTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MilkAmtjTextFieldFocusLost
        // TODO add your handling code here:
        String PATTERN = "[0-9]{1,8}";
        Pattern patt = Pattern.compile(PATTERN);
//        Matcher match = patt.matcher(managerNamejTextField.getText());
        String name = MilkAmtjTextField.getText();

        if(! patt.matcher(name).matches()){
//            JOptionPane.showMessageDialog(this, "Incorrect Email");
            Warning1.setText("Amount is incorrect");
            MilkAmtjTextField.setText("");
        }
        else{
            Warning1.setText("");
        }
    }//GEN-LAST:event_MilkAmtjTextFieldFocusLost

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        StaffInfojPanel.setVisible(false);
        ManageCustomerjPanel.setVisible(false);
        ManageMilkjPanel.setVisible(false);
        BranchDetailsjPanel.setVisible(false);
        MilkTypejPanel.setVisible(false);
        BilljPanel.setVisible(false);
        TransactionjPanel.setVisible(true);
        
        displaytransactionintotable();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void MilkIDjComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MilkIDjComboBoxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_MilkIDjComboBoxMouseClicked

    private void MilkAmtjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MilkAmtjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MilkAmtjTextFieldActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StaffpanelUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffpanelUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffpanelUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffpanelUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffpanelUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BilljPanel;
    private javax.swing.JTable BilltablejTable;
    private javax.swing.JScrollPane Br_TablejScrollPane;
    private javax.swing.JPanel BranchDetailsjPanel;
    private javax.swing.JTextField CustAddressjTextField;
    private javax.swing.JComboBox<String> CustIDjComboBox1;
    private javax.swing.JTextField CustnamejTextField;
    private javax.swing.JTable CustomertablejTable;
    private javax.swing.JTextField CustphonejTextField1;
    private javax.swing.JTable MTypejTable;
    private javax.swing.JPanel ManageCustomerjPanel;
    private javax.swing.JPanel ManageMilkjPanel;
    private javax.swing.JTextField MilkAmtjTextField;
    private javax.swing.JComboBox<String> MilkIDjComboBox;
    private javax.swing.JTextField MilkType;
    private javax.swing.JPanel MilkTypejPanel;
    private javax.swing.JTable MilktablejTable;
    private javax.swing.JTextField QuantityjTextField;
    private javax.swing.JLabel SignoutjLabel;
    private javax.swing.JPanel StaffInfojPanel;
    private javax.swing.JLabel Staff_EmailjLabel;
    private javax.swing.JLabel Staff_namejLabel;
    private javax.swing.JLabel Staff_phonejLabel;
    private javax.swing.JPanel TransactionjPanel;
    private javax.swing.JTable TransactionjTable;
    private javax.swing.JLabel Warning;
    private javax.swing.JLabel Warning1;
    private javax.swing.JComboBox<String> brIDjComboBox;
    private javax.swing.JTable br_tabjTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane mTypejScrollPane;
    // End of variables declaration//GEN-END:variables
}
