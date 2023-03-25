//package com.dao.cabin;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import java.io.*;
//import org.apache.lucene.analysis.*;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.*;
//import org.apache.lucene.index.*;
//import org.apache.lucene.store.*;
//import org.apache.lucene.search.*;
//import org.apache.lucene.queryParser.*;
//import java.net.URLEncoder;
//import org.apache.lucene.util.Version;
//
//import java.util.Date;
//import java.util.List;
//
//public class DSearchCabin  extends DSqlCabin
//{
////    static final File INDEX_DIR = new File("D:\\projects\\voffice\\vofficeV2\\public_html\\index\\cabin");
//
//    public static void CreateIndex(File files,String namefile) {
//    File INDEX_DIR=files;
//    String[] args=new String[]{namefile};
//    final File docDir = new File(args[0]);
//    String usage = "java org.apache.lucene.demo.IndexFiles <root_directory>";
//    if (args.length == 0) {
//      System.err.println("Usage: " + usage);
//      System.exit(1);
//    }
//
//    if (INDEX_DIR.exists()) {
//        
//      //.println("Cannot save index to '" +INDEX_DIR+ "' directory, please delete it first");
//      System.exit(1);
//    }
//    if (!docDir.exists() || !docDir.canRead()) {
//      //.println("Document directory '" +docDir.getAbsolutePath()+ "' does not exist or is not readable, please check the path");
//      System.exit(1);
//    }
//    
//    Date start = new Date();
//    try {
//      IndexWriter writer = new IndexWriter(FSDirectory.open(INDEX_DIR), new StandardAnalyzer(Version.LUCENE_CURRENT), true, IndexWriter.MaxFieldLength.LIMITED);
//      //.println("Indexing to directory '" +INDEX_DIR+ "'...");
//      indexDocs(writer, docDir);
//      //.println("Optimizing...");
//      writer.optimize();
//      writer.close();
//
//      Date end = new Date();
//      //.println(end.getTime() - start.getTime() + " total milliseconds");
//
//    } catch (IOException e) {
//      //.println(" caught a " + e.getClass() +
//       "\n with message: " + e.getMessage());
//    }
//}
//    static void indexDocs(IndexWriter writer, File file)
//      throws IOException {
//      // do not try to index files that cannot be read
//      if (file.canRead()) {
//        if (file.isDirectory()) {
//          String[] files = file.list();
//          // an IO error could occur
//          if (files != null) {
//            for (int i = 0; i < files.length; i++) {
//              indexDocs(writer, new File(file, files[i]));
//            }
//          }
//        } else {
//          //.println("adding " + file);
//          try {
//            writer.addDocument(Document(file));
//          }
//          // at least on windows, some temporary files raise this exception with an "access denied" message
//          // checking if the file can be read doesn't help
//          catch (FileNotFoundException fnfe) {
//            ;
//          }
//        }
//      }
//    }
//    public static Document Document(File f)
//         throws java.io.FileNotFoundException {
//           
//      // make a new, empty document
//      Document doc = new Document();
//
//      // Add the path of the file as a field named "path".  Use a field that is 
//      // indexed (i.e. searchable), but don't tokenize the field into words.
//      doc.add(new Field("path", f.getPath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
//      doc.add(new Field("title", f.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
//
//      // Add the last modified date of the file a field named "modified".  Use 
//      // a field that is indexed (i.e. searchable), but don't tokenize the field
//      // into words.
//      doc.add(new Field("modified",
//          DateTools.timeToString(f.lastModified(), DateTools.Resolution.MINUTE),
//          Field.Store.YES, Field.Index.NOT_ANALYZED));
//
//      // Add the contents of the file to a field named "contents".  Specify a Reader,
//      // so that the text of the file is tokenized and indexed, but not stored.
//      // Note that FileReader expects the file to be in the system's default encoding.
//      // If that's not the case searching for special characters will fail.
//      doc.add(new Field("contents", new FileReader(f)));
//
//      // return the document
//      return doc;
//    }
//
//public static String SearchIndex(String indexName,String contens) throws IOException {
//    String result ="";
//    boolean error = false;                  //used to control flow for error messages
////     String indexName = "D:\\projects\\voffice\\vofficeV2\\index";       //local copy of the configuration variable
//     IndexSearcher searcher = null;          //the searcher used to open/search the index
//     Query query = null;                     //the Query created by the QueryParser
//     TopDocs hits = null;                       //the search results
//     int startindex = 0;                     //the first index displayed on this page
//     int maxpage    = 50;                    //the maximum items displayed on this page
//     String queryString = null;              //the query entered in the previous page
//     String startVal    = null;              //string version of startindex
//     String maxresults  = null;              //string version of maxpage
//     int thispage = 0;                       //used for the for/next either maxpage or
//            try {
//              IndexReader reader = IndexReader.open(FSDirectory.open(new File(indexName)), true); // only searching, so read-only=true
//               searcher = new IndexSearcher(reader);
//                                                            
//            } catch (Exception e) {  
//                error = true; 
//            }
//    if (error == false) {                                           //did we open the index?
//              queryString = contens;           //get the search criteria
//              startVal    = "0";         //get the start index
//              maxresults  = "100";      //get max results per page
//              try {
//                      maxpage    = Integer.parseInt(maxresults);    //parse the max results first
//                      startindex = Integer.parseInt(startVal);      //then the start index  
//              } catch (Exception e) { } //we don't care if something happens we'll just start at 0
//              Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);           //construct our usual analyzer
//              try {
//                       QueryParser qp = new QueryParser(Version.LUCENE_CURRENT, "contents", analyzer);
//                      query = qp.parse(queryString); //parse the 
//              } catch (ParseException e) {                          //query and construct the Query
//              //.println(e.getMessage());
//                      error = true;                                 //don't bother with the rest of
//              }
//      }
//      
//                       TopScoreDocCollector collector = TopScoreDocCollector.create(10, true);
//                       searcher.search(query, collector);
//
//                       //.println("collector.getTotalHits()=" + collector.getTotalHits());
//                       //        assertEquals(2, collector.getTotalHits());
//
//                       // Iterate through the results:
//                       ScoreDoc[] hits1 = collector.topDocs().scoreDocs;
//                       for (int i = 0; i < hits1.length; i++) {
//                           Document hitDoc = searcher.doc(hits1[i].doc);
//                           String path = hitDoc.get("path");
//                           if (path != null) {
//                             //.println((i+1) + ". " + path);
//                             String title = hitDoc.get("title");
//                             if (title != null) {
//                               //.println("   contents: " + hitDoc.get("contents"));
//                             }
//                           } else {
//                             //.println((i+1) + ". " + "No path for this document");
//                           }
//                           result+=","+hitDoc.get("title");
////                           error = true; 
//                       }
//        if (!result.equals("")) {   
//            result=result.substring(1);
//        }                
//
//
////      if (error == false && searcher != null) {                     // if we've had no errors
////              thispage = maxpage;                                   // default last element to maxpage
////              hits = searcher.search(query, maxpage);                        // run the query 
////              if (hits.totalHits == 0) {                             // if we got no results tell the user
////              error = true;                                        // don't bother with the rest of the
////              }
////      }
////      if (error == false && searcher != null) {                   
////              if ((startindex + maxpage) > hits.totalHits) {
////                      thispage = hits.totalHits - startindex;      // set the max index to maxpage or last
////              }                                                   // actual search result whichever is less
////              for (int i = startindex; i < (thispage + startindex); i++) {  // for each element
////                      Document doc = searcher.doc(hits.scoreDocs[i].doc);                    //get the next document 
////                      String doctitle = doc.get("contents");            //get its title
////                      //.println(doctitle);
////                      String url = doc.get("path");                  //get its path field
////                       result+=","+doc.get("path");
//////                      if (url != null && url.startsWith("../webapps/")) { // strip off ../webapps prefix if present
//////                              url = url.substring(10);
//////                      }
////                      if ((doctitle == null) || doctitle.equals("")) //use the path if it has no title
////                              doctitle = url;
////                                                                     //then output!   
////                }
////        }
// if (searcher != null)
// searcher.close();
//        return result;
//    }
//    static public boolean deleteDirectory(File path) {
//       if( path.exists() ) {
//         File[] files = path.listFiles();
//         for(int i=0; i<files.length; i++) {
//            if(files[i].isDirectory()) {
//              deleteDirectory(files[i]);
//            }
//            else {
//              files[i].delete();
//            }
//         }
//       }
//       return( path.delete() );
//     }
//
//}