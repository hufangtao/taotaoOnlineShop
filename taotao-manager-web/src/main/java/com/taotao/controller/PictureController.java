package com.taotao.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传
 */
@Controller
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String picUpload(MultipartFile uploadFile){

        try {
            String originalFilename = uploadFile.getOriginalFilename();
            String extname=originalFilename.substring(originalFilename.lastIndexOf(".")+1);

            FastDFSClient fastDFSClient=new FastDFSClient("classpath:resource/client.conf");
            String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extname);
            url=IMAGE_SERVER_URL+url;

            Map reslut=new HashMap<>();
            reslut.put("error",0);
            reslut.put("url",url);
            return JsonUtils.objectToJson(reslut);
        } catch (Exception e) {
            e.printStackTrace();
            Map reslut=new HashMap<>();
            reslut.put("error",1);
            reslut.put("message","图片上传失败");
            return JsonUtils.objectToJson(reslut);
        }

    }

}
