<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/messages/js/searchEmail.js" ></script>
<script language="javascript">
function sendmail(obj){
obj.disabled=true;
checkedAll(document.createMessage.usersId);
var departmentID=0;
if(getObj('departmentID')!=null){
   departmentID=getObj('departmentID').value
}
post('createMessage',anchor + ':_CREATE:departmentID:'+departmentID)
}
</script>
                 <table class="tableForm" cellspacing="0" cellpadding="0" border="0" width="100%" style="border-collapse: collapse;" >
                      <tr>
                            <TD colspan="3" nowrap class="toolCmd" style="text-align:left">
                                        <html:button property="_CREATE" styleClass="button"  onclick="javascript:if(checkSubmit(this.form)){sendmail(this);}"> 
                                               <bean:message key="messages.form.send" bundle="<%=interfaces%>"/>
                                        </html:button>
                            </td>           
                      </tr>
                      
                      <tr>
                            <td nowrap ><bean:message key="messages.form.to_persion" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                            <td colspan="2" nowrap style="text-align:left">
                                    <jsp:include page="/messages/create/toAddress.jsp"/>
                            </td>           
                      </tr>
                      
                      <tr>
                            <td valign="top" nowrap style="padding-top:10px;text-align:right" >
                                 <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="app.remove.cation" bundle="<%=interfaces%>"/>" onClick="javascript:removeItem(document.createMessage.usersId,document.createMessage,0)">
                            </td>
                            <td id="tdRecieverDep" valign="top" align="left"  width="250px" > 
                               <select multiple="multiple" name="usersId" style="width:250px;height:50px" ondblclick="removeItem(this,this.form,0)" >
                                       <logic:notEmpty name="createMessage" property="empsRev" >
                                         <logic:iterate name="createMessage" property="empsRev" id="bRecv" type="com.form.messages.create.FCreate"> 
                                                <option value="<bean:write name="bRecv" property="toPertion"/>">
                                                    <bean:write name="bRecv" property="userFullName"/>
                                                </option>
                                        </logic:iterate>
                                    </logic:notEmpty> 
                               </select>
                            </td>            
                            <td align="left" width="100%" valign="top">

                            <p>
                            <label for="sendConpany">
                            <html:checkbox  name="createMessage" property="sendConpany" styleId="sendConpany" value="1" />
                            <bean:message key="cmd.message.sendcopany" bundle="<%=interfaces%>"/>
                            </label>
                            </p>
                            </td>
                      </tr>
                      
                      <tr>
                            <td nowrap >
                            <bean:message key="messages.list.title" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                            </td>
                            <td align="left"  nowrap width="100%" colspan="2" > <html:text name="createMessage" property="name" style="width:98%"/></td>            
                      </tr> 
                      <tr>
                            <td nowrap valign="top"><bean:message key="docs.download" bundle="<%=interfaces%>"/></td>
                            <td align="left" colspan="2">
                                <jsp:include page="/messages/create/upload.jsp" />
                            </td>            
                      </tr> 
                      </table>
                        <div>                      
                        <textarea  id="elm1" name="elm1" style="width:100%;height:250px" ><p><bean:write name="createMessage" property="fulltext"/></p></textarea>
                        </div>
                      <table class="tableForm" cellspacing="0" cellpadding="0" border="0" width="100%" style="border-collapse: collapse;" >
                       <tr>
                            <TD colspan="3" nowrap class="toolCmd" style="text-align:left">
                                <html:button property="_CREATE" styleClass="button"  onclick="javascript:if(checkSubmit(this.form)){sendmail(this);}"> 
                                       <bean:message key="messages.form.send" bundle="<%=interfaces%>"/>
                                </html:button>
                           </td>           
                      </tr>
                 </table>
        <html:hidden name="createMessage" property="secureId"/>
        <html:hidden name="createMessage" property="id"/> 
        <input type="hidden" name="fulltext" id="fulltext"/>

