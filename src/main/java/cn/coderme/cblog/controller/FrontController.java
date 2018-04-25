package cn.coderme.cblog.controller;

import cn.coderme.cblog.base.PageBean;
import cn.coderme.cblog.dto.PageDto;
import cn.coderme.cblog.entity.Article;
import cn.coderme.cblog.service.ArticleService;
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

    @RequestMapping("")
    public String index(PageDto dto, Model model) {
        dto.getOrderByMap().put("id", PageDto.DESC);
        dto.getOrderByMap().put("articleUpdateDate", PageDto.DESC);
        PageBean<Article> articles = articleService.page(dto);
        model.addAttribute("articles", articles);
        return "index";
    }
}