package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import dao.UserDao;
import dto.MyUser;
import dto.Task;

@WebServlet("/updatetask")
public class UpdateTask extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//sesssion validation logic
		MyUser myUser=(MyUser)req.getSession().getAttribute("user");
		if(myUser==null)
		{
			//invalid session
			resp.getWriter().print("<h1 style='color=red'> Session Expired, Login again</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}
		else
		{
		int id=Integer.parseInt(req.getParameter("id"));
		String taskname=req.getParameter("taskname");
		String description=req.getParameter("description");
		int taskday=Integer.parseInt(req.getParameter("taskday"));
		
		//settting the data inside task object
		Task task=new Task();
		task.setId(id);
		task.setTaskname(taskname);
		task.setDescription(description);
		task.setTaskDate(LocalDate.now());
		task.setCompletionDate(LocalDate.now().plusDays(taskday));
		
		UserDao userDao=new UserDao();
		userDao.update(task);
		
		MyUser myUser2=userDao.findByEmail(myUser.getEmail());
		req.getSession().setAttribute("myUser", myUser2);
		resp.getWriter().print("<h1> Updated successfully</h1>");
		req.setAttribute("list", myUser2.getTasks());
		req.getRequestDispatcher("Home.jsp").include(req, resp);
		
		}
	}
}
