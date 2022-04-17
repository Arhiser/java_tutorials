package ru.arhiser.visual.samples.array;

import ru.arhiser.sort.QuickSort;
import ru.arhiser.visual.engine.animator.Animator;
import ru.arhiser.visual.engine.scene.Scene;
import ru.arhiser.visual.impl.IntArrayDrawable;
import ru.arhiser.visual.raster.Raster;
import ru.arhiser.visual.raster.RasterWindow;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ArraySample {
    public static void main(String[] args) {

        Raster raster = new Raster(800, 600);

        RasterWindow rasterWindow = new RasterWindow(raster);

        int[] array = new int[] {64, 42, 68, 41, 32, 53, 16, 24, 57, 48, 74, 55, 36};

        IntArrayDrawable arrayDrawable = new IntArrayDrawable(0, 0, 800, 600, array);

        CombSortAnimationProducer combSortAnimationProducer = new CombSortAnimationProducer(array);
        List<Animator> animatorList = combSortAnimationProducer.makeAnimation(arrayDrawable);

        Scene scene = new Scene(raster, arrayDrawable, animatorList);

        RasterFileWriter rasterFileWriter = new RasterFileWriter(raster, "D:\\arhiser\\youtube\\comb_sort\\comb\\");

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                boolean finished;
                finished = !scene.renderNext();
                //rasterFileWriter.writeNextFrame();
                rasterWindow.updateRaster();

                if (finished) {
                    this.cancel();
                    timer.cancel();
                }
            }
        }, 0, 1);
    }
}
