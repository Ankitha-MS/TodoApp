package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.MyUser;
import dto.Task;

@WebServlet("/updatestatus")
public class Updtatestatus extends HttpServlet {
 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 MyUser myUser=(MyUser)req.getSession().getAttribute("user");
		if(myUser==null)
		{
			resp.getWriter().print("<h1 style='color=red'> Session Expired, Login again</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}
		else
		{
			int id=Integer.parseInt(req.getParameter("id"));
			UserDao userDao=new UserDao();
			req.setAttribute("task", userDao.fetchTask(id));
			req.getRequestDispatcher("EditTask.jsp").forward(req, resp);
			
		
		}
	}
}

