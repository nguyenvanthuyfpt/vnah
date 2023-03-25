<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="status" method="post">
<table align="left">
<tr>
<td>

  <fieldset class="friendset" style>
    <legend class="lengend"><bean:message key="categorystatus.status" bundle="<%=interfaces%>"/></legend>
    <table  align="center">
      <tr>
        <td><bean:message key="categorystatus.status" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:select property="id" styleClass="fieldSelect"  onchange="javascript:postAjax('status','tdMainBody',anchor + ':_SELECT')">
            <html:option value="0"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
            <logic:present name="BStatus" >
            <logic:iterate name="BStatus" id="bean" type="com.form.admin.doc.category.status.FStatus">
                <%String statusId=bean.getId()+"";%>
                <html:option value="<%=statusId%>">
                        <bean:write name="bean" property="name"/>
                        <logic:notEmpty name="bean" property="description" >
                             (<bean:write name="bean" property="description"/>)
                        </logic:notEmpty>
                </html:option>
            </logic:iterate>
            </logic:present>
          </html:select>
        </td>
      </tr>
      <tr>
        <td><bean:message key="categorystatus.name" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text property="name" onblur="javascript:genCode(this.value,'code')" styleClass="fieldText" maxlength="250"/>        
        </td>
      </tr>
      <tr>
        <td><bean:message key="categorystatus.code" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:text property="code" styleClass="fieldText" maxlength="250"/>
        </td>
      </tr>
        <tr>
        <td><bean:message key="categorystatus.color" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:text property="color" styleId="color" styleClass="fieldText"  maxlength="250"/>
          <img src="<%=contextPath%>/images/color_picker.png" onclick="javascript:openWindow('status',anchor + ':_COLOR:formName:status')">
        </td>
      </tr>    
       <tr>
        <td><bean:message key="categorystatus.group" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:select property="group" styleClass="fieldSelect" >
            <html:option value="0"><bean:message key="categorystatus.group.0" bundle="<%=interfaces%>"/></html:option>
            <html:option value="1"><bean:message key="categorystatus.group.1" bundle="<%=interfaces%>"/></html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <td><bean:message key="categorystatus.discription" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text property="description" styleClass="fieldText" maxlength="250"/>  
                        
        </td>
      </tr>
      <tr>
        <td class="tdButton" colspan="2">
        <p align="center">
          <html:button property="_CREATE" onclick="javascript:postAjax('status','tdMainBody',anchor + ':_CREATE')" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_EDIT"  onclick="javascript:postAjax('status','tdMainBody',anchor + ':_EDIT')" styleClass="button">
            <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_DELETE"  onclick="if(messageDelete())postAjax('status','tdMainBody',anchor + ':_DELETE')" styleClass="button">
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
