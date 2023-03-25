<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<script language=javascript>
     var fileCount= 1;
     function checkAddFile(obj){
        return obj.value && (obj.name=='file[' + (fileCount-1) + ']');
     }
     
     function addFile() {
      var input = '<input type=text name=fileText style="width:145px;" /> <input type=file size="30" name=file[' + fileCount  + '] onchange="javascript:if(checkAddFile(this)) addFile();"><\/div><div id="_' + (fileCount +1) + '">'  ; 
      document.getElementById('_' + fileCount).innerHTML = input;            
      fileCount++;
    }   
</script>
<div id='_0'><input type="text" name="fileText" style="width:145px;" /> <input type=file name='file[0]' size="30" onchange="if(checkAddFile(this)) addFile();"></div>
<div id='_1'></div>

<logic:notEmpty name="docssend" property="allFiles">
            <ol class="calassFile">
                <% String fileName = "";int id= 0,dem = 0; %>           
            <logic:iterate name="docssend" property="allFiles" id="beanFiles" type="com.form.doc.docssend.FFilesSend">   
             <% if (!fileName.equals(beanFiles.getFileName())){  fileName = beanFiles.getFileName(); id = beanFiles.getIdFiles();dem =0; %>
            <li>
                    <a href="javascript:post('docssend',anchor + ':_DOWNLOAD_DRAFT_EDIT:fileId:<bean:write name="beanFiles" property="idFiles"/>');remove('docssend',anchor);remove('docssend','fileId');" >
                        <logic:equal name="beanFiles" property="description" value="">
                            <bean:write name="beanFiles" property="fileName" />
                        </logic:equal>
                        <logic:notEqual name="beanFiles" property="description" value="">
                            <bean:write name="beanFiles" property="description" />
                        </logic:notEqual>
                        </a>
                                            (<bean:write name="beanFiles" property="numberReadedFile" />)
                        <logic:equal name="docssend" property="type" value="2">
                                <img  style="border:0px;" title='<bean:message key="system.action.delete" bundle="<%=interfaces%>"/>' src="<%=contextPath%>/images/delete.png" onclick="javascript:post('docssend',anchor + ':_DELETE_FILE:fileId:<bean:write name="beanFiles" property="idFiles"/>')"> 
                        </logic:equal>
                    <%}else{  dem ++;
                        if (dem==1){
                      %>                 
                          <input  style="border:0px;cursor: pointer;"  type="button"   value=" + "  onclick="checkedInnerHtml();addthis_open(this,'<bean:message key="doc.version.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('docssend','at_share',anchor + ':_FILEDINHKEM_IN_INFOR:id:<bean:write name="docssend" property="id"/>:fileId:<%=id%>');" />
                       <%}%>
                      
            </li>
            <%}%>
            </logic:iterate>               
            </ol>
    </logic:notEmpty>
    