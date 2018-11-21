package com.acuevas.sudokus.persistance;

import java.io.File;

import com.acuevas.sudokus.model.sudokus.Sudokus;

public interface IFileReader {

	public Sudokus readSudokusFile(File file);

}
