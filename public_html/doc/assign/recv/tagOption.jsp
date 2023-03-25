<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>
<table width="100%" cellpadding="0" cellspacing="0">
    <tr>
        <td align="left" style="padding-left:6px"><label for="review"><bean:message key="doc.header.assign.views.caption" bundle="<%=interfaces%>"/></label><input type="checkbox" name="review" id="review" value="1" checked></td>
        <td align="right">
                <table>
                   <tr>  
                     <td nowrap="nowrap">
                     <html:select name="docAssignRecv" property="departmentId" onchange="javascript:postAjax('docAssignRecv','listInforChecked',anchor +':_SHOW_ALL')" styleClass="fieldSelect" style="width:100px" >
                    <logic:present name="BDepartments">                    
                    <html:option value="0"> <bean:message key="departments.select.title.caption" bundle="<%=interfaces%>"/> </html:option>        
                    <html:options collection="BDepartments" property="id" labelProperty="name"/>
                    </logic:present>
                    </html:select>
                    <html:select name="docAssignRecv" property="groupId" onchange="javascript:postAjax('docAssignRecv','listInforChecked',anchor +':_SHOW_ALL')" styleClass="fieldSelect" style="width:100px" >
                        <html:option value="0"> <bean:message key="problem.select.group.caption" bundle="<%=interfaces%>"/> </html:option>        
                    <logic:present name="BGroups">
                    <html:options collection="BGroups" property="id" labelProperty="name"/>
                    </logic:present>
                    </html:select>
                     </td>
                     </tr>
                 </table>
        </td>
    </tr>
</table>
 
                  


