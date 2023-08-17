package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MyUser;

//this is accept URL(this should be same as href in logout button<a>
@WebServlet("/tasksession")
//to convert normal class into servlet class
public class TaskSesssion extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//logic to verify sesssion(here value should be same as key as we set in logic)
		MyUser user=(MyUser)req.getSession().getAttribute("user");
		if(user==null)
		{
			//not logged in already logged out
			resp.getWriter().print("<h1 style='color=red'> Session Expired, Login again</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
			
		}
		else
		{
			//valid user logged in
			req.getRequestDispatcher("Addtask.html").forward(req,resp);
		}
		
	}
}
