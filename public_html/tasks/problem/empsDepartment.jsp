<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div class="choseRecv" >
        <div style="padding-top:1px;padding-bottom:1px">
            <html:select styleClass="inputbox" style="width:160px;" name="problem" property="departmentID" onchange="javascript:postAjax('problemSelect','mainUsersId',anchor+':_SHOW');">
            <logic:present name="BDepartments">
                <html:options collection="BDepartments" property="id" labelProperty="name"/>          
            </logic:present>           
            </html:select>   
        </div>
        <div id="mainUsersId"><jsp:include page="/tasks/problem/empsList.jsp"/></div>
</div>