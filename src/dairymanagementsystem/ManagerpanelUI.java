/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dairymanagementsystem;

import com.mysql.cj.xdevapi.Statement;
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
public class ManagerpanelUI extends javax.swing.JFrame {

    Connection connection = null;
    public int special_variable;
    /**
     * Creates new form ManagerpanelUI
     */
    public ManagerpanelUI() {
        initComponents();
//        To center this frame
        this.setLocationRelativeTo(null);
        
//        Initial open window
        ManagestaffjPanel.setVisible(true);
        MgrInfojPanel.setVisible(false);
        BranchjPanel.setVisible(false);
        CustomerjPanel.setVisible(false);
        MilkdetailsjPanel.setVisible(false);
        
        addBranchIDTOComboBox();
        displayStaffdatailtoTable();
    }
    
    private void displayStaffdatailtoTable(){
        String url = " Select st_id,st_name,st_email,st_phone,br_name from staff natural join branch;";
        
        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(url);
            ResultSet rs = ps.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) StafftablejTable.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),"Delete"});
            }
        }
        catch( Exception ex){
            System.out.println("dairymanagementsystem.ManagerpanelUI.displayStaffdataintoTable()" + ex.getMessage());
        }
    }

    private void displayBranchintoTable(){
        String url = " select * from branch;";
        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(url);
            ResultSet rs = ps.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) BranchtablejTable.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3), "Delete"});
            }
        }
        catch( Exception ex){
            System.out.println("dairymanagementsystem.ManagerpanelUI.displayBranchintoTable()" + ex.getMessage());
        }
        
    }
    private void displayCustomerintoTable(){
        String url = " select * from customer;";
        
        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(url);
            ResultSet rs = ps.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) CusttablejTable.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
            }
        }
        catch( Exception ex){
            System.out.println("dairymanagementsystem.ManagerpanelUI.displayCustomerintoTable()" + ex.getMessage());
        }
        
    }
    
    private void displaymilkdetailsintoTable(){
        String url = " Select * from milk;";
        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(url);
            ResultSet rs = ps.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) MilktablejTable.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3)});
            }
        }
        catch( Exception ex){
            System.out.println("dairymanagementsystem.ManagerpanelUI.displaymilkdetailsintoTable()" + ex.getMessage());
        }
        
    }
    
    private void updateStafftable(String pass,String email){
        String url = " update staff set st_password='"+pass+"' where st_email='"+email+"';";
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
    
    private void deleteStafffromtable(int staffid){
        String url = " delete from staff where st_id="+staffid+";";
        try{
            connection = ConnectionManager.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(url);
            
            int i = ps.executeUpdate();
            
            if( i> 0){
                JOptionPane.showMessageDialog(this, " Staff Data Deleted");
                displayStaffdatailtoTable();
                
           }
            
        }
        catch( Exception ex){
            System.out.println("Error in dairymanagementsystem.ManagerPanelUI.updateStafftable(): " + ex.getMessage());
        }
        
    }
    
    private void insertbranchintable(String br_name, String br_address){
        String url = " insert into branch(br_name,br_address) values('"+br_name+"','"+br_address+"');";
        
        try{
            connection = ConnectionManager.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(url);
            
            int i = ps.executeUpdate();
            
            if( i> 0){
                JOptionPane.showMessageDialog(this, "Saved Successfully.");
           }
        }
        catch( Exception ex){
            System.out.println("Error in dairymanagementsystem.ManagerPanelUI.insertbranchintable(): " + ex.getMessage());
        }
        
    }
    
    private void deletebranchintoTable(int br_id){
        String url = " delete from branch where br_id = "+br_id+";";
        try{
            connection = ConnectionManager.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(url);
            
            int i = ps.executeUpdate();
            
            if( i> 0){
                JOptionPane.showMessageDialog(this, " Branch Data Deleted");
           }
            
        }
        catch( Exception ex){
            System.out.println("Error in dairymanagementsystem.StaffPanelUI.deleteBranchfromTable(): " + ex.getMessage());
        }
        
    }
    
    private void updateManagerpassinTable(int mgID,String pass){
          String url = " update manager set mg_password='"+pass+"' where mg_id="+mgID+";";
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
            System.out.println("Error in dairymanagementsystem.ManagerPanelUI.updateManagertable(): " + ex.getMessage());
        }
        
    }
    
    private void addBranchIDTOComboBox(){
        
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
            System.out.println("Error in dairymanagementsystem.ManagerLoginUI.validateManagerLogin(): " + ex.getMessage());
        }
        
    }
    
//    For Profile
    private String DisaplayManagerProfile(){
        String url = "Select mg_name,mg_email from manager where mg_id="+special_variable+";";
        String Mgrname = "";
        String MgrEmail = "";
        
        try{
            connection = ConnectionManager.getConnection();           
            PreparedStatement ps = connection.prepareStatement(url);           
//            Excecutiing and storing result
            ResultSet set = ps.executeQuery(url);
            if(set.next())
            {
                Mgrname = set.getString("mg_name");
                MgrEmail = set.getString("mg_email");
                
                Mgr_namejLabel.setText(Mgrname);
                Mgr_emailjLabel.setText(MgrEmail);
                return "";
            }
        }
        catch(Exception ex){
            System.out.println("dairymanagementsystem.ManagerpanelUI.DisaplayManagerProfile()");
        }
        return "No Data Found";
        
    }
    
    
    private void addStaffToTable(String name, String email, String pass, String phone, int brID){
        
        String url = " insert into staff(st_name,st_email,st_password,st_phone,br_id) values('"+name+"','"+email+"','"+pass+"','"+phone+"',"+brID+");";
        
        try{
            connection = ConnectionManager.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(url);
            
            int i = ps.executeUpdate();
            
            if( i> 0){
                JOptionPane.showMessageDialog(this, "Saved Successfully.");
           }
        }
        catch( Exception ex){
//            trigger raised!!!
            JOptionPane.showMessageDialog(this, "Data already exists","Triggered", JOptionPane.WARNING_MESSAGE);
            System.out.println("unhandled user-defined exception ");
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
        SignoutjLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ManagestaffjPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        StemailjTextField = new javax.swing.JTextField();
        StnamejTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        brIDjComboBox = new javax.swing.JComboBox<>();
        StphonejTextField = new javax.swing.JTextField();
        StpassjPasswordField = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        Warning = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        StafftablejTable = new javax.swing.JTable();
        BranchjPanel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        BranchtablejTable = new javax.swing.JTable();
        br_adrjLabel = new javax.swing.JLabel();
        brAdrjTextField1 = new javax.swing.JTextField();
        Br_namejLabel = new javax.swing.JLabel();
        brNamejTextField2 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        MgrInfojPanel = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Mgr_namejLabel = new javax.swing.JLabel();
        Mgr_emailjLabel = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        MilkdetailsjPanel = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        MilktablejTable = new javax.swing.JTable();
        CustomerjPanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        CusttablejTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 408));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setText("Manage Staff");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 200, -1));

        jButton3.setText("Customers");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 200, -1));

        jButton4.setText("Branch");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 200, -1));

        jButton5.setText("Milk Details");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 200, -1));

        SignoutjLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dairymanagementsystem/icons8-logout-48.png"))); // NOI18N
        SignoutjLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SignoutjLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SignoutjLabelMouseClicked(evt);
            }
        });
        jPanel2.add(SignoutjLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 50, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon("D:\\Codes\\user.png")); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 80, 80));

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Profile");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setLayout(new javax.swing.OverlayLayout(jPanel3));

        ManagestaffjPanel.setBackground(new java.awt.Color(204, 204, 255));
        ManagestaffjPanel.setLayout(new java.awt.GridLayout(2, 1));

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));
        jPanel4.setMaximumSize(new java.awt.Dimension(2147483647, 200));
        jPanel4.setMinimumSize(new java.awt.Dimension(500, 200));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel7.setText("Manage Staff");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        jLabel1.setText("Name");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        StemailjTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                StemailjTextFieldFocusLost(evt);
            }
        });
        jPanel4.add(StemailjTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 110, -1));

        StnamejTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                StnamejTextFieldFocusLost(evt);
            }
        });
        jPanel4.add(StnamejTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 80, -1));

        jLabel2.setText("Email");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));

        jLabel3.setText("Password");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, -1, -1));

        jLabel5.setText("Phone");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, -1));

        jLabel6.setText("Branch ID");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        brIDjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        brIDjComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brIDjComboBoxActionPerformed(evt);
            }
        });
        jPanel4.add(brIDjComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, -1));

        StphonejTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                StphonejTextFieldFocusLost(evt);
            }
        });
        StphonejTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                StphonejTextFieldKeyReleased(evt);
            }
        });
        jPanel4.add(StphonejTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, 110, -1));
        jPanel4.add(StpassjPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 110, -1));

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 70, -1));

        Warning.setForeground(new java.awt.Color(204, 0, 51));
        jPanel4.add(Warning, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 150, 20));

        ManagestaffjPanel.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        StafftablejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID","Name", "Email", "Phone", "Branch Name","Click To Delete"
            }
        ));
        StafftablejTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StafftablejTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(StafftablejTable);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 0, 550, 221));

        ManagestaffjPanel.add(jPanel5);

        jPanel3.add(ManagestaffjPanel);

        BranchjPanel.setBackground(new java.awt.Color(255, 204, 204));
        BranchjPanel.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(570, 80));

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel8.setText("BRANCH");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(215, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(250, 250, 250))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel8)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        BranchjPanel.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBackground(new java.awt.Color(255, 204, 204));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        BranchtablejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Brach ID", "Branch Name", "Address", "Click to delete"
            }
        ));
        BranchtablejTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BranchtablejTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(BranchtablejTable);

        jPanel7.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 540, 210));

        br_adrjLabel.setText("Address :");
        jPanel7.add(br_adrjLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 70, -1));
        jPanel7.add(brAdrjTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 90, -1));

        Br_namejLabel.setText("Name :");
        jPanel7.add(Br_namejLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 50, -1));
        jPanel7.add(brNamejTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 90, -1));

        jButton7.setBackground(new java.awt.Color(204, 204, 255));
        jButton7.setText("ADD");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 70, -1));

        BranchjPanel.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel3.add(BranchjPanel);

        MgrInfojPanel.setLayout(new java.awt.BorderLayout());

        jPanel12.setBackground(new java.awt.Color(204, 204, 255));
        jPanel12.setPreferredSize(new java.awt.Dimension(548, 75));

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        jLabel11.setText("Manager Information");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(164, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(158, 158, 158))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel11)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        MgrInfojPanel.add(jPanel12, java.awt.BorderLayout.PAGE_START);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setText("PROFILE");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 51));
        jLabel13.setText("Name  :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 51));
        jLabel14.setText("Email  :");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setText("Update Password");

        Mgr_namejLabel.setText("Name");
        Mgr_namejLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mgr_namejLabelMouseClicked(evt);
            }
        });

        Mgr_emailjLabel.setText("Email");

        jButton8.setBackground(new java.awt.Color(204, 204, 255));
        jButton8.setText("UPDATE");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel16.setIcon(new javax.swing.ImageIcon("D:\\Downloads\\icons8-manager-80.png")); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8)
                            .addComponent(jLabel15))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Mgr_emailjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Mgr_namejLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(41, 41, 41))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(Mgr_namejLabel))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(Mgr_emailjLabel)))
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(151, 151, 151)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        MgrInfojPanel.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel3.add(MgrInfojPanel);

        MilkdetailsjPanel.setBackground(new java.awt.Color(204, 255, 204));
        MilkdetailsjPanel.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(204, 204, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(540, 80));

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel10.setText("MILK DETAILS");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(196, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(218, 218, 218))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(27, 27, 27))
        );

        MilkdetailsjPanel.add(jPanel10, java.awt.BorderLayout.PAGE_START);

        jPanel11.setBackground(new java.awt.Color(204, 255, 204));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MilktablejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Milk ID", "Type of Milk", "Amount/liter"
            }
        ));
        jScrollPane4.setViewportView(MilktablejTable);

        jPanel11.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 540, 210));

        MilkdetailsjPanel.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel3.add(MilkdetailsjPanel);

        CustomerjPanel.setBackground(new java.awt.Color(255, 255, 204));
        CustomerjPanel.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(540, 80));

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel9.setText("Customer Information");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jLabel9)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel9)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        CustomerjPanel.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel9.setBackground(new java.awt.Color(255, 255, 204));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CusttablejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Phone", "BranchID", "Address"
            }
        ));
        jScrollPane3.setViewportView(CusttablejTable);

        jPanel9.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 540, 210));

        CustomerjPanel.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel3.add(CustomerjPanel);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        ManagestaffjPanel.setVisible(false);
        BranchjPanel.setVisible(true);
        CustomerjPanel.setVisible(false);
        MilkdetailsjPanel.setVisible(false);
        MgrInfojPanel.setVisible(false);
        displayBranchintoTable();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        ManagestaffjPanel.setVisible(false);
        BranchjPanel.setVisible(false);
        CustomerjPanel.setVisible(false);
        MilkdetailsjPanel.setVisible(true);
        MgrInfojPanel.setVisible(false);
        displaymilkdetailsintoTable();
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ManagestaffjPanel.setVisible(true);
        BranchjPanel.setVisible(false);
        CustomerjPanel.setVisible(false);
        MilkdetailsjPanel.setVisible(false);
        MgrInfojPanel.setVisible(false);
        
        addBranchIDTOComboBox();
        displayStaffdatailtoTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ManagestaffjPanel.setVisible(false);
        BranchjPanel.setVisible(false);
        CustomerjPanel.setVisible(true);
        MilkdetailsjPanel.setVisible(false);
        MgrInfojPanel.setVisible(false);
        displayCustomerintoTable();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String name = StnamejTextField.getText();
        String email = StemailjTextField.getText();
        String phone = StphonejTextField.getText();
        String password = String.valueOf(StpassjPasswordField.getPassword());
        
        String brID = String.valueOf(brIDjComboBox.getSelectedItem());
        int id = Integer.parseInt(brID);
        
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || brID.isEmpty()){
            JOptionPane.showMessageDialog(this, "Empty Fields are not allowed");
        }
        else{
            
            addStaffToTable(name, email, password, phone, id);
        }
        displayStaffdatailtoTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void brIDjComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brIDjComboBoxActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_brIDjComboBoxActionPerformed

    private void SignoutjLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignoutjLabelMouseClicked
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirmation", JOptionPane.YES_NO_OPTION);
        System.out.println("Option :- " + choice);
        
        if(choice == 0){
            new FirstPage().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_SignoutjLabelMouseClicked

    private void StafftablejTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StafftablejTableMouseClicked
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(this, "Want to delete", "Confirmation", JOptionPane.YES_NO_OPTION);
        System.out.println("Option :- " + choice);
        if(choice == 0){
            int column = 0;
            int row = StafftablejTable.getSelectedRow();
            int staffID = Integer.parseInt(StafftablejTable.getModel().getValueAt(row, column).toString());
            
            deleteStafffromtable(staffID);
          
        }
    }//GEN-LAST:event_StafftablejTableMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String br_name = brNamejTextField2.getText();
        String br_address = brAdrjTextField1.getText();
        
        if( br_name.isEmpty() || br_address.isEmpty()){
            JOptionPane.showMessageDialog(this, " Empty Fields are not allowed");
        }
        else{          
             insertbranchintable(br_name, br_address);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void BranchtablejTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BranchtablejTableMouseClicked
        // TODO add your handling code here:
        
        int choice = JOptionPane.showConfirmDialog(this, "Want to delete", "Confirmation", JOptionPane.YES_NO_OPTION);
        System.out.println("Option :- " + choice);
        
        if(choice==0){
            int column = 0;
            int row = BranchtablejTable.getSelectedRow();
            int br_id = Integer.parseInt(BranchtablejTable.getModel().getValueAt(row, column).toString());
            
            deletebranchintoTable(br_id);
            displayBranchintoTable();
        }
        
    }//GEN-LAST:event_BranchtablejTableMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        MgrInfojPanel.setVisible(true);
        ManagestaffjPanel.setVisible(false);
        BranchjPanel.setVisible(false);
        CustomerjPanel.setVisible(false);
        MilkdetailsjPanel.setVisible(false);
        DisaplayManagerProfile();
        
        
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
//        String mgID = JOptionPane.showInputDialog(this, "Enter Manager ID whose Password to Change ");
        String pass = JOptionPane.showInputDialog(this, "Enter Password to Change ");
        
        try{
            
        if(pass.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please Enter Valid Password.");
        }
        else{
            updateManagerpassinTable(special_variable, pass);            
        }
        }catch(Exception ex){
            System.out.println("dairymanagementsystem.ManagerpanelUI.jButton8ActionPerformed()");
        }
//        try{
//            if(mgID.isEmpty() || pass.isEmpty()){
//            JOptionPane.showMessageDialog(this, "Empty Fields are not accepted.!");
//        }
//        else{
//             updateManagerpassinTable(Integer.parseInt(mgID), pass);  
//             
//                System.out.println(mgID);
//        }
//        }catch(Exception ex){
//            System.out.println("dairymanagementsystem.ManagerpanelUI.jButton6ActionPerformed()");
//        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void Mgr_namejLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mgr_namejLabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Mgr_namejLabelMouseClicked

    private void StnamejTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_StnamejTextFieldFocusLost
        // TODO add your handling code here:
           String PATTERN = "^[a-zA-Z]{3,18}";
        Pattern patt = Pattern.compile(PATTERN);
//        Matcher match = patt.matcher(managerNamejTextField.getText());
        String name = StnamejTextField.getText();

        if(! patt.matcher(name).matches()){
            JOptionPane.showMessageDialog(this, "Incorrect Name");
            Warning.setText("Naming is incorrect");
            StnamejTextField.setText("");
        }
        else{
            Warning.setText("");
        }
    }//GEN-LAST:event_StnamejTextFieldFocusLost

    private void StemailjTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_StemailjTextFieldFocusLost
        // TODO add your handling code here:
           String PATTERN = "^[a-zA-Z0-9]{3,25}[@][a-zA-Z0-9]{0,10}[.][a-zA-Z]{0,5}";
        Pattern patt = Pattern.compile(PATTERN);
//        Matcher match = patt.matcher(managerNamejTextField.getText());
        String name = StemailjTextField.getText();

        if(! patt.matcher(name).matches()){
//            JOptionPane.showMessageDialog(this, "Incorrect Email");
            Warning.setText("Email is incorrect");
            StemailjTextField.setText("");
        }
        else{
            Warning.setText("");
        }
    }//GEN-LAST:event_StemailjTextFieldFocusLost

    private void StphonejTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StphonejTextFieldKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_StphonejTextFieldKeyReleased

    private void StphonejTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_StphonejTextFieldFocusLost
        // TODO add your handling code here:
            String PATTERN = "[0-9]{10}";
        Pattern patt = Pattern.compile(PATTERN);
//        Matcher match = patt.matcher(managerNamejTextField.getText());
        String name = StphonejTextField.getText();

        if(! patt.matcher(name).matches()){
//            JOptionPane.showMessageDialog(this, "Incorrect Email");
            Warning.setText("Phone number is incorrect");
            StphonejTextField.setText("");
        }
        else{
            Warning.setText("");
        }
    }//GEN-LAST:event_StphonejTextFieldFocusLost

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
            java.util.logging.Logger.getLogger(ManagerpanelUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerpanelUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerpanelUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerpanelUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerpanelUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Br_namejLabel;
    private javax.swing.JPanel BranchjPanel;
    private javax.swing.JTable BranchtablejTable;
    private javax.swing.JPanel CustomerjPanel;
    private javax.swing.JTable CusttablejTable;
    private javax.swing.JPanel ManagestaffjPanel;
    private javax.swing.JPanel MgrInfojPanel;
    private javax.swing.JLabel Mgr_emailjLabel;
    private javax.swing.JLabel Mgr_namejLabel;
    private javax.swing.JPanel MilkdetailsjPanel;
    private javax.swing.JTable MilktablejTable;
    private javax.swing.JLabel SignoutjLabel;
    private javax.swing.JTable StafftablejTable;
    private javax.swing.JTextField StemailjTextField;
    private javax.swing.JTextField StnamejTextField;
    private javax.swing.JPasswordField StpassjPasswordField;
    private javax.swing.JTextField StphonejTextField;
    private javax.swing.JLabel Warning;
    private javax.swing.JTextField brAdrjTextField1;
    private javax.swing.JComboBox<String> brIDjComboBox;
    private javax.swing.JTextField brNamejTextField2;
    private javax.swing.JLabel br_adrjLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
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
    // End of variables declaration//GEN-END:variables
}
