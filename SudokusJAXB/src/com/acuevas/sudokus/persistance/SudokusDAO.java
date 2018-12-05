package com.acuevas.sudokus.persistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.acuevas.sudokus.exceptions.CriticalException;
import com.acuevas.sudokus.exceptions.CriticalException.StructErrors;
import com.acuevas.sudokus.model.sudokus.Sudokus;

/**
 *
 * @author Alex
 *
 */
public class SudokusDAO {
	// All the data is managed in the DAO.

	private static SudokusDAO sudokusDAO;

	private SudokusDAO() {

	}

	/**
	 * Gets the instance of this class, if none exists then it creates one.
	 *
	 * @return SudokusDAO
	 */
	public static SudokusDAO getInstance() {
		if (sudokusDAO == null)
			sudokusDAO = new SudokusDAO();
		return sudokusDAO;
	}

	/**
	 * Reads and implements the Sudoku from a plain text file with the format
	 * // @formatter:off
	 * % (level) (description)
	 * (uncompleted sudoku)
	 * (completed sudoku)
	 * // @formatter:on
	 *
	 * @param file the File from where to read the sudokus.
	 * @return Sudokus, an object with a List of Sudoku.
	 * @throws CriticalException when the program has problems reading the data.
	 */
	public Sudokus readSudokusTXT(File file) throws CriticalException {
		Sudokus sudokus = new Sudokus();
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

			while ((line = bufferedReader.readLine()) != null)
				if (line.substring(0, 1).equals("%")) {
					line = line.replace(" ", ""); // to remove different spacings between sudokus
					try {
						level = Integer.parseInt(line.substring(1, 2));
						description = line.substring(2, line.length());
						uncompletedSudoku = bufferedReader.readLine();
						completedSudoku = bufferedReader.readLine();
						if (uncompletedSudoku.length() != 81 | completedSudoku.length() != 81)
							throw new CriticalException(StructErrors.SUDOKUES_NOT_CORRECT);
					} catch (CriticalException ex) {
						throw ex;
					} catch (Exception e) {
						// catch with generic Exception because no matter why if something goes wrong
						// here we cannot continue and the struct is bad.
						CriticalException exception = new CriticalException(StructErrors.PERSISTANCE_STRUCTURE);
						exception.setStackTrace(e.getStackTrace());
						throw exception;
					}
					Sudokus.Sudoku sudoku = new Sudokus.Sudoku(level, description, uncompletedSudoku, completedSudoku);
					sudokus.getSudokus().add(sudoku);
				}
		} catch (IOException e) {
			CriticalException exception = new CriticalException(StructErrors.FILE_NOT_FOUND);
			exception.setStackTrace(e.getStackTrace());
			throw exception;

		} catch (CriticalException e1) {
			throw e1;
		} finally {
			try {
				bufferedReader.close();
			} catch (Exception e) {
				CriticalException exception = new CriticalException(StructErrors.BUFFER_NOT_CLOSABLE);
				exception.setStackTrace(e.getStackTrace());
				throw exception;
			}
		}
		return sudokus;
	}

	/**
	 * Writes the JAXBElement object into and XML file using JAXB tech. (marshaller)
	 *
	 * @param jaxbElement an instance of an object compatible with JAXB tech.
	 * @param file        the File where you want to save data into (overrides old
	 *                    content).
	 * @throws CriticalException when the program can't save the data
	 */
	public void writeIntoXML(Object jaxbElement, File file) throws CriticalException {
		Marshaller marshaller;
		try {
			if ((marshaller = getMarshallerFromObj(jaxbElement)) != null)
				try {
					marshaller.marshal(jaxbElement, file);
				} catch (JAXBException e) {
					System.err.println(e.getMessage());
				}
		} catch (CriticalException e) {
			file.delete();
			e.printStackTrace();
			throw new CriticalException(StructErrors.CRITICAL_FAILURE);
		}
	}

	/**
	 * Reads an XML and transforms it into classes using JAXB tech.
	 *
	 * @param jaxbElement An instance of the class you want to insert the data into.
	 * @param file        The file to read data from.
	 * @return T Instance of the Object<T> with all the data.
	 * @throws CriticalException when the program can't read the data.
	 */
	@SuppressWarnings("unchecked")
	public <T> T readFromXML(Object jaxbElement, File file) throws CriticalException {
		Unmarshaller unmarshaller;
		try {
			if (!file.exists())
				throw new CriticalException(StructErrors.FILE_NOT_FOUND); // Had to throw manually, couldn't implement
																			// the IOException catch clause
			if ((unmarshaller = getUnmarshallerFromObj(jaxbElement)) != null)
				if (unmarshaller.unmarshal(file) != null)
					return (T) unmarshaller.unmarshal(file);
				else
					return null;
		} catch (CriticalException | JAXBException e) {
			e.printStackTrace();
			throw new CriticalException(StructErrors.CRITICAL_FAILURE);
		}
		return null;
		// TODO THROWS 2 FILE NOT FOUND EXCEPTIONS, ASK MAR
	}

	/**
	 * Gets the marshaller from an instance
	 *
	 * @param jaxbElement
	 * @return
	 * @throws CriticalException
	 */
	private Marshaller getMarshallerFromObj(Object jaxbElement) throws CriticalException {
		try {
			return getMarshaller(getContext(jaxbElement));
		} catch (JAXBException e) {
			throw new CriticalException(StructErrors.MARSHALLER_ERROR);
		}
	}

	private Unmarshaller getUnmarshallerFromObj(Object jaxbElement) throws CriticalException {
		try {
			return getUnmarshaller(getContext(jaxbElement));
		} catch (JAXBException e) {
			throw new CriticalException(StructErrors.UNMARSHALLER_ERROR);
		}
	}

	private JAXBContext getContext(Object jaxbElement) throws CriticalException {
		try {
			return JAXBContext.newInstance(jaxbElement.getClass());
		} catch (JAXBException e) {
			throw new CriticalException(StructErrors.GETTING_CONTEXT_ERROR);
		}
	}

	private Unmarshaller getUnmarshaller(JAXBContext context) throws JAXBException {
		return context.createUnmarshaller();
	}

	private Marshaller getMarshaller(JAXBContext context) throws JAXBException {
		return context.createMarshaller();
	}
}
