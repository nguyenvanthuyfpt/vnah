<%request.setAttribute("SESSION.DENY.GUEST","FALSE");%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%  
    boolean isMultipart = FileUpload.isMultipartContent(request);
    if(isMultipart){
    DiskFileUpload upload = new DiskFileUpload();     
    List items = null;
    int fid = -1;
    long secureId = 0; String sessionId = "";    
    long userId = 0;
try{       
        String name = "";String value = "";
        items = upload.parseRequest(request); 
        for(int i=0;i<items.size();i++){        
            FileItem item = (FileItem) items.get(i);
            name = item.getFieldName();//Ten truong SubID, FileCount
            if (item.isFormField()){                  
                value = item.getString(); //gia tri cua truong   
                if(name.equals("secureId")){
                    try  {
                        secureId = Long.parseLong(value);
                    } catch (Exception ex)  {
                        secureId = 0;
                    } finally  {
                    }
                }
                if(name.equals("sessionId")){
                    sessionId = value;
                }
            }
            else if(name.equals("vOfficeDraft")){
                if (item.getSize()>0)  fid = i;
	    }
	}
 }catch (Exception ex ) {
 }finally {
        if(fid>=0 && sessionId!=null && secureId>0 && Users.haveSessionID(sessionId)){
              com.users.OnlineUser user = Users.getUser(sessionId);
              if(user.getExtTagLong()==secureId){                 
                File file = new File(com.lib.AppConfigs.APP_SYSTEM_PATH + com.lib.AppConfigs.DOC_FILE_REVIEW_DRAFT_PATH + user.getId());
                FileItem item = (FileItem) items.get(fid);
                item.write(file);
                item.delete();
                
               com.form.doc.docssend.FDocssend beanSend = new com.form.doc.docssend.FDocssend();  
               String draftPath = com.lib.AppConfigs.APP_SYSTEM_PATH + com.lib.AppConfigs.DOC_FILE_REVIEW_DRAFT_PATH + user.getId();    
               String pathFoder = beanSend.dateToString(beanSend.getCurrentSqlDate(),com.lib.AppConfigs.DOC_FOLDER_UPLOAD);
               String dirs = com.lib.AppConfigs.APP_SYSTEM_PATH + com.lib.AppConfigs.DOC_FOLDER_ROOT+  com.lib.AppConfigs.SYSTEM_FILE_SCHIP + pathFoder;
               (new File(dirs)).mkdirs(); 
               String fileReName = user.getId() + "." + System.currentTimeMillis() ; 
               File fileSrc = new File(draftPath);                                                                     
               new com.action.doc.assign.CopyFile().copyFile(draftPath,dirs + fileReName );   
               beanSend.setDocId(user.getExtTagInt());
               beanSend.setFileName((String)user.getExtTagString());
               beanSend.setReName(fileReName);
               beanSend.setPathFile(pathFoder);        
               beanSend.setUserId((int)user.getId());
               beanSend.setFileId((int)(float)user.getExtTagFloat());
               beanSend.setIndex(1);
               new com.bo.doc.assign.BAssignSend().AddFileOnline(beanSend);               
               if(fileSrc.exists()) fileSrc.delete(); 
               
            }
        }
 }
}

%>
