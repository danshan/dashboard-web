/**
 * Created by Dan on 6/4/15.
 */
    // 路径配置
require.config({
    paths: {
        echarts: '/static/js/echarts'
    }
});

function drawChart(option, pageId) {
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
            myChart.setOption(chartOption, false);
        }
    );
}

function drawTable(datamap, pageId) {
    var datalist = datamap.data;
    var columns = datamap.columns;
    var table = $("#table_" + pageId);

    var html = "<thead><tr>";
    for (var i in datamap.columns) {
        html += "<th>" + columns[i] + "</th>";
    }
    html += "</tr></thead>";
    html += "<tbody>";
    for (var i in datalist) {
        html += "<tr>";
        for (var j in datalist[i]) {
            html += "<td>" + datalist[i][j] + "</td>";
        }
        html += "</tr>";
    }
    html += "</tbody>";
    table.get(0).innerHTML = html;

    var tbody = table.find("tbody");
}

var chartOption = loadOption(buildOptionApi(chartData.pageId));
var datamap = loadData(buildDataApi(chartData.pageId));
var option = buildOption(chartOption, datamap);
drawChart(option, chartData.pageId);
drawTable(datamap, chartData.pageId);
