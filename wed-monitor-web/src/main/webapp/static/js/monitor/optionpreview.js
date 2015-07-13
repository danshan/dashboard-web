var myChart;
var domCode = document.getElementById('sidebar-code');
var domGraphic = document.getElementById('graphic');
var domMain = document.getElementById('main');
var domMessage = document.getElementById('wrong-message');
var iconResize = document.getElementById('icon-resize');
var needRefresh = false;

var enVersion = location.hash.indexOf('-en') != -1;
var hash = location.hash.replace('-en','');
hash = hash.replace('#','') || (needMap() ? 'default' : 'macarons');
hash += enVersion ? '-en' : '';

var curTheme;
function requireCallback (ec) {
    curTheme = {};
    echarts = ec;
    refresh();
    window.onresize = myChart.resize;
}

function autoResize() {
    if ($(iconResize).hasClass('glyphicon-resize-full')) {
        focusCode();
        iconResize.className = 'glyphicon glyphicon-resize-small';
    }
    else {
        focusGraphic();
        iconResize.className = 'glyphicon glyphicon-resize-full';
    }
}

function focusCode() {
    domCode.className = 'col-md-8 ani';
    domGraphic.className = 'col-md-4 ani';
}

function focusGraphic() {
    domCode.className = 'col-md-4 ani';
    domGraphic.className = 'col-md-8 ani';
    if (needRefresh) {
        myChart.showLoading();
        setTimeout(refresh, 1000);
    }
}

var editor = CodeMirror.fromTextArea(
    document.getElementById("code"),
    {
        mode: "text/javascript",
        lineNumbers: true
    }
);
editor.setOption("theme", 'monokai');


editor.on('change', function(){needRefresh = true;});

function refresh(isBtnRefresh){
    domMessage.innerHTML = '';
    if (isBtnRefresh) {
        needRefresh = true;
        focusGraphic();
        return;
    }
    needRefresh = false;
    if (myChart && myChart.dispose) {
        myChart.dispose();
    }
    myChart = echarts.init(domMain, curTheme);
    window.onresize = myChart.resize;
    try {
        (new Function(editor.doc.getValue()))();
        var dataapi = $("#J_dataapi").val();
        var datamap = loadData(dataapi);
        var mergedOption = buildOption(option, datamap);
        myChart.setOption(mergedOption);
    } catch (e) {
        domMessage.innerHTML = e;
    }
}

function needMap() {
    var href = location.href;
    return href.indexOf('map') != -1
        || href.indexOf('mix3') != -1
        || href.indexOf('mix5') != -1
        || href.indexOf('dataRange') != -1;

}

function checkDataApi() {
    var dataapi = $("#J_dataapi").val();
    window.open(dataapi);
}

var echarts;
// for echarts online home page
require.config({
    paths: {
        echarts: '/static/js/echarts'
    }
});
launchExample();

var isExampleLaunched;
function launchExample() {
    if (isExampleLaunched) {
        return;
    }

    isExampleLaunched = 1;
    require(
        [
            'echarts',
            'echarts/chart/line',
            'echarts/chart/bar',
            'echarts/chart/scatter',
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
        ],
        requireCallback
    );
}

function saveQueryTemplate() {
    var chartOption = editor.getValue(),
        pageId = $('.J_pageId').val(),
        action = "update";

    try {
        $.post(
            "/ajax/monitor/optionpreviewop",
            {
                action: action,
                pageId: pageId,
                chartOption: chartOption
            },
            function(data){
                if (data.code != 200) {
                    alert(data.code.msg.message);
                } else {
                    alert("保存成功");
                }
            },
            "json"
        );
    } catch (e) {
        domMessage.innerHTML = e;
    }
}
