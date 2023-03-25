<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/styles/tools.css">
<html><head><title>Color Picker</title><meta name="description" content="Web Master Tools - Color Picker"><meta name="keywords" content="color picker"><meta name="robots" content="all"><meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><meta http-equiv="Content-Style-Type" content="text/css"><meta http-equiv="content-language" content="en">
<html:form action="transfer" method="post">
<table align="left">
<tr>
<td>

  <fieldset class="friendset" style>
    <legend class="lengend"><bean:message key="category.transfer" bundle="<%=interfaces%>"/></legend>
    <table  align="center">
      <tr>
        <td><bean:message key="category.transfer" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:select property="id" styleClass="fieldSelect"  onchange="javascript:postAjax('transfer','tdMainBody',anchor + ':_SELECT')">
            <html:option value="-1"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="BTransfer" property="id" labelProperty="name"/>
          </html:select>
        </td>
      </tr>
      <tr>
        <td><bean:message key="category.transfer.name" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:text property="name" styleClass="fieldText" maxlength="250"/>
        </td>
      </tr>
      <tr>
        <td><bean:message key="category.transfer.color" bundle="<%=interfaces%>"/>
        </td>
        <td class="tdDataField">
          <html:text property="color" styleClass="fieldText" maxlength="250"/>
                    <img src="<%=contextPath%>/images/color_picker.png" onclick="javascript:openWindow('transfer',anchor + ':_COLOR:formName:transfer')">
        </td>
      </tr>   
      <tr>
        <td><bean:message key="category.transfer.discription" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text property="description" styleClass="fieldText" maxlength="250"/>  
                        
        </td>
      </tr>
      <tr>
        <td class="tdButton" colspan="2">
        <p align="center">
          <html:button property="_CREATE" onclick="javascript:postAjax('transfer','tdMainBody',anchor + ':_CREATE')" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_EDIT"  onclick="javascript:postAjax('transfer','tdMainBody',anchor + ':_EDIT')" styleClass="button">
            <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_DELETE"  onclick="if(messageDelete())postAjax('transfer','tdMainBody',anchor + ':_DELETE')" styleClass="button">
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
</body></html>