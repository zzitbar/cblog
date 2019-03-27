package cn.coderme.cblog.controller.hotnews;

import cn.coderme.cblog.base.ResultJson;
import cn.coderme.cblog.entity.hotnews.Hotnews;
import cn.coderme.cblog.service.hotnews.crawl.BaiduHotnewsService;
import cn.coderme.cblog.service.hotnews.crawl.HotnewsCrawlService;
import cn.coderme.cblog.service.hotnews.crawl.WeiboHotnewsService;
import cn.coderme.cblog.service.hotnews.crawl.ZhihuHotnewsService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created By zzitbar
 * Date:2018/11/14
 * Time:14:08
 */
@Controller
@RequestMapping("/hotnews")
public class HotnewsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WeiboHotnewsService weiboHotnewsService;
    @Autowired
    private BaiduHotnewsService baiduHotnewsService;
    @Autowired
    private ZhihuHotnewsService zhihuHotnewsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        List<Map> categroyList = HotnewsCrawlService.getCategoryList();
        model.addAttribute("categroyList", new Gson().toJson(categroyList));
        return "hotnews/index";
    }

    /**
     * 分类列表
     *
     * @return
     */
    @RequestMapping(value = "/categoryList", method = RequestMethod.GET)
    @ResponseBody
    public List<Map> categoryList() {
        return HotnewsCrawlService.getCategoryList();
    }

    /**
     * 获取热点列表
     *
     * @param category
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Hotnews> list(String category) {
        return weiboHotnewsService.listByCategory(category, LocalDate.now());
    }

    @RequestMapping(value = "/zhihu/header", method = RequestMethod.GET)
    public String zhihuHeader(Model model) {
        model.addAttribute("header", zhihuHotnewsService.getHeader());
        return "hotnews/zhihu/header";
    }

    @RequestMapping(value = "/zhihu/header", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson zhihuHeader(@RequestParam Map params) {
        ResultJson result = new ResultJson();
        zhihuHotnewsService.getHeader().putAll(params);
        return result;
    }
}