package ru.arhiser.funcops.monad;

import ru.arhiser.funcops.Client;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Client client = new Client("Harry Carter", 15, true);
        Monad.from(client)
                .map(Client::getName)
                .map(s -> s.split(" "))
                .map(c -> {
                    System.out.println(c[0]);
                    return c[0];
                });

    }

    public static class Monad<T> {
        final T value;

        private Monad(T value) {
            this.value = value;
        }

        public static <T> Monad<T> from(T value) {
            return new Monad<>(value);
        }

        public <U> Monad<U> map(Function<T, U> mapFunc) {
            return flatMap(val -> new Monad<>(mapFunc.apply(val)));
        }

        public <U> Monad<U> flatMap(Function<T, Monad<U>> mapFunc) {
            return mapFunc.apply(value);
        }
    }
}
