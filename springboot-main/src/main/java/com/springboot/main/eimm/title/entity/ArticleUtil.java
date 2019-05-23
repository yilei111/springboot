package com.springboot.main.eimm.title.entity;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexableField;

public class ArticleUtil {
    public static Document articleToDocument(Articlel article){
        Document document = new Document();
        IndexableField idField = new StringField("id",article.getId(),Store.YES);
        IndexableField titleField = new StringField("title",article.getTitle(),Store.YES);
        IndexableField authorField = new StringField("author",article.getAuthor(),Store.YES);
        IndexableField  contentField = new TextField("content",article.getContent(),Store.YES);
        IndexableField urlField = new StringField("url",article.getUrl(),Store.YES);
        IndexableField dateField = new StringField("date",article.getDate(),Store.YES);

        document.add(idField);
        document.add(titleField);
        document.add(contentField);
        document.add(authorField);
        document.add(urlField);
        document.add(dateField);
        return document;
    }
}
