<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="reporttotal" method="post">
<div class="list-report" >
    <table width="100%" cellspacing="2" cellpadding="4" border="0">
    <tr>
        <td align="left">
            <ul>
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="01" />
                    <bean:message key="disability.report.list" bundle="<%=interfaces%>"/>                    
                    <bean:message key="disability.report.from" bundle="<%=interfaces%>"/> 0
                    <bean:message key="disability.report.to.under" bundle="<%=interfaces%>"/> 6                    
                    <bean:message key="disability.report.year" bundle="<%=interfaces%>"/> 
                    <i>(Bi&#7875;u 1)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="02" />
                    <bean:message key="disability.report.list" bundle="<%=interfaces%>"/>                    
                    <bean:message key="disability.report.from" bundle="<%=interfaces%>"/> 6
                    <bean:message key="disability.report.to.under" bundle="<%=interfaces%>"/> 13
                    <bean:message key="disability.report.year" bundle="<%=interfaces%>"/> 
                    <i>(Bi&#7875;u 2)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="03" />
                    <bean:message key="disability.report.list" bundle="<%=interfaces%>"/>                    
                    <bean:message key="disability.report.from" bundle="<%=interfaces%>"/> 13
                    <bean:message key="disability.report.to.under" bundle="<%=interfaces%>"/> 16
                    <bean:message key="disability.report.year" bundle="<%=interfaces%>"/> 
                    <i>(Bi&#7875;u 3)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="04" />
                    <bean:message key="disability.report.list" bundle="<%=interfaces%>"/>                    
                    <bean:message key="disability.report.from" bundle="<%=interfaces%>"/> 15
                    <bean:message key="disability.report.to.under" bundle="<%=interfaces%>"/> 16
                    <bean:message key="disability.report.year" bundle="<%=interfaces%>"/> 
                    <i>(Bi&#7875;u 4)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="05" />
                    <bean:message key="disability.report.list.nkt" bundle="<%=interfaces%>"/>
                    <bean:message key="disability.report.from" bundle="<%=interfaces%>"/> 15
                    <bean:message key="disability.report.for.sex" bundle="<%=interfaces%>"/>
                    <i>(Bi&#7875;u 5)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="06" />
                    <bean:message key="disability.report.list.nkt" bundle="<%=interfaces%>"/>
                    <bean:message key="disability.report.above" bundle="<%=interfaces%>"/>
                    <bean:message key="disability.report.for.sex" bundle="<%=interfaces%>"/>
                    <i>(Bi&#7875;u 6)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="07" />
                    <bean:message key="disability.report.list.nhao" bundle="<%=interfaces%>"/>
                    <bean:message key="disability.report.from" bundle="<%=interfaces%>"/> 18
                    <bean:message key="disability.report.year" bundle="<%=interfaces%>"/>
                    <bean:message key="disability.report.above" bundle="<%=interfaces%>"/>
                    <i>(Bi&#7875;u 7)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="08" />
                    <bean:message key="disability.report.list.nkt" bundle="<%=interfaces%>"/>
                    <bean:message key="disability.report.list.dangtat" bundle="<%=interfaces%>"/>
                    <i>(Bi&#7875;u 8)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="09" />
                    <bean:message key="disability.report.list.laodong.nhucau.vieclam" bundle="<%=interfaces%>"/>
                    <bean:message key="disability.report.from" bundle="<%=interfaces%>"/> 13
                    <bean:message key="disability.report.to" bundle="<%=interfaces%>"/>
                    <bean:message key="disability.report.under" bundle="<%=interfaces%>"/> 16
                    <bean:message key="disability.report.year" bundle="<%=interfaces%>"/>
                    <i>(Bi&#7875;u 9)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="10" />
                    <bean:message key="disability.report.list.laodong.nhucau.vieclam" bundle="<%=interfaces%>"/>
                    <bean:message key="disability.report.from" bundle="<%=interfaces%>"/> 15
                    <bean:message key="disability.report.to" bundle="<%=interfaces%>"/>
                    <bean:message key="disability.report.for.sex" bundle="<%=interfaces%>"/>
                    <i>(Bi&#7875;u 10)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="11" />
                    <bean:message key="disability.report.list.trinhdo.nhucau.giaoduc" bundle="<%=interfaces%>"/>
                    <bean:message key="disability.report.from" bundle="<%=interfaces%>"/> 6
                    <bean:message key="disability.report.to" bundle="<%=interfaces%>"/> 18
                    <bean:message key="disability.report.year" bundle="<%=interfaces%>"/>
                    <i>(Bi&#7875;u 11)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="12" />
                    <bean:message key="disability.report.list.old" bundle="<%=interfaces%>"/>
                    <bean:message key="disability.report.from" bundle="<%=interfaces%>"/> 60                    
                    <bean:message key="disability.report.year" bundle="<%=interfaces%>"/>
                    <bean:message key="disability.report.to" bundle="<%=interfaces%>"/>
                    79 <bean:message key="disability.report.year" bundle="<%=interfaces%>"/>
                    <i>(Bi&#7875;u 12)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="13" />
                    <bean:message key="disability.report.list.old.80" bundle="<%=interfaces%>"/>
                    <i>(Bi&#7875;u 13)</i>
                </li>                
            </ul>
        </td>
    </tr>
    <tr>
        <td align="left">
            <html:button property="_CREATE" styleClass="button" onclick="postAjax('reporttotal','MainCate',anchor + ':_REPORT_PARAM');">
                Xu&#7845;t excel
            </html:button>            
        </td>
    </tr>
    </table>
</div>
</html:form>