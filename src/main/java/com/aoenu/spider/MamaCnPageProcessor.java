package com.aoenu.spider;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @CreateDate: Created in 2019-05-29 15:58
 * @Author: baoben.wu@hand-china.com
 */
@Service("MamaCnPageProcessor")
public class MamaCnPageProcessor implements PageProcessor {

    /**
     * 编码、抓取间隔、重试次数等
     */
    private Site site = Site.me()
            .setCharset("UTF-8")
            .setRetryTimes(3)
            .setSleepTime(1000);

    @Override
    public void process(Page page) {
        System.out.println(page.getHtml().toString());
        List<Map<String, String>> pageList = Lists.newArrayList();
        for (int i = 1; i < 21; i++) {
            Map map = new HashMap(8);
            map.put("title", page.getHtml().xpath("//div[@class='imgbox'][" + i + "]/div[@class='txtArea']/dl/dt/a/text()").get());
            map.put("username", page.getHtml().xpath("//div[@class='imgbox'][" + i + "]/div[@class='txtArea']/dl/dd/span[1]/a/text()").get());
            map.put("babyAge", page.getHtml().xpath("//div[@class='imgbox'][" + i + "]/div[@class='txtArea']/dl/dd/span[2]/text()").get());
            map.put("city", page.getHtml().xpath("//div[@class='imgbox'][" + i + "]/div[@class='txtArea']/dl/dd/span[3]/text()").get());
            map.put("replyAndView", page.getHtml().xpath("//div[@class='imgbox'][" + i + "]/div[@class='txtArea']/dl/dd/div[@class='timebar']/span[1]/text()").get());
            map.put("lastActiveTime", page.getHtml().xpath("//div[@class='imgbox'][" + i + "]/div[@class='txtArea']/dl/dd/div[@class='timebar']/span[2]/text()").get());
            pageList.add(map);
        }
        if (CollectionUtils.isNotEmpty(pageList)) {
            page.putField(Constant.RESULT_LIST_MAP, pageList);
        }
        List<String> nextUrl = page.getHtml().xpath("//div[@class='pages']/div[@class='pg']/").nodes()
                .stream().filter(button -> "下一页".equals(button.xpath("//a[@class='nxt']/text()").get()))
                .map(url -> url.xpath("//a[@class='nxt']/@href").get()).collect(Collectors.toList());
        page.addTargetRequests(nextUrl);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        //启动爬虫
        //开启5个线程抓取
        Spider.create(new MamaCnPageProcessor())
                .addUrl("http://www.mama.cn/q/tlq/group/20562/50/")
                .thread(5)
                .run();
    }
}
