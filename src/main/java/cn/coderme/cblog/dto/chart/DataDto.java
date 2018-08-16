package cn.coderme.cblog.dto.chart;

/**
 * Created By Administrator
 * Date:2018/8/16
 * Time:14:06
 */
public class DataDto {

    private String item;
    private Integer cnt;

    public DataDto() {
    }

    public DataDto(String item, Integer cnt) {
        this.item = item;
        this.cnt = cnt;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
}