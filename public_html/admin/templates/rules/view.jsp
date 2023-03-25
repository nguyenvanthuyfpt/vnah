<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript"> 
    
    function selectedSubmit(form,id,index){          
        if (form.usersId.length==0 ){
           alert(<bean:message key="alert.templates.userId" bundle="<%=interfaces%>"/>);    
        }else if (form.categoriesIds.options.selectedIndex <0 ){
            alert(<bean:message key="alert.templates.categoryId" bundle="<%=interfaces%>"/>);              
        }else{             
             checkedAll(form.usersId);
             if (index==1){
                postAjax('templateRule','formEdit',anchor + ':_CREATE:categoriesId:' + id);
             }else{
                 postAjax('templateRule','formEdit',anchor + ':_EDIT:categoriesId:' + id)
             }
             messageImg('formEdit');
        }       
    }
     
</script>
<table width="100%" border="0px" cellpadding="0" cellspacing="0">
    <tr>
        <td valign="top" style="padding-left:4px">
            <table width="100%" class="adminheading">
                <tbody>
                    <tr>
                        <th class="app"><bean:message key="infor.templates.caption" bundle="<%=interfaces%>"/></th>
                    </tr>
                </tbody>
            </table>
        </td>
    </tr>
    <TR>
        <TD id="MainRules"><jsp:include page="/admin/templates/rules/list.jsp"/></td>
    </tr>
</table>