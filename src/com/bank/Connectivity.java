package com.bank;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connectivity {
	public static Connection connect() {
   	 Connection cn=null;
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");

           cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db","root","");
           if(cn==null){ 
           	System.out.println("Failed to connect...!");
           } else{
           	System.out.println("Connected...!"+cn); 
           }

          // System.out.println("Connected successfully");

       } catch (Exception e) {
           e.printStackTrace();
       }
		return cn;
   }

}
