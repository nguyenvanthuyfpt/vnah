<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
     var fileCount= 1;
     function checkAddFile(obj){
        return obj.value && (obj.name=='file[' + (fileCount-1) + ']');
     }
     function addFile() {
      var input = '<input type=file size=40 name=file[' + fileCount  + '] onchange="javascript:if(checkAddFile(this)) addFile();"><\/div><div id="_' + (fileCount +1) + '">'  ; 
      document.getElementById('_' + fileCount).innerHTML = input;
      fileCount++;
    }
</script>
<table width="100%" align="left" cellpadding="0" cellspacing="0">
<tr>
<td width="50%" valign="top" align="left">
<div id='_0' align="left"><input type=file name='file[0]' size=40 onchange="if(checkAddFile(this)) addFile();"></div><div id='_1'></div>
</td>
<td width="50%" valign="top" align="left">
<logic:notEmpty name="sendMail" property="allFiles">
        <ol style="padding-left:30px;margin:3px 3px 3px 3px;">
        <logic:iterate name="sendMail" property="allFiles" id="beanFiles" type="com.form.mail.FMail">                       
            <li>
            <logic:equal name="anchor" value="1">
            <input type="checkbox" name="fileIds" id="fileIds" checked value="<%=beanFiles.getFileId()%>" />
            </logic:equal>
            
            <A href="javascript:post('sendMail',anchor + ':_DOWNLOAD:fileName:<%=beanFiles.getFileName()%>:fileId:<%=beanFiles.getFileId()%>');remove('sendMail',anchor);remove('sendMail','fileId');remove('sendMail','fileName');" ><bean:write name="beanFiles" property="fileName" /></a>  
            </li>
        </logic:iterate>
         </ol>     
</logic:notEmpty>
</td>
</tr>
</table>