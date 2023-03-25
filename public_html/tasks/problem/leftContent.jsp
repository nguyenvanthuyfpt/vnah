<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
function postTabCabin(forward){
     post('change',anchor + ':'+forward);
     messageImg('right');
}
</script>

<html:form action="problemSelect" method="POST" >
<div id="left">  
         <div class="ctn-left">
            <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic"><bean:message key="menu.tree.task.title.caption" bundle="<%=interfaces%>"/></div></div>
              <div class="csstable">
              <logic:present name="Assign">
              <table cellpadding="0" cellspacing="0" border="0">
                <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_47.gif" /></td>
                    <td bgcolor="#a0aec2" style="padding:0 7px"><span class="add">
                      <a  href="#" onclick="javascript:post('problemMain',anchor + ':_PREPARED_CREATE:root:0:problemId:0')"><img src="<%=contextPath%>/images/newImages/i_16.gif" align="left" /><b><bean:message key="menu.top.rule.tasks.caption" bundle="<%=interfaces%>"/></b></a>
                    </span></td>
                    <td width="8"><img src="<%=contextPath%>/images/newImages/i_46.gif" width="8" /></td>
                </tr>
            </table>
            </logic:present>   
            
            </div>
                
                <%  if(request.getSession().getAttribute("02.01") !=null){ %>
                <div class="status">
                <div class="inbox">
                        <p onmouseover="className='row1'" onmouseout="className='row2'"  onclick="postTabCabin('_TASKS_ASSIGN:type:0');">
                                <logic:equal name="problem" property="type" value="0">
                                    <Strong><img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                                    <bean:message key="task.cmd.assign.caption" bundle="<%=interfaces%>"/></strong>
                                </logic:equal>
                                <logic:notEqual name="problem" property="type" value="0">
                                    <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                                    <bean:message key="task.cmd.assign.caption" bundle="<%=interfaces%>"/>
                                </logic:notEqual>
                               
                        </p>
                </div>
                </div>
                <%}%>
                
                <%  if(request.getSession().getAttribute("02.02") !=null){ %>
                <div class="status">
                <div class="inbox">
                        <p onmouseover="className='row1'" onmouseout="className='row2'"  onclick="postTabCabin('_TASKS_ASSIGN:type:1');">
                        <logic:equal name="problem" property="type" value="1">
                            <Strong><img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                            <bean:message key="doc.reci.title.caption" bundle="<%=interfaces%>"/></strong>
                        </logic:equal>
                        <logic:notEqual name="problem" property="type" value="1">
                            <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                            <bean:message key="doc.reci.title.caption" bundle="<%=interfaces%>"/>
                        </logic:notEqual>
                        </p>
                 </div>
                 </div>
                 <%}%>
                
                <%  if(request.getSession().getAttribute("02.03") !=null){ %>
                <div class="status">
                <div class="inbox">
                        <p onmouseover="className='row1'" onmouseout="className='row2'"  onclick="postTabCabin('_TASKS_ASSIGN_CATEGORY:type:2');">
                        
                        <logic:equal name="problem" property="type" value="2">
                            <Strong><img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                            <bean:message key="doc.reci.title.caption.category" bundle="<%=interfaces%>"/></strong>
                        </logic:equal>
                        <logic:notEqual name="problem" property="type" value="2">
                            <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                            <bean:message key="doc.reci.title.caption.category" bundle="<%=interfaces%>"/>
                        </logic:notEqual>
                        </p>
                 </div>
                 </div>
                 <%}%>
                 
        <logic:present name="Assign">
        <div class="csstable" >
             <table cellpadding="0" cellspacing="0" border="0" >
                <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_47.gif" /></td>
                    <td bgcolor="#a0aec2" style="padding:0 7px" nowrap><span class="add">
                        <Strong><bean:message key="app.folder.messages.employ.list" bundle="<%=interfaces%>"/></strong>
                    </span></td>
                    <td width="8"><img src="<%=contextPath%>/images/newImages/i_46.gif" width="8" /></td>
                    <td align="right" nowrap width="100%" style="padding-right:10px;"></td>
                </tr>
             </table>
        </div>
        <div class="status" id="tdMycontact">
                <jsp:include page="/tasks/problem/empsDepartment.jsp" />
        </div>
        </logic:present>
        </div> 
</div>     
</html:form>