package cn.coderme.cblog.controller;

import cn.coderme.cblog.base.PageBean;
import cn.coderme.cblog.base.ResultJson;
import cn.coderme.cblog.dto.ArticleTopDto;
import cn.coderme.cblog.dto.PageDto;
import cn.coderme.cblog.entity.Article;
import cn.coderme.cblog.entity.ArticleTags;
import cn.coderme.cblog.service.ArticleCategoryService;
import cn.coderme.cblog.service.ArticleService;
import cn.coderme.cblog.service.ArticleTagsService;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/19
 * Time:16:06
 */
// 允许跨域
@Controller
@RequestMapping("")
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ArticleService articleService;
    @Resource
    private ArticleCategoryService articleCategoryService;
    @Autowired
    private ArticleTagsService articleTagsService;

    /**
     * 查看某篇文章
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/article/{id}")
    public String view(@PathVariable Long id, Model model) {
        Article article = articleService.getById(id);
        articleService.read(article);

        MutableDataSet options = new MutableDataSet();

        // uncomment to set optional extensions
        //options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));

        // uncomment to convert soft-breaks to hard breaks
        //options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        // You can re-use parser and renderer instances
        Node document = parser.parse(article.getArticleContent());
        String html = renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
        model.addAttribute("html", html);
        article.setArticleContent("");
        model.addAttribute("article", article);
        return "article/article";
    }

    /**
     * 文章管理页面
     *
     * @return
     */
    @RequestMapping("/admin/article/manager")
    public String manage() {
        return "article/articleManage";
    }

    /**
     * 文章分页查询
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/admin/article/page", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson page(PageDto dto, HttpServletResponse response) {
        dto.getOrderByMap().put("id", PageDto.DESC);
        dto.getOrderByMap().put("articleUpdateDate", PageDto.DESC);
        PageBean<Article> pageBean = articleService.page(dto);
        ResultJson result = ResultJson.ok();
        result.setData(pageBean);
        return result;
    }

    /**
     * 修改或新增文章
     *
     * @return
     */
    @RequestMapping(value = "/admin/article/edit", method = RequestMethod.GET)
    public String edit(Long id, Model model) {
        Article article = new Article();
        if (null != id) {
            article = articleService.getById(id);
        }
        model.addAttribute("article", article);
        model.addAttribute("articleCategorys", articleCategoryService.findAll());
        return "article/articleEdit";
    }

    @RequestMapping(value = "/admin/article/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson edit(Article article) {
        ResultJson result = new ResultJson();
        try {
            articleService.update(article);
        } catch (Exception e) {
            logger.error("保存文章出错", e);
            result.setStatus(ResultJson.FAILED);
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/admin/article/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson get(@PathVariable Long id) {
        Article article = articleService.getById(id);
        ResultJson result = new ResultJson();
        result.setData(article);
        return result;
    }

    /**
     * 获取最热文章、最新文章、含文章最多标签
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/article/top", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson<ArticleTopDto> top(Model model) {
        ResultJson<ArticleTopDto> result = new ResultJson<ArticleTopDto>();
        result.setData(new ArticleTopDto());
        try {
            result.getData().setHotest(articleService.findHotest());
            result.getData().setLastest(articleService.findLastest());
            result.getData().setTags(articleTagsService.findRefOrder());
        } catch (Exception e) {
            logger.error("保存文章出错", e);
            result.setStatus(ResultJson.FAILED);
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/article/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson delete(Long id) {
        ResultJson result = new ResultJson();
        try {
            articleService.delete(id);
        } catch (Exception e) {
            logger.error("删除文章出错", e);
            result.setStatus(ResultJson.FAILED);
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }
}