<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<script type="text/javascript" src="<%=contextPath%>/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/tinymce.js"></script>
<script language="javascript">  
    function addUserNew(form,obj){                   
            id = obj.value; 
            result = false;
            for (i = 0; i < form.usersId.length; i++) {    
                if (id == form.usersId[i].value){
                    result = true;
                    break;
                }
            }          
           return  result;
 }
 function checkSubmit(form){
    if(form.title.value=='' || form.incharge.length==0 || form.usersId.length==0){
        alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
        return false;
    }
    document.problem.problem.value = subStringResult(tinyMCE.get('elm1').getContent());    
    return true;
}
</script>
<div class="padding-content">
<div id="mailcol">     
    <div class="tabmenu" id="container-1" >
            <div style="clear:both"></div>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="top" >
                        <ul id="ui-tabs-nav">  </ul>
                    </td>
                </tr>
            </table>
            
    </div>   
     <div id="fragment-1">       
     <div class="content-calendar">              
                <div  style="text-align:center">
                <table width="100%" border="0px" cellpadding="0" cellspacing="0">                            
                <html:form action="problem" enctype="multipart/form-data" method="post"> 
                <html:hidden name="problem" property="type" />
                <html:hidden name="problem" property="secureId" />
                <html:hidden name="problem" property="problem" /> 
                <TR>
                           <TD id="MainProblem" valign="top">                                       
                                    <jsp:include page="/tasks/problem/editForm.jsp"/>
                            </td>
                </tr>
                             </html:form>  
                </table>                        
                </div>
        </div>
</div>
     </div>
</div>


 
 