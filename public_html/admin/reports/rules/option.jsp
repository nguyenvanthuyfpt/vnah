<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<table width="100%" class="adminform" border="0" cellpadding="0" cellspacing="0" align="center">    
      <TR>
            <TH  colspan="2" align="left"><bean:message key="problem.emp.infor" bundle="<%=interfaces%>"/></TH>               
    </TR>
    <html:hidden name="reportRule" property="index"/>
    <tr >
        <td class="" height="26px" width="60px"><Strong><bean:message key="problem.dep.caption" bundle="<%=interfaces%>"/></strong></td>
        <td >
            <html:select styleClass="inputbox"  name="reportRule" property="departmentID" onchange="javascript:postAjax('reportRule','ruleUsers',anchor+':_SHOW');messageImg('ruleUsers');"> 
                <logic:present name="BDepartments">
                    <html:options collection="BDepartments" property="id" labelProperty="name"/>          
                </logic:present>
            </html:select>             
        </td>            
    </tr>
    <tr>
        <td><bean:message key="rules.assignEmp.caption" bundle="<%=interfaces%>"/> </td>
        <td>
                <bean:define name="reportRule" property="index" id="index" type="java.lang.Integer"/>          
            <input type="radio"  name="radioEmp" id="radioEmp" value="0" <%=index.intValue()==0?"checked":""%>/>
            <bean:message key="rules.recieverEmp.caption" bundle="<%=interfaces%>"/> <input type="radio" name="radioEmp" id="radioEmp" value="1" <%=index.intValue()>0?"checked":""%>/>
        </td>
    </tr>
    <tr>
        <td colspan="2" id="ruleUsers">
        <jsp:include page="/admin/reports/rules/empsList.jsp"/>    
        </td>
    </tr> 
</table>

  
 