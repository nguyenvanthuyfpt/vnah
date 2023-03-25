<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>  
<logic:present name="BDepartments">
         <select multiple="multiple" name="departmentId" id="departmentId" style="width:250px;height:130px" ondblclick="AddUser(this.form.depIdS,this);">
                 <logic:iterate name="BDepartments" id="bean" type="com.form.admin.departments.FDepartment"> 
                        <option value="<bean:write name="bean" property="id"/>"><bean:write name="bean" property="name"/></option>
                </logic:iterate>
        </select>
</logic:present>
