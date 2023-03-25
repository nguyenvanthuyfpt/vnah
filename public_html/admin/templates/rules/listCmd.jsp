 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<jsp:include page="/admin/alert.jsp" />
    <table class="adminlist" id="rulesTable" style="border-collapse: collapse" cellpadding="0" >  
        <TBODY>  
            <tr>
                        <th  width="255px"><bean:message key="rules.template.emp.excute.caption" bundle="<%=interfaces%>"/></th>
                        <th ><bean:message key="rules.template.category.caption" bundle="<%=interfaces%>"/></th>                        
                        
                        
                        <th  width="255px"><bean:message key="rules.template.emp.excute.caption" bundle="<%=interfaces%>"/></th>
                        <th ><bean:message key="rules.template.category.caption" bundle="<%=interfaces%>"/></th>                        
            </tr>            
             <logic:present name="BTemplateRules" >
              <bean:define name="BTemplateRules" id="beans" type="com.form.FBeans"/>
             <%  int i = beans.getFirstRecord();int sub=1;%>
             <logic:iterate name="BTemplateRules" id="bean" type="com.form.admin.templates.rules.FTemplatesRule"> 
              <%++i;++sub;%>
              <%if(sub%2==0){%>
                <tr>
               
                        <td  valign="top">
                          <select multiple style="width:250px;height:80px">
                              <logic:iterate name="bean" property="listsUserid" id="beanUser" type="com.form.admin.templates.rules.FTemplatesRule"> 
                                  <option value="<bean:write name="beanUser" property="userId"/>"><bean:write name="beanUser" property="userFullName"/></option>
                               </logic:iterate>
                         </select>
                        </td>
             
                        <td nowrap  valign="top">
                           <bean:write name="bean" property="categoryName" />
                           <div></div>
                             <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/update.png" onClick="javascript:postAjax('templateRule','formEdit',anchor + ':_PREPARED_EDIT:categoriesId:<%=bean.getCategoriesId()%>');messageImg('formEdit');" title="<bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>">
                            <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/delete.png" onClick="javascript:if (messageDelete()){postAjax('templateRule','formList',anchor + ':_DELETE:categoriesId:<%=bean.getCategoriesId()%>');messageImg('formList');}" title="<bean:message key="danhmuc.button.xoa" bundle="<%=interfaces%>"/>" >                                            
                        </td>  
             <%}else{%>
             <td  valign="top">
                          <select multiple style="width:250px;height:80px">
                              <logic:iterate name="bean" property="listsUserid" id="beanUser" type="com.form.admin.templates.rules.FTemplatesRule"> 
                                  <option value="<bean:write name="beanUser" property="userId"/>"><bean:write name="beanUser" property="userFullName"/></option>
                               </logic:iterate>
                         </select>
                        </td>
             
                        <td nowrap  valign="top">
                           <bean:write name="bean" property="categoryName" />
                           <div></div>
                             <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/update.png" onClick="javascript:postAjax('templateRule','formEdit',anchor + ':_PREPARED_EDIT:categoriesId:<%=bean.getCategoriesId()%>');messageImg('formEdit');" title="<bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>">
                            <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/delete.png" onClick="javascript:if (messageDelete()){postAjax('templateRule','formList',anchor + ':_DELETE:categoriesId:<%=bean.getCategoriesId()%>');messageImg('formList');}" title="<bean:message key="danhmuc.button.xoa" bundle="<%=interfaces%>"/>" >                                            
                        </td>  
                </tr>
              <%}%>
             </logic:iterate>   
           </logic:present>            
    </tbody>
  </table>  
  
     