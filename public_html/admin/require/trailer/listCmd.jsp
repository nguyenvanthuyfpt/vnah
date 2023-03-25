 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<div align="center">
<html:errors property="alert"  bundle="<%=interfaces%>" />
</div>
    <table class="adminlist" id="rulesTable" border="0" style="border-collapse: collapse" cellpadding="0" width="100%">  
     <TBODY>  
           
            <tr>
                <th width="210px"><bean:message key="require.assignEmp.caption" bundle="<%=interfaces%>"/></th>
                <th width="210px"><bean:message key="require.recieverEmp.caption" bundle="<%=interfaces%>"/></th>
                <th><bean:message key="rules.title.infor.caption" bundle="<%=interfaces%>"/></th>
                <th ></th>
            </tr>
             <logic:present name="BRequireRules" >
            
             <%  int i = 0;%>
             <logic:iterate name="BRequireRules" id="bean" type="com.form.admin.require.trailer.FRequireTrailer"> 
              <%++i;%>
              <%
              String color="";
               if(i%2==0){
              color="rgb(231,231,231)";
              }else{
              color="#ffffff";
              }
              
              %>
              
                <tr style="background-color:<%=color%>">
                    <td style="padding-bottom: 4px;padding-top: 4px">
                        <select multiple style="width:200px;height:100px">        
                           <logic:iterate name="bean" property="listsBoss" id="beanBoss" type="com.form.admin.require.trailer.FRequireTrailer"> 
                              <option value="<bean:write name="beanBoss" property="userId"/>"><bean:write name="beanBoss" property="userFullName"/></option>
                           </logic:iterate>
                       </select>
                    </td>
                    <td style="padding-bottom: 4px;padding-top: 4px" valign="top" >
                        <div>
                         <select multiple style="width:200px;height:50px">
                          <logic:iterate name="bean" property="listPriorities" id="beanPriorities" type="com.form.admin.require.trailer.FRequireTrailer"> 
                              <option value="<bean:write name="beanPriorities" property="userId"/>"><bean:write name="beanPriorities" property="userFullName"/></option>
                           </logic:iterate>
                       </select>
                        </div>
                       <select multiple style="width:200px;height:50px">
                          <logic:iterate name="bean" property="listsOffice" id="beanOffcer" type="com.form.admin.require.trailer.FRequireTrailer"> 
                              <option value="<bean:write name="beanOffcer" property="userId"/>"><bean:write name="beanOffcer" property="userFullName"/></option>
                           </logic:iterate>
                       </select>
                    </td>                    
                    <td valign="top" style="text-align:left">
                    <ul type="square">
                        <li>
                            <strong><bean:message key="rules.title.caption" bundle="<%=interfaces%>"/>:</strong>
                            <logic:equal name="bean" property="active" value="1">   
                               <a href="javascript:document.frmRequireRule.active.value=0;postAjax('frmRequireRule','formList',anchor + ':_ACTIVE:ruleId:<%=bean.getRuleId()%>')"><span class="stypeActive"><bean:write name="bean" property="title" /></span></a>                        
                            </logic:equal>
                            <logic:notEqual name="bean" property="active" value="1">    
                               <a href="javascript:document.frmRequireRule.active.value=1;postAjax('frmRequireRule','formList',anchor + ':_ACTIVE:ruleId:<%=bean.getRuleId()%>')"> <span class="stypeUnActive"> <bean:write name="bean" property="title" /></span></a>
                            </logic:notEqual>
                        </li>                    
                      
                        <li>
                                <strong><bean:message key="require.rule.status.trailer.caption" bundle="<%=interfaces%>"/> :</strong> <bean:write name="bean" property="statusName" />
                        </li>
                        
                       <li>
                        <strong><bean:message key="require.rule.trailer.direct.caption" bundle="<%=interfaces%>"/> :</strong>
                        <logic:equal name="bean" property="direct" value="1">
                            <bean:message key="require.rule.excute.caption" bundle="<%=interfaces%>"/>
                            
                        </logic:equal>
                         <logic:notEqual name="bean" property="direct" value="1">
                                <bean:message key="require.rule.not.excute.caption" bundle="<%=interfaces%>"/>
                        </logic:notEqual>                        
                      </li>
                      <li>
                        <strong><bean:message key="require.rule.comment.caption" bundle="<%=interfaces%>"/> :</strong>
                        <logic:equal name="bean" property="comment" value="1">
                            <bean:message key="require.rule.excute.caption" bundle="<%=interfaces%>"/>
                            
                        </logic:equal>
                         <logic:notEqual name="bean" property="comment" value="1">
                                <bean:message key="require.rule.not.excute.caption" bundle="<%=interfaces%>"/>
                        </logic:notEqual>                        
                      </li>
                      <li>
                        <strong><bean:message key="require.rule.review.caption" bundle="<%=interfaces%>"/> :</strong>
                        <logic:equal name="bean" property="commentView" value="0">
                            <bean:message key="require.rule.not.excute.caption" bundle="<%=interfaces%>"/>
                            
                        </logic:equal>
                         <logic:equal name="bean" property="commentView" value="1">
                                <bean:message key="require.rule.review.private.caption" bundle="<%=interfaces%>"/>
                        </logic:equal>   
                         <logic:equal name="bean" property="commentView" value="2">
                                <bean:message key="require.rule.review.public.caption" bundle="<%=interfaces%>"/>
                        </logic:equal>   
                      </li>
                      <li>
                        <strong><bean:message key="require.rule.trailer.caption" bundle="<%=interfaces%>"/> :</strong>
                        <logic:equal name="bean" property="trailer" value="1">
                            <bean:message key="require.rule.trailer.private.caption" bundle="<%=interfaces%>"/>
                            
                        </logic:equal>
                        <logic:equal name="bean" property="trailer" value="2">
                            <bean:message key="require.rule.trailer.public.caption" bundle="<%=interfaces%>"/>                            
                        </logic:equal>
                         <logic:equal name="bean" property="trailer" value="0">
                                <bean:message key="require.rule.not.excute.caption" bundle="<%=interfaces%>"/>
                        </logic:equal>                        
                      </li>
                      </ul>
                      </td>
                    <td  align="center" width="5%">                        
                        <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/update.png" onClick="javascript:postAjax('frmRequireRule','formEdit',anchor + ':_PREPARED_EDIT:ruleId:<%=bean.getRuleId()%>');messageImg('formEdit');" title="<bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>">
                        <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/delete.png" onClick="javascript:if (messageDelete()){postAjax('frmRequireRule','formList',anchor + ':_DELETE:ruleId:<%=bean.getRuleId()%>');messageImg('formList');}" title="<bean:message key="danhmuc.button.xoa" bundle="<%=interfaces%>"/>" >
                    </td>
                </tr>
             </logic:iterate>   
           </logic:present> 
    </tbody>
  </table>  
 
     