<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="form" method="post">
<table align="left">
<tr>
<td>
  <fieldset class="friendset" style>
    <legend class="lengend"><bean:message key="categoryform.form" bundle="<%=interfaces%>"/></legend>
    <table  align="center">
      <tr>
        <td><bean:message key="categoryform.form" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:select property="id" styleClass="fieldSelect"  onchange="javascript:postAjax('form','tdMainBody',anchor + ':_SELECT')">
            <html:option value="0"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="BForm" property="id" labelProperty="name"/>
          </html:select>
        </td>
      </tr>
      <tr>
        <td><bean:message key="categoryform.name" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text property="name" onblur="javascript:genCode(this.value,'code')" styleClass="fieldText" maxlength="250"/>        
        </td>
      </tr>
      <tr>
        <td><bean:message key="categoryform.code" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:text property="code" styleClass="fieldText" maxlength="250"/>
        </td>
      </tr>          
      <tr>
        <td><bean:message key="categoryform.discription" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text property="description" styleClass="fieldText" maxlength="250"/>  
                        
        </td>
      </tr>
      <tr>
        <td class="tdButton" colspan="2">
        <p align="center">
          <html:button property="_CREATE" onclick="javascript:postAjax('form','tdMainBody',anchor + ':_CREATE')" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_EDIT"  onclick="javascript:postAjax('form','tdMainBody',anchor + ':_EDIT')" styleClass="button">
            <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_DELETE"  onclick="if(messageDelete())postAjax('form','tdMainBody',anchor + ':_DELETE')" styleClass="button">
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
