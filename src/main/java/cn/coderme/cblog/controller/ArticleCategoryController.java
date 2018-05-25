package cn.coderme.cblog.controller;

import cn.coderme.cblog.base.PageBean;
import cn.coderme.cblog.base.ResultJson;
import cn.coderme.cblog.dto.PageDto;
import cn.coderme.cblog.entity.ArticleCategory;
import cn.coderme.cblog.service.ArticleCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/24
 * Time:16:25
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/admin/category")
public class ArticleCategoryController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ArticleCategoryService articleCategoryService;

    /**
     * 分类管理首页
     * @return
     */
    @RequestMapping("")
    public String manage() {
        return "category/categoryManage";
    }

    /**
     * 分页分页查询
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageBean<ArticleCategory> page(PageDto dto) {
        dto.getOrderByMap().put("createTime", PageDto.DESC);
        PageBean<ArticleCategory> pageBean = articleCategoryService.page(dto);
        return pageBean;
    }

    /**
     * 修改或新增文章
     *
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, Model model) {
        ArticleCategory category = new ArticleCategory();
        if (null != id) {
            category = articleCategoryService.getById(id);
        }
        model.addAttribute("category", category);
        return "category/categoryEdit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson edit(ArticleCategory category) {
        ResultJson result = new ResultJson();
        try {
            articleCategoryService.update(category);
        } catch (Exception e) {
            logger.error("保存文章分类出错", e);
            result.setStatus(ResultJson.FAILED);
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<ArticleCategory> list() {
        return articleCategoryService.findAll();
    }
}