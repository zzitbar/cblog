/**
 *
 */
package cn.coderme.cblog.dto.chart;

import cn.coderme.cblog.Constants;

import java.io.Serializable;
import java.util.List;

public class ChartDto implements Serializable {

	private static final long serialVersionUID = -6038810192126268106L;

	private Object[] xAxis;

	private List<ChartDataDto> chartDataDtos;

	private String renderTo;

	private String chartTitle;

	private String subtitle;

	private String yTitle;

	private String xTitle;

	private String type = Constants.CHART_TYPE.LINE.getValue();

	private List<Object[]> standards;//标准上下限

	private boolean isShowPlotBands = false;//是否显示标准分辨带

	private List<PieDto> pieDtos;// pie chart时 option.series.data置为该值

	private String tableId;//数据表格元素id
	private String exportFileSuffix;//数据表格导出问卷后缀

	public Object[] getxAxis() {
		return xAxis;
	}

	public void setxAxis(Object[] xAxis) {
		this.xAxis = xAxis;
	}

	public List<ChartDataDto> getChartDataDtos() {
		return chartDataDtos;
	}

	public void setChartDataDtos(List<ChartDataDto> chartDataDtos) {
		this.chartDataDtos = chartDataDtos;
	}

	public String getRenderTo() {
		return renderTo;
	}

	public void setRenderTo(String renderTo) {
		this.renderTo = renderTo;
	}

	public String getChartTitle() {
		return chartTitle;
	}

	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}

	public String getyTitle() {
		return yTitle;
	}

	public void setyTitle(String yTitle) {
		this.yTitle = yTitle;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isShowPlotBands() {
		return isShowPlotBands;
	}

	public void setShowPlotBands(boolean isShowPlotBands) {
		this.isShowPlotBands = isShowPlotBands;
	}

	public List<Object[]> getStandards() {
		return standards;
	}

	public void setStandards(List<Object[]> standards) {
		this.standards = standards;
	}

	public List<PieDto> getPieDtos() {
		return pieDtos;
	}

	public void setPieDtos(List<PieDto> pieDtos) {
		this.pieDtos = pieDtos;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getExportFileSuffix() {
		return exportFileSuffix;
	}

	public void setExportFileSuffix(String exportFileSuffix) {
		this.exportFileSuffix = exportFileSuffix;
	}

    public String getxTitle() {
        return xTitle;
    }

    public void setxTitle(String xTitle) {
        this.xTitle = xTitle;
    }
}
