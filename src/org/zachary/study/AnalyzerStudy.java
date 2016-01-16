package org.zachary.study;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by ZacharyChang.
 */
public class AnalyzerStudy {
    private static String str = "lucene分析器使用分词器和过滤器构成一个“管道”，文本在流经这个管道后成为可以进入索引的最小单位，因此，一个标准的分析器有两个部分组成，一个是分词器tokenizer,它用于将文本按照规则切分为一个个可以进入索引的最小单位。另外一个是TokenFilter，它主要作用是对切出来的词进行进一步的处理（如去掉敏感词、英文大小写转换、单复数处理）等。lucene中的Tokenstram方法首先创建一个tokenizer对象处理Reader对象中的流式文本，然后利用TokenFilter对输出流进行过滤处理";

    public static void print(Analyzer analyzer) {
        StringReader sReader = new StringReader(str);
        try {
            TokenStream tStream = analyzer.tokenStream("", sReader);
            tStream.reset();
            CharTermAttribute term = tStream.getAttribute(CharTermAttribute.class);
            System.out.println("\n分词：" + analyzer.getClass());
            while (tStream.incrementToken()) {
                System.out.print(term.toString() + "|");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analyzer analyzer = new StandardAnalyzer();
        AnalyzerStudy.print(analyzer);
        analyzer = new SmartChineseAnalyzer();
        AnalyzerStudy.print(analyzer);
        analyzer = new WhitespaceAnalyzer();
        AnalyzerStudy.print(analyzer);
        analyzer = new SimpleAnalyzer();
        AnalyzerStudy.print(analyzer);
    }
}
