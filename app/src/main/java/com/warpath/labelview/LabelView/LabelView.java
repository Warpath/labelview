package com.warpath.labelview.LabelView;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warpath.labelview.LabelBean;
import com.warpath.labelview.R;


public class LabelView extends LinearLayout {
    private Context mContext;
    private ImageView mIvGif;
    private TextView mTvLabel;
    private LinearLayout mRootLayout;
    private boolean mIsEdit = false;
    private LabelBean mLabelBean;
    private boolean mIsClickable = false;
    private boolean mIsMove = false;
    private ViewOnTouchListener mViewOnTouchListener;

    public LabelView(Context context) {
        super(context);
        mContext = context;
    }

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void initData(LabelBean labelBean, boolean isEdit, boolean isClickable){
        mLabelBean = labelBean;
        mIsEdit = isEdit;
        mIsClickable = isClickable;

        LayoutInflater.from(mContext).inflate(R.layout.v_label_left, this, true);

        mIvGif = (ImageView) findViewById(R.id.iv_labelview_gifview);
        mTvLabel = (TextView) findViewById(R.id.tv_labelview_label);
        mRootLayout = (LinearLayout) findViewById(R.id.ll_labelview_wrap);

        if(isClickable){
            setAnimMode();
        }else{
            setDrawMode();
        }

        if(mIsEdit) {
            LinearLayout.LayoutParams viewLP = (LinearLayout.LayoutParams) mRootLayout.getLayoutParams();
            viewLP.leftMargin = labelBean.getX();
            viewLP.topMargin = labelBean.getY();
            mRootLayout.setLayoutParams(viewLP);
        }

        mTvLabel.setText(labelBean.getLabel());

        mRootLayout.setLongClickable(true);

        if(labelBean.getDirection() == LabelBean.DIRECTION_RIGHT) {
            mRootLayout.setScaleX(-1f);
            mTvLabel.setBackgroundResource(R.drawable.bg_label_right);
            for(int i=0; i<mRootLayout.getChildCount(); i++) {
                View view = mRootLayout.getChildAt(i);
                view.setRotationY(180f);
            }
        }
    }

    public void setDrawMode(){
        mIsClickable = false;
        switch (mLabelBean.getType()) {
            case LabelBean.LABEL_TYPE_TOPIC:
                mIvGif.setImageResource(R.mipmap.label_topic_anim2);
                break;
            case LabelBean.LABEL_TYPE_BRAND:
                mIvGif.setImageResource(R.mipmap.label_brand_anim2);
                break;
            case LabelBean.LABEL_TYPE_LOCATION:
                mIvGif.setImageResource(R.mipmap.label_location_anim2);
                break;
        }
    }

    public void setAnimMode(){
        mIsClickable = true;
        switch (mLabelBean.getType()) {
            case LabelBean.LABEL_TYPE_TOPIC:
                mIvGif.setImageResource(R.drawable.label_topic_anim);
                break;
            case LabelBean.LABEL_TYPE_BRAND:
                mIvGif.setImageResource(R.drawable.label_brand_anim);
                break;
            case LabelBean.LABEL_TYPE_LOCATION:
                mIvGif.setImageResource(R.drawable.label_location_anim);
                break;
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) mIvGif.getDrawable();
        if(animationDrawable != null && !animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }

    public void setMax(int width, int height) {
        mViewOnTouchListener = new ViewOnTouchListener(mContext, width, height, LabelView.this);
        mRootLayout.setOnTouchListener(mViewOnTouchListener);
    }

    public void setOnClickListener(){
        mViewOnTouchListener = new ViewOnTouchListener(mContext, LabelView.this);
        mRootLayout.setOnTouchListener(mViewOnTouchListener);
    }

    public void setOnLabelViewLongClickListener(final OnLabelViewOperationListener listener) {
        mRootLayout.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mIsMove) {
                    return false;
                } else {
                    mViewOnTouchListener.longClick();
                    listener.onLabelViewLongClick(LabelView.this);
                    return true;
                }
            }
        });
    }

    public interface OnLabelViewOperationListener {
        void onLabelViewLongClick(LabelView labelView);
    }

    public LinearLayout getmRootLayout() {
        return mRootLayout;
    }

    public LabelBean getLabelBean() {
        return mLabelBean;
    }

    public void setLabel(String label) {
        if (mLabelBean != null) {
            mLabelBean.setLabel(label);
        }
        mTvLabel.setText(label);
    }

    public void onClick() {
        if(mIsEdit){//编辑界面
            if (mRootLayout.getScaleX() == 1f) {
                mRootLayout.setScaleX(-1f);
                mTvLabel.setBackgroundResource(R.drawable.bg_label_right);
                mLabelBean.setDirection(LabelBean.DIRECTION_RIGHT);
            } else {
                mRootLayout.setScaleX(1f);
                mTvLabel.setBackgroundResource(R.drawable.bg_label_left);
                mLabelBean.setDirection(LabelBean.DIRECTION_LEFT);
            }
            for(int i=0; i<mRootLayout.getChildCount(); i++) {
                View view = mRootLayout.getChildAt(i);
                if (view.getRotationY() == 180f) {
                    view.setRotationY(0f);
                } else {
                    view.setRotationY(180f);
                }
            }
        }else if(mIsClickable){//动态展示的情况
            //TODO::Click 事件
        }
    }

    public void onMove(boolean isMove) {
        mIsMove = isMove;
    }
}
