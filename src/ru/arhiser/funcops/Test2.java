package ru.arhiser.funcops;

import ru.arhiser.funcops.inter.Action1;
import ru.arhiser.funcops.inter.Func1;
import ru.arhiser.funcops.inter.Func2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Test2 {

    public static void main(String[] args) {
        ArrayList<Client> clients = new ArrayList<>();

        clients.add(new Client("Harry Carter", 15, true));
        clients.add(new Client("Adam Atkinson", 5, true));
        clients.add(new Client("Bobby Robertson", 8, true));
        clients.add(new Client("Liam Ellis", 6, false));
        clients.add(new Client("Alex Thomson", 9, true));
        clients.add(new Client("Ryan Ayala", 4, false));
        clients.add(new Client("Kale Molina", 3, true));
        clients.add(new Client("Otto Holman", 7, false));

        forEach(clients, c -> c.setBalance(c.getBalance() - 2));

        print(map(clients, Client::getName));

        print(find(clients, c -> c.getName().startsWith("Alex")));

        print(filter(clients, Client::isActive));
        print(map(filter(clients, c -> c.getBalance() > 5), Client::getName));

        print(clients);

        print(fold(clients, 0, (client, sum) -> client.getBalance() + sum));

    }

    private static void print(Collection items) {
        for(Object item: items) {
            System.out.println(item.toString());
        }
    }

    private static void print(Object item) {
        System.out.println(item.toString());
    }

    public static <X> void forEach(List<X> list, Action1<X> action1) {
        for(X item: list) {
            action1.apply(item);
        }
    }

    public static <X, Y> List<Y> map(List<X> list, Func1<X, Y> mapFunction) {
        ArrayList<Y> result = new ArrayList<>();
        for(X item: list) {
            result.add(mapFunction.apply(item));
        }
        return result;
    }

    public static <X> List<X> filter(List<X> list, Func1<X, Boolean> filterFunction) {
        ArrayList<X> result = new ArrayList<>();
        for(X item: list) {
            if (filterFunction.apply(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static <X> X find(List<X> list, Func1<X, Boolean> findFunction) {
        for(X item: list) {
            if (findFunction.apply(item)) {
                return item;
            }
        }
        return null;
    }

    public static <X, Y> Y fold(List<X> list, Y initValue, Func2<X, Y, Y> foldFunction) {
        Y result = initValue;
        for(X item: list) {
            result = foldFunction.apply(item, result);
        }
        return result;
    }

}
