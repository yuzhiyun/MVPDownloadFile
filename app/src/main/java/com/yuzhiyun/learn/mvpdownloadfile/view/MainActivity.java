package com.yuzhiyun.learn.mvpdownloadfile.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yuzhiyun.learn.mvpdownloadfile.R;
import com.yuzhiyun.learn.mvpdownloadfile.model.IDownloadFileView;
import com.yuzhiyun.learn.mvpdownloadfile.presenter.DownloadPresenter;
import com.yuzhiyun.learn.mvpdownloadfile.view.baseActivity.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener,IDownloadFileView {
    Button btn_download;
    EditText et_url;
    ProgressBar progressBar;
    DownloadPresenter downloadPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setViewForActivity() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void findViewByIds() {
        btn_download= (Button) findViewById(R.id.btn_download);
        et_url= (EditText) findViewById(R.id.et_url);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    protected void setListeners() {
        btn_download.setOnClickListener(this);
    }

    @Override
    protected void initOther() {
        downloadPresenter=new DownloadPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_download:
                downloadPresenter.startDownLoad();
                break;
        }
    }

    @Override
    public String getUrl() {
//        return ;
        return et_url.getText().toString().trim();
    }

    @Override
    public void showErrorDialog(String error) {
        //Toast.makeText(this,error,Toast.LENGTH_LONG);
        //Log.i("MainActivity",error);
        showDialog(error);
    }


    @Override
    public void showSuccessDialog() {
       // Toast.makeText(MainActivity.this,"下载成功",Toast.LENGTH_LONG);
        showDialog("下载成功");
        //Log.i("MainActivity","下载成功");
    }

    @Override
    public void changeProgress(int progress) {
        progressBar.setProgress(progress);
    }
    /**
     * 弹出对话框
     *
     * @param msg
     */
    public void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("返回结果:\n" + msg)
                .setCancelable(false)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

}
