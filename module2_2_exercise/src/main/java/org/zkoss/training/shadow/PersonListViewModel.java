package org.zkoss.training.shadow;


import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.training.domain.GenderType;
import org.zkoss.training.domain.Person;
import org.zkoss.zul.ListModelList;

public class PersonListViewModel {
	
	private ListModelList<Person> people = new ListModelList<>();
	
	@Init
	public void init() {
		people.add(new Person(1L, "Peter", 23, GenderType.MALE, "", "/resources/img/user/user1.svg"));
		people.add(new Person(2L, "Paul", 32, GenderType.MALE, "", "/resources/img/user/user2.svg"));
		people.add(new Person(3L, "Maria", 45, GenderType.FEMALE, "", "/resources/img/user/user3.svg"));
	}

	@Command
	public void addPerson() {
		people.add(new Person(4L, "Newbie", 18, GenderType.UNKNOWN, "", "/resources/img/user/user4.svg"));
	}

	@Command
	public void removePerson(@BindingParam("person") Person person) {
		people.remove(person);
	}
	
	public ListModelList<Person> getPeople() {
		return people;
	}
}

