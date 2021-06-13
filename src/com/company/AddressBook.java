package com.company;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private List<Entry> addressBook;

    public AddressBook() {
        this.addressBook = new ArrayList<>();
    }

    public String addEntry(Entry entry) {
        if (this.addressBook.contains(entry)) {
            return "Error! This email is already in the address book.";
        }
        this.addressBook.add(entry);
        return entry.toString() + "\nAdded new entry!\n";
    }

    public void removeEntry(String email) {
        for (int i = 0; i < addressBook.size(); i++) {
            if (this.addressBook.get(i).getEmailAddress().equals(email)) {
                System.out.println("\nDeleted the following entry:\n" + this.addressBook.get(i).toString());
                addressBook.remove(i);
                return;
            }
        }
        System.out.println("\nEntry not found!\n");
    }

    public List<Entry> searchEntry(String searchType, String searchQuery) {
        List<Entry> result = new ArrayList<>();

        switch (searchType) {
            case "first":
                for (Entry entry : this.addressBook) {
                    if (entry.getFirstName().contains(searchQuery)) {
                        result.add(entry);
                    }
                }
                break;
            case "last":
                for (Entry entry : this.addressBook) {
                    if (entry.getLastName().contains(searchQuery)) {
                        result.add(entry);
                    }
                }
                break;
            case "phone":
                for (Entry entry : this.addressBook) {
                    if (entry.getPhoneNumber().contains(searchQuery)) {
                        result.add(entry);
                    }
                }
                break;
            case "email":
                for (Entry entry : this.addressBook) {
                    if (entry.getEmailAddress().contains(searchQuery)) {
                        result.add(entry);
                    }
                }
                break;
            default:
                System.out.println("\nInvalid search option!");
        }

        if (result.isEmpty()) {
            System.out.println("\nNo results found!\n");
        }
        return result;
    }

    public void printBook() {
        if (this.addressBook.isEmpty()) {
            System.out.println("\nAddress Book is empty!\n");
        }
        for (Entry entry : this.addressBook) {
            System.out.println(entry.toString());
        }
    }

    public void deleteBook() {
        this.addressBook.clear();
        System.out.println("\nAddress book cleared!\n");
    }
}
