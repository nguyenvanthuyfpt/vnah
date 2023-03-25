<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="thePen" method="post">
<script type="text/javascript">


function createVia(){
postAjax('thePen','tdMainBody',anchor + ':_CREATE');
}


</script>
<table align="left">
<tr>
<td>
  <fieldset class="friendset" style>
    <legend class="lengend"><bean:message key="categorythePen.thePen" bundle="<%=interfaces%>"/></legend>
    <table  align="center">
      <tr>
        <td><bean:message key="categorythePen.thePen" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:select property="id" styleClass="fieldSelect"  onchange="javascript:postAjax('thePen','tdMainBody',anchor + ':_SELECT')">
            <html:option value="0"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="BThePen" property="id" labelProperty="name"/>
          </html:select>
        </td>
      </tr>
      <tr>
        <td><bean:message key="categorythePen.name" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text property="name" styleClass="fieldText" maxlength="250"/>        
        </td>
      </tr>
      <tr>
        <td class="tdButton" colspan="2">
        <p align="center">
          <html:button property="_CREATE" onclick="javascript:createVia();" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_EDIT"  onclick="javascript:postAjax('thePen','tdMainBody',anchor + ':_EDIT')" styleClass="button">
            <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_DELETE"  onclick="if(messageDelete())postAjax('thePen','tdMainBody',anchor + ':_DELETE')" styleClass="button">
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
