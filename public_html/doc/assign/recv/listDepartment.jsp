<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div style="padding:6px">
<logic:present name="BDepartments">
<% String statusIdsName = ""; %> 
<logic:notEmpty name="docAssignRecv" property="departmentMembers" >
  <bean:define name="docAssignRecv" property="departmentMembers" id="departmentMembers" type="java.lang.String"/>
  <% statusIdsName= departmentMembers ;%>
</logic:notEmpty>

   <% statusIdsName = "," + statusIdsName + ",";
    String checkeds = ""; %>
   <table  style="border-collapse: collapse" cellpadding="0" cellspacing="0" width="100%">
        <tbody>            
            <tr>
                <td class="tdheader" width="3%" nowrap>#</td>
                <td class="tdheader"><bean:message key="departments.title.caption" bundle="<%=interfaces%>"/></td>
                <logic:notEmpty name="BTranfers" > 
                <bean:define name="BTranfers" id="bean1" type="com.form.admin.departments.FDepartment" />                
                 <logic:iterate name="bean1"  property="tranfers" id="beanTranfer" type="com.form.admin.doc.category.transfer.FTransfer"> 
                     <td class="tdheader" width="10%" nowrap >
                          <bean:write name="beanTranfer" property="name"/>
                    </td>                
                </logic:iterate>
              </logic:notEmpty>
 
            </tr>
         <% int i =0;%>   
        <logic:iterate name="BDepartments" id="bean" type="com.form.admin.departments.FDepartment"> 
           <% i++;%>
            <tr>
                <td width="3%" nowrap align="center" class="<%=(i%2>0?"tdcontent":"tdcontent1")%>"><%=i%></td>                
                <td class="<%=(i%2>0?"tdcontent":"tdcontent1")%>"><bean:write name="bean" property="name" /></td>                
                <logic:iterate name="bean"  property="tranfers" id="beanTranfer" type="com.form.admin.doc.category.transfer.FTransfer"> 
                   <%  
                   checkeds = statusIdsName.indexOf("," + beanTranfer.getId() + "#" + bean.getId()+ "," )<0?"":"checked"; %>
                    <td class="<%=(i%2>0?"tdcontent":"tdcontent1")%>" align="center" >
                          <input  type="checkbox" <%=checkeds%> name="disposeDepartment" id="disposeDepartment" onclick="checkChecked(document.docAssignRecv.disposeDepartment,<bean:write name="beanTranfer" property="id"/>,<%=bean.getId()%>);memberChecked(document.docAssignRecv.disposeDepartment,2)" value="<bean:write name="beanTranfer" property="id"/>#<bean:write name="bean" property="id"/>"/>
                    </td>                
                </logic:iterate>                
            </tr>
        </logic:iterate>
        </tbody>
    </table>
</logic:present>
</div>

