package cn.coderme.cblog.dao;

import cn.coderme.cblog.entity.ApiLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created By Administrator
 * Date:2018/8/16
 * Time:11:17
 */
public interface ApiLogDAO extends JpaRepository<ApiLog, Long>, JpaSpecificationExecutor<ApiLog> {
}
