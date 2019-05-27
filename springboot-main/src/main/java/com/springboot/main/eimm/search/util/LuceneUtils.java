package com.springboot.main.eimm.search.util;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 
 * @method Lucene索引操作对象工具类
 * @author Mr yi
 * @time 2019年5月23日
 */
public class LuceneUtils {

    private static Directory directory  = null;

    private static IndexWriterConfig indexWriterConfig = null; 

    private static Analyzer analyzer = null;
    
    private static Version matchVersion = null;
    
    /**
	 * 存储indexWriter的map
	 */
	private static Map<Directory, IndexWriter> writerMap = new ConcurrentHashMap<>();
    
    //static 代码块随着类的加载，只加载一次。作用是初始化类。
    static{
        try {
        	//在 6.6 以上版本中 version 不再是必要的，并且，存在无参构造方法，可以直接使用默认的 StandardAnalyzer 分词器。
            matchVersion = Version.LUCENE_8_1_0;
            //索引存放的位置，设置在当前目录中（项目根路径下）
            final String INDEXURL = "./index_dir/search";
            directory = FSDirectory.open(new File(INDEXURL).toPath());
            //analyzer = new StandardAnalyzer(); // 标准分词器，适用于英文[支持中文采用的方法为单字切分。他会将词汇单元转换成小写形式，并去除停用词和标点符号]
            //analyzer = new SmartChineseAnalyzer();//中文分词
            //analyzer = new ComplexAnalyzer();//中文分词
        	//analyzer = new IKAnalyzer();//中文分词
             analyzer = new IKAnalyzer();//中文分词
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Directory getDirectory() {
        return directory;
    }

    /**
     * 
     * @method 返回用于操作索引的对象
     * @author Mr yi
     * @time 2019年5月23日
     * @return
     * @throws Exception
     */
    public static IndexWriter getIndexWriter() throws Exception{
    	// 如果map中有则直接返回
		if (writerMap.containsKey(directory)) {
			IndexWriter indexWriter = writerMap.get(directory);
			if (indexWriter.isOpen()) {
				return indexWriter;
			}
		}
 
    	
    	//创建索引写入配置
        indexWriterConfig = new IndexWriterConfig(analyzer);
        //创建索引写入对象
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
     // 没有则new一个加入map
     	writerMap.put(directory, indexWriter);
        return indexWriter;
    }

    /**
     * 
     * @method 返回用于读取索引的对象
     * @author Mr yi
     * @time 2019年5月23日
     * @return
     * @throws Exception
     */
    public static IndexSearcher getIndexSearcher() throws Exception{
        IndexReader indexReader  = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        return indexSearcher;
    }

    /**
     * 
     * @method 返回当前版本
     * @author Mr yi
     * @time 2019年5月23日
     * @return
     */
    public static Version getMatchVersion() {
        return matchVersion;
    }

    /**
     * 
     * @method 返回当前使用的分词器
     * @author Mr yi
     * @time 2019年5月23日
     * @return
     */
    public static Analyzer getAnalyzer() {
        return analyzer;
    }
    
    
  
 
}
