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

@WebServlet("/deletestatus")
public class Deletestatus extends HttpServlet {

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
			//logic to fetch task
			int id=Integer.parseInt(req.getParameter("id"));
			UserDao userDao=new UserDao();
			Task task=userDao.fetchTask(id);//primary key
			if(task==null) {
				resp.getWriter().print("<h1 style='color:Blue'>session expired,login again</h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			}
			else
			{
				//logic to remove mapping
				myUser.getTasks().remove(task);
				userDao.update(myUser);
				 
				//logic to delete
				userDao.remove(task);
				
				//logic to update the session
				MyUser myUser2=userDao.findByEmail(myUser.getEmail());
				req.getSession().setAttribute("user", myUser2);
				
				//give the msg
				resp.getWriter().print("<h1 style='color:Blue'>Status removed Success</h1>");
				
				//carry again to the home.jsp
				req.setAttribute("list", myUser2.getTasks());
				req.getRequestDispatcher("Home.jsp").include(req, resp);

				
			}	
		}
	}
		
	}

