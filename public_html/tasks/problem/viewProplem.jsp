<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BTaskRoot" >
<table width="100%" class="assign"  cellpadding="0" cellspacing="0"  style="border-collapse: collapse; background-color: rgb(171, 189, 213);">
<tr>
    <td valign="top" style="padding-left:4px">
      <bean:message key="problem.title" bundle="<%=interfaces%>"/>
    </td>
    <td valign="top" style="padding-left:4px" colspan="3">
       <bean:write name="BTaskRoot" property="title" /> 
    </td>   
</tr>
<tr>
    <td valign="top" style="padding-left:4px">
      <bean:message key="problem.creator" bundle="<%=interfaces%>"/>
    </td>
    <td valign="top" style="padding-left:4px" colspan="3">
       <bean:write name="BTaskRoot" property="nameCreator" />
    </td>   
</tr>


<tr>
    <td valign="top" style="padding-left:4px">
           <bean:message key="problem.timeCreator" bundle="<%=interfaces%>"/>
    </td>
    <td valign="top" style="padding-left:4px">
       <bean:write name="BTaskRoot" property="timeCreate" /> 
    </td>
    <td valign="top" style="padding-left:4px">
        <bean:message key="problem.complate" bundle="<%=interfaces%>"/>
    </td>
    <td valign="top" style="padding-left:4px">
        <bean:write name="BTaskRoot" property="complete" /> %
    </td>
</tr>
<tr>
    <td valign="top" style="padding-left:4px">
         <bean:message key="problem.fromdate" bundle="<%=interfaces%>"/>
    </td>
    <td valign="top" style="padding-left:4px">
        <bean:write name="BTaskRoot" property="fromDate" />
    </td>
    <td valign="top" style="padding-left:4px">
       <bean:message key="problem.todate" bundle="<%=interfaces%>"/>
    </td>
    <td valign="top" style="padding-left:4px">
       <bean:write name="BTaskRoot" property="toDate" />
    </td>
</tr>

<logic:notEqual name="BTaskRoot" property="problem" value="" >
<tr>
    <td valign="top" colspan="4" style="padding-left:4px">
       <bean:define name="BTaskRoot" property="problem" id="conten" />    
       <%=conten%>
    </td>    
</tr>
</logic:notEqual>

</table>
</logic:present>