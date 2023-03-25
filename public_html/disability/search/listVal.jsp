<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div><html:errors property="alert" bundle="<%=interfaces%>" /></div>
<%   String params = anchor + ":_SEARCH_RESULT"; %>

<logic:present name="BValues" >
<div>	  
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="BValues"/>
        <jsp:param name="FORM" value="searchdispeople"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="divSearchResult"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>

<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<TBODY>
    <TR>               
        <TH width="5%">#</TH>            
        <TH width="15%"><bean:message key="common.label.createdate" bundle="<%=interfaces%>"/></TH>
        <TH width="15%"><bean:message key="common.label.createby" bundle="<%=interfaces%>"/></TH>
        <TH width="15%"><bean:message key="common.label.location" bundle="<%=interfaces%>"/></TH>
        <TH width="15%"><bean:message key="common.label.period.type" bundle="<%=interfaces%>"/></TH>        
        <TH width="12%"><bean:message key="common.label.period" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.target" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.actual" bundle="<%=interfaces%>"/></TH>        
    </TR>    
    <logic:present name="BValues"> 
        <bean:define name="BValues" id="beans" type="com.form.FBeans" />
        <%  int i = beans.getFirstRecord();%>
        <logic:iterate name="BValues" id="bean" type="com.form.disability.search.FSearch">
            <!--<tr onclick="post('kpi_detail',anchor + ':_DETAIL:dtlId:<%=bean.getDtlId()%>:locationId:<%=bean.getLocationId()%>:inputType:0:type:0:objId:<%=bean.getObjId()%>:indId:<%=bean.getIndId()%>');" -->
            <tr class="<%=(i%2==0)?"content1":"content"%>">
                <td align="center"><span class="index"><%=i++%></span></td>
                <td align="center"><bean:write name="bean" property="createDate" /></td>
                <td align="left"><bean:write name="bean" property="userFullName" /></td>
                <td align="left"><bean:write name="bean" property="locationName" /></td>
                <td align="left">                    
                    <logic:equal value="0" name="bean" property="periodType">
                        <bean:message key="common.label.month" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    <logic:equal value="1" name="bean" property="periodType">
                        <bean:message key="common.label.quarter" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    <logic:equal value="2" name="bean" property="periodType">
                        <bean:message key="common.label.year" bundle="<%=interfaces%>"/>
                    </logic:equal>
                </td>
                <td align="center"><bean:write name="bean" property="period" /></td>
                <td align="right"><bean:write name="bean" property="target" /></td>
                <td align="right"><bean:write name="bean" property="actual" /></td> 
            </tr>
        </logic:iterate>
    </logic:present>
</tbody>
</table>


<logic:present name="BValues" >
<div>	  
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="BValues"/>
        <jsp:param name="FORM" value="searchdispeople"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="divSearchResult"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>