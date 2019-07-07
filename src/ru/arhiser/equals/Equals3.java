package ru.arhiser.equals;

import java.util.Collection;
import java.util.HashSet;

public class Equals3 {

    public static void main(String[] args) {
        test1();

    }

    private static void print(Object obj) {
        System.out.println(obj);
    }

    private static void printCollection(Collection collection) {
        for (Object obj: collection) {
            System.out.println(obj.toString());
        }
    }

    private static void test1() { //hashCode and hashCode only
        HashSet<Contact> contacts = new HashSet<>();

        Contact contact1 = new Contact(123, "Vasiliy", "+380681234136");

        contacts.add(contact1);


        //------------------------------------------


        Contact contact2 = new Contact(123, "Vasiliy", "+380689876543");

        contacts.remove(contact2);
        contacts.add(contact2);

        printCollection(contacts);
    }

    static class Contact {
        final int id;
        final String name;
        final String phone;

        private int hash;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;

            hash = id;
            hash = 31 * hash + (name != null ? name.hashCode() : 0);
            hash = 31 * hash + (phone != null ? phone.hashCode() : 0);
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

            if (hash != o.hashCode()) {
                return false;
            }

            if (id != contact.id) return false;
            if (hash != contact.hash) return false;
            if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
            return phone != null ? phone.equals(contact.phone) : contact.phone == null;
        }

        @Override
        public int hashCode() {
            return hash;
        }
    }

}
