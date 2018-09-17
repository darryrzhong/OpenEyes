package openeyes.dr.openeyes.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

/**
 * 获取文件夹大小or内置sd卡的使用情况
 * Created by darryrzhong on 2018/7/29.
 */

public class SdcardOrFileSizeUtil {

    /**
     * 获取内置sdcard剩余空间大小
     * */

    public static String getSdcardtFreeSpace(Context context){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File path = Environment.getExternalStorageDirectory();
            StatFs statFs = new StatFs(path.getPath());
            long blockSize,availableBlocks;
            availableBlocks = statFs.getAvailableBlocksLong();
            blockSize = statFs.getBlockSizeLong();
            long size = availableBlocks*blockSize;
            return Formatter.formatFileSize(context,size);

        }else {
            return "sdcard unable!";
        }
    }

    /**
     * 获取指定文件大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    private static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            Log.e("------","获取文件大小不存在!");
        }
        return size;
    }

    /**
     * 获取指定文件夹大小
     * */
    public static  long getFileSizes(File file) throws Exception {
        long size = 0;
        File []  flist = file.listFiles();
        for (int i=0;i<flist.length;i++){
            if (flist[i].isDirectory()){
                size = size+getFileSizes(flist[i]);
            }else {
                size = size + getFileSize(flist[i]);
            }
        }
        return size;

    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static String formetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }


}
