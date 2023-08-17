package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.MyUser;

//this is to accept url(this should be same as action in singup page)
@WebServlet("/signup")
//this is to make our Class as Servlet Class
public class Signup extends HttpServlet {
	@Override
	//when form is there and method is post we need to take dopost
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//recieve the data from front-end
		//Name attribute vale we should write here It is case sensitive
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String mobile=req.getParameter("mobile");
		String gender=req.getParameter("gen");
		String dob=req.getParameter("dob");
		String address=req.getParameter("address");
		String pwd=req.getParameter("pwd");
		String[] language=req.getParameterValues("languages");
		
		
		MyUser user=new MyUser();
		user.setName(name);
		user.setEmail(email);
		user.setAddress(address);
		user.setGender(gender);
		user.setPwd(pwd);
		user.setDob(LocalDate.parse(dob));
		user.setMobile(Long.parseLong(mobile));
		user.setLanguage(language);
		
		UserDao userDao=new UserDao();
		
		//logic to verify the email should not be repeated
		MyUser myUser=userDao.findByEmail(email);
		if(myUser==null) {
			//email does not exist so setting new object
			userDao.save(user);
			
			//after saving where to go
			resp.getWriter().print("<h1 style=\"color:green\"> Account created</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}
		else {
			// if email exists
			resp.getWriter().print("<h1 style=\"color:red\">Email already exist</h1>");
			req.getRequestDispatcher("Signup.html").include(req, resp);
		}
	}

}
