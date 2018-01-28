package com.taotao.fastdfs;

import com.taotao.utils.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;

public class TestFastDFS {

    @Test
    public void uploadFile() throws Exception{

        ClientGlobal.init("H:\\taotao\\taotao-manager-web\\src\\main\\resources\\resource\\client.conf");

        TrackerClient trackerClient=new TrackerClient();
        TrackerServer trackerServer=trackerClient.getConnection();
        StorageServer storageServer=null;
        StorageClient storageClient=new StorageClient(trackerServer,storageServer);

        String[] strings = storageClient.upload_file("H:\\taotao\\haizeiwang.jpg", "jpg", null);

        for (String string:strings){
            System.out.println(string);
        }
    }

    @Test
    public void testFastDfsClient() throws Exception{
        FastDFSClient fastDFSClient=new FastDFSClient("H:\\taotao\\taotao-manager-web\\src\\main\\resources\\resource\\client.conf");
        String string = fastDFSClient.uploadFile("H:\\taotao\\meinv.jpg");
        System.out.println(string);
    }

}
