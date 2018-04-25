package cn.coderme.cblog.controller;

import cn.coderme.cblog.entity.ArticleTags;
import cn.coderme.cblog.service.ArticleTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/23
 * Time:11:22
 */
@Controller
@RequestMapping("/tags")
public class ArticleTagsController {

    @Autowired
    private ArticleTagsService articleTagsService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<String> list() {
        List<ArticleTags> tags = articleTagsService.findAll();
        List<String> result = new ArrayList<String>(tags.size());
        for (ArticleTags tags1 : tags) {
            result.add(tags1.getTagTitle());
        }
        return result;
    }
}