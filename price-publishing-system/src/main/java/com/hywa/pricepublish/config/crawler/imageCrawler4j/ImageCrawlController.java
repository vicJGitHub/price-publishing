package com.hywa.pricepublish.config.crawler.imageCrawler4j;


import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.apache.http.message.BasicHeader;

import java.util.HashSet;


public class ImageCrawlController {

    public static void main(String[] args) throws Exception {
        String rootFolder = "e:/crawl/c";
        //线程数
        int numberOfCrawlers = 10;
        String storageFolder = "e:/crawl/data";
        CrawlConfig config = new CrawlConfig();
        //设置爬虫文件存储位置
        config.setCrawlStorageFolder(rootFolder);
        config.setIncludeBinaryContentInCrawling(true);
        // HashSet<BasicHeader> collections = new HashSet<BasicHeader>();
        //collections.add(new BasicHeader("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3192.0 Safari/537.36"));
        //collections.add(new BasicHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8"));
        //collections.add(new BasicHeader("Accept-Encoding", "gzip,deflate,sdch"));
        //collections.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6"));
        //collections.add(new BasicHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8"));
        // collections.add(new BasicHeader("Connection", "keep-alive"));
        //collections.add(new BasicHeader("Cookie", "bid=fp-BlwmyeTY; __yadk_uid=dLpMqMsIGD1N38NzhbcG3E6QA33NQ9bE; ps=y; _pk_ref.100001.8cb4=%5B%22%22%2C%22%22%2C1506515077%2C%22https%3A%2F%2Faccounts.douban.com%2Flogin%3Falias%3D793890838%2540qq.com%26redir%3Dhttps%253A%252F%252Fwww.douban.com%26source%3DNone%26error%3D1013%22%5D; ll=\"108296\"; ue=\"793890838@qq.com\"; __utmt=1; _ga=GA1.2.388925103.1505404043; _gid=GA1.2.1409223546.1506515083; dbcl2=\"161927939:ZDwWtUnYaH4\"; ck=rMaO; ap=1; push_noty_num=0; push_doumail_num=0; __utma=30149280.388925103.1505404043.1506510959.1506515077.8; __utmb=30149280.22.9.1506516374528; __utmc=30149280; __utmz=30149280.1506510959.7.5.utmcsr=accounts.douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/login; __utmv=30149280.16192; _pk_id.100001.8cb4=1df4f52fdf296b72.1505404042.8.1506516380.1506512502.; _pk_ses.100001.8cb4=*"));
        //config.setDefaultHeaders(collections);
        //抓取恢复
        config.setResumableCrawling(true);
        //最大抓取深度
        config.setMaxDepthOfCrawling(1);
        //爬虫通过代理
        //config.setProxyHost("http://111.121.193.214");
        //config.setProxyPort(3218);
        //爬取的种子页面(根据这个一级url匹配相关资源)
        String[] crawlDomains = {"http://www.58pic.com"};
        //实例化页面获取器
        PageFetcher pageFetcher = new PageFetcher(config);
        //设置requestHeader信息
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        robotstxtConfig.setUserAgentName("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");
        //实例化服务配置信息
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        for (String domain : crawlDomains) {
            controller.addSeed(domain);
        }
        ImageCrawler.configure(crawlDomains, storageFolder);
        //启动
        controller.start(ImageCrawler.class, numberOfCrawlers);
    }
}