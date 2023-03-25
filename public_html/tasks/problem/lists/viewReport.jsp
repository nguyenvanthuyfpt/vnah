<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<table  style="border-collapse:collapse;background-color:#ABBDD5;" align="left" cellpadding="0" cellspacing="0" border="0px" width="100%" >
<logic:present name="BReports" >
<bean:define name="BReports" id="beansR" type="com.form.FBeans"/>
<logic:notEmpty name="BReports" >
<tr><td>
<ul class="ulClassTaskReport">                 
<logic:iterate name="BReports" id="bean" type="com.form.tasks.report.FReport">                                              
       <li>
<bean:write name="bean" property="timeReport"/>
<logic:notEqual name="bean" property="fileName" value="">
 - 
<strong><bean:message key="doc.file.caption" bundle="<%=interfaces%>"/>: </strong>                                                                   
<a href="javascript:post('report',anchor + ':_SAVE:id:<%=bean.getId()%>');remove('report',anchor)"><bean:write name="bean" property="fileName"/></a>                                                                    
</logic:notEqual>  
- 
<strong> <bean:message key="problem.complate" bundle="<%=interfaces%>"/>: </strong><bean:write name="bean" property="complate"/>%
<br/>
<bean:write name="bean" property="report"/>
</logic:iterate> 
</ul> 
</td>
<td align="right" style="text-align:right">
    
</td>
</tr>
</logic:notEmpty>
<% if (beansR.size()==0){%>
    <span style="padding-left:4px"><bean:message key="report.notResult.caption" bundle="<%=interfaces%>"/></span>
<%}%>
</logic:present>
</table>
