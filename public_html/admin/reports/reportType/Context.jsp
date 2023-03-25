<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<html:form action="reportType" method="post">
<table align="left">
<tr>
<td>

  <fieldset class="friendset" style>
    <legend class="lengend"><bean:message key="categoryreportType.reportType" bundle="<%=interfaces%>"/></legend>
    <table class="dataTable" align="left">
      <tr>
        <td><bean:message key="categoryreportType.reportType" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:select property="id" styleClass="fieldSelect" onchange="javascript:postAjax('reportType','tdMainBody',anchor + ':_SELECT')">
            <html:option value="0"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="BReportType" property="id" labelProperty="name"/>
          </html:select>     
        </td>
      </tr>
      <tr>
        <td><bean:message key="categoryreportType.name" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text property="name" onblur="javascript: genCode(this.value,'code')" styleClass="fieldText"/>        
        </td>
      </tr>
      <tr>
        <td><bean:message key="categoryreportType.code" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">       
          <html:text property="code" styleClass="fieldText"/>
        </td>
      </tr>      
       <tr>
        <td><bean:message key="categoryreportType.discription" bundle="<%=interfaces%>"/>
        <td class="tdDataField">       
          <html:text property="description" styleClass="fieldText"/>
        </td>
      </tr> 
      <tr>
        <td class="tdButton" colspan="2">
        <p align="center">
          <html:button property="_CREATE" onclick="javascript:postAjax('reportType','tdMainBody',anchor + ':_CREATE')" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_EDIT"  onclick="javascript:postAjax('reportType','tdMainBody',anchor + ':_EDIT')" styleClass="button">
            <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_DELETE"  onclick="if(messageDelete())postAjax('reportType','tdMainBody',anchor + ':_DELETE')" styleClass="button">
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
