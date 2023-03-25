<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div id="infor">
<logic:present name="BRequire" >
<table  class="tab-review" width="100%" border="0" cellpadding="2" cellspacing="2">
    <tr>
        <td nowrap valign="top" ><strong><bean:message key="require.from.code.cation.endUser" bundle="<%=interfaces%>"/>:</strong></td>
        <td nowrap valign="top"><bean:write name="BRequire" property="code"/></td>
        <td nowrap valign="top"><strong><bean:message key="require.from.name.cation.endUser" bundle="<%=interfaces%>"/>:</strong></td>
        <td><bean:write name="BRequire" property="name"/></td>                            
    </tr>
    <tr>
        <td nowrap valign="top"><strong><bean:message key="require.from.datetimeto.cation" bundle="<%=interfaces%>"/>:</strong></td>
        <td nowrap valign="top"><bean:write name="BRequire" property="datetimto"/></td>
        <td nowrap valign="top"><Strong><bean:message key="require.from.datetimeFrom.cation" bundle="<%=interfaces%>"/>:</strong></td>
        <td nowrap valign="top"><bean:write name="BRequire" property="datetimFrom"/></td>
    </tr>
     <tr>
        <td nowrap valign="top"><strong><bean:message key="require.from.timeto.cation" bundle="<%=interfaces%>"/>:</strong></td>
        <td nowrap valign="top"><bean:write name="BRequire" property="timto"/></td>
        <td nowrap valign="top"><strong><bean:message key="require.from.timeFrom.cation" bundle="<%=interfaces%>"/>:</strong></td>
        <td nowrap valign="top"><bean:write name="BRequire" property="timFrom"/></td>
    </tr>  
     <logic:notEqual name="BRequire" property="dayOfWeek" value="">
       <bean:define name="BRequire" property="dayOfWeek" id="dayOfWeek" type="java.lang.String"/>
     <tr>
        <td nowrap valign="top"><strong><bean:message key="require.day.week.cation" bundle="<%=interfaces%>"/>:</strong></td>
        <td nowrap valign="top">      
        
                <%
                    if (dayOfWeek.indexOf("|2|")>=0){
                %>    
                            (<bean:message key="require.from.dayOfWeek.cation" bundle="<%=interfaces%>"/> 2 )
                 <%   }
                    if (dayOfWeek.indexOf("|3|")>=0){
                  %>
                        (<bean:message key="require.from.dayOfWeek.cation" bundle="<%=interfaces%>"/> 3)
                   <% }
                    if (dayOfWeek.indexOf("|4|")>=0){
                   %>
                        (<bean:message key="require.from.dayOfWeek.cation" bundle="<%=interfaces%>"/> 4)
                   <%
                    }
                    if (dayOfWeek.indexOf("|5|")>=0){
                    %>
                         (<bean:message key="require.from.dayOfWeek.cation" bundle="<%=interfaces%>"/> 5)        
                    <%
                    }
                    if (dayOfWeek.indexOf("|6|")>=0){
                    %>
                         (<bean:message key="require.from.dayOfWeek.cation" bundle="<%=interfaces%>"/> 6)        
                    <%
                    }
                    if (dayOfWeek.indexOf("|7|")>=0){
                    %>
                         (<bean:message key="require.from.dayOfWeek.cation" bundle="<%=interfaces%>"/> 7)
                    <%
                    }
                    if (dayOfWeek.indexOf("|8|")>=0){
                     %>
                         (<bean:message key="require.from.sunDay.cation" bundle="<%=interfaces%>"/>) 
                    <%
                    }
                %>        
        </td>        
    </tr>
    </logic:notEqual>   
    <tr>
        <td nowrap valign="top" colspan="4"><strong><bean:message key="require.from.content.cation.endUser" bundle="<%=interfaces%>"/>:</strong>
         (<logic:equal name="BRequire" property="repply" value="1">&#272;&#432;&#7907;c</logic:equal>
        <logic:notEqual name="BRequire" property="repply" value="1">Kh&#244;ng</logic:notEqual>
        <bean:message key="require.from.repply.cation" bundle="<%=interfaces%>"/>)
        </td>        
    </tr>  
     <tr>        
        <td nowrap valign="top" colspan="4"><bean:define name="BRequire" property="content" id="content" type="java.lang.String"/><%=content%></td>        
    </tr>      
    </table>                     
</logic:present>
</div>
<div style="background-color:#ABBDD5;"></div>



