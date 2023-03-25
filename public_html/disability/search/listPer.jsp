<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div><html:errors property="alert" bundle="<%=interfaces%>" /></div>
<% String params = anchor + ":_SEARCH_RESULT" ;%>

<logic:present name="BPersons" >
<div>	  
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="BPersons"/>
        <jsp:param name="FORM" value="searchdispeople"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="divSearchResult"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>
</logic:present>

<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<tbody>
    <tr>
        <TH width="5%">STT</TH>
        <!--<TH width="12%"><bean:message key="common.label.createdate" bundle="<%=interfaces%>"/></TH>-->
        <TH width="15%"><bean:message key="common.label.location" bundle="<%=interfaces%>"/></TH>
        <TH width="20%"><bean:message key="common.label.name" bundle="<%=interfaces%>"/></TH>
        <TH width="10%"><bean:message key="common.label.sex" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.role" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.agency" bundle="<%=interfaces%>"/></TH>        
    </tr>
    <logic:present name="BPersons"> 
        <bean:define name="BPersons" id="beans" type="com.form.FBeans" />
        <%  int i = beans.getFirstRecord();%>
        <logic:iterate name="BPersons" id="bean" type="com.form.disability.search.FSearch">
            <tr class="<%=(i%2==0)?"content1":"content"%>">
                <td align="center"><%=i++%></td>                
                <td align="left"><bean:write name="bean" property="locationName" /></td>
                <td align="left"><bean:write name="bean" property="perName" /></td>
                <td align="left">
                    <logic:equal name="bean" property="perSex" value="0" >
                        <bean:message key="common.label.sex.male" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    
                    <logic:equal name="bean" property="perSex" value="1" >
                        <bean:message key="common.label.sex.female" bundle="<%=interfaces%>"/>
                    </logic:equal>  
                </td>                                                    
                <td align="left"><bean:write name="bean" property="perTitle" /></td>
                <td align="left"><bean:write name="bean" property="perAgency" /></td>                
            </tr>
        </logic:iterate>
    </logic:present>
</tbody>
</table>

<logic:present name="BPersons" >
<div>	  
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="BPersons"/>
        <jsp:param name="FORM" value="searchdispeople"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="divSearchResult"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>
</logic:present>