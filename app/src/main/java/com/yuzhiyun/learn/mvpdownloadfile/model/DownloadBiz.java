package com.yuzhiyun.learn.mvpdownloadfile.model;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by qifeiqin on 2017/5/11.
 */
public class DownloadBiz implements IDownloadFileBiz {

    @Override
    public void startDownload(String url, OnDownloadListener downloadListener) {
        new DownLoadAsynctask(downloadListener).execute(url);
    }
}
class DownLoadAsynctask extends AsyncTask<String,Integer,String>{

    OnDownloadListener downloadListener;
    DownLoadAsynctask(OnDownloadListener downloadListener){
        this.downloadListener=downloadListener;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... strings)   {



        double now_length=0;
        double sum_length=0;
        String filename="";
        boolean isPaused = false;
        File file = null;
        int startIndex = 0,endIndex=0;
        try {
            byte[] bytes = new byte[1024];
            URL url = null;
            HttpURLConnection conn = null;
            url = new URL(strings[0]);
            conn = (HttpURLConnection) url.openConnection();
            endIndex = conn.getContentLength();
            System.out.println("获得输入流");
            InputStream is = conn.getInputStream();
            System.out.println("输入流完毕");
            sum_length = endIndex;

            filename = strings[0].substring(strings[0].lastIndexOf("/")+1);
            file = new File(Environment.getExternalStorageDirectory(),filename);
            /**
             *  RandomAccessFile可以访问文件的任意位置
             *  "r"    以只读方式打开。调用结果对象的任何 write 方法都将导致抛出 IOException。
             *  "rw"   打开以便读取和写入。
             *  "rws"  打开以便读取和写入。相对于 "rw"，"rws" 还要求对“文件的内容”或“元数据”的每个更新都同步写入到基础存储设备。
             *  "rwd"  打开以便读取和写入，相对于 "rw"，"rwd" 还要求对“文件的内容”的每个更新都同步写入到基础存储设备。
             */
            RandomAccessFile raf1 = new RandomAccessFile(file,"rwd");
            System.out.println("开始写入");
            raf1.seek(startIndex);
            now_length += startIndex;
            int i =1;
            int len =0;
            while (((len=is.read(bytes))!=-1)&&!isPaused){
                System.out.println("第"+i+"次读入");
                raf1.write(bytes, 0, len);
                System.out.println("第" + i + "次写入");
                now_length+=len;
                i++;
                publishProgress((int)(now_length/sum_length *100));
            }

            System.out.println("写入完毕");
            raf1.close();

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }

        return "success";
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s.equals("success"))
            downloadListener.OnSuccess();
        else
            downloadListener.OnError(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        downloadListener.OnProgress(values[0]);
    }


}
