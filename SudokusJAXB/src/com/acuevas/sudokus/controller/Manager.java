package com.acuevas.sudokus.controller;

import java.io.File;

import com.acuevas.sudokus.model.sudokus.Sudokus;
import com.acuevas.sudokus.model.users.Users;
import com.acuevas.sudokus.persistance.MyPersistanceManager;

public class Manager {
	private static final File XMLSUDOKUS = new File("sudokus.xml");
	private static final File TXTSUDOKUS = new File("sudokus.txt");
	private static Sudokus sudokus = new Sudokus();
	private static Users users = new Users();

	public static void main(String[] args) {
		MyPersistanceManager persistanceManager = MyPersistanceManager.getInstance();
		if (!XMLSUDOKUS.exists()) {
			sudokus = persistanceManager.readSudokusTXT(TXTSUDOKUS);
			persistanceManager.writeIntoXML(sudokus, XMLSUDOKUS);
		}
		persistanceManager.readFromXML(sudokus, XMLSUDOKUS);
		System.out.println("Done");
	}

	private void createNewUser() {
		String user;
		String pswrd;
		String pswrd2;

		user = InputAsker.pedirCadena("Please, insert your desired username.");
		if (!users.getUsers().contains(user)) {

		} else
			System.out.println("user already in use.");
		// TODO CHANGE .EQUALS IN USER
	}

}
