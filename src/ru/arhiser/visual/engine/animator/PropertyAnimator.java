package ru.arhiser.visual.engine.animator;

import java.util.function.Consumer;

public class PropertyAnimator<T> implements Animator {
    protected int steps;
    protected int currentStep = 1;

    protected T startValue;
    protected T endValue;

    protected Evaluator<T> evaluator;

    protected Consumer<T> setter;

    public interface Evaluator<T> {
        T evaluate(T startValue, T endValue, int steps, int currentStep);
    }

    public PropertyAnimator(T startValue, T endValue, int steps, Consumer<T> setter, Evaluator<T> evaluator) {
        this.steps = steps;
        this.startValue = startValue;
        this.endValue = endValue;
        this.setter = setter;
        this.evaluator = evaluator;
    }

    @Override
    public boolean makeStep() {
        if (currentStep <= steps) {
            setter.accept(evaluator.evaluate(startValue, endValue, steps, currentStep));
            currentStep += 1;
        }
        return currentStep <= steps;
    }
}
