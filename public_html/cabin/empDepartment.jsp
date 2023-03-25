<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>   
<script language="javascript">
    function showuserindep(obj){
        if(getObj('userIdS')!=null){
            AddUser(getObj('userIdS'),obj);
        }else{
            document.cabin.toPertion.value=obj.value;
            document.cabin.userFullName.value=obj.options[obj.selectedIndex].text;
            post('cabin',anchor + ':_PREPARED_CREATE:id:0:type:1');
        }
    }
</script>
<input type="hidden" name="toPertion" id="toPertion" value="" />
<input type="hidden" name="userFullName" id="userFullName" value="" />
<table><tr><td nowrap>
    <html:select name="cabin" property="departmentID" styleId="departmentID" onchange="javascript:postAjax('cabin','mainUsers',anchor+':_SHOW')" styleClass="inputbox" style="width:130px;" > 
    <logic:present name="BDepartments">
    <html:options collection="BDepartments" property="id" labelProperty="name"/>          
    </logic:present>           
    </html:select>
    <input type="button" name="" value=">>"  onclick="if(document.cabin.userIdS!=null){AddAllUser(document.cabin.userIdS,document.cabin.userList)}" />
    </td></tr>
    <tr><td>
    <div id="mainUsers" style="padding-top:10px;">
            <jsp:include page="/cabin/userList.jsp"/>
    </div>
 </td>
 </tr>
</table>
