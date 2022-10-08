package br.com.erivelton.canchafut.conexaoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.TimeZone;

public class UtilBDJdbc {
	private static String url = "jdbc:mysql://localhost:3306/prototipotcc?autoReconnect=true&serverTimezone=" + TimeZone.getDefault().getID();
	private static String senha = "nanuko135";
	private static String user = "root";
	private static Connection con = null;
	
	static {
		conectar();
	}
	
	public UtilBDJdbc() {
		conectar();
	}
	
	private static void conectar() {
		try {
			if(con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url, user, senha);
				//con.setAutoCommit(false);
				System.out.println("OK");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConexao() {
		return con;
	}
}
