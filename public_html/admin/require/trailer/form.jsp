 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">         
function checkIds(obj,ids) {
    getObj('buttonRestore').style.display = (obj.checked)?'block':'none';
    checkment(ids);
}
function checkment(ids){
for (i = 0; i < ids.length; i++){          
        if(ids[i].checked==true){
            getObj('buttonRestore').style.display ='block';
            break;
        }
    }
}
function checkAllIdsCabin(checkname,exby) {
   if (checkname!=null){
         if(checkname.length>1){
         getObj('buttonRestore').style.display = (exby.checked)?'block':'none';
           for (i = 0; i < checkname.length; i++){          
             checkname[i].checked = exby.checked;    
           }    
         }else if(!checkname.length){       
           checkname.checked = exby.checked;
           getObj('buttonRestore').style.display = (exby.checked)?'block':'none';
         }
    }
}
</script>
<% int j=0;%>  
<input type="hidden" name="statusIdsNameTemp" value="<bean:write name="frmRequireRule" property="statusIdsNameTemp"/>"/>
<input type="hidden" name="deadline1" value="<bean:write name="frmRequireRule" property="deadline"/>"/>
<input type="hidden" name="unIncharge1" value="<bean:write name="frmRequireRule" property="unIncharge"/>"/>
<table class="adminform" id="rulesTable" border="0" style="border-collapse: collapse" cellpadding="0" width="100%">  
<TBODY>
            <TR>                       
                <th><bean:message key="require.assignEmp.caption" bundle="<%=interfaces%>"/></th>
                <th><bean:message key="require.recieverEmp.caption" bundle="<%=interfaces%>"/></th>
                <th ><jsp:include page="/admin/require/trailer/tagOption.jsp"/></th>  
            </tr>                           
            <TR> 
               <td colspan="2" valign="top" width="45%">
                    <table>  
                            <tr>
                                <td valign="top" >
                                <select multiple="multiple" name="docBoss"  style="width:200px;height:172px">
                                 <logic:notEmpty name="frmRequireRule" property="listsBoss" >
                                     <logic:iterate name="frmRequireRule" property="listsBoss" id="beanBoss" type="com.form.admin.require.trailer.FRequireTrailer"> 
                                            <option value="<bean:write name="beanBoss" property="userId"/>"><bean:write name="beanBoss" property="userFullName"/></option>
                                    </logic:iterate>
                                </logic:notEmpty> 
                                </select> 
                                <div>
                                 <a href="javascript:removeItem(document.frmRequireRule.docBoss,document.frmRequireRule,0)"><bean:message key="app.remove.cation" bundle="<%=interfaces%>"/></a>
                                 
                                 </div>
                                </td>   
                                 <td  valign="top">
                                      <strong>&#431;u ti&#234;n</strong>
                                           
                                           <div>
                                           <select multiple="multiple" name="priorities" style="width:200px;height:50px"> 
                                                                  <logic:notEmpty name="frmRequireRule" property="listPriorities" >
                                                                    <logic:iterate name="frmRequireRule" property="listPriorities" id="beanpriorities" type="com.form.admin.require.trailer.FRequireTrailer"> 
                                                                        <option value="<bean:write name="beanpriorities" property="userId"/>"><bean:write name="beanpriorities" property="userFullName"/></option>
                                                                    </logic:iterate>
                                                                   </logic:notEmpty> 
                                                                </select>
                                                                
                                           </div>
                                           
                                           <div>
                                                                    <a href="javascript:removeItem(document.frmRequireRule.priorities,document.frmRequireRule,0)"><bean:message key="app.remove.cation" bundle="<%=interfaces%>"/></a>
                                           </div>
                                                        <select multiple="multiple" name="docofficer" style="width:200px;height:80px" ondblclick="AddUser(this.form.priorities,this)"> 
                                                                <logic:notEmpty name="frmRequireRule" property="listsOffice" >
                                                                        <logic:iterate name="frmRequireRule" property="listsOffice" id="beanOffcer" type="com.form.admin.require.trailer.FRequireTrailer"> 
                                                                            <option value="<bean:write name="beanOffcer" property="userId"/>"><bean:write name="beanOffcer" property="userFullName"/></option>
                                                                        </logic:iterate>
                                                                </logic:notEmpty> 
                                                        </select> 
                                           <div>                                     
                                                        <a href="javascript:removeItemPrio(document.frmRequireRule.docofficer,document.frmRequireRule.priorities,document.frmRequireRule,0);removeItem(document.frmRequireRule.docofficer,document.frmRequireRule,0)"><bean:message key="app.remove.cation" bundle="<%=interfaces%>"/></a>
                                           </div>
                                 </td>
                            </tr>
                        </table>
                        <table>                    
                    </table>
               </td>                
               <td valign="top" align="left" id="optionUsers">
                 <jsp:include page="/admin/require/trailer/inforForm.jsp"/>         
               </td>  
            </tr>                      
    </tbody>
</table>

                     
