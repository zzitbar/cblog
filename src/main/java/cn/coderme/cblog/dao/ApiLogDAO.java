package cn.coderme.cblog.dao;

import cn.coderme.cblog.dto.chart.DataDto;
import cn.coderme.cblog.entity.ApiLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created By Administrator
 * Date:2018/8/16
 * Time:11:17
 */
public interface ApiLogDAO extends JpaRepository<ApiLog, Long>, JpaSpecificationExecutor<ApiLog> {

    @Query(value = "SELECT DATE_FORMAT(createTime, '%H') AS item, COUNT(id) as cnt " +
            "FROM api_log t WHERE t.userId=:userId AND t.createTime>=:startTime AND t.createTime<:endTime " +
            "GROUP BY item ORDER BY item ", nativeQuery = true)
    List<Object[]> groupByHour(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("userId") Long userId);

    @Query(value = "SELECT DATE_FORMAT(createTime, '%Y-%m-%d') AS item, COUNT(id) as cnt " +
            "FROM api_log t WHERE t.userId=:userId AND t.createTime>=:startTime AND t.createTime<:endTime " +
            "GROUP BY item ORDER BY item ", nativeQuery = true)
    List<Object[]> groupByDay(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("userId") Long userId);
}
