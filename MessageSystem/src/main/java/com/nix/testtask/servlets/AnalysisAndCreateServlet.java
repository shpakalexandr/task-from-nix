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

public class AnalysisAndCreateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private List<String> Message = new ArrayList<String>();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Message.clear();

		String userfirstname = req.getParameter("cruserfirstname");
		String userlastname = req.getParameter("cruserlastname");
		String usernickname = req.getParameter("crusernickname");
		String userpassword = req.getParameter("cruserpassword");

		if (userlastname.equals("") | userfirstname.equals("")
				| usernickname.equals("") | userpassword.equals("")) {
			if (userlastname.equals("")) {
				Message.add("Поле \"Фамилия\" является пустым. Заполните его!");
				req.setAttribute("crlastnameerrorstar", "*");
			}
			if (userfirstname.equals("")) {
				Message.add("Поле \"Имя\" является пустым. Заполните его!");
				req.setAttribute("crfirstnameerrorstar", "*");
			}
			if (usernickname.equals("")) {
				Message.add("Поле \"Никнейм\" является пустым. Заполните его!");
				req.setAttribute("crnicknameerrorstar", "*");
			}
			if (userpassword.equals("")) {
				Message.add("Поле \"Пароль\" является пустым. Заполните его!");
				req.setAttribute("crpassworderrorstar", "*");
			}

			req.setAttribute("ErrMsgs", Message);

			req.setAttribute("cruserfirstname", userfirstname);
			req.setAttribute("cruserlastname", userlastname);
			req.setAttribute("crusernickname", usernickname);
			req.setAttribute("cruserpassword", userpassword);
			RequestDispatcher dispatcher = req
					.getRequestDispatcher("/CreateUser");
			dispatcher.forward(req, resp);
			return;
		} else {
			if (DatabaseHelper.analysisNickname(usernickname, "create")) {
				Message.add("Пользователь с таким никнеймом уже существует!");
				req.setAttribute("crnicknameerrorstar", "*");

				req.setAttribute("ErrMsgs", Message);

				req.setAttribute("cruserfirstname", userfirstname);
				req.setAttribute("cruserlastname", userlastname);
				req.setAttribute("crusernickname", usernickname);
				req.setAttribute("cruserpassword", userpassword);
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("/CreateUser");
				dispatcher.forward(req, resp);
				return;
			} else {
				DatabaseHelper.insertUser(userfirstname, userlastname,
						usernickname, userpassword, 2);
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("/UsersList");
				dispatcher.forward(req, resp);
				return;
			}
		}
	}
}
