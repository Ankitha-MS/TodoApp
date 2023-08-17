package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import dao.UserDao;
import dto.MyUser;
import dto.Task;

//this is to Accept url(this should be same as action in Addtask page
@WebServlet("/addtask")
public class AddTask extends HttpServlet {
@Override

//when ever we form we use dopost
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//sesssion validation logic
	if(req.getSession().getAttribute("user")==null)
	{
		//invalid session
		resp.getWriter().print("<h1 style='color=red'> Session Expired, Login again</h1>");
		req.getRequestDispatcher("Login.html").include(req, resp);
	}
	else
	{
	//valid session
		//receving data from the front end form(name attribute value should be written
	String taskname=req.getParameter("taskname");
	String description=req.getParameter("description");
	int taskday=Integer.parseInt(req.getParameter("taskday"));
	
	//settting the data inside task object
	Task task=new Task();
	task.setTaskname(taskname);
	task.setDescription(description);
	task.setTaskDate(LocalDate.now());
	task.setCompletionDate(LocalDate.now().plusDays(taskday));
	
	
	
	MyUser myUser=(MyUser) req.getSession().getAttribute("user");
	
	//mapping task to user(it is onetomany so list is required so taking existing list)
	List<Task> list=myUser.getTasks();
	//to avoid nullpointer exception
	if(list==null)
	{
		list=new ArrayList<Task>();
	}
	list.add(task);
	//mapping
	myUser.setTasks(list);
	
	//saving changes in database
	UserDao userDao=new UserDao();
	userDao.save(task);
	userDao.update(myUser);
	
	//saving changes in session
	MyUser myUser2=userDao.findByEmail(myUser.getEmail());
	req.getSession().setAttribute("myUser", myUser2);
	
	//going back to Home.jsp with values
	resp.getWriter().print("<h1 style= 'color:green'>Task added sucess</h1>");
	req.setAttribute("list",myUser.getTasks());
	req.getRequestDispatcher("Home.jsp").include(req, resp);
	
}
}
}
