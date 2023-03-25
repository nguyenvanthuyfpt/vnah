<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<HTML>
<OBJECT
    classid="clsid:3486AA7C-AB7C-42ED-9D99-549EBFE3A04D"
    codebase="VOffice.ocx"
    align=center
    name = "VOfficeScanner">
<param name="location" value="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=contextPath%>/doc/vOfficeScan.jsp">
<param name="SecureID" value="<%=me.getExtTagLong()%>">
<param name="SessionID" value="<%=me.getSessionID()%>">
</OBJECT>
</HTML>