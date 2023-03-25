<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<script language=javascript>
     var fileCount= 1;
     function checkAddFile(obj){
        return obj.value && (obj.name=='file[' + (fileCount-1) + ']');
     }
     function addFile() {
      var input = '<input type=file size=30 name=file[' + fileCount  + '] onchange="javascript:if(checkAddFile(this)) addFile();"><\/div><div id="_' + (fileCount +1) + '">'  ; 
      document.getElementById('_' + fileCount).innerHTML = input;
      fileCount++;
    }
</script>
<div id='_0'><input type=file name='file[0]' size="30" onchange="if(checkAddFile(this)) addFile();"></div><div id='_1'></div>
 
<logic:notEmpty name="docsrecv" property="allFiles">
        <ol style="padding-left:30px;margin:3px 3px 3px 3px;">
        <logic:iterate name="docsrecv" property="allFiles" id="beanFiles" type="com.form.doc.docsrecv.FFilesRecv">                       
               <li>
               <logic:equal name="BHaveFileFromEmail" value="1" >
                <input type="checkbox" name="emailFileIds" checked id="emailFileIds" value="<bean:write name="beanFiles" property="idFiles"/>"  />
                <A href="javascript:post('docsrecv',anchor + ':_DOWNLOAD_FILE_EMAIL:fileId:<bean:write name="beanFiles" property="idFiles"/>');remove('docsrecv',anchor);remove('docsrecv','fileId');" ><bean:write name="beanFiles" property="fileName" /></a>  
               </logic:equal>
               
               <logic:notEqual name="BHaveFileFromEmail" value="1" >
                    <A href="javascript:post('docsrecv',anchor + ':_DOWNLOAD:fileId:<bean:write name="beanFiles" property="idFiles"/>');remove('docsrecv',anchor);remove('docsrecv','fileId');" ><bean:write name="beanFiles" property="fileName" /></a>  
                    <img style="border:0px;cursor:pointer;" src="<%=contextPath%>/images/newImages/i_17.gif"  title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:post('docsrecv',anchor + ':_DELETE_FILE:fileId:<bean:write name="beanFiles" property="idFiles"/>')">
                </logic:notEqual>
                </li>
        </logic:iterate>
         </ol>     
</logic:notEmpty>
