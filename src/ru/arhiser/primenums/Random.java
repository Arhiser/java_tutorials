package ru.arhiser.primenums;

public class Random {

    public static void main(String[] args) {
        //RandomGenerator goodGenerator = new RandomGenerator(1103515245, 12345, 32767, 1);

        RandomGenerator goodGenerator = new RandomGenerator(318, 51, 128, 1);

        for (int i = 0; i < 100; i++) {
            System.out.println(goodGenerator.get());
        }
    }

    public static class RandomGenerator {
        //Xnew = (a * Xold + c) mod m
        // m >=2
        // a   0 <= a <= m
        // X0  0 <= X0 <= m
        private final int a;
        private final int c;
        private final int m;
        private int xlast;

        public RandomGenerator(int a, int c, int m, int xlast) {
            this.a = a;
            this.c = c;
            this.m = m;
            this.xlast = xlast;
        }

        int get() {
            xlast = (a * xlast + c) % m;
            return Math.abs(xlast);
        }
    }

}
