package ru.arhiser.funcops.monad;

import ru.arhiser.funcops.Client;

import java.util.function.Function;

public class OptionalMain {

    public static void main(String[] args) {
        Client client = new Client("Harry Carter", 15, true);
        Optional.from(client)
                .map(Client::getName)
                .map(s -> s.split(" "))
                .map(c -> {
                    System.out.println(c[0]);
                    return c[0];
                });
    }

    public static class Optional<T> {

        private static Optional<?> empty = new Optional<>(null);

        T value;

        private Optional(T value) {
            this.value = value;
        }

        public static <T> Optional<T> from(T value) {
            if (value != null) {
                return new Optional<>(value);
            } else {
                return (Optional<T>) empty;
            }
        }

        public <U> Optional<U> map(Function<T, U> mapFunc) {
            if (value != null) {
                return new Optional<U>(mapFunc.apply(value));
            } else {
                return (Optional<U>) empty;
            }
        }
    }
}
