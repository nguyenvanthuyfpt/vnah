<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BTotalsBranch" >
<bean:define name="BTotalsBranch" id="beans" type="com.form.FBeans"/>
<% if (beans.size()>0){ %>
<p><font style="color:#a0aec2"><b> <bean:message key="doc.docreport.form.branch" bundle="<%=interfaces%>"/></b></font></p>
<table cellpadding="0" cellspacing="0">    
      <logic:iterate name="BTotalsBranch" id="bean" type="com.form.doc.docreport.FDocReport">
          <tr><td nowrap><bean:write name="bean" property="statusName" />(<bean:write name="bean" property="amount" />)</td></tr>
      </logic:iterate>    
</table>
<%}%>
</logic:present>
