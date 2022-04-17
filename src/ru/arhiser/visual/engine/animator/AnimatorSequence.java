package ru.arhiser.visual.engine.animator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimatorSequence implements Animator {
    ArrayList<Animator> animators = new ArrayList<>();

    public AnimatorSequence(Animator... animators) {
        this.animators.addAll(Arrays.asList(animators));
    }

    public AnimatorSequence(List<Animator> animators) {
        this.animators.addAll(animators);
    }


    @Override
    public boolean makeStep() {
        if (!animators.isEmpty()) {
            boolean ended = !animators.get(0).makeStep();
            if (ended) {
                animators.remove(0);
            }
        }
        return !animators.isEmpty();
    }
}
