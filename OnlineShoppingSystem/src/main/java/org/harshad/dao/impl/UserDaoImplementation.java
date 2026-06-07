package org.harshad.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.harshad.dao.UserDao;
import org.harshad.entity.User;
import org.harshad.exceptions.UserNotFoundException;
import org.harshad.exceptions.UserNotInsertedException;

public class UserDaoImplementation implements UserDao {
	Connection con;
	PreparedStatement ps;
	
	public UserDaoImplementation() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac_170", "root", "root");
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	@Override
	public boolean isAuthenticated(User obj) throws UserNotFoundException  {
		try (PreparedStatement psAuthenticate = con.prepareStatement("select * from users where username=? and password=?")) {
			psAuthenticate.setString(1, obj.getUsername());
			psAuthenticate.setString(2, obj.getPassword());
			
			System.out.println(obj.getUsername());
			System.out.println(obj.getPassword());
			try (ResultSet res = psAuthenticate.executeQuery()) {
				if(res.next()) {
					return true;
				}
				return false;
			}
		} catch (SQLException e) {
			throw new UserNotFoundException("User not found"+e.getMessage());
		}
	}

	@Override
	public boolean registerUser(User obj) throws UserNotInsertedException{
		try (PreparedStatement psInsertUser = con.prepareStatement("Insert into users (username,password,name,email,city) values(?,?,?,?,?)")) {
			psInsertUser.setString(1, obj.getUsername());
			psInsertUser.setString(2, obj.getPassword());
			psInsertUser.setString(3, obj.getName());
			psInsertUser.setString(4, obj.getEmail());
			psInsertUser.setString(5, obj.getCity());
			
			int isAddedOrNot=psInsertUser.executeUpdate();
			if(isAddedOrNot!=0)
				return true;
			return false;
		} catch (SQLException e) {
			throw new UserNotInsertedException("enable to insert the user "+e.getMessage());
		}
	}
}
