<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="dataBaseServer" method="post">
<table align="left">
<tr>
<td>
  <fieldset class="friendset" style>
    <legend class="lengend"><bean:message key="category.dataBaseServer" bundle="<%=interfaces%>"/></legend>
    <table  align="center">
      <tr>
        <td><bean:message key="category.dataBaseServer" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:select name="dataBaseServer" property="id" styleClass="fieldSelect"  onchange="javascript:postAjax('dataBaseServer','tdMainBody',anchor + ':_SELECT')" style="width:200px;">
            <html:option value="0"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="BDataBaseServer" property="id" labelProperty="url"/>
          </html:select>
        </td>
      </tr>
      <tr>
        <td><bean:message key="category.dataBaseServer.url" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:textarea name="dataBaseServer" property="url" style="width:200px;"/>        
        </td>
      </tr>
      <tr>
        <td><bean:message key="category.dataBaseServer.selectSql" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:text name="dataBaseServer" property="selectSql" styleClass="fieldText" style="width:200px;" size="250"/>
        </td>
      </tr>          
      <tr>
        <td><bean:message key="category.dataBaseServer.description" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text name="dataBaseServer" property="description" styleClass="fieldText" style="width:200px;" size="250"/>  
        </td>
      </tr>
      <tr>
        <td class="tdButton" colspan="2">
        <p align="center">
          <html:button property="_CREATE" onclick="javascript:postAjax('dataBaseServer','tdMainBody',anchor + ':_CREATE')" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_EDIT"  onclick="javascript:postAjax('dataBaseServer','tdMainBody',anchor + ':_EDIT')" styleClass="button">
            <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_DELETE"  onclick="if(messageDelete())postAjax('dataBaseServer','tdMainBody',anchor + ':_DELETE')" styleClass="button">
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
