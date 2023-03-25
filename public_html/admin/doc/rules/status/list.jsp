<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<html:hidden name="docrule" property="direct" />
<html:hidden name="docrule" property="excuteGroup" />
<html:hidden name="docrule" property="sendSms" />
<html:hidden name="docrule" property="reply" />
<html:hidden name="docrule" property="reviewfile" />
<logic:present name="BStatus" >
<% String statusIdsName = ""; %> 
<logic:notEmpty name="docrule" property="statusIdsNameTemp" >
  <bean:define name="docrule" property="statusIdsNameTemp" id="statusIdsNameTemp" type="java.lang.String"/>
  <% statusIdsName= statusIdsNameTemp ;%>
</logic:notEmpty>


<% String unIncharge = ""; %> 
<logic:notEmpty name="docrule" property="unIncharge" >
  <bean:define name="docrule" property="unIncharge" id="unInchargeValue" type="java.lang.String"/>
  <% unIncharge = unInchargeValue ;%>
</logic:notEmpty>


   <bean:define name="BStatus" id="beans" type="com.form.FBeans"/>
<%  
    int i = beans.getFirstRecord(),j=0,k =0;
    statusIdsName = "," + statusIdsName + ",";
    unIncharge  = "," + unIncharge + ",";
    String checkedIncharge = "";
    String checkeds = "";
%>

<table cellpadding="0" cellspacing="0" border="0">
    <tbody>
     <tr >
     <%String workflowId=com.lib.AppConfigs.DOCSRECV_WORKFLOWID+"";%>
        <logic:equal name="docrule" property="workflowId" value="<%=workflowId%>" >
        <td  align="left" nowrap><html:checkbox  name="docrule" property="deadline" value="1" /><bean:message key="rules.status.deadline.caption" bundle="<%=interfaces%>"/></td>          
        </logic:equal>
        
        <td  nowrap align="left" > 
            <logic:present name="BTransfer">
            <bean:define name="BTransfer" id="beansT" type="com.form.FBeans"/>
            <%if(beansT.size()>1){%>
            <logic:iterate name="BTransfer" id="bean" type="com.form.admin.doc.category.transfer.FTransfer"> 
              <%  checkedIncharge = unIncharge.indexOf("," + bean.getId()+ "," )<0?"":"checked"; %>
                    <%if(bean.getId()>0){ %>
                    <input type="checkbox" <%=checkedIncharge%>  name="unIncharge"  id="unIncharge" value='<bean:write name="bean" property="id" />'>     
                   <bean:write name="bean" property="name" />
                    <%}else{ if (bean.getId()==0)%>
                       <input type="checkbox" <%=checkedIncharge%>  name="unIncharge" id="unIncharge" value='<bean:write name="bean" property="id" />'><bean:message key="rules.status.unread.caption" bundle="<%=interfaces%>"/>
                    <%}%>
                    
            </logic:iterate>
            <%}%>
            </logic:present>
       </td>           
    </tr>        
<logic:iterate name="BStatus" id="bean" type="com.form.admin.doc.category.status.FStatus">    
    
    <%if(j%2==0){%>
          <tr>
          <%}%>
          <td>
          <%             
              checkeds = statusIdsName.indexOf("," + bean.getId()+ "," )<0?"":"checked";             
          %>
          <input type="checkbox"  <%=checkeds%>  name="statusIds" id="statusIds" value="<%=bean.getId()%>"  />
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