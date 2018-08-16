package cn.coderme.cblog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sys_user")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class User implements Serializable {
 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String name;
    private String username;
    private String password;
    private Date createTime;
    private Date updateTime;
    private String appSecret;

    private Integer minuteLimit;//每分钟调用限制
    private Integer dayLimit;//每天调用限制

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Integer getMinuteLimit() {
        return minuteLimit;
    }

    public void setMinuteLimit(Integer minuteLimit) {
        this.minuteLimit = minuteLimit;
    }

    public Integer getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(Integer dayLimit) {
        this.dayLimit = dayLimit;
    }
}