<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
	
<bean:define name="BDisabilitys" id="beans" type="com.form.FBeans" />
<%
    String total = com.util.Formater.num2str(beans.getTotalRows());
%>
<table class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
	<TBODY>
        <TR>               
            <td nowrap align="center"><bean:message key="disability.search.form.area" bundle="<%=interfaces%>"/>:</td>            
            <td nowrap ><bean:write name="timkiem" property="tinhName" /></td>
        </TR>
        <TR>               
            <td nowrap align="center"><bean:message key="disability.search.form.totaldis" bundle="<%=interfaces%>"/>:</td>            
            <td nowrap ><%=total%></td>
        </TR>
	</tbody>
</table>

  
