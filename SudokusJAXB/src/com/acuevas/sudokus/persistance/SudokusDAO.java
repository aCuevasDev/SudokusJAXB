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
import com.acuevas.sudokus.model.records.Records;
import com.acuevas.sudokus.model.sudokus.Sudokus;
import com.acuevas.sudokus.model.users.Users;

/**
 *
 * @author Alex
 *
 */
public class SudokusDAO {
	// All the data is managed in the DAO.

	private static final File XMLSUDOKUS = new File("sudokus.xml");
	private static final File TXTSUDOKUS = new File("sudokus.txt");
	private static final File XMLRECORDS = new File("records.xml");
	private static final File XMLUSERS = new File("users.xml");

	private static Sudokus sudokus = new Sudokus();
	private static Users users = new Users();
	private static Records records = new Records();

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
	 * @param file the File from where to read the sudokus
	 * @return Sudokus, an object with a List of Sudoku
	 * @throws CriticalException Exceptions that cannot permit the program to continue
	 * @see CriticalException
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
						throw new CriticalException(StructErrors.PERSISTANCE_STRUCTURE);
					}
					Sudokus.Sudoku sudoku = new Sudokus.Sudoku(level, description, uncompletedSudoku, completedSudoku);
					sudokus.getSudokus().add(sudoku);
				}
		} catch (IOException e) {
			// TODO CHANGE THIS INTO A VIEW CLASS, IF I CHANGE THE CONSOLE TO A GUI IT MUST
			// STILL WORK CHANGING ONLY VIEW CLASS
			System.err.println(e.getMessage());
		} catch (CriticalException e) {
			throw e;
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
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
				return (T) unmarshaller.unmarshal(file);
		} catch (CriticalException | JAXBException e) {
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
			throw new CriticalException(StructErrors.MARSHALLER_ERROR); // TODO GET Nº OF THE LINE FROM CODE TO SHOW ON
			// EXCEPTION
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
		SudokusDAO.sudokus = sudokus;
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
		SudokusDAO.users = users;
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
		SudokusDAO.records = records;
	}
}
