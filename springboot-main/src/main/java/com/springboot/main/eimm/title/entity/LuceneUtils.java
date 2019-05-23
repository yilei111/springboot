package com.springboot.main.eimm.title.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

//在Lucene对索引的操作需要获取其相应的对象，这里我将其封装到一个工具类中
public class LuceneUtils {

    private static Directory directory  = null;

    private static IndexWriterConfig indexWriterConfig = null; 

    private static Analyzer analyzer = null;
    private static Version matchVersion = null;
    static{
        try {

            matchVersion = Version.LUCENE_8_1_0;
            
             //Contants.INDEXURL 索引保存的位置
             final String INDEXURL = "./index_dir/news";
             
            directory = FSDirectory.open(new File(INDEXURL).toPath());
            //标准分词
            analyzer = new StandardAnalyzer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Directory getDirectory() {
        return directory;
    }
    /**
     * 返回用于操作索引的对象
     * @return
     * @throws Exception
     */
    public static IndexWriter getIndexWriter() throws Exception{
        indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        return indexWriter;
    }
    /**
     * 返回用于读取索引的对象
     * @return
     * @throws Exception
     */
    public static IndexSearcher getIndexSearcher() throws Exception{
        IndexReader indexReader  = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        return indexSearcher;
    }
    /**
     * 返回当前版本
     * @return
     */
    public static Version getMatchVersion() {
        return matchVersion;
    }
    /**
     * 返回当前使用的分词器
     * @return
     */
    public static Analyzer getAnalyzer() {
        return analyzer;
    }

    
    
   

}
