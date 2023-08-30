package ru.arhiser.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import ru.arhiser.Utils;
import ru.arhiser.funcops.Client;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main2 {
    public static void main(String[] args) {
        Observable<Date> timeObservable = Observable.create(new ObservableOnSubscribe<Date>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Date> emitter) throws Throwable {
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        emitter.onNext(new Date());
                    }
                }, 0, 1000);
            }
        });

        timeObservable.subscribe(new Consumer<Date>() {
            @Override
            public void accept(Date date) throws Throwable {
                System.out.println(date.toString());
            }
        });

        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel picLabel = new JLabel();
        frame.getContentPane().add(picLabel, BorderLayout.CENTER);

        frame.setVisible(true);

        Disposable subscription = timeObservable.subscribe(new Consumer<Date>() {
            @Override
            public void accept(Date date) throws Throwable {
                picLabel.setText(date.toString());
            }
        });

        //subscription.dispose();
    }
}
