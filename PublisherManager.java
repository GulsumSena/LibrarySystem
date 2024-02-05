package Manager;
import java.sql.SQLException;
import java.util.Scanner;

import dal.PublisherDal;

public class PublisherManager {
	
	private final static Scanner scn = new Scanner(System.in);
	
	public static void infoPublisher() throws SQLException {

		while (true) {
			System.out.println("\nİslem Seciniz: ");
			System.out.println("0-Çıkış");
			System.out.println("1-Yayıncı Listele");
			System.out.println("2-Yayıncı Ekle");
			System.out.println("3-Yayıncı Güncelle");
			System.out.println("4-Yayıncı Sil");
			int islem = scn.nextInt();

			if (islem == 0) {
				System.out.println("Yayınevi Sisteminden Çıkış Yapılıyor...");
				break;
			}

			switch (islem) {
			case 1: {
				PublisherDal.listPublisher();
				break;
			}
			case 2: {
				PublisherDal.addPublisher();
				break;
			}
			case 3: {
				PublisherDal.updatePublisher();
				break;
			}
			case 4: {
				PublisherDal.deletePublisher();
				break;
			}
			default:
				System.out.println("Hatalı Tuşlama");
			}
		}
	}
}
