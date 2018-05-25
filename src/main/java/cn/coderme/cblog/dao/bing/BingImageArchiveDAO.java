package cn.coderme.cblog.dao.bing;

import cn.coderme.cblog.entity.bing.BingImageArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created By zhangtengfei
 * Date:2018/4/28
 * Time:11:40
 */
public interface BingImageArchiveDAO extends JpaRepository<BingImageArchive, Long>, JpaSpecificationExecutor<BingImageArchive> {

    BingImageArchive findByImageDateAndImageZone(String imageDate, String imageZone);

    @Query(value = "SELECT MAX(t.imageDateEnd) FROM bing_image_archive t WHERE t.imageZone='cn'", nativeQuery = true)
    String findMaxImageDateEnd();
}