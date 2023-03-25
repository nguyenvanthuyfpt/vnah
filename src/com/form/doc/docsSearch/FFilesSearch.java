package com.form.doc.docsSearch;


import com.form.FSeed;

public class FFilesSearch extends FSeed
{
    private int idFiles;
    private int docId;
    private String file;
    private String fileName;
    private String path;
    private int tyleDoc;
    
 
  public void reset()
  {
        this.setIdFiles(0);
        this.setDocId(0);
        this.setFileName("");
        this.setFile("");
  }

  

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

 

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getIdFiles() {
        return idFiles;
    }

    public void setIdFiles(int idFiles) {
        this.idFiles = idFiles;
    }

    public int getTyleDoc() {
        return tyleDoc;
    }

    public void setTyleDoc(int tyleDoc) {
        this.tyleDoc = tyleDoc;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
