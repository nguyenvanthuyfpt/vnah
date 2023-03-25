<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="doctype" method="post">
<table align="left">
<tr>
<td>

  <fieldset class="friendset" style>
    <legend class="lengend"><bean:message key="categorydoctype.doctype" bundle="<%=interfaces%>"/></legend>
    <table class="dataTable" align="left">
      <tr>
        <td><bean:message key="categorydoctype.doctype" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:select property="idDocType" styleClass="fieldSelect" onchange="javascript:postAjax('doctype','tdMainBody',anchor + ':_SELECT')">
            <html:option value="0"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="BDocType" property="idDocType" labelProperty="nameDocType"/>
          </html:select>     
        </td>
      </tr>
      <tr>
        <td><bean:message key="categorydoctype.name" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text property="nameDocType"  onblur="javascript: genCode(this.value,'codeDocType')" styleClass="fieldText" maxlength="200"/>        
        </td>
      </tr>
      <tr>
        <td><bean:message key="categorydoctype.code" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">       
          <html:text maxlength="250" property="codeDocType" styleClass="fieldText"/>
        </td>
      </tr>      
       <tr>
        <td><bean:message key="categorydoctype.discription" bundle="<%=interfaces%>"/>
        <td class="tdDataField">       
          <html:text property="descriptionDocType" styleClass="fieldText" maxlength="250"/>
        </td>
      </tr> 
      <tr>
        <td class="tdButton" colspan="2">
        <p align="center">
          <html:button property="_CREATE" onclick="javascript:postAjax('doctype','tdMainBody',anchor + ':_CREATE')" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_EDIT"  onclick="javascript:postAjax('doctype','tdMainBody',anchor + ':_EDIT')" styleClass="button">
            <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_DELETE"  onclick="if(messageDelete())postAjax('doctype','tdMainBody',anchor + ':_DELETE')" styleClass="button">
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
