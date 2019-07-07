package ru.arhiser.equals;

import java.util.ArrayList;
import java.util.HashSet;

public class Equals {

    public static void main(String[] args) {
        test3();

    }

    private static void print(Object obj) {
        System.out.println(obj);
    }

    private static void test1() {
        Contact contact1 = new Contact(124, "Vasiliy", "+380681234136");
        Contact contact2 = new Contact(124, "Vasiliy", "+380681234136");

        print(contact1.hashCode());
        print(contact2.hashCode());

        print(contact1.equals(contact2));
    }

    private static void test2() {
        Contact contact1 = new Contact(123, "Vasiliy", "+380681234136");
        Contact contact2 = new Contact(123, "Vasiliy", "+380681234136");

        ArrayList<Contact> contacts = new ArrayList<>();

        contacts.add(contact1);

        print(contacts.contains(contact2));

        print(contacts.indexOf(contact2));

        contacts.remove(contact2);
        print(contacts.size());

    }

    private static void test3() {
        HashSet<Contact> contacts = new HashSet<>();

        Contact contact1 = new Contact(123, "Vasiliy", "+380681234136");
        Contact contact2 = new Contact(123, "Vasiliy", "+380681234136");

        contacts.add(contact1);

        contacts.add(contact2);

        print(contacts.size());
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
