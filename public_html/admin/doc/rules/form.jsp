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
<input type="hidden" name="statusIdsNameTemp" value="<bean:write name="docrule" property="statusIdsName"/>"/>
<input type="hidden" name="deadline1" value="<bean:write name="docrule" property="deadline"/>"/>
<input type="hidden" name="unIncharge1" value="<bean:write name="docrule" property="unIncharge"/>"/>
<table class="adminform" id="rulesTable" style="border-collapse: collapse" cellpadding="0" width="100%">  
<TBODY>
            <TR>                       
                <th width="210px"><bean:message key="rules.assignEmp.caption" bundle="<%=interfaces%>"/></th>
                <th width="210px"><bean:message key="rules.recieverEmp.caption" bundle="<%=interfaces%>"/></th>
                <th ><jsp:include page="/admin/doc/rules/tagOption.jsp"/></th>  
            </tr>                           
            <TR> 
               <td colspan="2" valign="top">
                    <table>  
                            <tr>
                                <td valign="top" colspan="2">
                                <select multiple="multiple" name="docBoss"  style="width:200px;height:172px">
                                 <logic:notEmpty name="docrule" property="listsBoss" >
                                     <logic:iterate name="docrule" property="listsBoss" id="beanBoss" type="com.form.admin.doc.rules.FDocRules"> 
                                            <option value="<bean:write name="beanBoss" property="userId"/>"><bean:write name="beanBoss" property="userFullName"/></option>
                                    </logic:iterate>
                                </logic:notEmpty> 
                                </select> 
                                <div>
                                 <a href="javascript:removeItem(document.docrule.docBoss,document.docrule,0)"><bean:message key="app.remove.cation" bundle="<%=interfaces%>"/></a>
                                 
                                 </div>
                                </td>   
                                 <td  valign="top">
                                           <strong>&#431;u ti&#234;n</strong>
                                           
                                           <div>
                                           <select multiple="multiple" name="priorities" style="width:200px;height:50px"> 
                                                                  <logic:notEmpty name="docrule" property="listPriorities" >
                                                                    <logic:iterate name="docrule" property="listPriorities" id="beanpriorities" type="com.form.admin.doc.rules.FDocRules"> 
                                                                        <option value="<bean:write name="beanpriorities" property="userId"/>"><bean:write name="beanpriorities" property="userFullName"/></option>
                                                                    </logic:iterate>
                                                                   </logic:notEmpty> 
                                                                </select>
                                                                
                                           </div>
                                           
                                           <div>
                                                                    <a href="javascript:removeItem(document.docrule.priorities,document.docrule,0)"><bean:message key="app.remove.cation" bundle="<%=interfaces%>"/></a>
                                           </div>
                                                        <select multiple="multiple" name="docofficer" style="width:200px;height:80px" ondblclick="AddUser(this.form.priorities,this)"> 
                                                                <logic:notEmpty name="docrule" property="listsOffice" >
                                                                        <logic:iterate name="docrule" property="listsOffice" id="beanOffcer" type="com.form.admin.doc.rules.FDocRules"> 
                                                                            <option value="<bean:write name="beanOffcer" property="userId"/>"><bean:write name="beanOffcer" property="userFullName"/></option>
                                                                        </logic:iterate>
                                                                </logic:notEmpty> 
                                                        </select> 
                                           <div>                                     
                                                        <a href="javascript:removeItemPrio(document.docrule.docofficer,document.docrule.priorities,document.docrule,0);removeItem(document.docrule.docofficer,document.docrule,0)"><bean:message key="app.remove.cation" bundle="<%=interfaces%>"/></a>
                                           </div>
                                        </td>
                            </tr>
                        </table>
                        <table>
                                <tr>
             
                                <td colspan="3">
                                <hr size="0">
                                </td>
                                </tr>     
        
                               <tr>
                                    <td width="33%" nowrap> <Strong><bean:message key="doc.review.readcation.title.caption" bundle="<%=interfaces%>"/> (<bean:message key="doc.header.title.caption" bundle="<%=interfaces%>"/>) </strong></td>
                                    <td colspan="2" > <html:text name="docrule" property="readcaption"  style="width:150px" size="250"/> </td>
                                </tr>
                                <tr>
                                    <td height="24px"><Strong><bean:message key="doc.header.status.transfer.caption" bundle="<%=interfaces%>"/></strong></td>
                                    <td colspan="2"> 
                                             <html:select property="status_id" styleClass="fieldSelect"  name="docrule">          
                                                <logic:present name="BStatus" >
                                                <logic:iterate name="BStatus" id="bean" type="com.form.admin.doc.category.status.FStatus">    
                                                    <%String statusId=bean.getId()+"";%>
                                                    <html:option value="<%=statusId%>">
                                                            <bean:write name="bean" property="name"/>
                                                            <logic:notEmpty name="bean" property="description" >
                                                                 (<bean:write name="bean" property="description"/>)
                                                            </logic:notEmpty>
                                                    </html:option>
                                                </logic:iterate>
                                                </logic:present>
                                              </html:select>
                                    </td>
                                </tr>
                                <tr>
                    <td >
                             <label for="store"><Strong><bean:message key="doc.header.status.store.caption" bundle="<%=interfaces%>" /></strong></label>     
                    </td><td colspan="2">
                            <html:select name="docrule" property="store" styleClass="fieldSelect" >
                            <html:option value="-2"><B><bean:message key="rules.select.statusEndNot" bundle="<%=interfaces%>" /></B></html:option>
                            <html:option value="-3"><B><bean:message key="rules.select.statusEnd" bundle="<%=interfaces%>" /></B></html:option>
                            <logic:present name="BStatus">
                             <logic:iterate name="BStatus" id="bean" type="com.form.admin.doc.category.status.FStatus"> 
                                        <%String statusId=bean.getId()+"";%>
                                        <html:option value="<%=statusId%>"> + 
                                                <bean:write name="bean" property="name"/>
                                                <logic:notEmpty name="bean" property="description" >
                                                     (<bean:write name="bean" property="description"/>)
                                                </logic:notEmpty>
                                        </html:option>
                                    </logic:iterate>                           
                            </logic:present>
                            </html:select>
                             
                    </td>
                    </tr>
                              <tr>
                                <td valign="top" align="right">
                                <html:checkbox  name="docrule" property="detail" styleId="detail" value="1" />
                               <Strong><bean:message key="doc.header.detail.caption" bundle="<%=interfaces%>"/></strong>
                               
                                
                                </td>
                                <td align="right">
                                 <html:checkbox  name="docrule" property="storeDrapt" styleId="storeDrapt" value="1" />
                                <Strong><bean:message key="doc.header.storedrapt.caption" bundle="<%=interfaces%>"/></strong>                             
                               
                             
                                </td>
                                <td align="right">
                                <html:checkbox  name="docrule" property="dossier" styleId="dossier" value="1" />
                                <Strong><bean:message key="doc.header.dossier.caption" bundle="<%=interfaces%>"/></strong>                                                            
                              
                        </td>
                    </tr>
                    
                     <tr>
                                <td valign="top">                                
                                <html:checkbox  name="docrule" property="docReply" styleId="docReply" value="1" />
                                <Strong><bean:message key="doc.header.docReply.caption" bundle="<%=interfaces%>"/></strong>     
                              
                                </td>
                                
                                <td>
                                 <html:checkbox  name="docrule" property="updateDraft" styleId="updateDraft" value="1" />   
                                 <Strong><bean:message key="doc.header.updateDraft.caption" bundle="<%=interfaces%>"/></strong> 
                                 </td>
                                 <td>                        
                                <html:checkbox  name="docrule" property="readOnly" styleId="readOnly" value="1" />
                                <Strong><bean:message key="doc.header.readOnly.caption" bundle="<%=interfaces%>"/></strong> 
                                </td>
                    </tr>
                     <tr>
                                <td valign="top" nowrap>     
                                <html:checkbox  name="docrule" property="forYouAssign" styleId="forYouAssign" value="1" />
                                <strong><bean:message key="doc.header.forYouAssign.caption" bundle="<%=interfaces%>"/></strong>
                                </td>
                                
                                
                       <td>
                               <html:checkbox  name="docrule" property="classify" styleId="classify" value="1" />
                               <strong><bean:message key="doc.header.classify.caption" bundle="<%=interfaces%>"/></strong>
                              
                        </td>
                        <td>
                                        <html:checkbox  name="docrule" property="defineFileEmit" styleId="defineFileEmit" value="1" />
                                        <Strong><bean:message key="doc.header.defineFileEmit.caption" bundle="<%=interfaces%>"/></strong>
                                </td>
                    </tr>
                     <tr>
                                
                                <td colspan="3">
                                           <html:checkbox  name="docrule" property="excuteNotView" styleId="excuteNotView" value="1" />
                                           <strong><bean:message key="doc.header.excuteNotView.caption" bundle="<%=interfaces%>"/></strong>
                                </td>
                               
                    </tr>
                    
                    </table>
               </td> 
               
                 <td valign="top" colspan="2" style="padding-bottom:20px" id="optionUsers">                                      
                            <jsp:include page="/admin/doc/rules/inforForm.jsp"/>
                </td> 
            </tr>                      
    </tbody>
</table>

                     

