package org.zkoss.training.shop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.training.shop.model.bean.User;

public class UserDAO {
	private static final Map<Long, User> dbModel = 
			Collections.synchronizedMap(new HashMap<Long, User>());

	static {
		dbModel.put(1L, new User(1L, "ian", "ian", "user"));
		dbModel.put(2L, new User(2L, "zk", "zk", "admin"));
		dbModel.put(3L, new User(3L, "tom", "tom", "user"));
	}

	public List<User> findAll() {
		return new ArrayList<User>(dbModel.values());
	}

	public User findById(long id) {
		return dbModel.get(id);
	}

	public User findUserByName(String name) {
		for (User user : dbModel.values()) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}

}
