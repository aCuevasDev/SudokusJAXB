//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2018.11.28 a las 05:50:00 PM CET 
//

package com.acuevas.sudokus.model.sudokus;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
 *         &lt;element name="sudoku">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="problem" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="solved" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="level" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                 &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "", propOrder = { "sudoku" })
@XmlRootElement(name = "sudokus")
public class Sudokus {

	protected List<Sudokus.Sudoku> sudoku;

	/**
	 * Gets the value of the sudoku property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the sudoku property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSudoku().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Sudokus.Sudoku }
	 * 
	 * 
	 */
	public List<Sudokus.Sudoku> getSudokus() {
		if (sudoku == null) {
			sudoku = new ArrayList<Sudokus.Sudoku>();
		}
		return this.sudoku;
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
	 *         &lt;element name="problem" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="solved" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *       &lt;/sequence>
	 *       &lt;attribute name="level" type="{http://www.w3.org/2001/XMLSchema}int" />
	 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "problem", "solved" })
	public static class Sudoku {

		@XmlElement(required = true)
		protected String problem;
		@XmlElement(required = true)
		protected String solved;
		@XmlAttribute(name = "level")
		protected Integer level;
		@XmlAttribute(name = "description")
		protected String description;

		public Sudoku() {
		}

		public Sudoku(Integer level, String description, String unCompletedSudoku, String solvedSudoku) {
			super();
			this.problem = unCompletedSudoku;
			this.solved = solvedSudoku;
			this.level = level;
			this.description = description;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((level == null) ? 0 : level.hashCode());
			result = prime * result + ((problem == null) ? 0 : problem.hashCode());
			result = prime * result + ((solved == null) ? 0 : solved.hashCode());
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Sudoku other = (Sudoku) obj;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (level == null) {
				if (other.level != null)
					return false;
			} else if (!level.equals(other.level))
				return false;
			if (problem == null) {
				if (other.problem != null)
					return false;
			} else if (!problem.equals(other.problem))
				return false;
			if (solved == null) {
				if (other.solved != null)
					return false;
			} else if (!solved.equals(other.solved))
				return false;
			return true;
		}

		/**
		 * Obtiene el valor de la propiedad problem.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getProblem() {
			return problem;
		}

		/**
		 * Define el valor de la propiedad problem.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setProblem(String value) {
			this.problem = value;
		}

		/**
		 * Obtiene el valor de la propiedad solved.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getSolved() {
			return solved;
		}

		/**
		 * Define el valor de la propiedad solved.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setSolved(String value) {
			this.solved = value;
		}

		/**
		 * Obtiene el valor de la propiedad level.
		 * 
		 * @return possible object is {@link Integer }
		 * 
		 */
		public Integer getLevel() {
			return level;
		}

		/**
		 * Define el valor de la propiedad level.
		 * 
		 * @param value allowed object is {@link Integer }
		 * 
		 */
		public void setLevel(Integer value) {
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

	}

}
