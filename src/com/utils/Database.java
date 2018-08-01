package com.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Database {
    Connection con;

    Statement st;

    ResultSet rs;

    String s;

    int slno = 1;

    public Database() {
        try {
            createCon();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createCon() {
        try {
        	

        	//Class.forName("oracle.jdbc.OracleDriver");

        // url="jdbc:oracle:thin:@localhost:1521:XE";

        //	con=DriverManager.getConnection(url,"abcde","abcde");
        	
        	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    		con=DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=instance.mdb");
          
        	
        	st = con.createStatement();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String check(String usr, String pwd) {

        try {
        	st = con.createStatement();
            rs = st.executeQuery("select * from login where username='" + usr
                    + "' and password='" + pwd + "'");
            if (rs.next()) {
                s = "Valid";
            } else {
                s = "Invalid";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

   
    public boolean insertToLogin(Vector<String> vec) {
        boolean ret=false;
        try {
            st.executeUpdate("insert into login values('" + vec.get(0) + "','"
                    + vec.get(1) + "')");
            ret=true;
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    public boolean chkUserNme(String usrNme) {
        boolean result=true;
        try {
            rs = st.executeQuery("select username from login where username='" + usrNme + "'");
            if (rs.next()) {
                result=false;
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}