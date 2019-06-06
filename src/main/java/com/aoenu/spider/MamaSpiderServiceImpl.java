package com.aoenu.spider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import javax.annotation.Resource;

/**
 * @Description:
 * @CreateDate: Created in 2019-05-29 21:38
 * @Author: baoben.wu@hand-china.com
 */
@Service
public class MamaSpiderServiceImpl implements MamaSpiderService {

    public static Logger logger = LoggerFactory.getLogger(MamaSpiderServiceImpl.class);

    @Autowired
    private MamaCnPageProcessor pageProcessor;

    @Autowired
    private InsertToMysqlPipeline pipeline;


    @Override
    public void startSpider() {

        final String startUrl = "http://www.mama.cn/q/tlq/club201901_1.html";

        AsyncUtil.execute(new Runnable() {

            @Override
            public void run() {
                long startTime, endTime;
                startTime = System.currentTimeMillis();
                logger.info("【爬虫开始】");
                Spider.create(pageProcessor)
                        // 从url开始抓
                        .addUrl(startUrl)
                        // 设置Scheduler，使用File来管理URL队列, 可以去重爬取
//                        .setScheduler(new FileCacheQueueScheduler(cacheQueuePath + "/queue"))
                        // 设置Pipeline，将结果输出到elasticSearch
                        .addPipeline(pipeline)
                        // 开启5个线程同时执行
                        .thread(5)
                        // 启动爬虫
                        .run();
                endTime = System.currentTimeMillis();
                logger.info("【爬虫结束】耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库！");

            }
        });

        logger.info("spider start!");
    }
}
