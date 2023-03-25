<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">  
    var detailReport=null;
    var idname=null;
    function navigator(pageIndex){     
        postAjax('problem','MainProblem',anchor + ':_SEARCH_PAGE:pageIndex:'+ pageIndex);        
    }    
     function getInforMess(type){
       postAjax('problem','MainProblem',anchor + ':_PREPARED_SAVE:type:' + type);      
    }    
   
     function getDetail(id){
       postAjax('formProblem','MainProblem',anchor + ':_PREPARED_SAVE:type:' + type);      
    }    


</script>

<html:form action="formProblem" />
<html:form action="report" />
<div class="padding-content">
<div id="mailcol">     
    <div class="tabmenu" id="container-1" >
            <div style="clear:both"></div>            
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="top" >
  <ul id="ui-tabs-nav">  
    <li class="optionTab"></li>
</ul>
                    </td>
                </tr>
            </table>            
    </div>   
     <div id="fragment-1">                                                  
                  <div class="content-calendar">
<script language="javascript">     
   function showReport(showId,assignId,problemId){ 
    
       if ((idname=="showId" + problemId) &&  (detailReport!=null && detailReport.innerHTML!='')){
             detailReport.innerHTML='';     
       }else{        
             if (detailReport!=null && detailReport.innerHTML!='') detailReport.innerHTML=''; 
             postAjax('problem',showId,anchor +':_REPORT:assignId:'+assignId+':problemId:'+ problemId);
             messageImg(showId)   
        }
        idname = showId ;
        detailReport=getObj(showId)
   }
</script>
                        <html:form action="problem" enctype="multipart/form-data">

                        <table width="100%" border="0px" cellpadding="0" cellspacing="0">
                            <tr>
                                <td valign="top">
                                         <div class="toolCmd" align="right" style="padding-right:10px;text-align:left" >     
                                              <jsp:include page="/tasks/problem/search.jsp"/>
                                       </div>
                                </td>
                            </tr>
                            <TR>
                                   <TD id="MainProblem">                                                                              
                                            <jsp:include page="/tasks/problem/mainList.jsp"/>
                                    </td>
                            </tr>                                     
                            </table>
                            <html:hidden name="problem" property="type"/>
                            <input type="hidden" name="app" value="2"                  
                            </html:form>  
                        </div>
                    
       
</div>
     </div>
 </div>




