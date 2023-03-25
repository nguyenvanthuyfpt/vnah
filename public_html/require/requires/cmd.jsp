<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<logic:present name="<%=com.lib.AppConfigs.CHECK_RULE_REQUIRES%>" >
 <html:button property="_EDIT" styleClass="button"  onclick="javascript:this.disabled=true;post('frmRequire',anchor + ':_CREATE')" >                 
    <bean:message key="require.cmd.create.store.caption" bundle="<%=interfaces%>"/>
 </html:button> 

<logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_REQUIRES%>" property="selectEmp" value="1">
<a class="modal-button" href="frmRequire<%=extention%>?<%=anchor%>=_SELECT_EMP&id=0" rel="{handler: 'iframe', size: {x:340, y: 310},bookmark:'if(SqueezeBox.presets.target==0){post(\'frmRequire\',anchor + \':_PREPARED_CREATE_AND_CREATE_RM\');}'}">
    <html:button property="_PREPARE_CHOSE_RECV" styleClass="button" style="width:120px;" >
    <bean:message key="doc.header.selectRecv.caption" bundle="<%=interfaces%>"/>
    </html:button>
</a>
</logic:equal>

<logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_REQUIRES%>" property="direct" value="1">
<html:button property="_DOC_ASSIGN_CREATE" styleClass="button"  onclick="javascript:post('frmRequire',anchor+':_RM_ASSIGN_CREATE');" >                             
     <bean:message key="doc.assign.direct.cmd.caption" bundle="<%=interfaces%>"/>                              
</html:button>
</logic:equal>

<logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_REQUIRES%>" property="selectDep" value="1">
  <logic:present name="BDepartments">
      <html:select styleClass="inputbox"  name="frmRequire" property="departmentId">           
        <html:option value="0"><bean:message key="problem.dep.select.caption" bundle="<%=interfaces%>"/></html:option>                               
            <html:options collection="BDepartments" property="id" labelProperty="name"/>                                         
      </html:select> 
 </logic:present>
 </logic:equal>
 </logic:present>