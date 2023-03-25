<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="reporttotal" method="post">
<div class="list-report" >
    <table width="100%" cellspacing="2" cellpadding="4" border="0">
    <tr>
        <td>
            <ul>
            
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="14" />
                    <bean:message key="disability.report.list.divide.year" bundle="<%=interfaces%>"/>
                    <i>(Bi&#7875;u 14)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="15b" />
                    T&#7893;ng h&#7907;p chi ti&#7871;t c&#225;c d&#7841;ng v&#224; 
                    nguy&#234;n nh&#226;n khuy&#7871;t t&#7853;t chia theo &#273;&#7897; tu&#7893;i
                    <i>(Bi&#7875;u 15)</i>
                </li>
                
                 <li>
                    <html:radio  name="reporttotal"  property="reportType" value="16" />
                    T&#7893;ng h&#7907;p TKT t&#7915; 0 &#273;&#7871;n d&#432;&#7899;i 6 tu&#7893;i 
                    chia theo nhu c&#7847;u, nguy&#7879;n v&#7885;ng t&#236;nh h&#236;nh 
                    ti&#7871;p c&#7853;n d&#7883;ch v&#7909; y t&#7871;, tr&#432;&#7901;ng h&#7885;c                    
                    <i>(Bi&#7875;u 16)</i>
                </li>
                
                 <li>
                    <html:radio  name="reporttotal"  property="reportType" value="17" />
                    T&#7893;ng h&#7907;p NKT t&#7915; 0 &#273;&#7871;n d&#432;&#7899;i 16 tu&#7893;i 
                    chia theo tr&#236;nh &#273;&#7897; h&#7885;c v&#7845;n nhu c&#7847;u, nguy&#7879;n 
                    v&#7885;ng ch&#237;nh s&#225;ch &#273;ang &#273;&#432;&#7907;c h&#432;&#7903;ng
                    <i>(Bi&#7875;u 17)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="18" />
                    T&#7893;ng h&#7907;p NKT t&#7915; 16 &#273;&#7871;n d&#432;&#7899;i 60 tu&#7893;i 
                    chia theo tr&#236;nh &#273;&#7897; h&#7885;c v&#7845;n nhu c&#7847;u, nguy&#7879;n 
                    v&#7885;ng ch&#237;nh s&#225;ch &#273;ang &#273;&#432;&#7907;c h&#432;&#7903;ng
                    <i>(Bi&#7875;u 18)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="19" />
                    T&#7893;ng h&#7907;p NKT t&#7915; 60 tu&#7893;i tr&#7903; l&#234;n 
                    chia theo tr&#236;nh &#273;&#7897; h&#7885;c v&#7845;n nhu c&#7847;u, nguy&#7879;n 
                    v&#7885;ng ch&#237;nh s&#225;ch &#273;ang &#273;&#432;&#7907;c h&#432;&#7903;ng
                    <i>(Bi&#7875;u 19)</i>
                </li>
                
                <li>
                    <html:radio  name="reporttotal"  property="reportType" value="20" />
                    <bean:message key="disability.report.list.summary" bundle="<%=interfaces%>"/>
                    <i>(Bi&#7875;u 20)</i>
                </li>
            </ul>
        </td>
    </tr>
    </table>
</div>
</html:form>    