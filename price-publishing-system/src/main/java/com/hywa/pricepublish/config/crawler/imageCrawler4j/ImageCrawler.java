package com.hywa.pricepublish.config.crawler.imageCrawler4j;

import com.google.common.io.Files;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.TextParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

/*
 */
public class ImageCrawler extends WebCrawler {

  /*
  过滤dehouzui
   */
  private static final Pattern filters = Pattern
      .compile(".*(\\.(css|js|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    /*
     *匹配的后缀
     */
    private static final Pattern imgPatterns = Pattern.compile(".*(\\.(bmp|gif|jpe?g!?|png|tiff?))$");

    private static File storageFolder;
    private static String[] crawlDomains;


    //验证存储文件夹是否存在,不存在就创建
    public static void configure(String[] domain, String storageFolderName) {
        crawlDomains = domain;

        storageFolder = new File(storageFolderName);
        if (!storageFolder.exists()) {
            storageFolder.mkdirs();
        }
    }


    //[匹配爬取的url是否符合规定
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if (filters.matcher(href).matches()) {
            return false;
        }
        return true;
   /* if (imgPatterns.matcher(href).matches()) {
      return true;
    }*/

        /*for (String domain : crawlDomains) {
            if (href.startsWith(domain)) {
                return true;
            }
        }
        return false;*/
    }


    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        //获取所有外联url匹配html信息
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlData = (HtmlParseData) page.getParseData();
            try {
                Files.write(htmlData.getHtml().getBytes(), new File(storageFolder.getAbsolutePath() + "/" + UUIDUtils.randomUUID() + ".html"));
            } catch (IOException iox) {
                iox.printStackTrace();
            }
        }

        //匹配文本信息
        if (page.getParseData() instanceof TextParseData) {
            TextParseData textParseData = (TextParseData) page.getParseData();
            try {
                Files.write(textParseData.getTextContent().getBytes(), new File(storageFolder.getAbsolutePath() + "/" + UUIDUtils.randomUUID() + ".text"));
            } catch (IOException iox) {
                iox.printStackTrace();
            }
        }
        //匹配(二进制资源)图片后缀,10以上
    if (!imgPatterns.matcher(url).matches() ||
        !((page.getParseData() instanceof BinaryParseData) || (page.getContentData().length < (10 * 1024)))) {
      return;
    }
        //格式
        String extension = url.substring(url.lastIndexOf('.'));
        //名称
        String hashedName = UUID.randomUUID() + extension;
        String filename = storageFolder.getAbsolutePath() + "/" + hashedName;
        //写入图片文件
        try {
            Files.write(page.getContentData(), new File(filename));
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }
}