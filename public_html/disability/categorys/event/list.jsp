<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%
    String params = anchor + ":_VIEW";
    java.util.Map<String, String> mapEventType = (java.util.Map<String, String>) request.getAttribute("mapEventType");
    java.util.Map<String, String> mapEventField = (java.util.Map<String, String>) request.getAttribute("mapEventField");
%>
<logic:present name="listEvents" >
<div>	  
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listEvents"/>
        <jsp:param name="FORM" value="event"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="listId"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>
	
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<TBODY>
    <TR>               
        <TH width="10px" align="center"><bean:message key="disability.unit.label.stt" bundle="<%=interfaces%>"/></TH>    
        <TH><bean:message key="common.label.location" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.info" bundle="<%=interfaces%>"/></TH>
        <TH width="40px" nowrap align="center">#</TH>
    </TR>
    
    <logic:present name="listEvents"> 
        <bean:define name="listEvents" id="beans" type="com.form.FBeans" />
        <%  int i = beans.getFirstRecord();%>
        <logic:iterate name="listEvents" id="bean" type="com.form.disability.categorys.FEvent">  
        <tr style="cursor:pointer" class="<%=(i%2==0)?"content1":"content"%>">
                <td align="center"><span class="index"><%=i++%></span></td>
                <td align="left"><bean:write name="bean" property="locationName" /></td>
                <td align="left">
                    <div>
                        <bean:write name="bean" property="activity" /><br/>
                        <logic:notEqual value="0" name="bean" property="eventType" >
                            <b><bean:message key="common.label.event.type" bundle="<%=interfaces%>"/> : </b>
                            <%=mapEventType.get(String.valueOf(bean.getEventType()))%><br/>
                        </logic:notEqual>
                        <logic:notEqual value="0" name="bean" property="eventField" >
                            <b><bean:message key="common.label.event.field" bundle="<%=interfaces%>"/> : </b>
                            <%=mapEventField.get(String.valueOf(bean.getEventField()))%><br/>
                        </logic:notEqual>
                        <logic:notEmpty name="bean" property="location">
                            <b><bean:message key="common.label.event.location" bundle="<%=interfaces%>"/> : </b>
                            <bean:write name="bean" property="location" /><br/>
                        </logic:notEmpty>
                        <b>Th&#7901;i gian</b> (t&#7915;-&#273;&#7871;n) : <bean:write name="bean" property="startDate" /> - <bean:write name="bean" property="endDate" />
                     </div>
                </td>
                <td>
                    <img style="border:0px" src="<%=contextPath%>/images/editdraft.png" 
                      title="<bean:message key="action.detail" bundle="<%=interfaces%>"/>" 
                      onClick="post('event',anchor + ':_DETAIL:dtlId:<%=bean.getEventId()%>');">
                    <img style="border:0px" src="<%=contextPath%>/images/delete.png" 
                      title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                      onClick="javascript:if(messageDelete()){post('event',anchor + ':_DELETE:dtlId:<%=bean.getEventId()%>');}">
                </td>
            </tr>
        </logic:iterate>
    </logic:present>
</tbody>
</table>

<logic:present name="listEvents" >
<div>	
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listEvents"/>
        <jsp:param name="FORM" value="event"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="listId"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>	 
