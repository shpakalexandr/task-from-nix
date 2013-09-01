package com.nix.testtask.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nix.testtask.servlets.ModifyServlet;

/*
 * Класс DatabaseHelper разработан специально для работы с базой данных
 */
public class DatabaseHelper {
	public static final String USERS_TABLE = "app_users";
	public static final String ROLES_TABLE = "app_user_role";
	public static final String MESSAGE_TABLE = "app_messages";

	public static final String JDBC_DRIVER = "org.h2.Driver";
	// public static final String DB_URL = "jdbc:h2:mem:test";
	public static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";

	static final String USER = "sa";
	static final String PASS = "";

	static Connection conn = null;

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/*
 * 
 */

	/*
	 * Метод, создающий соединение с базой данных. Используется только в
	 * MessageSystemListener пакета com.nix.testtask.listeners
	 */
	public static void openConnetion() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Метод, закрывающий соединение с базой данных. Используется только в
	 * MessageSystemListener пакета com.nix.testtask.listeners
	 */
	public static void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	/*
	 * Метод, закрывающий Statement. Используется только в рамках данного класса
	 */
	private static void closeStatement(Statement stmt1) {
		if (stmt1 != null) {
			try {
				stmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/*
	 * Методы для создания таблиц базы данных в том случе если они не
	 * существуют. Используются эти методы в MessageSystemListener пакета
	 * com.nix.testtask.listeners
	 */

	/*
	 * Метод, создающий таблицу базы данных, предназначенную для хранения данных
	 * о ролях пользователей системы
	 */
	public static void createRolesTable() {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sqlsrc = "CREATE TABLE " + ROLES_TABLE
					+ "(ROLE_ID INT(10) UNSIGNED NOT NULL,"
					+ "ROLE_NAME VARCHAR(50) NOT NULL,"
					+ "PRIMARY KEY (ROLE_ID));";
			stmt.execute(sqlsrc);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
			System.out.println("Table is created???");
		}
	}

	/*
	 * Метод, создающий таблицу базы данных, предназначенную для хранения данных
	 * о пользователях системы
	 */
	public static void createUsersTable() {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sqlsrc = "CREATE TABLE " + USERS_TABLE
					+ "(USER_ID INT(10) UNSIGNED AUTO_INCREMENT NOT NULL,"
					+ "USER_FIRSTNAME VARCHAR(50) NOT NULL,"
					+ "USER_LASTNAME VARCHAR(50) NOT NULL,"
					+ "USER_NICKNAME VARCHAR(50) NOT NULL,"
					+ "USER_PASSWORD VARCHAR(50) NOT NULL,"
					+ "USER_ROLE_ID INT(10) UNSIGNED NOT NULL,"
					+ "USER_ENABLED tinyint(1) NOT NULL,"
					+ "FOREIGN KEY (USER_ROLE_ID) REFERENCES " + ROLES_TABLE
					+ " (ROLE_ID)," + "PRIMARY KEY (USER_ID));";
			stmt.execute(sqlsrc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
			System.out.println("Table is created???");
		}
	}

	/*
	 * Метод, создающий таблицу базы данных, предназначенную для хранения
	 * передаваемых и получаемых сообщений
	 */
	public static void createMessagesTable() {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sqlsrc = "CREATE TABLE " + MESSAGE_TABLE
					+ "(MESS_ID INT(10) UNSIGNED AUTO_INCREMENT NOT NULL, "
					+ "WHO_SEND_MESS_ID INT(10) UNSIGNED, "
					+ "WHO_SEND_MESS_NICKNAME VARCHAR(50) NOT NULL, "
					+ "WHO_RECEIVE_MESS_ID INT(10) UNSIGNED, "
					+ "WHO_RECEIVE_MESS_NICKNAME VARCHAR(50) NOT NULL, "
					+ "MESS_TITLE VARCHAR(50) NOT NULL, "
					+ "MESS_BODY VARCHAR(255) NOT NULL, "
					+ "DISP_MESS_SENDER BOOLEAN NOT NULL, "
					+ "DISP_MESS_RECEIVER BOOLEAN NOT NULL, "
					+ "FOREIGN KEY (WHO_SEND_MESS_ID) REFERENCES "
					+ USERS_TABLE + " (USER_ID),"
					+ "FOREIGN KEY (WHO_RECEIVE_MESS_ID) REFERENCES "
					+ USERS_TABLE + " (USER_ID)," + "PRIMARY KEY (MESS_ID));";
			stmt.execute(sqlsrc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
			System.out.println("Table is created???");
		}
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/*
	 * Методы предназначенные для добавления записей в таблицы. Используются эти
	 * методы в MessageSystemListener пакета com.nix.testtask.listeners и при
	 * редактировании данных
	 */

	/*
	 * Метод, предназначенный для добавления записей в таблицу ролей.
	 * Используется только в MessageSystemListener пакета
	 * com.nix.testtask.listeners
	 */
	public static void insertRole(int roleId, String roleName) {
		PreparedStatement stmt = null;
		try {
			String sqlsrc = "INSERT INTO " + ROLES_TABLE
					+ "(ROLE_ID, ROLE_NAME) values (?, ?);";
			stmt = conn.prepareStatement(sqlsrc);
			stmt.setInt(1, roleId);
			stmt.setString(2, roleName);
			stmt.addBatch();
			stmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
		}
	}

	/*
	 * Метод, предназначенный для добавления записей в таблицу пользователей
	 * системы. Используются в MessageSystemListener пакета
	 * com.nix.testtask.listeners, а также в AnalysisAndCreateServlet при
	 * создании нового пользователя.
	 */
	public static void insertUser(String userFirstName, String userLastName,
			String userNickName, String userPassword, int userRoleId) {
		PreparedStatement stmt = null;
		try {
			String sqlsrc = "INSERT INTO "
					+ USERS_TABLE
					+ "(user_firstname, user_lastname, user_nickname, user_password, user_role_id, user_enabled) values "
					+ "(?, ?, ?, ?, ?, 1);";
			stmt = conn.prepareStatement(sqlsrc);
			stmt.setString(1, userFirstName);
			stmt.setString(2, userLastName);
			stmt.setString(3, userNickName);
			stmt.setString(4, userPassword);
			stmt.setInt(5, userRoleId);
			stmt.addBatch();
			stmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
		}
	}

	/*
	 * Метод, предназначенный для добавления записей в таблицу сообщений.
	 * Используются в ...
	 */
	public static void insertMessage(String whosendmess, String whoreceivemess,
			String messagetitle, String messagebody) {
		PreparedStatement stmt = null;
		try {
			String sqlsrc = "INSERT INTO "
					+ MESSAGE_TABLE
					+ " (WHO_SEND_MESS_ID, WHO_SEND_MESS_NICKNAME, WHO_RECEIVE_MESS_ID,  WHO_RECEIVE_MESS_NICKNAME, MESS_TITLE, MESS_BODY, DISP_MESS_SENDER, DISP_MESS_RECEIVER) values "
					+ "(?, ?, ?, ?, ?, ?, true, true);";
			stmt = conn.prepareStatement(sqlsrc);
			stmt.setInt(1, getIDByUsername(whosendmess));
			stmt.setString(2, whosendmess);
			stmt.setInt(3, getIDByUsername(whoreceivemess));

			stmt.setString(4, whoreceivemess);

			stmt.setString(5, messagetitle);
			stmt.setString(6, messagebody);

			stmt.addBatch();
			stmt.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
		}
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/*
	 * Метод, предназначанный для проверки существования заданной таблици.
	 * Используются в MessageSystemListener пакета com.nix.testtask.listeners
	 */
	public static boolean tableExist(String tablename) {
		DatabaseMetaData md;
		ResultSet rs = null;
		boolean exist = false;
		try {
			md = conn.getMetaData();
			rs = md.getTables(conn.getCatalog(), null, tablename.toUpperCase(),
					null);
			exist = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}

	/*
	 * Метод, предназначанный для проверки существования заданного никнейма в
	 * базеданных. Используются в AnalysisAndSendServlet пакета
	 * com.nix.testtask.servlets
	 */
	public static boolean nicknameExist(String nickname) {
		ResultSet rs = null;
		Statement stmt = null;
		boolean exist = false;
		try {
			String sqlsrc = "SELECT user_nickname FROM " + USERS_TABLE + ";";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlsrc);

			while (rs.next()) {
				if (rs.getString("user_nickname").equals(nickname)) {
					exist = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
		}
		return exist;
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/*
	 * Методы, предназаначенные для обработки запросов к базе со стороны
	 * клиентов.
	 */

	/*
	 * Метод формирует и возвращает список всех пользователей системы из
	 * соответствующей таблицы базы данных. Используется в LoadUsersListServlet
	 * пакета com.nix.testtask.servlet
	 */
	public static List<User> selectAllUsers() {
		Statement stmt = null;
		List<User> userList = new ArrayList<User>();
		try {
			String sqlsrc = "SELECT * FROM " + USERS_TABLE + ";";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlsrc);

			while (rs.next()) {
				User u = new User();

				u.setUserid(rs.getInt("user_id"));
				u.setUserfirstname(rs.getString("user_firstname"));
				u.setUserlastname(rs.getString("user_lastname"));
				u.setUsernickname(rs.getString("user_nickname"));
				u.setUserpassword(rs.getString("user_password"));
				u.setUserroleid(rs.getInt("user_role_id"));
				u.setUserenabled(rs.getInt("user_enabled"));

				userList.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
		}
		return userList;
	}

	/*
	 * 
	 */
	public static List<Message> selectUserMessage(String type, String nickname) {
		Statement stmt = null;
		List<Message> messageList = new ArrayList<Message>();

		try {
			String sqlsrc = "SELECT * FROM " + MESSAGE_TABLE + ";";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlsrc);

			if (type.equals("sended")) {

				while (rs.next()) {
					if (nickname.equals(rs.getString("WHO_SEND_MESS_NICKNAME"))
							& (rs.getBoolean("DISP_MESS_SENDER"))) {
						Message mes = new Message();

						mes.setMessageid(rs.getInt("MESS_ID"));
						mes.setWhosendmessageid(rs.getInt("WHO_SEND_MESS_ID"));
						mes.setWhosendmessagenickname(rs
								.getString("WHO_SEND_MESS_NICKNAME"));
						mes.setWhoreceivemessageid(rs
								.getInt("WHO_RECEIVE_MESS_ID"));
						mes.setWhoreceivemessagenickname(rs
								.getString("WHO_RECEIVE_MESS_NICKNAME"));
						mes.setMessagetitle(rs.getString("MESS_TITLE"));
						mes.setMessagebody(rs.getString("MESS_BODY"));
						mes.setDisplaytosender(rs
								.getBoolean("DISP_MESS_SENDER"));
						mes.setDisplaytoreceiver(rs
								.getBoolean("DISP_MESS_RECEIVER"));

						messageList.add(mes);
					}
				}
			}

			if (type.equals("received")) {
				while (rs.next()) {

					if (nickname.equals(rs
							.getString("WHO_RECEIVE_MESS_NICKNAME"))
							& (rs.getBoolean("DISP_MESS_RECEIVER"))) {
						Message mes = new Message();

						mes.setMessageid(rs.getInt("MESS_ID"));
						mes.setWhosendmessageid(rs.getInt("WHO_SEND_MESS_ID"));
						mes.setWhosendmessagenickname(rs
								.getString("WHO_SEND_MESS_NICKNAME"));
						mes.setWhoreceivemessageid(rs
								.getInt("WHO_RECEIVE_MESS_ID"));
						mes.setWhoreceivemessagenickname(rs
								.getString("WHO_RECEIVE_MESS_NICKNAME"));
						mes.setMessagetitle(rs.getString("MESS_TITLE"));
						mes.setMessagebody(rs.getString("MESS_BODY"));
						mes.setDisplaytosender(rs
								.getBoolean("DISP_MESS_SENDER"));
						mes.setDisplaytoreceiver(rs
								.getBoolean("DISP_MESS_RECEIVER"));

						messageList.add(mes);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
		}
		return messageList;
	}

	/*
	 * Метод находит и возвращает пользователя с заданным ID. Используется в
	 * ModifyServlet пакета com.nix.testtask.servlets
	 */
	public static User getUserByID(int id) {
		Statement stmt = null;
		User u = new User();
		try {
			String sqlsrc = "SELECT * FROM " + USERS_TABLE + ";";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlsrc);

			while (rs.next()) {

				if (Integer.parseInt(rs.getString("user_id")) == id) {
					u.setUserid(rs.getInt("user_id"));
					u.setUserfirstname(rs.getString("user_firstname"));
					u.setUserlastname(rs.getString("user_lastname"));
					u.setUsernickname(rs.getString("user_nickname"));
					u.setUserpassword(rs.getString("user_password"));
					u.setUserroleid(rs.getInt("user_role_id"));
					u.setUserenabled(rs.getInt("user_enabled"));
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
		}
		return u;
	}

	/*
	 * Метод находит и возвращает пользователя с заданным ID. Используется в
	 * ModifyServlet пакета com.nix.testtask.servlets
	 */
	private static int getIDByUsername(String usernickname) {
		Statement stmt = null;
		int ID = 0;
		try {
			String sqlsrc = "SELECT user_id, user_nickname FROM " + USERS_TABLE
					+ ";";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlsrc);

			while (rs.next()) {

				if (rs.getString("user_nickname").equals(usernickname)) {
					ID = rs.getInt("user_id");
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
		}
		return ID;
	}

	/*
	 * Метод проверяет наличие идентичного никнейма в таблице пользователей базы
	 * данных. Используется в AnalisisAndCreateServlet и
	 * AnalisisAndModifyServlet пакета com.nix.testtask.servlets
	 */
	public static boolean analysisNickname(String nickname, String operation) {
		boolean analysisResult = false;
		Statement stmt = null;
		try {
			String sqlsrc = "SELECT user_id, user_nickname FROM " + USERS_TABLE
					+ ";";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlsrc);

			while (rs.next()) {
				String userNick = rs.getString("user_nickname");
				int userID = rs.getInt("user_id");

				System.out.println("" + userNick + userID);

				// Использование короткой схемы для проверки
				if (operation.equals("create") && userNick.equals(nickname)) {
					analysisResult = true;
				}

				// Использование короткой схемы для проверки
				if (operation.equals("modify") && userNick.equals(nickname)
						&& (userID != ModifyServlet.getIdModify())) {
					analysisResult = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
		}
		return analysisResult;
	}

	/*
	 * Метод изменяет запись в таблице пользователей, соответствующую
	 * определенному ID. Используется в AnalisisAndModifyServlet пакета
	 * com.nix.testtask.servlets
	 */
	public static void modifyUser(String userFirstName, String userLastName,
			String userNickName, String userPassword) {
		Statement stmt = null;
		try {
			String sqlsrc = "UPDATE " + USERS_TABLE + " SET user_firstname=\'"
					+ userFirstName + "\', user_lastname=\'" + userLastName
					+ "\', user_nickname=\'" + userNickName
					+ "\', user_password=\'" + userPassword
					+ "\' WHERE USER_ID=" + ModifyServlet.getIdModify() + ";";
			stmt = conn.createStatement();
			stmt.executeUpdate(sqlsrc);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
		}
	}

	/*
	 * Метод удаляет запись из таблицы пользователей, соответствующую
	 * определенному ID. Используется в DeleteServlet пакета
	 * com.nix.testtask.servlets
	 */
	public static void deleteUser(int ID) {
		Statement stmt = null;
		try {
			removeUserLinks(ID);
			String sqlsrc = "DELETE FROM " + USERS_TABLE + " WHERE USER_ID="
					+ ID + ";";
			stmt = conn.createStatement();
			stmt.executeUpdate(sqlsrc);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
		}
	}

	private static void removeUserLinks(int ID) {
		Statement stmt = null;
		try {
			String sqlsrc = "SELECT * FROM " + MESSAGE_TABLE + ";";
			String sqlup = "";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlsrc);
			while (rs.next()) {
				stmt = conn.createStatement();
				if (rs.getInt("WHO_SEND_MESS_ID") == ID) {
					sqlup = "UPDATE "
							+ MESSAGE_TABLE
							+ " SET WHO_SEND_MESS_ID=NULL, WHO_SEND_MESS_NICKNAME=\'"
							+ rs.getString("WHO_SEND_MESS_NICKNAME")
							+ "(Удален)\'" + " WHERE MESS_ID="
							+ rs.getInt("MESS_ID") + ";";
					stmt.executeUpdate(sqlup);
				}
				if (rs.getInt("WHO_RECEIVE_MESS_ID") == ID) {
					sqlup = "UPDATE "
							+ MESSAGE_TABLE
							+ " SET WHO_RECEIVE_MESS_ID=NULL, WHO_RECEIVE_MESS_NICKNAME=\'"
							+ rs.getString("WHO_RECEIVE_MESS_NICKNAME")
							+ "(Удален)\'" + " WHERE MESS_ID="
							+ rs.getInt("MESS_ID") + ";";
					stmt.executeUpdate(sqlup);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
		}
	}

	public static void removeFromSended(int ID) {
		Statement stmt = null;

		try {
			/*
			 * String sqlsrc = "SELECT WHO_SEND_ID FROM  " + MESSAGE_TABLE +
			 * " WHERE MESS_ID="+ ID + ";"; stmt = conn.createStatement();
			 * ResultSet rs = stmt.executeQuery(sqlsrc);
			 */

			String sqlsrc = "UPDATE " + MESSAGE_TABLE
					+ " SET DISP_MESS_SENDER=FALSE WHERE MESS_ID=" + ID + ";";
			stmt = conn.createStatement();
			stmt.executeUpdate(sqlsrc);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
		}
	}

	public static void removeFromReceived(int ID) {
		Statement stmt = null;

		try {
			/*
			 * String sqlsrc = "SELECT WHO_SEND_ID FROM  " + MESSAGE_TABLE +
			 * " WHERE MESS_ID="+ ID + ";"; stmt = conn.createStatement();
			 * ResultSet rs = stmt.executeQuery(sqlsrc);
			 */

			String sqlsrc = "UPDATE " + MESSAGE_TABLE
					+ " SET DISP_MESS_RECEIVER=FALSE WHERE MESS_ID=" + ID + ";";
			stmt = conn.createStatement();
			stmt.executeUpdate(sqlsrc);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
		}
	}
}
