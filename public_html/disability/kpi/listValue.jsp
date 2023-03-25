<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<bean:define id="typeVal" name="kpi" property="type" type="java.lang.Integer" />
<bean:define id="indIdVal" name="kpi" property="indId" type="java.lang.Integer" />
<%
    String strType = String.valueOf(typeVal);      
    String params = anchor + ":_VIEW_VALUE:type:" + strType;
%>

<!-- Values -->
<logic:present name="listDataDtl" >
<div>	  
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listDataDtl"/>
        <jsp:param name="FORM" value="kpi"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="listId"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>
	
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<TBODY>
    <!--<TR>               
        <TH colspan="9"><div style="text-align:left;">Danh s&#225;ch d&#7919; li&#7879;u &#273;&#227; nh&#7853;p</div></TH>
    </TR>-->
    <TR>               
        <TH width="10px"><bean:message key="disability.unit.label.stt" bundle="<%=interfaces%>"/></TH>            
        <TH width="15%"><bean:message key="common.label.createdate" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.username" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.location" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.period.type" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.period" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.target" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.actual" bundle="<%=interfaces%>"/></TH>
        <TH width="40px" align="center">#</TH>
    </TR>    
    <logic:present name="listDataDtl"> 
        <bean:define name="listDataDtl" id="beans" type="com.form.FBeans" />
        <%  int i = beans.getFirstRecord();%>
        <logic:iterate name="listDataDtl" id="bean" type="com.form.disability.FDataDtl">
        <tr style="cursor:pointer" class="<%=(i%2==0)?"content1":"content"%>">
                <td align="center"><span class="index"><%=i++%></span></td>
                <td align="center"><bean:write name="bean" property="createDate" /></td>
                <td align="left"><bean:write name="bean" property="fullName" /></td>
                <td align="left"><bean:write name="bean" property="location" /></td>
                <td align="left">
                    <logic:equal value="0" name="bean" property="period">
                        <bean:message key="common.label.month" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    <logic:equal value="1" name="bean" property="period">
                        <bean:message key="common.label.quarter" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    <logic:equal value="2" name="bean" property="period">
                        <bean:message key="common.label.year" bundle="<%=interfaces%>"/>
                    </logic:equal>
                <td align="center">
                     <logic:equal value="0" name="bean" property="period">
                        <bean:write name="bean" property="month" />/<bean:write name="bean" property="year" />
                    </logic:equal>
                    <logic:equal value="1" name="bean" property="period">
                        <bean:write name="bean" property="quarter" />/<bean:write name="bean" property="year" />
                    </logic:equal>
                    <logic:equal value="2" name="bean" property="period">
                        <bean:write name="bean" property="year" />
                    </logic:equal>
                </td>
                <td align="right">
                    <logic:equal value="0" name="bean" property="period">
                        <bean:write name="bean" property="targetM" />
                    </logic:equal>
                    <logic:equal value="1" name="bean" property="period">
                        <bean:write name="bean" property="targetQ" />
                    </logic:equal>
                    <logic:equal value="2" name="bean" property="period">
                        <bean:write name="bean" property="targetY" />
                    </logic:equal></td> 
                <td align="right">
                    <bean:write name="bean" property="actual" />
                </td>              
                <td>
                    <img style="border:0px" src="<%=contextPath%>/images/editdraft.png" 
                        title="<bean:message key="action.detail" bundle="<%=interfaces%>"/>" 
                        onClick="postAjax('kpi', 'div_value', anchor + ':_VALUE_VIEW_DETAIL:dtlId:<%=bean.getId()%>:inputType:0')">
                    <img style="border:0px" src="<%=contextPath%>/images/delete.png" 
                      title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                      onClick="javascript:if(messageDelete()){postAjax('kpi','div_value', anchor + ':_VALUE_DELETE:dtlId:<%=bean.getId()%>:inputType:0');}">
                </td>
            </tr>
        </logic:iterate>
    </logic:present>
</tbody>
</table>

<logic:present name="listDataDtl" >
<div>	
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listDataDtl"/>
        <jsp:param name="FORM" value="kpi"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="listId"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>	