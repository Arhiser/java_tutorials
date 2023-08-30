package ru.arhiser.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.arhiser.funcops.Client;

import java.util.ArrayList;

public class Main1 {
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

        clients.stream()
                .filter(Client::isActive)
                .map(Client::getName)
                .forEach(System.out::println);

        System.out.println("111111111111111");

        Flowable<Client> clientFlowable = Flowable.create(new FlowableOnSubscribe<Client>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Client> emitter) throws Throwable { // subscribeOn
                for (Client c: clients) {
                    emitter.onNext(c);
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        clientFlowable
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .filter(Client::isActive)               // observeOn
                .map(Client::getName)
                .forEach(System.out::println);

        System.out.println("2222222222222222");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
