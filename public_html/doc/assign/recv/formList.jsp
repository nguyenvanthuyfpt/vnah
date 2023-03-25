<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
function submitAssign(obj){
        
            post('docAssignRecv',anchor+':_CREATE')
//            if(obj.review.checked){
//                parent.SqueezeBox.presets.target=0;
//                parent.SqueezeBox.close();
//            }
//         }
}

function mdotabOption(obj,params){
    if(obj.className=='tab'){
        for(i=0;i<obj.parentNode.cells.length-1;i++){
                obj.parentNode.cells[i].className='tab';
        }
        obj.className='tabactive';       
        postAjax('docMainAssignRecv','changeOption',anchor + ':' + params);
        messageImg('changeOption')
    }    
}
function checkValues(obj){
        if(obj.usersId.length==0){
        alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
        }else{
        checkedAll(obj.usersId);
        parent.SqueezeBox.presets.target=0;
        post('docAssignRecv',anchor+':_CREATE_FROM_DOC');
        }
}


function checkedEmpSelect(value,checkname){
     for (i = 0; i < checkname.length; i++){    
         if (checkname[i].value == value){
             checkname[i].checked = false;  
        }
}  
     
        
}
</script>
<html:form action="docMainAssignRecv" method="post"/>
<html:form action="docAssignRecv" method="post">
<html:hidden name="docAssign" property="id" /> 
<html:hidden name="docAssign" property="type" /> 
<html:hidden name="docAssign" property="checkComman" /> 
<div class="ct-celendar">
<table width="100%"  cellpadding="0" cellspacing="0">
    <tr>
        <td width="100%" colspan="2"  id="mainUsers">
        <jsp:include page="/doc/assign/recv/userList.jsp"/>
        </td>
    </tr>
   
        <logic:notPresent name="CreateDoc">
        <tr>
            <td nowrap  align="left" >
            <html:button property="_CREATE" onclick="javascript:submitAssign(this.form)" styleClass="button">                                                                                        
            <bean:message key="doc.assign.cmd.caption" bundle="<%=interfaces%>"/>
            </html:button> 
            </td>
        </tr>
    </logic:notPresent>
    <logic:present name="CreateDoc">
        <tr>
        <td nowrap  align="center" colspan="2">
        <logic:equal name="docAssign" property="id" value="0">
        
            <html:button property="_CREATE_FROM_DOC" onclick="javascript:checkValues(this.form)" styleClass="button">                                                                                        
            <bean:message key="doc.assign.cmd.caption" bundle="<%=interfaces%>"/>
            </html:button> 
        </logic:equal>
        <logic:notEqual name="docAssign" property="id" value="0">
            <html:button property="_CREATE_FROM_DOC" onclick="javascript:checkedAll(this.form.usersId);post('docAssignRecv',anchor+':_CREATE_FROM_DOC');" styleClass="button">                                                                                        
            <bean:message key="doc.assign.cmd.caption" bundle="<%=interfaces%>"/>
            </html:button> 
        </logic:notEqual>
        </td>
        </tr>
    </logic:present>    
</table>
</div>
 </html:form>    
 
 

            
            
  
 
 