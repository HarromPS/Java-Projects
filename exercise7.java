package chapter_12;

import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;
// import java.lang.Exception;

interface IssueBook {
    void issueBook();

    void returnBook();
}

interface BookInfo {
    void bookName();

    void issuedOn();

    void authorName();

    void issusedTo();
}

interface AddBooks {
    void addbooks();

    void deleteBooks();
}

class Menu extends Library {
    Scanner sc;

    Menu() {
        sc = new Scanner(System.in);
    }

    @Override
    public void show() {
        Library obj = new Library();
        System.out.println("__________Choose_________");
        System.out.println("______ 1) Add a Book ______");
        System.out.println("______ 2) Issue a Book ______");
        System.out.println("______ 3) Return Book ______");
        System.out.println("______ 4) Delete Book ______");
        System.out.println("______ 5) Exit Library ______");
        int x = 0;
        while (x != 7) {

            x = sc.nextInt();
            switch (x) {
                case 1:
                    obj.addbooks();
                    break;

                case 2:
                    obj.issueBook();
                    break;

                case 3:
                    obj.returnBook();
                    break;

                case 4:
                    obj.deleteBooks();
                    break;

                case 5:
                    obj.listAll();
                    break;

                case 6:
                    obj.about();
                    break;

                default:
                    System.out.println("_______Please Enter a valid input_______\nPress Enter");
                    sc.nextLine();
                    break;
            }
            System.out.println("__________Choose_________");
            System.out.println("______ 1) Add a Book ______");
            System.out.println("______ 2) Issue a Book ______");
            System.out.println("______ 3) Return Book ______");
            System.out.println("______ 4) Delete Book ______");
            System.out.println("______ 5) List All ______");
            System.out.println("______ 6) About ______");
            System.out.println("______ 7) Exit Library ______");
        }
        System.out.println("\n\t*********Thank you for Choosing our library*********");
        sc.close();

        // java.util.Scanner(System.in).close();
    }
}

class Library implements IssueBook, BookInfo, AddBooks {
    private String[] books; // book name
    private String[] bAuthor; // book author name
    private Date[] issueTime; // issusing time of the book
    private Date[] returnTime; // returning time of the book
    private String regNo; // returning time of the book

    // number of books it can issue: 3
    int bNos;

    // only 3 books from each student can be added
    int aBooks;
    String[] addedBooks;

    Library() {
        this.bNos = 0;
        this.books = new String[3];
        this.addedBooks = new String[3];
        this.issueTime = new Date[3];
        this.regNo = "";
        this.bAuthor = new String[3];
        this.returnTime = new Date[3];
    }

    Scanner sc = new Scanner(System.in);

    public void show() {

    }

    @Override
    public void issueBook() {
        if (this.bNos > 2) {
            System.out.println("^^^^^ You can only Issue 3 books from the library ^^^^^");
        } else {
            System.out.print("Enter the Name of the book: ");
            this.books[this.bNos] = sc.nextLine();
            if (this.bNos == 0) {
                this.issusedTo();
            }
            this.issuedOn();
            this.bNos++;
        }
        System.out.print("Press Enter ");
        sc.nextLine();
        this.show();
    }

    @Override
    public void returnBook() {
        if (this.bNos < 0) {
            System.out.println("No Books To Return ");
        } else {
            System.out.print("Enter the book name to return: ");
            String str = sc.nextLine();
            for (int i = 0; i < 3; i++) {
                if (str.equals(books[i])) {
                    this.books[i] = null;
                    returnTime[bNos] = returnTime1();
                    this.bNos--;
                    System.out.println(
                            "****** Book Returned Successfully ******\nReturned Time :: " + returnTime[bNos + 1]);
                    break;
                }
                if (i == (3 - 1)) {
                    System.out.println("BOOK NOT FOUND");
                }
            }
        }
        System.out.print("Press Enter ");
        sc.nextLine();
        show();
    }

    @Override
    public void bookName() {
        System.out.print("Please Enter the Name of the book you are adding: ");
        this.addedBooks[this.aBooks] = sc.nextLine();
    }

    @Override
    public void issuedOn() {
        Calendar c = Calendar.getInstance();
        this.issueTime[bNos] = c.getTime();
        System.out.println(("Isseed Time: " + this.issueTime[bNos]));
    }

    @Override
    public void authorName() {
        System.out.print("Enter the name of the author=>> ");
        this.bAuthor[aBooks] = sc.nextLine();
        this.aBooks++;
    }

    @Override
    public void issusedTo() {
        System.out.print("Enter your RegNo: ");
        this.regNo = sc.nextLine();
    }

    @Override
    public void addbooks() {
        if (this.aBooks > 2) {
            System.out.println("+++++ Book adding limit +++++");
            System.out.print("Press Enter ");
            sc.nextLine();
            show();
        } else {
            this.bookName();
            this.authorName();
            System.out.print("Press Enter ");
            sc.nextLine();
            show();
        }
    }

    @Override
    public void deleteBooks() {
        System.out.print("Enter the name of the book: ");
        String temp = sc.nextLine();
        for (int i = 0; i < 3; i++) {
            if (temp.equals(this.books[i])) {
                this.books[i] = null;
                System.out.println("0000000000 Book Deleted Successfully 0000000000");
            }
        }
        for (int i = 0; i < 3; i++) {
            if (temp.equals(this.addedBooks[i])) {
                this.addedBooks[i] = null;
                this.bAuthor[i] = null;
                System.out.println("0000000000 Book Deleted Successfully 0000000000");
            }
        }
        System.out.print("Press Enter ");
        sc.nextLine();
        show();
    }

    public Date returnTime1() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    // list all the deatils of the books about a student
    public void listAll() {
        System.out.println("_______ Books Added _______");
        System.out.println("Sr No\tBook Name\tAuthor Name");
        for (int i = 0; i < 3; i++) {
            if (addedBooks[i] != null) {
                System.out.println((i + 1) + "\t" + addedBooks[i] + "\t\t" + bAuthor[i]);
            } else {
                System.out.println((i + 1) + "\t" + addedBooks[i] + "\t\t" + bAuthor[i]);
            }
        }
        if (regNo != null) {
            System.out.println("\n\t_______ Issued Books: " + regNo + " _______\n");
            System.out.println("Sr No\tBook Name\tIssued");
            for (int i = 0; i < 3; i++) {
                if (books[i] != null) {
                    System.out.println((i + 1) + "\t" + books[i] + "\t\t" + "Yes");
                } else {
                    System.out.println((i + 1) + "\t" + books[i] + "\t\t" + "No");
                }
            }
            System.out.print("Press Enter ");
            sc.nextLine();
            show();
        } else {
            System.out.println("_______ Books Added _______");
            System.out.println("Sr No\tBook Name\t\tAuthor Name\tIssued");
            for (int i = 0; i < 3; i++) {
                if (books[i] != null) {
                    System.out.println((i + 1) + "\t" + books[i] + "\t\t" + bAuthor[i] + "\t\t\tYes");
                } else {
                    System.out.println((i + 1) + "\t" + books[i] + "\t\t" + bAuthor[i] + "\t\t\tNo");
                }
            }
            System.out.print("Press Enter ");
            sc.nextLine();
            show();
        }

    }

    public void about() {
        System.out.printf("\tThis Library application is a set of basic functions of Adding\n");
        System.out.printf("\t\tDeleting, Updating Books.\n");
        System.out.printf(
                "It has used the Core Java + OOPs concepts & Data Structures in this project.\n\n\t\t   Thank You for using this App\n\n");
        System.out.printf("\t\tDeveloped by ~ Hariom Pravin Shivhare\n\n");
        /// System.out.printf("\n\n\t\t\t\tENTER ANY KEY\n\n");
    }

}

public class exercise7 {

    static Scanner sc = new Scanner(System.in);

    /**
     * @param args
     */
    public static void main(String[] args) {
        /*
         * Exercise 7-> Library management system using all the concepts learnt until.
         * 1) Create a library management which is capable of issuing books to the
         * students.
         * 2) Book should have info like
         * - book name
         * - author name
         * - issued to
         * - issued on
         * 3) user should be able to add books, return issued books, issue books
         * 4) Assume that all the users are registered with their names in the central
         * database.
         */

        // User login
        String usr = new String("a");
        String passwd = new String("a");
        // String usr = new String("admin");
        // String passwd = new String("admin@123");
        int i = 3;
        while (i > 0) {
            System.out.print("\tUsername\n\t=>>");
            String username = sc.nextLine();
            System.out.print("\tPassword: \n\t=>>");
            String password = sc.nextLine();
            if (username.equals(usr) && password.equals(passwd)) {
                Menu m = new Menu();
                System.out.print("Press Enter ");
                sc.nextLine();
                m.show();
                i = -1;
            } else {
                System.out.println("((((    WRONG PASSWORD or USERNAME    ))))");
            }

            i--;
        }
        System.out.println("\n\nWaiting for Your Feedbacks");
    }
};

// for(i=1;i<=n;i++)
// {
// for (j = 1; j <= (2 * n); j++) {

// // Right Part of pattern
// if (i >= j)
// System.out.print(" ");
// else
// System.out.print("*");

// // Left Part of pattern
// if (i > ((2 * n) - j))
// System.out.print(" ");
// else
// System.out.print("*");
// }
// System.out.println();
// }