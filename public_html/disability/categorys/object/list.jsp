<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%
    String params = anchor + ":_VIEW";
%>
<logic:present name="listObjects" >
<div>	  
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listObjects"/>
        <jsp:param name="FORM" value="object"/>
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
        <TH width="15%" height="21"><bean:message key="object.edit.code" bundle="<%=interfaces%>"/></TH>
        <TH height="21"><bean:message key="object.edit.name" bundle="<%=interfaces%>"/></TH>
        <TH height="21">Hi&#7875;n th&#7883;</TH>
        <TH width="70px" height="21"><bean:message key="common.label.total.indicator" bundle="<%=interfaces%>"/></TH>
        <TH width="60px" align="center" height="21">#</TH>
    </TR>
    
    <logic:present name="listObjects"> 
        <bean:define name="listObjects" id="beans" type="com.form.FBeans" />
        <%  int i = beans.getFirstRecord();%>
        <logic:iterate name="listObjects" id="bean" type="com.form.disability.categorys.FObject">  
        <tr style="cursor:pointer" class="<%=(i%2==0)?"content1":"content"%>">
                <td align="center"><span class="index"><%=i++%></span></td>
                <td align="center"><bean:write name="bean" property="createDate" /></td>
                <td align="left"><bean:write name="bean" property="code" /></td>
                <td align="left"><bean:write name="bean" property="name" /></td>
                <td align="left">
                  <logic:equal value="0" property="type" name="bean">
                    Danh s&#225;ch ch&#7881; s&#7889;
                  </logic:equal>
                  <logic:equal value="1" property="type" name="bean">
                    Nh&#7853;p th&#244;ng tin
                  </logic:equal>
                </td>
                <td align="right"><bean:write name="bean" property="totalIndicator" /></td>
                <td>
                    <img style="border:0px" src="<%=contextPath%>/images/tick.png" 
                        title="<bean:message key="action.select" bundle="<%=interfaces%>"/>" 
                        onClick="post('object',anchor + ':_SELECT:dtlId:<%=bean.getId()%>');">
                    <img style="border:0px" src="<%=contextPath%>/images/editdraft.png" 
                        title="<bean:message key="action.detail" bundle="<%=interfaces%>"/>" 
                        onClick="post('object',anchor + ':_DETAIL:dtlId:<%=bean.getId()%>');">
                    <img style="border:0px" src="<%=contextPath%>/images/delete.png" 
                        title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                        onClick="javascript:if(messageDelete())post('object',anchor + ':_DELETE:dtlId:<%=bean.getId()%>')">
                </td>
            </tr>
        </logic:iterate>
    </logic:present>
</tbody>
</table>

<logic:present name="listObjects" >
<div>	
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listObjects"/>
        <jsp:param name="FORM" value="object"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="listId"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>	 
