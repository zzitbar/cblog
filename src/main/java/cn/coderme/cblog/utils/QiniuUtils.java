package cn.coderme.cblog.utils;

import cn.coderme.cblog.BusException;
import cn.coderme.cblog.base.QiniuKey;
import cn.coderme.cblog.dto.qiniu.FetchRetDto;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * Created By zhangtengfei
 * Date:2018/4/23
 * Time:14:54
 */
@Component
public class QiniuUtils {

    @Autowired
    private QiniuKey qiniuKey;

    public String upload(InputStream io) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(getZone());
        UploadManager uploadManager = new UploadManager(cfg);
        //生成上传凭证，准备上传
        String accessKey = qiniuKey.getAccessKey();
        String secretKey = qiniuKey.getSecretKey();
        String bucket = qiniuKey.getBucket();
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        String fileUrl = "";
        try {
            Response response = uploadManager.put(io, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            fileUrl = qiniuKey.getQiniuUrl()+ "/" +putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
                ex2.printStackTrace();
            }
        }
        return fileUrl;
    }

    /**
     * 抓取网络资源上传
     * @param remoteSrcUrl
     * @param fileName
     * @return
     */
    public FetchRetDto fetctToUpload(String remoteSrcUrl, String fileName) {
        FetchRetDto dto = null;
        Configuration cfg = new Configuration(getZone());
        //...生成上传凭证，然后准备上传
        String accessKey = qiniuKey.getAccessKey();
        String secretKey = qiniuKey.getSecretKey();
        String bucket = qiniuKey.getBucket();
        //文件名
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        //抓取网络资源到空间
        try {
            FetchRet fetchRet = bucketManager.fetch(remoteSrcUrl, bucket, key);
            dto = new FetchRetDto();
            dto.setHash(fetchRet.hash);
            dto.setKey(fetchRet.key);
            dto.setMimeType(fetchRet.mimeType);
            dto.setFsize(fetchRet.fsize);
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
            throw new BusException("上传出错", ex);
        }
        return dto;
    }

    /**
     * 根据配置文件的zone创建Zone
     * @return
     */
    private Zone getZone() {
        String zoneArea = qiniuKey.getZone();
        Zone zone = null;
        if (null != zoneArea && ""!=zoneArea) {
            try {
                Class clazz = Class.forName("com.qiniu.common.Zone");
                Method method = clazz.getMethod(zoneArea);
                zone = (Zone) method.invoke(clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (null == zone) {
            zone = Zone.autoZone();
        }
        return zone;
    }


}