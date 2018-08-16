package cn.coderme.cblog.dto.chart;

public class PieDto {

    /**
     * y : 1
     * name : Point2
     * color : #00FF00
     */
    private Object y;
    private String name;
    private String color;

    public PieDto() {
    }

    public PieDto(Object y, String name) {
        this.y = y;
        this.name = name;
    }

    public PieDto(Object y, String name, String color) {
        this.y = y;
        this.name = name;
        this.color = color;
    }

    public Object getY() {
        return y;
    }

    public void setY(Object y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
