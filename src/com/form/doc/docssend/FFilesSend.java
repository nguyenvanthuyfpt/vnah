package com.form.doc.docssend;

import com.form.FSeed;

public class FFilesSend extends FSeed
{
    private String[] fileText;
    private String description;
    private int numberReadedFile;
    private int idFiles;
    private int parent_id;
    private int docId;
    private int blockFile;
    private long userId;
    private int flag;
    private int version;
    private String file;
    private String fileName;
    private String views;
    private String userName;
    private String createTimeName;
    private String path;
 
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTimeName() {
        return createTimeName;
    }

    public void setCreateTimeName(String createTimeName) {
        this.createTimeName = createTimeName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String[] getFileText() {
        return fileText;
    }

    public void setFileText(String[] fileText) {
        this.fileText = fileText;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberReadedFile() {
        return numberReadedFile;
    }

    public void setNumberReadedFile(int numberReadedFile) {
        this.numberReadedFile = numberReadedFile;
    }

    public int getBlockFile() {
        return blockFile;
    }

    public void setBlockFile(int blockFile) {
        this.blockFile = blockFile;
    }
}
