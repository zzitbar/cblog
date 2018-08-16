/**
 * Sand-Signika theme for Highcharts JS
 * @author Torstein Honsi
 */

// Load the fonts
Highcharts.createElement('link', {
    rel: 'stylesheet',
    type: 'text/css'
}, null, document.getElementsByTagName('head')[0]);

// Add the background image to the container
Highcharts.wrap(Highcharts.Chart.prototype, 'getContainer', function (proceed) {
    proceed.call(this);
});


Highcharts.theme = {
    colors: ['#2ec7c9','#dd8668','#b6a2de','#5ab1ef'],
    lang: {
        thousandsSep: ','
    },
    chart: {
        backgroundColor: null,
        style: {
            fontFamily: "\"Lucida Grande\", \"Lucida Sans Unicode\", Verdana, Arial, Helvetica, sans-serif"
        }
    },
    title: {
        text:null
    },
    tooltip: {
        shared: true,
        crosshairs: true,
        borderWidth: 0,
        headerFormat: '<span style="font-size: 15px">{point.key}</span><br/>',
        style: {
            fontSize: '14px'
        }
    },
    legend: {
        itemStyle: {
            fontSize: '13px'
        }
    },
    credits: {
        enabled: false
    },
    yAxis: {
        allowDecimals: false
    },
    plotOptions: {
        spline: {
            dataLabels: {
                enabled: true,
                style: {
                    textShadow: null
                }
            }
        },
        column: {
            dataLabels: {
                enabled: true,
                style: {
                    textShadow: null
                }
            }
        },
        series: {
            pointWidth: 20,
            marker: {
                // fillColor: '#FFFFFF',
                lineWidth: 2,
                lineColor: null,
                radius: 3,
                symbol:'circle'
            },
            dataLabels: {
                crop: false,
                overflow: 'none',
                style: {
                    fontFamily: 'Arial, Verdana, sans-serif',
                    fontSize: "12px",
                    fontWeight: 'normal'
                }
            }
        }
    }
};

// Apply the theme
Highcharts.setOptions(Highcharts.theme);
// $("#main").css({"text-align":"center","height":$(window).height()+"px","line-height": $(window).height()+"px"});