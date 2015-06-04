function buildDataApi(pageId) {
    return 'http://localhost:8080/ajax/monitor/data?pageId=' + pageId;
}

function buildOptionApi(pageId) {
    return 'http://localhost:8080/ajax/monitor/options?pageId=' + pageId;
}

function loadOption(optionapi) {
    var result;
    var ajaxOption = {
        type: "GET",
        url: optionapi,
        async: false,
        dataType: "json",
        success: function (data) {
            result = data.msg.option;
        },
        error: function (error) {
            alert("call option api failed, " + optionapi);
        }
    };
    $.ajax(ajaxOption);
    return result;
    /*
    $.getJSON(optionapi, function (data) {
        chartOption = data.msg.option;
        if (chartOption != undefined && columns.length > 0) {
            drawChart(pageId);
            drawTable(pageId);
        }
    });
    */
}

function loadData(dataapi) {
    var result;
    var ajaxOption = {
        type: "GET",
        url: dataapi,
        async: false,
        dataType: "json",
        success: function (data) {
            result = data.msg;
        },
        error: function (error) {
            alert("call data api failed, " + dataapi);
        }
    };
    $.ajax(ajaxOption);
    return result;

    /*
    $.getJSON(dataapi, function (data) {
        columns = data.msg.columns;
        datalist = data.msg.data;
        if (chartOption != undefined && columns.length > 0) {
            drawChart(pageId);
            drawTable(pageId);
        }
    });
    */
}

function buildOption(chartOption, datamap) {
    var columns = datamap.columns;
    var datalist = datamap.data;

    chartOption.legend.data = columns.slice(1);
    if (chartOption.xAxis instanceof Array) {
        chartOption.xAxis[0].data = fetchDataByColumn(datalist, 0);
    } else {
        chartOption.xAxis.data = fetchDataByColumn(datalist, 0);
    }

    for (var i = 0; i < chartOption.series.length; i++) {
        chartOption.series[i].name = columns[i + 1];
        chartOption.series[i].data = fetchDataByColumn(datalist, i + 1);
    }
    return chartOption;
}

function fetchDataByColumn(datalist, columnIndex) {
    var list = [];
    for (var i = 0; i < datalist.length; i++) {
        list.push(datalist[i][columnIndex]);
    }
    return list;
}