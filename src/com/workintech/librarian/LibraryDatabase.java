package com.workintech.librarian;
import com.workintech.books.BookStatus;
import com.workintech.books.Books;
import com.workintech.user.Client;
import com.workintech.user.ClientRentBook;
import com.workintech.user.UserData;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class LibraryDatabase implements LibraryManagement, BookManagement, CartManagement, RentManagement{
    private static Map<String, UserData> userDatabase;
    private static Map<Long, Books> books;
    private UserData currentlyUser;
    private Set<Books> searchResult;
    private Set<Books> cart = new HashSet<>();


    public LibraryDatabase(Set<Books> data, UserData... newUserData) {
        userDatabase = new TreeMap<>();
        books = new TreeMap<>();
        for(UserData user: newUserData){
            userDatabase.put(user.getEmail(), user);
        }
        for(Books book : data){
            books.put(book.getId(),book);
        }
    }

    //Getter Methods
    public static Map<String, UserData> getUserDatabase() {
        return userDatabase;
    }

    public Map<Long, Books> getBooks() {
        return books;
    }

    public Books getBook(Long id){
        return books.get(id);
    }

    public UserData getCurrentlyUser() {
        return currentlyUser;
    }

    public Set<Books> getSearchResult() {
        return searchResult;
    }

    public Set<Books> getCart() {
        return cart;
    }

    public Books getBookForUpdate(Long id) {
        return books.get(id);
    }

    //LibraryManagement implementations
    @Override
    public boolean logIn(String email, String password){
        if(userDatabase == null){
            userDatabase = new TreeMap<>();
        }
        UserData user =  userDatabase.get(email);
        if(user != null && user.getPassword().equals(password)){
            this.currentlyUser = user;
            System.out.println("Giriş başarılı! Hoşgeldiniz " + user.getName());
            return true;
        } else {
            System.out.println("Giriş başarısız. Lütfen e-posta adresinizi ve şifrenizi kontrol edin.");
            return false;
        }
    }

    @Override
    public void logOut(){
        currentlyUser = null;
    }

    @Override
    public boolean loggedStatus(){
        return currentlyUser.getStatus().toLowerCase().equals("admin");
    }

    @Override
    public void addUser(UserData newUserData){
        if(userDatabase == null){
            userDatabase = new TreeMap<>();
        }
        userDatabase.put(newUserData.getEmail(), newUserData);
        System.out.println("Yeni kullanıcı bilgileri" + newUserData);
    }

    //Book Management implementations
    @Override
    public void addBook(Books newBooks){
        books.put(newBooks.getId(),newBooks);
        System.out.println("Bağışladığınız kitap sistemimize eklenmiştir. Teşekkür ederiz." + newBooks);
    }

    @Override
    public void findBook(Long id){
        List<Books> booksList = new ArrayList<>();
        for(Map.Entry<Long, Books> entry : books.entrySet()){
            booksList.add(entry.getValue());
        }
        searchResult =  new LinkedHashSet<>();
        boolean isHaveit = false;
        for(Books book : booksList){
            if(book.getId().equals(id)){
                searchResult.add(book);
                isHaveit = true;
            }
        }
        if(!isHaveit){
            System.out.println("No books with Id " + id + " can be found.");
        }
    }

    @Override
    public void findBook(String data){
        List<Books> booksList = new ArrayList<>();
        for(Map.Entry<Long, Books> entry : books.entrySet()){
            booksList.add(entry.getValue());
        }
        searchResult = new LinkedHashSet<>();
        boolean isHaveit = false;
        for(Books book : booksList){
            String simpleName = book.getName().toLowerCase().replaceAll("[^a-zA-Z]", "");
            String simpleData = data.toLowerCase().replaceAll("[^a-zA-Z]", "");
            String simpleAuthor = book.getAuthor().toLowerCase().replaceAll("[^a-zA-Z]", "");
            if(simpleName.equals(simpleData) || simpleAuthor.equals(simpleData)){
                searchResult.add(book);
                isHaveit = true;
            }
        }
        if( !isHaveit ){
            System.out.println("Sorry don't have that book.");

        }
    }

    @Override
    public void findCategoryBooks(String category){
        searchResult = new LinkedHashSet<>();
        System.out.println("Category search result :");
        for(Map.Entry<Long, Books> entry : books.entrySet()){
            if(entry.getValue().getClass().getSimpleName().equals(category)){
                searchResult.add(entry.getValue());
            }
        }
    }

    @Override
    public void updateBook(Books book){
        Books oldBook = books.get(book.getId());
        books.replace(book.getId(),book);
        cart.remove(oldBook);
    }

    @Override
    public void removeBooks(String[] removeBookIds){
        List<Long> idList = new ArrayList<>();
        for(String id : removeBookIds){
            idList.add(Long.parseLong(id));
        }
        for(Long id : idList){
            if(books.get(id) != null ){
                Books book = books.get(id);
                books.remove(id);
                cart.remove(book);
                System.out.println(book.getId() + "'li " + book.getName() + " isimli kitap kütüpahane veritabanından silinmiştir.");
            } else{
                System.out.println( id + " id'li kitap bulunamamıştır.");
            }
        }
    }

    //Cart Management
    @Override
    public void addToCart(Long[] addToCartIdArray){
        List<Long> idList = new ArrayList<>(Arrays.asList(addToCartIdArray));
        if( cart == null){
            cart = new LinkedHashSet<>();
        }

        int userRentSize = ((Client) currentlyUser).getRentBooks() == null ? 0 : ((Client) currentlyUser).getRentBooks().size();
        int maxRentSize = 5;

        Iterator<Books> iterator =searchResult.iterator();
        while( iterator.hasNext()){
            Books book = iterator.next();

            int availableRentSize = maxRentSize - userRentSize - cart.size();

            if (availableRentSize == 0) {
                System.out.println( "Kiraladığınız " + userRentSize + " adet kitap vardır. Maksimum 5 adet kitap kiralanabilir. Daha fazla kitap kiralayamazsınız.");
                break;
            } else {
                if( idList.contains(book.getId())) {
                    if(book.getStatus().equals(BookStatus.AVAILABLE)){
                        updateBookStatus(book);
                        iterator.remove();
                        cart.add(book);
                        idList.remove(book.getId());
                    }
                    else{
                        System.out.println(book.getId() + " id'li kitap " + (cart.contains(book) ? "zaten sepetinizde." : "kiralanmıştır, bu yüzden sepetinize eklenemedi."));
                    }
                    idList.remove(book.getId());
                }

            }
        }
    }

    @Override
    public void addToCartAdmin(Long[] addToCartIdArray){
        Set<Long> idSet = new HashSet<>(Arrays.asList(addToCartIdArray));

        if( cart == null){
            cart = new LinkedHashSet<>();
        }

        Iterator<Books> iterator =searchResult.iterator();
        while( iterator.hasNext()){
            Books book = iterator.next();
            Long bookId = book.getId();

            if (idSet.contains(bookId)) {
                if (cart.contains(book)) {
                    System.out.println("Bu kitap zaten sepetinizde.");
                    iterator.remove();
                } else {
                    cart.add(book);
                    iterator.remove();
                }
                idSet.remove(bookId);
            }
        }
    }

    @Override
    public void removeToCart(Long[] removeBooksIds){
        if( cart != null ){
            List<Long> removeIdList = new ArrayList<>(Arrays.asList(removeBooksIds));
            Iterator<Books> iterator = cart.iterator();
            while( iterator.hasNext()){
                Books book = iterator.next();
                if( removeIdList.contains(book.getId())){
                    updateBookStatus(book);
                    iterator.remove();
                    removeIdList.remove(book.getId());
                }
            }
        }else {
            System.out.println("Your cart empty");
        }
    }

    //Rent Management Implementation
    @Override
    public void updateBookStatus(Books updateBook){
        Books selectedBook = books.get(updateBook.getId());
        if(selectedBook.getStatus().equals(BookStatus.AVAILABLE)){
            selectedBook.setStatus(BookStatus.UNAVAILABLE);
        } else if(selectedBook.getStatus().equals(BookStatus.UNAVAILABLE)){
            selectedBook.setStatus(BookStatus.AVAILABLE);
        }
    }

    @Override
    public void rentToBook(){
        Set<ClientRentBook> clientRentBooks = new LinkedHashSet<>();
        Iterator<Books> iterator = cart.iterator();
        while( iterator.hasNext()){
            Books book = iterator.next();
            int customerRentedBooksCount = ((Client) currentlyUser).getRentBooks().isEmpty() ? 0 : ((Client)currentlyUser).getRentBooks().size();
            if( customerRentedBooksCount + cart.size() <= 5){
                clientRentBooks.add(new ClientRentBook(book, LocalDate.now()));
                iterator.remove();
                for(ClientRentBook book11 : clientRentBooks){
                    System.out.println("1 adet " + ((book11).getRentedBook().getName() + ", " +
                            book11.getRentDate() + " tarihinde kiraladınız." + book11.getRentDate().plusDays(5)  +
                            " tarihine kadar ücretsiz kiralayabilirsiniz. 5 günden sonraki hergün için 5 tl kiralama ücreti ödemeniz gerekir."));
                }

            } else {
                System.out.println("The number of books that can be rented per person is 5.");
                System.out.println(book.getName() + "book exceeds the rent limit");
            }

        }
        ((Client)currentlyUser).setRentBooks(clientRentBooks);
        Set<ClientRentBook> rentedUserBooks = ((Client)currentlyUser).getRentBooks();
        System.out.println("Rented books data : ");
        for(ClientRentBook book : rentedUserBooks){
            System.out.println(book);
        }
    }

    @Override
    public void returnRentedBooksId(String[] ids){
        for(String id : ids){
            Long rentedId = Long.parseLong(id.trim());
            Set<ClientRentBook> UserRentedBook = ((Client)currentlyUser).getRentBooks();
            Iterator<ClientRentBook> iterator = UserRentedBook.iterator();
            while(iterator.hasNext()){
                ClientRentBook book = iterator.next();
                if(book.getRentedBook().getId().equals(Long.parseLong(id))){
                    findBook(Long.parseLong(id));
                    for(Books findingBook : searchResult){
                        updateBookStatus(findingBook);
                        iterator.remove();
                        int rentedDaysCount = (int) ChronoUnit.DAYS.between(book.getRentDate(), LocalDate.now());
                        System.out.println( book.getRentedBook().getName() + " kitabı teslim alınmıştır. Kitabı " +
                                book.getRentDate() + " tarihinde kiraladınız " + LocalDate.now() + " tarihinde teslim ettiniz. " +
                                ( rentedDaysCount > 5 ? ((rentedDaysCount-5) * 5) + " tl ödeme yamanız gerekmektedir. " +
                                        (rentedDaysCount-5) + " gün ücretli kiralama yaptınız. " : " Kiralama süreniz 5 günü geçmediği için ödeme yapmanıza gerek yoktur.")
                        );

                    }

                }
            }
        }
    }

    @Override
    public void getRentedBooksData(){
        for (Map.Entry<String, UserData> entry : userDatabase.entrySet()) {
            UserData user = entry.getValue();
            if (user.getStatus().equals("Client") && currentlyUser.getStatus().equals("Admin")) {
                Client client = (Client) user;
                System.out.println(client.getName() + " isimli " +
                        client.getId() + " id'li kullanıcı bu kiraladığı kitaplar:");
                for (ClientRentBook rentBook : client.getRentBooks()) {
                    System.out.println(" - " + rentBook.getRentedBook());
                }
            } else if(user.getStatus().equals("Client") && currentlyUser.getId() == user.getId()){
                System.out.println("Kiraladığınız kitaplar:");
                for(ClientRentBook book : ((Client)currentlyUser).getRentBooks()){
                    System.out.println( " - " + book.getRentedBook() );
                }
                ;
            }
        }
    }

    @Override
    public String toString() {
        return "LibraryDatabase{" +
                "books=" + books +
                '}';
    }
}
