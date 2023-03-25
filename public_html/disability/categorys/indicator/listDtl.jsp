<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%
    String params = anchor + ":_VIEW";
%>

<logic:present name="listDtl" >
<div>	  
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listDtl"/>
        <jsp:param name="FORM" value="indicator"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="listId"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>

<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<TBODY>
    <TR>               
        <TH width="10px" nowrap align="center"><bean:message key="disability.unit.label.stt" bundle="<%=interfaces%>"/></TH>
        <TH width="15%" nowrap align="center"><bean:message key="common.label.createdate" bundle="<%=interfaces%>"/></TH>                
        <TH><bean:message key="common.label.location" bundle="<%=interfaces%>" /></TH>
        <TH><bean:message key="common.label.data.type" bundle="<%=interfaces%>" /></TH>
        <TH><bean:message key="common.label.value" bundle="<%=interfaces%>"/></TH>
        <TH width="40px" nowrap align="center">#</TH>
    </TR>
    
    <logic:present name="listDtl"> 
        <bean:define name="listDtl" id="beans" type="com.form.FBeans" />
        <%  int i = beans.getFirstRecord();%>
        <logic:iterate name="listDtl" id="bean" type="com.form.disability.FIndicatorVal">  
        <tr style="cursor:pointer" class="<%=(i%2==0)?"content1":"content"%>">
                <td align="center"><span class="index"><%=i++%></span></td>
                <td align="center"><bean:write name="bean" property="createDate" /></td>
                <td align="center"><bean:write name="bean" property="locationName" /></td>
                <td>
                    <logic:equal value="0" property="type" name="bean">
                        <bean:message key="common.label.target" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    <logic:equal value="1" property="type" name="bean">
                        <bean:message key="common.label.baseline" bundle="<%=interfaces%>"/>
                    </logic:equal>
                </td>
                <td align="right"><bean:write name="bean" property="val" /></td>                
                <td><img style="border:0px" src="<%=contextPath%>/images/editdraft.png" 
                      title="<bean:message key="action.detail" bundle="<%=interfaces%>"/>" 
                      onClick="post('indicator',anchor + ':_DETAIL_VALUE:mapId:<%=bean.getId()%>');">
                    <img style="border:0px" src="<%=contextPath%>/images/delete.png" 
                      title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                      onClick="javascript:if(messageDelete()){post('indicator',anchor + ':_DELETE_VALUE:mapId:<%=bean.getId()%>');}"></td>
            </tr>
        </logic:iterate>
    </logic:present>
</tbody>
</table>

<logic:present name="listDtl" >
<div>	
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listDtl"/>
        <jsp:param name="FORM" value="indicator"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="listId"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>	 
