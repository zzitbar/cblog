package cn.coderme.cblog.entity.bing;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created By Administrator
 * Date:2018/8/16
 * Time:10:54
 */
@Entity
@Table(name = "bingapi_daily")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BingapiDaily implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Date day;
    private Integer cnt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
}