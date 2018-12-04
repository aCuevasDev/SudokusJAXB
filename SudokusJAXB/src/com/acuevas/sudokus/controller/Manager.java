package com.acuevas.sudokus.controller;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.acuevas.sudokus.exceptions.MyException;
import com.acuevas.sudokus.exceptions.RunnableExceptions;
import com.acuevas.sudokus.exceptions.RunnableExceptions.RunErrors;
import com.acuevas.sudokus.model.records.Records;
import com.acuevas.sudokus.model.records.Records.Record;
import com.acuevas.sudokus.model.sudokus.Sudokus;
import com.acuevas.sudokus.model.sudokus.Sudokus.Sudoku;
import com.acuevas.sudokus.model.users.Users;
import com.acuevas.sudokus.model.users.Users.User;
import com.acuevas.sudokus.persistance.SudokusDAO;
import com.acuevas.sudokus.views.InputAsker;
import com.acuevas.sudokus.views.View;
import com.acuevas.sudokus.views.View.Messages;

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
		try {
			SudokusDAO sudokusDAO = SudokusDAO.getInstance();
			if (!XMLSUDOKUS.exists()) {
				sudokus = sudokusDAO.readSudokusTXT(TXTSUDOKUS);
				sudokusDAO.writeIntoXML(sudokus, XMLSUDOKUS);
			}
			try {
				sudokus = sudokusDAO.readFromXML(sudokus, XMLSUDOKUS);
				records = sudokusDAO.readFromXML(records, XMLRECORDS);
				users = sudokusDAO.readFromXML(users, XMLUSERS);
			} catch (MyException e) {
				throw e;
			}
			System.out.println("Done");
		} catch (MyException e) {
			System.exit(0);
		}
	}

	public void createNewUser(String username, String name, String password) {
		User user = new User(name, username, password);
		loggedInUser = user;
		users.getUsers().add(user);
	}

	public boolean createNewUser() {
		// TODO SEPARATE INTO MORE METHODS AND TRY IN THE TEST, DOESN'T DETECT
		// REGISTERED USERS PROPERLY NOW
		String username = null;
		String name;
		String pswrd;
		String pswrd2;
		boolean error;

		// checking if user == null because if the username is already in use it gets
		// reseted, but if the user fails at matching passwords the program doesn't ask
		// for a username again.
		do {
			try {
				error = false;
				if (username == null) {
					View.printMessage(Messages.ASK_USERNAME, true);
					username = InputAsker.pedirCadena("");
				}
				if (!users.getUsers().contains(username)) {
					View.printMessage(Messages.ASK_NAME, true);
					name = InputAsker.pedirCadena("");
					do {
						try {
							error = false;
							View.printMessage(Messages.ASK_PASSWORD, true);
							pswrd = InputAsker.pedirCadena("");
							View.printMessage(Messages.ASK_PASSWORD, false);
							View.printMessage(Messages.AGAIN, true);
							pswrd2 = InputAsker.pedirCadena("");
							if (pswrd.equals(pswrd2)) {
								User user = new User(name, username, pswrd);
								loggedInUser = user;
								users.getUsers().add(user);
								return true;
							} else {
								throw new RunnableExceptions(RunErrors.PASSWORDS_DONT_MATCH);
							}
						} catch (RunnableExceptions e) {
							View.printError(e.getMessage());
							error = true;
						}
					} while (error);
				} else {
					throw new RunnableExceptions(RunErrors.USER_IN_USE);
				}
			} catch (RunnableExceptions e) {
				View.printError(e.getMessage());
				username = null;
				error = true;
			}
		} while (error);
		return false;
	}

	public void logIn() throws RunnableExceptions {
		boolean error = false;

		do {
			View.printMessage(Messages.ASK_USERNAME, true);
			String username = InputAsker.pedirCadena("");

			View.printMessage(Messages.ASK_PASSWORD, true);
			String password = InputAsker.pedirCadena("");

			User user1 = users.getUsers().stream().filter(user -> user.equals(username)).findFirst().orElse(null);
			if (user1 != null)
				if (user1.getPassword().equals(password))
					loggedInUser = user1;
				else
					throw new RunnableExceptions(RunErrors.USER_NOT_FOUND_OR_INCORRECT_PASSWORD);
			// TODO DOCUMENTATE WHY I USE THE SAME EXCEPTION ENUM (LESS HACKABLE)
			else
				throw new RunnableExceptions(RunErrors.USER_NOT_FOUND_OR_INCORRECT_PASSWORD);
		} while (error);
	}
// @formatter:off
	//TODO ERASE THIS
/*	@Deprecated
	private void insertRecordsIntoUser(Records records, User user) {
		List<Record> recordsList = records.getRecords().stream()
				.filter(record -> record.getUsername().equals(user.getUsername())).collect(Collectors.toList());
		user.setRecords(recordsList);
	} */
	// @formatter:on

	// TODO SEPARATE THIS INTO ANOTHER CLASS
	/**
	 * Gets a random Sudoku from the list which the user has not played yet.
	 * 
	 * @param user The user loggedIn
	 * @return a random Sudoku or null if the player has no sudokus left to play.
	 */
	public Sudoku getSudokusNotUsed(User user, Sudokus sudokus, Records records) {
//		List<Sudoku> sudokus1 = loggedInUser.getRecords().stream().filter(record -> !(record.getCode().equals(sudokus.getSudokus().stream().map(sudoku -> sudoku.getCode())))).collect(Collectors.toList());
		List<Sudoku> sudokusCompleted = records.getRecords().stream()
				.filter(record -> record.getUsername().equals(user.getUsername()))
				.map(record -> new Sudoku(record.getLevel(), record.getDescription(), record.getUncompletedSudoku(),
						record.getCompletedSudoku()))
				.collect(Collectors.toList());
		// @formatter:off
		
		Integer randomSkip = new Random().nextInt(sudokus.getSudokus().size()-1);
		return sudokus.getSudokus().stream()
				.filter(sudoku -> !sudokusCompleted.contains(sudoku))
				.unordered() // should  be always unordered, and thus return itself, 
							 // causing no performance losses i use it just to be sure.																								
				.skip(randomSkip)
				.findFirst().orElse(null);
		// @formatter:on

	}

	public void registerRecord(Sudoku sudoku) {
		boolean error;
		do {
			try {
				error = false;
				int time = InputAsker.pedirEntero("");
				if (time <= 0)
					throw new RunnableExceptions(RunErrors.WRONG_TIME);
			} catch (RunnableExceptions e) {
				e.printStackTrace();
//				View.printError(e.getMessage());
				error = true;
			}
		} while (error);
		Record record;

	}

	public void reload(Object object) throws MyException {
		// I'm not using switch because it only accepts constant keys.
		// I don't like using constants if i can avoid it tbh.
		try {
			SudokusDAO reader = SudokusDAO.getInstance();
			Class class1 = object.getClass();
			if (class1.equals(sudokus.getClass())) {
				sudokus = reader.readFromXML(sudokus, XMLSUDOKUS);
			} else if (class1.equals(records.getClass())) {
				records = reader.readFromXML(records, XMLRECORDS);
			} else if (class1.equals(users.getClass())) {
				users = reader.readFromXML(users, XMLUSERS);
			} else {
				throw new RunnableExceptions(RunErrors.NOT_SUPPORTED);
			}
		} catch (RunnableExceptions e) {
			View.printError(e.getMessage());
		}
		// TODO RELOAD THE INSTANCE OF 'Class' FROM THE XML (ex. records)
	}

	public void finishSudoku() {
		// TODO IMPLEMENT THIS WAY OF SENDING MESSAGES TO THE USER EVERYWHERE
		boolean finished = InputAsker.yesOrNo(View.Messages.FINISH_SUDOKU.toString());
		int time = InputAsker.pedirEntero(View.Messages.ASK_TIME.toString());

	}
}
