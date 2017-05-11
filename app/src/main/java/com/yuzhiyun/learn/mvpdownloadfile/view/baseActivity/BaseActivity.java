package com.yuzhiyun.learn.mvpdownloadfile.view.baseActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yuzhiyun.learn.mvpdownloadfile.R;

public abstract  class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewForActivity();
        findViewByIds();
        setListeners();
        initOther();
    }
    protected abstract  void setViewForActivity();
    protected abstract  void findViewByIds();
    protected abstract  void setListeners();
    protected abstract  void initOther();

}
