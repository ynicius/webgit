<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="wrapper">
		<div id="header">
			<h2>Lista Alunos</h2>
		</div>
	</div>

	<div id="Container">
		<h3>Adicionar Estudantes</h3>

		<form action="controler_dbstudent" method="GET">
		
				
			<input type="hidden" name="command" value="ADD" />
			<input type="hidden" name="studentId" value="${THE_STUDENT.id }" />
			<table>
				<tbody>
					<tr>
						<td><label>Primeiro name:</label></td>
						<td><input type="text" name="firstname" aria-required="true" aria-invalid="false" required="" /></td>
					</tr>

					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastname" aria-required="true" aria-invalid="false" required="" /></td>

					</tr>
					
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" aria-required="true" aria-invalid="false" required="" /></td>

					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" name="save" /></td>

					</tr>
					
				</tbody>

			</table>

		</form>
		<p> <a href="http://localhost:8080/unidade4-verificacao/controler_dbstudent" > Voltar pagina </a> </p>

	</div>

</body>
</html>