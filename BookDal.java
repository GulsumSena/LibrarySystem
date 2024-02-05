package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BookDal {

	private final static Scanner scn = new Scanner(System.in);
	private static Connection cnn;
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement pst;

	private static String path = "jdbc:mysql://localhost:3306/kys";
	private static String user = "root";
	private static String pass = "1234";

	public static void listBook() throws SQLException {
		cnn = DriverManager.getConnection(path, user, pass);
		st = cnn.createStatement();

		String q = "select books.book_id, books.title, genres.genre_name, publishers.publisher_name, books.publication_year"
				+ " from books " + " join genres on books.genre_id=genres.genre_id "
				+ " join publishers on books.publisher_id=publishers.publisher_id";
		rs = st.executeQuery(q);

		while (rs.next()) {

			int id = rs.getInt("book_id");
			String name = rs.getString("title");
			String genre = rs.getString("genre_name");
			String publishers = rs.getString("publisher_name");
			int year = rs.getInt("publication_year");

			System.out.println(Renk.BLUE + "\n" + name + Renk.RESET + "\nID: " + id + "\nKategori: " + genre
					+ "\nYayınevi: " + publishers + "\nYayın Yılı: " + year);
		}

	}

	public static void addBook() throws SQLException {
		cnn = DriverManager.getConnection(path, user, pass);

		PublisherDal.listPublisher();
		System.out.println("\n[0]-Yeni Yayınevi Ekle" + "\nYayınevi Seçiniz: ");
		int yayin = scn.nextInt();
		int yayin2 = 0;

		if (yayin == 0) {
			PublisherDal.addPublisher();
			PublisherDal.listPublisher();
			System.out.println("Kategori Seçiniz: ");
			yayin2 = scn.nextInt();
		} else {
			yayin2 = yayin;
		}

		GenreDal.listGenre();
		System.out.print("\n[0]-Yeni Kategori Ekle" + "Kategori Seçiniz: ");
		int kategori = scn.nextInt();
		int kategori2 = 0;

		if (kategori == 0) {
			GenreDal.addGenre();
			GenreDal.listGenre();
			System.out.println("Kategori Seçiniz: ");
			kategori2 = scn.nextInt();
		} else {
			kategori2 = kategori;
		}
		
		System.out.print("Kitap Adı: ");
		scn.nextLine();
		String nameB = scn.nextLine();
		System.out.print("Basım Yılı: ");
		int yil = scn.nextInt();

		String q = "insert into books (title, genre_id, publisher_id, publication_year) values (?,?,?,?)";
		pst = cnn.prepareStatement(q);
		pst.setString(1, nameB);
		pst.setInt(2, kategori2);
		pst.setInt(3, yayin2);
		pst.setInt(4, yil);
		int row = pst.executeUpdate();

		if (row > 0) {
			System.out.println(Renk.RED + "Kitap Ekleme İşlemi Başarılı" + Renk.RESET);
		}
	}

	public static void updateBook() throws SQLException {

		listBook();
		System.out.println("Güncelleme yapmak istediğiniz kitap ID: ");
		int id = scn.nextInt();

		cnn = DriverManager.getConnection(path, user, pass);
		st = cnn.createStatement();

		String q = "select * from books where book_id=" + id;
		ResultSet rs = st.executeQuery(q);

		while (rs.next()) {
			System.out.println("Kitap Adı: ");
			scn.nextLine();
			String newName = scn.nextLine();

			if (newName == null || newName.equals("")) {
				newName = rs.getString("title");
			}

			GenreDal.listGenre();
			System.out.println("\nKategori [0-Değiştirme] :");
			int newGenre = scn.nextInt();
			if (newGenre == 0) {
				newGenre = rs.getInt("genre_id");
			}

			PublisherDal.listPublisher();
			System.out.println("\nYayınevi: [0-Değiştirme]");
			int newPublisher = scn.nextInt();

			if (newPublisher == 0) {
				newPublisher = rs.getInt("publisher_id");
			}

			System.out.println("\nYayın yılı: [0-Değiştirme]");
			int newYear = scn.nextInt();
			if (newYear == 0) {
				newYear = rs.getInt("publication_year");
			}

			String newQ = "update books set title=?, genre_id=?, publisher_id=?, publication_year=? where book_id=?";
			pst = cnn.prepareStatement(newQ);
			pst.setString(1, newName);
			pst.setInt(2, newGenre);
			pst.setInt(3, newPublisher);
			pst.setInt(4, newYear);
			pst.setInt(5, id);

			int row = pst.executeUpdate();
			if (row > 0) {
				System.out.println(Renk.RED + "İşlem Başarılı" + Renk.RESET);
			}

		}
	}

	public static void deleteBook() throws SQLException {
		listBook();
		System.out.println("Silmek İstediğiniz Kitap ID: ");
		int id = scn.nextInt();

		cnn = DriverManager.getConnection(path, user, pass);

		String q = "delete from books where book_id=?";
		pst = cnn.prepareStatement(q);
		pst.setInt(1, id);
		int row = pst.executeUpdate();
		if (row > 0) {
			System.out.println(Renk.RED + "İşlem Başarılı" + Renk.RESET);
		}
	}

}
