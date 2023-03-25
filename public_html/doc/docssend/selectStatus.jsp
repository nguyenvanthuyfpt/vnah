<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:equal name="<%=com.lib.AppConfigs.CHECK_OBSERVER_DOCSSEND%>" value="1" >            
    <bean:message key="form.docs.obServer" bundle="<%=interfaces%>"/>:
    <html:checkbox  name="docssend" property="obServer" value="1" onclick="javascript:post('docssend',anchor+':_VIEW');" /> </p>
</logic:equal>                                    
<p><font style="color:#a0aec2"><b><bean:message key="form.docs.statusId.excute.caption" bundle="<%=interfaces%>"/></b></font></p>
<logic:present name="BDocsSendRead">
<p>
<% String waitIdAll=com.inf.doc.IKeyDoc.DOC_STATUS_VIEW_ALL+""; %>
<div class="docsListTrailer">
  <span onclick="javascript:post('docssend',anchor+':_VIEW:statusId:<%=waitIdAll%>');" style="cursor: pointer;"> 
  <logic:equal  name="docssend" property="statusId" value="<%=waitIdAll%>" >
         <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
         <strong><bean:message  key="status.all" bundle="<%=interfaces%>"/> (<bean:write name="BDocsSendRead" property="amount"/>)</strong>
  </logic:equal>
  <logic:notEqual  name="docssend" property="statusId" value="<%=waitIdAll%>" >
        <img src="<%=contextPath%>/images/newImages/folder.gif"/>
        <bean:message  key="status.all" bundle="<%=interfaces%>"/> (<bean:write name="BDocsSendRead" property="amount"/>)
  </logic:notEqual>
  </span>
</div>  
             
          <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkUnReaded" value="1">
          <logic:present name="BDocsSendWait">
              <logic:iterate name="BDocsSendWait" id="bean" type="com.form.main.FMain"> 
                  <logic:equal name="bean" property="id" value="0" >                              
                              <% String waitId= com.inf.doc.IKeyDoc.DOC_STATUS_VIEW_WAIT+""; %>
                              <div class="docsListTrailer">
                              <span onclick="javascript:post('docssend',anchor+':_VIEW:statusId:<%=waitId%>');" style="cursor: pointer;"> 
                              <logic:equal  name="docssend" property="statusId" value="<%=waitId%>" >
                                     <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                                     <strong><bean:message key="status.wait" bundle="<%=interfaces%>"/><span>(<bean:write name="bean" property="amount"/>)</span></strong>
                              </logic:equal>
                              <logic:notEqual  name="docssend" property="statusId" value="<%=waitId%>" >
                                    <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                                    <bean:message key="status.wait" bundle="<%=interfaces%>"/><span>(<bean:write name="bean" property="amount"/>)</span>
                              </logic:notEqual>
                              </span>
                            </div>  
                 </logic:equal>
              </logic:iterate>
           </logic:present>
          </logic:equal>
          
            <logic:present name="BDocsSend">                   
                <logic:iterate name="BDocsSend" id="beanStatus" type="com.form.main.FMain">
                    <p>
                    <% String waitId=beanStatus.getStatusId()+""; %>
                    <div class="docsListTrailer">
                      <span onclick="javascript:post('docssend',anchor+':_VIEW:statusId:<%=waitId%>');" style="cursor: pointer;"> 
                      <logic:equal  name="docssend" property="statusId" value="<%=waitId%>" >
                             <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                             <strong><bean:write name="beanStatus" property="name" /> (<bean:write name="beanStatus" property="amount" />)</strong>
                      </logic:equal>
                      <logic:notEqual  name="docssend" property="statusId" value="<%=waitId%>" >
                            <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                            <bean:write name="beanStatus" property="name" /> (<bean:write name="beanStatus" property="amount" />)
                      </logic:notEqual>
                      </span>
                    </div>  
                </logic:iterate>
             </logic:present>
   </logic:present>
