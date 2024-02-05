package Manager;

import java.sql.SQLException;
import java.util.Scanner;

import dal.GenreDal;

public class GenreManager {
	
	private final static Scanner scn = new Scanner(System.in);

	public static void infoGenre() throws SQLException {

		while (true) {
			System.out.println("\nİslem Seciniz: ");
			System.out.println("0-Çıkış");
			System.out.println("1-Kategori Listele");
			System.out.println("2-Kategori Ekle");
			System.out.println("3-Kategori Güncelle");
			System.out.println("4-Kategori Sil");
			int islem = scn.nextInt();

			if (islem == 0) {
				System.out.println("Kategori Sisteminden Çıkış Yapılıyor...");
				break;
			}

			switch (islem) {
			case 1: {
				GenreDal.listGenre();
				break;
			}
			case 2: {
				GenreDal.addGenre();
				break;
			}
			case 3: {
				GenreDal.updateGenre();
				break;
			}
			case 4: {
				GenreDal.deleteGenre();
				break;
			}
			default:
				System.out.println("Hatalı Tuşlama");
			}
		}
	}
}
