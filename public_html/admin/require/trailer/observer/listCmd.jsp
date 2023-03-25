 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<table class="adminlist" id="rulesTable" style="border-collapse: collapse" cellpadding="0" width="100%">  
 <TBODY> 
        <tr>
            <td nowrap align="left" colspan="3"><jsp:include page="/admin/alert.jsp" /></td>
        </tr> 
        <tr>
            <th><bean:message key="require.observer.emp.caption" bundle="<%=interfaces%>"/></th>
            <th><bean:message key="require.observer.delete.caption" bundle="<%=interfaces%>"/></th>
            <th><br></th>
        </tr>
         <logic:present name="BRequiresObserver" >          
         <%  int i = 0;%>
         <logic:iterate name="BRequiresObserver" id="bean" type="com.form.admin.require.trailer.FRequireTrailer"> 
          <%++i;%>
            <tr>
                <td width="50%" nowrap style="padding-bottom: 4px;padding-top: 4px">
                    <bean:write name="bean" property="userFullName" />
                </td>   
                <td>
                    <logic:equal name="bean" property="delRm" value="1" >
                        <bean:message key="require.observer.del.rm..caption" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    <logic:notEqual name="bean" property="delRm" value="1" >
                        <bean:message key="require.observer.not.del.rm..caption" bundle="<%=interfaces%>"/>
                    </logic:notEqual>
                </td>
                <td align="center" width="5%">
                    <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/delete.png" onClick="javascript:if (messageDelete()){postAjax('frmRequireRule','formList',anchor + ':_DELETE_OBSERVER:userIdObserver:<%=bean.getUserId()%>');messageImg('formList');}" title="<bean:message key="danhmuc.button.xoa" bundle="<%=interfaces%>"/>" >                        
                </td>
            </tr>
         </logic:iterate>   
       </logic:present> 
</tbody>
</table>   
     