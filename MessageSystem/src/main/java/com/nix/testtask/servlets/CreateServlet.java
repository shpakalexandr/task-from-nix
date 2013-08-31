package com.nix.testtask.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("going to create-page");
		/*
		 * Testing modification part User dem = new User(); dem.setUserid(2);
		 * dem.setUserfirstname("aaaaa"); dem.setUserlastname("bbbbb");
		 * dem.setUsernickname("ccccc"); dem.setUserpassword("ddddd");
		 * dem.setUserroleid(2); dem.setUserenabled(1);
		 * 
		 * User userInfo = dem;
		 */
		req.setAttribute("CREATE", true);
		if (req.getAttribute("ErrMsgs") == null) {
			req.setAttribute("ErrMsgs", null);			
		} else {
			req.setAttribute("ErrMsgs", req.getAttribute("ErrMsgs"));
		}

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/jsps/CreateOrModifyUser.jsp");
		dispatcher.forward(req, resp);
	}
}
