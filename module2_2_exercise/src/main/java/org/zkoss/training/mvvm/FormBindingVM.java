package org.zkoss.training.mvvm;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.training.shop.model.bean.User;

public class FormBindingVM {

	private User user1, user2;

	@Init
	public void init() {
		user1 = new User(2L, "account1", "password1");
		user2 = new User(3L, "account2", "password2");
	}

	@Command
	public void save() {
		System.out.println("save");
	}

	public User getUser1() {
		return user1;
	}

	public User getUser2() {
		return user2;
	}

}
