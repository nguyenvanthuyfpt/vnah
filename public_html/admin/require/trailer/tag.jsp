<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>
<script language="javascript">
function tranfer(){
    postAjax('trailerStore','listDocs',anchor + ':_TRANSFER');
}
function addAllDoc(obj,objs){
  var member=',';
  if(typeof objs.length !="undefined"){
        for (i=0;i<objs.length;i++){
            objs[i].checked=obj.checked;
            if(objs[i].checked){
                member+=objs[i].value + ',';
            }else{
                member=member.replace(','+objs[i].value,"");
            }
        }
  }else{
    objs.checked=obj.checked;
    if(obj.checked){
        member+=objs.value + ',';
    }else{
        member=member.replace(','+obj.value,"");
    }
  }
document.trailerStore.docIds.value=member;
}
function addDoc(obj){
   var member=document.trailerStore.docIds.value;
   if(member=='') member=',';
   if(obj.checked){
        member+=obj.value+','
   }else{
        member=member.replace(','+obj.value,"");
   }
document.trailerStore.docIds.value=member; 
}

function mdotab(obj,params){
    empDep='';
    if(obj.className=='tab'){
        for(i=0;i<obj.parentNode.cells.length-1;i++){
                obj.parentNode.cells[i].className='tab';
        }
        obj.className='tabactive';       
        postAjax('frmRequireRule','MainRequireRules',anchor + ':' + params);
        messageImg('MainRequireRules');
    }    
}
</script>
<table style="border-bottom:#CCDAE3 solid 1px;"  align="left" cellpadding="0" cellspacing="0"><tr><td valign="bottom">
                 <div class="d7v0" align="center">                 
                     <table border="0" cellpadding="0" cellspacing="0" >
                       <tr valign="bottom">  
                         <td id="_EMPLOYEE_LYLICH" nowrap="nowrap" class="tabactive"  onclick="mdotab(this,'_VIEW_FORM');">
                            <div>&nbsp;&nbsp;<bean:message key="require.tag.rule.caption" bundle="<%=interfaces%>"/>&nbsp;&nbsp;</div>
                        </td>
                        <td id="_EMPLOYEE_DAOTAO" nowrap="nowrap" class="tab"  onclick="mdotab(this,'_SET');">
                            <div>&nbsp;<bean:message key="require.tag.rule.assign.caption" bundle="<%=interfaces%>"/>&nbsp;</div>
                        </td>                        
                         <td nowrap="nowrap" width="100%">&nbsp;</td>
                       </tr>
                     </table>
                 </div>	
 </td></tr></table> 


