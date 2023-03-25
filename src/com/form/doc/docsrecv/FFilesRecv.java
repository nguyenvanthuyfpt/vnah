package com.form.doc.docsrecv;

import com.form.FSeed;

public class FFilesRecv extends FSeed
{
    private int idFiles;
    private int docId;
    private String file;
    private String fileName;
    private String path;
    private int blockFile;
    
 
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getBlockFile() {
        return blockFile;
    }

    public void setBlockFile(int blockFile) {
        this.blockFile = blockFile;
    }
}
