<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
function inputInToCategery(){
    if(getObj('cabinType_id').value>0){
        postAjax('cabinInput','right',anchor + ':_INPUT_OK');
    }else{
        alert(<bean:message key="alert.not.enough.input.cabin" bundle="<%=interfaces%>"/>);
    }
}
</script>
<html:form action="reports" method="post" >
<html:hidden name="reports" property="type" />
<table width="100%" cellpadding="0" class="TITLECLASS" cellspacing="0"   >
    <tr>
        <td class="LEFT" width="98%" align="left">
            <Strong>
                <logic:equal name="reports" property="type" value="1">
                <bean:message key="menu.top.recvReport.caption" bundle="<%=interfaces%>"/>
                </logic:equal>
                <logic:notEqual name="reports" property="type" value="1">
                <bean:message key="menu.top.report.caption" bundle="<%=interfaces%>"/>
                </logic:notEqual>
<bean:define name="BReport" id="beans" type="com.form.FBeans"/>
(<bean:write name="beans" property="totalRows"/>)
            </Strong>
        <td  class="RIGHT">
        </td>
    </tr>
</table>
<div class="content-calendar">
<div class="toolCmd" id="toolSearch"  style="line-height:18px;height:25px;padding-right:8px;margin-right:8px;text-align:left;padding-top:3px"> 
<table width="100%" cellpadding="0" cellspacing="0" >               
<tr>
<td width="150px">
<table class="tableClassSearch" cellpadding="0" border="0" cellspacing="0" >
        <tr>
            <td><input class="inputClassSearch" type="text" onkeydown="if(event.keyCode==13){post('reports',anchor + ':_SEARCH');messageImg('right');return false;}" onfocus="javascript:if(this.value == '') this.value='';" onblur="javascript:if(this.value=='') this.value='';" name="nameSearch" id="name" value=""/>
            </td>
            <td class="imgClassSearch" height="18px" width="20px" onclick="post('reports',anchor + ':_SEARCH');messageImg('right')" >&nbsp;</td>
        </tr>
</table>    

</td>
<td align="left">
            <html:select name="reports" property="reportType_id" onchange="javascript:post('reports',anchor + ':_SEARCH')" style="width:150px;" >
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <logic:present name="BReportTypes">
                 <html:options collection="BReportTypes" property="id" labelProperty="name"/>
                 </logic:present>
            </html:select>
            
            <logic:equal name="reports" property="type" value="1">
                <html:select name="reports" property="userId" onchange="javascript:post('reports',anchor + ':_SEARCH')" style="width:150px;" >
                    <logic:present name="BUsers">
                     <html:options collection="BUsers" property="id" labelProperty="fullName"/>
                     </logic:present>
                </html:select>
            </logic:equal>
            
            <bean:message key="categories.list.from.dateTime" bundle="<%=interfaces%>"/>
            <input type="text" style="width:70px;" name="timeCreateForm" id="timeCreateForm" value="" onkeydown="if(event.keyCode==13){post('reports',anchor + ':_SEARCH');messageImg('right');return false;}" /><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'timeCreateForm','dd/mm/yyyy');return false;" >
            
            <bean:message key="problem.todate" bundle="<%=interfaces%>"/>
            <input type="text" style="width:70px;" name="timeCreateTo" id="timeCreateTo" value="" onkeydown="if(event.keyCode==13){post('reports',anchor + ':_SEARCH');messageImg('right');return false;}"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'timeCreateTo','dd/mm/yyyy');return false;">

</td>
</tr>
</table>
</div>
<div class="padding-content" style="margin-right:5px">
        <div class="tabmenu" id="container-1" >
            <div id="fragment-1">
               <div class="content-calendar" id="MainMessage">      
                    <jsp:include page="/report/list.jsp" />  
               </div>
            </div>
        </div>
</div>
</div>
</html:form>

