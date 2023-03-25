//package com.dao.search;
//
////imports
//import com.dao.importdata.DSqlImportData;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//
//import org.apache.lucene.index.CorruptIndexException;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.LockObtainFailedException;
//import org.apache.lucene.store.RAMDirectory;
//import org.apache.lucene.queryParser.ParseException;
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.demo.FileDocument;
//import org.apache.lucene.demo.IndexFiles;
//import org.apache.lucene.document.DateTools;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.queryParser.QueryParser;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.TopScoreDocCollector;
//import org.apache.lucene.store.FSDirectory;
//import org.apache.lucene.util.Version;
//
//
////function
//public class luceneIndex extends DSqlImportData{
//public void getLuceneSearch() throws CorruptIndexException, LockObtainFailedException, IOException, ParseException {
//        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
//
//        // Store the index in memory:
////        Directory directory = new RAMDirectory();
//
//        // To store an index on disk, use this instead (note that the
//        // parameter true will overwrite the index in that directory
//        // if one exists):
//         Directory directory =new RAMDirectory();
//         FSDirectory.open(new File("D:\\hungnn\\project\\viets\\vofficeV2\\vofficeV2\\public_html\\luceneindex"));
//
//        IndexWriter iwriter = new IndexWriter(directory, analyzer, true, new IndexWriter.MaxFieldLength(25000));
//
////        Document doc = new Document();
////        String text = "This is the st. text to be indexed1.";
////        doc.add(new Field("contents", text, Field.Store.YES, Field.Index.ANALYZED));
////        iwriter.addDocument(doc);
////        doc = new Document();
////        text = "hung dang thu text to be indexed2.";
////        doc.add(new Field("contents", text, Field.Store.YES, Field.Index.ANALYZED));
//        
//        
////        iwriter.addDocument(doc);
//        File file=new File("3DACAD.doc");
//        indexDocs(iwriter,file);
//
//        iwriter.close();
//
//        // Now search the index:
//        IndexSearcher isearcher = new IndexSearcher(directory);
//
//        // Parse a simple query that searches for "text":
//        QueryParser parser = new QueryParser(Version.LUCENE_CURRENT, "path", analyzer);
//        Query query = parser.parse(".doc");
//
//        TopScoreDocCollector collector = TopScoreDocCollector.create(10, true);
//        isearcher.search(query, collector);
//
//        //.println("collector.getTotalHits()=" + collector.getTotalHits());
////        assertEquals(2, collector.getTotalHits());
//
//        // Iterate through the results:
//        ScoreDoc[] hits = collector.topDocs().scoreDocs;
//        for (int i = 0; i < hits.length; i++) {
//            Document hitDoc = isearcher.doc(hits[i].doc);
//            String path = hitDoc.get("path");
//            if (path != null) {
//              //.println((i+1) + ". " + path);
//              String title = hitDoc.get("title");
//              if (title != null) {
//                //.println("   Title: " + hitDoc.get("title"));
//              }
//            } else {
//              //.println((i+1) + ". " + "No path for this document");
//            }
//
//            //.println("hitDoc.get(\"path\")=" + hitDoc.get("path"));
//        }
//
//        isearcher.close();
//        directory.close();
//    }
//    
//     static void indexDocs(IndexWriter writer, File file)
//       throws IOException {
//       // do not try to index files that cannot be read
//       if (file.canRead()) {
//         if (file.isDirectory()) {
//           String[] files = file.list();
//           // an IO error could occur
//           if (files != null) {
//             for (int i = 0; i < files.length; i++) {
//               indexDocs(writer, new File(file, files[i]));
//             }
//           }
//         } else {
//           //.println("adding " + file);
//           try {
//             writer.addDocument(Document(file));
//           }
//           // at least on windows, some temporary files raise this exception with an "access denied" message
//           // checking if the file can be read doesn't help
//           catch (FileNotFoundException fnfe) {
//             ;
//           }
//         }
//       }
//     }
//     public static Document Document(File f)
//          throws java.io.FileNotFoundException {
//            
//       // make a new, empty document
//       Document doc = new Document();
//
//       // Add the path of the file as a field named "path".  Use a field that is 
//       // indexed (i.e. searchable), but don't tokenize the field into words.
//       doc.add(new Field("path", f.getPath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
//
//       // Add the last modified date of the file a field named "modified".  Use 
//       // a field that is indexed (i.e. searchable), but don't tokenize the field
//       // into words.
//       doc.add(new Field("modified",
//           DateTools.timeToString(f.lastModified(), DateTools.Resolution.MINUTE),
//           Field.Store.YES, Field.Index.NOT_ANALYZED));
//
//       // Add the contents of the file to a field named "contents".  Specify a Reader,
//       // so that the text of the file is tokenized and indexed, but not stored.
//       // Note that FileReader expects the file to be in the system's default encoding.
//       // If that's not the case searching for special characters will fail.
//       doc.add(new Field("contents", new FileReader(f)));
//
//       // return the document
//       return doc;
//     }
// }