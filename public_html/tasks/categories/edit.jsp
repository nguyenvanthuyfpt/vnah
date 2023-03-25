<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<div class="col1-ctn1 clearfix">
 <table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
            <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_19.gif" width="8" height="43" /></td>
                <td class="sharebackground">
                <img src="<%=contextPath%>/images/security_f2.gif"  height="18px" />
                <Strong> <bean:message key="doc.reci.title.caption.category" bundle="<%=interfaces%>"/></strong></td>
                <td width="15" class="sharebackground"></td>
                <td width="10"><img src="<%=contextPath%>/images/newImages/i_20.gif" width="10" height="43" /></td>
            </tr>
</table>
 <div style="clear:both"></div>
<html:form action="formCate" target="_top">
 <html:hidden  name="formCate" property="id"/>
<TABLE cellSpacing="0" cellPadding="0" width="95%" border="0" align="center" style="line-height:24px;padding-top:5px">
        
        <tr>
            <td ><bean:message key="categories.task.title" bundle="<%=interfaces%>"/> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
            <td><html:text name="formCate" property="title" style="width:220px" maxlength="50"/></td>
        </tr>
        
        <tr>
            <td ><bean:message key="categories.task.description" bundle="<%=interfaces%>"/></td>
            <td><html:textarea name="formCate" property="description" style="width:220px;height:60px;"/></td>
        </tr>
        
        <tr>
            <td ><bean:message key="category.mailAccount.status" bundle="<%=interfaces%>"/></td>
            <td>
            <html:select name="formCate" property="block" styleClass="fieldSelect" style="width:200px;"  >
                    <html:option value="1"><bean:message key="mailAccount.status.0" bundle="<%=interfaces%>"/></html:option>
                    <html:option value="0"><bean:message key="mailAccount.status.1" bundle="<%=interfaces%>"/></html:option>
            </html:select>
            </td>
        </tr>
        
        
                    
</table>        
<div align="center" style="padding-top:5px;">
<logic:equal name="formCate" property="id" value="0">
 <html:button property="_EDIT"  onclick="post('formCate',anchor+':_CREATE');" styleClass="button">                       
     <bean:message key="action.insert" bundle="<%=interfaces%>"/>
 </html:button>
</logic:equal>  
<logic:notEqual name="formCate" property="id" value="0">
 <html:button property="_EDIT"  onclick="post('formCate',anchor + ':_EDIT');"  styleClass="button">                       
     <bean:message key="action.update" bundle="<%=interfaces%>"/>
 </html:button>
</logic:notEqual>  
</div>
 </html:form>           
 </div>
 
            
  
 
 