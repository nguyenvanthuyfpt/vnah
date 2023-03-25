<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<script language="javascript">
function mdotabOption(obj,params){
    if(obj.className=='tab'){
        for(i=0;i<obj.parentNode.cells.length-1;i++){
                obj.parentNode.cells[i].className='tab';
        }
        obj.className='tabactive';       
        postAjax('docMainAssignRecv','changeOption',anchor + ':' + params);
        messageImg('changeOption')
    }    
}
</script>
<table   width="100%" cellpadding="0" cellspacing="0">   
    <tr>
            <td valign="top" id="docMain" height="180px">                
                  <jsp:include page="/doc/reviews/recv/docInfor.jsp"/>
            </td>                    
   </tr>  
    <html:form action="docReviewRecv" enctype="multipart/form-data" method="post">
   <tr>
       <td style="padding-top:10px">
       <logic:equal name="trackingInfor" value="0">
           <jsp:include page="/doc/reviews/recv/formEdit.jsp"/>    
           </logic:equal>
       </td>
    </tr> 
    </html:form>
</table>

         

            
            
  
 
 