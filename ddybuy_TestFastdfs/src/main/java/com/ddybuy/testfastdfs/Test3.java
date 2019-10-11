package com.ddybuy.testfastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test3 {
    public static void main(String[] args) {

        try {

            //加载配置
            ClientGlobal.init("fastdfs.properties");
            //创建一个 TrackerClient 对象
            TrackerClient trackerClient=new TrackerClient();
            //使用 TrackerClient 对象创建连接，获得一个 TrackerServer 对象
            TrackerServer connection = trackerClient.getConnection();
            //创建一个 StorageServer 的引用，值为 nul
            StorageServer storageServer=null;
            //创建一个 StorageClient 对象，需要两个参数 TrackerServer 对象、StorageServer 的引用
            StorageClient storageClient=new StorageClient(storageServer,storageServer);

            //使用 StorageClient 对象下载图片。
            byte[] bytes= storageClient.download_file("group1","M00/00/00/wKgBHl08hXWAKqLrABPvmEJrkQ0155.gif");

            //将到返回的二进制数据写入文件
            FileOutputStream fos=new FileOutputStream("C:\\Users\\LCQ\\Desktop\\111.gif");
            fos.write(bytes);
            //清除缓存区  刷新
            fos.flush();
            //关流
            fos.close();

            //5.关闭
            connection.close();
            System.out.println("下载图片成功");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
