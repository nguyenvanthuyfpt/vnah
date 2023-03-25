<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:equal name="<%=com.lib.AppConfigs.CHECK_OBSERVER_DOCSRECV%>" value="1" >            
    <bean:message key="form.docs.obServer" bundle="<%=interfaces%>"/>:
    <html:checkbox  name="docsrecv" property="obServer" value="1" onclick="javascript:post('docsrecv',anchor+':_VIEW');" />
    </logic:equal>                                    
    <p><font style="color:#a0aec2"><b><bean:message key="form.docs.statusId.excute.caption" bundle="<%=interfaces%>"/></b></font></p>
<logic:present name="BDocsRecvRead">

 <% String viewAll= com.inf.doc.IKeyDoc.DOC_STATUS_VIEW_ALL+""; %>
<div class="docsListTrailer">
   <span onclick="javascript:post('docsrecv',anchor+':_VIEW:statusId:<%=viewAll%>');" style="cursor: pointer;"> 
  <logic:equal  name="docsrecv" property="statusId" value="<%=viewAll%>" >
         <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
         <strong><bean:message  key="status.all" bundle="<%=interfaces%>"/> (<bean:write name="BDocsRecvRead" property="amount"/>)</strong>
  </logic:equal>
  <logic:notEqual  name="docsrecv" property="statusId" value="<%=viewAll%>" >
        <img src="<%=contextPath%>/images/newImages/folder.gif"/>
        <bean:message  key="status.all" bundle="<%=interfaces%>"/> (<bean:write name="BDocsRecvRead" property="amount"/>)
  </logic:notEqual>
  </span>
</div>             
 
  <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkUnReaded" value="1">
  <logic:present name="BDocsRecvWait">
      <logic:iterate name="BDocsRecvWait" id="bean" type="com.form.main.FMain"> 
          <logic:equal name="bean" property="id" value="0" >
                      
                      <% String waitId= com.inf.doc.IKeyDoc.DOC_STATUS_VIEW_WAIT+""; %>                      
                      <div class="docsListTrailer">
                            <span onclick="javascript:post('docsrecv',anchor+':_VIEW:statusId:<%=waitId%>');" style="cursor: pointer;">
                            <logic:equal  name="docsrecv" property="statusId" value="<%=waitId%>" >
                                <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                                <strong><bean:message key="status.wait" bundle="<%=interfaces%>"/><span>(<bean:write name="bean" property="amount"/>)</span></strong>
                            </logic:equal>
                            <logic:notEqual  name="docsrecv" property="statusId" value="<%=waitId%>" >
                                <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                                <bean:message key="status.wait" bundle="<%=interfaces%>"/><span>(<bean:write name="bean" property="amount"/>)</span>
                            </logic:notEqual>
                            </span>
                      </div>
         </logic:equal>
      </logic:iterate>
   </logic:present>
  </logic:equal>
        
          
            <logic:present name="BDocsRecv">                   
                <logic:iterate name="BDocsRecv" id="beanStatus" type="com.form.main.FMain">
                    <p>
                    <% String waitId=beanStatus.getStatusId()+""; %>                    
                    <div class="docsListTrailer">
                           <span onclick="javascript:post('docsrecv',anchor+':_VIEW:statusId:<%=waitId%>');" style="cursor: pointer;"> 
                            <logic:equal  name="docsrecv" property="statusId" value="<%=waitId%>" >
                                <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                                <strong><bean:write name="beanStatus" property="name" /> (<bean:write name="beanStatus" property="amount" />)</strong>
                            </logic:equal>
                            <logic:notEqual  name="docsrecv" property="statusId" value="<%=waitId%>" >
                                <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                                <bean:write name="beanStatus" property="name" /> (<bean:write name="beanStatus" property="amount" />)                            
                            </logic:notEqual>
                           </span>  
                    </div>
                </logic:iterate>
             </logic:present>
   </logic:present>


               