<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%String action = "_VIEWUSERS";%>

<script language=javascript>
    function getInforMess(type){
       postAjax('problem','MainProblem',anchor + ':_PREPARED_SAVE:type:' + type);
       message('MainProblem','Xin cho')
    }
      dLeft = new dTree('dLeft');
      dLeft.add(0,-1,'<font style="font-size:11px;"><bean:message key="app.folder" bundle="<%=interfaces%>"/></font>');
      dLeft.add(<%=com.inf.messages.IConstantsMessages.STATUS_SEND_REV_DEL[0]%>,0,'<bean:message key="problem.task.worker" bundle="<%=interfaces%>"/><logic:present name="BAmount"> (<span style="font-weight: bold;"><bean:write name="BAmount" property="amountSend"/></span>)</logic:present>',"javascript:getInforMess(0)");
      dLeft.add(<%=com.inf.messages.IConstantsMessages.STATUS_SEND_REV_DEL[1]%>,0,'<bean:message key="problem.task.assign" bundle="<%=interfaces%>"/><logic:present name="BAmount"> (<span style="font-weight: bold;"><bean:write name="BAmount" property="amountRev"/></span>)</logic:present>',"javascript:getInforMess(1)");      
      document.write(dLeft);
</script>
