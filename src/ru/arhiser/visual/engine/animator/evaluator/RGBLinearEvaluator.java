package ru.arhiser.visual.engine.animator.evaluator;

import ru.arhiser.visual.engine.animator.PropertyAnimator;

public class RGBLinearEvaluator implements PropertyAnimator.Evaluator<Integer> {

    @Override
    public Integer evaluate(Integer startValue, Integer endValue, int steps, int currentStep) {
        int rStart = startValue >> 16 & 0xff;
        int gStart = startValue >> 8 & 0xff;
        int bStart = startValue & 0xff;

        int rEnd = endValue >> 16 & 0xff;
        int gEnd = endValue >> 8 & 0xff;
        int bEnd = endValue & 0xff;

        int rValue = rStart + Math.round(((float) currentStep / steps) * (rEnd - rStart));
        int gValue = gStart + Math.round(((float) currentStep / steps) * (gEnd - gStart));
        int bValue = bStart + Math.round(((float) currentStep / steps) * (bEnd - bStart));

        return 0xff000000 | rValue << 16 | gValue << 8 | bValue;
    }
}
