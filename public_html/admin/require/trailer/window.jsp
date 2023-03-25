<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<table width="550px" style="border: 1px solid #77BBDD;z-index: 20;background-color:white" cellpadding="0" cellspacing="0" align="center">
<tr>
<td bgcolor="#F0F5E5" valign="top" align="center" style="cursor: move;"  onmousedown="makeObjectToDrag('winPopup')"> 
    <TABLE cellSpacing=0 cellPadding=0 width="100%" border="0">  
        <TR >                       
            <TD   onmousedown="makeObjectToDrag('winPopup')" valign="middle" class="titleOpen" background="images/bg_t.gif" align="left" style="BORDER-BOTTOM: #8CACBB 1px solid;background-color:#DEE7EC;padding-left:4px">
              <span style="color: #FFFFFF;font-weight: bold;padding-top: 4px;"> <bean:message key="problem.infor.caption" bundle="<%=interfaces%>"/></span>
            </TD>
             <TD  onmousedown="makeObjectToDrag('winPopup')"  height="22px" background="images/bg_t.gif" align="right" style="padding-right:1px">
              <img src="images/close.png" border="0" style="cursor: pointer;" onclick="getObj('formEdit').style.display='block';getObj('formList').style.display='block';closeWindow()"  />
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
       <html:form action="docRulesOpen" method="POST">
            <table width="98%" class="adminformEdit" border="0" cellpadding="0" cellspacing="0" align="center">    
      <TR>
            <TH  colspan="2" align="left"><bean:message key="problem.emp.infor" bundle="<%=interfaces%>"/></TH>               
    </TR>
    <html:hidden name="docRulesOpen" property="index"/>
    <tr >
        <td class="" height="26px"><bean:message key="problem.dep.caption" bundle="<%=interfaces%>"/></td>
        <td >
            <html:select styleClass="inputbox"  name="docRulesOpen" property="departmentID" onchange="javascript:postAjax('docRulesOpen','ruleUsers',anchor+':_SHOW:departmentID:'+ this.value);"> 
             <logic:present name="BDepartments">
                    <html:options collection="BDepartments" property="id" labelProperty="name"/>          
                </logic:present>
            </html:select> 
            <bean:define name="docRulesOpen" property="index" id="index" type="java.lang.Integer"/>          
            <bean:message key="rules.assignEmp.caption" bundle="<%=interfaces%>"/> <input type="radio" name="radioEmp" value="0" <%=index.intValue()==0?"checked":""%>/>
            <bean:message key="rules.recieverEmp.caption" bundle="<%=interfaces%>"/> <input type="radio" name="radioEmp" value="1" <%=index.intValue()>0?"checked":""%>/>
        </td>            
    </tr>
      <tr>
        <td colspan="2" id="ruleUsers">
        <jsp:include page="/admin/doc/rules/empsList.jsp"/>    
        </td>
    </tr> 
</table>
</html:form>   
       </td>
  </tr>     
</table> 
 
  
 