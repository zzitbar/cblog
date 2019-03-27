package cn.coderme.cblog.service.hotnews;

import cn.coderme.cblog.dao.hotnews.HotnewsDAO;
import cn.coderme.cblog.entity.hotnews.Hotnews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created By zzitbar
 * Date:2018/11/14
 * Time:11:29
 */
@Service
public class HotnewsService {

    @Autowired
    private HotnewsDAO hotnewsDAO;

}