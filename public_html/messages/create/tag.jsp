<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
function mdotab(obj,depGroup,idRec){    
    if(obj.className=='tab'){
        for(i=0;i<obj.parentNode.cells.length-1;i++){
                obj.parentNode.cells[i].className='tab';
        }
        obj.className='tabactive';   	   
    }    
   // postAjax('createMessage','mainDepGroup',anchor + ':_SEARCH:depGroup:'+ depGroup + ':idRec:' + idRec); 
    getEmpDepOrGroup(idRec);
    message('MainMessage','Xin cho');
    
}
</script>
 <bean:define name="createMessage" property="idRec" id="idRec" type="java.lang.Integer"/>        
   <table cellpadding="0" cellspacing="0"><tr><td valign="bottom">
                 <div class="d7v0" align="center">
                     <table border="0" cellpadding="0" cellspacing="0" >
                       <tr valign="bottom">                      
                         <td id="_EMPLOYEE_LYLICH" nowrap="nowrap" class="tabactive"  onclick="mdotab(this,0,<%=idRec%>);">                                                
                         <div style="width:55px;text-align:center"><bean:message key="departments.title.caption" bundle="<%=interfaces%>"/></div></td>                                           
                         <!--<td id="_EMPLOYEE_DAOTAO" nowrap="nowrap" class="tab"  onclick="mdotab(this,1,<%=idRec%>);">                       
                          <div style="width:55px;text-align:center">Nh&#243;m</div></td> -->                       
                         <td nowrap="nowrap" width="100%">&nbsp;</td>
                       </tr>
                     </table>
                 </div>	
 </td></tr></table> 