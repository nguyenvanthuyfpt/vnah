<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div style="overflow: auto; height: 100px;margin-left:20px;">
            <bean:message key="forYou.label.boss" bundle="<%=interfaces%>"/> :
            <Strong><bean:write name="forYou" property="boss" /></strong>
            <br>
            <bean:message key="forYou.label.dateFrom" bundle="<%=interfaces%>"/>: <bean:write  name="forYou" property="dateFrom"/> 
            <bean:message key="forYou.label.dateTo" bundle="<%=interfaces%>"/>:
            <bean:write name="forYou" property="dateTo"/>
            <br>
            <bean:message key="forYou.label.problem" bundle="<%=interfaces%>"/> :
            <bean:write  name="forYou" property="problem" />
            <br>
</div>