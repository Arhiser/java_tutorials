package ru.arhiser.funcops;

import ru.arhiser.funcops.inter.Action1;
import ru.arhiser.funcops.inter.Func1;
import ru.arhiser.funcops.inter.Func2;

import java.util.ArrayList;
import java.util.List;

public class FuncUtils {

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
        ArrayList<X> result = new ArrayList<>();
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
