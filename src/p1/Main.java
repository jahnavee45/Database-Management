package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// creating variable names same as in database table column names
		String name;
		String email;
		String mobile;

		// taking user input to perform an SQL operation
		System.out.println("PERFORM ANY GIVEN SQL OPERATION" + "\n1. Check sql connection" + "\n2. Insert a new member"
				+ "\n3. Delete a member" + "\n4. Update member details" + "\n5. Retrieve the table data");
		System.out.print("Choose an option: ");

		Scanner scan = new Scanner(System.in);
		int option = scan.nextInt();
		scan.nextLine();

		while (option != 6) {
			switch (option) {

			case 1: {

				// calling method to check SQL connection
				option = checkSQLConnection();
				break;
			}
			case 2: {

				System.out.print("\nEnter name: ");
				name = scan.nextLine();
				System.out.print("Enter email id: ");
				email = scan.nextLine();
				System.out.print("Enter mobile: ");
				mobile = scan.nextLine();

				// calling method to perform insert operation
				option = insertQuery(name, email, mobile);
				break;
			}
			case 3: {

				System.out.println("\nEnter email id: ");
				email = scan.nextLine();

				// calling method to perform delete operation
				option = deleteQuery(email);
				break;
			}
			case 4: {

				System.out
						.println("\nWhat do you want to update? " + "\n1. Name " + "\n2. Email" + "\n3. Mobile number");
				System.out.print("Choose an option: ");
				int option2 = scan.nextInt();
				scan.nextLine();

				option = updateQuery(option2);
				break;
			}
			case 5: {

				// calling method to display the table data
				option = retrieveQuery();
				break;
			}
			}
		}

		System.out.print("\n-----------SESSION TERMINATED----------");
		scan.close();

	}

	// CASE 5
	private static int retrieveQuery() {

		try {
			// connect to database
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database_name", "user_name", "password");

			// execute SQL query
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery("select * from registration");

			// display table in a proper table format
			System.out.println("\nRegistration Table Data");
			System.out.printf("%-15s %-20s %-15s%n", "Name", "Email", "Mobile");
			System.out.println("---------------------------------------------------");
			while (result.next()) {
				System.out.printf("%-15s %-20s %-15s%n", result.getString(1), result.getString(2), result.getString(3));
			}

			// close database connection
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// check if user wants to continue or not
		Scanner scan = new Scanner(System.in);
		System.out.println("\nDo you want to continue? yes/no");
		String flag = scan.nextLine();

		if (flag.equals("yes")) {
			System.out.print("Choose an option again: ");
			int option = scan.nextInt();
			scan.close();
			return option;
		}
		scan.close();
		return 6;
	}

	// CASE 4
	private static int updateQuery(int option) {

		Scanner scan = new Scanner(System.in);

		try {

			String name;
			String email;
			String mobile;

			// connect to database
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database_name", "user_name", "password");

			// execute SQL query
			Statement stm = con.createStatement();

			switch (option) {
			case 1: {
				System.out.println("Enter email id of the member: ");
				email = scan.nextLine();
				System.out.println("Enter modified name: ");
				name = scan.nextLine();
				stm.executeUpdate("Update registration set name = '" + name + "' where email = '" + email + "'");
				break;
			}
			case 2: {
				System.out.println("Enter name of the member: ");
				name = scan.nextLine();
				System.out.println("Enter modified email id: ");
				email = scan.nextLine();
				stm.executeUpdate("Update registration set email = '" + email + "' where name = '" + name + "'");
				break;
			}
			case 3: {
				System.out.println("Enter email id: ");
				email = scan.nextLine();
				System.out.println("Enter new mobile number: ");
				mobile = scan.nextLine();
				stm.executeUpdate("Update registration set mobile = '" + mobile + "' where email = '" + email + "'");
				break;
			}
			}
			System.out.println("Updation complete!");

			// close database connection
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// check if user wants to continue or not
		System.out.println("\nDo you want to continue? yes/no");
		String flag = scan.nextLine();

		if (flag.equals("yes")) {
			System.out.print("Choose an option again: ");
			int option1 = scan.nextInt();
			scan.close();
			return option1;
		}
		scan.close();
		return 6;

	}

	// CASE 3
	private static int deleteQuery(String email) {

		try {
			// connect to database
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database_name", "user_name", "password");

			// execute SQL query
			Statement stm = con.createStatement();
			stm.executeUpdate("delete from registration where email = '" + email + "' ");
			System.out.println("Deletion complete!");

			// close database connection
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// check if user wants to continue or not
		Scanner scan = new Scanner(System.in);
		System.out.println("\nDo you want to continue? yes/no");
		String flag = scan.nextLine();

		if (flag.equals("yes")) {
			System.out.print("Choose an option again: ");
			int option = scan.nextInt();
			scan.close();
			return option;
		}
		scan.close();
		return 6;

	}

	// CASE 2
	private static int insertQuery(String name, String email, String mobile) {

		try {
			// connect to database
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database_name", "user_name", "password");

			// execute SQL query
			Statement stm = con.createStatement();
			stm.executeUpdate("insert into registration values('" + name + "', '" + email + "', '" + mobile + "')");
			System.out.println("Insertion complete!");

			// close database connection
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// check if user wants to continue or not
		Scanner scan = new Scanner(System.in);
		System.out.println("\nDo you want to continue? yes/no");
		String flag = scan.nextLine();

		if (flag.equals("yes")) {
			System.out.print("Choose an option again: ");
			int option = scan.nextInt();
			scan.close();
			return option;
		}
		scan.close();
		return 6;

	}

	// CASE 1
	private static int checkSQLConnection() {

		try {
			// connect to database
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database_name", "user_name", "password");
			System.out.println("\n" + con);
			System.out.println("SQL connection successful!");

			// close database connection
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// check if user wants to continue or not
		Scanner scan = new Scanner(System.in);
		System.out.println("\nDo you want to continue? yes/no");
		String flag = scan.nextLine();

		if (flag.equals("yes")) {
			System.out.print("Choose an option again: ");
			int option = scan.nextInt();
			scan.close();
			return option;
		}
		scan.close();
		return 6;

	}

}
