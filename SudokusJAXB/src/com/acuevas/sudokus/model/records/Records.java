//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2018.11.22 a las 03:14:38 PM CET 
//

package com.acuevas.sudokus.model.records;

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
 *         &lt;element name="record">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="uncompletedSudoku" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="completedSudoku" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="code" type="{http://www.w3.org/2001/XMLSchema}string" />
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
	public List<Records.Record> getRecord() {
		if (record == null) {
			record = new ArrayList<Records.Record>();
		}
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
	 *         &lt;element name="uncompletedSudoku" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="completedSudoku" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *       &lt;/sequence>
	 *       &lt;attribute name="code" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "username", "uncompletedSudoku", "completedSudoku" })
	public static class Record {

		@XmlElement(required = true)
		protected String username;
		@XmlElement(required = true)
		protected String uncompletedSudoku;
		@XmlElement(required = true)
		protected String completedSudoku;
		@XmlAttribute(name = "code")
		protected String code;

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

		/**
		 * Obtiene el valor de la propiedad code.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCode() {
			return code;
		}

		/**
		 * Define el valor de la propiedad code.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCode(String value) {
			this.code = value;
		}

	}

}
