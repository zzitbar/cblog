package cn.coderme.cblog.service.bing;

import cn.coderme.cblog.Constants;
import cn.coderme.cblog.base.PageBean;
import cn.coderme.cblog.dao.bing.BingImageArchiveDAO;
import cn.coderme.cblog.dto.PageDto;
import cn.coderme.cblog.entity.bing.BingImageArchive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/28
 * Time:11:40
 */
@Service
public class BingImageArchiveService {

    @Autowired
    private BingImageArchiveDAO bingImageArchiveDAO;

    @Transactional
    public void save(BingImageArchive imageArchive) {
        bingImageArchiveDAO.save(imageArchive);
    }

    /**
     * 查询bing中国当天图片
     * @return
     */
    public BingImageArchive findTodayCnImage() {
        PageDto dto = new PageDto();
        dto.getOrderByMap().put("imageDateEnd", PageDto.DESC);
        dto.setRows(1);
        Page<BingImageArchive> pictures = bingImageArchiveDAO.findAll(new Specification<BingImageArchive>() {
            @Override
            public Predicate toPredicate(Root<BingImageArchive> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> lstPredicates = new ArrayList<Predicate>();
                lstPredicates.add(cb.equal(root.get("imageZone").as(String.class), "cn"));
                Predicate[] arrayPredicates = new Predicate[lstPredicates.size()];
                return cb.and(lstPredicates.toArray(arrayPredicates));
            }
        }, dto.getPageable());
        if (pictures.getSize() > 0) {
            return pictures.getContent().get(0);
        } else {
            return new BingImageArchive();
        }
    }
    /**
     * 根据日期查询bing中国图片
     * @param imageDate
     * @return
     */
    public BingImageArchive findCnImageByDate(String imageDate) {
        return this.findByImageDateAndImageZone(imageDate, Constants.IMAGE_ZONE.CN.getValue());
    }
    /**
     * 根据日期和国家查询
     * @param imageDate
     * @param imageZone
     * @return
     */
    public BingImageArchive findByImageDateAndImageZone(String imageDate, String imageZone) {
        return bingImageArchiveDAO.findByImageDateAndImageZone(imageDate, imageZone);
    }

    /**
     * 获取最大日期
     * @return
     */
    public String maxDate() {
        return bingImageArchiveDAO.findMaxImageDateEnd();
    }

    /**
     * 分页查询
     * @param dto
     * @return
     */
    public PageBean<BingImageArchive> page(PageDto dto) {
        return new PageBean<>(bingImageArchiveDAO.findAll(dto.getPageable()));
    }
}