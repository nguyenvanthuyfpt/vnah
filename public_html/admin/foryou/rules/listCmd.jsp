 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<jsp:include page="/admin/alert.jsp" />
    <table class="adminlist" id="ruleforyou" style="border-collapse: collapse" cellpadding="0">  
     <TBODY> 
            <tr>
                 <th  ><bean:message key="rules.assignEmp.caption" bundle="<%=interfaces%>"/></th>
                 <th  width="330px"><bean:message key="rules.recieverEmp.caption" bundle="<%=interfaces%>"/></th>               
            </tr>
            <logic:present name="BRules" >
              <bean:define name="BRules" id="beans" type="com.form.FBeans"/>
             <%  int i = beans.getFirstRecord();%>
             <logic:iterate name="BRules" id="bean" type="com.form.admin.foryou.rules.FRule"> 
              <%++i;%>
                <tr >
                     <td  valign="top" colspan="2">
                         <table width="100%" cellpadding="0" cellspacing="0">
                                <tr>
                                      <td>
                                            <bean:message key="rules.title.caption" bundle="<%=interfaces%>"/> :
                                             <logic:equal name="bean" property="active" value="1">   
                                               <a href="javascript:this.src='images/loading.gif';document.ruleforyou.active.value=0;postAjax('ruleforyou','formList',anchor + ':_ACTIVE:ruleId:<%=bean.getRuleId()%>')"><span class="stypeActive"><bean:write name="bean" property="title" /></span></a>                        
                                             </logic:equal>
                                              <logic:notEqual name="bean" property="active" value="1">    
                                                   <a href="javascript:this.src='images/loading.gif';document.ruleforyou.active.value=1;postAjax('ruleforyou','formList',anchor + ':_ACTIVE:ruleId:<%=bean.getRuleId()%>')"> <span class="stypeUnActive"> <bean:write name="bean" property="title" /></span></a>
                                              </logic:notEqual>
                                              
                                               <logic:notEqual name="bean" property="workflowId" value="1">   
                                                 (<bean:message key="menu.top.doc.send.caption" bundle="<%=interfaces%>"/>)
                                               </logic:notEqual>
                                               
                                               <logic:equal name="bean" property="workflowId" value="1">  
                                              (<bean:message key="menu.top.doc.recivers.caption" bundle="<%=interfaces%>"/>)
                                               </logic:equal>
                                              
                                         </td>
                                        <td width="5%" nowrap>                                                            
                                            <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/update.png" onClick="javascript:postAjax('ruleforyou','formEdit',anchor + ':_PREPARED_EDIT:ruleId:<%=bean.getRuleId()%>');messageImg('formEdit');" title="<bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>">
                                            <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/delete.png" onClick="javascript:if (messageDelete()){postAjax('ruleforyou','formList',anchor + ':_DELETE:ruleId:<%=bean.getRuleId()%>');messageImg('formList');}" title="<bean:message key="danhmuc.button.xoa" bundle="<%=interfaces%>"/>" >
                                        </td>
                                 </tr>
                          </table>
                    </td> 
                </tr>
                <tr>
                    <td >
                        <select multiple style="width:250px;height:80px">                                    
                           <logic:iterate name="bean" property="listsBoss" id="beanBoss" type="com.form.admin.foryou.rules.FRule"> 
                              <option value="<bean:write name="beanBoss" property="userId"/>"><bean:write name="beanBoss" property="userFullName"/></option>
                           </logic:iterate>
                       </select>
                    </td>
                    <td >
                       <select multiple style="width:250px;height:80px">
                          <logic:iterate name="bean" property="listsOffice" id="beanOffcer" type="com.form.admin.foryou.rules.FRule"> 
                              <option value="<bean:write name="beanOffcer" property="userId"/>"><bean:write name="beanOffcer" property="userFullName"/></option>
                           </logic:iterate>
                       </select>
                    </td>                                       
                </tr>
             </logic:iterate>   
           </logic:present> 
    </tbody>
  </table>  
  
     