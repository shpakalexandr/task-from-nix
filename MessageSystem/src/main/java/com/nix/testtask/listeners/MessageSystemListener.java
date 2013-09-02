package com.nix.testtask.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.nix.testtask.db.DatabaseHelper;

public class MessageSystemListener implements ServletContextListener {

	static final String JDBC_DRIVER = "org.h2.Driver";
	static final String DB_URL = "jdbc:h2:mem:test";

	// Database credentials
	static final String USER = "sa";
	static final String PASS = "";

	public void contextInitialized(ServletContextEvent sce) {
		DatabaseHelper.openConnetion();

		String usersTable = DatabaseHelper.USERS_TABLE;
		String rolesTable = DatabaseHelper.ROLES_TABLE;
		String messageTable = DatabaseHelper.MESSAGE_TABLE;

		if (!DatabaseHelper.tableExist(rolesTable)) {
			DatabaseHelper.createRolesTable();
			DatabaseHelper.insertRole(1, "ROLE_ADMIN");
			DatabaseHelper.insertRole(2, "ROLE_USER");

		}
		if (!DatabaseHelper.tableExist(usersTable)) {
			DatabaseHelper.createUsersTable();
			DatabaseHelper.insertUser("Alexandr", "Shpak", "admin", "123", 1);
		}
		if (!DatabaseHelper.tableExist(messageTable)) {
			DatabaseHelper.createMessagesTable();
		}

	}

	public void contextDestroyed(ServletContextEvent sce) {
		DatabaseHelper.closeConnection();
	}
}