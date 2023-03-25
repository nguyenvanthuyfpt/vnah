<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%" noborder style="border-collapse: collapse"  cellpadding="0" cellspacing="0">
<tr><td  class="title-01" nowrap>
<a href="#" class="li-title-foryou">
  <bean:message key="foryou.hearde.form.add" bundle="<%=interfaces%>"/>
</a>
</td>
</tr>
</table>
<br>
<html:form action="forYou" method="post">
<table class="tableForm" style="border-collapse: collapse" cellpadding="0" align="left">
    <tr>
        <td  ><bean:message key="forYou.label.boss" bundle="<%=interfaces%>"/>:</td>
        <td  ><b><bean:write name="forYou" property="boss" /></b></td>
    </tr>
    <tr>
        <td  ><bean:message key="forYou.label.forYou" bundle="<%=interfaces%>"/>:</td>
        <td  >
          <html:select property="userIdTo" styleClass="fieldSelect" onchange="javascript:postAjax('doctype','tdMainBody',anchor + ':_SELECT')">
                <html:options collection="BUsers" property="id" labelProperty="fullName"/>
          </html:select>     
        </td>
    </tr>
    <tr>
        <td  ><bean:message key="forYou.label.problem" bundle="<%=interfaces%>"/>:</td>
        <td  ><html:textarea  name="forYou" property="problem"  /></td>
    </tr>
    <tr>
        <td  ><bean:message key="forYou.label.dateFrom" bundle="<%=interfaces%>"/>:</td>
        <td  >
        <input type="text" name="dateFrom" id="dateFrom" value="<bean:write name="forYou" property="dateFrom"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'dateFrom','dd/mm/yyyy');return false;"> <bean:message key="forYou.label.dateTo" bundle="<%=interfaces%>"/>:
        <input type="text" name="dateTo" id="dateTo" value="<bean:write name="forYou" property="dateTo"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'dateTo','dd/mm/yyyy');return false;">
        </td>
    </tr>
     <tr>
        <td  ><bean:message key="forYou.label.state" bundle="<%=interfaces%>"/>:</td>
        <td  >
          <html:select property="userIdTo" styleClass="fieldSelect"  onchange="javascript:postAjax('doctype','tdMainBody',anchor + ':_SELECT')">
                <html:option value="0"><bean:message key="forYou.label.state.1" bundle="<%=interfaces%>"/></html:option>
                <html:option value="1"><bean:message key="forYou.label.state.2" bundle="<%=interfaces%>"/></html:option>
          </html:select>     
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center"  > 
                <html:button property="_CREATE" onclick="javascript:postAjax('forYou','thongbao',anchor + ':_CREATE')" styleClass="button">
               <bean:message key="forYou.cmd.add" bundle="<%=interfaces%>"/>
                </html:button>        
        </td>
    </tr>
    <tr>
    <td colspan="2">
        <div id="thongbao">
        <jsp:include page="/admin/alert.jsp" />
    </div>
    </td>
    </tr>
</table>

<html:hidden name="forYou" property="userIdFrom" />


</html:form>
