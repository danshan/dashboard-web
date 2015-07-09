package com.dianping.wed.monitor.web.pagelet.monitor;

import com.dianping.w3c.pagelet.Pagelet;
import com.dianping.w3c.pagelet.annotation.PageletModel;
import com.dianping.w3c.pagelet.template.TemplateSource;

/**
 * @author dan.shan
 * @since 2015-07-07 12:07
 */
@PageletModel(templateKey = "/WEB-INF/pages/pagelet/monitor/pageListPagelet.ftl", source = TemplateSource.FILE)
public class PageListPagelet implements Pagelet {

    @Override
    public String doBusiness() {
        return SUCCESS;
    }

}
