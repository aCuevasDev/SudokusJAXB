package com.acuevas.sudokus.persistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.acuevas.sudokus.exceptions.Errors;
import com.acuevas.sudokus.exceptions.MyException;
import com.acuevas.sudokus.model.sudokus.Sudokus;

public class MyFileReader implements IFileReader {

	Sudokus sudokus = new Sudokus();

	@Override
	public Sudokus readSudokusFile(File file) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line;
			Integer level;
			String description;
			String uncompletedSudoku;
			String completedSudoku;

			while ((line = bufferedReader.readLine()) != null) {
				if (line.substring(0, 1).equals("%")) {
					line = line.replace(" ", "");
					try {
						level = Integer.parseInt(line.substring(1, 2));
						description = line.substring(2, line.length());
						uncompletedSudoku = bufferedReader.readLine();
						completedSudoku = bufferedReader.readLine();
						if (uncompletedSudoku.length() != 80 + 1 | completedSudoku.length() != 80 + 1) {
							// 80+1 because length starts counting on 1 instead of 0.
							throw new MyException(Errors.SUDOKUESNOTCORRECT);
						}
					} catch (MyException ex) {
						throw ex;
					} catch (Exception e) {
						// catch with generic Exception because no matter why if something goes wrong
						// here we cannot continue and the struct is bad.
						throw new MyException(Errors.PERSISTANCESTRUCTURE);
					}

					Sudokus.Sudoku sudoku = new Sudokus.Sudoku(level, description, uncompletedSudoku, completedSudoku);
					sudokus.getSudokus().add(sudoku);
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (MyException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		return sudokus;

	}
}
