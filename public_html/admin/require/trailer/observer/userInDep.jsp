 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
 <select name="userId" style="width:120px">
            <logic:present name="BUsersDep">
          <logic:iterate name="BUsersDep" id="bean1" type="com.form.admin.users.FUser">   
                    <option value="<bean:write name="bean1" property="id" />" selected ><strong> <bean:write name="bean1" property="fullName"/></strong> </option>
          </logic:iterate>
           </logic:present>
 </select>
   