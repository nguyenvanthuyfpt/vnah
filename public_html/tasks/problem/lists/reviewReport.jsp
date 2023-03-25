<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div style="background-color:#ABBDD5;margin:0px;">
<bean:define name="BReportsReview" id="beansRe" type="com.form.FBeans"/>
<ul style="background-color:#ABBDD5;margin:0px;" >                 
<logic:iterate name="BReportsReview" id="bean" type="com.form.tasks.report.FReport">    
     <li>
     <bean:write name="bean" property="timeReport"/>
     <strong><bean:message key="report.name.caption" bundle="<%=interfaces%>"/>:</strong>
     <bean:write name="bean" property="report"/>
     <logic:notEqual name="bean" property="fileName" value="">                   
                     <a href="javascript:post('report',anchor + ':_SAVE:id:<%=bean.getId()%>');remove('report',anchor)">  <img border="0" src="<%=contextPath%>/images/attach.gif"></a>
                  </logic:notEqual>  
     </li>                                            
</logic:iterate>
</ul> 
<% if (beansRe.size()==0){%>
    <span style="padding-left:4px"><bean:message key="review.report.notResult.caption" bundle="<%=interfaces%>"/></span>
<%}%>
</div>
