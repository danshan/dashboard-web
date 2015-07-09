/**
 * Created by Dan on 7/9/15.
 */

function submitPageConfig() {

    var pageId = $(".J_pageId").val(),
        pageName = $(".J_pageName").val(),
        pageDesc = $(".J_pageDesc").val(),
        action = pageId ? "update" : "add";


    $.post(
        "/ajax/monitor/pageconfigop", {
            pageId: pageId,
            pageName: pageName,
            pageDesc: pageDesc,
            action: action
        }, function(data) {
            if (data.code == 200) {
                window.location.href='/monitor/pageconfiglist';
            } else {
                alert(data.msg.message);
            }
        }

    ).error(
        function(error) {
            alert(error);
        }
    );

}