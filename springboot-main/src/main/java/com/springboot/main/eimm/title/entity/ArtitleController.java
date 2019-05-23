package com.springboot.main.eimm.title.entity;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.main.eimm.role.controller.RoleController;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/artitle")
public class ArtitleController {

	@RequestMapping("/artitle")
	public String logout1() {
		String path = "http://news.qq.com/newsgn/rss_newsgn.xml";
	    try {
	        List<Articlel> articles = HttpUtil.getNewByPath(path);
	        LuceneDao luceneDao = new LuceneDao();
	        for(Articlel a : articles){
	        	System.out.println("==============++++");
	            luceneDao.addIndex(a);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "login";
      
	}
	@RequestMapping("/search")
	public String testsearcher() throws Exception{
	    String keywords="联盟委员会";
	    LuceneDao luceneDao = new LuceneDao();
	    List<Articlel> listArticles=luceneDao.findIndex(keywords);
	    for(Articlel article:listArticles){
	        System.out.println(article.getId());
	        System.out.println(article.getTitle());
	        System.out.println(article.getAuthor());
	        System.out.println(article.getUrl());
	        System.out.println(article.getContent());
	        System.out.println(article.getDate());
	    }
	    return "login";
	}
}
