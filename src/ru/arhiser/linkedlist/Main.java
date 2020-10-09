package ru.arhiser.linkedlist;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        SingleLinkList<Contact> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Contact(123, "Васильев Евстахий Борисович", "+129381832"));
        contactList.addToEnd(new Contact(151, "Коновалов Степан Петрович", "+234432334"));
        contactList.addToEnd(new Contact(332, "Калинин Артём Валериевич", "+2234234423"));
        contactList.addToEnd(new Contact(432, "Предыбайло Григорий Анатолиевич", "+2342344234"));
        contactList.addToEnd(new Contact(556, "Степанов Мирослав Андреевич", "+6678877777"));

        for(Contact contact: contactList) {
            System.out.println(contact);
        }

        contactList.reverse();

        System.out.println("------------------------");

        for(Contact contact: contactList) {
            System.out.println(contact);
        }
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
    }

    public static class SingleLinkList<T> implements Iterable<T> {

        ListItem<T> head;
        ListItem<T> tail;

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void addToEnd(T item) {
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;
            if (isEmpty()) {
                head = newItem;
                tail = newItem;
            } else {
                tail.next = newItem;
                tail = newItem;
            }
        }

        public void reverse() {
            if (!isEmpty() && head.next != null) {
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}
