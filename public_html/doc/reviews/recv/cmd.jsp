<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:hidden name="docsrecv" property="id" /> 
<html:hidden name="docAssign" property="checkComman" /> 
<html:hidden name="docsrecv" property="dossierId" />
<html:hidden name="docsrecv" property="views" />
<bean:define name="docsrecv" property="id" id="id" type="java.lang.Integer" />
<bean:define name="docAssign" property="statusId" id="statusId" type="java.lang.Integer" />
<bean:define name="docsrecv" property="views" id="views" type="java.lang.Integer" />
<bean:define name="docAssign" property="dossierId" id="dossierId" type="java.lang.Integer" />
<Script type="text/javascript">
function validateReview(form){
    if(form.title.value==''){
        alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
        return false;
    }
    return true;
}
</Script>
<div style="padding-bottom:5px;" ><input style="width:80px" type="button" value="<bean:message key="command.doc.review.anwear.send.caption" bundle="<%=interfaces%>"/>"  onclick="post('docssend',anchor + ':_PREPARED_CREATE:id:<%=id%>:type:2')" /> <input type="checkbox" name="readed" id="readed" value="1" checked ><label for="readed">T&#7921; &#273;&#7897;ng chuy&#7875;n v&#7873; danh s&#225;ch c&#244;ng v&#259;n</label></div>
<logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkSelectRecv" value="1">
            <a class="modal-button" href="docsrecv<%=extention%>?<%=anchor%>=_PREPARE_CHOSE_RECV_FROM_INFOR&id=<%=id%>" rel="{handler: 'iframe', size: {x:420, y: 500},bookmark:'if(SqueezeBox.presets.target==0){post(\'docReviewRecv\',anchor + \':_PREPARED_CREATE\');}'}">
                         <html:button property="_DOC_ASSIGN_CREATE" styleClass="button" style="width:100px;"  >
                                    <bean:message key="doc.header.selectRecv.caption" bundle="<%=interfaces%>"/>
                        </html:button>
            </a>
</logic:equal>

<logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkDirect" value="1">
        
      
      <html:button property="_DOC_ASSIGN_CREATE" styleClass="button"  onclick="javascript:this.disabled=true;post('docReviewRecv',anchor+':_DOC_ASSIGN_CREATE');" >
                    <logic:notEmpty name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkTcaption" >
                      <bean:write name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkTcaption" />
                    </logic:notEmpty>
                    <logic:empty name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkTcaption" >
                             <bean:message key="doc.assign.direct.cmd.caption" bundle="<%=interfaces%>"/>  
                    </logic:empty>
    </html:button> 
    
      <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkSelectDept" value="1">
            <logic:present name="BDepartments">
            <html:select styleClass="inputbox"  name="docAssign" property="departmentId">           
            <html:option value="0"><bean:message key="problem.dep.select.caption" bundle="<%=interfaces%>"/></html:option>                               
            <html:options collection="BDepartments" property="id" labelProperty="name"/>                                         
            </html:select>
            </logic:present>
     </logic:equal>
</logic:equal>
  
 

         

            
            
  
 
 
