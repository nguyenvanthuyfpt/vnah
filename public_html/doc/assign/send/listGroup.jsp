<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div style="padding:6px">
 <logic:present name="BGroups">
 <% String statusIdsName = ""; %> 
<logic:notEmpty name="docAssignSend" property="groupMembers" >
  <bean:define name="docAssignSend" property="groupMembers" id="groupMembers" type="java.lang.String"/>
  <% statusIdsName= groupMembers ;%>
</logic:notEmpty>

   <% statusIdsName = "," + statusIdsName + ",";
    String checkeds = ""; %>
   <table  style="border-collapse: collapse" cellpadding="0" cellspacing="0" width="100%">
        <tbody>            
            <tr>
               <td class="tdheader" width="3%" nowrap>#</td>
               <td class="tdheader" ><bean:message key="groups.tag.caption" bundle="<%=interfaces%>"/></td>
                   <logic:notEmpty name="BTranfers" > 
                    <bean:define name="BTranfers" id="bean1" type="com.form.admin.groups.FGroup" />                
                    <logic:iterate name="bean1"  property="tranfers" id="beanTranfer" type="com.form.admin.doc.category.transfer.FTransfer"> 
                         <% if (beanTranfer.getId()==0){%>
                             <td class="tdheader" width="10%" nowrap >
                                  <input type="checkbox" name="checkAllEmp"  value="1" onclick="checkAll(document.docAssignSend.disposeGroup,this);memberChecked(document.docAssignSend.disposeGroup,3)"/>
                            </td>                
                        <%}%>
                    </logic:iterate>
                  </logic:notEmpty>
            </tr>
         <% int i =0;%>   
        <logic:iterate name="BGroups" id="bean" type="com.form.admin.groups.FGroup"> 
           <% i++;%>
            <tr>
                <td width="3%" nowrap align="center" class="<%=(i%2>0?"tdcontent":"tdcontent1")%>"><%=i%></td>                
                <td class="<%=(i%2>0?"tdcontent":"tdcontent1")%>"><bean:write name="bean" property="name" /></td>                
                <logic:iterate name="bean"  property="tranfers" id="beanTranfer" type="com.form.admin.doc.category.transfer.FTransfer"> 
                   <% if (beanTranfer.getId()==0){
                   checkeds = statusIdsName.indexOf("," + beanTranfer.getId() + "#" + bean.getId()+ "," )<0?"":"checked"; 
                  %>
                    <td class="<%=(i%2>0?"tdcontent":"tdcontent1")%>" align="center" >
                          <input type="checkbox" <%=checkeds%> name="disposeGroup" id="disposeGroup" onclick="checkChecked(document.docAssignSend.disposeGroup,<bean:write name="beanTranfer" property="id"/>,<%=bean.getId()%>);memberChecked(document.docAssignSend.disposeGroup,3)" value="<bean:write name="beanTranfer" property="id"/>#<bean:write name="bean" property="id"/>"/>
                    </td>  
                   <%}%> 
                </logic:iterate>                
            </tr>
        </logic:iterate>
        </tbody>
    </table>
     </logic:present>
</div>