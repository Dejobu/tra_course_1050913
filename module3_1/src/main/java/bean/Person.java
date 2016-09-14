/* Person.java

	Purpose:
		
	Description:
		
	History:
		2010/7/30, Created by Ian Tsai

Copyright (C) 2010 Potix Corporation. All Rights Reserved.
 */
package bean;

/**
 * @author Ian Tsai
 * 
 */
public class Person {
	private int id;
	private String _firstName = "";
	private String _lastName = "";
	private boolean married = false;

	public Person() {
	}

	/**
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 */
	public Person(Integer id, String firstName, String lastName) {
		super();
		this.id = id;
		_firstName = firstName;
		_lastName = lastName;
	}

	/**
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 */
	public Person(Integer id, String firstName, String lastName, boolean married) {
		super();
		this.id = id;
		_firstName = firstName;
		_lastName = lastName;
		this.married = married;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public String getLastName() {
		return _lastName;
	}

	public String getFullName() {
		return _firstName + " " + _lastName;
	}
}
