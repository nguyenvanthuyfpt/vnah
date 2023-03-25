<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>  
<logic:present name="BUsersDep">
<div align="left">
         <select multiple="multiple" style="width:250px;height:200px" ondblclick="javascript:if(this.form.radioEmp[0].checked){AddUser(this.form.bossUsersId,this)}else{AddUser(this.form.offUsersId,this);};">
                 <logic:iterate name="BUsersDep" id="user" type="com.form.admin.users.FUser"> 
                        <option value="<bean:write name="user" property="id"/>"><bean:write name="user" property="fullName"/></option>
                </logic:iterate>
        </select>
</div>
</logic:present>
