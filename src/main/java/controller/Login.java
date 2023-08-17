package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dto.MyUser;
import dto.Task;

//this is to accept url(this should be same as action in singup page)
@WebServlet("/login")
public class Login extends HttpServlet {
@Override
//when form is there and method is post we need to take dopost
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	
	UserDao userDao=new UserDao();
	
	//email is not present ie wrong email
	MyUser myUser=userDao.findByEmail(email);
	if(myUser==null) {
		resp.getWriter().print("<h1 style=\"color:green\">Incorrect Email</h1>");
		req.getRequestDispatcher("Login.html").include(req, resp);
	}
	else {
		//email is correct need to check password
		if(myUser.getPwd().equals(password))
		{
			//password is crt so login sucess
			//session tracking logic adding into session
			//setting user inside session key and value(key is important
		req.getSession().setAttribute("user", myUser);
		//we will set the time
		req.getSession().setMaxInactiveInterval(50);
		//list is a keyword and another list is a reference variable
		
		resp.getWriter().print("<h1 style=\"color:red\">Login success</h1>");
		//carring data to home.jsp to display
		req.setAttribute("list", myUser.getTasks());
		
		req.getRequestDispatcher("Home.jsp").include(req, resp);
		}
		else
		{
			//email correct but incorrect password
		resp.getWriter().print("<h1 style=\"color:red\">Incorrect password</h1>");
		req.getRequestDispatcher("Login.html").include(req, resp);
		}
	}	
	
}

}
