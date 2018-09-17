package openeyes.dr.openeyes.utils;

import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 视频下载工具类(单例模式)
 * Created by darryrzhong on 2018/7/27.
 */

public class VideoDownloadUtil {
     private static VideoDownloadUtil videoDownloadUtil;
     private OkHttpClient okHttpClient;

    private VideoDownloadUtil(){
      okHttpClient= new OkHttpClient();
    }

    public static VideoDownloadUtil getInstance(){
        if (videoDownloadUtil==null){
            videoDownloadUtil= new VideoDownloadUtil();
        }

        return videoDownloadUtil;
    }


    /**
     * @param url          下载连接
     * @param destFileDir  下载的文件储存目录
     * @param destFileName 下载文件名称
     * @param listener     下载监听
     */

    public void download(final String url, final String destFileDir, final String destFileName, final OnDownloadListener listener){

        Request request = new Request.Builder()
                .url(url)
                .build();

        //异步请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               listener.onDownloadFailed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] bytes = new byte[2048];
                int len=0;
                FileOutputStream fos = null;

                //储存下载文件的目录
                File dir = new File(destFileDir);
                if (!dir.exists()){
                    dir.mkdirs();
                }
                File file = new File(dir,destFileName);
                //Log.e("jjjjjjjjj",file+"");
                try {
                    is=response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum=0;
                    while ((len=is.read(bytes))!=-1){
                        fos.write(bytes,0,len);
                        sum+=len;
                        int progress = (int) (sum*1.0f/total*100);
                        listener.onDownloading(progress);//下载中更新进度条
                    }
                    fos.flush();
                    listener.onDownloadSuccess(file);
                }catch (Exception e){
                    listener.onDownloadFailed(e);
                }finally {

                    try {
                        if (is!=null){
                            is.close();
                        }
                        if (fos!=null){
                            fos.close();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }
        });


    }


    public interface  OnDownloadListener{
        /**
         * 下载成功之后的文件
         */
        void onDownloadSuccess(File file);

        /**
         * 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载异常信息
         */

        void onDownloadFailed(Exception e);

    }

}
