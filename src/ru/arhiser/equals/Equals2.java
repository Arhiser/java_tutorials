package ru.arhiser.equals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class Equals2 {

    public static void main(String[] args) {
        test4();

    }

    private static void print(Object obj) {
        System.out.println(obj);
    }

    private static void printCollection(Collection collection) {
        for (Object obj: collection) {
            System.out.println(obj.toString());
        }
    }

    private static void test4() { //hashCode and hashCode only
        Contact contact1 = new Contact(123, "Vasiliy", "+380681234136");
        Contact contact2 = new Contact(123, "Vasiliy", "+380689876543");

        HashSet<Contact> contacts = new HashSet<>();
        contacts.add(contact1);

        contacts.remove(contact2);
        contacts.add(contact2);

        printCollection(contacts);
    }

    static class Contact {
        int id;
        String name;
        String phone;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Contact)) return false;

            Contact contact = (Contact) o;

            if (id != contact.id) return false;
            if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
            return phone != null ? phone.equals(contact.phone) : contact.phone == null;
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (phone != null ? phone.hashCode() : 0);
            return result;
        }

    }

}
