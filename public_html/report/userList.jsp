<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BListUsers">
         <select multiple="multiple" name="userList" id="userList" style="width:150px;height:250px" ondblclick="showuserindep(this);">
                 <logic:iterate name="BListUsers" id="user" type="com.form.admin.reports.rules.FReportRule"> 
                        <option value="<bean:write name="user" property="userId"/>"><bean:write name="user" property="userFullName"/></option>
                </logic:iterate>
        </select>

</logic:present>
