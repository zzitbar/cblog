package cn.coderme.cblog.utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * freemarker 模板操作类
 * Created By Administrator
 * Date:2018/8/14
 * Time:13:31
 */
public class FreeMarkerUtil {

    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_22);

    static{
        //指定加载ftl模板文件所在的路径
        CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(FreeMarkerUtil.class, "/templates/mail"));
        CONFIGURATION.setDefaultEncoding("UTF-8");
        CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
    }

    public static void clearCache() {
        CONFIGURATION.clearTemplateCache();
    }

    /**
     * 获取模板
     *
     * @param templateName
     * @return
     */
    public static Template getTemplate(String templateName) {
        try {
            // 在模板文件目录中找到名称为name的文件
            Template temp = CONFIGURATION.getTemplate(templateName);
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param name
     * @param root
     * @return
     */
    public static String toHtml(String name, Map<String, Object> root) {
        String result = "";
        try {
            // 通过Template可以将模板文件输出到相应的流
            Template temp = getTemplate(name);
            StringWriter sw = new StringWriter();
            temp.process(root, sw);
            result = sw.toString();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 输出到控制台
     *
     * @param name 模板文件名
     * @param root
     */
    public static void print(String name, Map<String, Object> root) {
        try {
            // 通过Template可以将模板文件输出到相应的流
            Template temp = getTemplate(name);
            temp.process(root, new PrintWriter(System.out));
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出到文件
     *
     * @param name
     * @param root
     * @param outFile
     */
    public void fprint(String name, Map<String, Object> root, String outFile) {
        FileWriter out = null;
        try {
            // 通过一个文件输出流，就可以写到相应的文件中
            out = new FileWriter(new File(outFile));
            Template temp = this.getTemplate(name);
            temp.process(root, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Map param = new HashMap();
        param.put("user", "Big Joe");
        String str = FreeMarkerUtil.toHtml("mail.ftl", param);
        System.out.println(str);
    }
}