<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>
<script language="javascript">
function mdotabChange(obj,params){   
    var statuses ="";
     if (getObj('statusIds')!=null){        
              var objCheck=document.frmRequireRule.statusIds;              
             for (j=0;j<objCheck.length;j++){                    
                 if (objCheck[j].checked){  
                       if (statuses!="") statuses +=",";                        
                        statuses += objCheck[j].value;
                 }
             }
             document.frmRequireRule.statusIdsNameTemp.value = statuses;
             if (document.frmRequireRule.deadline!=null){
                var deadline =  document.frmRequireRule.deadline.checked?document.frmRequireRule.deadline.value:0 ;  
             }
             
             if (document.frmRequireRule.unIncharge!=null){
                var unIncharge =  document.frmRequireRule.unIncharge.checked?document.frmRequireRule.unIncharge.value:0 ;  
             }
             document.frmRequireRule.unIncharge1.value = unIncharge;              
             document.frmRequireRule.deadline1.value = deadline;              
             params += ":deadline:" + document.frmRequireRule.deadline1.value + ":unIncharge:" +  document.frmRequireRule.unIncharge1.value ;    
             
    }else{ 
            var deadline = document.frmRequireRule.deadline1.value;
            var unIncharge = document.frmRequireRule.unIncharge1.value;
            params += ":deadline:" + deadline + ":unIncharge:" + unIncharge
    } 
    
    if(obj.className=='tabOption'){       
        for(i=0;i<obj.parentNode.cells.length-1;i++){
                obj.parentNode.cells[i].className='tabOption';
        }
        obj.className='tabactiveOption';
        postAjax('frmRequireRule','optionUsers',anchor + ':' + params);       
        messageImg('optionUsers');
    }
}
</script>  
 <table>
   <tr>  
     <td id="_EMPLOYEE_LYLICH" nowrap="nowrap" class="tabactiveOption"  onclick="mdotabChange(this,'_GET');">
         <bean:message key="rules.tagOption.infor.caption" bundle="<%=interfaces%>"/> |         
    </td>
    <td id="_EMPLOYEE_DAOTAO" nowrap="nowrap" class="tabOption" onclick="mdotabChange(this,'_PREPARE');">
        <bean:message key="app.emp.excute.caption" bundle="<%=interfaces%>"/> 
     </td>   
     <td id="_EMPLOYEE_DAOTAO" nowrap="nowrap" class="tabOption" onclick="mdotabChange(this,'_PREPARE_STATUS');">          
          <bean:message key="rules.tagOption.status.title.caption" bundle="<%=interfaces%>"/>
     </td> 
     <td nowrap="nowrap">&nbsp;</td>
 </table>
                  


