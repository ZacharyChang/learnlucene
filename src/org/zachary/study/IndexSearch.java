package org.zachary.study;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by ZacharyChang.
 */
public class IndexSearch {
    public static void main(String[] args) {
        try {
            Directory directory;
            directory = FSDirectory.open(Paths.get("C://Dev/test"));
            DirectoryReader dReader = DirectoryReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(dReader);
            Analyzer analyzer = new StandardAnalyzer();
            QueryParser qParser = new QueryParser("content", analyzer);
            Query query = qParser.parse("Hello");
            TopDocs tDocs = searcher.search(query, 10);
            if (tDocs != null) {
                System.out.println("count: " + tDocs.totalHits + "\n");
                for (int i = 0; i < tDocs.scoreDocs.length; i++) {
                    Document doc = searcher.doc(tDocs.scoreDocs[i].doc);
                    System.out.println("id: " + doc.get("id"));
                    System.out.println("content: " + doc.get("content"));
                    System.out.println("num: " + doc.get("num") + "\n");
                }
            }
            directory.close();
            dReader.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
