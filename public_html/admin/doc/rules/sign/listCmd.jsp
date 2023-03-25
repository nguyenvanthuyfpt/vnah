 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
    <div><jsp:include page="/admin/alert.jsp" /></div>
    <table class="adminlist" id="rulesTable" style="border-collapse: collapse" cellpadding="0" width="100%">  
     <TBODY>  
            <tr>
                    <th  width="31%" ><bean:message key="doc.sign.caption" bundle="<%=interfaces%>"/></th>                                      
                    <th colspan="2" ></th>
            </tr>
             <logic:present name="BSigns" >           
             <%  int i = 0;%>
             <logic:iterate name="BSigns" id="bean" type="com.form.admin.doc.rules.FDocRules"> 
              <%++i;%>
                <tr>
                    <td  style="padding-bottom: 4px;padding-top: 4px">
                        <bean:write name="bean" property="userFullName" />
                    </td>                     
                    <td align="center" width="3%">
                        <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/delete.png" onClick="javascript:if (messageDelete()) {postAjax('sign','formListSign',anchor + ':_DELETE:userList:<%=bean.getUserId()%>'); messageImg('formListSign');}" title="<bean:message key="danhmuc.button.xoa" bundle="<%=interfaces%>"/>" >                        
                    </td>
                </tr>
             </logic:iterate>   
           </logic:present> 
    </tbody>
  </table>    
     