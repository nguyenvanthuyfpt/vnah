<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="reportcollect" method="post">
<script language="javascript">
    function excuteReport(anchorName,funcName){		
        post('reportcollect',anchor + ':'+ anchorName+':func:'+funcName);    
    }
</script>	

<div class="padding-content">	
    <ul id="tree">
        <li>
            <logic:equal name="anchor" value="03">
                <div class="bgr7"><a href="javascript:excutePostCategorys('_REPORT_COLLECT_DATA','_QUANLYCA')">
                    T&#7893;ng h&#7907;p nhu c&#7847;u NKT</a></div>
            </logic:equal>
            <logic:notEqual name="anchor" value="03">
                <div class="bgr7"><a href="javascript:excutePostCategorys('_REPORT_COLLECT_DATA','_REPORT_COMMUNE')">
                    T&#7893;ng h&#7907;p nhu c&#7847;u NKT</a></div>
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
        <td align="left" >
            Ch&#7885;n Tuy&#7871;n :
            <html:select styleClass="inputbox" name="reportCollect" property="id_tinh">
                <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
            </html:select>
        </td>
    </tr>
 
    <tr>
        <td align="left">
            <bean:message key="disability.population.country.period" bundle="<%=interfaces%>"/> :
            <html:select styleClass="inputbox" name="reportCollect" property="kyBC" style="width:30px">
                <html:option value="1">1</html:option>
                <html:option value="2">2</html:option>
            </html:select>
            <bean:message key="disability.population.country.yearOfPeriod" bundle="<%=interfaces%>"/> :
            <html:text name="reportCollect" property="namBC" onblur="isYear(this);" style="width:40px" />
        </td>
    </tr>
    
    
    <tr>
        <td>
          <div style="padding:16px 0">
          <logic:equal name="anchor" value="2">                
                <span class="bt_left_Search">
                      <span class="bt_right_Search">
                          <span class="bt_center_Search">
                              <html:button property="_CREATE" styleClass="button" onclick="excuteReport('_REPORT_COLLECT','_QUANLYCA');" >
                                  <bean:message key="action.export.report" bundle="<%=interfaces%>"/>
                              </html:button>
                          </span>
                      </span>
                  </span>
            </logic:equal>
            <logic:notEqual name="anchor" value="2">               
                <span class="bt_left_Search">
                      <span class="bt_right_Search">
                          <span class="bt_center_Search">
                            <html:button property="_CREATE" styleClass="button" onclick="excuteReport('_REPORT_COLLECT','_REPORT_COMMUNE');" >
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
        <td height="30px"><i>(T&#7893;ng h&#7907;p nhu c&#7847;u NKT thu&#7897;c di&#7879;n Qu&#7843;n l&#253; ca)</i></td>
    </tr>
    </logic:equal>
</table>
     
</div>
</html:form>  
