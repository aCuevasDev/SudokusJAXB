package com.acuevas.sudokus.controller;

import java.io.File;

import com.acuevas.sudokus.exceptions.RunnableExceptions;
import com.acuevas.sudokus.exceptions.RunnableExceptions.RunErrors;
import com.acuevas.sudokus.model.records.Records;
import com.acuevas.sudokus.model.sudokus.Sudokus;
import com.acuevas.sudokus.model.users.Users;
import com.acuevas.sudokus.model.users.Users.User;
import com.acuevas.sudokus.persistance.MyPersistanceManager;

public class Manager {
	private static final File XMLSUDOKUS = new File("sudokus.xml");
	private static final File TXTSUDOKUS = new File("sudokus.txt");
	private static final File XMLRECORDS = new File("records.xml");
	private static final File XMLUSERS = new File("users.xml");

	private static Sudokus sudokus = new Sudokus();
	private static Users users = new Users();
	private static Records records = new Records();
	private User loggedInUser;

	public static void main(String[] args) {
		MyPersistanceManager persistanceManager = MyPersistanceManager.getInstance();
		if (!XMLSUDOKUS.exists()) {
			sudokus = persistanceManager.readSudokusTXT(TXTSUDOKUS);
			persistanceManager.writeIntoXML(sudokus, XMLSUDOKUS);
		}
		sudokus = persistanceManager.readFromXML(sudokus, XMLSUDOKUS);
		records = persistanceManager.readFromXML(records, XMLRECORDS);
		users = persistanceManager.readFromXML(users, XMLUSERS);

		System.out.println("Done");
	}

	private void createNewUser() {
		String username = null;
		String name;
		String pswrd;
		String pswrd2;
		boolean error;

		// checking if user == null because if the username is already in use it gets
		// reseted, but if the user fails at matching passwords the program doesn't ask
		// for a username again.
		do {
			error = false;
			if (username == null)
				username = InputAsker.pedirCadena("Please, insert your desired username.");
			if (!users.getUsers().contains(username)) {
				name = InputAsker.pedirCadena("Now insert your name.");
				pswrd = InputAsker.pedirCadena("Insert your password.");
				pswrd2 = InputAsker.pedirCadena("Insert your password again.");
				if (pswrd.equals(pswrd2)) {
					User user = new User();
				} else {
					System.err.println("Passwords doesn't match.");
					// TODO CREATE EXCEPTIONS TO CONTROL PASSWORDS&USERINUSE
					error = true;
				}
			} else {
				System.out.println("user already in use.");
				username = null;
				error = true;
			}
		} while (error);
	}

	private User logIn() throws RunnableExceptions {
		boolean error = false;

		do {
			String username = InputAsker.pedirCadena("Insert your username.");
			String password = InputAsker.pedirCadena("Insert your password.");
			User user1 = users.getUsers().stream().filter(user -> user.equals(username)).findFirst().orElse(null);
			if (user1 != null)
				if (user1.getPassword().equals(password))
					return user1;
				else
					throw new RunnableExceptions(RunErrors.USERNOTFOUNDORINCORRECTPASSWORD);
			else
				throw new RunnableExceptions(RunErrors.USERNOTFOUNDORINCORRECTPASSWORD);
		} while (error);
	}
}
