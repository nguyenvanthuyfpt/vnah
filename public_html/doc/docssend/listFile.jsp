<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<bean:define name="BDocssend" id="beanSend" type="com.form.doc.docssend.FDocssend" />
<logic:present name="BDocsFiles">
<div style="padding-left:4px">
<%String realName="";int filedoc =0;%>
   <ol style="margin:9px;padding:0px;line-height:16px;">     
        <logic:iterate name="BDocsFiles" id="beanFiles" type="com.form.doc.docssend.FFilesSend">                      
            <li style="padding-left:8px">            
             <a href="javascript:post('docssend',anchor + ':<%=(beanSend.getStatusId()!=-1 && beanFiles.getFileName().indexOf("doc",beanFiles.getFileName().length()-3)>=0)?"_DOWNLOAD_DRAFT":"_DOWNLOAD"%>:fileId:<bean:write name="beanFiles" property="idFiles"/>');remove('docssend',anchor);remove('docssend','fileId');" >                            
                   <logic:equal name="beanFiles" property="description" value="">
                        <bean:write name="beanFiles" property="fileName" />
                    </logic:equal>
                    <logic:notEqual name="beanFiles" property="description" value="">
                        <bean:write name="beanFiles" property="description" />
                    </logic:notEqual>
                    (<bean:write name="beanFiles" property="numberReadedFile" />)
                  - <bean:write name="beanFiles" property="userName" />   (<bean:write name="beanFiles" property="createTimeName" />)     
                  - <span class="version"><span class="version"> (V<bean:write name="beanFiles" property="version" />) </span>
                  <logic:equal name="beanFiles" property="views" value="1">                  
                  <span style="color:blue"> - <strong><bean:message key="cmd.docssend.docssend" bundle="<%=interfaces%>"/></strong></span>
                  </logic:equal>
            </span>
            
            </a>
            </li>
        </logic:iterate>
    </ol>
</div>
</logic:present>


<logic:present name="BDocsFilesInInfor">
<div>
   <ol style="margin:0px;padding:0px;line-height:16px;">  
    <logic:iterate name="BDocsFilesInInfor" id="beanFiles" type="com.form.doc.docssend.FFilesSend">       
    <li style="padding-left:8px">    
    <A href="javascript:post('docssend',anchor + ':<%=(beanSend.getStatusId()!=-1 && beanFiles.getFileName().indexOf("doc",beanFiles.getFileName().length()-3)>=0)?"_DOWNLOAD_DRAFT":"_DOWNLOAD"%>:fileId:<bean:write name="beanFiles" property="idFiles"/>');remove('docssend',anchor);remove('docssend','fileId');" >
          <logic:equal name="beanFiles" property="description" value="">
                    <bean:write name="beanFiles" property="fileName" />
                    </logic:equal>
                    <logic:notEqual name="beanFiles" property="description" value="">
                    <bean:write name="beanFiles" property="description" />
                    </logic:notEqual>
                    (<bean:write name="beanFiles" property="numberReadedFile" />)
          - <bean:write name="beanFiles" property="userName" />   (<bean:write name="beanFiles" property="createTimeName" />)   - <span class="version"> (V<bean:write name="beanFiles" property="version" />) </span>               
    </a>
     <logic:equal name="beanFiles" property="views" value="1">                  
              <span style="color:blue"> - <strong> <bean:message key="cmd.docssend.docssend" bundle="<%=interfaces%>"/></strong></span>
        </logic:equal>
    </li>    
    </logic:iterate>
    </ol>
</div>
</logic:present>
