package com.medikart.org;

import android.graphics.Point;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ArcTranslate extends Animation {

private Point start;
private Point end;
private Point middle;
private final float mFromXValue;
private final float mToXValue;
private final float mYValue;
private final int mFromXType;
private final int mToXType;
private final int mYType;

public ArcTranslate(long duration, int fromXType, float fromXValue,
        int toXType, float toXValue, int yType, float yValue){
    setDuration(duration);

     mFromXValue = fromXValue;
     mToXValue = toXValue;
     mYValue = yValue;

     mFromXType = fromXType;
     mToXType = toXType;
     mYType = yType;

}
private long calcBezier(float interpolatedTime, float p0, float p1, float p2){
    return Math.round((Math.pow((1 - interpolatedTime), 2) * p0)
           + (2 * (1 - interpolatedTime) * interpolatedTime * p1)
           + (Math.pow(interpolatedTime, 2) * p2));
}

@Override
protected void applyTransformation(float interpolatedTime, Transformation t) {
    float dx = calcBezier(interpolatedTime, start.x, middle.x, end.x);
    float dy = -calcBezier(interpolatedTime, start.y, middle.y+50, end.y);

    t.getMatrix().setTranslate(dx, dy);
}

@Override
public void initialize(int width, int height, int parentWidth, int parentHeight) {
    super.initialize(width, height, parentWidth, parentHeight);
    float startX = resolveSize(mFromXType, mFromXValue, width, parentWidth);
    float endX = resolveSize(mToXType, mToXValue, width, parentWidth);
    float middleY = resolveSize(mYType, mYValue, width, parentWidth);
    float middleX = startX + ((endX-startX)/2);
    start = new Point((int) startX, 0);
    end = new Point((int) endX, 0);
    middle = new Point((int)middleX, (int)middleY);
}
}