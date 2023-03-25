<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<bean:define name="kpi" property="eventSel" id="eventSelId" type="java.lang.String" />
<logic:present name="listEvents" >
<%   String params = anchor + ":_VIEW_EVENT"; %>
<div>	  
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listEvents"/>
        <jsp:param name="FORM" value="kpi"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="listId"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>  
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
    <tbody>
        <tr>
            <TH width="3%">&nbsp;</TH>
            <TH width="5%">STT</TH>            
            <TH width="50%"><bean:message key="common.label.activity" bundle="<%=interfaces%>"/></TH>        
            <TH><bean:message key="common.label.event.location" bundle="<%=interfaces%>"/></TH>
            <TH><bean:message key="common.label.total.person" bundle="<%=interfaces%>"/></TH>
            <TH width="8%">#</TH>
        </tr>
        <logic:present name="listEvents"> 
            <bean:define name="listEvents" id="beans" type="com.form.FBeans" />
            <%  int i = beans.getFirstRecord();%>
            <logic:iterate name="listEvents" id="bean" type="com.form.disability.categorys.FEvent">                          
                <%
                    String checkBox = (eventSelId.indexOf(String.valueOf(bean.getEventId()))>=0) ? "checked='checked'":"";
                %>
                <tr class="<%=(i%2==0)?"content1":"content"%>">
                    <td align="center" class="no-padding">
                        <logic:equal value="0" name="bean" property="expire">
                            <input type="checkbox" name="eventSel"  value="<%=bean.getEventId()%>" <%=checkBox%> />
                        </logic:equal>
                        <logic:equal value="1" name="bean" property="expire">
                            <input type="checkbox" name="eventSel"  value="<%=bean.getEventId()%>" <%=checkBox%> disabled="disabled"/>
                        </logic:equal>
                     </td>
                    <td><%=i++%></td>                    
                    <td align="left">
                          <bean:write name="bean" property="activity" /><br/>
                          <bean:write name="bean" property="startDate" /> - <bean:write name="bean" property="endDate" />
                    </td>
                    <td align="left"><bean:write name="bean" property="location" /></td>
                    <td align="left"><bean:write name="bean" property="total" /></td>
                    <td>
                        <logic:equal value="0" name="bean" property="expire">
                            <img style="border:0px" src="<%=contextPath%>/images/editdraft.png" 
                              title="<bean:message key="action.detail" bundle="<%=interfaces%>"/>" 
                              onClick="post('kpi',anchor + ':_DETAIL_EVENT:dtlId:<%=bean.getEventId()%>');">
                        </logic:equal>
                        <logic:equal value="0" name="bean" property="total">
                            <img style="border:0px" src="<%=contextPath%>/images/delete.png" 
                                title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                                onClick="javascript:if(messageDelete()){post('kpi',anchor + ':_DELETE_EVENT:dtlId:<%=bean.getEventId()%>');}">
                        </logic:equal>
                    </td>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>

<logic:present name="listEvents" >
<%   String params = anchor + ":_VIEW_EVENT"; %>
<div>	  
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listEvents"/>
        <jsp:param name="FORM" value="kpi"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="listId"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>