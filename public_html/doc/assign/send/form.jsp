<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<script language="javascript">

function submitAssign(obj){
     var result = 0;
     if(obj.members.value.length>2){
            post('docAssignSend',anchor+':_CREATE:membersFile:' + parent.empDep)
                if(obj.review.checked){
                    parent.SqueezeBox.presets.target=0;                
                }
    }else{
    return false;
    }
}
function checkValues(obj){
    if(obj.members.value.length>2){
        post('docAssignSend',anchor+':_CREATE_FROM_DOC:membersFile:' + parent.empDep);
        parent.SqueezeBox.presets.target=0;
       
    }else{return false;}
}


</script>
<div style="padding:6px">
<body onLoad="if(parent.SqueezeBox.presets.target==0){parent.SqueezeBox.close()};">
<html:form action="docAssignSend" >
<html:hidden name="docAssign" property="id" /> 
<html:hidden name="docAssign" property="statusId" />
<html:hidden name="docAssign" property="dossierId" />
<html:hidden name="docAssign" property="members" /> 
<html:hidden name="docAssign" property="departmentMembers" /> 
<html:hidden name="docAssign" property="groupMembers" /> 
<table width="100%"   cellpadding="0" cellspacing="0" >
    <tr>
        <td width="100%" valign="top" id="mainUsers">
                <div style="text-align: right;padding-right:4px;padding-bottom:4px">
                        <jsp:include page="/doc/assign/send/tagOption.jsp"/>
                </div>      
                <div id="listInforChecked">  
                        <jsp:include page="/doc/assign/send/list.jsp"/>
                </div>
        </td>
    </tr>
  </table>

<table width="100%" cellpadding="0" cellspacing="0" >
  <logic:notPresent name="CreateDoc">
    <tr>
        <td nowrap  align="center" height="26px">
        <html:button property="_CREATE" onclick="javascript:submitAssign(this.form)" styleClass="button">                                                                                        
        <bean:message key="doc.assign.cmd.caption" bundle="<%=interfaces%>"/>
        </html:button> 
        </td>
    </tr>
  </logic:notPresent>

  <logic:present name="CreateDoc">
    <tr>
         <td nowrap  align="center" height="26px">
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

                 

            
            
  
 
 
