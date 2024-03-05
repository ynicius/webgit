package com.unidade4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

//import com.cruzeiro.Student;

//import com.conecta.dbstudent.*;

public class util_dbstudent {

	String url = "jdbc:mysql://localhost:3306/dbstudent?user=root&password=admin";

	public List<dados_dbstudent> getStudents() throws Exception {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<dados_dbstudent> students_lista = new ArrayList<>();

		try {
			con = DriverManager.getConnection(url);
			String sql = "SELECT * FROM Student";

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String first_name = rs.getString("firstName");
				String last_name = rs.getString("lastName");
				String e_mail = rs.getString("email");

				dados_dbstudent tempStudent = new dados_dbstudent(id, first_name, last_name, e_mail);

				students_lista.add(tempStudent);
			}
			// JOptionPane.showMessageDialog(null, "Lista de estudantes carregada com
			// sucesso!");

		} finally {
			// TODO: handle finally clause
			close(con, stmt, rs);

		}

		return students_lista;
	}

	public dados_dbstudent getStudents(String idd) throws Exception {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		dados_dbstudent students_lista = null;

		try {
			con = DriverManager.getConnection(url);
			String sql = "SELECT * FROM Student where id = '"+idd+"'";

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String first_name = rs.getString("firstName");
				String last_name = rs.getString("lastName");
				String e_mail = rs.getString("email");

				students_lista = new dados_dbstudent(id, first_name, last_name, e_mail);

				
			}
			// JOptionPane.showMessageDialog(null, "Lista de estudantes carregada com
			// sucesso!");

		} finally {
			// TODO: handle finally clause
			close(con, stmt, rs);

		}

		return students_lista;
	}

	public void addStudent(dados_dbstudent student) throws Exception {
		// criar a instrução para gravar
		Connection con = null;
		PreparedStatement stmt = null;

		try {

			// configura os valores dos estudantes
			con = DriverManager.getConnection(url);

			String sql = "INSERT INTO Student (firstname , lastname , email) VALUES (?,?,?)";

			stmt = con.prepareStatement(sql);
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.setString(3, student.getEmail());

			stmt.execute();

		} finally {
			close(con, stmt, null);
		}

	}

	// atualização Student

	public void updateStudent(dados_dbstudent student) throws Exception {
		// cria a instrução para alteração
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			// configura os valos que serão atualizados
			con = DriverManager.getConnection(url);
			String sql = "update Student set firstName =?, lastName =?, email =? where id = ?";

			stmt = con.prepareStatement(sql);
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.setString(3, student.getEmail());
			stmt.setInt(4, student.getId());

			// executa instrução
			stmt.execute();

		} finally  {
			close(con, stmt, null);
		}
	}
	
	public void deleteStudents (int id) throws Exception {
		//criar a instrução para excluir
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			//conectar no banco
			con = DriverManager.getConnection(url);
			String sql = "delete from Student where id = ?";
			
			//prepara a instrução
			stmt = con.prepareStatement(sql);
			
			//configura os parametros stmt
			stmt.setInt(1, id);
			
			//executa a instrução
			stmt.execute();			
			
		} finally  {
			close(con, stmt, null);
		}
	}
	
	

	private void close(Connection con, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
