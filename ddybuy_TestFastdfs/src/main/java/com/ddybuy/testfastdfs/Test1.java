package com.ddybuy.testfastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class Test1 {
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

            //使用 StorageClient 对象上传图片。
            String[] strings = storageClient.upload_file("C:\\Users\\LCQ\\Desktop\\a.mp4","mp4",null);

            for (String str:strings) {
                System.out.println(str);
            }
            System.out.println("上传图片成功");
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
