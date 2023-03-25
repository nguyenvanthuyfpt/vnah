<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>  
<logic:present name="BUsersDep">
         <select multiple="multiple" name="userList" id="userList" style="width:150px;height:250px" ondblclick="showuserindep(this);">
                 <logic:iterate name="BUsersDep" id="user" type="com.form.admin.users.FUser"> 
                        <option value="<bean:write name="user" property="id"/>"><bean:write name="user" property="fullName"/></option>
                </logic:iterate>
        </select>
</logic:present>
