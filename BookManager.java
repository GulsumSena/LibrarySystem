package Manager;


import java.sql.SQLException;
import java.util.Scanner;

import dal.BookDal;

public class BookManager {

	private final static Scanner scn = new Scanner(System.in);
	
	public static void infoBook() throws SQLException {

		while (true) {
			System.out.println("\nİslem Seciniz: ");
			System.out.println("0-Çıkış");
			System.out.println("1-Kitap Listele");
			System.out.println("2-Kitap Ekle");
			System.out.println("3-Kitap Güncelle");
			System.out.println("4-Kitap Sil");
			int islem = scn.nextInt();

			if (islem == 0) {
				System.out.println("Kitap Sisteminden Çıkış Yapılıyor...");
				break;
			}

			switch (islem) {
			case 1: {
				BookDal.listBook();
				break;
			}
			case 2: {
				BookDal.addBook();
				break;
			}
			case 3: {
				BookDal.updateBook();
				break;
			}
			case 4: {
				BookDal.deleteBook();
				break;
			}
			default:
				System.out.println("Hatalı Tuşlama");
			}
		}
	}
	
	
}
