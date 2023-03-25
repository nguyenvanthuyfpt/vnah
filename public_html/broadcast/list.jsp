<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="broadcast" method="post" >
<div class="padding-content">     
<div id="mailcol">
    <div class="tabmenu" id="container-1" >
        <div style="clear:both"></div>
        <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="top" width="180px">
                            <div class="ctn-left">
                                <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic">
                                    <bean:message key="broadcast.list.caption" bundle="<%=interfaces%>"/>
                                </div></div>
                            </div>  
                    </td>
                    <td valign="top" >
                        <ul id="ui-tabs-nav"><li class="ui-tabs-selected"></li><li class="" ></li></ul>
                    </td>
                </tr>
            </table>        
        <div id="fragment-1">           
            <div class="content-calendar" id="vtCalendar">
                    <%if(me.isRole(com.inf.IRoles.rBROADCAST)){%>
                    <div class="toolCmd" style="padding-left:10px" align="left">
                            <table width="100%">
                                <tr>
                                     <td nowrap style="text-align:left"  class="BGSearch">                      
                                                <html:text name="broadcast" property="title" styleClass="InputSearch" size="20" onkeydown="if(event.keyCode==13){post('broadcast',anchor + ':_VIEW');return false;}" />
                                                <input class="Button_Search" value="<bean:message key="categories.cmd.search" bundle="<%=interfaces%>"/>" type="button" onclick="post('broadcast',anchor + ':_VIEW');return false;">                       
                                                 
                                     </td>
                                    <td align="right">
                                            <a class="modal-button" href="broadcast<%=extention%>?<%=anchor%>=_PREPARE&broadcastId=0" rel="{handler: 'iframe', size: {x: 520, y: 460},bookmark:'if(SqueezeBox.presets.target==0) { post(\'broadcast\',anchor + \':_VIEW\'); }'}"> 
                                                <html:button property="_PREPARED_CREATE" styleClass="button">
                                                     <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                                                 </html:button>
                                             </a>
                                    </td>
                                </tr>
                            </table>
                    </div>
                    <%}%>
<table  class="list-voffice"  cellpadding="0" cellspacing="0" border="0" width="100%" >
    <tr>      
        <th  class="col" width="8px" nowrap><bean:message key="broadcast.stt.caption" bundle="<%=interfaces%>"/></th> 
        <th class="col"> <bean:message key="broadcast.infor.caption" bundle="<%=interfaces%>"/></th>
        <%if(me.isRole(com.inf.IRoles.rBROADCAST)){%>
        <th class="col" width="8px" nowrap>&nbsp;</th>
        <th class="col" width="10px">#</th>
        <%}%>
    </tr>
   <logic:present name="BBroadcasts" > 
    <bean:define name="BBroadcasts" id="beans" type="com.form.FBeans"/>
   <%  int i = beans.getFirstRecord();%>
    <logic:iterate name="BBroadcasts" id="bean" type="com.form.broadcast.FBroadcast">
        <tr bgcolor="<%=(i%2==0)?"#FFFFFF":"#e7ebee"%>">   
               <td  align="center" width="8px" nowrap ><span class="index">
                <%=i++%>.
              </span></td>
            
               <td >
                
                <div>
                   <strong>
                   
                     <a href="javascript:post('broadcast',anchor + ':_DETAIL:broadcastId:<%=bean.getBroadcastId()%>:user_id:<%=bean.getUser_id()%>')"><bean:write name="bean" property="title" /></a>                                       
                   </strong>
                </div>
                
                 
                 <logic:notEqual name="bean" property="content" value="">
                 <bean:define  name="bean" property="content" id="content" />
                 <div><%=content%></div>
                 </logic:notEqual>
                 <span style="float: right;font-size: 10px;padding-right:4px"><strong><bean:write name="bean" property="fullName" /></strong>: <bean:write name="bean" property="createtime" /></span>                
                </td>   
                <%if(me.isRole(com.inf.IRoles.rBROADCAST)){%>
                <td  width="35px" nowrap>     
                  <% if (i==beans.getFirstRecord()+1){%>
                         
                          <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/orders/downarrow.png" title="<bean:message key="broadcast.downarrow.caption" bundle="<%=interfaces%>"/>" onClick="javascript:post('broadcast',anchor + ':_ORDERS:broadcastId:<bean:write name="bean" property="broadcastId"/>:orders:<bean:write name="bean" property="orders"/>:index:-1')">
                  <%}else if (i-1 == beans.size()){%>
                          <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/orders/uparrow.png" title="<bean:message key="broadcast.uparrow.caption" bundle="<%=interfaces%>"/>" onClick="javascript:post('broadcast',anchor + ':_ORDERS:broadcastId:<bean:write name="bean" property="broadcastId"/>:orders:<bean:write name="bean" property="orders"/>:index:1')">
                          
                  <%}else{%>                          
                          <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/orders/uparrow.png" title="<bean:message key="broadcast.uparrow.caption" bundle="<%=interfaces%>"/>" onClick="javascript:post('broadcast',anchor + ':_ORDERS:broadcastId:<bean:write name="bean" property="broadcastId"/>:orders:<bean:write name="bean" property="orders"/>:index:1')">
                          <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/orders/downarrow.png" title="<bean:message key="broadcast.downarrow.caption" bundle="<%=interfaces%>"/>" onClick="javascript:post('broadcast',anchor + ':_ORDERS:broadcastId:<bean:write name="bean" property="broadcastId"/>:orders:<bean:write name="bean" property="orders"/>:index:-1')">
                  <%}%>
                 
                          
                </td>
               <td width="45px" nowrap >
                 <a class="modal-button" href="broadcast<%=extention%>?<%=anchor%>=_PREPARED_EDIT&broadcastId=<bean:write name="bean" property="broadcastId"/>" rel="{handler: 'iframe', size: {x: 600, y: 460},bookmark:'if(SqueezeBox.presets.target==0) {post(\'broadcast\',anchor + \':_VIEW\');}'}"> 
                    <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>"/>
                 </a>
                    <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('broadcast',anchor + ':_DELETE:broadcastId:<bean:write name="bean" property="broadcastId"/>')">
                </td>
                <%}%>
        </tr>
     </logic:iterate>   
   </logic:present> 
</table>
<logic:present name="BBroadcasts" >
<div class="toolCmd" style="padding-left:10px" align="left">
<table width="100%">
    <tr>
        <td align="left">
        <Strong><bean:message key="page.caption.total" bundle="<%=interfaces%>"/> <bean:write name="beans" property="totalRows"/></strong></td>
        <td align="right">
         <%String params = anchor + ":_VIEW";%>
        <jsp:include page="/paging.jsp">
            <jsp:param name="BEANS" value="BBroadcasts"/>
             <jsp:param name="PARAMS" value="<%=params%>"/>
            <jsp:param name="FORM" value="broadcast"/>
            <jsp:param name="METHOD" value="post"/>
        </jsp:include>
        </td>
    </tr>
</table>
</div>
</logic:present>
                    
                    
            </div>
        </div>              
    </div>
</div>    
</div>

</html:form>
