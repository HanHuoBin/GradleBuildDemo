package com.hb.network.activity;

import android.view.View;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.hb.base.base.BaseActivity;
import com.hb.network.R;

import butterknife.Bind;


public class ViewStubActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.stub_layout)
    ViewStub viewStub;
    @Bind(R.id.tv_extend)
    TextView extendTv;

    @Override
    protected int initLayout() {
        return R.layout.activity_view_stub;
    }

    @Override
    protected void initView() {
        initToolBar();
        setTooBarBackBtn();
        setOnClick(R.id.tv_extend);
    }

    @Override
    protected void initData() {
        setActionTitle("ViewStub");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_extend:
                if (extendTv.getText().equals("展开")) {
                    AlphaAnimation animation = new AlphaAnimation(0, 1.0f);
                    viewStub.startAnimation(animation);
                    animation.setDuration(1000);
                    viewStub.setVisibility(View.VISIBLE);
                    extendTv.setText("关闭");
                } else {
                    AlphaAnimation animation = new AlphaAnimation(1.0f, 0);
                    viewStub.startAnimation(animation);
                    animation.setDuration(1000);
                    viewStub.setVisibility(View.GONE);
                    extendTv.setText("展开");
                }
                break;
        }
    }
}
