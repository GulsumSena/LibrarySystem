import java.sql.SQLException;
import java.util.Scanner;

import Manager.BookManager;
import Manager.GenreManager;
import Manager.PublisherManager;
import dal.BookDal;

public class Main {

	public static void main(String[] args) throws SQLException {
		Scanner scn = new Scanner(System.in);

		System.out.println("Kütüphane Kayıt Sistemine Hoşgeldiniz. ");
		
		while (true) {
			
			System.out.println("\nİşlem Türü Seçiniz: ");
			System.out.println("0-Çıkış");
			System.out.println("1-Kitap İşlemleri");
			System.out.println("2-Yayınevi İşlemleri");
			System.out.println("3-Kategori İşlemleri");
			int islem = scn.nextInt();

			if (islem == 0) {
				break;
			}

			switch (islem) {
			case 1: {
				BookManager.infoBook();
				break;
			}
			case 2: {
				PublisherManager.infoPublisher();
				break;
			}
			case 3: {
				GenreManager.infoGenre();
				break;
			}
			default:
				System.out.println("Hatalı tuşlama");
			}
		}
	}
}
