package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

public class GenreDal {
	
	private final static Scanner scn = new Scanner(System.in);
	private static Connection cnn;
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement pst;
	
	private static String path="jdbc:mysql://localhost:3306/kys";
	private static String user="root";
	private static String pass="1234";
	
	public static void listGenre() throws SQLException {
		cnn=DriverManager.getConnection(path, user, pass);
		st=cnn.createStatement();
		String q="select * from genres";
		rs=st.executeQuery(q);
		while(rs.next()) {
			System.out.println("ID:"+rs.getInt("genre_id")+" - "+rs.getString("genre_name"));
		}
	}
	
	public static void addGenre() throws SQLException {
		cnn=DriverManager.getConnection(path, user, pass);
		System.out.println(Renk.BLUE+"Kategori Adı: "+Renk.RESET);
		String name=scn.nextLine();
		
		String q="insert into genres (genre_name) values(?)";
		pst=cnn.prepareStatement(q);
		pst.setString(1, name);
		int row=pst.executeUpdate();
		if(row>0) { 
			System.out.println(Renk.RED+"İşlem Başarılı"+Renk.RESET);
		}
	}
	
	public static void updateGenre() throws SQLException {
		cnn=DriverManager.getConnection(path, user, pass);
		listGenre();
		
		System.out.println("\nGüncellemek istediğiniz kategori ID: ");
		int id=scn.nextInt();
		
		System.out.println(Renk.BLUE+"Kategori Adı: "+Renk.RESET);
		scn.nextLine();
		String newName=scn.nextLine();
		
		String q1="update genres set genre_name=? where genre_id=?";
		pst=cnn.prepareStatement(q1);
		pst.setString(1, newName);
		pst.setInt(2, id);
		
		int row=pst.executeUpdate();
		
		if(row>0) {
			System.out.println(Renk.RED+"İşlem Başarılı"+Renk.RESET);
		}
	}
	
	public static void deleteGenre() throws SQLException {
		cnn=DriverManager.getConnection(path, user, pass);
		listGenre();
		
		System.out.println("Silmek İstediğiniz Kategori ID: ");
		int id=scn.nextInt();
		
		String q="delete from genres where genre_id=?";
		pst=cnn.prepareStatement(q);
		pst.setInt(1, id);
		
		try {
			int row = pst.executeUpdate();
			if (row > 0) {
				System.out.println("İşlem Başarılı");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Kütüphanemizde Seçtiğiniz Kategoriden Kitaplar Var. İşleminizi Gerçekleştiremiyoruz.");
		}
	}

}
