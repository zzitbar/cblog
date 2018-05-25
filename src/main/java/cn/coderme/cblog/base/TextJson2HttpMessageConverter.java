package cn.coderme.cblog.base;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理http 请求返回content-type为text/json
 * Created By zhangtengfei
 * Date:2018/4/25
 * Time:13:47
 */
public class TextJson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public TextJson2HttpMessageConverter() {
        super();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(new MediaType("text", "json"));
        setSupportedMediaTypes(mediaTypes);
    }
}