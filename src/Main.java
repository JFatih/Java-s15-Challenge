import com.workintech.books.Books;
import com.workintech.books.category.EducationalBook;
import com.workintech.books.category.FictionBook;
import com.workintech.books.category.MagazinesBook;
import com.workintech.librarian.LibraryDatabase;
import com.workintech.user.Admin;
import com.workintech.user.Client;
import com.workintech.user.UserData;
import com.workintech.userscanner.ScanManager;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        UserData userFatih = new Client("fatih","cakmak", "test@gmail.com", "123");
        Set<Books> bookMainData = new HashSet<>();
        bookMainData.add(new EducationalBook("Mathematics for Engineers", "John Smith", 50, "2nd Edition", LocalDate.of(2022, 1, 15)));
        bookMainData.add(new EducationalBook("Advanced Mathematics for Engineers", "John Smith", 60, "3rd Edition", LocalDate.of(2023, 2, 20)));
        bookMainData.add(new EducationalBook("Introduction to Computer Science", "Jane Doe", 45, "3rd Edition", LocalDate.of(2021, 5, 10)));
        bookMainData.add(new EducationalBook("Computer Science Fundamentals", "Jane Doe", 50, "2nd Edition", LocalDate.of(2022, 6, 18)));
        bookMainData.add(new EducationalBook("Physics: Principles and Problems", "Richard Roe", 55, "1st Edition", LocalDate.of(2023, 3, 20)));

        // Fiction Books
        bookMainData.add(new FictionBook("The Great Adventure", "Emily Bronte", 20, "1st Edition", LocalDate.of(2020, 8, 25)));
        bookMainData.add(new FictionBook("The Great Adventure", "Emily Bronte", 25, "2nd Edition", LocalDate.of(2021, 9, 30)));
        bookMainData.add(new FictionBook("Mystery of the Old House", "Arthur Conan Doyle", 18, "2nd Edition", LocalDate.of(2019, 11, 30)));
        bookMainData.add(new FictionBook("Mystery of the Lost City", "Arthur Conan Doyle", 22, "1st Edition", LocalDate.of(2020, 10, 15)));
        bookMainData.add(new FictionBook("Fantasy World", "J.K. Rowling", 25, "4th Edition", LocalDate.of(2021, 7, 14)));
        bookMainData.add(new FictionBook("Fantasy World", "J.K. Rowling", 28, "5th Edition", LocalDate.of(2022, 12, 1)));

        // Magazines
        bookMainData.add(new MagazinesBook("Tech Today", "Various Authors", 10, "June 2023 Edition", LocalDate.of(2023, 6, 1)));
        bookMainData.add(new MagazinesBook("Tech Today", "Various Authors", 11, "July 2023 Edition", LocalDate.of(2023, 7, 1)));
        bookMainData.add(new MagazinesBook("Health Weekly", "Various Authors", 8, "April 2023 Edition", LocalDate.of(2023, 4, 10)));
        bookMainData.add(new MagazinesBook("Health Weekly", "Various Authors", 9, "May 2023 Edition", LocalDate.of(2023, 5, 10)));
        bookMainData.add(new MagazinesBook("Travel Explorer", "Various Authors", 12, "July 2023 Edition", LocalDate.of(2023, 7, 5)));
        bookMainData.add(new MagazinesBook("Travel Explorer", "Various Authors", 13, "August 2023 Edition", LocalDate.of(2023, 8, 5)));

        LibraryDatabase workintecthKutuphanesi = new LibraryDatabase(bookMainData, userFatih);




        System.out.println("Kütüphane Yönetim Sistemine Hoşgeldiniz");
        System.out.println("Kütüphane sistemine giriş yapmadan işlem yapamazsınız");
        System.out.println("--------------------------");

        int choice = 0;
        boolean isLogin = false;
        boolean loggedStatus = false;
        while (choice != 9 && !isLogin) {
            System.out.println("1. Kayıt Ol");
            System.out.println("2. Giriş yap");
            System.out.println("9. Programı kapat");
            choice = ScanManager.getIntInput("Seçiminizi yapın");

            switch (choice) {
                case 1:
                    System.out.println("Kayıt Ol");
                    int status = ScanManager.getIntInput("1. Müşteri kaydı, 2. Admin kaydı");
                    String name = ScanManager.getStringInput("İsminiz");
                    String surname = ScanManager.getStringInput("Soyisminiz");
                    String email = ScanManager.getStringInput("Mail Adresi");
                    String password = ScanManager.getStringInput("Şifreniz");
                    if (status == 1 ){
                        workintecthKutuphanesi.addUser(new Client(name,surname,email,password));
                    } else if (status == 2 ) {
                        workintecthKutuphanesi.addUser(new Admin(name,surname,email,password));
                    }
                    break;
                case 2:
                    String signEmail = ScanManager.getStringInput("Mail adresiniz giriniz");
                    String signPassword = ScanManager.getStringInput("Şifrenizi giriniz");
                    isLogin = workintecthKutuphanesi.logIn(signEmail, signPassword);
                    if(isLogin){
                        loggedStatus = workintecthKutuphanesi.loggedStatus();
                    }
                    break;
                case 9:
                    System.out.println("Programdan çıkılıyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
                    break;
            }

            while(choice != 9 && isLogin && !loggedStatus){
                System.out.println("**************************");
                System.out.println("Sayın " + workintecthKutuphanesi.getCurrentlyUser().getName() + " kitap kiralamada ilk 5 gün ücretsizdir. 5 gün üzeri hergün için 5 tl kiralama ücreti vardır.");
                System.out.println("1 Kütüphaneki Kitaplar");
                System.out.println("2 Kitap bağışla");
                System.out.println("3 Id isim veya yazara göre kitap Ara");
                System.out.println("4 Kategoriye göre kitap ara");
                System.out.println("5 Kitap kirala");
                System.out.println("6 Kitap teslim et");
                System.out.println("7 Ana Menü");
                System.out.println("8 Hesabımdan çıkış yap");
                System.out.println("9 Programı kapat");
                System.out.println("**************************");
                choice = ScanManager.getIntInput("Seçiminizi yapın");

                switch (choice){
                    case 1:
                        System.out.println("Kütüphanemizdeki Kitaplar");
                        workintecthKutuphanesi.getBooks();
                        break;
                    case 2:
                        System.out.println("Kitap bağışla");
                        System.out.println("Kategori seçiniz");
                        int category = ScanManager.getIntInput("1 Educational Book, 2 Fiction Book, 3 Magazines");
                        String name = ScanManager.getStringInput("Kitap adı");
                        String author = ScanManager.getStringInput("Yazar");
                        int price = ScanManager.getIntInput("Price");
                        String edition = ScanManager.getStringInput("Edition");
                        LocalDate dateOfpurchase = LocalDate.now();
                        switch (category){
                            case 1:
                                workintecthKutuphanesi.addBook(new EducationalBook(name, author,price,edition,dateOfpurchase));
                                break;
                            case 2:
                                workintecthKutuphanesi.addBook(new FictionBook(name, author,price,edition,dateOfpurchase));
                                break;
                            case 3:
                                workintecthKutuphanesi.addBook(new MagazinesBook(name, author,price,edition,dateOfpurchase));
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("Id isim veya yazara göre kitap Ara");
                        int searcChoise = ScanManager.getIntInput("1 Id göre ara, 2 İsim veya Yazara göre Ara");
                        switch (searcChoise){
                            case 1:
                                int searchId = ScanManager.getIntInput("Kitap Id si girin: ");
                                workintecthKutuphanesi.findBook(Long.parseLong(String.valueOf(searchId)));
                                Set<Books> searchResult = workintecthKutuphanesi.getSearchResult();
                                int index = 1;
                                for(Books book : searchResult){
                                    System.out.println( index + book.getName());
                                    index++;
                                }
                                Integer addTocartIndex = ScanManager.getIntInput("Eklemek istediğiniz kitapları numaralarını virgül ile ayırarak giriniz.");
                                workintecthKutuphanesi.addToCart(addTocartIndex);
                                break;
                            case 2:
                                String searchName = ScanManager.getStringInput("Kitap yada yazar ismi girin: ");
                                workintecthKutuphanesi.findBook(searchName);
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("Kategoriye göre kitap ara");
                        int categoryName = ScanManager.getIntInput("1 Educational Book, 2 Fiction Book, 3 Magazines");
                        switch (categoryName){
                            case 1:
                                workintecthKutuphanesi.findCategoryBooks("EducationalBook");
                                break;
                            case 2:
                                workintecthKutuphanesi.findCategoryBooks("FictionBook");
                                break;
                            case 3:
                                workintecthKutuphanesi.findCategoryBooks("MagazinesBook");
                                break;
                        }
                        break;
                    case 5:
                        System.out.println("Kitap kirala");
                        choice = 3;
                        Set<Books> searchResult = workintecthKutuphanesi.getSearchResult();
                        int index = 1;
                        for(Books book : searchResult){
                            System.out.println( index + book.getName() );
                            index++;
                        }


                        break;
                    case 6:
                        System.out.println("Kitap teslim et");
                        break;
                    case 7:
                        choice = 0;
                        break;
                    case 8:
                        workintecthKutuphanesi.logOut();
                        isLogin = false;
                        System.out.println("Çıkış yapıldı");
                        break;
                    case 9:
                        System.out.println("Programdan çıkılıyor...");
                        break;
                }
            }
        }


        ScanManager.closeScanner();
    }
    }

