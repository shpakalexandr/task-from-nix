package com.nix.testtask.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.nix.testtask.db.DatabaseHelper;

public class AnalysisAndSendServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private List<String> Message = new ArrayList<String>();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Message.clear();

		String receiver = req.getParameter("messagereciever");
		String title = req.getParameter("messagetitle");
		String message = req.getParameter("messagebody");
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String whosend = auth.getName();

		if (receiver.equals("") | title.equals("")) {
			if (receiver.equals("")) {
				Message.add("Поле \"Получатель\" является пустым. Заполните его!");
				req.setAttribute("recievererrorstar", "*");
			}
			if (title.equals("")) {
				Message.add("Поле \"Тема\" является пустым. Заполните его!");
				req.setAttribute("titleererrorstar", "*");
			}

			req.setAttribute("ErrMsgs", Message);

			req.setAttribute("messagereciever", receiver);
			req.setAttribute("messagetitle", title);
			req.setAttribute("messagebody", message);

			RequestDispatcher dispatcher = req
					.getRequestDispatcher("/SendMessage");
			dispatcher.forward(req, resp);
			return;
		} else {
			if (whosend.equals(receiver)) {
				Message.add("Пользователь не может отослать сам себе сообщение!");
				req.setAttribute("recievererrorstar", "*");
				
				req.setAttribute("ErrMsgs", Message);

				req.setAttribute("messagereciever", receiver);
				req.setAttribute("messagetitle", title);
				req.setAttribute("messagebody", message);
				
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("/SendMessage");
				dispatcher.forward(req, resp);
			} else {
				if (!DatabaseHelper.nicknameExist(receiver)) {
					Message.add("Пользователя с таким никнеймом не существует!");
					req.setAttribute("recievererrorstar", "*");

					req.setAttribute("ErrMsgs", Message);

					req.setAttribute("messagereciever", receiver);
					req.setAttribute("messagetitle", title);
					req.setAttribute("messagebody", message);

					RequestDispatcher dispatcher = req
							.getRequestDispatcher("/SendMessage");
					dispatcher.forward(req, resp);
					return;
				} else {

					DatabaseHelper.insertMessage(whosend, receiver, title,
							message);
					RequestDispatcher dispatcher = req
							.getRequestDispatcher("/SuccessSend");
					dispatcher.forward(req, resp);
					return;
				}
			}
		}
	}
}
