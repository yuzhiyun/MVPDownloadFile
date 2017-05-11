package com.yuzhiyun.learn.mvpdownloadfile.presenter;

import com.yuzhiyun.learn.mvpdownloadfile.model.DownloadBiz;
import com.yuzhiyun.learn.mvpdownloadfile.model.IDownloadFileBiz;
import com.yuzhiyun.learn.mvpdownloadfile.model.IDownloadFileView;
import com.yuzhiyun.learn.mvpdownloadfile.model.OnDownloadListener;

/**
 * Created by qifeiqin on 2017/5/11.
 */
public class DownloadPresenter {
    IDownloadFileView downloadFileView;
    IDownloadFileBiz downloadFileBiz;
    public  DownloadPresenter(IDownloadFileView iDownloadFileView){
        downloadFileView=iDownloadFileView;
        downloadFileBiz=new DownloadBiz();
    }
    public  void startDownLoad(){
        downloadFileBiz.startDownload(downloadFileView.getUrl(), new OnDownloadListener() {
            @Override
            public void OnError(String error) {
                downloadFileView.showErrorDialog(error);
            }

            @Override
            public void OnSuccess() {
                downloadFileView.showSuccessDialog();
            }

            @Override
            public void OnProgress(Integer progress) {
                downloadFileView.changeProgress(progress);
            }
        });
    }
}
