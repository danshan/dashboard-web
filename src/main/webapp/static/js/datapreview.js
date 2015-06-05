var domCode = document.getElementById('sidebar-code');
var domGraphic = document.getElementById('graphic');
var domMain = document.getElementById('main');
var domMessage = document.getElementById('wrong-message');
var iconResize = document.getElementById('icon-resize');
var needRefresh = false;
var mime = $('#J_datasource option:selected').attr("mime");
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
    mime = $('#J_datasource option:selected').attr("mime");
    editor = CodeMirror.fromTextArea(
        document.getElementById("code"),
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
    if (isBtnRefresh) {
        needRefresh = true;
        focusGraphic();
        return;
    }
    needRefresh = false;
    try {
        /*
        (new Function(editor.doc.getValue()))();
        var dataapi = $("#J_dataapi").val();
        var datamap = loadData(dataapi);
        */
    } catch (e) {
        domMessage.innerHTML = e;
    }
}

function checkDataApi() {
    var dataapi = $("#J_dataapi").val();
    window.open(dataapi);
}

launchExample();

var isExampleLaunched;
function launchExample() {
    if (isExampleLaunched) {
        return;
    }

    isExampleLaunched = 1;
    changeDatasource();
    refresh();
}
