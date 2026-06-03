package com.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("database.properties"));
			String url = props.getProperty("db.url");
			String user = props.getProperty("db.user");
			String password = props.getProperty("db.password");

			try (Connection con = DriverManager.getConnection(url, user, password); Scanner sc = new Scanner(System.in);) {
				System.out.println("1. Add User ");
				System.out.println("2. List all users based on city");
				System.out.println("3. Update password for user");
				System.out.println("4. Display user information based on user name");
				System.out.println("5.Exit");

				int choice;
				do {
					System.out.println("Enter the choice");
					choice = sc.nextInt();
					switch (choice) {

					case 1:
						addUser(con, sc);
						break;
					case 2:
						System.out.println("Enter the city to list the users");
						String city = sc.next();
						listUsersByCity(con, city);
						break;
					case 3:
						updatePassword(con, sc);
						break;
					case 4:
						displayuserInformation(con, sc);
						break;
					case 5:
						System.out.println("Exiting");
						break;
					default:
						System.out.println("Please enter the valid choice(1-4)");
					}
				} while (choice != 5);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static void addUser(Connection con, Scanner sc) throws SQLException {
		try (PreparedStatement pstmt = con
				.prepareStatement("Insert into users (username,password,name,email,city)values(?,?,?,?,?)");) {
			System.out.println("Enter the username ");
			String userName = sc.next();
			System.out.println("Enter the password ");
			String password = sc.next();
			System.out.println("Enter the name ");
			String name = sc.next();
			System.out.println("Enter the email");
			String email = sc.next();
			System.out.println("Enter the city");
			String city = sc.next();

			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, city);

			int result = pstmt.executeUpdate();
			if (result != -1) {
				System.out.println("Record inserted");
			} else {
				System.out.println("Problem in inserting record");
			}
		}
	}

	protected static void listUsersByCity(Connection con, String city) {
			try (PreparedStatement pstmt = con.prepareStatement("Select * from users where city=?")) {
				pstmt.setString(1, city);
				try (ResultSet result = pstmt.executeQuery()) {
					while (result.next()) {
						System.out.println(result.getString(2) + " " + result.getString(3) + " " + result.getString(4));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	protected static void updatePassword(Connection con, Scanner sc) {
		System.out.println("Enter the username to update the password ");
		String username = sc.next();
			try (PreparedStatement pstmt = con.prepareStatement("select * from users where username=?")) {
				pstmt.setString(1, username);
				try(ResultSet result = pstmt.executeQuery();){
				if (result.next()) {
					System.out.println("Enter the new password");
					String newPassword = sc.next();
					try (PreparedStatement pstmt1 = con.prepareStatement("update users set password =? where username=?")) {
						pstmt1.setString(1, newPassword);
						pstmt1.setString(2, username);
						int isUpdatedOrNot = pstmt1.executeUpdate();
						if (isUpdatedOrNot != 0) {
							System.out.println("Password updated successfully");
						}
					}
				} else
					System.out.println("User does not exsit");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	protected static void displayuserInformation(Connection con, Scanner sc) {
		System.out.println("Enter the username");
		String username = sc.next();
		try(PreparedStatement pstmt = con.prepareStatement("select * from users where username=?");) {
			pstmt.setString(1, username);
			try (ResultSet result = pstmt.executeQuery()) {
				if (result.next()) {
					System.out.println(result.getString(1) + " " + result.getString(2) + " " + result.getString(3) + " "
							+ result.getString(4) + " " + result.getString(5));

				} else
					System.out.println("User does not exsit");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
