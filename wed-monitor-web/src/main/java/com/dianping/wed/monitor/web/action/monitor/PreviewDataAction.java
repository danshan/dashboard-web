package com.dianping.wed.monitor.web.action.monitor;

import com.dianping.wed.monitor.config.service.dto.MonitorQueryTemplateDTO;
import com.dianping.wed.monitor.service.MonitorService;
import com.dianping.wed.monitor.web.action.BaseAction;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-03 15:23
 */
public class PreviewDataAction extends BaseAction {

    @Setter @Getter
    private String pageId;
    @Getter
    private MonitorQueryTemplateDTO queryTemplate;

    @Getter
    private String demoMongoQuery = "{\n" +
            "    \"collection\": \"Event\",\n" +
            "    \"query\": {},\n" +
            "    \"keys\": {\n" +
            "        \"createTime\": 1,\n" +
            "        \"pcUrlRewriteID\": 1,\n" +
            "        \"mUrlRewriteID\": 1\n" +
            "    }\n" +
            "}\n";
    @Getter
    private String demoMongoXAxis = "createTime";

    @Resource
    private MonitorService monitorService;

    @Override
    protected String doExecute() throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be null.");

        this.queryTemplate = monitorService.loadQueryTemplateByPageId(this.pageId);
        return SUCCESS;
    }

    @Override
    protected void doValidate() throws Exception {

    }

    @Override
    protected void doPrepare() throws Exception {

    }
}
