package com.springboot.main.eimm.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.main.core.controller.M;
import com.springboot.main.eimm.permission.service.PermissionService;
import com.springboot.main.eimm.search.entity.Search;
import com.springboot.main.eimm.search.service.SearchService;
import com.springboot.main.eimm.search.util.SearchUtils;
import com.springboot.main.eimm.title.entity.Articlel;
import com.springboot.main.eimm.title.entity.ArtitleController;
import com.springboot.main.eimm.title.entity.HttpUtil;
import com.springboot.main.eimm.title.entity.LuceneDao;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @method lucene全文检索
 * @author Mr yi
 * @time 2019年5月24日
 */
@Controller
@Slf4j
@RequestMapping("/search")
public class SearchController {

	@Autowired
	SearchService searchService;
	
	/**
	 * 
	 * @method 添加索引(首次启动第一次添加索引，会对全部数据进行第一次索引添加，后续不再执行此方法，只会添加、更新或删除索引）
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addAllIndex") 
	public String addAllIndex() throws Exception {
		searchService.addIndex(); 
		return "login";
	}
	
	/**
	 * 
	 * @method 根据关键字查找索引，返回符合关键字的全部数据
	 * @author Mr yi
	 * @time 2019年5月27日
	 * @param model
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/search")
	public String search(Model model,@RequestParam("keyword") String keyword) throws Exception{
	    List<Search> list=SearchUtils.findIndex(keyword);
	    System.out.println(list);
	    model.addAttribute("list", list);
		return M.goTo("search/search_list");
	}
	
	
	
}
