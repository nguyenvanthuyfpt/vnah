<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BFiles">
<div style="border:1px solid #CCCCCC">
   <ol style="padding-left:15px;margin-left:15px;line-height:20px;">
    <logic:iterate name="BFiles" id="beanFiles" type="com.form.messages.create.FCreate">    
    <li>
       <a href="javascript:post('messsagesList',anchor + ':_SAVE:fileId:<%=beanFiles.getFileId()%>');remove('messsagesList',anchor);remove('messsagesList','fileId')">
         <bean:write name="beanFiles" property="readName" />
       </a>
       
       <div align="right" style="font-size:10px;padding-right:5px;"><bean:write name="beanFiles" property="timeCreate" /></div>
    </li>
    </logic:iterate>
    </ol>
</div>
</logic:present>
