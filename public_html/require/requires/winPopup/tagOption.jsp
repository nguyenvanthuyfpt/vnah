<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>                
 <html:select name="frmRequireEmp" property="departmentId" onchange="javascript:postAjax('frmRequireEmp','listInforChecked',anchor +':_SHOW_ALL')" styleClass="fieldSelect" style="width:100px" >
<logic:present name="BDepartments">                    
<html:option value="0"> <bean:message key="departments.select.title.caption" bundle="<%=interfaces%>"/> </html:option>        
<html:options collection="BDepartments" property="id" labelProperty="name"/>
</logic:present>
</html:select>
<html:select name="frmRequireEmp" property="groupId" onchange="javascript:postAjax('frmRequireEmp','listInforChecked',anchor +':_SHOW_ALL')" styleClass="fieldSelect" style="width:100px" >
    <html:option value="0"> <bean:message key="problem.select.group.caption" bundle="<%=interfaces%>"/> </html:option>        
<logic:present name="BGroups">
<html:options collection="BGroups" property="id" labelProperty="name"/>
</logic:present>
</html:select>
                   
      
 
                  


