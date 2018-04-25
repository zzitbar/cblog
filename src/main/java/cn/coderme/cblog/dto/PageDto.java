package cn.coderme.cblog.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By zhangtengfei
 * Date:2018/4/19
 * Time:15:58
 */
public class PageDto {
    private Integer page = 1;//页码
    private Integer rows = 10;//页 行数

    public static final String ASC = "asc";
    public static final String DESC = "desc";

    // laytable 分页请求参数
    private Integer limit = 10;

    //排序map
    private Map<String, String> orderByMap = new LinkedHashMap<String, String>(4);

    private Pageable pageable;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Pageable getPageable() {
        if (orderByMap.size()>0) {
            List<Sort.Order> orders = new ArrayList<Sort.Order>(orderByMap.size());
            for (Map.Entry<String, String> entry : orderByMap.entrySet()) {
                Sort.Order order = new Sort.Order(ASC.equalsIgnoreCase(entry.getValue())? Sort.Direction.ASC : Sort.Direction.DESC, entry.getKey());
                orders.add(order);
            }
            return new PageRequest(page-1, rows, new Sort(orders));
        } else {
            return new PageRequest(page-1, rows);
        }
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public Map<String, String> getOrderByMap() {
        return orderByMap;
    }

    public void setOrderByMap(Map<String, String> orderByMap) {
        this.orderByMap = orderByMap;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        this.rows = limit;
    }
}