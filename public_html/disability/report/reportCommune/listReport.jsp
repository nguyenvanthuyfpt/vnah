<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">    
    function excuteReport(anchorName,funcName){		
        post('reporttotal',anchor + ':'+ anchorName+':func:'+funcName);
    }
</script>
<html:form action="reporttotal" method="post">
<div class="padding-content">
	
    <ul id="tree">
        <li>
            <div class="bgr7"><a href="javascript:excutePostCategorys('_REPORT_COMMUNE')">
                    B&#225;o c&#225;o t&#7893;ng h&#7907;p theo c&#225;c tuy&#7871;n</a></div>
        </li>
    </ul>
	
    <br/>	
    
    <table width="100%" class="tableForm" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <th>
            <div class="content-calendar-2" align="left">
            	T&#7893;ng h&#7907;p theo c&#225;c Tuy&#7871;n
            </div>    
        </th>
    </tr>
    
    <tr>
        <td align="left" id="id_option_area">
            <jsp:include page="/disability/report/reportCommune/option_area.jsp" />
        </td>
    </tr>
	
    <tr>
        <td align="left">
            <bean:message key="disability.population.country.period" bundle="<%=interfaces%>"/> :
            <html:select styleClass="inputbox" name="reportcommune" property="period" style="width:30px">
                <html:option value="1">1</html:option>
                <html:option value="2">2</html:option>
            </html:select>
            <bean:message key="disability.population.country.yearOfPeriod" bundle="<%=interfaces%>"/> :
            <html:text name="reportcommune" property="yearoperiod" onblur="isYear(this);" style="width:40px" />
                   
            <logic:equal name="anchor" value="03">
                <html:button property="_CREATE" styleClass="button" onclick="excuteReport('_REPORT_TOTAL','_QUANLYCA');" >Xu&#7845;t excel</html:button>
            </logic:equal>
            <logic:notEqual name="anchor" value="03">
                <html:button property="_CREATE" styleClass="button" onclick="excuteReport('_REPORT_TOTAL','_REPORT_COMMUNE');" >Xu&#7845;t excel</html:button>
            </logic:notEqual>
        </td>
    </tr>
	
    <tr>
        <td height="20px"></td>
    </tr>  
</table>        
</div>
</html:form>  
