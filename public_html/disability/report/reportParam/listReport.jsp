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
        <logic:equal name="anchor" value="03">
            <div class="bgr7"><a href="javascript:excutePostCategorys('_REPORT_PARAM','_QUANLYCA')">
                T&#7893;ng h&#7907;p theo &#273;i&#7873;u ki&#7879;n</a></div
        </logic:equal>
        <logic:notEqual name="anchor" value="03">
            <div class="bgr7"><a href="javascript:excutePostCategorys('_REPORT_PARAM','_REPORT_COMMUNE')">
                T&#7893;ng h&#7907;p theo &#273;i&#7873;u ki&#7879;n</a></div
        </logic:notEqual>
        </li>
    </ul>
    
    <br/>
    <table width="100%" class="tableForm" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <th>
            <div class="content-calendar-2" align="left">
                <bean:message key="common.parameter.report" bundle="<%=interfaces%>"/>
            </div>    
        </th>
    </tr>
    <tr>
        <td align="left" id="id_option_area">
            <jsp:include page="/disability/report/reportParam/option_area.jsp" />
        </td>
    </tr>
	
    <tr>
        <td align="left">
            <div class="list-report" >
                <table width="100%" cellspacing="2" cellpadding="4" border="0">
                <tr>
                    <td align="left"><b>B&#225;o c&#225;o chi ti&#7871;t</b></td>
                </tr>
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
                    <td align="left"><b>B&#225;o c&#225;o t&#7893;ng h&#7907;p</b></td>
                </tr>
                
                <tr>
                    <td>
                        <ul>
                        
                            <li>
                                <html:radio  name="reporttotal"  property="reportType" value="14" />
                                <bean:message key="disability.report.list.divide.year" bundle="<%=interfaces%>"/>
                                <i>(Bi&#7875;u 14)</i>
                            </li>
                            
                            <li>
                                <html:radio  name="reporttotal"  property="reportType" value="15" />
                                T&#7893;ng h&#7907;p chi ti&#7871;t c&#225;c d&#7841;ng v&#224; 
                                nguy&#234;n nh&#226;n khuy&#7871;t t&#7853;t chia theo &#273;&#7897; tu&#7893;i
                                <i>(Bi&#7875;u 15b)</i>
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
        </td>
    </tr>
    
    <tr>
        <td>
          <div style="padding:16px 0">
          <logic:equal name="anchor" value="02">                
                <span class="bt_left_Search">
                      <span class="bt_right_Search">
                          <span class="bt_center_Search">
                              <html:button property="_CREATE" styleClass="button" onclick="excuteReport('_REPORT_PARAM','_QUANLYCA');" >
                                  <bean:message key="action.export.report" bundle="<%=interfaces%>"/>
                              </html:button>
                          </span>
                      </span>
                  </span>
            </logic:equal>
            <logic:notEqual name="anchor" value="02">               
                <span class="bt_left_Search">
                      <span class="bt_right_Search">
                          <span class="bt_center_Search">
                            <html:button property="_CREATE" styleClass="button" onclick="excuteReport('_REPORT_PARAM','_REPORT_COMMUNE');" >
                              <bean:message key="action.export.report" bundle="<%=interfaces%>"/>
                            </html:button>
                          </span>
                      </span>
                  </span>
            </logic:notEqual>
          </div>
        </td>
    </tr>
    	
    <logic:equal name="anchor" value="03">	
    <tr>
        <td height="30px"><i>(T&#7893;ng h&#7907;p b&#225;o c&#225;o v&#7899;i s&#7889; li&#7879;u NKT thu&#7897;c di&#7879;n Qu&#7843;n l&#253; ca)</i></td>
    </tr>
    </logic:equal>
    </table>
</div>
</html:form>  
