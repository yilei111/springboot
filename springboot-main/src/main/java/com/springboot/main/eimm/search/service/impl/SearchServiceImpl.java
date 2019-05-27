package com.springboot.main.eimm.search.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexableField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.springboot.main.eimm.search.dao.SearchMapper;
import com.springboot.main.eimm.search.entity.Search;
import com.springboot.main.eimm.search.service.SearchService;
import com.springboot.main.eimm.search.util.LuceneUtils;
import com.springboot.main.eimm.search.util.SearchUtils;
import com.springboot.main.eimm.title.entity.ArticleUtil;
import com.springboot.main.eimm.title.entity.Articlel;

@Service
@Transactional(rollbackFor = Exception.class)
public class SearchServiceImpl implements SearchService {

	@Autowired
	SearchMapper searchMapper;

	/**
	 * 
	 * @method 根据表名获取Search对象信息
	 * @author Mr yi
	 * @time 2019年5月23日
	 * @param table 表名（小写）
	 * @return
	 */
	@Override
	public Search getSearchByTable(String table) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", table.toLowerCase());
		List<Search> list = searchMapper.selectByMap(map);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;

	}

	 
	@Override
	public void addIndex() throws Exception {
		List<Search> list = searchMapper.selectList(null);
		Map<String, Object> map = new HashMap<String, Object>();
		for (Search search : list) {
			map.put("table", search.getType());
			List<Map<String, Object>> dataList = searchMapper.getList(map);
			System.out.println(dataList);
			for (Map<String, Object> dataMap : dataList) {
				Document document = SearchUtils.mapToDocument(dataMap, search);
				System.out.println(document);
				SearchUtils.addIndex(document);
			}
		}
	}

}
