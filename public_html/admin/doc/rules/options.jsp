<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<html:hidden name="docrule" property="direct" />
<html:hidden name="docrule" property="excuteGroup" />
<html:hidden name="docrule" property="sendSms" />
<html:hidden name="docrule" property="reply" />
<html:hidden name="docrule" property="reviewfile" />
<table width="100%"  border="0" cellpadding="0" cellspacing="0">         
    <html:hidden name="docrule" property="index"/>
    <tr >
        <td class="" height="26px"><Strong><bean:message key="problem.dep.caption" bundle="<%=interfaces%>"/></strong></td>
        <td >
            <html:select styleClass="inputbox"  name="docrule" property="departmentID" onchange="javascript:postAjax('docrule','ruleUsers',anchor+':_SHOW');messageImg('ruleUsers');"> 
             <logic:present name="BDepartments">
                    <html:options collection="BDepartments" property="id" labelProperty="name"/>          
                </logic:present>
            </html:select> 
        </td>            
    </tr>    
    <tr>
        <td colspan="2">
            <strong><bean:message key="docs.tab.header.userName" bundle="<%=interfaces%>"/></strong>
            <div>
                  <bean:message key="rules.assignEmp.caption" bundle="<%=interfaces%>"/> <input type="radio" name="radioEmp" value="0" checked/>
                  <bean:message key="rules.recieverEmp.caption" bundle="<%=interfaces%>"/> <input type="radio" name="radioEmp" value="1"/>
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="2" id="ruleUsers">
        <jsp:include page="/admin/doc/rules/empsList.jsp"/>    
        </td>
    </tr> 
</table>

 