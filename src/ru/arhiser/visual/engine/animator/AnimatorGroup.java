package ru.arhiser.visual.engine.animator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimatorGroup implements Animator {
    ArrayList<Animator> animators = new ArrayList<>();

    public AnimatorGroup(Animator... animators) {
        this.animators.addAll(Arrays.asList(animators));
    }

    public AnimatorGroup(List<Animator> animators) {
        this.animators.addAll(animators);
    }


    @Override
    public boolean makeStep() {
        boolean notEnded = false;
        for (Animator animator: animators) {
            notEnded |= animator.makeStep();
        }
        return notEnded;
    }
}
