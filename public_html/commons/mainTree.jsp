<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="docssendMain" method="post" />
<html:form action="docsrecvMain" method="post" />
<html:form action="messsagesListMain" method="post" />
<html:form action="problemMain" method="post" />
 <html:form action="addPortlet" method="post" />
<logic:present name="BMenus" >
<div id="menu" >
<div class="title-02" style="text-align:left:padding-left:3px;height:21px;">
<html:select  name="change" property="app" onchange="javascript:eval(this.value)"  styleClass="fieldSelect" style="width:90px;"> 
            <html:option value="0">---<bean:message key="action.insert" bundle="<%=interfaces%>"/>---</html:option>
            <logic:equal name="BCheckRulesCreatorDataRecv" value="1">
            <html:option value="post('docsrecvMain',anchor + ':_PREPARED_CREATE:app:1')" ><bean:message key="menu.top.doc.recivers.caption" bundle="<%=interfaces%>"/></html:option>
            </logic:equal>
            <logic:equal name="BCheckRulesCreatorDataSend" value="1">
            <html:option value="post('docssendMain',anchor + ':_PREPARED_CREATE:type:1:app:1')"><bean:message key="menu.top.doc.send.caption" bundle="<%=interfaces%>"/></html:option>
            </logic:equal>
            <%if(me.isRole(com.inf.IRoles.rLEADER)){%>
            <html:option value="post('docssendMain',anchor + ':_PREPARED_CREATE:type:2:app:1')"><bean:message key="form.docs.type.header.send.dt" bundle="<%=interfaces%>"/></html:option>
            <%}%>
            <html:option value="post('messsagesListMain',anchor + ':_PREPARED_CREATE:id:0:app:0')"><bean:message key="app.send.item.messages" bundle="<%=interfaces%>"/></html:option>
            <logic:present name="Assign">
             <html:option value="post('problemMain',anchor + ':_PREPARED_CREATE:root:0:problemId:0:app:2')"><bean:message key="problem.detail.caption" bundle="<%=interfaces%>"/></html:option>
            </logic:present>   
            <%if(me.isRole(com.inf.IRoles.rBROADCAST)){%>
                <html:option value="post('change',anchor + ':_PREPARE_BROADCAST');"><bean:message key="broadcast.main.caption" bundle="<%=interfaces%>"/></html:option>
           <%}%> 
</html:select>
<span id="openCombo">
<jsp:include page="/commons/application.jsp" />
</span>
</div>
<%int pId=0;
  int app = Integer.parseInt(request.getSession().getAttribute("BApp").toString());  
%>
<div id="content-pane" class="pane-sliders">
<logic:iterate name="BMenus" id="bean" type="com.form.admin.menu.FMenu">
<%if (app == pId){%>	
                <div class="panel">
                    <h3 class="jpane-toggler title" id="detail-page"><span><bean:write name="bean" property="title" /></span></h3>
                    <div class="jpane-slider">
                                <table width="100%" class="paramlist admintable" cellspacing="1">
                                <logic:iterate name="bean" property="beansMenu" id="subBean" type="com.form.admin.menu.FMenu">
                                    <tr>                                
                                    <td class="paramlist_key">
                                        <span class="editlinktip">
                                            <label id="paramsshow_title-lbl" for="paramsshow_title" class="hasTip" title="">
                                              <span style="cursor: pointer;" onclick="javascript:id=<%=Integer.toString(pId)%>;<bean:write name="subBean" property="url" />"><bean:write name="subBean" property="title" /> </span>
                                            </label>
                                       </span>     
                                    </td>				
                                    </tr>	
                                </logic:iterate>    
                            </table>
                    </div>
		</div>
      <%}pId++;%>
</logic:iterate>

<%pId=0;%>
<logic:iterate name="BMenus" id="bean" type="com.form.admin.menu.FMenu">
<%if (app != pId){%>	
		<div class="panel">
                    <h3 class="jpane-toggler title" id="detail-page"><span><bean:write name="bean" property="title" /></span></h3>
                    <div class="jpane-slider">
                                <table width="100%" class="paramlist admintable" cellspacing="1">
                                <logic:iterate name="bean" property="beansMenu" id="subBean" type="com.form.admin.menu.FMenu">
                                    <tr>                                
                                    <td class="paramlist_key">
                                        <span class="editlinktip">
                                            <label id="paramsshow_title-lbl" for="paramsshow_title" class="hasTip" title="">
                                               <span style="cursor: pointer;" onclick="javascript:id=<%=Integer.toString(pId)%>;<bean:write name="subBean" property="url" />"><bean:write name="subBean" property="title" /> </span>
                                            </label>
                                       </span>     
                                    </td>				
                                    </tr>	
                                </logic:iterate>    
                            </table>
                    </div>
		</div>
<%}pId++;%>
</logic:iterate>       
</div>
</div>	
</logic:present>
<html:form action="serveyQuestions" method="post">
<div  id="content-servey">
<jsp:include page="/serveyQuestions/main.jsp" />
</div>
<br>

</html:form>
