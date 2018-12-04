package tests;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.acuevas.sudokus.controller.Manager;
import com.acuevas.sudokus.model.records.Records;
import com.acuevas.sudokus.model.records.Records.Record;
import com.acuevas.sudokus.model.sudokus.Sudokus;
import com.acuevas.sudokus.model.sudokus.Sudokus.Sudoku;
import com.acuevas.sudokus.model.users.Users;
import com.acuevas.sudokus.model.users.Users.User;

class Tests {

	private static final File XMLSUDOKUS = new File("sudokus.xml");
	private static final File TXTSUDOKUS = new File("sudokus.txt");
	private static final File XMLRECORDS = new File("records.xml");
	private static final File XMLUSERS = new File("users.xml");
//Model
	private static Sudokus sudokus = new Sudokus();
	private static Users users = new Users();
	private static Records records = new Records();
//Controller	
	private static Manager manager = new Manager();
//Test objects
	private static Sudokus.Sudoku sudoku1;
	private static Sudokus.Sudoku sudoku2;
	private static Sudokus.Sudoku sudoku3;
	private static Users.User user;

	@BeforeAll
	static void init() {
		sudoku1 = new Sudoku(7, "Medium (Level 7)",
				"5......4129.7.4..83...1.....4...2...6.2...5.9...8...3.....8...34..6.9.5283......7",
				"578263941291754368364918725743592186682341579159876234925487613417639852836125497");
		sudoku2 = new Sudoku(6, "Medium (Level 6)",
				".3.....6..518.723.2.7...8.4...754.......3.......692...3.6...5.2.852.371..4.....9.",
				"839425167451867239267319854623754981794138625518692473376981542985243716142576398");
		sudoku3 = new Sudoku(6, "Medium (Level 6)",
				"..94.83..1.4...85.....23.......719.58.......42.158.......69.....56...4.8..78.52..",
				"729458361134769852568123749643271985875936124291584637482697513956312478317845296");

		sudokus.getSudokus().add(sudoku1);
		sudokus.getSudokus().add(sudoku2);
		sudokus.getSudokus().add(sudoku3);

		user = new User("testname", "test", "1234");
		users.getUsers().add(user);
	}

	/**
	 * Checks if the sudoku given by getSudokusNotUsed from Manager works properly
	 * assertFalse if the sudoku given is not the same as sudoku1, the one played by
	 * the user in any of the iterations.
	 */
	@Test
	void randomSudoku() {
//		Record(String username, int time, int level, String description, String uncompletedSudoku, String completedSudoku)
//		Sudoku(Integer level, String description, String unCompletedSudoku, String solvedSudoku)
		final int iterations = 15;

		Users.User user = new User("testname", "test", "1234");

		records.getRecords().add(new Record(user.getUsername(), 60, sudoku1));

		Manager manager = new Manager();
		for (int i = 0; i < iterations; i++) {
			Sudoku randSudokuNotUsed = manager.getSudokusNotUsed(user, sudokus, records);
			assertFalse((randSudokuNotUsed.equals(sudoku1)));
		}
	}

	@Test
	void randomSudokuWhenAllDone() {
		final int iterations = 15;

		records.getRecords().add(new Record(user.getUsername(), 60, sudoku1));
		records.getRecords().add(new Record(user.getUsername(), 60, sudoku2));
		records.getRecords().add(new Record(user.getUsername(), 60, sudoku3));

		for (int i = 0; i < iterations; i++) {
			Sudoku randSudokuNotUsed = manager.getSudokusNotUsed(user, sudokus, records);
			assertNull(randSudokuNotUsed);
		}
	}

	@Test
	void userAlreadyRegistered() {
		// TODO THIS PROPERLY BY SEPARATING METHODS IN MANAGER
		Users.User user2 = new User("testname", "test", "1234"); // same values as the user field
		assertTrue(manager.createNewUser());
		assertTrue(manager.createNewUser());
	}

}
