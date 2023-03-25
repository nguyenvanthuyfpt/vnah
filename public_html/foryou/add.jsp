<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form target="_parent" action="forYou" method="post">
<script type="text/javascript">
function checkSubmit(form){  
  if(form.dateFrom.value=='' || form.dateTo.value==''){
        alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
        return false;
   }    
    obj= document.forYou ;
    temp = parseInt(obj.status.options[obj.status.selectedIndex].value)==1?obj.temp.value:obj.temp6.value;
    if (obj.publicInfor.checked){
        obj.titleMess.value = obj.temp1.value + " " + temp + " "  + obj.userIdTo.options[obj.userIdTo.selectedIndex].text ;
        obj.tempMess.value = obj.temp2.value + ":" + obj.workflowId.options[obj.workflowId.selectedIndex].text  + "<br>" + obj.temp5.value + ":" + obj.status.options[obj.status.selectedIndex].text + "<br>" + obj.temp3.value + ":" + obj.dateFrom.value + "  " + obj.temp4.value + ":" + obj.dateTo.value;
    }      
    return true;
}
</script>
<html:hidden  name="forYou"  property="id" />
<html:hidden  name="forYou"  property="titleMess" />
<html:hidden  name="forYou"  property="tempMess" />
<input type="hidden" name="temp" value="<bean:message key="foryou.who.caption" bundle="<%=interfaces%>"/>" />
<input type="hidden" name="temp1" value="<bean:write name="forYou" property="boss" />" />
<input type="hidden" name="temp2" value="<bean:message key="forYou.label.workflowId" bundle="<%=interfaces%>"/>" />
<input type="hidden" name="temp3" value="<bean:message key="forYou.label.dateFrom" bundle="<%=interfaces%>"/>" />
<input type="hidden" name="temp4" value="<bean:message key="forYou.label.dateTo" bundle="<%=interfaces%>"/>" />
<input type="hidden" name="temp5" value="<bean:message key="forYou.label.state" bundle="<%=interfaces%>"/>" />
<input type="hidden" name="temp6" value="<bean:message key="forYou.label.unforyou" bundle="<%=interfaces%>"/>" />
<div class="ct-celendar">   
<TABLE  style="border-collapse: collapse;line-height:24px;" cellpadding="0" cellspacing="0" width="100%">   
    <tr>
        <td width="120px"><bean:message key="forYou.label.boss" bundle="<%=interfaces%>"/>:</td>
        <td  align="left"><b><bean:write name="forYou" property="boss" /></b></td>
    </tr>
    <tr>
        <td ><bean:message key="forYou.label.workflowId" bundle="<%=interfaces%>"/>:</td>
        <td  align="left">
         <html:select styleClass="fieldSelect" name="forYou" property="workflowId"   onchange="javascript:postAjax('forYou','optionUser',anchor + ':_SELECT')">
                    <html:options collection="BWorkflows" property="workflowId" labelProperty="title"/>
        </html:select>
        </td>
    </tr>
    
    <tr>
        <td ><bean:message key="forYou.label.forYou" bundle="<%=interfaces%>"/>:</td>
        <td  align="left"  id="optionUser">
        
          <html:select property="userIdTo" styleClass="fieldSelect">
                <html:options collection="BUsers" property="id" labelProperty="fullName"/>
          </html:select>     
        
        </td>
    </tr>
   
    <tr>
        <td ><bean:message key="forYou.label.dateFrom" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td  align="left">
        <input type="text" name="dateFrom" id="dateFrom" value="<bean:write name="forYou" property="dateFrom"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'dateFrom','dd/mm/yyyy');return false;"> 
        </td>
    </tr>
    <tr>
        <td ><bean:message key="forYou.label.dateTo" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
         <td  align="left"><input type="text" name="dateTo" id="dateTo" value="<bean:write name="forYou" property="dateTo"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'dateTo','dd/mm/yyyy');return false;"/></td>
    </tr>
     <tr>
        <td ><bean:message key="forYou.label.problem" bundle="<%=interfaces%>"/> :</td>
        <td ><html:textarea  name="forYou" property="problem" style="width:300px;height:100px" /></td>
    </tr>
     <tr>
        <td ><bean:message key="forYou.label.state" bundle="<%=interfaces%>"/>:</td>
        <td >
          <html:select name="forYou" property="status" styleClass="fieldSelect">
                <html:option value="0"><bean:message key="forYou.label.state.2" bundle="<%=interfaces%>"/></html:option>
                <html:option value="1"><bean:message key="forYou.label.state.1" bundle="<%=interfaces%>"/></html:option>
          </html:select>     
          <bean:message key="forYou.header.public.caption" bundle="<%=interfaces%>"/>
          <html:checkbox  name="forYou" property="publicInfor" value="1" />
        </td>
    </tr>   
    <tr>
         <td></td>
         <td>
         <logic:equal name="forYou" property="id" value="0" >
          <html:button property="_CREATE" onclick="javascript:if(checkSubmit(this.form)){post('forYou',anchor + ':_CREATE');}" styleClass="button">
                <bean:message key="action.insert" bundle="<%=interfaces%>"/>
           </html:button>    
          </logic:equal> 
          <logic:notEqual name="forYou" property="id" value="0" >
           <html:button property="_EDIT" onclick="javascript:if(checkSubmit(this.form)){post('forYou',anchor + ':_EDIT');}" styleClass="button">
                <bean:message key="action.update" bundle="<%=interfaces%>"/>
           </html:button>    
          </logic:notEqual>
         
        </td>
    </tr>   
</table>

</div>
<html:hidden name="forYou" property="userIdFrom" />
</html:form>
