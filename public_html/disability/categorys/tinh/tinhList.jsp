<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<TBODY>
    <TR>               
        <TH width="10px" nowrap align="center"><bean:message key="disability.unit.label.stt" bundle="<%=interfaces%>"/></TH>            
        <TH nowrap ><bean:message key="disability.unit.label.name" bundle="<%=interfaces%>"/></TH>
        <TH nowrap >#</TH>
    </TR>
    <logic:present name="BListTinhs"> 
	    <bean:define name="BListTinhs" id="beans" type="com.form.FBeans" />
	    <%  int i =1;%>
	    <logic:iterate name="BListTinhs" id="bean" type="com.form.disability.categorys.FTinh">
	        <tr style="cursor:pointer">
	            <td align="center"><span class="index"><%=i++%></span></td>
	            <td align="center"><a href="#" onclick="post('tinh',anchor + ':_DETAIL:id:<%=bean.getId()%>')" ><bean:write name="bean" property="name" /></a></td>
	            <td><img style="border:0px" src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('tinh',anchor + ':_DELETE:id:<%=bean.getId()%>')"></td>
	        </tr>
	    </logic:iterate>
    </logic:present>
    
</tbody>
</table>

 
