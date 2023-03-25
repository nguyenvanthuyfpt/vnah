<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<% request.setAttribute("SESSION.DENY.GUEST","FALSE");%>
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<body>	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><bean:message key="app.title" bundle="<%=interfaces%>"/></title>	
	<link type="text/css" href="css/stylesheet.css" rel="stylesheet" />
	<tiles:insert page="/layout/layoutHome.jsp" flush="true">
		<tiles:put name="tree" value="/disability/rightContent.jsp" />
		<tiles:put name="content" value="/disability/home.jsp" />
	</tiles:insert>
</body>
</html>
