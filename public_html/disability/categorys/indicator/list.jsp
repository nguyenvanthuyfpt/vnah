<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%
    String params = anchor + ":_VIEW";
%>
<!--
<logic:present name="listIndicators" >
<div>	  
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listIndicators"/>
        <jsp:param name="FORM" value="indicator"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="listId"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>
-->	
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<TBODY>
    <TR>               
        <TH width="10px" nowrap align="center"><bean:message key="disability.unit.label.stt" bundle="<%=interfaces%>"/></TH>            
        <TH width="15%" nowrap align="center"><bean:message key="common.label.createdate" bundle="<%=interfaces%>"/></TH>
        <TH width="12%"><bean:message key="indicator.edit.code" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="indicator.edit.name" bundle="<%=interfaces%>"/></TH>
        <TH width="60px" nowrap align="center">#</TH>
    </TR>
    
    <logic:present name="listIndicators"> 
        <bean:define name="listIndicators" id="beans" type="com.form.FBeans" />
        <%  int i = beans.getFirstRecord();%>
        <logic:iterate name="listIndicators" id="bean" type="com.form.disability.categorys.FIndicator">  
        <tr style="cursor:pointer" class="<%=(i%2==0)?"content1":"content"%>">
                <td align="center"><span class="index"><%=i++%></span></td>
                <td align="center"><bean:write name="bean" property="createDate" /></td>
                <td align="left"><bean:write name="bean" property="code" /></td>
                <td align="left"><bean:write name="bean" property="name" /></td>              
                <td>
                    <img style="border:0px" src="<%=contextPath%>/images/addUser.gif" 
                      title="<bean:message key="action.add" bundle="<%=interfaces%>"/>" 
                      onClick="post('indicator',anchor + ':_INPUT_VALUE:indId:<%=bean.getId()%>');">
                    <img style="border:0px" src="<%=contextPath%>/images/editdraft.png" 
                      title="<bean:message key="action.detail" bundle="<%=interfaces%>"/>" 
                      onClick="post('indicator',anchor + ':_DETAIL:indId:<%=bean.getId()%>');">
                    <img style="border:0px" src="<%=contextPath%>/images/delete.png" 
                      title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                      onClick="javascript:if(messageDelete()){post('indicator',anchor + ':_DELETE:indId:<%=bean.getId()%>');}"></td>
            </tr>
        </logic:iterate>
    </logic:present>
</tbody>
</table>
<!--
<logic:present name="listIndicators" >
<div>	
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listIndicators"/>
        <jsp:param name="FORM" value="indicator"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="listId"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>	 
-->