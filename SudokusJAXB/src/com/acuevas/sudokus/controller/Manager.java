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
		if (!XMLSUDOKUS.exists()) {
			MyFileReader reader = new MyFileReader();
			sudokus = reader.readSudokusFile(TXTSUDOKUS);
			writeIntoXML(sudokus, XMLSUDOKUS);
		}

	}

	private static void writeIntoXML(Object JAXBElement, File file) {
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

	@SuppressWarnings("unchecked")
	private static <T> Object readFromXML(Object JAXBElement, File file) {
		Unmarshaller unmarshaller;
		try {
			if ((unmarshaller = getUnmarshallerFromObj(JAXBElement)) != null)
				try {
					return (T) unmarshaller.unmarshal(file);
				} catch (JAXBException e) {
					e.printStackTrace();
				}
		} catch (MyException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Marshaller getMarshallerFromObj(Object object) throws MyException {
		try {
			return getMarshaller(getContext(object));
		} catch (JAXBException e) {
			throw new MyException(MyException.MARSHALLERERROR); // TODO GET Nº OF THE LINE FROM CODE TO SHOW ON
																// EXCEPTION
		}
	}

	private static Unmarshaller getUnmarshallerFromObj(Object object) throws MyException {
		try {
			return getUnmarshaller(getContext(object));
		} catch (JAXBException e) {
			throw new MyException(MyException.UNMARSHALLERERROR);
		}
	}

	private static JAXBContext getContext(Object object) throws MyException {
		try {
			return JAXBContext.newInstance(object.getClass());
		} catch (JAXBException e) {
			throw new MyException(MyException.GETTINGCONTEXTERROR);
		}
	}

	private static Unmarshaller getUnmarshaller(JAXBContext context) throws JAXBException {
		return context.createUnmarshaller();
	}

	private static Marshaller getMarshaller(JAXBContext context) throws JAXBException {
		return context.createMarshaller();
	}

}
