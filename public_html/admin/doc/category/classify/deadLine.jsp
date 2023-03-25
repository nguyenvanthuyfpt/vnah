<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<input type="text" name="deadLine" id="deadLine"   value="<bean:write name="deadLine"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'deadLine','dd/mm/yyyy');return false;">