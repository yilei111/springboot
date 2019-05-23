package com.springboot.main.eimm.title.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.Highlighter;
public class LuceneDao {
    /**
     * 添加索引
     * @param article
     * @throws Exception 
     */
    public void addIndex(Articlel article) throws Exception{
        //获取indexWrite对象
        IndexWriter indexWriter =  LuceneUtils.getIndexWriter();
        /*
        Lucene操作的都是Document对象所以这里需要将javaBean对象转化为Document对象
        */
        Document document = ArticleUtil.articleToDocument(article);
        //将document写入磁盘中
        indexWriter.addDocument(document);
        indexWriter.close();
    }
    
    //搜索索引（不高亮)
    public List<Articlel> findIndex1(String keywords) throws Exception {
        List<Articlel> articles = new ArrayList<Articlel>();
        //Lucene的搜索方法
        IndexSearcher indexSearcher = LuceneUtils.getIndexSearcher();
        //所要搜索的位置
        /**
         * 在Lucene中索引的保存都是以键值对的形式保存，所以这里需要指定所要查询的域
         */
        String fields[] = {"title", "content", "author"};
        //在Lucene中查询的方式还有很多，这里使用简单的MultiPhraseQuery查询
        MultiFieldQueryParser queryParser = new MultiFieldQueryParser(fields, LuceneUtils.getAnalyzer());
        Query query = queryParser.parse(keywords);
        TopDocs topDocs = indexSearcher.search(query,500);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (int i = 0; i < scoreDocs.length; i++) {
            //封装查询到的结果
            Articlel article = new Articlel();
            int doc = scoreDocs[i].doc;
            Document document = indexSearcher.doc(doc);
            article.setId(document.get("id"));
            article.setTitle(document.get("title"));
            article.setContent(document.get("content"));
            article.setUrl(document.get("url"));
            article.setAuthor(document.get("author"));
            article.setDate(document.get("date"));
            articles.add(article);

        }
        return articles;
    }
  //搜索索引（高亮)
    public List<Articlel> findIndex(String keywords) throws Exception {
        List<Articlel> articles = new ArrayList<Articlel>();
        //Lucene的搜索方法
        IndexSearcher indexSearcher = LuceneUtils.getIndexSearcher();

        String fields[] = {"title", "content", "author"};
        //在Lucene中查询的方式还有很多，这里使用简单的MultiPhraseQuery查询
        MultiFieldQueryParser queryParser = new MultiFieldQueryParser(fields, LuceneUtils.getAnalyzer());
        Query query = queryParser.parse(keywords);
        TopDocs topDocs = indexSearcher.search(query,500);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;


        for (int i = 0; i < scoreDocs.length; i++) {
            Articlel article = new Articlel();
            int doc = scoreDocs[i].doc;
            Document document = indexSearcher.doc(doc);
            article.setId(document.get("id"));
            //高亮处理
            String title = this.Highlighter(query, "title", document.get("title"));
            if (title != null) {
                article.setTitle(title);
            } else {
                article.setTitle(document.get("title"));
            }
            String content = this.Highlighter(query, "content", document.get("content"));
            if (title != null) {
                article.setContent(content);
            } else {
                article.setContent(document.get("content"));
            }
            article.setUrl(document.get("url"));
            article.setAuthor(document.get("author"));
            article.setDate(document.get("date"));
            articles.add(article);

        }
        return articles;
    }
 
    
    //高亮显示的方法，这里的高亮，其实就是给查询的结果添加一个html标签，修改相应的样式
 private String Highlighter(Query query, String field, String value) throws Exception {
    QueryScorer queryScorer = new QueryScorer(query);
    //所要添加的样式
    Formatter formatter = new SimpleHTMLFormatter("<span style='color:red;'>", "</span>");
    //设置高亮分词器
    Highlighter highlighter = new  Highlighter(formatter, queryScorer);
    highlighter.setTextFragmenter(new SimpleFragmenter(100));
    String text = highlighter.getBestFragment(LuceneUtils.getAnalyzer(), field, value);
    return text;
}

}
