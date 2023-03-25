  <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <table align="left">
<tr>
<td>
  <fieldset class="friendset" style>
    <legend class="lengend"><bean:message key="rm.category.form.caption" bundle="<%=interfaces%>"/></legend>
    <table  align="center">
      <tr>
        <td><bean:message key="rm.category.form.caption" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:select name="requireCat" property="catId" styleClass="fieldSelect"  onchange="javascript:postAjax('requireCat','divFormCatRequire',anchor + ':_PREPARE')">
            <html:option value="0"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="BCatRequires" property="catId" labelProperty="name"/>
          </html:select>
        </td>
      </tr>
      <tr>
        <td><bean:message key="rm.category.form.name.caption" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text name="requireCat" property="name" onblur="javascript:genCode(this.value,'code')" styleClass="fieldText" maxlength="250"/>        
        </td>
      </tr>
      <tr>
        <td><bean:message key="rm.category.form.code.caption" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:text name="requireCat" property="code" styleClass="fieldText" maxlength="250"/>
        </td>
      </tr>          
      
      <tr>
        <td class="tdButton" colspan="2">
        <p align="center">
          <html:button property="_CREATE" onclick="javascript:this.disabled=true;postAjax('requireCat','divFormCatRequire',anchor + ':_CREATE')" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_EDIT"  onclick="javascript:this.disabled=true;postAjax('requireCat','divFormCatRequire',anchor + ':_EDIT')" styleClass="button">
            <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_DELETE"  onclick="if(messageDelete()){this.disabled=true;postAjax('requireCat','divFormCatRequire',anchor + ':_DELETE')}" styleClass="button">
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