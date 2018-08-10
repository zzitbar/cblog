package cn.coderme.cblog.controller.bing;

import cn.coderme.cblog.base.PageBean;
import cn.coderme.cblog.dto.PageDto;
import cn.coderme.cblog.entity.bing.BingImageArchive;
import cn.coderme.cblog.jobs.BingImageJob;
import cn.coderme.cblog.service.bing.BingImageArchiveService;
import cn.coderme.cblog.utils.BingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created By zhangtengfei
 * Date:2018/4/25
 * Time:11:34
 */
@Controller
@RequestMapping("/bing")
public class BingController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BingImageArchiveService bingImageArchiveService;
    @Autowired
    private BingUtils bingUtils;
    @Autowired
    private BingImageJob bingImageJob;

    /**
     * 当日图片页面
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String today(Model model) {
        BingImageArchive image = bingImageArchiveService.findTodayCnImage();
        bingUtils.dealImage(image);
        model.addAttribute("image", image);
        return "bing/bing-today";
    }

    /**
     * 重定向到今日图片
     * @return
     */
    @RequestMapping(value = "/today", method = RequestMethod.GET)
    public String today() {
        BingImageArchive image = bingImageArchiveService.findTodayCnImage();
        bingUtils.dealImage(image);
        return "redirect:"+image.getImageUrl();
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, PageDto dto) {
        dto.getOrderByMap().put("date", PageDto.DESC);
        dto.setRows(12);
        PageBean<BingImageArchive> pageBean = bingImageArchiveService.page(dto);
        model.addAttribute("pageBean", pageBean);
        return "bing/bingIndex";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        bingImageJob.histroy(null);
        return "success";
    }
}