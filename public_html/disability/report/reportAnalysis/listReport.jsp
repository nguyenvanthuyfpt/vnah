<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">    
    function excuteReport(anchorName,funcName){		
        post('reportanalysis',anchor + ':'+ anchorName+':func:'+funcName);
    }
</script>
<html:form action="reportanalysis" method="post">
<div id="divSearchResult" style="display:none"></div>
<%

%>
<div class="padding-content">	
	<ul id="tree">
            <li>
                <logic:equal name="anchor" value="03">
                    <div class="bgr7"><a href="javascript:excutePostCategorys('_REPORT_ANALYSIS_DATA','_QUANLYCA')">
                        B&#225;o c&#225;o ph&#226;n t&#237;ch s&#7889; li&#7879;u NKT</a></div>
                </logic:equal>
                <logic:notEqual name="anchor" value="03">
                    <div class="bgr7"><a href="javascript:excutePostCategorys('_REPORT_ANALYSIS_DATA','_REPORT_COMMUNE')">
                        B&#225;o c&#225;o ph&#226;n t&#237;ch s&#7889; li&#7879;u NKT</a></div>
                </logic:notEqual>
            </li>    
	</ul>
	
	<br/>
	
   <table width="100%" class="tableForm" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <th>
            <div class="content-calendar-2" align="left">
            	Ph&#226;n t&#237;ch s&#7889; li&#7879;u NKT theo tuy&#7871;n
            </div>    
        </th>
    </tr>
	
    <tr>
        <td align="left" >
            Ch&#7885;n Tuy&#7871;n :
            <html:select styleClass="inputbox" name="reportAnalysis" property="id_tinh" >
                <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
            </html:select>
            <logic:equal name="anchor" value="03">
                <html:button property="_CREATE" styleClass="button" onclick="excuteReport('_REPORT_ANALYSIS','_QUANLYCA');" >Xu&#7845;t excel</html:button>
            </logic:equal>
            <logic:notEqual name="anchor" value="03">
                <html:button property="_CREATE" styleClass="button" onclick="excuteReport('_REPORT_ANALYSIS','_REPORT_COMMUNE');" >Xu&#7845;t excel</html:button>
            </logic:notEqual>
        </td>
    </tr>
 
    <tr>
        <td align="left">
            <bean:message key="disability.population.country.period" bundle="<%=interfaces%>"/> :
            <html:select styleClass="inputbox" name="reportAnalysis" property="kyBC" style="width:30px">
                    <html:option value="1">1</html:option>
                    <html:option value="2">2</html:option>
            </html:select>
            <bean:message key="disability.population.country.yearOfPeriod" bundle="<%=interfaces%>"/> :
            <html:text name="reportAnalysis" property="namBC" onblur="isYear(this);" style="width:40px" />
        </td>
    </tr>

    <logic:equal name="anchor" value="03">	
    <tr>
        <td height="30px"><i>(Ph&#226;n t&#237;ch s&#7889; li&#7879;u NKT thu&#7897;c di&#7879;n Qu&#7843;n l&#253; ca)</i></td>
    </tr>
    </logic:equal>
    
    <logic:notEqual name="anchor" value="03">
    <tr>
        <td height="20px">&nbsp;</td></tr>
    </logic:notEqual>
</table>
     
</div>
</html:form>  
