/**
 * Created by Dan on 6/4/15.
 */
    // 路径配置
require.config({
    paths: {
        echarts: '/static/js/echarts'
    }
});

$('.input-daterange').datepicker({
    format: "yyyy-mm-dd",
    clearBtn: true,
    todayHighlight: true
});

var needRefresh = false;
var myChart;
var echarts;
var domMessage = document.getElementById('wrong-message');

function drawChart(option) {
    myChart.setOption(option, false);
}

function drawTable(datamap) {
    var datalist = datamap.data;
    var columns = datamap.columns;
    var table = $("#table_" + chartData.pageId);

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

}

function focusGraphic() {
    if (needRefresh) {
        myChart.showLoading();
        setTimeout(refresh, 1000);
    }
}

function fetchFilters() {
    var params = "";
    $(".J_form input[type='text']").each(function(index, input) {
        var input = $(input);
        if (input.val().trim().length > 0) {
            params += input.attr("name") + ':' + input.val().trim() + ',';
        }
    });
    return params;
}

function refresh(isBtnRefresh){
    domMessage.innerHTML = '';
    var filters = fetchFilters();
    if (isBtnRefresh) {
        needRefresh = true;
        focusGraphic();
        return;
    }
    needRefresh = false;
    if (myChart && myChart.dispose) {
        myChart.dispose();
    }
    myChart = echarts.init(document.getElementById('chart_' + chartData.pageId));
    window.onresize = myChart.resize;
    try {
        var chartOption = loadOption(buildOptionApi(chartData.pageId));
        var datamap = loadData(buildDataApi(chartData.pageId, filters));
        var option = buildOption(chartOption, datamap);
        drawChart(option);
        drawTable(datamap);
        /*
        var datamap = loadData(dataapi);
        var mergedOption = buildOption(option, datamap);
        myChart.setOption(mergedOption);
        */
    } catch (e) {
        domMessage.innerHTML = e;
    }
}

launchMonitor();

function requireCallback (ec) {
    echarts = ec;
    refresh();
    window.onresize = myChart.resize;
}

var isMonitorLaunched;
function launchMonitor() {
    if (isMonitorLaunched) {
        return;
    }

    isMonitorLaunched = 1;
    require(
        [
            '.',
            'echarts/chart/line',
            'echarts/chart/bar',
            'echarts/chart/scatter',
            /*
            'echarts/chart/k',
            'echarts/chart/pie',
            'echarts/chart/radar',
            'echarts/chart/force',
            'echarts/chart/chord',
            'echarts/chart/gauge',
            'echarts/chart/funnel',
            'echarts/chart/eventRiver',
            'echarts/chart/venn',
            'echarts/chart/treemap',
            'echarts/chart/tree',
            'echarts/chart/wordCloud'
            */
        ],
        requireCallback
    );
}


