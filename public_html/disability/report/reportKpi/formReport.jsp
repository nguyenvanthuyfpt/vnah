<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">    
    function excuteReport(form){
        var chk = document.getElementsByName("extend")[0];
        var extend = "0";
        if (chk!=undefined) {
            extend = chk.checked?"1":"0";
        }
        
        /*
        var periodType = form.periodType.value;
        if (periodType==4){
           if (form.createDateFrom.value==''||form.createDateTo.value==''){
               alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
               return false;
           }
        }
        */
        
        post('reportkpi',anchor + ':_REPORT:extend:'+extend);
        remove('reportkpi',anchor);
        remove('reportkpi','extend');
    }
    
    function changeOption(subanchor) {
        postAjax('reportkpi','form', anchor + ':_REPORT_SELECT_TINH:subFunction:'+subanchor);        
    }
</script>

<bean:define id="subanchor" name="reportkpi" property="subFunction" type="java.lang.String" />
<bean:define id="periodtype" name="reportkpi" property="periodType" type="java.lang.String" />
<html:form action="reportkpi" method="post">
<html:hidden name="reportkpi" property="subFunction" />
    <div class="padding-content">
        <ul id="tree">
            <li>
                <div <%=(((!"03.01".equals(subanchor)) && (!"03.02".equals(subanchor)))?"class='bgr7'":"class='bgr9'")%>>
                    <a href="#">
                        <%if("04.01".equals(subanchor)){%>
                        <bean:message key="common.label.function.report.indicator" bundle="<%=interfaces%>"/>
                        <%}else if("04.02".equals(subanchor)){%>
                        <bean:message key="common.label.function.report.object" bundle="<%=interfaces%>"/>
                        <%}else if("04.03".equals(subanchor)){%>
                        <bean:message key="common.label.function.report.insurance" bundle="<%=interfaces%>"/>
                        <%}else if("04.04".equals(subanchor)){%>
                        <bean:message key="common.label.function.report.support" bundle="<%=interfaces%>"/>
                        <%}else if("04.05".equals(subanchor)) {%>
                        <bean:message key="common.label.function.report.commune" bundle="<%=interfaces%>"/>
                        <%}else if ("04.06".equals(subanchor)){%>
                        <bean:message key="common.label.function.report.export" bundle="<%=interfaces%>"/>
                        <%}else if ("04.07".equals(subanchor)){%>
                        <bean:message key="common.label.function.report.dis-support-list" bundle="<%=interfaces%>"/>
                        <%}else if ("03.02".equals(subanchor)){%>
                        <bean:message key="common.label.function.report.export.2020" bundle="<%=interfaces%>"/>
                        <%}%>
                    </a>
                </div>
            </li>
        </ul>
        
        <br/>
        <table width="100%" class="tableForm" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td colspan="2">
                   <table width="100%">
                    <tr>
                        <th colspan="2">
                            <div class="content-calendar-2" align="left">
                                <bean:message key="common.parameter.report" bundle="<%=interfaces%>"/>                        
                            </div>
                        </th>
                    </tr>
                     
                    <tr>
                        <td align="left" width="20%"><bean:message key="location" bundle="<%=interfaces%>"/></td>
                        <td>
                            <%if("04.01".equals(subanchor)){%>
                            <html:select styleClass="inputbox" name="reportkpi" property="tinhId" onchange="javascript:postAjax('reportkpi','form', anchor + ':_REPORT_SELECT_TINH:subFunction:04.01');">
                            <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>  
                            </html:select>
                            <%}else if("04.02".equals(subanchor)){%>
                            <html:select styleClass="inputbox" name="reportkpi" property="tinhId" onchange="javascript:postAjax('reportkpi','form', anchor + ':_REPORT_SELECT_TINH:subFunction:04.02');">
                            <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>  
                            </html:select>
                            <%}else if("04.03".equals(subanchor)){%>
                            <html:select styleClass="inputbox" name="reportkpi" property="tinhId" onchange="javascript:postAjax('reportkpi','form', anchor + ':_REPORT_SELECT_TINH:subFunction:04.03');">
                            <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>  
                            </html:select>
                            <%}else if("04.04".equals(subanchor)){%>
                            <html:select styleClass="inputbox" name="reportkpi" property="tinhId" onchange="javascript:postAjax('reportkpi','form', anchor + ':_REPORT_SELECT_TINH:subFunction:04.04');">
                            <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>  
                            </html:select>
                            <%}else if("04.05".equals(subanchor)){%>
                            <html:select styleClass="inputbox" name="reportkpi" property="tinhId" onchange="javascript:postAjax('reportkpi','form', anchor + ':_REPORT_SELECT_TINH:subFunction:04.05');">
                            <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>  
                            </html:select>
                            <%}else if("04.06".equals(subanchor)||"03.02".equals(subanchor)){%>
                            <html:select styleClass="inputbox" name="reportkpi" property="tinhId" onchange="javascript:postAjax('reportkpi','form', anchor + ':_REPORT_SELECT_TINH:subFunction:04.06');">
                            <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>  
                            </html:select>
                            <%}else if("04.07".equals(subanchor)){%>
                            <html:select styleClass="inputbox" name="reportkpi" property="tinhId" onchange="javascript:postAjax('reportkpi','form', anchor + ':_REPORT_SELECT_TINH:subFunction:04.07');">
                            <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>  
                            </html:select>
                            <%}%>                            
                            <span style="color:#005BCC"><bean:write name="reportkpi" property="tinhName" /></span>
                        </td>
                    </tr>
                   
                    <%if(subanchor.startsWith("04")) {%>
                    <tr>
                        <td align="left"><bean:message key="common.label.period.type" bundle="<%=interfaces%>"/></td>
                        <td>                            
                            <%if("04.01".equals(subanchor)){%>
                            <html:select styleClass="inputbox" name="reportkpi" property="periodType" onchange="javascript:postAjax('reportkpi','form', anchor + ':_REPORT_SELECT_TINH:subFunction:04.01');" >                                
                                <html:option value="1"><bean:message key="common.label.quarter" bundle="<%=interfaces%>"/></html:option>
                                <html:option value="2"><bean:message key="common.label.year" bundle="<%=interfaces%>"/></html:option>                                
                            </html:select>
                            <%}else if("04.02".equals(subanchor)){%>
                            <html:select styleClass="inputbox" name="reportkpi" property="periodType" onchange="javascript:postAjax('reportkpi','form', anchor + ':_REPORT_SELECT_TINH:subFunction:04.02');" >                                
                                <html:option value="1"><bean:message key="common.label.quarter" bundle="<%=interfaces%>"/></html:option>
                                <html:option value="2"><bean:message key="common.label.year" bundle="<%=interfaces%>"/></html:option>
                            </html:select>                          
                            <%}else if("04.04".equals(subanchor)){%>
                            <html:select styleClass="inputbox" name="reportkpi" property="periodType" onchange="javascript:postAjax('reportkpi','form', anchor + ':_REPORT_SELECT_TINH:subFunction:04.04');" >
                                <html:option value="0"><bean:message key="common.label.month" bundle="<%=interfaces%>"/></html:option>                                
                            </html:select>
                            <%}else if ("04.05".equals(subanchor)){%>
                            <html:select styleClass="inputbox" name="reportkpi" property="periodType" onchange="javascript:postAjax('reportkpi','form', anchor + ':_REPORT_SELECT_TINH:subFunction:04.05');" >
                                  <html:option value="0"><bean:message key="common.label.month" bundle="<%=interfaces%>"/></html:option>
                                  <html:option value="1"><bean:message key="common.label.quarter" bundle="<%=interfaces%>"/></html:option>
                                  <html:option value="2"><bean:message key="common.label.year" bundle="<%=interfaces%>"/></html:option>
                            </html:select>
                            <%} else if ("04.06".equals(subanchor)){%>
                            <html:select styleClass="inputbox" name="reportkpi" property="periodType" onchange="javascript:postAjax('reportkpi','form', anchor + ':_REPORT_SELECT_TINH:subFunction:04.06');" >
                                <html:option value="4"><bean:message key="common.label.date" bundle="<%=interfaces%>"/></html:option>
                            </html:select>
                            <%} else if ("04.07".equals(subanchor)){%>
                              <html:select styleClass="inputbox" name="reportkpi" property="periodType" onchange="javascript:postAjax('reportkpi','form', anchor + ':_REPORT_SELECT_TINH:subFunction:04.07');" >
                                <html:option value="0"><bean:message key="common.label.month" bundle="<%=interfaces%>"/></html:option>
                                <html:option value="4"><bean:message key="common.label.date" bundle="<%=interfaces%>"/></html:option>
                            </html:select>
                            <%}%>
                        </td>
                    </tr>
                    <%  } else if (subanchor.startsWith("03")){ %>
                     <tr>
                        <td align="left"><bean:message key="common.label.period.type" bundle="<%=interfaces%>"/></td>
                        <td>
                           <html:select styleClass="inputbox" name="reportkpi" property="periodType">
                                <html:option value="4"><bean:message key="common.label.date" bundle="<%=interfaces%>"/></html:option>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td align="left">Ng&#224;y m&#7903; s&#7893;/ca t&#7915;</td>
                        <td>                            
                            <input type="text" name="createDateFrom" id="createDateFrom" 
                                onkeypress="return formatDate(event,this);" 
                                onblur="isDate(this);" style="width:80px;" 
                                class="textfield_date"
                                value="<bean:write name="reportkpi" property="createDateFrom"/>" />						
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'createDateFrom','dd/mm/yyyy');return false;">
                            &#273;&#7871;n 
                            <input type="text" name="createDateTo" id="createDateTo" 
                                onkeypress="return formatDate(event,this);" 
                                onblur="isDate(this);" style="width:80px;" 
                                class="textfield_date"
                                value="<bean:write name="reportkpi" property="createDateTo"/>" />						
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'createDateTo','dd/mm/yyyy');return false;">
                        </td>
                    </tr>
                    <%}%>                    
                    
                    <%if("04.06".equals(subanchor)) {%>
                    <tr>
                        <td><bean:message key="common.label.dis.project" bundle="<%=interfaces%>" /></td>
                        <td>
                          <html:select styleClass="combobox_w120" name="reportkpi" property="duAnId">
                              <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                              <html:option value="0"><bean:message key="common.label.dis.project.direct" bundle="<%=interfaces%>"/></html:option>
                              <html:option value="1"><bean:message key="common.label.dis.project.inclusion3" bundle="<%=interfaces%>"/></html:option>
                           </html:select>   
                        </td>
                    </tr>
                    <%}%>
                    
                    <%if("03.02".equals(subanchor)||"04.06".equals(subanchor)){%>
                    <tr>
                        <td align="left">Ng&#224;y m&#7903; s&#7893;/ca t&#7915;</td>
                        <td>                            
                            <input type="text" name="createDateFrom" id="createDateFrom" 
                                onkeypress="return formatDate(event,this);" 
                                onblur="isDate(this);" style="width:80px;" 
                                class="textfield_date"
                                value="<bean:write name="reportkpi" property="createDateFrom"/>" />						
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'createDateFrom','dd/mm/yyyy');return false;">
                            &#273;&#7871;n 
                            <input type="text" name="createDateTo" id="createDateTo" 
                                onkeypress="return formatDate(event,this);" 
                                onblur="isDate(this);" style="width:80px;" 
                                class="textfield_date"
                                value="<bean:write name="reportkpi" property="createDateTo"/>" />						
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'createDateTo','dd/mm/yyyy');return false;">
                        </td>
                    </tr>
                    <tr>
                        <td align="left">Ng&#224;y nh&#7853;n DV t&#7915;</td>
                        <td>                            
                            <input type="text" name="dvuDateFrom" id="dvuDateFrom" 
                                onkeypress="return formatDate(event,this);" 
                                onblur="isDate(this);" style="width:80px;" 
                                class="textfield_date"
                                value="<bean:write name="reportkpi" property="dvuDateFrom"/>" />						
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' 
                                onClick="popUpCalendar(this,'dvuDateFrom','dd/mm/yyyy');return false;">
                            &#273;&#7871;n
                            <input type="text" name="dvuDateTo" id="dvuDateTo" 
                                onkeypress="return formatDate(event,this);" 
                                onblur="isDate(this);" style="width:80px;" 
                                class="textfield_date"
                                value="<bean:write name="reportkpi" property="dvuDateTo"/>" />						
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' 
                              onClick="popUpCalendar(this,'dvuDateTo','dd/mm/yyyy');return false;">
                        </td>
                    </tr>
                    <tr>
                        <td align="left">Ng&#224;y t&#225;i &#272;G t&#7915;</td>
                        <td>                            
                            <input type="text" name="tdgDateFrom" id="tdgDateFrom" 
                                onkeypress="return formatDate(event,this);" 
                                onblur="isDate(this);" style="width:80px;" 
                                class="textfield_date"
                                value="<bean:write name="reportkpi" property="tdgDateFrom"/>" />						
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' 
                                onClick="popUpCalendar(this,'tdgDateFrom','dd/mm/yyyy');return false;">
                            &#273;&#7871;n
                            <input type="text" name="tdgDateTo" id="tdgDateTo" 
                                onkeypress="return formatDate(event,this);" 
                                onblur="isDate(this);" style="width:80px;" 
                                class="textfield_date"
                                value="<bean:write name="reportkpi" property="tdgDateTo"/>" />						
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' 
                                onClick="popUpCalendar(this,'tdgDateTo','dd/mm/yyyy');return false;">
                        </td>
                    </tr>
                    <tr>
                        <td align="left">Ng&#224;y &#273;&#243;ng/m&#7903; ca t&#7915;</td>
                        <td>                            
                            <input type="text" name="dmcDateFrom" id="dmcDateFrom" 
                                onkeypress="return formatDate(event,this);" 
                                onblur="isDate(this);" style="width:80px;" 
                                class="textfield_date"
                                value="<bean:write name="reportkpi" property="dmcDateFrom"/>" />						
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' 
                                onClick="popUpCalendar(this,'dmcDateFrom','dd/mm/yyyy');return false;">
                            &#273;&#7871;n
                            <input type="text" name="dmcDateTo" id="dmcDateTo" 
                                onkeypress="return formatDate(event,this);" 
                                onblur="isDate(this);" style="width:80px;" 
                                class="textfield_date"
                                value="<bean:write name="reportkpi" property="dmcDateTo"/>" />						
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' 
                                onClick="popUpCalendar(this,'dmcDateTo','dd/mm/yyyy');return false;">
                        </td>
                    </tr>                   
                    <%}%>
                    </table>
                    
                    <%--<bean:write name="reportkpi" property="periodType"/>--%>
                    
                    <table width="100%">
                    <%if (!"04.06".equals(subanchor)&&!"03.02".equals(subanchor)){%>
                    <logic:equal name="reportkpi" property="periodType" value="0" >
                    <tr>
                        <td align="left"  width="20%"><bean:message key="common.label.month" bundle="<%=interfaces%>"/></td>
                        <td>
                            <html:select styleClass="inputbox" name="reportkpi" property="monthReport">
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
                             <html:select styleClass="inputbox" name="reportkpi" property="yearReport" >                          
                                  <html:option value="2016">2016</html:option>
                                  <html:option value="2017">2017</html:option>
                                  <html:option value="2018">2018</html:option>
                                  <html:option value="2019">2019</html:option>
                                  <html:option value="2020">2020</html:option>
                                  <html:option value="2021">2021</html:option>
                                  <html:option value="2022">2022</html:option>
                                  <html:option value="2023">2023</html:option>
                                  <html:option value="2024">2024</html:option>
                                  <html:option value="2025">2025</html:option>
                              </html:select>
                        </td>
                    </tr>
                    </logic:equal>
                    <%}%>
                   
                    <logic:equal name="reportkpi" property="periodType" value="1" >
                    <tr>
                        <td align="left"  width="20%"><bean:message key="common.label.quarter" bundle="<%=interfaces%>"/></td>
                        <td>
                            <html:select styleClass="inputbox" name="reportkpi" property="quarterReport">
                                <html:option value="1">1</html:option>
                                <html:option value="2">2</html:option>
                                <html:option value="3">3</html:option>
                                <html:option value="4">4</html:option>
                            </html:select>
                             <html:select styleClass="inputbox" name="reportkpi" property="yearReport" >                          
                                  <html:option value="2016">2016</html:option>
                                  <html:option value="2017">2017</html:option>
                                  <html:option value="2018">2018</html:option>
                                  <html:option value="2019">2019</html:option>
                                  <html:option value="2020">2020</html:option>
                                  <html:option value="2021">2021</html:option>
                                  <html:option value="2022">2022</html:option>
                                  <html:option value="2023">2023</html:option>
                                  <html:option value="2024">2024</html:option>
                                  <html:option value="2025">2025</html:option>
                              </html:select>
                        </td>
                    </tr>
                    </logic:equal>
                    
                    <logic:equal name="reportkpi" property="periodType" value="2" >
                    <tr>
                        <td align="left"  width="20%"><bean:message key="common.label.year" bundle="<%=interfaces%>"/></td>
                        <td>
                             <html:select styleClass="inputbox" name="reportkpi" property="yearReport" >                          
                                  <html:option value="2016">2016</html:option>
                                  <html:option value="2017">2017</html:option>
                                  <html:option value="2018">2018</html:option>
                                  <html:option value="2019">2019</html:option>
                                  <html:option value="2020">2020</html:option>
                                  <html:option value="2021">2021</html:option>
                                  <html:option value="2022">2022</html:option>
                                  <html:option value="2023">2023</html:option>
                                  <html:option value="2024">2024</html:option>
                                  <html:option value="2025">2025</html:option>
                              </html:select>
                        </td>
                    </tr>
                    </logic:equal>
                    
                   
                                      
                    <% if ("04.01".equals(subanchor)){ %>
                    <tr>
                          <td align="left"><bean:message key="common.label.report.extend" bundle="<%=interfaces%>"/></td>
                          <td><html:checkbox name="reportkpi" property="extend" value="1"/></td>
                    </tr>
                    <%}%>
                    
                    <% if("04.05".equals(subanchor)){%>
                    <tr>
                          <td align="left"><bean:message key="common.label.report.extend" bundle="<%=interfaces%>"/></td>
                          <td>
                              <html:select styleClass="inputbox" name="reportkpi" property="extend" >                          
                                  <html:option value="0">T&#7893;ng h&#7907;p</html:option>
                                  <html:option value="1">Chi ti&#7871;t</html:option>
                                </html:select>
                          </td>
                    </tr>
                    <%}%>
                    
                    <!-- periodType = 3 -->                    
                    <logic:equal name="reportkpi" property="periodType" value="3" >
                    <tr>
                        <td align="left"  width="20%"><bean:message key="common.label.from-to" bundle="<%=interfaces%>"/></td>
                        <td>
                             <html:select styleClass="inputbox" name="reportkpi" property="fromMonth">
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
                            <html:select styleClass="inputbox" name="reportkpi" property="fromYear" >                          
                                  <html:option value="2016">2016</html:option>
                                  <html:option value="2017">2017</html:option>
                                  <html:option value="2018">2018</html:option>
                                  <html:option value="2019">2019</html:option>
                                  <html:option value="2020">2020</html:option>
                                  <html:option value="2021">2021</html:option>
                                   <html:option value="2022">2022</html:option>
                                  <html:option value="2023">2023</html:option>
                                  <html:option value="2024">2024</html:option>
                                  <html:option value="2025">2025</html:option>
                            </html:select>
                            <bean:message key="common.label.to" bundle="<%=interfaces%>"/>
                            <html:select styleClass="inputbox" name="reportkpi" property="toMonth">
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
                             <html:select styleClass="inputbox" name="reportkpi" property="toYear" >                          
                                  <html:option value="2016">2016</html:option>
                                  <html:option value="2017">2017</html:option>
                                  <html:option value="2018">2018</html:option>
                                  <html:option value="2019">2019</html:option>
                                  <html:option value="2020">2020</html:option>
                                  <html:option value="2021">2021</html:option>
                                  <html:option value="2022">2022</html:option>
                                  <html:option value="2023">2023</html:option>
                                  <html:option value="2024">2024</html:option>
                                  <html:option value="2025">2025</html:option>
                              </html:select>
                        </td>
                    </tr>
                    </logic:equal>
                    
                     <!-- periodType = 4 -->
                     <% if ("04.07".equals(subanchor) && "4".equals(periodtype)) { %>
                     <tr>
                        <td align="left"  width="20%">Ng&#224;y h&#7895; tr&#7907; t&#7915;
                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                        </td>
                        <td>                            
                            <input type="text" name="createDateFrom" id="createDateFrom" 
                                onkeypress="return formatDate(event,this);" 
                                onblur="isDate(this);" style="width:80px;" 
                                class="textfield_date"
                                value="<bean:write name="reportkpi" property="createDateFrom"/>" />						
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'createDateFrom','dd/mm/yyyy');return false;">
                            &#273;&#7871;n&nbsp;<bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                            <input type="text" name="createDateTo" id="createDateTo" 
                                onkeypress="return formatDate(event,this);" 
                                onblur="isDate(this);" style="width:80px;" 
                                class="textfield_date"
                                value="<bean:write name="reportkpi" property="createDateTo"/>" />						
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'createDateTo','dd/mm/yyyy');return false;">
                        </td>
                    </tr>
                    <%}%>
                    </table>             
             </td>
        </tr>
        <% if("04.04|04.05|03.02|04.07".indexOf(subanchor)==-1){%>
        <tr>
            <td colspan="2">
                <div id="alert">
                    &nbsp;&nbsp;&nbsp;&nbsp;<i><bean:write name="reportkpi" property="jobMsg" /></i>
                </div>
            </td>
        <tr>
        <%}%>
        <tr>
            <td colspan="2">
              <div id="alert">
                  <jsp:include page="/admin/alert.jsp"/>
              </div>
            </td>
        <tr>
        
        <tr>
            <td colspan="2">
                <div style="padding:16px 0">
                    <span class="bt_left_Search">
                        <span class="bt_right_Search">
                            <span class="bt_center_Search">
                                <html:button property="_CREATE" styleClass="button" onclick="javascript:excuteReport(this.form);">
                                    <bean:message key="action.export.report" bundle="<%=interfaces%>"/>
                                </html:button>
                            </span>
                        </span>
                    </span>
                </div>
            </td>
        </tr>
        </table>
    </div>
</html:form>



