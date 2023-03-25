<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BDocsFiles">
   <ol style="margin:0px;">
    <logic:iterate name="BDocsFiles" id="beanFiles" type="com.form.doc.docsrecv.FFilesRecv">   
     <li>
     <a href="javascript:post('docsrecv',anchor + ':_DOWNLOAD:fileId:<bean:write name="beanFiles" property="idFiles"/>');remove('docsrecv',anchor);remove('docsrecv','fileId');" >
        <bean:write name="beanFiles" property="fileName" />
     </a>
     </li>    
    </logic:iterate>
    </ol>
</logic:present>
<logic:empty name="BDocsFiles">
    <bean:message key="docs.download.notExist" bundle="<%=interfaces%>"/>
</logic:empty>

