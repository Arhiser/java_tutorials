package ru.arhiser.visual.samples.array;

import ru.arhiser.visual.engine.animator.Animator;
import ru.arhiser.visual.engine.animator.AnimatorGroup;
import ru.arhiser.visual.engine.animator.PropertyAnimator;
import ru.arhiser.visual.engine.animator.evaluator.IntLinearEvaluator;
import ru.arhiser.visual.engine.drawable.DrawableObject;
import ru.arhiser.visual.impl.IntArrayDrawable;

import java.util.ArrayList;
import java.util.List;

public class SortAnimationRecorder {

    IntArrayDrawable root;

    int[] lastArray;

    ArrayList<Animator> animatorQueue = new ArrayList<>();

    public SortAnimationRecorder(IntArrayDrawable root, int[] array) {
        this.lastArray = copyArray(array);
        this.root = root;
    }

    private int[] copyArray(int[] array) {
        int[] arrayCopy = new int[array.length];
        System.arraycopy(array, 0, arrayCopy, 0, array.length);
        return arrayCopy;
    }

    public void recordStep(int[] array) {
        ArrayList<Integer> moved = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (lastArray[i] != array[i]) {
                moved.add(lastArray[i]);
            }
        }
        if (!moved.isEmpty()) {
            ArrayList<Animator> animators = new ArrayList<>();
            for (Integer movedElement: moved) {
                int startX = root.getPositionX(indexOf(movedElement, lastArray));
                int endX = root.getPositionX(indexOf(movedElement, array));

                DrawableObject drawable = root.getDrawableForIndex(movedElement);
                PropertyAnimator<Integer> animator = new PropertyAnimator<>(startX, endX,
                        30, drawable::setX, new IntLinearEvaluator());

                animators.add(animator);
            }
            animatorQueue.add(new AnimatorGroup(animators));
        }
        lastArray = copyArray(array);
    }

    public List<Animator> getAnimationQueue() {
        return animatorQueue;
    }

    private static int indexOf(int needle, int[] haystack) {
        for (int i = 0; i < haystack.length; i++) {
            if (haystack[i] == needle) {
                return i;
            }
        }
        return -1;
    }

    private static <T> int indexOf(T needle, T[] haystack) {
        for (int i = 0; i < haystack.length; i++) {
            if (haystack[i] != null && haystack[i].equals(needle)
                    || needle == null && haystack[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
