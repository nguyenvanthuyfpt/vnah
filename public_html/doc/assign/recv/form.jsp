<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
function submitAssign(obj){ 
if(document.docAssignRecv.members.value.length>2){
        post('docAssignRecv',anchor+':_CREATE')          
            if(obj.review.checked){
                  parent.SqueezeBox.presets.target=0;                
                 // parent.SqueezeBox.close();
            }            
}else{
return false;
}           
}
function checkValues(obj){
        if(document.docAssignRecv.members.value.length>2){
        post('docAssignRecv',anchor+':_CREATE_FROM_DOC');
        parent.SqueezeBox.presets.target=0;
        }else{
        return false;
        }
}
</script>
<div style="padding:6px">
<body onLoad="if(parent.SqueezeBox.presets.target==0){parent.SqueezeBox.close()};">
<html:form action="docMainAssignRecv" method="post"/>
<html:form action="docAssignRecv" method="post">
<html:hidden name="docAssign" property="id" /> 
<html:hidden name="docAssign" property="forYouId" /> 
<html:hidden name="docAssign" property="type" /> 
<html:hidden name="docAssign" property="members" /> 
<html:hidden name="docAssign" property="departmentMembers" /> 
<html:hidden name="docAssign" property="groupMembers" /> 
    <table width="100%" cellpadding="0" cellspacing="0" >
        <tr>
            <td width="100%" id="mainUsers">
              <jsp:include page="/doc/assign/recv/userList.jsp"/>
            </td>
        </tr> 
       </table>

       <table width="100%" cellpadding="0" cellspacing="0" >
         <logic:notPresent name="CreateDoc">
            <tr>       
                <td nowrap  align="center" height="30px" valign="bottom">              
                <html:button property="_CREATE" onclick="javascript:submitAssign(this.form)" styleClass="button">                                                                                        
                <bean:message key="doc.assign.cmd.caption" bundle="<%=interfaces%>"/>
                </html:button> 
                </td>
            </tr>
        </logic:notPresent>     
         <logic:present name="CreateDoc">
            <tr>
            <td nowrap  align="center">
                <html:button property="_CREATE_FROM_DOC" onclick="javascript:checkValues(this.form)" styleClass="button">                                                                                        
                <bean:message key="doc.assign.cmd.caption" bundle="<%=interfaces%>"/>
                </html:button> 
             </td>
            </tr>
     </logic:present>
    </table>
</html:form>    
</body>
</div>
