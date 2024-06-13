import com.workintech.books.BookStatus;
import com.workintech.books.Books;
import com.workintech.books.category.EducationalBook;
import com.workintech.books.category.FictionBook;
import com.workintech.books.category.MagazinesBook;
import com.workintech.librarian.LibraryDatabase;
import com.workintech.user.Admin;
import com.workintech.user.Client;
import com.workintech.user.ClientRentBook;
import com.workintech.user.UserData;
import com.workintech.userscanner.ScanManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Set<Books> bookMainData = new HashSet<>();

        //Educational Books
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
        bookMainData.add((new MagazinesBook("Tech Today", "Various Authors", 10, "June 2023 Edition", LocalDate.of(2023, 6, 1))));
        bookMainData.add(new MagazinesBook("Tech Today", "Various Authors", 11, "July 2023 Edition", LocalDate.of(2023, 7, 1)));
        bookMainData.add(new MagazinesBook("Health Weekly", "Various Authors", 8, "April 2023 Edition", LocalDate.of(2023, 4, 10)));
        bookMainData.add(new MagazinesBook("Health Weekly", "Various Authors", 9, "May 2023 Edition", LocalDate.of(2023, 5, 10)));
        bookMainData.add(new MagazinesBook("Travel Explorer", "Various Authors", 12, "July 2023 Edition", LocalDate.of(2023, 7, 5)));
        bookMainData.add(new MagazinesBook("Travel Explorer", "Various Authors", 13, "August 2023 Edition", LocalDate.of(2023, 8, 5)));

        Client userFatih = new Client("fatih","cakmak", "test@gmail.com", "123");
        Client userKerem = new Client("Kerem","karagöz", "kerem@gmail.com", "123123");
        UserData adminFatih = new Admin("AdminName","AdminSurname","admin@gmail.com","1234");

        LibraryDatabase workintecthKutuphanesi = new LibraryDatabase(bookMainData, userFatih, userKerem, adminFatih);

        Map<Long, Books> databaseBooks = workintecthKutuphanesi.getBooks();
        Books book10 = databaseBooks.get(20L);
        Books book11 = databaseBooks.get(21L);
        book10.setStatus(BookStatus.UNAVAILABLE);
        book11.setStatus(BookStatus.UNAVAILABLE);
        userFatih.setRentBooks(new ClientRentBook(book10, LocalDate.of(2024, 5, 10)));
        userFatih.setRentBooks(new ClientRentBook(book11, LocalDate.of(2024, 6, 10)));

        Books book12 = databaseBooks.get(22L);
        Books book13 = databaseBooks.get(23L);
        book12.setStatus(BookStatus.UNAVAILABLE);
        book13.setStatus(BookStatus.UNAVAILABLE);
        userKerem.setRentBooks(new ClientRentBook(book12,LocalDate.of(2024, 6, 11)));
        userKerem.setRentBooks(new ClientRentBook(book13,LocalDate.of(2024, 6, 12)));
//        **********************************************************************************************************
        System.out.println("**************************");
        System.out.println("Kütüphane Yönetim Sistemine Hoşgeldiniz");
        System.out.println("Kütüphane sistemine giriş yapmadan işlem yapamazsınız");

        int choice = 0;
        boolean isLogin = false;
        boolean loggedStatus = false;
        while (choice != 9) {
            System.out.println("**************************");
            System.out.println("1. Kayıt Ol");
            System.out.println("2. Giriş yap");
            System.out.println("9. Programı kapat");
            System.out.println("**************************");
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
                System.out.println("10 Sepetiniz");
                System.out.println("11 Kiraladığım kitaplar");
                System.out.println("**************************");
                choice = ScanManager.getIntInput("Seçiminizi yapın");

                switch (choice){
                    case 1:
                        System.out.println("Kütüphanemizdeki Kitaplar");
                        for(Map.Entry<Long, Books> entry : workintecthKutuphanesi.getBooks().entrySet()){
                            System.out.println(entry.getValue());
                        }
                        break;
                    case 2:
                        System.out.println("Kitap bağışla");
                        System.out.println("Kategori seçiniz");
                        int category = ScanManager.getIntInput("1 Educational Book, 2 Fiction Book, 3 Magazines");
                        String name = ScanManager.getStringInput("Kitap adı");
                        String author = ScanManager.getStringInput("Yazar");
                        int price = ScanManager.getIntInput("Price");
                        String edition = ScanManager.getStringInput("Edition");
                        LocalDate dateOfPurchase = LocalDate.now();
                        switch (category){
                            case 1:
                                workintecthKutuphanesi.addBook(new EducationalBook(name, author,price,edition,dateOfPurchase));
                                break;
                            case 2:
                                workintecthKutuphanesi.addBook(new FictionBook(name, author,price,edition,dateOfPurchase));
                                break;
                            case 3:
                                workintecthKutuphanesi.addBook(new MagazinesBook(name, author,price,edition,dateOfPurchase));
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
                                addToCartMethod(workintecthKutuphanesi);
                                break;
                            case 2:
                                String searchName = ScanManager.getStringInput("Kitap yada yazar ismi girin: ");
                                workintecthKutuphanesi.findBook(searchName);
                                addToCartMethod(workintecthKutuphanesi);
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("Kategoriye göre kitap ara");
                        int categoryName = ScanManager.getIntInput("1 Educational Book, 2 Fiction Book, 3 Magazines");
                        switch (categoryName){
                            case 1:
                                workintecthKutuphanesi.findCategoryBooks("EducationalBook");
                                addToCartMethod(workintecthKutuphanesi);
                                break;
                            case 2:
                                workintecthKutuphanesi.findCategoryBooks("FictionBook");
                                addToCartMethod(workintecthKutuphanesi);
                                break;
                            case 3:
                                workintecthKutuphanesi.findCategoryBooks("MagazinesBook");
                                addToCartMethod(workintecthKutuphanesi);
                                break;
                            default:
                                System.out.println("Yazdığız numaranın kategorisi bulunmamaktadır.");
                        }
                        break;
                    case 5:
                        System.out.println("Kitap kirala");
                        if(workintecthKutuphanesi.getCart().isEmpty()){
                            System.out.println("Sepetiniz boştur. Kategoriye, id veya isim/yazar göre kitap arayarak sepetinize kitap ekleyebilrisiniz.");
                        } else{
                            System.out.println("Sepetiniz :");
                            for(Books book : workintecthKutuphanesi.getCart()){
                                System.out.println(book);
                            }
                            int addOrRemove = ScanManager.getIntInput("1 Sepetten kitap çıkar, 2 Sepetteki kitapları kirala");
                            if(addOrRemove == 1){
                                String ids= ScanManager.getStringInput("Sepetinizden çıkarılacak kitaplaraın ıd lerini virgül ile ayırarak yazınız :");
                                String[] removeBooksIds = ids.split(",");
                                Long[] removeBooksIdsLong = new Long[removeBooksIds.length];
                                for(int i = 0 ; i < removeBooksIds.length; i++ ){
                                    removeBooksIdsLong[i] = Long.parseLong(removeBooksIds[i]);
                                }
                                workintecthKutuphanesi.removeToCart(removeBooksIdsLong);
                                break;
                            } else if( addOrRemove == 2 ){
                                workintecthKutuphanesi.rentToBook();
                                break;
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Kitap teslim et");
                        Set<ClientRentBook> userRentBooks = ((Client)workintecthKutuphanesi.getCurrentlyUser()).getRentBooks();
                        if(userRentBooks.isEmpty()){
                            System.out.println("Kiraladığınız kitap bulunmamaktadır");
                            break;
                        } else {
                            for(ClientRentBook rentedBook : userRentBooks){
                                System.out.println( "ID: " + rentedBook.getRentedBook().getId() + "|| Kitap adı: " + rentedBook.getRentedBook().getName() + "|| Kiralama tarihi " + rentedBook.getRentDate());
                            }
                            String returnBooksIdString = ScanManager.getStringInput("Kiraladığınız kitaplardan teslim etmek istediğinizin Id'sini girin lütfen! (Birden fazla kitap için virgül ile ayırınız.)");
                            String[] returnBooksId = returnBooksIdString.split(",");
                            workintecthKutuphanesi.returnRentedBooksId(returnBooksId);
                            break;
                        }

                    case 7:
                        choice = 0;
                        break;
                    case 8:
                        workintecthKutuphanesi.logOut();
                        isLogin = false;
                        System.out.println("Çıkış yapıldı...");
                        break;
                    case 9:
                        System.out.println("Programdan çıkılıyor...");
                        break;
                    case 10:
                        System.out.println("Sepetiniz : ");
                        if(workintecthKutuphanesi.getCart().isEmpty()){
                            System.out.println("Sepetinizde kitap bulunmamaktadır");
                        } else {
                            for(Books book : workintecthKutuphanesi.getCart()){
                                System.out.println(book);
                            }
                        }
                        break;
                    case 11:
                        workintecthKutuphanesi.getRentedBooksData();
                        break;
                    default:
                        System.out.println("Geçersiz seçim, Tekrar deneyin");
                        break;
                }
            }

            while(choice != 9 && isLogin) {
                System.out.println("**************************");
                System.out.println("Sayın Yönetici " + workintecthKutuphanesi.getCurrentlyUser().getName() + " kütüphane yönetim sistemine hoşgeldiniz.");
                System.out.println("1 Kütüphaneki Kitaplar:");
                System.out.println("2 Kitap ekle:");
                System.out.println("3 Id isim veya yazara göre kitap ara:");
                System.out.println("4 Kategoriye göre kitap ara:");
                System.out.println("5 Kiralanan kitapları göster:");
                System.out.println("6 Kitap sil veya bilgisi güncelle:");
                System.out.println("7 Ana Menü");
                System.out.println("8 Hesabımdan çıkış yap");
                System.out.println("9 Programı kapat");
                System.out.println("**************************");
                choice = ScanManager.getIntInput("Seçiminizi yapın");

                switch (choice){
                    case 1:
                        System.out.println("Kütüphanemizdeki Kitaplar");
                        for(Map.Entry<Long, Books> entry : workintecthKutuphanesi.getBooks().entrySet()){
                            System.out.println(entry.getValue());
                        }
                        break;
                    case 2:
                        System.out.println("Kitap ekle");
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
                                addToCartMethod(workintecthKutuphanesi);
                                break;
                            case 2:
                                String searchName = ScanManager.getStringInput("Kitap yada yazar ismi girin: ");
                                workintecthKutuphanesi.findBook(searchName);
                                addToCartMethod(workintecthKutuphanesi);
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("Kategoriye göre kitap ara");
                        int categoryName = ScanManager.getIntInput("1 Educational Book, 2 Fiction Book, 3 Magazines");
                        switch (categoryName){
                            case 1:
                                workintecthKutuphanesi.findCategoryBooks("EducationalBook");
                                addToCartMethod(workintecthKutuphanesi);
                                break;
                            case 2:
                                workintecthKutuphanesi.findCategoryBooks("FictionBook");
                                addToCartMethod(workintecthKutuphanesi);
                                break;
                            case 3:
                                workintecthKutuphanesi.findCategoryBooks("MagazinesBook");
                                addToCartMethod(workintecthKutuphanesi);
                                break;
                            default:
                                System.out.println("Yazdığız numaranın kategorisi bulunmamaktadır.");
                        }
                        break;
                    case 5:
                        System.out.println("Kiralanan kitapları göster :");
                        workintecthKutuphanesi.getRentedBooksData();
                        break;
                    case 6:
                        System.out.println("Kitap bilgisi güncelle :");
                        if(workintecthKutuphanesi.getCart().isEmpty()){
                            System.out.println("Güncellemek yada silmek istediğiniz kitapları sepete ekleyin.");
                        } else {
                            int updateOrRemove = ScanManager.getIntInput("1 Kitap sil, 2 Kitap bilgisi güncelle");
                            System.out.println("Sepetiniz");
                            for(Books book : workintecthKutuphanesi.getCart()){
                                System.out.println(book);
                            }
                            switch (updateOrRemove){
                                case 1:
                                    String ids = ScanManager.getStringInput("Silinmesini istediğiniz idleri girin (Birden fazla id için virgül kullanın)");
                                    String[] removeBooksIds = ids.split(",");
                                    workintecthKutuphanesi.removeBooks(removeBooksIds);
                                    break;
                                case 2:
                                    int id = ScanManager.getIntInput("Güncellemek istediğiniz kitap idsini girin :");
                                    Map<Long, Books> booksDatabase = workintecthKutuphanesi.getBooks();
                                    Books updateBook = booksDatabase.get(Long.parseLong(String.valueOf(id)));
                                    int exitUpdate = 0;
                                    while( exitUpdate != 9 ){
                                        int updateChoice = ScanManager.getIntInput( "Güncellemek istediğiniz datanın değerini giriniz." + "\n" +
                                                " 1 " + " Kitap adı: " + updateBook.getName() + "\n"+
                                                " 2 " + " Yazar adı: " + updateBook.getAuthor() + "\n"+
                                                " 3 " + " Fiyatı: " + updateBook.getPrice() + "\n"+
                                                " 4 " + " Baskı: " + updateBook.getEdition() + "\n"+
                                                " 5 " + " Satın alma tarihi: " + updateBook.getDateOfPurchase() + "\n"+
                                                " 6 " + "değişiklikleri onayla." + "\n"+
                                                " 9 " + "Menüye dön");
                                        switch (updateChoice){
                                            case 1:
                                                String updateName = ScanManager.getStringInput("Yeni kitap ismi giriniz");
                                                updateBook.setName(updateName);
                                                System.out.println(updateBook);
                                                break;
                                            case 2:
                                                String updateAuthor = ScanManager.getStringInput("Yeni yazar ismi giriniz");
                                                updateBook.setAuthor(updateAuthor);
                                                System.out.println(updateBook);
                                                break;
                                            case 3:
                                                int updatePrice = ScanManager.getIntInput("Yeni fiyat giriniz");
                                                updateBook.setPrice(updatePrice);
                                                System.out.println(updateBook);
                                                break;
                                            case 4:
                                                String updateEdition = ScanManager.getStringInput("Yeni yazar ismi giriniz");
                                                updateBook.setEdition(updateEdition);
                                                System.out.println(updateBook);
                                                break;
                                            case 5:
                                                String updateDateOfPurchase = ScanManager.getStringInput("Yeni tarih giriniz (Örn giriş şekli yyyy-MM-dd");
                                                updateBook.setDateOfPurchase(LocalDate.parse(updateDateOfPurchase,DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                                                System.out.println(updateBook);
                                                break;
                                            case 6:
                                                workintecthKutuphanesi.updateBook(updateBook);
                                                System.out.println("Güncellenmiş kitap bilgileriniz :");
                                                System.out.println(updateBook);
                                                exitUpdate = 9;
                                                break;
                                            case 9:
                                                exitUpdate = 9;
                                                break;
                                        }
                                    }
                                    break;
                            }
                        }
                    case 7:
                        choice = 0;
                        break;
                    case 8:
                        workintecthKutuphanesi.logOut();
                        choice = 0;
                        isLogin = false;
                        System.out.println("Çıkış yapıldı...");
                        break;
                    case 9:
                        System.out.println("Programdan çıkılıyor...");
                        break;
                    case 10:
                        System.out.println("Sepetiniz : ");
                        if(workintecthKutuphanesi.getCart().isEmpty()){
                            System.out.println("Sepetinizde kitap bulunmamaktadır");
                        } else {
                            for(Books book : workintecthKutuphanesi.getCart()){
                                System.out.println(book);
                            }
                        }
                        break;
                }
            }

        }
        ScanManager.closeScanner();
    }

    public static void addToCartMethod(LibraryDatabase libraryDatabase){
        if(!libraryDatabase.getSearchResult().isEmpty()){
            Set<Books> searchResult1 = libraryDatabase.getSearchResult();
            for(Books book : searchResult1){
                System.out.println( book );
            }
            System.out.println("7 Ana menü");
            String addToCartIndex = ScanManager.getStringInput("Sepete eklemek istediğiniz kitabın id'sini girin birden fazla id'yi virgül ile ayırarak girebilirsiniz");
            String[] addToCartIdArrayString = addToCartIndex.split(",");
            Long[] addToCartIdArrayLong = new Long[addToCartIdArrayString.length];
            for(int i = 0 ; i < addToCartIdArrayLong.length; i++ ){
                addToCartIdArrayLong[i] = Long.parseLong(addToCartIdArrayString[i]);
            }
            if(libraryDatabase.getCurrentlyUser().getStatus().equals("Client")){
                libraryDatabase.addToCart(addToCartIdArrayLong);
            }else {
                libraryDatabase.addToCartAdmin(addToCartIdArrayLong);
            }

            System.out.println("Sepetiniz : ");
            for(Books book : libraryDatabase.getCart()){
                System.out.println(book);
            }
        }
    }

    }


