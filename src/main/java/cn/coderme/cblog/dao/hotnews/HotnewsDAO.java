package cn.coderme.cblog.dao.hotnews;

import cn.coderme.cblog.entity.hotnews.Hotnews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created By zzitbar
 * Date:2018/11/14
 * Time:11:27
 */
public interface HotnewsDAO extends JpaRepository<Hotnews, Long>, JpaSpecificationExecutor<Hotnews> {

    Hotnews findByNewsTitleAndNewsCategoryAndNewsDate(String newsTitle, String newsCategory, Date newsDate);

    @Modifying
    @Query("delete from Hotnews o where o.newsCategory =:newsCategory and newsDate=:newsDate")
    void deleteByNewsCategoryAndNewsDate(@Param("newsCategory") String newsCategory, @Param("newsDate") LocalDate newsDate);

    List<Hotnews> findByNewsCategoryAndNewsDateOrderByNewsOrderAsc(String newsCategory, LocalDate newsDate);
}