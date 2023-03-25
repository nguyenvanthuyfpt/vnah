<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>  
<logic:present name="BUsersDep">
         <select multiple="multiple" name="userList" id="userList" class="combo-text" style="width:150px;height:250px" ondblclick="showuserindep(this);">
                 <logic:iterate name="BUsersDep" id="user" type="com.form.messages.create.FUserExt"> 
                        <option value="<bean:write name="user" property="id"/>"><bean:write name="user" property="fullName"/></option>
                </logic:iterate>
        </select>
</logic:present>
