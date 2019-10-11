package com.ddybuy.ddybuy_manager_web.until;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FastdfsUtil {

    //定义属性引用配置文件中属性
    @Value(value="${nginx.fastdfs.address}")
    private  String nginx_fastdfs_address;
    @Value(value="${nginx.fastdfs.port}")
    private  String nginx_fastdfs_port;

    /**
     *  上传文件的方法
     * @param bs  上传文件的二进制数据
     * @param expname 上传文件的扩展名  .jpg  .png
     * @return  返回上传文件的路径: http://localhost:8080/组名/文件名...
     */
    public  String uploadFile(byte[]bs,String expname){
        String filePath=null;
        //1.加载属性
        try {
            ClientGlobal.init("fastdfs.properties");
            //2.创建tracker服务器对象
            TrackerClient client=new TrackerClient();
            TrackerServer trackerServer=client.getConnection();
            //3.创建storage客户端
            StorageServer storageServer=null;
            StorageClient storageClient=new StorageClient(trackerServer,storageServer);
            //4.上传图片
            String [] infos=storageClient.upload_file(bs,expname,null);
            filePath=nginx_fastdfs_address+":"+nginx_fastdfs_port+"/"+infos[0]+"/"+infos[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }



    public int deleteFile(String group,String filename){
        int i =0;
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
            i = storageClient.delete_file(group, filename);
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
          return i;
    }
}

