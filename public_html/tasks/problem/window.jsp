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
              <img src="<%=contextPath%>/images/close.png" border="0" style="cursor: pointer;" onclick="getObj('hiddenSearch').style.display='block';closeWindow()"  />
           </TD>
        </TR>    
     </table>
 </td>
 </tr>
    
  <tr>
       <td style="pading-top:10px;padding-bottom:10px" bgcolor="#F0F5E5">
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td colspan="4" height="8px"></td>
                </tr>
                <tr class="boderProblemD">
                    <td width="25%" height="22px" nowrap style="padding-left:6px"><strong> <bean:message key="problem.categories" bundle="<%=interfaces%>"/></strong></td>
                    <td><bean:write name="problem" property="categoriesName"/></td>
                     <td width="10%" nowrap><strong><bean:message key="problem.title" bundle="<%=interfaces%>"/></strong></td>
                     <td width="40%"><bean:write name="problem" property="title"/></td>
                </tr>
               
                 <tr class="boderProblemD">
                    <td style="padding-left:6px" height="22px"><strong><bean:message key="problem.fromdate" bundle="<%=interfaces%>"/></strong></td>
                    <td><bean:write name="problem" property="fromDate"/></td>
                     <td><strong><bean:message key="problem.todate" bundle="<%=interfaces%>"/></strong></td>
                    <td><bean:write name="problem" property="toDate"/></td>
                </tr>
                <tr>
                    <td style="padding-left:6px" height="24px"><strong><bean:message key="problem.incharge" bundle="<%=interfaces%>"/></strong></td>
                    <td colspan="3"><bean:write name="problem" property="inchargeName"/></td>
                </tr>
                <tr class="boderProblemD">
                    <td style="padding-left:6px" height="22px"><strong><bean:message key="problem.task" bundle="<%=interfaces%>"/></strong></td>
                    <td colspan="3"><bean:write name="problem" property="problem"/></td>
                    
                </tr>
                
            </table>
       </td>
  </tr>    
        <logic:present name="userAssigns">
<tr>            
    <td bgcolor="#F0F5E5"> 
          <table class="tableForm" id="table6" style="border-collapse: collapse" cellpadding="0" width="100%">
           <tr>
                <td colspan="5" class="title"><bean:message key="report.list.worker" bundle="<%=interfaces%>"/></td>
           </tr> 
            <tr>
                 <th class="tdheader" width="5%"><bean:message key="problem.stt" bundle="<%=interfaces%>"/></th>
                 <th class="tdheader"><bean:message key="problem.usersAssign" bundle="<%=interfaces%>"/></th>
                 <th class="tdheader"><bean:message key="problem.dateAssign.caption" bundle="<%=interfaces%>"/></th>
                 <th class="tdheader"><bean:message key="problem.complate" bundle="<%=interfaces%>"/></th>
                 <th class="tdheader"><bean:message key="problem.report" bundle="<%=interfaces%>"/></th>
            </tr>                    
            
            <bean:define name="userAssigns" id="beans" type="com.form.FBeans"/>
            <%  int i = beans.getFirstRecord();%>
              <logic:iterate name="userAssigns" id="bean" type="com.form.tasks.problem.FProblem">                      
                <tr>
                    <td class="<%=(i%2>0?"tdcontent1":"tdcontent")%>"><%=++i%></td>
                    <td class="<%=(i%2>0?"tdcontent":"tdcontent1")%>"><bean:write name="bean" property="workerName"/></td>
                    <td class="<%=(i%2>0?"tdcontent":"tdcontent1")%>"><bean:write name="bean" property="timeCreateAssign"/></td>
                    <td class="<%=(i%2>0?"tdcontent":"tdcontent1")%>"><bean:write name="bean" property="complete"/> %</td>
                    <td class="<%=(i%2>0?"tdcontent":"tdcontent1")%>" align="center"><a href="javascript:postAjax('problem','listReport',anchor + ':_PUT:assignId:<%=bean.getAssignId()%>:workerName:<%=bean.getWorkerName()%>')"> <bean:message key="problem.report" bundle="<%=interfaces%>"/></a></td>
                </tr>
             </logic:iterate>   
            
        </table>
    </td>
</tr>
</logic:present>
<tr>
        <td id="listReport" bgcolor="#F0F5E5"><jsp:include page="/tasks/problem/reportList.jsp"/></td>
</tr>

</table> 
    
  
 