<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%
    String total = (String)request.getAttribute("total");    
%>
<div class="content-calendar">      
    <div class="content-calendar-2" >
        <a href="javascript:getSearchForm();">
            <bean:message key="common.label.search" bundle="<%=interfaces%>"/></a>
            | 
        <logic:equal value="1" name="timkiem" property="dataType">
        <a href="javascript:openWindow('searchdispeople',anchor +':_PREPARED');remove('searchdispeople',anchor);">
            <bean:message key="common.label.report" bundle="<%=interfaces%>"/>
            |
        </a>
        </logic:equal>
        <logic:equal value="1" name="timkiem" property="dataType">
        <a href="javascript:post('searchdispeople',anchor + ':_REPORT_TEMP:dataType:1');remove('searchdispeople',anchor);">        
            <bean:message key="common.label.report.temp" bundle="<%=interfaces%>"/>
            |
        </a>
        <a href="javascript:post('searchdispeople',anchor + ':_REPORT_TEMP_XA:dataType:1');remove('searchdispeople',anchor);">        
            <bean:message key="common.label.report.commune" bundle="<%=interfaces%>"/>
            |
        </a>
        </logic:equal>        
        <logic:equal value="2" name="timkiem" property="dataType">
        <a href="javascript:post('searchdispeople',anchor + ':_REPORT_TEMP:dataType:2');remove('searchdispeople',anchor);">        
            <bean:message key="common.label.report.temp" bundle="<%=interfaces%>"/>
            |
        </a>
        </logic:equal>
        
        <logic:notEqual value="1" name="timkiem" property="dataType">
        <a href="javascript:post('searchdispeople',anchor + ':_REPORT');remove('searchdispeople',anchor);">        
            <bean:message key="common.label.report" bundle="<%=interfaces%>"/></a>
            |
        </logic:notEqual>
        <a href="#"><bean:message key="common.label.total.result" bundle="<%=interfaces%>"/> <%=total%></a>
    </div>
                    
    <div>
        <logic:equal value="0" name="timkiem" property="dataType">
            <jsp:include page="/disability/search/listVal.jsp" />  
        </logic:equal>        
        <logic:equal value="1" name="timkiem" property="dataType">
            <jsp:include page="/disability/search/listDis.jsp" />  
        </logic:equal>
        <logic:equal value="2" name="timkiem" property="dataType">
            <jsp:include page="/disability/search/listPer.jsp" />  
        </logic:equal>
    </div>
</div> 
 
