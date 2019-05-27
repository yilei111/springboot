package com.springboot.main.eimm.search.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.MapUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import com.springboot.main.eimm.search.entity.Search;
import com.springboot.main.function.article.entity.Article;

public class SearchUtils {

	/**
	 * 
	 * @method 将Article数据转换为Document
	 * @author Mr yi
	 * @time 2019年5月23日
	 * @param article 对象
	 * @return
	 */
	public static Document articleToDocument(Article article) {

		if (article == null)
			return null;

		Document document = new Document();
		IndexableField idField = new StringField("id", article.getId(), Store.YES);
		IndexableField titleField = new StringField("artitle_title", article.getArticle_title(), Store.YES);
		IndexableField contentField = new TextField("artitle_content", article.getArticle_content(), Store.YES);
		IndexableField urlField = new StringField("artitle_url", article.getArticle_url(), Store.YES);
		IndexableField authorField = new StringField("artitle_author", article.getArticle_author(), Store.YES);
		IndexableField stateField = new StringField("artitle_state", article.getArticle_state(), Store.YES);
		IndexableField typeField = new StringField("artitle_type", article.getArticle_type(), Store.YES);

		document.add(idField);
		document.add(titleField);
		document.add(contentField);
		document.add(urlField);
		document.add(authorField);
		document.add(stateField);
		document.add(typeField);
		return document;
	}

	/**
	 * 
	 * @method 将map数据转换为Document
	 * @author Mr yi
	 * @time 2019年5月23日
	 * @param map    存放一个对象具有的数据
	 * @param search 保存字段信息
	 * @return
	 */
	public static Document mapToDocument(Map<String, Object> map, Search search) {

		if (search == null)
			return null;
		Document document = new Document();
		// doc.add(new IntPoint("id", id));
		IndexableField idField = new StringField("id", MapUtils.getString(map, "id", ""), Store.YES);
		IndexableField titleField = new StringField("title", MapUtils.getString(map, search.getTitle(), ""), Store.YES);
		IndexableField contentField = new TextField("content", MapUtils.getString(map, search.getContent(), ""),Store.YES);
		IndexableField authorField = new StringField("author", MapUtils.getString(map, search.getAuthor(), ""),Store.YES);
		IndexableField typeField = new StringField("type", search.getType(), Store.YES);
		IndexableField dateField = new StringField("create_time", MapUtils.getString(map, search.getCreate_time(), ""),Store.YES);

		document.add(idField);
		document.add(titleField);
		document.add(contentField);
		document.add(authorField);
		document.add(typeField);
		document.add(dateField);
		return document;
	}

	/**
	 * 
	 * @method 添加索引
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @param document
	 * @throws Exception
	 */
	public static void addIndex(Document document) throws Exception {
		// 获取indexWrite对象
		IndexWriter indexWriter = LuceneUtils.getIndexWriter();
		try {
			// 将document写入磁盘中
			indexWriter.addDocument(document);
		} finally {// 定要注意关闭indexWrite. 包括异常下,用finally关闭.否则会导致下一次写索引失败.，修改程序后，直接删除write.lock文件后就可以
			indexWriter.close();
		}

	}

	/**
	 * 
	 * @method 多条件查询
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @throws Exception
	 */
	public static List<Search> findIndex(String keywords) throws Exception {
		IndexSearcher indexSearcher = LuceneUtils.getIndexSearcher();
		String fields[] = { "title", "content", "author" }; //需要索引的字段
		BooleanQuery.Builder builder = new BooleanQuery.Builder();
		for (String string : fields) {
			Query query = new TermQuery(new Term(string, keywords));
			builder.add(query, BooleanClause.Occur.SHOULD);
		}
		BooleanQuery query = builder.build();
		//MultiFieldQueryParser queryParser = new MultiFieldQueryParser(fields, LuceneUtils.getAnalyzer());
		
		TopDocs topDocs = indexSearcher.search(query, 500);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		
		List<Search> searchs = new ArrayList<Search>();
		// 将查询出的数据封装成search对象集合
		for (int i = 0; i < scoreDocs.length; i++) {
			Search search = new Search();
			int doc = scoreDocs[i].doc;
			Document document = indexSearcher.doc(doc);
            //id
			search.setId(document.get("id"));
			//title 高亮处理
			String title = Highlighter(query, "title", document.get("title"));
			if (title != null) {
				search.setTitle(title);
			} else {
				search.setTitle(document.get("title"));
			}
            //content 高亮处理
			String content = Highlighter(query, "content", document.get("content"));
			if (title != null) {
				search.setContent(content);
			} else {
				search.setContent(document.get("content"));
			}
			//author  
			search.setAuthor(document.get("author"));
			//type  
			search.setType(document.get("type"));
			//create_time
			search.setCreate_time(document.get("create_time"));
			searchs.add(search);
		} 
		 return searchs;
	}
	
	 

	/**
	 * 
	 * @method 控制文字高亮显示，本质上是添加了html标签样式
	 * @author Mr yi
	 * @time 2019年5月23日
	 * @param query
	 * @param field
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private static String Highlighter(Query query, String field, String value) throws Exception {
		QueryScorer queryScorer = new QueryScorer(query);
		// 所要添加的样式
		Formatter formatter = new SimpleHTMLFormatter("<span style='color:red;'>", "</span>");
		// 设置高亮分词器
		Highlighter highlighter = new Highlighter(formatter, queryScorer);
		highlighter.setTextFragmenter(new SimpleFragmenter(100));
		String text = highlighter.getBestFragment(LuceneUtils.getAnalyzer(), field, value);
		return text;
	}

	/**
	 * 
	 * @method 删除文档
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @throws IOException
	 */
	public void deleteDocuments() throws Exception {
		// 获取indexWrite对象
		IndexWriter indexWriter = LuceneUtils.getIndexWriter();
		// 删除title中含有关键词“Spark”的文档
		long count = indexWriter.deleteDocuments(new Term("title", "Spark"));
		// 除此之外IndexWriter还提供了以下方法：
		// DeleteDocuments(Query query):根据Query条件来删除单个或多个Document
		// DeleteDocuments(Query[] queries):根据Query条件来删除单个或多个Document
		// DeleteDocuments(Term term):根据Term来删除单个或多个Document
		// DeleteDocuments(Term[] terms):根据Term来删除单个或多个Document
		// DeleteAll():删除所有的Document

		// 使用IndexWriter进行Document删除操作时，文档并不会立即被删除，而是把这个删除动作缓存起来，当IndexWriter.Commit()或IndexWriter.Close()时，删除操作才会被真正执行。
		indexWriter.commit();
		indexWriter.close();
		System.out.println("删除完成:" + count);
	}
	
	

	/**
	 * @method 更新文档(实际上就是删除后新增一条)
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @param document Document文档对象
	 * @param id 原数据id
	 * @throws Exception
	 */
	public void updateDocumentTest(Document document, String id) throws Exception {
		// 获取indexWrite对象
		IndexWriter indexWriter = LuceneUtils.getIndexWriter();
		//本质上是先删除id为1的索引数据，然后又添加一个更新后的Document文档
		long count = indexWriter.updateDocument(new Term("id", id), document);
		System.out.println("更新文档:" + count);
		indexWriter.close();
	}
	
	/**
	 * 
	 * @method 按词条搜索[TermQuery是最简单、也是最常用的Query。TermQuery可以理解成为“词条搜索”，
	 * 在搜索引擎中最基本的搜索就是在索引中搜索某一词条，而TermQuery就是用来完成这项工作的。
	 * 在Lucene中词条是最基本的搜索单位，从本质上来讲一个词条其实就是一个名/值对。
	 * 只不过这个“名”是字段名，而“值”则表示字段中所包含的某个关键字。
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @param keyword
	 * @throws IOException
	 */
	public void termQuery(String keyword) throws Exception {
		String searchField = "title";
		//这是一个条件查询的api，用于添加条件
		TermQuery query = new TermQuery(new Term(searchField, keyword));
		//执行查询，并打印查询到的记录数
		executeQuery(query);
	}
	
	/**
	 * 
	 * @method 执行查询，并打印查询到的记录数
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @param query
	 * @throws Exception
	 */
	public void executeQuery(Query query) throws Exception {
		IndexSearcher indexSearcher = LuceneUtils.getIndexSearcher();
		TopDocs topDocs = indexSearcher.search(query, 100);
		//打印查询到的记录数
		System.out.println("总共查询到" + topDocs.totalHits + "个文档");
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			//取得对应的文档对象
			Document document = indexSearcher.doc(scoreDoc.doc);
			System.out.println("id：" + document.get("id"));
			System.out.println("title：" + document.get("title"));
			System.out.println("content：" + document.get("content"));
			System.out.println("author：" + document.get("author"));
		}
	}
	
	/**
	 * 
	 * @method 分词打印
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @param analyzer
	 * @param text
	 * @throws IOException
	 */
	public void printAnalyzerDoc(Analyzer analyzer,String text) throws Exception {
		TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		try {
			tokenStream.reset();
			while (tokenStream.incrementToken()) {
				System.out.println(charTermAttribute.toString());
			}
			tokenStream.end();
		} finally {
			tokenStream.close();
			analyzer.close();
		}
	}
	
	/**
	 * 
	 * @method 多条件查询[BooleanQuery也是实际开发过程中经常使用的一种Query。 它其实是一个组合的Query，在使用时可以把各种Query对象添加进去并标明它们之间的逻辑关系。
	 * BooleanQuery本身来讲是一个布尔子句的容器，它提供了专门的API方法往其中添加子句，标明它们之间的关系，以下代码为BooleanQuery提供的用于添加子句的API接口：]
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @throws Exception
	 */
	public void BooleanQuery() throws Exception {
		String searchField1 = "title";
		String searchField2 = "content";
		Query query1 = new TermQuery(new Term(searchField1, "标题"));
		Query query2 = new TermQuery(new Term(searchField2, "内容"));
		BooleanQuery.Builder builder = new BooleanQuery.Builder();
		// BooleanClause用于表示布尔查询子句关系的类，
		// 包 括：
		// BooleanClause.Occur.MUST，
		// BooleanClause.Occur.MUST_NOT，
		// BooleanClause.Occur.SHOULD。
		// 必须包含,不能包含,可以包含三种.有以下6种组合：
		// 1．MUST和MUST：取得连个查询子句的交集。
		// 2．MUST和MUST_NOT：表示查询结果中不能包含MUST_NOT所对应得查询子句的检索结果。
		// 3．SHOULD与MUST_NOT：连用时，功能同MUST和MUST_NOT。
		// 4．SHOULD与MUST连用时，结果为MUST子句的检索结果,但是SHOULD可影响排序。
		// 5．SHOULD与SHOULD：表示“或”关系，最终检索结果为所有检索子句的并集。
		// 6．MUST_NOT和MUST_NOT：无意义，检索无结果。
		builder.add(query1, BooleanClause.Occur.SHOULD);
		builder.add(query2, BooleanClause.Occur.SHOULD);
		BooleanQuery query = builder.build();

		//执行查询，并打印查询到的记录数
		executeQuery(query);
	}

	/**
	 * 
	 * @method 匹配前缀[ PrefixQuery用于匹配其索引开始以指定的字符串的文档。就是文档中存在xxx%]
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @throws IOException
	 */
	public void prefixQueryTest() throws Exception {
		String searchField = "title";
		Term term = new Term(searchField, "Lucen"); //可以匹配到Lucen%格式
		Query query = new PrefixQuery(term);
		//执行查询，并打印查询到的记录数
		executeQuery(query);
	}
	
	/**
	 * 
	 * @method 短语搜索 
	 * [所谓PhraseQuery，就是通过短语来检索，比如我想查“big car”这个短语，那么如果待匹配的document的指定项里包含了"big car"这个短语，
	 * 这个document就算匹配成功。可如果待匹配的句子里包含的是“big black car”，那么就无法匹配成功了，如果也想让这个匹配，就需要设定slop，
	 * 先给出slop的概念：slop是指两个项的位置之间允许的最大间隔距离
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @throws Exception
	 */
	public void phraseQueryTest() throws Exception {

		String searchField = "content";
		String query1 = "apache";
		String query2 = "spark";
		PhraseQuery.Builder builder = new PhraseQuery.Builder();
		builder.add(new Term(searchField, query1));
		builder.add(new Term(searchField, query2));
		builder.setSlop(0);
		PhraseQuery phraseQuery = builder.build();
		//执行查询，并打印查询到的记录数
		executeQuery(phraseQuery);
	}
	
	/**
	 * 
	 * @method 相近词语搜索
	 * [FuzzyQuery是一种模糊查询，它可以简单地识别两个相近的词语。]
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @throws Exception
	 */
	public void fuzzyQueryTest() throws Exception {
		String searchField = "content";
		Term t = new Term(searchField, "大规模");
		Query query = new FuzzyQuery(t);
		//执行查询，并打印查询到的记录数
		executeQuery(query);
	}
	
	/**
	 * 
	 * @method 通配符搜索
	 * [ Lucene也提供了通配符的查询，这就是WildcardQuery。通配符“?”代表1个字符，而“*”则代表0至多个字符。]
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @throws Exception
	 */
	public void wildcardQueryTest() throws Exception {
		String searchField = "content";
		Term term = new Term(searchField, "大*规模");
		Query query = new WildcardQuery(term);
		//执行查询，并打印查询到的记录数
		executeQuery(query);
	}

	/**
	 * 
	 * @method 分词查询
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @throws Exception
	 */
	public void queryParser() throws Exception {
		Analyzer analyzer =LuceneUtils.getAnalyzer();
		String searchField = "content";
		//指定搜索字段和分析器
		QueryParser parser = new QueryParser(searchField, analyzer);
		//用户输入内容
		Query query = parser.parse("计算引擎");
		//执行查询，并打印查询到的记录数
		executeQuery(query);
	}
	
	/**
	 * 
	 * @method 多个 Field 分词查询
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @throws Exception
	 */
	public void multiFieldQueryParser() throws Exception  {
		Analyzer analyzer =LuceneUtils.getAnalyzer();
		String[] filedStr = new String[]{"title", "content"};
		//指定搜索字段和分析器
		QueryParser queryParser = new MultiFieldQueryParser(filedStr, analyzer);
		//用户输入内容
		Query query = queryParser.parse("Spark");
		//执行查询，并打印查询到的记录数
		executeQuery(query);
	}
	
	/**
	 * 
	 * @method 中文分词器[选择不同analyzer分词器会有不同结果]
	 * @author Mr yi
	 * @time 2019年5月24日
	 * @throws Exception
	 */
	public void AnalyzerTest() throws Exception {
		Analyzer analyzer =LuceneUtils.getAnalyzer();
		String text = "Apache Spark 是专为大规模数据处理而设计的快速通用的计算引擎";
		printAnalyzerDoc(analyzer, text);
		 
	}
	
   
}
