package com.acuevas.sudokus.controller;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.acuevas.sudokus.exceptions.MyException;
import com.acuevas.sudokus.model.sudokus.Sudokus;
import com.acuevas.sudokus.persistance.MyFileReader;

public class Manager {
	private static final File XMLSUDOKUS = new File("sudokus.xml");
	private static final File TXTSUDOKUS = new File("sudokus.txt");
	private static Sudokus sudokus;

	public static void main(String[] args) {

		MyFileReader reader = new MyFileReader();
		sudokus = reader.readSudokusFile(TXTSUDOKUS);

	}

	private static void writeIntoXML(Object JAXBElement, File file) {
		Marshaller marshaller;
		if ((marshaller = getMarshallerFromObj(sudokus)) != null) {
			try {
				marshaller.marshal(sudokus, XMLSUDOKUS);
			} catch (JAXBException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> Object readFromXML(Object JAXBElement, File file) {
		Unmarshaller unmarshaller;
		if ((unmarshaller = getUnmarshallerFromObj(JAXBElement)) != null)
			try {
				return (T) unmarshaller.unmarshal(file);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}

	private static Marshaller getMarshallerFromObj(Object object) throws MyException {
		try {
			return getMarshaller(getContext(object));
		} catch (JAXBException e) {
			throw new MyException("Error getting the marshaller");
			// TODO NOT HARDCODED MESSAGE

		}
	}

	private static Unmarshaller getUnmarshallerFromObj(Object object) {
		try {
			return getUnmarshaller(getContext(object));
		} catch (JAXBException e) {
			e.printStackTrace();
			System.out.println("Something went wrong.");
			System.out.println(e.getMessage());
		}
		return null;
	}

	private static JAXBContext getContext(Object object) throws JAXBException {
		return JAXBContext.newInstance(object.getClass());
	}

	private static Unmarshaller getUnmarshaller(JAXBContext context) throws JAXBException {
		return context.createUnmarshaller();
	}

	private static Marshaller getMarshaller(JAXBContext context) throws JAXBException {
		return context.createMarshaller();
	}

}
