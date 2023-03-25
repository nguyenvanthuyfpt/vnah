 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<div align="center">
<html:errors property="alert"  bundle="<%=interfaces%>" />
</div>
    <table class="adminlist" id="rulesTable" border="1" style="border-collapse: collapse" cellpadding="0" width="100%">  
     <TBODY>  
           
            <tr>
                <th width="210px"><bean:message key="rules.assignEmp.caption" bundle="<%=interfaces%>"/></th>
                <th width="210px"><bean:message key="rules.recieverEmp.caption" bundle="<%=interfaces%>"/></th>
                <th><bean:message key="rules.title.infor.caption" bundle="<%=interfaces%>"/></th>
                <th ></th>
            </tr>
             <logic:present name="BDocRules" >
            
             <%  int i = 0;%>
             <logic:iterate name="BDocRules" id="bean" type="com.form.admin.doc.rules.FDocRules"> 
              <%++i;%>
              <%
              String color="";
              if(bean.getWorkflowId()==1){
              color="rgb(231,231,231)";
              }else{
              color="#ffffff";
              }
              
              %>
              
                <tr style="background-color:<%=color%>">
                    <td style="padding-bottom: 4px;padding-top: 4px">
                        <select multiple style="width:200px;height:100px">        
                           <logic:iterate name="bean" property="listsBoss" id="beanBoss" type="com.form.admin.doc.rules.FDocRules"> 
                              <option value="<bean:write name="beanBoss" property="userId"/>"><bean:write name="beanBoss" property="userFullName"/></option>
                           </logic:iterate>
                       </select>
                    </td>
                    <td style="padding-bottom: 4px;padding-top: 4px" valign="top" >
                        <div>
                         <select multiple style="width:200px;height:50px">
                          <logic:iterate name="bean" property="listPriorities" id="beanPriorities" type="com.form.admin.doc.rules.FDocRules"> 
                              <option value="<bean:write name="beanPriorities" property="userId"/>"><bean:write name="beanPriorities" property="userFullName"/></option>
                           </logic:iterate>
                       </select>
                        </div>
                       <select multiple style="width:200px;height:50px">
                          <logic:iterate name="bean" property="listsOffice" id="beanOffcer" type="com.form.admin.doc.rules.FDocRules"> 
                              <option value="<bean:write name="beanOffcer" property="userId"/>"><bean:write name="beanOffcer" property="userFullName"/></option>
                           </logic:iterate>
                       </select>
                    </td>                    
                    <td valign="top" style="text-align:left">
                    <ul type="square">
                    <li><Strong><bean:message key="rules.title.caption" bundle="<%=interfaces%>"/>:</strong>
                        <logic:equal name="bean" property="active" value="1">   
                           <a href="javascript:document.docrule.active.value=0;postAjax('docrule','formList',anchor + ':_ACTIVE:ruleId:<%=bean.getRuleId()%>')"><span class="stypeActive"><bean:write name="bean" property="title" /></span></a>                        
                      </logic:equal>
                      <logic:notEqual name="bean" property="active" value="1">    
                           <a href="javascript:document.docrule.active.value=1;postAjax('docrule','formList',anchor + ':_ACTIVE:ruleId:<%=bean.getRuleId()%>')"> <span class="stypeUnActive"> <bean:write name="bean" property="title" /></span></a>
                      </logic:notEqual>
                      </li>
                      <li><Strong><bean:message key="doc.header.workflow.caption" bundle="<%=interfaces%>"/>:</strong>
                            <bean:write name="bean" property="workflowName" />                      
                      </li>
                      <li><Strong><bean:message key="doc.header.direct.caption" bundle="<%=interfaces%>"/>:</strong>
                            <logic:equal name="bean" property="direct" value="1">   
                            <bean:message key="doc.header.direct.caption" bundle="<%=interfaces%>"/>
                            </logic:equal>
                            <logic:notEqual name="bean" property="direct" value="1">    
                            <bean:message key="doc.header.Undirect.caption" bundle="<%=interfaces%>"/>
                            </logic:notEqual>
                      </li>
                      <li>
                        <Strong><bean:message key="users.list.active" bundle="<%=interfaces%>"/>:</strong> <bean:write name="bean" property="statusName" />
                      </li>
                      <li><Strong><bean:message key="doc.header.selectRecv.caption" bundle="<%=interfaces%>"/>: </strong>
                           <logic:equal name="bean" property="selectRecv" value="0">
                                <bean:message key="rules.unSelect.compo.caption" bundle="<%=interfaces%>"/>
                            </logic:equal>
                            <logic:equal name="bean" property="selectRecv" value="1">
                                <bean:message key="rules.select.compo.caption" bundle="<%=interfaces%>"/>
                            </logic:equal>
                      </li>
                      <li><Strong><bean:message key="doc.header.comment.caption" bundle="<%=interfaces%>"/>: </strong>
                        <logic:equal name="bean" property="comment" value="0">
                                <bean:message key="rules.UnComment.caption.combo" bundle="<%=interfaces%>"/>
                            </logic:equal>
                            <logic:equal name="bean" property="comment" value="1">
                                <bean:message key="rules.comment.caption.combo" bundle="<%=interfaces%>"/>
                            </logic:equal>
                      </li>
                      
                      <li><Strong><bean:message key="rules.selectDept.caption" bundle="<%=interfaces%>"/>: </strong>
                        <logic:equal name="bean" property="selectDept" value="0">
                                <bean:message key="rules.UnSelectDept.caption.combo" bundle="<%=interfaces%>"/>
                            </logic:equal>
                            <logic:equal name="bean" property="selectDept" value="1">
                                <bean:message key="rules.selectDept.caption.combo" bundle="<%=interfaces%>"/>
                            </logic:equal>
                      </li>
                      
                      
                      </ul>
                      </td>
                    <td  align="center" width="5%">                        
                        <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/update.png" onClick="javascript:postAjax('docrule','formEdit',anchor + ':_PREPARED_EDIT:ruleId:<%=bean.getRuleId()%>');messageImg('formEdit');" title="<bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>">
                        <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/delete.png" onClick="javascript:if (messageDelete()){postAjax('docrule','formList',anchor + ':_DELETE:ruleId:<%=bean.getRuleId()%>');messageImg('formList');}" title="<bean:message key="danhmuc.button.xoa" bundle="<%=interfaces%>"/>" >
                    </td>
                </tr>
             </logic:iterate>   
           </logic:present> 
    </tbody>
  </table>  
 
     