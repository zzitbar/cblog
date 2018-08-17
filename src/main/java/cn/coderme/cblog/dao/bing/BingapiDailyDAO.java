package cn.coderme.cblog.dao.bing;

import cn.coderme.cblog.entity.bing.BingImageArchive;
import cn.coderme.cblog.entity.bing.BingapiDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created By Administrator
 * Date:2018/8/16
 * Time:10:56
 */
public interface BingapiDailyDAO extends JpaRepository<BingapiDaily, Long>, JpaSpecificationExecutor<BingapiDaily> {
}