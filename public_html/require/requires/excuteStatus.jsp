<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%> 
  <bean:message key="form.docs.obServer" bundle="<%=interfaces%>"/>:
    <html:checkbox  name="frmRequireEmp" property="obServer" value="1" onclick="javascript:post('frmRequireEmp',anchor+':_REQUIRE_LIST');" />
  <p><font style="color:#a0aec2"><b><bean:message key="require.rule.stattus.excute.caption" bundle="<%=interfaces%>"/></b></font></p>     
 <logic:present name="BTotalRM">
    <div class="docsListTrailer">
     <% String statusAll=com.inf.doc.IKeyDoc.RM_STATUS_VIEW_ALL+""; %>
    <span onclick="javascript:post('frmRequireEmp',anchor+':_REQUIRE_LIST:rmStatus:<%=statusAll%>');" style="cursor: pointer;">
         <logic:equal  name="frmRequireEmp" property="rmStatus" value="<%=statusAll%>" >
            <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
            <strong><bean:message key="require.rule.stattus.all.caption" bundle="<%=interfaces%>"/> (<bean:write name="BTotalRM"/>)</strong>
        </logic:equal>
        <logic:notEqual  name="frmRequireEmp" property="rmStatus" value="<%=statusAll%>" >
            <img src="<%=contextPath%>/images/newImages/folder.gif"/>
            <bean:message key="require.rule.stattus.all.caption" bundle="<%=interfaces%>"/> (<bean:write name="BTotalRM"/>)
        </logic:notEqual>
    </span>
    </div>
</logic:present>    
<p/>     
<logic:notPresent parameter="obServer">      
<logic:present name="BExcuteWailt">
 <bean:define name="<%=com.lib.AppConfigs.CHECK_RULE_REQUIRES%>" id="beanRM"  type="com.form.admin.require.trailer.FRequireTrailer" />
 <% if (beanRM.getUnIncharge()==1){ %>
    <div class="docsListTrailer">
         <% String unread=com.inf.doc.IKeyDoc.RM_STATUS_UNREAD+""; %>        
        <span onclick="javascript:post('frmRequireEmp',anchor+':_REQUIRE_LIST:rmStatus:<%=unread%>');" style="cursor: pointer;">
         <logic:equal  name="frmRequireEmp" property="rmStatus" value="<%=unread%>" >
            <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
            <strong><bean:message key="require.rule.stattus.wailt.caption" bundle="<%=interfaces%>"/> (<bean:write name="BExcuteWailt"/>)</strong>
        </logic:equal>
        <logic:notEqual  name="frmRequireEmp" property="rmStatus" value="<%=unread%>" >
            <img src="<%=contextPath%>/images/newImages/folder.gif"/>
            <bean:message key="require.rule.stattus.wailt.caption" bundle="<%=interfaces%>"/> (<bean:write name="BExcuteWailt"/>)
        </logic:notEqual>
        </span>
    </div>
    <%}%>
</logic:present>
</logic:notPresent>
<p/>        
<logic:present name="BExcuteStatus">
    <logic:iterate name="BExcuteStatus" id="bean" type="com.form.require.requires.FRequire"> 
     <div class="docsListTrailer">
      <% String statusExcute=bean.getRmStatus()+""; %>
      <span onclick="javascript:post('frmRequireEmp',anchor+':_REQUIRE_LIST:rmStatus:<%=statusExcute%>');" style="cursor: pointer;">
         <logic:equal  name="frmRequireEmp" property="rmStatus" value="<%=statusExcute%>" >
            <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
            <strong><bean:write name="bean" property="name" /> (<bean:write name="bean" property="amount" />)</strong>
        </logic:equal>
        <logic:notEqual  name="frmRequireEmp" property="rmStatus" value="<%=statusExcute%>" >
            <img src="<%=contextPath%>/images/newImages/folder.gif"/>
            <bean:write name="bean" property="name" /> (<bean:write name="bean" property="amount" />)
        </logic:notEqual>
        </span>
        
    </div>  
     <p/>  
    </logic:iterate>
</logic:present>