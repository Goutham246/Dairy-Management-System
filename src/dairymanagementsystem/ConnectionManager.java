/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dairymanagementsystem;
import java.sql.*;
/**
 *
 * @author Akhil
 */
public class ConnectionManager {
    public static Connection con = null;
    
    public static String DriverTest(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(java.lang.ClassNotFoundException e){
            return "Class Not Found Exception!";
        }
        return("Driver found");
    }
    
    public static Connection getConnection(){
        String userid = "root"; //uid
        String password = "root"; //password
        String hostname = "localhost";
        String dbName = "dms";
        String url = "jdbc:mysql://" + hostname + ":3306/" + dbName + "?autoReconnect=true&useSSL=false";
        con = null;
        try{
            con = DriverManager.getConnection(url, userid, password);
        } catch(SQLException ex){
            System.out.println("Error:- "+ex);
            con = null;
        }
        return(con);
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        //String ret = DB_Part.DriverTest();
        //System.out.println(ret);
        
        Connection connection = ConnectionManager.getConnection();
        if(connection!=null)
            System.out.println("Succesfully connected!");
        else
            System.out.println("Error while connecting to Databases!");
    }
}
