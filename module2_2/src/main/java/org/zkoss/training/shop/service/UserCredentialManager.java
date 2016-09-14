package org.zkoss.training.shop.service;

import org.zkoss.training.shop.model.UserDAO;
import org.zkoss.training.shop.model.bean.User;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

public class UserCredentialManager {

	private static final String KEY_USER_MODEL = UserCredentialManager.class.getName() + "_MODEL";
	private UserDAO userDAO;
	private User user;

	private UserCredentialManager() {
		userDAO = new UserDAO();
	}

	public static UserCredentialManager getInstance() {
		return getInstance(Sessions.getCurrent());
	}

	public static UserCredentialManager getInstance(Session zkSession) {
		/**
		 * Alternative API HttpSession httpSession = (HttpSession) zkSession.getNativeSession(); 
		 * Session session = Executions.getCurrent().getDesktop().getSession(); 
		 * Session session = Executions.getCurrent().getSession();
		 * Session session = Sessions.getCurrent();
		 */
		synchronized (zkSession) {
			UserCredentialManager userModel = (UserCredentialManager) zkSession.getAttribute(KEY_USER_MODEL);
			if (userModel == null) {
				zkSession.setAttribute(KEY_USER_MODEL, userModel = new UserCredentialManager());
			}
			return userModel;
		}
	}

	public synchronized void login(String name, String password) {
		User tempUser = userDAO.findUserByName(name);
		if (tempUser != null && tempUser.getPassword().equals(password)) {
			user = tempUser;
		} else {
			user = null;
		}
	}

	public synchronized User find(String name) {
		return userDAO.findUserByName(name);
	}
	public synchronized void logOff() {
		this.user = null;
	}

	public synchronized User getUser() {
		return user;
	}

	public synchronized boolean isAuthenticated() {
		return user != null;
	}

}
