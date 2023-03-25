<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<html:form action="event" method="post">
    <html:hidden name="event" property="eventId"/>
    <div class="padding-content">
        <table align="left" width="100%">
            <tbody>
                <tr>
                    <td valign="top">
                        <logic:present name="event">
                            <table class="tableForm" cellpadding="0" width="100%" align="center"
                                   style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">
                                <tbody>
                                    <tr>
                                        <td width="25%">
                                            <bean:message key="location" bundle="<%=interfaces%>" />                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td colspan="3">
                                            <html:select styleClass="combobox_w150" name="event" property="locationId" onchange="javascript:postAjax('event','listId',anchor + ':_CHANGE_OPTION');">
                                                <logic:present name="BTreeTinhs">
                                                    <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
                                                </logic:present>
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <!--<td width="25%">
                                            <bean:message key="event.edit.code" bundle="<%=interfaces%>"/>                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td width="25%">
                                            <html:text name="event" property="code" readonly="true" styleClass="inputbox" size="20"/>
                                        </td>-->
                                        <td width="25%">
                                            <bean:message key="common.label.createdate" bundle="<%=interfaces%>"/>                                             
                                        </td>
                                        <td colspan="3">
                                            <html:text name="event" property="createDate" readonly="true" size="10" styleClass="center"/>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td>
                                            <bean:message key="common.label.activity" bundle="<%=interfaces%>"/>                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td colspan="3">                                            
                                            <html:textarea name="event" property="activity" rows="3" cols="55"/>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td>
                                            <bean:message key="common.label.event.location" bundle="<%=interfaces%>"/>
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td colspan="3">
                                            <html:textarea name="event" property="location" rows="3" cols="55"/>                                            
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td><bean:message key="common.label.date.start" bundle="<%=interfaces%>" />
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td><html:text name="event" property="startDate" styleClass="textfield_date" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="startDate" style="width:80px" 
                                            onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
                                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'startDate','dd/mm/yyyy');return false;"></td>
                                        <td><bean:message key="common.label.date.end" bundle="<%=interfaces%>" />
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td><html:text name="event" property="endDate" styleClass="textfield_date" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="endDate" style="width:80px" 
                                            onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
                                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'endDate','dd/mm/yyyy');return false;"></td>                                     
                                    </tr>
                                    
                                    <tr>
                                        <td>
                                            <bean:message key="common.label.event.type" bundle="<%=interfaces%>"/> 
                                        </td>
                                        <td>
                                            <html:select name="event" property="eventType" styleClass="combobox_w100">
                                                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                                                <html:options collection="mapEventType" property="key" labelProperty="value" />
                                            </html:select>
                                        </td>
                                         <td>
                                            <bean:message key="common.label.event.field" bundle="<%=interfaces%>"/>    
                                        </td>
                                        <td>
                                            <html:select name="event" property="eventField" styleClass="combobox_w100">
                                                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                                                <html:options collection="mapEventField" property="key" labelProperty="value" />
                                            </html:select>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </logic:present>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <div id="alert">
                            <jsp:include page="/admin/alert.jsp"/>
                        </div>
                    </td>
                </tr>    
                
                <tr>
                    <td>
                        <logic:present name="event">
                            <jsp:include page="/disability/categorys/event/cmd.jsp"/>
                        </logic:present>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</html:form>