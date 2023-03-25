  <%@ include file="/commons/tags.jsp"%>
  <%@ include file="/commons/params.jsp"%> 
  <div style="padding:6px">
  <bean:define name="BRequire" id="beanRequire"  type="com.form.require.requires.FRequire" />
  <bean:define name="<%=com.lib.AppConfigs.CHECK_RULE_REQUIRES%>" id="beanRequireRule"  type="com.form.admin.require.trailer.FRequireTrailer" />
  <logic:notEqual name="beanRequireRule" property="ruleId" value="0">
<% if (beanRequire.getReaded()==0 && beanRequire.getRmStatus()!=-1 || beanRequire.getRmStatus()==0){%> 
 
  <div style="float: left;">
  
  <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_REQUIRES%>" property="repply" value="2">
     <% String updateReply="post('frmRequire','" + anchor + ":_CREATE_REVIEW_SELECT:rmId:" + beanRequire.getRmId() + "')";%>  
      <html:button property="_CREATE_REVIEW" styleClass="button" onclick="<%=updateReply%>">
            <bean:message key="require.rule.cmd.review.caption" bundle="<%=interfaces%>"/>   
      </html:button>
  </logic:equal>
  
  <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_REQUIRES%>" property="selectEmp" value="1">
      <a class="modal-button" href="frmRequire<%=extention%>?<%=anchor%>=_SELECT_EMP&id=<%=beanRequire.getRmId()%>" rel="{handler: 'iframe', size: {x:340, y: 310},bookmark:'if(SqueezeBox.presets.target==0){post(\'frmRequire\',anchor + \':_PREPARED_CREATE_TRAILER\');}'}">
        <html:button property="_DOC_ASSIGN_CREATE" styleClass="button" style="width:120px;" >
          <bean:message key="require.rule.trailer.select.emp.caption" bundle="<%=interfaces%>"/>
        </html:button>
      </a>
  </logic:equal>
  
<logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_REQUIRES%>" property="direct" value="1">
   <% String assignDirect="post('frmRequire','" + anchor + ":_TRAILER_CREATE:rmId:" + beanRequire.getRmId() + "')";%>   
    <html:button property="_DOC_ASSIGN_CREATE" styleClass="button" onclick="<%=assignDirect%>">
             <bean:message key="require.rule.cmd.redirect.caption" bundle="<%=interfaces%>"/>      
    </html:button>  


    <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_REQUIRES%>" property="selectDep" value="1">
      <logic:present name="BDepartments">
          <html:select styleClass="inputbox"  name="frmRequire" property="departmentId">           
            <html:option value="0"><bean:message key="problem.dep.select.caption" bundle="<%=interfaces%>"/></html:option>                               
                <html:options collection="BDepartments" property="id" labelProperty="name"/>                                         
          </html:select> 
     </logic:present>
     </logic:equal>
</logic:equal>
</div>
<%}%>
<%if((beanRequire.getRmStatus()!=-1 && beanRequireRule.getStatus_store()!=-2)){%>
<div style="float: right;">
<%      int store = beanRequireRule.getStatus_store()==-3?beanRequireRule.getStatus_id():beanRequireRule.getStatus_store();
   
%>
<% String excuteEndStatus="excuteEndRequire(" +  beanRequire.getRmId() + "," + store + "," + beanRequireRule.getStatus_id() + ")";%>
<html:button property="_DOC_ASSIGN_CREATE" styleClass="button" onclick="<%=excuteEndStatus%>">
     <bean:message key="require.rule.trailer.end.caption" bundle="<%=interfaces%>"/>      
</html:button> 
</div>
<%}%>
</logic:notEqual>
</div>
<br>
