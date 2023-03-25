<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%
    String params = anchor + ":_VIEW";
%>
<logic:present name="listRanks" >
<div>	  
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listRanks"/>
        <jsp:param name="FORM" value="kpi_rank"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="listId"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>
	
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<TBODY>
    <TR>               
        <TH width="10px" align="center" height="21"><bean:message key="disability.unit.label.stt" bundle="<%=interfaces%>"/></TH>            
        <TH width="15%" align="center" height="21"><bean:message key="common.label.createdate" bundle="<%=interfaces%>"/></TH>
        <TH width="15%" height="21"><bean:message key="rank.edit.code" bundle="<%=interfaces%>"/></TH>
        <TH height="21"><bean:message key="rank.edit.name" bundle="<%=interfaces%>"/></TH>
        <TH width="60px" align="center" height="21">#</TH>
    </TR>
    
    <logic:present name="listRanks"> 
        <bean:define name="listRanks" id="beans" type="com.form.FBeans" />
        <%  int i = beans.getFirstRecord();%>
        <logic:iterate name="listRanks" id="bean" type="com.form.disability.categorys.FRank">  
        <tr style="cursor:pointer" class="<%=(i%2==0)?"content1":"content"%>">
                <td align="center"><span class="index"><%=i++%></span></td>
                <td align="center"><bean:write name="bean" property="createDate" /></td>
                <td align="left"><bean:write name="bean" property="code" /></td>
                <td align="left"><bean:write name="bean" property="name" /></td>
                <td>
                    <img style="border:0px" src="<%=contextPath%>/images/editdraft.png" 
                        title="<bean:message key="action.detail" bundle="<%=interfaces%>"/>" 
                        onClick="post('kpi_rank',anchor + ':_DETAIL:dtlId:<%=bean.getId()%>');">
                        
                    <img style="border:0px" src="<%=contextPath%>/images/delete.png" 
                        title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                        onClick="javascript:if(messageDelete())post('kpi_rank',anchor + ':_DELETE:id:<%=bean.getId()%>')">
                </td>
            </tr>
        </logic:iterate>
    </logic:present>
</tbody>
</table>

<logic:present name="listRanks" >
<div>	
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listRanks"/>
        <jsp:param name="FORM" value="kpi_rank"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="listId"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>	 
