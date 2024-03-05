package com.unidade4;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

//import com.cruzeiro.Student;

//import com.cruzeiro.Student;

/**
 * Servlet implementation class controler_dbstudent
 */
@WebServlet("/controler_dbstudent")
public class controler_dbstudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private util_dbstudent util_dbstudent_obje = new util_dbstudent();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public controler_dbstudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// CARREGAMENTO DO DRIVER NA CLASSE Class.forName.
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Erro ao carregar Driver " + e);
		}
		/// ***fim***

		try {
			// verifica o valor do paramentro "command"
			String command = request.getParameter("command");
			if (command == null) {
				command = "LIST";
			}

			switch (command) {
			case "LIST":
				listStudent(request, response);
				break;

			case "ADD":
				addStudents(request, response);
				break;

			case "LOAD":
				loadStudents(request, response);

				break;

			case "UPDATE":
				updateStudent(request, response);

				break;

			case "DELETE":
				deletStudents(request, response);

				break;

			/*
			 * case "LOAD": loadSudents(request, response); break;
			 * 
			 * case "UPDATE": updateStudents(request, response); break; case "DELETE":
			 * deleteStudent(request, response); break;
			 */

			default:
				listStudent(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException(e);
		}
	}

	private void listStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			List<dados_dbstudent> student1 = util_dbstudent_obje.getStudents();
			request.setAttribute("STUDENTS_LIST", student1);
			RequestDispatcher despacha = request.getRequestDispatcher("apresenta.jsp");
			despacha.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException("erro execuçao do DOGET" + e);
		}

	}

	private void addStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// recuperar as informações eviadadas
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");

		// criar um novo objeto de estudante
		dados_dbstudent student = new dados_dbstudent(0, firstname, lastname, email);

		// grava na base
		util_dbstudent_obje.addStudent(student);

		// volta para a lista de estudantes
		listStudent(request, response);
	}

	private void loadStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ler o id do estudante da base que passou para web
		String studentId = request.getParameter("studentId");

		// recuperar os dados do estudante com base no id informado
		dados_dbstudent student = util_dbstudent_obje.getStudents(studentId);

		// setar o objeto no request
		request.setAttribute("THE_STUDENT", student);

		// envia para a pagina de alteração
		RequestDispatcher despacha = request.getRequestDispatcher("update-student-form.jsp");
		despacha.forward(request, response);

	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ler informações
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		// criar o objeto de studensts
		dados_dbstudent student = new dados_dbstudent(id, firstName, lastName, email);

		// altera os dados na base
		util_dbstudent_obje.updateStudent(student);

		// voltar para a lista de estudantes
		listStudent(request, response);

	}

	private void deletStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("studentId"));

		// Deleta o aluno com base no id informado
		util_dbstudent_obje.deleteStudents(id);
		
		
		// voltar para a lista de estudantes
		listStudent(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
