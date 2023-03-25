<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 

<table width="550px" style="border: 1px solid #77BBDD;z-index: 20;background-color:white" cellpadding="0" cellspacing="0" align="center">
<tr>
<td bgcolor="#F0F5E5" valign="top" align="center" style="cursor: move;"  onmousedown="makeObjectToDrag('winPopup')"> 
    <TABLE cellSpacing=0 cellPadding=0 width="100%" border="0">  
        <TR >                       
            <TD   onmousedown="makeObjectToDrag('winPopup')" valign="middle" class="titleOpen" background="<%=contextPath%>/images/bg_t.gif" align="left" style="BORDER-BOTTOM: #8CACBB 1px solid;background-color:#DEE7EC;padding-left:4px">
              <span style="color: #FFFFFF;font-weight: bold;padding-top: 4px;"> <bean:message key="problem.infor.caption" bundle="<%=interfaces%>"/></span>
            </TD>
             <TD  onmousedown="makeObjectToDrag('winPopup')"  height="22px" background="<%=contextPath%>/images/bg_t.gif" align="right" style="padding-right:1px">
              <img src="<%=contextPath%>/images/close.png" border="0" style="cursor: pointer;" onclick="getObj('formEdit').style.display='block';getObj('formList').style.display='block';closeWindow()"  />
           </TD>
        </TR>    
     </table>
 </td>
 </tr>
  <tr>
    <td bgcolor="#F0F5E5"><br> </td>
  </tr>  
  <tr>
       <td style="padding-bottom:10px" bgcolor="#F0F5E5">
       <html:form action="rulesOpen" method="POST">
            <table width="98%" class="adminformEdit" border="0" cellpadding="0" cellspacing="0" align="center">    
      <TR>
            <TH  colspan="2" align="left"><bean:message key="problem.emp.infor" bundle="<%=interfaces%>"/></TH>               
    </TR>
    <html:hidden name="rulesOpen" property="index"/>
    <tr >
        <td class="" height="26px"><bean:message key="problem.dep.caption" bundle="<%=interfaces%>"/></td>
        <td >
            <html:select styleClass="inputbox"  name="rules" property="departmentID" onchange="javascript:postAjax('rulesOpen','ruleUsers',anchor+':_SHOW:departmentID:'+ this.value);"> 
            <%if(me.getDepartmentID()==0){%>
            <html:option value="0"><bean:message key="users.edit.notindepartment" bundle="<%=interfaces%>"/></html:option>
            <%}%>
            <logic:present name="BDepartments">
            <logic:iterate name="BDepartments" id="bean" type="com.form.admin.departments.FDepartment">                
            <option value="<bean:write name="bean" property="id" />" selected ><strong> <bean:write name="bean" property="name"/></strong> </option>
            
            </logic:iterate>
            </logic:present>
            </html:select> 
            <bean:define name="rulesOpen" property="index" id="index" type="java.lang.Integer"/>          
            <bean:message key="rules.assignEmp.caption" bundle="<%=interfaces%>"/> <input type="radio" name="radioEmp" value="0" <%=index.intValue()==0?"checked":""%>/>
            <bean:message key="rules.recieverEmp.caption" bundle="<%=interfaces%>"/> <input type="radio" name="radioEmp" value="1" <%=index.intValue()>0?"checked":""%>/>
        </td>            
    </tr>
      <tr>
        <td colspan="2" id="ruleUsers">
        <jsp:include page="/admin/tasks/rules/empsList.jsp"/>    
        </td>
    </tr> 
</table>
</html:form>   
       </td>
  </tr>     
</table> 
 
  
 