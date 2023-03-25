<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="mailAccount" method="post">
<table align="left">
<tr>
<td>
  <fieldset class="friendset" style>
    <legend class="lengend"><bean:message key="category.mailAccount" bundle="<%=interfaces%>"/></legend>
    <table  align="center">
      <tr>
        <td><bean:message key="category.mailAccount" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:select name="mailAccount" property="id" styleClass="fieldSelect"  onchange="javascript:postAjax('mailAccount','tdMainBody',anchor + ':_SELECT')" style="width:200px;">
            <html:option value="0"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="BMailAccount" property="id" labelProperty="userMail"/>
          </html:select>
        </td>
      </tr>
      <tr>
        <td><bean:message key="category.mailUserId" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:select name="mailAccount" property="userId" styleClass="fieldSelect"  >
            <logic:present name="BUsers">  
            <html:options collection="BUsers" property="id" labelProperty="fullName"/>
          </logic:present>
          </html:select>
        </td>
      </tr>
      <tr>
        <td><bean:message key="category.mailAccount.userMail" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text name="mailAccount" property="userMail" styleClass="fieldText" style="width:200px;" size="250"/>        
        </td>
      </tr>
      <tr>
        <td><bean:message key="category.mailAccount.passMail" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:password name="mailAccount" property="passMail" styleClass="fieldText" style="width:200px;"/>
        </td>
      </tr>          
      <tr>
        <td><bean:message key="category.mailAccount.serverMail" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
                <html:text name="mailAccount" property="serverMail" style="width:200px" />
        </td>
      </tr>
      
      <tr>
        <td><bean:message key="category.mailAccount.sercure" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
                <html:checkbox name="mailAccount" property="sercure" value="1" />
        </td>
      </tr>
      
        <tr>
            <td><bean:message key="category.mailAccount.status" bundle="<%=interfaces%>"/></td>
            <td class="tdDataField">
                    <html:select name="mailAccount" property="status" styleClass="fieldSelect" style="width:200px;"  >
                        <html:option value="0"><bean:message key="mailAccount.status.0" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="1"><bean:message key="mailAccount.status.1" bundle="<%=interfaces%>"/></html:option>
                    </html:select>
            </td>
      </tr>
      <tr>
        <td class="tdButton" colspan="2">
        <p align="center">
          <html:button property="_CREATE" onclick="javascript:postAjax('mailAccount','tdMainBody',anchor + ':_CREATE')" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_EDIT"  onclick="javascript:postAjax('mailAccount','tdMainBody',anchor + ':_EDIT')" styleClass="button">
            <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_DELETE"  onclick="if(messageDelete())postAjax('mailAccount','tdMainBody',anchor + ':_DELETE')" styleClass="button">
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
