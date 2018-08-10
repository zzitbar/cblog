package cn.coderme.cblog.dto.bing;

import java.util.List;

/**
 * Created By Administrator
 * Date:2018/8/10
 * Time:17:35
 */
public class BingDataMainDto {

    private List<BingDataDto> data;

    public List<BingDataDto> getData() {
        return data;
    }

    public void setData(List<BingDataDto> data) {
        this.data = data;
    }
}