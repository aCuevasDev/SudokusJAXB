package com.acuevas.sudokus.controller;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.acuevas.sudokus.exceptions.CriticalException;
import com.acuevas.sudokus.exceptions.RunnableException;
import com.acuevas.sudokus.exceptions.RunnableException.RunErrors;
import com.acuevas.sudokus.model.Ranking;
import com.acuevas.sudokus.model.records.Records;
import com.acuevas.sudokus.model.records.Records.Record;
import com.acuevas.sudokus.model.sudokus.Sudokus;
import com.acuevas.sudokus.model.sudokus.Sudokus.Sudoku;
import com.acuevas.sudokus.model.users.Users;
import com.acuevas.sudokus.model.users.Users.User;
import com.acuevas.sudokus.persistance.SudokusDAO;
import com.acuevas.sudokus.userInteraction.UserInteraction;
import com.acuevas.sudokus.userInteraction.UserInteraction.Messages;

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
			} catch (CriticalException e) {
				throw e;
			}
			System.out.println("Done");
		} catch (CriticalException e) {
			System.exit(0);
		}
	}

	public void createNewUser(String username, String name, String password) {
		User user = new User(name, username, password);
		loggedInUser = user;
		users.getUsers().add(user);
	}

	/**
	 * Checks if the given username is in use in the given Users.
	 * 
	 * @param username String, the username.
	 * @param users    A Users object with all the registered users.
	 * @return
	 */
	public boolean usernameInUse(String username, Users users) {
		return users.getUsers().stream().anyMatch(user -> user.equals(new User(username)));
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
		do
			try {
				error = false;
				if (username == null) {
					UserInteraction.printMessage(Messages.ASK_USERNAME, true);
					username = InputAsker.pedirCadena("");
				}
				if (!usernameInUse(username, users)) {
					UserInteraction.printMessage(Messages.ASK_NAME, true);
					name = InputAsker.pedirCadena("");
					do
						try {
							error = false;
							UserInteraction.printMessage(Messages.ASK_PASSWORD, true);
							pswrd = InputAsker.pedirCadena("");
							UserInteraction.printMessage(Messages.ASK_PASSWORD, false);
							UserInteraction.printMessage(Messages.AGAIN, true);
							pswrd2 = InputAsker.pedirCadena("");
							if (pswrd.equals(pswrd2)) {
								User user = new User(name, username, pswrd);
								loggedInUser = user;
								users.getUsers().add(user);
								return true;
							} else
								throw new RunnableException(RunErrors.PASSWORDS_DONT_MATCH);
						} catch (RunnableException e) {
							UserInteraction.printError(e.getMessage());
							error = true;
						}
					while (error);
				} else
					throw new RunnableException(RunErrors.USER_IN_USE);
			} catch (RunnableException e) {
				UserInteraction.printError(e.getMessage());
				username = null;
				error = true;
			}
		while (error);
		return false;
	}

	public void logIn() throws RunnableException {
		boolean error = false;

		do {
			UserInteraction.printMessage(Messages.ASK_USERNAME, true);
			String username = InputAsker.pedirCadena("");

			UserInteraction.printMessage(Messages.ASK_PASSWORD, true);
			String password = InputAsker.pedirCadena("");

			User user1 = users.getUsers().stream().filter(user -> user.equals(username)).findFirst().orElse(null);
			if (user1 != null)
				if (user1.getPassword().equals(password))
					loggedInUser = user1;
				else
					throw new RunnableException(RunErrors.USER_NOT_FOUND_OR_INCORRECT_PASSWORD);
			// TODO DOCUMENTATE WHY I USE THE SAME EXCEPTION ENUM (LESS HACKABLE)
			else
				throw new RunnableException(RunErrors.USER_NOT_FOUND_OR_INCORRECT_PASSWORD);
		} while (error);
	}

	// TODO SEPARATE THIS INTO ANOTHER CLASS
	/**
	 * Gets a random Sudoku from the list which the user has not played yet.
	 *
	 * @param user The user loggedIn
	 * @return a random Sudoku or null if the player has no sudokus left to play.
	 */
	public Sudoku getSudokusNotUsed(User user, Sudokus sudokus, Records records) {
		List<Sudoku> sudokusCompleted = records.getRecords().stream()
				.filter(record -> record.getUsername().equals(user.getUsername()))
				.map(record -> new Sudoku(record.getLevel(), record.getDescription(), record.getUncompletedSudoku(),
						record.getCompletedSudoku()))
				.collect(Collectors.toList());
		// @formatter:off

		int randomSkip = new Random().nextInt(sudokus.getSudokus().size()-1);
		return sudokus.getSudokus().stream()
				.filter(sudoku -> !sudokusCompleted.contains(sudoku))
				.unordered() // should  be always unordered, and thus return itself,
							 // causing no performance losses, I use it just to be sure.
				.skip(randomSkip)
				.findFirst().orElse(null);
		// @formatter:on

	}

	/**
	 * Registers the given sudoku into the records of the User
	 * 
	 * @param user   the User saving the data.
	 * @param sudoku the Sudoku to save.
	 */
	public void registerRecord(User user, Sudoku sudoku) {
		// TODO TEST THIS
		boolean error;
		do
			try {
				error = false;
				boolean finished = InputAsker.yesOrNo(UserInteraction.Messages.FINISH_SUDOKU.toString());
				int time = InputAsker.pedirEntero(UserInteraction.Messages.ASK_TIME.toString());
				if (time > 0)
					records.getRecords().add(new Record(loggedInUser.getUsername(), time, sudoku));
				else
					throw new RunnableException(RunErrors.WRONG_TIME);
			} catch (RunnableException e) {
				UserInteraction.printError(e.getMessage());
				error = true;
			}
		while (error);
		Record record;

	}

	/**
	 * Reloads the given object, reading data from its XML
	 * 
	 * @param jaxbElement an instance of a JAXB object.
	 * @throws CriticalException when the program can't save the data.
	 */
	public void reload(Object jaxbElement) throws CriticalException {
		// I'm not using switch because it only accepts constant keys.
		try {
			SudokusDAO reader = SudokusDAO.getInstance();
			Class<? extends Object> class1 = jaxbElement.getClass();
			if (class1.equals(sudokus.getClass()))
				sudokus = reader.readFromXML(sudokus, XMLSUDOKUS);
			else if (class1.equals(records.getClass()))
				records = reader.readFromXML(records, XMLRECORDS);
			else if (class1.equals(users.getClass()))
				users = reader.readFromXML(users, XMLUSERS);
			else
				throw new RunnableException(RunErrors.NOT_SUPPORTED);
		} catch (RunnableException e) {
			UserInteraction.printError(e.getMessage());
		}
	}

	/**
	 * Saves the given object into its XML
	 * 
	 * @param jaxbElement an instance of a JAXB object.
	 * @throws CriticalException when the program can't save the data.
	 */
	public void store(Object jaxbElement) throws CriticalException {
		// I'm not using switch because it only accepts constant keys.
		try {
			SudokusDAO writter = SudokusDAO.getInstance();
			Class<? extends Object> class1 = jaxbElement.getClass();
			if (class1.equals(sudokus.getClass()))
				writter.writeIntoXML(jaxbElement, XMLSUDOKUS);
			else if (class1.equals(records.getClass()))
				writter.writeIntoXML(jaxbElement, XMLRECORDS);
			else if (class1.equals(users.getClass()))
				writter.writeIntoXML(jaxbElement, XMLUSERS);
			else
				throw new RunnableException(RunErrors.NOT_SUPPORTED);
		} catch (RunnableException e) {
			UserInteraction.printError(e.getMessage());
		}
	}

	/**
	 * Gets a List with all the rankings sorted by the mean time of the players.
	 * Note: it needs records from manager to have data, else will return null
	 * 
	 * @param users
	 * @return
	 */
	public List<Ranking> getSortedRankings(Users users, Records records) {
		// TODO TEST THIS
		List<Ranking> rankings = users.getUsers().stream().filter(user -> user.hasPlayed(records))
				.map(user -> new Ranking(user, records)).collect(Collectors.toList());
		Collections.sort(rankings);
		return rankings;
	}

	/**
	 * @return the sudokus
	 */
	public static Sudokus getSudokus() {
		return sudokus;
	}

	/**
	 * @param sudokus the sudokus to set
	 */
	public static void setSudokus(Sudokus sudokus) {
		Manager.sudokus = sudokus;
	}

	/**
	 * @return the users
	 */
	public static Users getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public static void setUsers(Users users) {
		Manager.users = users;
	}

	/**
	 * @return the records
	 */
	public static Records getRecords() {
		return records;
	}

	/**
	 * @param records the records to set
	 */
	public static void setRecords(Records records) {
		Manager.records = records;
	}

}
