package com.acuevas.sudokus.controller;

import java.io.File;

import com.acuevas.sudokus.persistance.MyFileReader;

public class Manager {
	private static final File XMLSUDOKUS = new File("sudokus.xml");
	private static final File TXTSUDOKUS = new File("sudokus.txt");

	public static void main(String[] args) {
		MyFileReader read = new MyFileReader();
		read.readFile(TXTSUDOKUS);
	}

}
