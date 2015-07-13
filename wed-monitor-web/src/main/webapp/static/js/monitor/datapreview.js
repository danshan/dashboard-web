var domCode = document.getElementById('sidebar-code');
var domGraphic = document.getElementById('graphic');
var domMessage = document.getElementById('wrong-message');
var iconResize = document.getElementById('icon-resize');
var needRefresh = false;
var mime = $('.J_datasource option:selected').attr("mime");
var dataResult = $('.J_result');
var editor;

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
        setTimeout(refresh, 1000);
    }
}

function changeDatasource() {
    mime = $('.J_datasource option:selected').attr("mime");
    $('.J_codeMirror').find('.CodeMirror').remove();
    editor = CodeMirror.fromTextArea(
        document.getElementsByClassName("J_code")[0],
        {
            mode: mime,
            lineNumbers: true
        }
    );
    editor.setOption("theme", 'monokai');
    editor.on('change', function(){needRefresh = true;});
}

function refresh(isBtnRefresh){
    domMessage.innerHTML = '';
    var queryTemplate = editor.getValue(),
        pageId = $('.J_pageId').val(),
        datasource = $('.J_datasource option:selected').val(),
        xAxis = $(".J_xAxis").val();

    if (isBtnRefresh) {
        needRefresh = true;
        focusGraphic();
        return;
    }
    needRefresh = false;
    try {
        $.post(
            "/ajax/monitor/datapreview",
            {
                xAxis: xAxis,
                datasource: datasource,
                pageId: pageId,
                queryTemplate: queryTemplate
            },
            function(data){
                console.log(data); //  2pm
                dataResult.html(JsonUti.convertToString(data));
            },
            "json"
        );
    } catch (e) {
        domMessage.innerHTML = e;
    }
}

launchExample();

var isExampleLaunched;
function launchExample() {
    if (isExampleLaunched) {
        return;
    }

    isExampleLaunched = 1;
    changeDatasource();
}

function saveQueryTemplate() {
    var queryTemplate = editor.getValue(),
        pageId = $('.J_pageId').val(),
        datasource = $('.J_datasource option:selected').val(),
        xAxis = $('J_xAxis').val(),
        action = "update";

    try {
        $.post(
            "/ajax/monitor/datapreviewop",
            {
                action: action,
                datasource: datasource,
                pageId: pageId,
                queryTemplate: queryTemplate,
                xAxis: xAxis
            },
            function(data){
                alert("保存成功");
            },
            "json"
        );
    } catch (e) {
        domMessage.innerHTML = e;
    }
}

var JsonUti = {
    //定义换行符
    n: "\n",
    //定义制表符
    t: "  ",
    //转换String
    convertToString: function(obj) {
        return JsonUti.__writeObj(obj, 1);
    },
    //写对象
    __writeObj: function(obj //对象
        , level //层次（基数为1）
        , isInArray) { //此对象是否在一个集合内
        //如果为空，直接输出null
        if (obj == null) {
            return "null";
        }
        //为普通类型，直接输出值
        if (obj.constructor == Number || obj.constructor == Date || obj.constructor == String || obj.constructor == Boolean) {
            var v = obj.toString();
            var tab = isInArray ? JsonUti.__repeatStr(JsonUti.t, level - 1) : "";
            if (obj.constructor == String || obj.constructor == Date) {
                //时间格式化只是单纯输出字符串，而不是Date对象
                return tab + ("\"" + v + "\"");
            }
            else if (obj.constructor == Boolean) {
                return tab + v.toLowerCase();
            }
            else {
                return tab + (v);
            }
        }
        //写Json对象，缓存字符串
        var currentObjStrings = [];
        //遍历属性
        for (var name in obj) {
            var temp = [];
            //格式化Tab
            var paddingTab = JsonUti.__repeatStr(JsonUti.t, level);
            temp.push(paddingTab);
            //写出属性名
            temp.push("\"" + name + "\" : ");
            var val = obj[name];
            if (val == null) {
                temp.push("null");
            }
            else {
                var c = val.constructor;
                if (c == Array) { //如果为集合，循环内部对象
                    temp.push(JsonUti.n + paddingTab + "[" + JsonUti.n);
                    var levelUp = level + 2; //层级+2
                    var tempArrValue = []; //集合元素相关字符串缓存片段
                    for (var i = 0; i < val.length; i++) {
                        //递归写对象
                        tempArrValue.push(JsonUti.__writeObj(val[i], levelUp, true));
                    }
                    temp.push(tempArrValue.join("," + JsonUti.n));
                    temp.push(JsonUti.n + paddingTab + "]");
                }
                else if (c == Function) {
                    temp.push("[Function]");
                }
                else {
                    //递归写对象
                    temp.push(JsonUti.__writeObj(val, level + 1));
                }
            }
            //加入当前对象“属性”字符串
            currentObjStrings.push(temp.join(""));
        }
        return (level > 1 && !isInArray ? JsonUti.n: "") //如果Json对象是内部，就要换行格式化
            + JsonUti.__repeatStr(JsonUti.t, level - 1) + "{" + JsonUti.n //加层次Tab格式化
            + currentObjStrings.join("," + JsonUti.n) //串联所有属性值
            + JsonUti.n + JsonUti.__repeatStr(JsonUti.t, level - 1) + "}"; //封闭对象
    },
    __repeatStr: function(str, times) {
        var newStr = [];
        if (times > 0) {
            for (var i = 0; i < times; i++) {
                newStr.push(str);
            }
        }
        return newStr.join("");
    }
};
