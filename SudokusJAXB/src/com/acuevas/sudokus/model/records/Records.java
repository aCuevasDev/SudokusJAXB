//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen.
// Generado el: 2018.11.28 a las 05:50:18 PM CET
//

package com.acuevas.sudokus.model.records;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.acuevas.sudokus.model.sudokus.Sudokus.Sudoku;
import com.acuevas.sudokus.model.users.Users.User;

/**
 * <p>
 * Clase Java para anonymous complex type.
 *
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="record">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="level" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="uncompletedSudoku" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="completedSudoku" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "record" })
@XmlRootElement(name = "records")
public class Records {

	protected List<Records.Record> record;

	/**
	 * Gets the value of the record property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the record property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getRecord().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Records.Record }
	 *
	 *
	 */
	public List<Records.Record> getRecords() {
		if (record == null)
			record = new ArrayList<Records.Record>();
		return this.record;
	}

	/**
	 * <p>
	 * Clase Java para anonymous complex type.
	 *
	 * <p>
	 * El siguiente fragmento de esquema especifica el contenido que se espera que
	 * haya en esta clase.
	 *
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}int"/>
	 *         &lt;element name="level" type="{http://www.w3.org/2001/XMLSchema}int"/>
	 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="uncompletedSudoku" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="completedSudoku" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 *
	 *
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "username", "time", "level", "description", "uncompletedSudoku",
			"completedSudoku" })
	public static class Record {

		@XmlElement(required = true)
		protected String username;
		protected int time;
		protected int level;
		@XmlElement(required = true)
		protected String description;
		@XmlElement(required = true)
		protected String uncompletedSudoku;
		@XmlElement(required = true)
		protected String completedSudoku;

		/**
		 * Empty Constructor
		 */
		public Record() {
		}

		/**
		 * Constructor with all its fields.
		 *
		 * @param username          String
		 * @param time              int
		 * @param level             int
		 * @param description       String
		 * @param uncompletedSudoku String
		 * @param completedSudoku   String
		 */
		public Record(String username, int time, int level, String description, String uncompletedSudoku,
				String completedSudoku) {
			this.username = username;
			this.time = time;
			this.level = level;
			this.description = description;
			this.uncompletedSudoku = uncompletedSudoku;
			this.completedSudoku = completedSudoku;
		}

		/**
		 * Constructor using an instanciated Sudoku
		 *
		 * @param username String
		 * @param time     int
		 * @param sudoku   Sudoku object
		 */
		public Record(String username, int time, Sudoku sudoku) {
			this.username = username;
			this.time = time;
			this.level = sudoku.getLevel();
			this.description = sudoku.getDescription();
			this.uncompletedSudoku = sudoku.getProblem();
			this.completedSudoku = sudoku.getSolved();
		}

		public boolean hasUser(User user) {
			return this.username.equals(user.getUsername());
		}

		/**
		 * Obtiene el valor de la propiedad username.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getUsername() {
			return username;
		}

		/**
		 * Define el valor de la propiedad username.
		 *
		 * @param value allowed object is {@link String }
		 *
		 */
		public void setUsername(String value) {
			this.username = value;
		}

		/**
		 * Obtiene el valor de la propiedad time.
		 *
		 */
		public int getTime() {
			return time;
		}

		/**
		 * Define el valor de la propiedad time.
		 *
		 */
		public void setTime(int value) {
			this.time = value;
		}

		/**
		 * Obtiene el valor de la propiedad level.
		 *
		 */
		public int getLevel() {
			return level;
		}

		/**
		 * Define el valor de la propiedad level.
		 *
		 */
		public void setLevel(int value) {
			this.level = value;
		}

		/**
		 * Obtiene el valor de la propiedad description.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * Define el valor de la propiedad description.
		 *
		 * @param value allowed object is {@link String }
		 *
		 */
		public void setDescription(String value) {
			this.description = value;
		}

		/**
		 * Obtiene el valor de la propiedad uncompletedSudoku.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getUncompletedSudoku() {
			return uncompletedSudoku;
		}

		/**
		 * Define el valor de la propiedad uncompletedSudoku.
		 *
		 * @param value allowed object is {@link String }
		 *
		 */
		public void setUncompletedSudoku(String value) {
			this.uncompletedSudoku = value;
		}

		/**
		 * Obtiene el valor de la propiedad completedSudoku.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getCompletedSudoku() {
			return completedSudoku;
		}

		/**
		 * Define el valor de la propiedad completedSudoku.
		 *
		 * @param value allowed object is {@link String }
		 *
		 */
		public void setCompletedSudoku(String value) {
			this.completedSudoku = value;
		}

	}

}
