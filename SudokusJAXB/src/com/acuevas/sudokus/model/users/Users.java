
package com.acuevas.sudokus.model.users;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.acuevas.sudokus.controller.InputAsker;
import com.acuevas.sudokus.exceptions.RunnableException;
import com.acuevas.sudokus.userInteraction.UserInteraction;

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
		if (user == null)
			user = new ArrayList<Users.User>();
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

		public User() {
		}

		public User(String name, String username, String password) {
			this.name = name;
			this.username = username;
			this.password = password;
		}

		/**
		 * Changes the password of this User, asks the password twice to check they're
		 * correct.
		 */
		public void changePassword() {
			boolean error;
			do {
				error = false;
				String psword;
				String psword2;
				UserInteraction.printMessage(UserInteraction.Messages.ASK_PASSWORD, true);
				psword = InputAsker.pedirCadena("");
				if (psword.equals(password)) {
					UserInteraction.printMessage(UserInteraction.Messages.NEW_PASWORD, true);
					psword = InputAsker.pedirCadena("");
					UserInteraction.printMessage(UserInteraction.Messages.NEW_PASWORD, false);
					UserInteraction.printMessage(UserInteraction.Messages.AGAIN, true);
					psword2 = InputAsker.pedirCadena("");
					if (psword.equals(psword2)) {
						password = psword;
						UserInteraction.printMessage(UserInteraction.Messages.PSWRD_CHANGED, true);
					} else
						UserInteraction.printError(RunnableException.RunErrors.PASSWORDS_DONT_MATCH.toString());
				} else
					UserInteraction.printError(UserInteraction.Messages.INCORRECT_PASSWORD.toString());
			} while (error);
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

	}

}
