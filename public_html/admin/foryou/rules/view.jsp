<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<script language="javascript">

    function selectedSubmit(obj,form,id,index){
    
         checkedAll(form.bossUsersId);
         checkedAll(form.offUsersId);  
         if (index==1){
            postAjax('ruleforyou','formList',anchor + ':_CREATE:ruleId:' + id);
         }else{
             postAjax('ruleforyou','formList',anchor + ':_EDIT:ruleId:' + id)
         }
          messageImg('formList');
    }
  
</script>
<table width="100%" border="0px" cellpadding="0" cellspacing="0">
    <tr>
        <td valign="top" style="padding-left:4px">
            <table width="100%" class="adminheading">
                <tbody>
                    <tr>
                        <th class="app"><bean:message key="infor.foryou.rule.caption" bundle="<%=interfaces%>"/></th>
                    </tr>
                </tbody>
            </table>
        </td>
    </tr>
    <TR>
        <TD id="MainRules"><jsp:include page="/admin/foryou/rules/list.jsp"/></td>
    </tr>
</table>