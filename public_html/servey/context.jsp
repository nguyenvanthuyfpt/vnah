<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="servey" method="post">
<table align="left">
<tr>
<td>
  <fieldset class="friendset" style>
    <legend class="lengend"><bean:message key="category.servey" bundle="<%=interfaces%>"/></legend>
    <table  align="center">
      <tr>
        <td><bean:message key="category.servey" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:select name="servey" property="serveyId" styleClass="fieldSelect"  onchange="javascript:postAjax('servey','tdMainBody',anchor + ':_SELECT')" style="width:200px;">
            <html:option value="0"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="BServey" property="serveyId" labelProperty="code"/>
          </html:select>
        </td>
        <td ><bean:message key="category.servey.orders" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField"><html:text name="servey" property="orders" styleClass="fieldText" style="width:20px;"/></td>
        
       </tr>
        <tr>
        
        <td><bean:message key="category.servey.name" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:text name="servey" property="name" onblur="javascript:genCode(this.value,'code')" styleClass="fieldText" style="width:200px;"/>        
        </td>
        <td><bean:message key="category.servey.code" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text name="servey" property="code" style="width:120px;"/>        
        </td>
      </tr>      
      <tr>
        <td><bean:message key="category.servey.fromDate" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text name="servey" property="fromDate" styleId="fromDate" styleClass="fieldText" style="width:100px;"/>
            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'fromDate','dd/mm/yyyy');return false;">
        </td>
        <td><bean:message key="category.servey.toDate" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text name="servey" property="toDate" styleId="toDate" styleClass="fieldText" style="width:100px;"/>  
            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'toDate','dd/mm/yyyy');return false;">
        </td>
      </tr>
      <tr>
        <td><bean:message key="category.servey.description" bundle="<%=interfaces%>"/></td>
        <td colspan="3" class="tdDataField"><html:text name="servey" property="description" styleClass="fieldText" style="width:400px;"/></td>
     </tr>
      <tr>
      </tr>
      <tr>
        <td><bean:message key="category.servey.active" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:select name="servey" property="active" styleClass="fieldSelect" style="width:100px;" >
            <html:option value="0"><bean:message key="category.servey.active.0" bundle="<%=interfaces%>"/></html:option>
            <html:option value="1"><bean:message key="category.servey.active.1" bundle="<%=interfaces%>"/></html:option>
          </html:select>
 
        </td>
      </tr>
      
      <tr>
        <td class="tdButton" colspan="4">
        <p align="center">
          <html:button property="_CREATE" onclick="javascript:postAjax('servey','tdMainBody',anchor + ':_CREATE')" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_EDIT"  onclick="javascript:postAjax('servey','tdMainBody',anchor + ':_EDIT')" styleClass="button">
            <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_DELETE"  onclick="if(messageDelete())postAjax('servey','tdMainBody',anchor + ':_DELETE')" styleClass="button">
            <bean:message key="danhmuc.button.xoa" bundle="<%=interfaces%>"/>
          </html:button>  
        </p>
       <p align="left">
          <html:errors property="alert"  bundle="<%=interfaces%>" />
        </p>
        </td>
      </tr>
    </table>
  </fieldset>
  </td>
</tr>
</table>
</html:form>
