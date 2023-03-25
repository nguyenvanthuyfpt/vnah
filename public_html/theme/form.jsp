<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table align="left">
<tr>
<td>
  <fieldset class="friendset" style>
    <legend class="lengend"><bean:message key="theme.category.form.caption" bundle="<%=interfaces%>"/></legend>
    <table  align="center">
      <tr>
        <td><bean:message key="theme.category.form.caption" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:select name="theme" property="id" styleClass="fieldSelect"  onchange="javascript:postAjax('theme','divFormTheme',anchor + ':_PREPARE')">
            <html:option value="0"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="BThemes" property="id" labelProperty="title"/>
          </html:select>
        </td>
      </tr>
      <tr>
        <td><bean:message key="theme.category.form.title.caption" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text name="theme" property="title" styleClass="fieldText" maxlength="250"/>        
        </td>
      </tr>
      <tr>
        <td><bean:message key="theme.category.form.pathfolder.caption" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:text name="theme" property="pathImages" styleClass="fieldText" maxlength="250"/>
        </td>
      </tr>          
      <tr>
        <td><bean:message key="theme.category.form.pathStyle.caption" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:text name="theme" property="pathStyle" styleClass="fieldText" maxlength="250"/>
        </td>
      </tr>          
      
      <tr>
        <td><bean:message key="theme.category.form.active.caption" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
                <html:select name="theme" property="active" styleId="active" styleClass="fieldSelect"  >
                <html:option value="0"> <bean:message key="theme.category.form.active.0.caption" bundle="<%=interfaces%>"/> </html:option>        
                <html:option value="1"> <bean:message key="theme.category.form.active.1.caption" bundle="<%=interfaces%>"/> </html:option>        
                </html:select>
        </td>
      </tr>   
      <tr>
        <td class="tdButton" colspan="2">
        <p align="center">
          <html:button property="_CREATE" onclick="javascript:this.disabled=true;postAjax('theme','divFormTheme',anchor + ':_CREATE')" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_EDIT"  onclick="javascript:this.disabled=true;postAjax('theme','divFormTheme',anchor + ':_EDIT')" styleClass="button">
            <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_DELETE"  onclick="if(messageDelete()){this.disabled=true;postAjax('theme','divFormTheme',anchor + ':_DELETE')}" styleClass="button">
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