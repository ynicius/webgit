<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista alunos</title>
<script>
	function addstudent() {
		window.location.href = 'add-student.jsp';
		return false;
	}
	function delstudent() {
		if (!(confirm('Deseja excluir o registro?'))) return false;
	}
</script>

</head>
<body>
	<h2>Lista de Alunos</h2>
	<div>
		<div id="content"></div>
		<input type="button" value="Add Student" onclick="addstudent()" />
		<table border="1">
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>action</th>

			</tr>
			<c:forEach var="tempStudent" items="${STUDENTS_LIST}">

				<c:url var="tempLink" value="controler_dbstudent">
					<c:param name="command" value="LOAD" />
					<c:param name="studentId" value="${tempStudent.id}" />
				</c:url>			
				

				<c:url var="tempLinkDelete" value="controler_dbstudent">
					<c:param name="command" value="DELETE" />
					<c:param name="studentId" value="${tempStudent.id}" />
				</c:url>
				

				<tr>
					<td>${tempStudent.firstName}</td>
					<td>${tempStudent.lastName}</td>
					<td>${tempStudent.email}</td>
					<td><a href="${tempLink}"> Update </a>
						
					<a href="${tempLinkDelete}" onclick="if (!(confirm('Deseja excluir o registro?'))) return false"> Delete</a></td>
						
				</tr>
			</c:forEach>

		</table>
	</div>

</body>
</html>