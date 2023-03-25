<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%String params = anchor + ":_VIEW";%>
<logic:present name="BUnits" >
<div>	  
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="BUnits"/>
        <jsp:param name="FORM" value="dr_unit"/>
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
        <TH width="40%" nowrap><bean:message key="disability.unit.label.name" bundle="<%=interfaces%>"/></TH>
        <TH nowrap><bean:message key="disability.unit.label.info" bundle="<%=interfaces%>"/></TH>
        <TH width="20px" nowrap align="center">#</TH>
    </TR>
    
    <logic:present name="BUnits"> 
        <bean:define name="BUnits" id="beans" type="com.form.FBeans" />
        <%  int i = beans.getFirstRecord();%>
        <logic:iterate name="BUnits" id="bean" type="com.form.disability.FUnit">                                       
            <tr style="cursor:pointer" onclick="post('dr_unit',anchor + ':_DETAIL:id:<%=bean.getId()%>')"  class="<%=(i%2==0)?"content1":"content"%>">
                <td align="center"><span class="index"><%=i++%></span></td>
                <td align="left"><bean:write name="bean" property="name" /></td>
                <td align="left">
                    <bean:write name="bean" property="address" /><br>
                    <logic:notEmpty name="bean" property="phone">
                        <bean:write name="bean" property="phone" /><br/>
                    </logic:notEmpty>
                    <logic:notEmpty name="bean" property="fax">
                        <bean:write name="bean" property="fax" /><br/>
                    </logic:notEmpty>
                    <logic:notEmpty name="bean" property="email">
                        <bean:write name="bean" property="email" />
                    </logic:notEmpty>
                </td>
                <td><img style="border:0px" src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('dr_unit',anchor + ':_DELETE:id:<%=bean.getId()%>')"></td>
            </tr>
        </logic:iterate>
    </logic:present>
</tbody>
</table>

<logic:present name="BUnits" >
<div>	
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="BUnits"/>
        <jsp:param name="FORM" value="dr_unit"/>
        <jsp:param name="METHOD" value="postAjax"/>
    	<jsp:param name="POSITION" value="listId"/>
    	<jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>	 
