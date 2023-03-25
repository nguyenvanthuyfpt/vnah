<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="secure" method="post">
<table align="left">
<tr>
<td>

  <fieldset class="friendset" style>
    <legend class="lengend"><bean:message key="categorysecure.secure" bundle="<%=interfaces%>"/></legend>
    <table  align="center">
      <tr>
        <td><bean:message key="categorysecure.secure" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:select property="id" styleClass="fieldSelect"  onchange="javascript:postAjax('secure','tdMainBody',anchor + ':_SELECT')">
            <html:option value="0"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="BSecure" property="id" labelProperty="name"/>
          </html:select>
        </td>
      </tr>
      
      <tr>
        <td><bean:message key="categorysecure.name" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text property="name" onblur="javascript:genCode(this.value,'code')" styleClass="fieldText" maxlength="250"/>        
        </td>
      </tr>
      <tr>
        <td><bean:message key="categorysecure.code" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:text property="code" styleClass="fieldText" maxlength="250"/>
        </td>
      </tr>          
      <tr>
        <td><bean:message key="categorysecure.discription" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text property="description" styleClass="fieldText" maxlength="250"/>  
                        
        </td>
      </tr>
      <tr>
        <td class="tdButton" colspan="2">
        <p align="center">
          <html:button property="_CREATE" onclick="javascript:postAjax('secure','tdMainBody',anchor + ':_CREATE')" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_EDIT"  onclick="javascript:postAjax('secure','tdMainBody',anchor + ':_EDIT')" styleClass="button">
            <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_DELETE"  onclick="if(messageDelete())postAjax('secure','tdMainBody',anchor + ':_DELETE')" styleClass="button">
            <bean:message key="danhmuc.button.xoa" bundle="<%=interfaces%>"/>
          </html:button>  
        </p>
        <p align="left">
          <html:errors property="SecureErrors"  bundle="<%=interfaces%>" />
        </p>
        </td>
      </tr>
    </table>
  </fieldset>
  </td>
</tr>
</table>
</html:form>
