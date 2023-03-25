<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript">      
    function excuteReport(anchorName,funcName){
        //var fm = document.getElementsByName('fromMonth')[0].value;
        //var tm = document.getElementsByName('toMonth')[0].value;
        //var fy = document.getElementsByName('fromYear')[0].value;
        //var ty = document.getElementsByName('toYear')[0].value;
        //if (fy>ty){
            //alert(1);
            //alert(<bean:message key="alert.invalid.year.report" bundle="<%=interfaces%>"/>);
            //return false;
        /*} else if (fm>tm) {
            alert(<bean:message key="alert.invalid.month.report" bundle="<%=interfaces%>"/>);
            return false;*/
        //} else {
            post('reporttotal',anchor + ':'+ anchorName+':func:'+funcName);
        //}
    }
</script>
<html:form action="reporttotal" method="post">

<div class="padding-content">	
    <ul id="tree">
        <li>
            <logic:equal name="anchor" value="03">
                <div class="bgr7"><a href="javascript:excutePostCategorys('_REPORT_STATISTICS_DATA','_QUANLYCA')">
                    B&#225;o c&#225;o</a></div>
            </logic:equal>
            <logic:notEqual name="anchor" value="03">
                <div class="bgr7"><a href="javascript:excutePostCategorys('_REPORT_STATISTICS_DATA','_REPORT_COMMUNE')">
                    B&#225;o c&#225;o</a></div>
            </logic:notEqual>
        </li>
    </ul>
    
    <br/>
    <table width="100%" class="tableForm" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <th>
            <div class="content-calendar-2" align="left">
            	Ph&#7909; l&#7909;c 2: Danh s&#225;ch NKT h&#432;&#7903;ng l&#7907;i &#273;&#432;&#7907;c th&#7889;ng k&#234; trong th&#225;ng
            </div>    
        </th>
    </tr>
    <tr>
        <td align="left" id="id_option_area">
            <jsp:include page="/disability/report/reportStatistics/option_area.jsp" />
        </td>
    </tr>
    
    <tr>
        <td align="left">
            Ch&#7885;n l&#297;nh v&#7921;c
            <html:select styleClass="inputbox" name="reporttotal" property="fieldType" >
                <html:option value="1">Gi&#225;o d&#7909;c</html:option>
                <html:option value="2">Y t&#7871;</html:option>
                <html:option value="3">X&#227; h&#7897;i</html:option>
            </html:select>
        </td>
    </tr>        
    
    <tr>
        <td align="left">
            Th&#7889;ng k&#234; t&#7915; th&#225;ng 
            <html:select styleClass="inputbox" name="reporttotal" property="fromMonth" >
                <html:option value="1">1</html:option>
                <html:option value="2">2</html:option>
                <html:option value="3">3</html:option>
                <html:option value="4">4</html:option>
                <html:option value="5">5</html:option>
                <html:option value="6">6</html:option>
                <html:option value="7">7</html:option>
                <html:option value="8">8</html:option>
                <html:option value="9">9</html:option>
                <html:option value="10">10</html:option>
                <html:option value="11">11</html:option>
                <html:option value="12">12</html:option>                
            </html:select>
            n&#259;m <html:text name="reporttotal" property="fromYear" onblur="isYear(this);" size="4" style="text-align:center;" />
            &#273;&#7871;n th&#225;ng
            <html:select styleClass="inputbox" name="reporttotal" property="toMonth" >
                <html:option value="1">1</html:option>
                <html:option value="2">2</html:option>
                <html:option value="3">3</html:option>
                <html:option value="4">4</html:option>
                <html:option value="5">5</html:option>
                <html:option value="6">6</html:option>
                <html:option value="7">7</html:option>
                <html:option value="8">8</html:option>
                <html:option value="9">9</html:option>
                <html:option value="10">10</html:option>
                <html:option value="11">11</html:option>
                <html:option value="12">12</html:option>                
            </html:select>    
            n&#259;m <html:text name="reporttotal" property="toYear" onblur="isYear(this);" size="4" style="text-align:center;" />
        </td>
    </tr>
    
    <tr>
        <td align="left">
            <logic:equal name="anchor" value="03">
                <html:button property="_CREATE" styleClass="button" onclick="excuteReport('_REPORT_STATISTICS','_QUANLYCA');" >Xu&#7845;t excel</html:button>
            </logic:equal>
            <logic:notEqual name="anchor" value="03">
                <html:button property="_CREATE" styleClass="button" onclick="excuteReport('_REPORT_STATISTICS','_REPORT_COMMUNE');" >Xu&#7845;t excel</html:button>
            </logic:notEqual>
        </td>
    </tr>
    	
    <logic:equal name="anchor" value="03">	
    <tr>
        <td height="30px"><i>(T&#7893;ng h&#7907;p b&#225;o c&#225;o v&#7899;i s&#7889; li&#7879;u NKT thu&#7897;c di&#7879;n Qu&#7843;n l&#253; ca)</i></td>
    </tr>
    </logic:equal>
    
    <logic:notEqual name="anchor" value="03">
    <tr>
        <td height="20px">&nbsp;</td></tr>
    </logic:notEqual>   
    </table>
</div>
</html:form>  
