<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/tinymce.js"></script>
<script type="text/javascript">
function checkSubmit(form){
    if(form.name.value=='' || (form.sendConpany.checked==false && form.usersId.length==0)){
        alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
        return false;
    }
   document.createMessage.fulltext.value = subStringResult(tinyMCE.get('elm1').getContent());    
   return true;
}
</script>

<html:form action="createMessage" target="_parent" enctype="multipart/form-data" method="post">
<div class="padding-content">
<div id="mailcol">     
    <div class="tabmenu" id="container-1" >
            <div style="clear:both"></div>
             <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="top" ><ul id="ui-tabs-nav"><li class="optionTab"></li></ul></td>
                </tr>
            </table>
            
    </div>   
    
     <div id="fragment-1">
        <div id="MainMessage" style="text-align:center"> 
               <div class="content-calendar">
                <table width="100%" border="0px" cellpadding="0" cellspacing="0"> 
                    <tr><td valign="top">
                        <jsp:include page="/messages/create/formEdit.jsp"/>            
                    </td></tr>
                </table>
               </div>
        </div>
     </div>
    
 </div>
</div>

 </html:form>