<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="trailerStore" method="POST">
<table width="100%" class="adminform" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3">
            <html:select styleClass="inputbox" name="trailerStore" property="workflowId"  style="width:107px;" onchange="postAjax('trailerStore','MainDocRules',anchor + ':_WORKFLOW');messageImg('MainDocRules');">
            <html:options collection="BWorkflows" property="workflowId" labelProperty="title"/>
            </html:select>
    </td>
  </tr>
  
  <tr>
    <td width="45%">
             <html:select styleClass="inputbox"  name="trailerStore" property="departmentIdBoss" style="width:150px" onchange="javascript:postAjax('trailerStore','MainDocRules',anchor+':_SHOW_USER_SEND');"> 
             <html:option value="0"><bean:message key="status.all" bundle="<%=interfaces%>"/></html:option>
             <logic:present name="BDepartments">
                    <html:options collection="BDepartments" property="id" labelProperty="name"/>          
                </logic:present>
            </html:select> 
            <span id="userSendId">
                        <jsp:include page="/admin/doc/rules/trailerStore/usersend.jsp" />                
            </span>
            <input type="text" name="content" id="content" style="width:100px" onkeydown="if(event.keyCode==13){postAjax('trailerStore','listDocs',anchor + ':_VIEW');messageImg('right');return false;}" />
    </td>
    <td width="10%">
             <html:button property="_TRANSFER_DOC" styleClass="button" style="width:120px" onclick="javascript:tranfer()"  >
                Chuy&#7875;n c&#244;ng t&#225;c
            </html:button>
    </td>
    <td width="45%">
            <html:select styleClass="inputbox"  name="trailerStore" property="departmentIdOffice" style="width:150px" onchange="javascript:postAjax('trailerStore','userRecvId',anchor+':_SHOW_USER_RECV');" > 
            <html:option value="0"><bean:message key="status.all" bundle="<%=interfaces%>"/></html:option>
            <logic:present name="BDepartments">
            <html:options collection="BDepartments" property="id" labelProperty="name"/>          
            </logic:present>
            </html:select> 
            <span id="userRecvId">
                    <jsp:include page="/admin/doc/rules/trailerStore/userrecv.jsp" />                
            </span>
            
    </td>
  </tr>  
</table>
<div id="listDocs" style="padding-top:6px"><jsp:include page="/admin/doc/rules/trailerStore/listDocs.jsp" /></div>
</html:form>