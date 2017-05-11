package com.yuzhiyun.learn.mvpdownloadfile.model;

/**
 * Created by qifeiqin on 2017/5/11.
 */
public interface IDownloadFileView {
    String getUrl();
    void showErrorDialog(String error);
    void showSuccessDialog();
    void changeProgress(int progress);

}
