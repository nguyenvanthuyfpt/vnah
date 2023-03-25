<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>   
<script language="javascript">
    function showuserindep(obj){
        if(document.createMessage.usersId){
            AddUser(document.createMessage.usersId,obj);
        }else{
            document.messsagesList.toPertion.value=obj.value;
            document.messsagesList.userFullName.value=obj.options[obj.selectedIndex].text;
            post('messsagesList',anchor + ':_PREPARED_CREATE:id:0:app:0');
        }
    }
</script>
<input type="hidden" name="toPertion" id="toPertion" value="" />
<input type="hidden" name="userFullName" id="userFullName" value="" />
<table><tr><td nowrap>
<html:select name="createMessage" property="departmentID" styleId="departmentID" onchange="javascript:postAjax('messsagesList','mainUsers',anchor+':_SHOW_USER')" styleClass="inputbox" style="width:130px;"> 
    <logic:present name="BDepartments">
    <html:options collection="BDepartments" property="id" labelProperty="name"/>          
    </logic:present>           
</html:select>

<input type="button" name="" value=">>"  onclick="if(document.createMessage.usersId!=null){AddAllUser(document.createMessage.usersId,document.messsagesList.userList)}" />
    </td></tr>
    <tr><td>
    <div id="mainUsers" style="padding-top:10px;">
            <jsp:include page="/messages/create/options/form.jsp"/>    
    </div>
 </td>
 </tr>
</table>
