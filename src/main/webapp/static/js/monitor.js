/**
 * Created by Dan on 6/4/15.
 */
    // 路径配置
require.config({
    paths: {
        echarts: '/static/js/echarts'
    }
});

var option,
    datalist = [],
    columns = [];

function loadOptions(pageId) {
    $.getJSON('http://localhost:8080/ajax/monitor/options?pageId=' + pageId, function (data) {
        option = data.msg.option;
        if (option != undefined && columns.length > 0) {
            drawChart(pageId);
        }
    });
}

function loadData(pageId) {
    $.getJSON('http://localhost:8080/ajax/monitor/data?pageId=' + pageId, function (data) {
        columns = data.msg.columns;
        datalist = data.msg.data;
        if (option != undefined && columns.length > 0) {
            drawChart(pageId);
        }
    });
}

function drawChart(pageId) {
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/line', // 使用柱状图就加载bar模块，按需加载
            'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('chart_' + pageId));

            option.legend.data = columns.slice(1);
            option.xAxis.data = fetchDataByColumn(0);
            for (var i = 0; i < option.series.length; i++) {
                option.series[i].name = columns[i + 1];
                option.series[i].data = fetchDataByColumn(i + 1);
            }
            myChart.setOption(option);
        }
    );
}

function fetchDataByColumn(columnIndex) {
    var list = [];
    for (var i = 0; i < datalist.length; i++) {
        list.push(datalist[i][columnIndex]);
    }
    return list;
}

loadOptions(chartData.pageId);
loadData(chartData.pageId);
