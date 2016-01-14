package org.zachary.study;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by ZacharyChang.
 */
public class IndexCreate {
    public static void main(String[] args) {
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        Directory directory = null;
        IndexWriter indexWriter = null;

        try {
            directory = FSDirectory.open(Paths.get("C://Dev/test"));
// function unlock() not available.
//            if(indexWriter.isLocked(directory)){
//                indexWriter.unlock()
//            }
            indexWriter = new IndexWriter(directory, indexWriterConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Document doc1 = new Document();
        doc1.add(new StringField("id", "123", Field.Store.YES));
        doc1.add(new TextField("content", "Hello,World!", Field.Store.YES));
        doc1.add(new IntField("num", 111111, Field.Store.YES));
        try {
            indexWriter.addDocument(doc1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Document doc2 = new Document();
        doc2.add(new StringField("id", "456", Field.Store.YES));
        doc2.add(new TextField("content", "Hello,Zachary!", Field.Store.YES));
        doc2.add(new IntField("num", 222222, Field.Store.YES));
        try {
            indexWriter.addDocument(doc2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            indexWriter.commit();
            indexWriter.close();
            directory.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
