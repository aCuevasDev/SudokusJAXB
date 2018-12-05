package com.acuevas.sudokus.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.acuevas.sudokus.model.sudokus.Sudokus.Sudoku;

/**
 * This class generates a pseudorandomised Sudoku, used in tests to maintain
 * program coherence. Not recommended to actually try to play them.
 *
 * @author Alex
 *
 */
public class SudokuGenerator {
	private static SudokuGenerator sudokuGenerator;
	final int maxLevel = 22;
	final int minLevel = 0;
	final int easyUntil = 4;
	final int mediumUntil = 7;
	final int hardUntil = 11;
	final int fiendishUntil = maxLevel;

	final Map<Integer, String> levelDescription = new HashMap<>();

	/**
	 * Constructor
	 */
	private SudokuGenerator() {
		for (int i = minLevel; i <= maxLevel; i++)
			if (i <= easyUntil)
				levelDescription.put(i, "Easy  (Level " + i + ")");
			else if (i <= mediumUntil)
				levelDescription.put(i, "Medium  (Level " + i + ")");
			else if (i <= hardUntil)
				levelDescription.put(i, "Hard (Level " + i + ")");
			else if (i <= fiendishUntil)
				levelDescription.put(i, "Fiendish (Level " + i + ")");
	}

	// needs to be a Singleton and not abstract to initiate the Map
	/**
	 * Gets the instance of SudokuGenerator.
	 * 
	 * @return
	 */
	public static SudokuGenerator getInstance() {
		if (sudokuGenerator == null)
			sudokuGenerator = new SudokuGenerator();
		return sudokuGenerator;
	}

	/**
	 * WARNING, Method generates a Sudoku which is entirely pseudorandomised, this
	 * means more often than not it'll be impossible to complete and the description
	 * and level values may make no sense with the sudoku itself.
	 *
	 * @return Sudoku... A randomised sudoku.
	 */
	public Sudoku getRandomSudoku() {
		final int size = 9 * 9;
		Random random = new Random();
		int level = random.nextInt(maxLevel);
		String description = levelDescription.get(level);
		String solved = "";
		String problem = "";

		for (int i = 0; i < size; i++)
			solved += random.nextInt(10);

		for (int i = 0; i < size; i++)
			if (random.nextBoolean())
				problem += solved.charAt(i);
			else
				problem += ".";
		return new Sudoku(level, description, problem, solved);
	}

}
