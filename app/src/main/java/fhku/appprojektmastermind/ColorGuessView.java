package fhku.appprojektmastermind;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public class ColorGuessView extends ViewGroup {

    private ColorGuess colorGuess;

    private final int DEFAULT_DIAMETER = 50;
    private int single_spacing;
    private int diameter;
    private int padding_top;

    public ColorGuessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initColorGuess() {
        if (colorGuess.getColorBalls().isEmpty()) {
            return;
        }
        for (ColorBall colorBall : colorGuess.getColorBalls()) {
            ColorBallView newColorBallView = new ColorBallView(getContext());
            newColorBallView.setColorBall(colorBall);
            this.addView(newColorBallView);
        }

    }

    public void draw(ColorGuess colorGuess) {
        this.colorGuess = colorGuess;
        invalidate();
    }

    public void setColorGuess(ColorGuess colorGuess) {
        this.colorGuess = colorGuess;
        initColorGuess();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int CHILD_COUNT = getChildCount();

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //TODO: overthink spacing, sizing and padding calculations
        int spacing = width / 5;
        single_spacing = spacing / (CHILD_COUNT + 1);

        padding_top = height / 10;

        int availableWidth = width - spacing;
        diameter = (CHILD_COUNT == 0 ?
                DEFAULT_DIAMETER : availableWidth / CHILD_COUNT); //TODO avoid divide by zero
//        int diameterHeight = height - (padding_top * 2);
        diameter = Math.min(diameter, height - (padding_top * 2));


        for (int i = 0; i < CHILD_COUNT; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            LayoutParams layoutParams = child.getLayoutParams();
            layoutParams.width = diameter;
            layoutParams.height = diameter;
        }


        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int startPosition = (diameter + single_spacing) * i + single_spacing;
            child.layout(
                    startPosition,
                    padding_top,
                    startPosition + diameter,
                    padding_top + diameter);
        }
    }
}