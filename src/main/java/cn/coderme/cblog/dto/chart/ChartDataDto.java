/**
 *
 */
package cn.coderme.cblog.dto.chart;

import java.io.Serializable;

public class ChartDataDto implements Serializable {

    private static final long serialVersionUID = 3544348758880772179L;

    private String name;
    private Object[] data;
    private Double lineWidth = 2.0;// 线宽，默认为2

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }

    public Double getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(Double lineWidth) {
        this.lineWidth = lineWidth;
    }
}
