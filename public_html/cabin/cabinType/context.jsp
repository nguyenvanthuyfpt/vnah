<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
function inforCheck(){
if (document.cabinType.name.value == "") {
alert(unescape('<bean:message key="errors.cabinType.name.null" bundle="<%=interfaces%>"/>'));
return false;
}else{
return true;
}
}
</script>
<html:form action="cabinType" method="post">
<html:hidden name="cabinType" property="id" styleId="id" />
<html:hidden name="cabinType" property="type" styleId="type" />
<html:hidden name="cabinType" property="parentID" styleId="parentID" />
    <table cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td nowrap><bean:message key="title.cabin.label.name" bundle="<%=interfaces%>"/>:
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td>
            <html:text  name="cabinType"  property="name" style="width:100px;" styleClass="fieldText"/>        
        </td>
      </tr>
      <tr>
        <td colspan="2">
        <p align="center">
         <html:button property="_CREATE" onclick="javascript:if(inforCheck())post('cabinType',anchor + ':_CREATE')" styleClass="button">
                    <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                  </html:button>
            <logic:greaterThan name="cabinType" property="id" value="0">
                  <html:button property="_EDIT"  onclick="javascript:if(inforCheck())post('cabinType',anchor + ':_EDIT')" styleClass="button">
                    <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
                  </html:button>
            </logic:greaterThan>
        </p>
        </td>
      </tr>
      <tr>
      <td colspan="2" height="5px">
      </td>
      </tr>
    </table>
</html:form>



