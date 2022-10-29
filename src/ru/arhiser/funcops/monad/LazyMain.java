package ru.arhiser.funcops.monad;

import ru.arhiser.funcops.Client;

import java.util.function.Function;
import java.util.function.Supplier;

public class LazyMain {

    public static void main(String[] args) {

        Client client = new Client("111 222", 15, true);
        Lazy<String> composed = Lazy.from(client)
                .map(Client::getName)
                .map(s -> s.split(" "))
                .map(c -> {
                   System.out.println(c[0]);
                   return c[0];
                });

        composed.get();
    }

    public static class Lazy<T> {
        T value;

        Supplier<T> supplier;

        private Lazy(T value) {
            this.value = value;
        }

        private Lazy(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        public static <T> Lazy<T> from(T value) {
            return new Lazy<>(value);
        }

        public T get() {
            if (value == null) {
                value = supplier.get();
            }
            return value;
        }

        public <U> Lazy<U> map(Function<T, U> mapFunc) {
            return new Lazy<>(() -> mapFunc.apply(get()));
        }

        public <U> Lazy<U> flatMap(Function<T, Lazy<U>> mapFunc) {
            return mapFunc.apply(value);
        }
    }
}
