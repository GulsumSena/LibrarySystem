package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

public class PublisherDal {
	private final static Scanner scn = new Scanner(System.in);
	private static Connection cnn;
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement pst;

	private static String path = "jdbc:mysql://localhost:3306/kys";
	private static String user = "root";
	private static String pass = "1234";

	public static void listPublisher() throws SQLException {
		cnn = DriverManager.getConnection(path, user, pass);
		st = cnn.createStatement();
		String q = "select * from publishers";
		rs = st.executeQuery(q);
		while (rs.next()) {
			System.out.println("ID:" + rs.getInt("publisher_id") + " - " + rs.getString("publisher_name"));
		}
	}

	public static void addPublisher() throws SQLException {
		cnn = DriverManager.getConnection(path, user, pass);

		System.out.println("Yayınevi Adı: ");
//		scn.nextLine();
		String name = scn.nextLine();
		String q = "insert into publishers (publisher_name) values (?)";
		pst = cnn.prepareStatement(q);
		pst.setString(1, name);
		int row = pst.executeUpdate();
		if (row > 0) {
			System.out.println("işlem Başarılı");
		}
	}

	public static void updatePublisher() throws SQLException {
		cnn = DriverManager.getConnection(path, user, pass);
		listPublisher();

		System.out.println("Güncellemek istediğiniz Yayınevi ID: ");
		int id = scn.nextInt();
		scn.nextLine();
		System.out.println("Yayınevi Adı: ");

		String name = scn.nextLine();

		String q = "update publishers set publisher_name=? where publisher_id=?";
		pst = cnn.prepareStatement(q);
		pst.setString(1, name);
		pst.setInt(2, id);
		int row = pst.executeUpdate();

		if (row > 0) {
			System.out.println("işlem başarılı");
		}
	}

	public static void deletePublisher() throws SQLException {
		cnn = DriverManager.getConnection(path, user, pass);
		listPublisher();

		System.out.println("Silmek istediğiniz yayınevi ID: ");
		int id = scn.nextInt();

		String q = "delete from publishers where publisher_id=?";
		pst = cnn.prepareStatement(q);
		pst.setInt(1, id);
		try {
			int row = pst.executeUpdate();
			if (row > 0) {
				System.out.println("İşlem Başarılı");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Kütüphanemizde Seçtiğiniz Yayınevinden Yayınlanan Kitaplar Var. İşleminizi Gerçekleştiremiyoruz.");
		}
		
	}

}
