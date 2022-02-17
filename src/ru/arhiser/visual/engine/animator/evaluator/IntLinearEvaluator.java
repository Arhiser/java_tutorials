package ru.arhiser.visual.engine.animator.evaluator;

import ru.arhiser.visual.engine.animator.PropertyAnimator;

public class IntLinearEvaluator implements PropertyAnimator.Evaluator<Integer> {

    @Override
    public Integer evaluate(Integer startValue, Integer endValue, int steps, int currentStep) {
        int dif = endValue - startValue;
        return startValue + Math.round(((float) currentStep / steps) * dif);
    }
}
