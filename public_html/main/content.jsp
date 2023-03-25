<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
 <script language="javascript" >     
    function postDate(){
            getObj('showDate').innerHTML=getObj('createtime').value;
            postAjax('broadcast','mainList',anchor + ':_SHOW');
    }  
    function mdotabMycaontact(obj,form,params){  
    if(obj.className=='userOnlineNoneActive'){           
        for(i=0;i<obj.parentNode.cells.length-1;i++){ 
                obj.parentNode.cells[i].className='userOnlineNoneActive';
        }
        obj.className='userOnlineActive';                     
    }   
     postAjax('change','tdMycontact',anchor + ':' + params); 
}

</script>
<div id="winPopup" class="popup" align="center"></div>
<html:form action="docssend" method="post" />
<html:form action="docsrecv" method="post" />
<html:form action="messsagesList" method="post" />
<html:form action="problem" method="post" />
<table  cellpadding="3px" cellspacing="3px" width="100%" >    
    <tr>
        <td  valign="top" align="center" width="50%">
            <span id="11"  >
              <jsp:include page="/main/needDo.jsp" />
              </span>
            <span id="06.01"  >
                        <jsp:include page="/main/calendapublic.jsp" />
            </span>
        </td>
         <td  valign="top" align="center"  width="20%">
                <span id="03" >
                       <jsp:include page="/main/messages.jsp" />
                </span>
                 <span id="02"  >
                       <jsp:include page="/main/tasks.jsp" />
                </span>
                <span id="04" >
              <jsp:include page="/main/cabin.jsp" />
              </span>
               
               <span id="08" >
              <jsp:include page="/main/report.jsp" />
              </span>
                
                 
        </td>
        <td  valign="top" align="center"  width="30%">
              <span id="09.01"  >
                            <jsp:include page="/main/broadcastList.jsp" />
                </span>   
              
              <span id="07" >
                        <jsp:include page="/main/listTemplate.jsp" />
              </span>
                
                 <html:form action="directory" method="post"/>
                <html:form action="formMyContact" method="post" >
                <span id="05.02"  >
                        <jsp:include page="/main/myContact.jsp" />
                </span>
                </html:form>
                
                <span id="01">
                            <jsp:include page="/main/docsRecv.jsp" />
               </span>
                
             
        </td>
    </tr>
</table>
   
