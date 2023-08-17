package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//this is accept URL(this should be same as href in logout button<a>
@WebServlet("/logout")
public class Logout extends HttpServlet {
	@Override
	//when button is there and <a> it is doget
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//logout logic it will end sesssion
		req.getSession().invalidate();
		resp.getWriter().print("<h1 style='color:Orange'>Logout success</h1>");
		req.getRequestDispatcher("Login.html");
	}

}
