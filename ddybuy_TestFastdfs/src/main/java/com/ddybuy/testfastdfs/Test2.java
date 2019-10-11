package com.ddybuy.testfastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class Test2 {
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

            //使用 StorageClient 对象删除图片。
            int i = storageClient.delete_file("group1", "M00/00/00/wKgBHl08hHGAE_woABPvmEJrkQ0592.gif");
            System.out.println("删除图片成功");
            System.out.println(i);
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
