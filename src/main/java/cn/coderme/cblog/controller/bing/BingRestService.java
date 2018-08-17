package cn.coderme.cblog.controller.bing;

import cn.coderme.cblog.base.PageBean;
import cn.coderme.cblog.entity.bing.BingImageArchive;
import cn.coderme.cblog.service.bing.BingImageArchiveService;
import cn.coderme.cblog.utils.BingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created By Administrator
 * Date:2018/5/11
 * Time:16:01
 */
@Controller
@RequestMapping("/api/bing")
public class BingRestService {

    @Autowired
    private BingImageArchiveService bingImageArchiveService;
    @Autowired
    private BingUtils bingUtils;

    @RequestMapping(value = "/today", method = RequestMethod.GET)
    @ResponseBody
    public BingImageArchive today() {
        BingImageArchive image = bingImageArchiveService.findTodayCnImage();
        bingUtils.dealImage(image);
        return image;
    }
}