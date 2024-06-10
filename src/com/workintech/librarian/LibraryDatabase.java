package com.workintech.librarian;
import com.workintech.books.Books;
import com.workintech.user.UserData;
import java.util.*;

public class LibraryDatabase {
    private static Set<Books> books;
    private static Map<String, UserData> userDatabase = new TreeMap<>();
    private UserData currentlyUser;
    private Set<Books> bookCart;
    private Set<Books> searchResult = new LinkedHashSet<>();

    public LibraryDatabase(Set<Books> data) {
        books = new HashSet<>();
        books.addAll(data);
    }

    public LibraryDatabase(Set<Books> data, UserData newUserData) {
        userDatabase = new TreeMap<>();
        userDatabase.put(newUserData.getEmail(), newUserData);
        books = new HashSet<>();
        books.addAll(data);
    }

    public void addUser(UserData newUserData){
            userDatabase.put(newUserData.getEmail(), newUserData);
            System.out.println("Yeni kullanıcı bilgileri" + newUserData);
    }

    public boolean logIn(String email, String password){
        UserData user =  userDatabase.get(email);
        if(user != null && user.getPassword().equals(password)){
            this.currentlyUser = user;
            System.out.println("Giriş başarılı! Hoşgeldiniz " + user.getName());
            System.out.println(currentlyUser);
            return true;
        } else {
            System.out.println("Giriş başarısız. Lütfen e-posta adresinizi ve şifrenizi kontrol edin.");
            return false;
        }
    }

    public void logOut(){
        currentlyUser = null;
    }

    public boolean loggedStatus(){
        return currentlyUser.getStatus().toLowerCase().equals("admin");
    }

    public UserData getCurrentlyUser() {
        return currentlyUser;
    }

    public Set<Books> getSearchResult() {
        return searchResult;
    }

    public void getBooks() {
        for(Books book : books){
            System.out.println(book);
        }
    }

    public void addBook(Books newBooks){
        books.add(newBooks);
        System.out.println("Bağışladığınız kitap sistemimize eklenmiştir. Teşekkür ederiz." + newBooks.toString());
    }



    public void findBook(Long id){
        for(Books book : books){
            if(book.getId().equals(id)){
                searchResult.clear();
                searchResult.add(book);
                System.out.println(searchResult);
            }
        }
    }

    public void findBook(String data){
        boolean isHaveit = false;
        for(Books book : books){
            if(book.getName().equals(data) || book.getAuthor().equals(data)){
                searchResult.add(book);
                System.out.println(searchResult);
                isHaveit = true;
            }
        }
        if( !isHaveit ){
            System.out.println("Sorry don't have that book.");
        }
    }

    public void findCategoryBooks(String category){
        for(Books book : books){
            if(book.getClass().getSimpleName().equals(category)){
                searchResult.add(book);
                System.out.println(searchResult);
            }
        }
    }

    public void addToCart(int... index){

    }



    @Override
    public String toString() {
        return "LibraryDatabase{" +
                "books=" + books +
                '}';
    }

}
