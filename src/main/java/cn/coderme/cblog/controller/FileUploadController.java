package cn.coderme.cblog.controller;

import cn.coderme.cblog.utils.QiniuUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By zhangtengfei
 * Date:2018/4/23
 * Time:14:57
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private QiniuUtils qiniuUtils;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "editormd-image-file", required = false) MultipartFile attach) {
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            String fileUrl = qiniuUtils.upload(attach.getInputStream());
            res.put("url", fileUrl);
            res.put("success", 1);
            res.put("message", "upload success!");
        } catch (Exception e) {
            logger.error("上传图片出错", e);
            res.put("success", 0);
            res.put("message", e.getMessage());
        }
        return res;
    }
}