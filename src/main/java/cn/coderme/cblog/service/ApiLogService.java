package cn.coderme.cblog.service;

import cn.coderme.cblog.Constants;
import cn.coderme.cblog.dao.ApiLogDAO;
import cn.coderme.cblog.dto.chart.ChartDataDto;
import cn.coderme.cblog.dto.chart.ChartDto;
import cn.coderme.cblog.dto.chart.DataDto;
import cn.coderme.cblog.entity.ApiLog;
import cn.coderme.cblog.entity.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * API 请求日志 Service
 * Created By Administrator
 * Date:2018/8/16
 * Time:11:17
 */
@Service
public class ApiLogService {

    @Autowired
    private ApiLogDAO apiLogDAO;
    @Autowired
    private UserService userService;

    @Transactional
    @Async
    public void saveLog(HttpServletRequest request, Exception ex, int costTime) {
        ApiLog log = new ApiLog();
        log.setStatus(ex == null ? 1:0);
        log.setRemoteAddr(getRemoteAddr(request));
        log.setUserAgent(request.getHeader("user-agent"));
        log.setRequestUri(request.getRequestURI());
        Gson gson = new Gson();
        String params = gson.toJson(request.getParameterMap());
        log.setParams(params);
        log.setMethod(request.getMethod());
        log.setCreateTime(new Date());
        log.setCostTime(costTime);

        // AppSecret
        String appSecret = request.getHeader("AppSecret");
        log.setAppSecret(appSecret);
        if (StringUtils.hasText(appSecret)) {
            User user = userService.findByAppSecret(appSecret);
            log.setUserId(null==user?null:user.getId());
        }

        // 异常信息
        if (ex != null){
            StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            log.setException(stringWriter.toString());
        }

        apiLogDAO.save(log);
    }

    /**
     * 按小时统计数据
     * @param userId
     * @param date
     * @return
     */
    public ChartDto hourData(Long userId, LocalDate startDate, LocalDate endDate, Integer duration) {
        ChartDto chartDto = new ChartDto();
        chartDto.setChartTitle(Constants.API_REPORT_DURATION.convert(duration)+" API 调用趋势");
        chartDto.setyTitle("次数（次）");
        chartDto.setxTitle("时间");
        chartDto.setRenderTo("chart");
        chartDto.setType(Constants.CHART_TYPE.SPLINE.getValue());
        List<Object[]> objects = apiLogDAO.groupByHour(Date.from(startDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(endDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), userId);
        List<DataDto> dataDtos = new ArrayList<>();
        objects.stream().forEach(x -> dataDtos.add(new DataDto((String) x[0], ((BigInteger) x[1]).intValue())));
        chartDto.setxAxis(new String[24]);

        List<ChartDataDto> chartDataDtos = new ArrayList<ChartDataDto>();

        ChartDataDto highPressDataDto = new ChartDataDto();
        highPressDataDto.setName("调用次数");
        highPressDataDto.setData(new Integer[24]);
        highPressDataDto.setLineWidth(1.0);
        for (int i = 0; i < 24; i++) {
            chartDto.getxAxis()[i] = String.valueOf(i)+":00";
            highPressDataDto.getData()[i] = 0;
        }
        dataDtos.stream().forEach(dataDto -> {
            highPressDataDto.getData()[Integer.valueOf(dataDto.getItem())] = dataDto.getCnt();
        });
        chartDataDtos.add(highPressDataDto);

        chartDto.setChartDataDtos(chartDataDtos);
        return chartDto;
    }

    /**
     * 按天统计数据
     * @param userId
     * @param startDate
     * @param endDate
     * @param duration
     * @return
     */
    public ChartDto dayData(Long userId, LocalDate startDate, LocalDate endDate, Integer duration) {
        ChartDto chartDto = new ChartDto();
        chartDto.setChartTitle(Constants.API_REPORT_DURATION.convert(duration)+" API 调用趋势");
        chartDto.setyTitle("次数（次）");
        chartDto.setxTitle("时间");
        chartDto.setRenderTo("chart");
        chartDto.setType(Constants.CHART_TYPE.SPLINE.getValue());
        List<Object[]> objects = apiLogDAO.groupByDay(Date.from(startDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(endDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), userId);
        List<DataDto> dataDtos = new ArrayList<>();
        objects.stream().forEach(x -> dataDtos.add(new DataDto((String) x[0], ((BigInteger) x[1]).intValue())));
        int length = startDate.until(endDate).getDays();
        chartDto.setxAxis(new String[length]);

        List<ChartDataDto> chartDataDtos = new ArrayList<ChartDataDto>();

        ChartDataDto highPressDataDto = new ChartDataDto();
        highPressDataDto.setName("调用次数");
        highPressDataDto.setData(new Integer[length]);
        highPressDataDto.setLineWidth(1.0);
        for (int i = 0; i < length; i++) {
            chartDto.getxAxis()[i] = startDate.plusDays(i).toString();
            highPressDataDto.getData()[i] = 0;
        }
        dataDtos.stream().forEach(dataDto -> {
            int index = startDate.until(LocalDate.parse(dataDto.getItem())).getDays();
            highPressDataDto.getData()[index] = dataDto.getCnt();
        });
        chartDataDtos.add(highPressDataDto);

        chartDto.setChartDataDtos(chartDataDtos);
        return chartDto;
    }
    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request){
        String remoteAddr = request.getHeader("X-Real-IP");
        if (StringUtils.hasText(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (StringUtils.hasText(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (StringUtils.hasText(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return (remoteAddr != null ? remoteAddr : request.getRemoteAddr()).split(",")[0];// 有时会出现以，分割的多个IP
    }

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        LocalDate start = now.minusDays(29);
        LocalDate end = now.plusDays(1);
        System.out.println(start.until(start).getDays());
    }
}