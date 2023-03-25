 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table  class="list-voffice"  cellpadding="5" cellspacing="0" border="0" width="100%" >
<tr>
                <th width="5%" nowrap ><bean:message key="app.stt" bundle="<%=interfaces%>"/></th>
                <th width="10%" nowrap ><bean:message key="lable.from.code" bundle="<%=interfaces%>"/></th>
                <th width="25%" nowrap ><bean:message key="lable.from.vnName" bundle="<%=interfaces%>"/></th>
                <th width="15%" nowrap ><bean:message key="lable.from.enName" bundle="<%=interfaces%>"/></th>
                <th width="15%" nowrap ><bean:message key="lable.from.description" bundle="<%=interfaces%>"/></th>
                <th width="5%"   nowrap >&nbsp;#</th>
</tr>
<logic:present name="BFroms">
<bean:define name="BFroms" id="beans" type="com.form.FBeans"/>
<%  int i = 1;%>
<logic:iterate name="BFroms" id="bean" type="com.form.doc.from.FFrom">   
    <tr class="<%=(i%2==0)?"content1":"content"%>" >
        <td align="center" ><span class="index"><%=i++%></span></td>
        <td>
                <bean:write name="bean" property="code" />
        </td>
        <td><bean:write name="bean" property="vnName"/></td>
        <td><bean:write name="bean" property="enName"/></td>
        <td><bean:write name="bean" property="description"/></td>
        <td nowrap>
        <a class="modal-button" href="from<%=extention%>?<%=anchor%>=_PREPARED_EDIT&id=<%=bean.getId()%>" rel="{handler: 'iframe', size: {x:365, y: 240}}">
                    <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" >
        </a>
                    <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('dossiers',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>')">
        </td>
    </tr>
</logic:iterate>
</logic:present>
</table>
