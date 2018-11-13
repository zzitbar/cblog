package cn.coderme.cblog.controller;

import cn.coderme.cblog.base.PageBean;
import cn.coderme.cblog.dto.PageDto;
import cn.coderme.cblog.entity.Article;
import cn.coderme.cblog.entity.bing.BingImageArchive;
import cn.coderme.cblog.service.ArticleService;
import cn.coderme.cblog.service.bing.BingImageArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created By zhangtengfei
 * Date:2018/4/19
 * Time:13:33
 */
@Controller
@RequestMapping("")
public class FrontController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private BingImageArchiveService bingImageArchiveService;

    @RequestMapping("")
    public String index(PageDto dto, Model model) {
        dto.getOrderByMap().put("articleUpdateDate", PageDto.DESC);
        dto.getOrderByMap().put("id", PageDto.DESC);
        PageBean<Article> articles = articleService.page(dto);
        BingImageArchive image = bingImageArchiveService.findTodayCnImage();
        model.addAttribute("bingToday", image);
        model.addAttribute("articles", articles);
        return "index";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/link")
    public String link() {
        return "link";
    }
}