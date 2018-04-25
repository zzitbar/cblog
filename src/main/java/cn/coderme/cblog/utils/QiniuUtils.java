package cn.coderme.cblog.utils;

import cn.coderme.cblog.base.QiniuKey;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

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
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
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
}