<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<bean:define name="rank" property="idNkt" id="id" type="java.lang.Integer" />
<div align="left" class="fullName">
	<strong>H&#7885; v&#224; t&#234;n NKT: <bean:write name="BNkts" property="nkt" /></strong>
</div>

<div id="div_rank">  	
		<jsp:include page="/disability/rank/list.jsp" />
</div>	 
