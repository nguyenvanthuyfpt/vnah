<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="docSendClassify"  method="post" >
<div align="center" id="erroralert"></div>
<logic:present name="BClassify">
<html:hidden name="docssend" property="id"  />
<bean:define name="docssend" property="id" id="docId" type="java.lang.Integer" />
<ul style="margin-left:15px" class="MainUl">
<logic:iterate name="BClassify" id="bean" type="com.form.admin.doc.category.classify.FClassify">
        <li>
        <input type="radio" name="storeClassify" id="storeClassify" value="<bean:write name="bean" property="id" />" />
        <span id="name<%=bean.getId()%>"> <bean:write name="bean" property="name" /></span>
        </li>
</logic:iterate> 
</ul>
<div align="left" style="padding:10px">
<% String  classifyOnclick = "classify(" + docId + ")" ;%>
    <html:button property="_DOC_ASSIGN_CREATE" styleClass="button" onclick="<%=classifyOnclick%>" style="width:100px;"  >
       <bean:message key="categoryclassify.classify" bundle="<%=interfaces%>"/>
    </html:button>
</div>
</logic:present>
</html:form>
