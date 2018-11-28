
package com.acuevas.sudokus.model.users;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.acuevas.sudokus.model.records.Records.Record;

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
 *         &lt;element name="user">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "", propOrder = { "user" })
@XmlRootElement(name = "users")
public class Users {

	protected List<Users.User> user;

	/**
	 * Gets the value of the user property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the user property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getUser().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Users.User }
	 * 
	 * 
	 */
	public List<Users.User> getUsers() {
		if (user == null) {
			user = new ArrayList<Users.User>();
		}
		return this.user;
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
	 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
	@XmlType(name = "", propOrder = { "name", "username", "password" })
	public static class User {

		@XmlElement(required = true)
		protected String name;
		@XmlElement(required = true)
		protected String username;
		@XmlElement(required = true)
		protected String password;
		@XmlAttribute(name = "code")
		protected String code;

		private List<Record> records;

		private long index;

//TODO JAXB CREATES A USER() THEN CALLS THE SETTERS TO INSERT THE FIELDS. SECURE CODE PERSISTANCE
		public User() {
			code = User.class.getSimpleName().substring(0, 3) + index++;
		}

		public User(String name, String username, String password) {
			this.name = name;
			this.username = username;
			this.password = password;
		}

		// TODO right now i use the username to identify

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((username == null) ? 0 : username.hashCode());
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
			User other = (User) obj;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			return true;
		}

		/**
		 * Obtiene el valor de la propiedad name.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getName() {
			return name;
		}

		/**
		 * Define el valor de la propiedad name.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setName(String value) {
			this.name = value;
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
		 * Obtiene el valor de la propiedad password.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * Define el valor de la propiedad password.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setPassword(String value) {
			this.password = value;
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
		private void setCode(String value) {
			this.code = value;
		}

		/**
		 * @return the records of sudokus
		 */
		public List<Record> getRecords() {
			return records;
		}

		/**
		 * @param recordsList the records to set
		 */
		public void setRecords(List<Record> recordsList) {
			this.records = recordsList;
		}
	}
}
