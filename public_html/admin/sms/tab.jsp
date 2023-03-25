<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>
<script language="javascript">
function mdotab(obj,params){
    empDep='';
    if(obj.className=='tab'){
        for(i=0;i<obj.parentNode.cells.length-1;i++){
                obj.parentNode.cells[i].className='tab';
        }
        obj.className='tabactive';       
        postAjax('smsControler','mainSms',anchor + ':' + params);
        messageImg('MainDocRules');
    }
    
}
</script>
<table style="border-bottom:#CCDAE3 solid 1px;"  align="left" cellpadding="0" cellspacing="0"><tr><td valign="bottom">
                 <div class="d7v0" align="center">                 
                     <table border="0" cellpadding="0" cellspacing="0" >
                       <tr valign="bottom">  
                         <td id="_PERMISION_SMS" nowrap="nowrap" class="tabactive"  onclick="mdotab(this,'_PERMISION_SMS');">
                            <div>&nbsp;&nbsp;<bean:message key="category.sms.permision" bundle="<%=interfaces%>"/>&nbsp;&nbsp;</div>
                        </td>
                        <td id="_LOG_SMS" nowrap="nowrap" class="tab"  onclick="mdotab(this,'_LOG_SMS');">
                            <div>&nbsp;<bean:message key="category.sms.log" bundle="<%=interfaces%>"/>&nbsp;</div>
                        </td>
                         <td nowrap="nowrap" width="100%">&nbsp;</td>
                       </tr>
                     </table>
                 </div>	
 </td></tr></table> 


