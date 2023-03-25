<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
    <html:select name="formMyContact" property="departmentID" styleId="departmentID" onchange="javascript:postAjax('formMyContact','mainUsers',anchor+':_SHOW_USER')" styleClass="inputbox" style="width:130px;"> 
    <html:option value="0"> <bean:message key="department.task.select.all" bundle="<%=interfaces%>"/> </html:option>
    <logic:present name="BDepartments">
    <html:options collection="BDepartments" property="id" labelProperty="name"/>          
    </logic:present>           
    </html:select>
    <div id="mainUsers" style="padding-top:10px;">
            <jsp:include page="/mail/menu/userlist.jsp"/>    
    </div>
