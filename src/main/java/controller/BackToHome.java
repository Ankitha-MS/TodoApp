package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MyUser;
//this is to accept url(this should be same as href in singup page)
@WebServlet("/backtohome")
//to make this class as servlet class
public class BackToHome extends HttpServlet {

	@Override
	//when button is there and <a>it is doget
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//session validation logic
		MyUser user=(MyUser)req.getSession().getAttribute("user");
		if(user==null)
		{
			resp.getWriter().print("<h1 style='color:red'>Session expired</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}
		else
		{
			req.setAttribute("list", user.getTasks());
			req.getRequestDispatcher("Home.jsp").include(req, resp);
		}
	}

}
