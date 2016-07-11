package com.warpath.labelview;

import android.app.Activity;
import android.os.Bundle;

import com.warpath.labelview.LabelView.LabelView;

/**
 * Created by 饭团 on 2016/7/11.
 */
public class MainActivity extends Activity{
    private LabelView mLabelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        mLabelView = (LabelView) findViewById(R.id.labelView);

        LabelBean labelBean = new LabelBean(LabelBean.LABEL_TYPE_TOPIC, LabelBean.DIRECTION_LEFT, 300, 300, 100, 50, "台风来了", 30, 30, 0, "");
        mLabelView.initData(labelBean, false, true);
    }
}
