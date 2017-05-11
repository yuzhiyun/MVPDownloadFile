package com.yuzhiyun.learn.mvpdownloadfile.model;

/**
 * Created by qifeiqin on 2017/5/11.
 */
public interface IDownloadFileBiz {
    void startDownload(String url,OnDownloadListener downloadListener);
}
