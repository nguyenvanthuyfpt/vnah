<% request.setAttribute("SESSION.DENY.GUEST","FALSE");%>
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
    long secureId = 0; String sessionId = "";
    long userId = 0;String name = "";
try{       
        String value = "";
        items = upload.parseRequest(request); 
        for(int i=0;i<items.size();i++){        
            FileItem item = (FileItem) items.get(i);
            name = item.getFieldName();
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
	}
 }catch (Exception ex ) {
 
 }finally {
        if(sessionId!=null && secureId>0 && Users.haveSessionID(sessionId)){
              com.users.OnlineUser user = Users.getUser(sessionId);
              if(user.getExtTagLong()==secureId){
                List names = new ArrayList();
                for(int i=0;i<items.size();i++){        
                    FileItem item = (FileItem) items.get(i);
                    name = item.getFieldName();
                    if (!item.isFormField() && item.getSize()>0 && name!=null && !name.equals("")){                  
                    try  {
                        File file = new File(com.lib.AppConfigs.APP_SYSTEM_PATH + com.lib.AppConfigs.DOC_FILE_REVIEW_DRAFT_PATH + user.getId() + "." + name);
                        item.write(file);
                        item.delete();
                        names.add(name);
                    } catch (Exception ex)  {

                    } finally  {
                    }
                    
                    }
                }
               items = null;
                if(names.size()>0){
                    if(user.getExtInformation()!=null){
                        items = (List)user.getExtInformation();
                        for(int i=0;i<names.size();i++){
                            items.add(names.get(i));
                        }
                    }else{                    
                        items = names;
                    }
                    user.setExtInformation(items);
                    Users.saveUser(user);
                }
               items = null;
               names = null;
            }
 }
}
}
%>
