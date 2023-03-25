 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<jsp:include page="/admin/alert.jsp" />
    <table class="adminlist" id="rulesTable" style="border-collapse: collapse" cellpadding="0">  
     <TBODY>  
           
            <tr>
                        <th  ><bean:message key="rules.assignEmp.caption" bundle="<%=interfaces%>"/></th>
                        <th  width="330px"><bean:message key="rules.recieverEmp.caption" bundle="<%=interfaces%>"/></th>                    
            </tr>
            <logic:present name="BRules" >
              <bean:define name="BRules" id="beans" type="com.form.FBeans"/>
             <%  int i = beans.getFirstRecord();%>
             <logic:iterate name="BRules" id="bean" type="com.form.admin.tasks.rules.FRules"> 
              <%++i;%>
                <tr>
                         <td  colspan="2">
                            <table width="100%" cellpadding="0" cellspacing="0">
                                <td>
                                         <logic:equal name="bean" property="active" value="1">   
                                               <a href="javascript:document.rules.active.value=0;postAjax('rules','formList',anchor + ':_ACTIVE:ruleId:<%=bean.getRuleId()%>')"> <span class="stypeActive"> <bean:write name="bean" property="title" /></span></a>                        
                                          </logic:equal>
                                          <logic:notEqual name="bean" property="active" value="1">    
                                               <a href="javascript:document.rules.active.value=1;postAjax('rules','formList',anchor + ':_ACTIVE:ruleId:<%=bean.getRuleId()%>')"> <span class="stypeUnActive"> <bean:write name="bean" property="title" /></span></a>
                                          </logic:notEqual>
                                </td>
                                <td width="5%" nowrap>                                        
                                        <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/update.png" onClick="javascript:postAjax('rules','formEdit',anchor + ':_PREPARED_EDIT:ruleId:<%=bean.getRuleId()%>');messageImg('formEdit');" title="<bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>"> 
                                        <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/delete.png" onClick="javascript:if (messageDelete()){postAjax('rules','formList',anchor + ':_DELETE:ruleId:<%=bean.getRuleId()%>');messageImg('formList');}" title="<bean:message key="danhmuc.button.xoa" bundle="<%=interfaces%>"/>" >
                                </td>
                            </table>
                        </td>
                </tr>
                <tr>
                    <td style="padding-bottom: 4px;padding-top: 4px">
                        <select multiple style="width:250px;height:80px">                                    
                           <logic:iterate name="bean" property="listsBoss" id="beanBoss" type="com.form.admin.tasks.rules.FRules"> 
                              <option value="<bean:write name="beanBoss" property="userId"/>"><bean:write name="beanBoss" property="userFullName"/></option>
                           </logic:iterate>
                       </select>
                    </td>
                    <td style="padding-bottom: 4px;padding-top: 4px">
                       <select multiple style="width:250px;height:80px">
                          <logic:iterate name="bean" property="listsOffice" id="beanOffcer" type="com.form.admin.tasks.rules.FRules"> 
                              <option value="<bean:write name="beanOffcer" property="userId"/>"><bean:write name="beanOffcer" property="userFullName"/></option>
                           </logic:iterate>
                       </select>
                    </td>                                      
                </tr>
             </logic:iterate>   
           </logic:present>            
    </tbody>
  </table>  
 
     