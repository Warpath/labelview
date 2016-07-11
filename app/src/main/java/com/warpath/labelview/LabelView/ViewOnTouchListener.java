package com.warpath.labelview.LabelView;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;



/**
 * Author: warpath
 * Date: 2015-09-21
 * Time: 10:56
 */
public class ViewOnTouchListener implements View.OnTouchListener{
    private static final int MAX_CLICK_DURATION = 200;
    private static final int MAX_CLICK_DISTANCE = 5;

    private Context mContext;
    private Point pushPoint;
    private int lastLayoutLeft, lastLayoutTop;
    private LinearLayout.LayoutParams layoutLP;
    private float moveX, moveY;
    private int maxX, maxY;
    private long startClickTime;
    private LabelView mLabelView;
    private boolean mIsEdit = true;
    private boolean mIsLongClick = false;

    public ViewOnTouchListener(Context context, int maxX, int maxY, LabelView labelView) {
        mContext = context;
        this.maxX = maxX;
        this.maxY = maxY;
        mLabelView = labelView;
    }

    public ViewOnTouchListener(Context context, LabelView labelView) {
        mContext = context;
        mLabelView = labelView;
        mIsEdit = false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                startClickTime = System.currentTimeMillis();
                if(mIsEdit) {
                    if (null == layoutLP) {
                        layoutLP = (LinearLayout.LayoutParams) view.getLayoutParams();
                    }

                    pushPoint = getRawPoint(event);
                    lastLayoutLeft = layoutLP.leftMargin;
                    lastLayoutTop = layoutLP.topMargin;
                    mLabelView.onMove(false);
                    mIsLongClick = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(mIsEdit && !mIsLongClick) {
                    Point newPoint = getRawPoint(event);
                    moveX = newPoint.x - pushPoint.x;
                    moveY = newPoint.y - pushPoint.y;
                    if (lastLayoutLeft + moveX <= 0) {
                        return true;
                    } else if (lastLayoutLeft + view.getWidth() + moveX > maxX) {
                        return true;
                    }
                    if (lastLayoutTop + moveY <= 0) {
                        return true;
                    } else if (lastLayoutTop + view.getHeight() + moveY > maxY) {
                        return true;
                    }

                    layoutLP.leftMargin = (int) (lastLayoutLeft + moveX);
                    layoutLP.topMargin = (int) (lastLayoutTop + moveY);
                    view.setLayoutParams(layoutLP);
                    if(distance(pushPoint.x, pushPoint.y, event.getRawX(), event.getRawY()) > MAX_CLICK_DISTANCE){
                        mLabelView.onMove(true);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                long clickDuration = System.currentTimeMillis() - startClickTime;
                if (clickDuration < MAX_CLICK_DURATION) {
                    mLabelView.onClick();
                }
                break;
        }
        return false;
    }

    public void longClick(){
        mIsLongClick = true;
    }

    private Point getRawPoint(MotionEvent event) {
        return new Point((int) event.getRawX(), (int) event.getRawY());
    }

    private float distance(float x1, float y1, float x2, float y2) {
        float dx = x1 - x2;
        float dy = y1 - y2;
        float distanceInPx = (float) Math.sqrt(dx * dx + dy * dy);
        return pxToDp(distanceInPx);
    }

    private float pxToDp(float px) {
        return px / mContext.getResources().getDisplayMetrics().density;
    }
}
