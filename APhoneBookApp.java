// Amaan Seraj
// 02/10/2024
// CS & 145
// Assignment 2: Phone Book
// This program makes a phone book

import java.util.Scanner;

class PhoneBook {
    String contactName;
    String contactAddress;
    String contactCity;
    String contactPhoneNumber;
    int contactNumber;
    PhoneBook nextContact;

    // initialize phone book object
    public PhoneBook(String contactName, String contactAddress, String contactCity, 
                     String contactPhoneNumber, int contactNumber) {
        this.contactName = contactName;
        this.contactAddress = contactAddress;
        this.contactCity = contactCity;
        this.contactPhoneNumber = contactPhoneNumber;
        this.contactNumber = contactNumber;
        this.nextContact = null;
    }

    // returns a string representation of the object
    public String toString() {
        return "Contact Number: " + contactNumber + ". Name: " + contactName + 
               ", Address: " + contactAddress + ", City: " + contactCity + 
               ", Phone Number: " + contactPhoneNumber;
    }
}


class PhoneBookManager {
    PhoneBook startContact;
    int contactCount; 

    // initialzies a new object
    public PhoneBookManager() {
        this.startContact = null;
        this.contactCount = 1; 
    }

    // Displays all contacts in the phone book
    void display() {
        PhoneBook temp = startContact;
        boolean isEmpty = true;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.nextContact;
            isEmpty = false;
        }
        if (isEmpty)
            System.out.println("Phonebook is empty");
    }

    // Adds a new contact to the phone book
    void add(PhoneBook pb) {
        if (startContact == null) {
            startContact = pb;
        } else {
            PhoneBook current = startContact;
            while (current.nextContact != null) {
                current = current.nextContact;
            }
            current.nextContact = pb;
        }
        pb.contactNumber = contactCount++; 
    }

    // Modifies the contact
    void modify(int contactNumber) {
        PhoneBook temp = startContact;
        boolean found = false;
        while (temp != null) {
            if (temp.contactNumber == contactNumber) {
                found = true;
                break;
            }
            temp = temp.nextContact;
        }
        if (found) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter new details");
            System.out.print("Name: ");
            temp.contactName = sc.next();
            System.out.print("Address: ");
            temp.contactAddress = sc.next();
            System.out.print("City: ");
            temp.contactCity = sc.next();
            System.out.print("Phone Number: ");
            temp.contactPhoneNumber = sc.next();
            System.out.println("Successfully updated");
        } else {
            System.out.println("Contact number not found!");
        }
    }

    // Deletes the contact
    void delete(int contactNumber) {
        if (startContact == null) {
            System.out.println("Phonebook is empty");
            return;
        }

        if (startContact.contactNumber == contactNumber) {
            startContact = startContact.nextContact;
            System.out.println("Contact deleted successfully");
            return;
        }

        PhoneBook current = startContact;
        PhoneBook prev = null;
        boolean found = false;
        while (current != null) {
            if (current.contactNumber == contactNumber) {
                found = true;
                break;
            }
            prev = current;
            current = current.nextContact;
        }
        if (found) {
            prev.nextContact = current.nextContact;
            System.out.println("Contact deleted successfully");
        } else {
            System.out.println("Contact number not found!");
        }
    }
}


public class APhoneBookApp {
    // Main method
    public static void main(String[] args) {
        PhoneBookManager pbManager = new PhoneBookManager();
        Scanner sc = new Scanner(System.in);

        // Menu
        System.out.println("1. Add new contact\n2. View contacts\n3. Modify contact\n" + 
        "4. Delete contact\n5. Exit");
        int choice = sc.nextInt();

        while (choice != 5) {
            if (choice == 1) {
                System.out.println("Enter details:");
                System.out.print("Name: ");
                String name = sc.next();
                System.out.print("Address: ");
                String address = sc.next();
                System.out.print("City: ");
                String city = sc.next();
                System.out.print("Phone Number: ");
                String phoneNumber = sc.next();
                PhoneBook pbook = new PhoneBook(name, address, city, phoneNumber, 0);
                pbManager.add(pbook);
            } else if (choice == 2) {
                pbManager.display();
            } else if (choice == 3) {
                System.out.println("Enter contact number to modify");
                int number = sc.nextInt();
                pbManager.modify(number);
            } else if (choice == 4) {
                System.out.println("Enter contact number to delete");
                int number = sc.nextInt();
                pbManager.delete(number);
            } else {
                System.out.println("Invalid choice!");
            }
            System.out.println("1. Add new contact\n2. View contacts\n3. Modify contact\n" + 
            "4. Delete contact\n5. Exit");
            choice = sc.nextInt();
        }
    }
}