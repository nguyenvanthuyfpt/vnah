<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
    function selectedSubmit(form,id,index){
         checkedAll(form.docBoss);
         checkedAll(form.docofficer);  
         checkedAll(form.priorities);          
         var  params = "";         
         if (getObj('title')!=null && getObj('title').value!=''){              
             if (index==1){
                postAjax('frmRequireRule','formList',anchor + ':_CREATE:ruleId:' + id + ":deadline:"  + form.deadline1.value + ":unIncharge:" + form.unIncharge1.value +  params);
             }else{
                 postAjax('frmRequireRule','formList',anchor + ':_EDIT:ruleId:' + id  + ":deadline:"  + form.deadline1.value + ":unIncharge:" + form.unIncharge1.value + params)
             }
        }else{
            alert('chua nhap ten luong yeu cau');
        }
    }    
</script>
<html:form action="frmRequireRule" method="POST">
<table width="100%" cellpadding="0" cellspacing="0">   
    <tr>
        <td valign="top" style="padding-left:4px">
            <table width="100%" class="adminheading">
                <tbody>
                    <tr>
                        <th class="app"><bean:message key="require.rule.list.caption" bundle="<%=interfaces%>"/></th>
                    </tr>
                </tbody>
            </table>
        </td>
    </tr>  
     <tr>
           <td valign="top">                                       
                <jsp:include page="/admin/require/trailer/tag.jsp"/>                                       
           </td>
     </tr>  
     <TR>
        <TD id="MainRequireRules"><jsp:include page="/admin/require/trailer/list.jsp"/></td>
    </tr>
</table>
</html:form>
