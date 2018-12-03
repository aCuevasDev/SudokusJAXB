package com.acuevas.sudokus.persistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.acuevas.sudokus.exceptions.MyException;
import com.acuevas.sudokus.exceptions.MyException.StructErrors;
import com.acuevas.sudokus.model.sudokus.Sudokus;

public class SudokusDAO {

	private static SudokusDAO reader;
	private Sudokus sudokus = new Sudokus();

	private SudokusDAO() {

	}

	public static SudokusDAO getInstance() {
		if (reader == null)
			reader = new SudokusDAO();
		return reader;
	}

	public Sudokus readSudokusTXT(File file) {
		// TODO MAYBE ERASE THE REPLACE(" ")?
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
							throw new MyException(StructErrors.SUDOKUES_NOT_CORRECT);
						}
					} catch (MyException ex) {
						throw ex;
					} catch (Exception e) {
						// catch with generic Exception because no matter why if something goes wrong
						// here we cannot continue and the struct is bad.
						throw new MyException(StructErrors.PERSISTANCE_STRUCTURE);
					}
					Sudokus.Sudoku sudoku = new Sudokus.Sudoku(level, description, uncompletedSudoku, completedSudoku);
					sudokus.getSudokus().add(sudoku);
				}
			}
		} catch (IOException e) {
			// TODO CHANGE THIS INTO A VIEW CLASS, IF I CHANGE THE CONSOLE TO A GUI IT MUST
			// STILL WORK CHANGING ONLY VIEW CLASS
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

	public void writeIntoXML(Object JAXBElement, File file) {
		Marshaller marshaller;
		try {
			if ((marshaller = getMarshallerFromObj(JAXBElement)) != null) {
				try {
					marshaller.marshal(JAXBElement, file);
				} catch (JAXBException e) {
					System.err.println(e.getMessage());
				}
			}
		} catch (MyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads an XML and transforms it into classes using JAXB tech.
	 * 
	 * @param JAXBElement An object of the class you want to insert the data into.
	 * @param file        The file to read data from
	 * @return T Instance of the Object<T> with all the data.
	 */
	@SuppressWarnings("unchecked")
	public <T> T readFromXML(Object JAXBElement, File file) throws MyException {
		Unmarshaller unmarshaller;
		try {
			if (!file.exists())
				throw new MyException(StructErrors.FILE_NOT_FOUND);
			if ((unmarshaller = getUnmarshallerFromObj(JAXBElement)) != null)
				try {
//					if (!file.exists()) // If the file didn't exist the program throwed a IOException and crashed but I
					// couldn't put the IOException catch clause without throwing it manually, Don't
					// know why.
//						throw new IOException(new Throwable("Input Output error"));
					return (T) unmarshaller.unmarshal(file); // TODO THROWS EXCEPTION WHEN READS NON EXISTANT XML
//				}
//					catch (IOException ex) {
//					ex.printStackTrace();
//					View.printError(ex.getMessage());
//					throw new MyException(StructErrors.CRITICAL_FAILURE);
				} catch (JAXBException e) {
					e.printStackTrace();
				}
		} catch (MyException e) {
			e.printStackTrace();
			throw new MyException(StructErrors.CRITICAL_FAILURE);
		}
		// TODO FIX THIS SO THE STACKTRACE EXCEPTION WORKS PROPERLY
	}

	private Marshaller getMarshallerFromObj(Object object) throws MyException {
		try {
			return getMarshaller(getContext(object));
		} catch (JAXBException e) {
			throw new MyException(StructErrors.MARSHALLER_ERROR); // TODO GET N� OF THE LINE FROM CODE TO SHOW ON
			// EXCEPTION
		}
	}

	private Unmarshaller getUnmarshallerFromObj(Object object) throws MyException {
		try {
			return getUnmarshaller(getContext(object));
		} catch (JAXBException e) {
			throw new MyException(StructErrors.UNMARSHALLER_ERROR);
		}
	}

	private JAXBContext getContext(Object object) throws MyException {
		try {
			return JAXBContext.newInstance(object.getClass());
		} catch (JAXBException e) {
			throw new MyException(StructErrors.GETTING_CONTEXT_ERROR);
		}
	}

	private Unmarshaller getUnmarshaller(JAXBContext context) throws JAXBException {
		return context.createUnmarshaller();
	}

	private Marshaller getMarshaller(JAXBContext context) throws JAXBException {
		return context.createMarshaller();
	}
}
