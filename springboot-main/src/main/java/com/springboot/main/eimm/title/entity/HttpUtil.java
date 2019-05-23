package com.springboot.main.eimm.title.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class HttpUtil {

    public static List<Articlel> getNewByPath(String path) throws Exception {
        List<Articlel> articles = new ArrayList<Articlel>();
        // 使用httpcomponents发送请求
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(path);
        HttpResponse response = httpClient.execute(httpGet);

        // 使用sax解析器解析数据
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(response.getEntity().getContent());
        Element root = document.getRootElement();
        Element element = root.element("channel");
        List<Element> elements = element.elements("item");
        for (Element e : elements) {
            //封装解析的数据
            Articlel article = new Articlel();
            article.setId(UUID.randomUUID().toString());
            article.setTitle(e.elementText("title"));
            article.setContent(e.elementText("description"));
            article.setUrl(e.elementText("link"));
            article.setAuthor(e.elementText("author"));

            article.setDate(e.elementText("pubDate"));
            articles.add(article);

            /*System.out.println("title===" + e.elementText("title"));
            System.out.println("link===" + e.elementText("link"));
            System.out.println("author===" + e.elementText("author"));
            System.out.println("pubDate===" + e.elementText("pubDate"));
            System.out.println("description===" + e.elementText("description"));
            System.out.println("=================================================");
            System.out.println();
            System.out.println();*/
        }
        return articles;
    }
    //测试成功之后这段代码可以注释掉
    public static void main(String[] args) throws Exception {
        List<Articlel> articles = HttpUtil.getNewByPath("http://news.qq.com/newsgn/rss_newsgn.xml");
        for(Articlel a : articles){
            System.out.println(a.getTitle());
            System.out.println(a.getDate());
            System.out.println(a.getContent());
            System.out.println("=====================");
        }
    }
}
