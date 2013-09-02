package com.nix.testtask.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nix.testtask.db.DatabaseHelper;

public class AnalysisAndModifyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private List<String> Message = new ArrayList<String>();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Message.clear();

		String userfirstname = req.getParameter("moduserfirstname");
		String userlastname = req.getParameter("moduserlastname");
		String usernickname = req.getParameter("modusernickname");
		String userpassword = req.getParameter("moduserpassword");

		if (userlastname.equals("") | userfirstname.equals("")
				| usernickname.equals("") | userpassword.equals("")) {
			if (userlastname.equals("")) {
				Message.add("Поле \"Фамилия\" является пустым. Заполните его!");
				req.setAttribute("modlastnameerrorstar", "*");
			}
			if (userfirstname.equals("")) {
				Message.add("Поле \"Имя\" является пустым. Заполните его!");
				req.setAttribute("modfirstnameerrorstar", "*");
			}
			if (usernickname.equals("")) {
				Message.add("Поле \"Никнейм\" является пустым. Заполните его!");
				req.setAttribute("modnicknameerrorstar", "*");
			}
			if (userpassword.equals("")) {
				Message.add("Поле \"Пароль\" является пустым. Заполните его!");
				req.setAttribute("modpassworderrorstar", "*");
			}

			req.setAttribute("ErrMsgs", Message);

			req.setAttribute("moduserfirstname", userfirstname);
			req.setAttribute("moduserlastname", userlastname);
			req.setAttribute("modusernickname", usernickname);
			req.setAttribute("moduserpassword", userpassword);

			RequestDispatcher dispatcher = req
					.getRequestDispatcher("/ModifyUser");
			dispatcher.forward(req, resp);
			return;
		} else {
			if (DatabaseHelper.analysisNickname(usernickname, "modify")) {
				Message.add("Пользователь с таким никнеймом уже существует!");
				req.setAttribute("modnicknameerrorstar", "*");

				req.setAttribute("ErrMsgs", Message);

				req.setAttribute("moduserfirstname", userfirstname);
				req.setAttribute("moduserlastname", userlastname);
				req.setAttribute("modusernickname", usernickname);
				req.setAttribute("moduserpassword", userpassword);
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("/ModifyUser");
				dispatcher.forward(req, resp);
				return;
			} else {
				DatabaseHelper.modifyUser(userfirstname, userlastname,usernickname, userpassword);
				ModifyServlet.setIdModify(0);
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("/UsersList");
				dispatcher.forward(req, resp);
				return;
			}
		}
	}
}
