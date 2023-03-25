<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:hidden name="infor" property="nktId" styleId="nktId" />
<%String srcStringTemp="";%>
<logic:notEmpty name="srcString">
    <bean:define name="srcString" id="srcString" type="java.lang.String" />
    <%srcStringTemp=srcString;%>
</logic:notEmpty>
<logic:present name="BTemps"> 
<div class="content-calendar-2">
<table class="tableForm" cellpadding="0" width="100%" align="center" style="border-collapse: collapse" cellspacing="0" border="0">    
      <logic:iterate name="BTemps" id="bean" type="com.form.disability.FInforNKT">
      <tr>
                <td  nowrap="nowrap" width="10px">
                <%if(srcStringTemp.indexOf("#"+bean.getId()+"#")>=0){%>
                    <input type="checkbox" name="tempId" checked id="tempId" value="<bean:write name="bean" property="id" />" />    
                <%}else{%>
                    <input type="checkbox" name="tempId" id="tempId" value="<bean:write name="bean" property="id" />" />    
                <%}%>
                </td>
                <td align="left" >
                <bean:write name="bean" property="name" />      
                 </td>
      </tr>
   </logic:iterate>
            
      <tr>
        <td colspan="2">
            <logic:notEqual name="infor" property="nktId" value="0">
                <html:button property="_CREATE" styleClass="button" onclick="postAjax('inforNKT','MainCate',anchor + ':_INSERT')" >
                        <bean:message key="action.update" bundle="<%=interfaces%>"/>
                </html:button>            
            </logic:notEqual>
        </td>
      </tr>
</table>  
</div>
</logic:present>
<div><html:errors property="alert" bundle="<%=interfaces%>" /></div>
 
