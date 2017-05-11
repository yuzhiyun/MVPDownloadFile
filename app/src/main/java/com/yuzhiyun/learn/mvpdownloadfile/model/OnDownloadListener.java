package com.yuzhiyun.learn.mvpdownloadfile.model;

/**
 * Created by qifeiqin on 2017/5/11.
 */
public interface OnDownloadListener {
    void OnError(String error);
    void OnSuccess();
    void OnProgress(Integer progress);
}
