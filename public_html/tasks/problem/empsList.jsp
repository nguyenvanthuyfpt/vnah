<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>  
<logic:present name="BUsersDep">
         <logic:notEqual name="problem" property="problemId" value="0" > 
             <logic:equal name="problem" property="type" value="1" > 
                <select multiple="multiple" style="width:160px;height:160px" ondblclick="AddUser(document.problem.usersId,this);AddUser(document.problem.incharge,this)">
                         <logic:iterate name="BUsersDep" id="user" type="com.form.tasks.problem.FUserPext"> 
                                <option value="<bean:write name="user" property="id"/>"><bean:write name="user" property="fullName"/></option>
                        </logic:iterate>
                </select>             
            </logic:equal>
            
                <logic:notEqual name="problem" property="type" value="1" > 
                <select multiple="multiple" style="width:160px;height:160px" ondblclick="if (!addUserNew(document.problem,this)){AddUser(document.problem.usersIdNew,this);};AddUser(document.problem.incharge,this);">
                     <logic:iterate name="BUsersDep" id="user" type="com.form.tasks.problem.FUserPext"> 
                            <option value="<bean:write name="user" property="id"/>"><bean:write name="user" property="fullName"/></option>
                    </logic:iterate>
            </select>
            </logic:notEqual>
        </logic:notEqual>  
        
        <logic:equal name="problem" property="problemId" value="0" > 
             <select multiple="multiple" style="width:160px;height:160px" ondblclick="AddUser(document.problem.usersId,this);AddUser(document.problem.incharge,this)">
                     <logic:iterate name="BUsersDep" id="user" type="com.form.tasks.problem.FUserPext"> 
                            <option value="<bean:write name="user" property="id"/>"><bean:write name="user" property="fullName"/></option>
                    </logic:iterate>
            </select>
        </logic:equal>
</logic:present>
