package com.hb.network.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hb.base.base.BaseActivity;
import com.hb.base.utils.PackageUtils;
import com.hb.base.utils.iconfont.MDFont;
import com.hb.network.R;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.img_logo)
    ImageView logoImg;
    @Bind(R.id.tv_version)
    TextView versionTv;
    @Bind(R.id.tv_icon_1)
    TextView iconTv1;
    @Bind(R.id.tv_icon_2)
    TextView iconTv2;
    @Bind(R.id.tv_icon_3)
    TextView iconTv3;
    @Bind(R.id.tv_icon_4)
    TextView iconTv4;
    @Bind(R.id.tv_icon_5)
    TextView iconTv5;
    @Bind(R.id.tv_icon_6)
    TextView iconTv6;


    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setOnClick(R.id.tv_test, R.id.tv_view_stub);
        initToolBar();
        setIconFontInfo();
    }

    private void setIconFontInfo() {
        setIconFontTv(iconTv1, MDFont.AIR_HOT);
        setIconFontTv(iconTv2, MDFont.AIR_COLD);
        setIconFontTv(iconTv3, MDFont.AIR_HOT);
        setIconFontTv(iconTv4, MDFont.PASSWORD);
        setIconFontTv(iconTv5, MDFont.NFC_PASSWORD);
        setIconFontTv(iconTv6, MDFont.BATTERY);
    }

    @Override
    protected void initData() {
        setActionTitle(getString(R.string.main));
        logoImg.setImageDrawable(getResources().getDrawable(R.mipmap.icon));
        versionTv.setText(getString(R.string.app_name)+ getString(R.string.version) + " " + PackageUtils.getCurrentVersion(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_test:
                Intent intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_view_stub:
                intent = new Intent(this, ViewStubActivity.class);
                startActivity(intent);
                break;
        }
    }
}
