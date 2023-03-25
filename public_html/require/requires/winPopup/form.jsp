<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
function checkValues(obj){
        if(document.frmRequireEmp.members.value.length>2){
        post('frmRequireEmp',anchor+':_CREATE_FROM_DOC');     
        parent.SqueezeBox.presets.target=0;
        }else{
        return false;
        }
}
</script>

<body onLoad="if(parent.SqueezeBox.presets.target==0){parent.SqueezeBox.close()};">
<html:form action="frmRequireEmp" method="post">
<html:hidden name="frmRequireEmp" property="members" /> 
 <html:hidden name="frmRequireEmp" property="surcureId" />    
<table  cellpadding="0" cellspacing="0" width="100%" >
<tr>
    <td width="100%" id="mainUsers">
      <jsp:include page="/require/requires/winPopup/userList.jsp"/>
    </td>
</tr>
<tr>
    <td width="100%"  >
            <div id="listInforChecked" class="classScoreRequires"  >  
            <jsp:include page="/require/requires/winPopup/list.jsp"/>    
            </div>
    </td>
</tr>
<tr>
    <td width="100%" class="classRepuiresCommand" >
                <html:button property="_CREATE_FROM_DOC" onclick="javascript:checkValues(this.form)" styleClass="button">                                                                                        
                <bean:message key="doc.assign.cmd.caption" bundle="<%=interfaces%>"/>
                </html:button> 
    </td>
</tr>
</table>
</html:form>    
</body>
