<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
    function deleteItem(obj){
         var tab = obj.parentNode.parentNode;
        var id=tab.rowIndex;       
        tab.parentNode.deleteRow(id);
        countRow --;
    }
    
    function selectedSubmit(form,id,index){
    
         checkedAll(form.bossUsersId);
         checkedAll(form.offUsersId);       
         if (index==1){
            postAjax('reportRule','formEdit',anchor + ':_CREATE:ruleId:' + id);
         }else{
             postAjax('reportRule','formEdit',anchor + ':_EDIT:ruleId:' + id)
         }
         messageImg('formEdit');
    }    
        
</script>
<table width="100%" border="0px" cellpadding="0" cellspacing="0">
    <tr>
        <td valign="top" style="padding-left:4px">
            <table width="100%" class="adminheading">
                <tbody>
                    <tr>
                        <th class="app"><bean:message key="infor.task.report.caption" bundle="<%=interfaces%>"/></th>
                    </tr>
                </tbody>
            </table>
        </td>
    </tr>
    <TR>
        <TD id="MainRules"><jsp:include page="/admin/reports/rules/list.jsp"/></td>
    </tr>
</table>