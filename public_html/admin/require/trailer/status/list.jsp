<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 

<logic:present name="BRmStatus" >
<% String statusIdsName = ""; %> 
<logic:notEmpty name="frmRequireRule" property="statusIdsNameTemp" >
  <bean:define name="frmRequireRule" property="statusIdsNameTemp" id="statusIdsNameTemp" type="java.lang.String"/>
  <% statusIdsName= statusIdsNameTemp ;%>
</logic:notEmpty>

   <bean:define name="BRmStatus" id="beans" type="com.form.FBeans"/>
<%  
    int i = beans.getFirstRecord(),j=0,k =0;
    statusIdsName = "," + statusIdsName + ",";       
    String checkeds = "";
%>

<table cellpadding="0" cellspacing="0" border="0">
    <tbody>
     <tr >
     
        <td  nowrap align="left" > 
            <html:checkbox name="frmRequireRule"  property="unIncharge" value='1'/><bean:message key="require.rule.admin.caption" bundle="<%=interfaces%>"/>     
           
       </td> 
        <td  align="left" nowrap><html:checkbox  name="frmRequireRule" property="deadline" value="1" /><bean:message key="rules.status.deadline.caption" bundle="<%=interfaces%>"/></td>          
                  
    </tr>        
<logic:iterate name="BRmStatus" id="bean" type="com.form.admin.require.rm_status.FRmStatus">    
    
    <%if(j%2==0){%>
          <tr>
          <%}%>
          <td>
          <%             
              checkeds = statusIdsName.indexOf("," + bean.getStatusId()+ "," )<0?"":"checked";             
          %>
          <input type="checkbox"  <%=checkeds%>  name="statusIds" id="statusIds" value="<%=bean.getStatusId()%>"  />
            <bean:write name="bean" property="name"/>
            <logic:notEqual name="bean" property="description" value="">
                    (<bean:write name="bean" property="description"/>)
            </logic:notEqual>
          </td>
          <%if(j%2>0){%> 
          </tr> 
          <%};j++;%>
       
    </logic:iterate>
    </tbody>
</table>

</logic:present>