<%@page import="java.time.Period"%>
<%@page import="dto.Task"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Task task=(Task)request.getAttribute("task");
%>


<form action="updatetask" method="post">
		<th><input type="text" name="id" value="<%=task.getId() %>" hidden required></th>
		<fieldset>
			<h1>Enter AddTask Details</h1>
			<table>
				<tr>
					<th>Task name:</th>
					<th><input type="text" name="taskname" value="<%=task.getTaskname()%>"required></th>
				</tr>
				<tr>
					<th>Task Description:</th>
					<th><input type="text" name="description" value="<%=task.getDescription()%>" required></th>
				</tr>
				<tr>
					<th>Number of Days required:</th>
					<th><input type="text" name="taskday" value="<%=Period.between(task.getTaskDate(), task.getCompletionDate()).getDays()%>" required></th>
				</tr>
				<tr>
					<th><button>update</button></th>
					<th><button>Cancel</button></th>
				</tr>
			
			</table>
		</fieldset>
	</form>
	<br>
	<a href="backtohome"><button>Back</button></a>



</body>
</html>




	