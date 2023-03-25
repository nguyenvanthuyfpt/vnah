<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>
<script language="javascript">
function mdotabChange(obj,params){   
    var statuses = "";
    if (getObj('statusIds')!=null){        
              var objCheck=document.docrule.statusIds;              
             for (j=0;j<objCheck.length;j++){                    
                 if (objCheck[j].checked){  
                       if (statuses!="") statuses +=",";                        
                        statuses += objCheck[j].value;
                 }
             }
             document.docrule.statusIdsNameTemp.value = statuses;
             if (document.docrule.deadline!=null){
                var deadline =  document.docrule.deadline.checked?document.docrule.deadline.value:0 ;  
             }
             var unIncharge = getUnIncharge();
             
             document.docrule.unIncharge1.value = unIncharge;              
             document.docrule.deadline1.value = deadline;              
             params += ":deadline:" + document.docrule.deadline1.value + ":unIncharge:" +  document.docrule.unIncharge1.value ;    
             
    }else{ 
            var deadline = document.docrule.deadline1.value;
            var unIncharge = document.docrule.unIncharge1.value;
            params += ":deadline:" + deadline + ":unIncharge:" + unIncharge
    } 
  
   if (getObj('title')!=null ){   
       if (!document.docrule.direct.checked){
            params +=":direct:0" ;
       }
       if (!document.docrule.excuteGroup.checked){
             params +=":excuteGroup:0" ;
       }
       if (!document.docrule.sendSms.checked){
             params +=":sendSms:0" ;
       }
       if (!document.docrule.reply.checked){
             params +=":reply:0" ;
       }
       if (!document.docrule.reviewfile.checked){
             params +=":reviewfile:0" ;
       }
       if (!document.docrule.defineFileEmit.checked){
             params +=":defineFileEmit:0" ;
       }
       
       
    }   

    if(obj.className=='tabOption'){       
        for(i=0;i<obj.parentNode.cells.length-1;i++){
                obj.parentNode.cells[i].className='tabOption';
        }
        obj.className='tabactiveOption';
        postAjax('docrule','optionUsers',anchor + ':' + params);       
        messageImg('optionUsers');
    }
}

function getUnIncharge(){
        var unIncharge ="";      
      if (document.docrule.unIncharge!=null){        
         if (typeof document.docrule.unIncharge.length!="undefined"){ 
            for (i=0;i<document.docrule.unIncharge.length;i++){                   
                   if (document.docrule.unIncharge[i].checked){                         
                        if (unIncharge!="") unIncharge +=",";
                        unIncharge += document.docrule.unIncharge[i].value;
                   }
            }
         }else{       
                unIncharge = document.docrule.unIncharge.checked?document.docrule.unIncharge.value:"";       
         }
        }
           
       return   unIncharge;
}

</script>  
 <table>
   <tr>  
     <td id="_EMPLOYEE_LYLICH" nowrap="nowrap" class="tabactiveOption"  onclick="mdotabChange(this,'_GET');">
         <bean:message key="rules.tagOption.infor.caption" bundle="<%=interfaces%>"/> |         
    </td>
    <td id="_EMPLOYEE_DAOTAO" nowrap="nowrap" class="tabOption" onclick="mdotabChange(this,'_PREPARE');">
        <bean:message key="app.emp.excute.caption" bundle="<%=interfaces%>"/>  | 
     </td>
      <td id="_EMPLOYEE_DAOTAO" nowrap="nowrap" class="tabOption" onclick="mdotabChange(this,'_PREPARE_STATUS');">          
          <bean:message key="rules.tagOption.status.title.caption" bundle="<%=interfaces%>"/>
     </td>    
     <td nowrap="nowrap">&nbsp;</td>
 </table>
                  


