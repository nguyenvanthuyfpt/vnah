<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="template" method="post" >
<html:hidden name="template" property="type" />
<html:hidden name="template" property="creator" />
<div id="left">
     <div class="ctn-left">
        <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic" ><bean:message key="menu.top.template.caption" bundle="<%=interfaces%>"/></div></div>
         <logic:notEmpty name="BRuleTemplateTypes">
             <div class="csstable">
                 <table cellpadding="0" cellspacing="0" border="0">
                    <tr><td width="8" ><img src="<%=contextPath%>/images/newImages/i_47.gif" /></td>
                        <td bgcolor="#a0aec2" style="padding:0 7px"><span class="add">
                                
                                <a href="javascript:post('template',anchor + ':_PREPARED_CREATE');"><img src="<%=contextPath%>/images/newImages/i_16.gif" align="left" /><b><bean:message key="action.insert" bundle="<%=interfaces%>"/></b></a>
                                
                        </span></td>
                        <td width="8"><img src="<%=contextPath%>/images/newImages/i_46.gif" width="8" /></td>
                    </tr>
                 </table>
            </div>
        </logic:notEmpty>
        <div class="status">
        <p class="left-p-title"><bean:message key="cmd.search" bundle="<%=interfaces%>"/></p>
        <p>
        <input class="InputSearch" id="name" name="name"  onkeydown="if(event.keyCode==13){post('template',anchor + ':_SEARCH');messageImg('idTemplate');return false;}"  onFocus="this.value=''" type="text" >
        <input class="Button_Search" value="<bean:message key="categories.cmd.search" bundle="<%=interfaces%>"/>" type="button" onclick="post('template',anchor + ':_SEARCH');messageImg('idTemplate')">
        </p>
        <p class="left-p-title">
                <bean:message key="categorytemplateType.templateType" bundle="<%=interfaces%>"/></p>
        <p>            
                <html:select name="template" property="templateType_id" onchange="javascript:post('template',anchor + ':_SEARCH')"   >
                <html:option value="0"><bean:message key="status.all" bundle="<%=interfaces%>"/></html:option>
                <logic:present name="BTemplateTypes">
                <html:options collection="BTemplateTypes" property="id" labelProperty="name"/>
                </logic:present>
                </html:select>
        </p>
        
        <p class="left-p-title">
                <bean:message key="title.template.label.department" bundle="<%=interfaces%>"/>
        </p><p>
                <html:select name="template" property="departmentId" onchange="javascript:post('template',anchor + ':_SEARCH')"   >
                <html:option value="0"><bean:message key="status.all" bundle="<%=interfaces%>"/></html:option>
                <logic:present name="BDepartments">
                <html:options collection="BDepartments" property="id" labelProperty="name"/>
                </logic:present>
                </html:select>                
        </p>
        </div>
    </div>
 </div>
 </html:form>
    
        