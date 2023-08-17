<%@page import="dto.Task"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Todo Home</title>
</head>
<body>

	<center><h1>Welcome to Home page</h1>

		<table border="2" style="color: blue;" cellspacing="10px" cellpadding="5px">
			<tr>
				<th>ID</th>
				<th>Task name</th>
				<th>Task description</th>
				<th>Task date</th>
				<th>completion date</th>
				<th>status</th>
				<th>changes</th>
				<th>update</th>
				<th>delete</th>
			</tr>
			<% 
			List<Task> tasks=(List<Task>)request.getAttribute("list");//list come from myuser
	
		   for(Task task:tasks){%>
			<tr>
			<!-- printing the value -->
				<th><%=task.getId() %></th>
				<th><%=task.getTaskname() %></th>
				<th><%=task.getDescription() %></th>
				<th><%=task.getTaskDate() %></th>
				<th><%=task.getCompletionDate() %></th>
				<th><%if(task.isStatus()) %>completed<%else %>not completed</th>
				<th><a href="changestatus?id=<%=task.getId()%>"><button>Change</button></a></th>
				<th><a href="updatestatus?id=<%=task.getId()%>"><button>Update</button></a></th>
				<th><a href="deletestatus?id=<%=task.getId()%>"><button>Delete</button></a></th>
			</tr>
			<% } %>
		</table>
		
	
	<br>
		<a href="tasksession"><button>Add Task</button></a>
		<a href="logout"><button>Logout</button></a>
</center>
</body>
</html>