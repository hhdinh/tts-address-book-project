package com.company;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    private static int menu() {
        System.out.println("1) Add an Entry\n" +
                "2) Remove an Entry\n" +
                "3) Search for a Specific Entry\n" +
                "4) Print Address Book\n" +
                "5) Delete Book\n" +
                "6) Quit\n");
        return getInput("Please choose what you'd like to do with the database: ", 6);
    }

    public static void addEntry(AddressBook addressBook) {
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();

        System.out.print("Email Address: ");
        String email = scanner.next();

        Entry entry = new Entry(firstName, lastName, phone, email);
        System.out.println(addressBook.addEntry(entry));
    }

    public static String removeEntry() {
        System.out.print("\nEnter an entry's email to remove: ");
        return scanner.nextLine();
    }

    public static void searchMenu(AddressBook addressBook) {
        int input;

        System.out.println("1) First Name\n" +
                "2) Last Name\n" +
                "3) Phone Number\n" +
                "4) Email Address\n" +
                "5) Return to Menu\n");
        input = getInput("Choose a search type: ", 5);
        if (input == 5) {
            return;
        }

        System.out.print("\nEnter your search: ");
        String searchQuery = scanner.nextLine();

        List<Entry> searchResults = null;
        switch (input) {
            case 1:
                searchResults = addressBook.searchEntry("first", searchQuery);
                break;
            case 2:
                searchResults = addressBook.searchEntry("last", searchQuery);
                break;
            case 3:
                searchResults = addressBook.searchEntry("phone", searchQuery);
                break;
            case 4:
                searchResults = addressBook.searchEntry("email", searchQuery);
                break;
            default:
                System.out.println("Something went wrong here!");
        }

        for (Entry entry : searchResults) {
            System.out.println(entry);
        }
    }

    public static int getInput(String message, int maxOptions) {
        boolean invalid;
        int input = 0;
        do {
            try {
                System.out.print(message);
                invalid = false;
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
                invalid = true;
                System.out.println("\nInvalid input! Please try again.");
            }
        } while (invalid || (input < 1 || input > maxOptions));
        scanner.nextLine();
        return input;
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        scanner = new Scanner(System.in);
        int input = 0;

        while (input != 6) {
            input = menu();
            switch (input) {
                case 1:
                    addEntry(addressBook);
                    break;
                case 2:
                    String email = removeEntry();
                    addressBook.removeEntry(email);
                    break;
                case 3:
                    searchMenu(addressBook);
                    break;
                case 4:
                    addressBook.printBook();
                    break;
                case 5:
                    addressBook.deleteBook();
                    break;
                case 6:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Something went wrong!");
            }
        }
    }
}
