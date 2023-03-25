<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <%String params = anchor + ":_VIEW";%>
<logic:present name="BThongTinTuyens" >
<div>	 
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="BThongTinTuyens"/>
        <jsp:param name="FORM" value="thongtinTuyen"/>
        <jsp:param name="METHOD" value="postAjax"/>
    	<jsp:param name="POSITION" value="listId"/>
    	<jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<TBODY>
    <TR>               
        <TH width="3%" align="center"><bean:message key="disability.tuyen.label.stt" bundle="<%=interfaces%>"/></TH>            		
        <TH width="7%"><bean:message key="disability.tuyen.label.period" bundle="<%=interfaces%>"/></TH>
        <TH width="20%" ><bean:message key="disability.tuyen.label.area" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="disability.tuyen.label.population" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="disability.tuyen.label.female" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="disability.tuyen.label.male" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="disability.tuyen.label.female.has.jobs" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="disability.tuyen.label.has.jobs" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="disability.tuyen.label.female.less.jobs" bundle="<%=interfaces%>"/></TH>		
        <TH><bean:message key="disability.tuyen.label.less.jobs" bundle="<%=interfaces%>"/></TH>		
        <TH width="8%" align="center">#</TH>
    </TR>
    
    <logic:present name="BThongTinTuyens"> 
	    <bean:define name="BThongTinTuyens" id="beans" type="com.form.FBeans" />
	    <%  int i = beans.getFirstRecord();%>
	    <logic:iterate name="BThongTinTuyens" id="bean" type="com.form.disability.FThongTinTuyen">                                       
		<tr style="cursor:pointer" class="<%=(i%2==0)?"content1":"content"%>">
			<td align="center"><span class="index"><%=i++%></span></td>
			<td class="center"><bean:write name="bean" property="kyBC" />/<bean:write name="bean" property="namBC" /></td>
			<td class="left"><bean:write name="bean" property="name_tinh" /></td>
			<td class="right"><bean:write name="bean" property="totalPopulation" /></td>
			<td class="right"><bean:write name="bean" property="totalFemale" /></td>
			<td class="right"><bean:write name="bean" property="totalMale" /></td>
                        <td class="right"><bean:write name="bean" property="femaleHasJob" /></td>
			<td class="right"><bean:write name="bean" property="totalHasJob" /></td>
                        <td class="right"><bean:write name="bean" property="femaleJobLess" /></td>
			<td class="right"><bean:write name="bean" property="totalJobLess" /></td>
			<td class="center">
                            <% if(request.getSession().getAttribute("06.02")!=null){%>
                            <img style="border:0px" src="<%=contextPath%>/images/editdraft.png" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onclick="post('thongtinTuyen',anchor + ':_DETAIL:id:<%=bean.getId()%>')"  class="<%=(i%2==0)?"content1":"content"%>">
                            <%}%>	
                            <% if(request.getSession().getAttribute("06.03")!=null){%>
                            <img style="border:0px" src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete()){post('thongtinTuyen',anchor + ':_DELETE:id:<%=bean.getId()%>')}">
                            <%}%>
			</td>
		</tr>
	    </logic:iterate>
    </logic:present>
</tbody>
</table>
<logic:present name="BThongTinTuyens" >
<div>	
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="BThongTinTuyens"/>
        <jsp:param name="FORM" value="thongtinTuyen"/>
        <jsp:param name="METHOD" value="postAjax"/>
    	<jsp:param name="POSITION" value="listId"/>
    	<jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>	 
