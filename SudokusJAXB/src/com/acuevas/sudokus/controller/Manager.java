package com.acuevas.sudokus.controller;

import java.io.File;

import com.acuevas.sudokus.model.sudokus.Sudokus;
import com.acuevas.sudokus.persistance.MyPersistanceManager;

public class Manager {
	private static final File XMLSUDOKUS = new File("sudokus.xml");
	private static final File TXTSUDOKUS = new File("sudokus.txt");
	private static Sudokus sudokus = new Sudokus();

	public static void main(String[] args) {
		MyPersistanceManager persistanceManager = MyPersistanceManager.getInstance();
		if (!XMLSUDOKUS.exists()) {
			sudokus = persistanceManager.readSudokusTXT(TXTSUDOKUS);
			persistanceManager.writeIntoXML(sudokus, XMLSUDOKUS);
		}
		persistanceManager.readFromXML(sudokus, XMLSUDOKUS);
		System.out.println("Done");

	}

}
