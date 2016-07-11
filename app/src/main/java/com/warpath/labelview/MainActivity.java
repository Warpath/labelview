package com.warpath.labelview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.warpath.labelview.LabelView.LabelView;

public class MainActivity extends Activity{
    private LabelView mLabelView;
    private LabelView mLabelViewRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        mLabelView = (LabelView) findViewById(R.id.labelView);
        mLabelViewRight = (LabelView) findViewById(R.id.labelview_right);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        LabelBean labelBean = new LabelBean(LabelBean.LABEL_TYPE_TOPIC, LabelBean.DIRECTION_LEFT, 300, 300, 80, 50, "台风来了", 30, 30, 0, "");
        mLabelView.initData(labelBean, true, true);
        mLabelView.setMax(wm.getDefaultDisplay().getWidth(), wm.getDefaultDisplay().getHeight());

        LabelBean labelBeanRight = new LabelBean(LabelBean.LABEL_TYPE_BRAND, LabelBean.DIRECTION_RIGHT, 100, 100, 100, 50, "客官不可以", 30, 30, 0, "");
        mLabelViewRight.initData(labelBeanRight, true, true);
        mLabelViewRight.setMax(wm.getDefaultDisplay().getWidth(), wm.getDefaultDisplay().getHeight());

    }
}
