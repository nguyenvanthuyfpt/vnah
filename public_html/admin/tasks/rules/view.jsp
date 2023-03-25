<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">

    function selectedSubmit(form,id,index){
         checkedAll(form.bossUsersId);
         checkedAll(form.offUsersId);       
         if (index==1){
            postAjax('rules','formEdit',anchor + ':_CREATE:ruleId:' + id);
         }else{
             postAjax('rules','formEdit',anchor + ':_EDIT:ruleId:' + id)
         }
    }
   
  
</script>
<table width="100%" border="0px" cellpadding="0" cellspacing="0">
    <tr>
        <td valign="top" style="padding-left:4px">
            <table width="100%" class="adminheading">
                <tbody>
                    <tr>
                        <th class="app"><bean:message key="infor.task.rule.caption" bundle="<%=interfaces%>"/></th>
                    </tr>
                </tbody>
            </table>
        </td>
    </tr>
    <TR>
        <TD id="MainRules"><jsp:include page="/admin/tasks/rules/list.jsp"/></td>
    </tr>
</table>