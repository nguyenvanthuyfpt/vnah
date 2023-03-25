<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<script type="text/javascript">
    $(function() {
        // alert(dateFrom + " - " + dateTo);
        $("#eventStartDate").focus();
    });  
</script>

<div>
    <ul id="tree">
        <li class="">
            <div class="bgr5"><a href="#"><bean:message key="kpi.title.caption" bundle="<%=interfaces%>"/></a></div>
        </li>	
    </ul>
</div>

<br/>	

<html:form action="kpi" method="post" onsubmit="false">
    <html:hidden name="kpi" property="id"/>
    <html:hidden name="kpi" property="objId"/>
    <html:hidden name="kpi" property="indId"/>
    <html:hidden name="kpi" property="eventId"/>
    <html:hidden name="kpi" property="type"/>
    <bean:define name="kpi" property="eventSel" id="eventSelId" type="java.lang.String" />
    
<div class="content-calendar">
    <div class="padding-content">    
        <logic:present name="kpi">               
              <table align="left" width="100%">
              <tbody>
                  <tr>
                      <td valign="top">
                          <logic:present name="kpi">
                              <table class="tableForm" cellpadding="0" width="100%" align="center"
                                     style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">
                                  <tbody>
                                      <tr>
                                          <td width="25%">
                                              <bean:message key="event.edit.code" bundle="<%=interfaces%>"/>                                             
                                              <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                          </td>
                                          <td width="25%">
                                              <html:text name="kpi" property="eventCode" styleClass="inputbox" size="20" readonly="true"/>
                                          </td>
                                          <td width="25%">
                                              <bean:message key="common.label.createdate" bundle="<%=interfaces%>"/>                                             
                                          </td>
                                          <td width="25%">
                                              <html:text name="kpi" property="eventCreateDate" styleClass="textfield_date" 
                                                  onkeypress="return formatDate(event,this);" onblur="isDate(this);" 
                                                  styleId="eventCreateDate" style="width:80px" 
                                                  onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
                                             <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' 
                                                  onClick="popUpCalendar(this,'eventCreateDate','dd/mm/yyyy');return false;">   
                                          </td>
                                      </tr>
                                      
                                       <tr>
                                          <td><bean:message key="common.label.date.start" bundle="<%=interfaces%>" />
                                              <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                          </td>
                                          <td><html:text name="kpi" property="eventStartDate" styleClass="textfield_date" 
                                              onkeypress="return formatDate(event,this);" onblur="isDate(this);" 
                                              styleId="eventStartDate" style="width:80px" 
                                              onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
                                              <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' 
                                                  onClick="popUpCalendar(this,'eventStartDate','dd/mm/yyyy');return false;"></td>
                                          <td><bean:message key="common.label.date.end" bundle="<%=interfaces%>" />
                                              <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                          </td>
                                          <td><html:text name="kpi" property="eventEndDate" styleClass="textfield_date" 
                                              onkeypress="return formatDate(event,this);" onblur="isDate(this);" 
                                              styleId="eventEndDate" style="width:80px" 
                                              onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
                                              <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' 
                                                  onClick="popUpCalendar(this,'eventEndDate','dd/mm/yyyy');return false;"></td>                                     
                                      </tr>
                                      
                                      <tr>
                                          <td>
                                              <bean:message key="common.label.activity" bundle="<%=interfaces%>"/>                                             
                                              <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                          </td>
                                          <td colspan="3">
                                              <html:textarea name="kpi" property="eventActivity" rows="3" cols="55"/>
                                          </td>
                                      </tr>
                                      <tr>
                                           <td>
                                              <bean:message key="common.label.event.location" bundle="<%=interfaces%>"/>    
                                          </td>
                                          <td colspan="3">
                                              <html:textarea name="kpi" property="eventLocation" rows="3" cols="55"/>
                                          </td>
                                      </tr>
                                      
                                      <!--<tr>
                                          <td>
                                              <bean:message key="common.label.event.type" bundle="<%=interfaces%>"/> 
                                          </td>
                                          <td>
                                              <html:select name="kpi" property="eventType" styleClass="combobox_w100">
                                                  <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                                                  <html:options collection="mapEventType" property="key" labelProperty="value" />
                                              </html:select>
                                          </td>
                                           <td>
                                              <bean:message key="common.label.event.field" bundle="<%=interfaces%>"/>    
                                          </td>
                                          <td>
                                              <html:select name="kpi" property="eventField" styleClass="combobox_w100">
                                                  <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                                                  <html:options collection="mapEventField" property="key" labelProperty="value" />
                                              </html:select>
                                          </td>
                                      </tr>-->
                                  </tbody>
                              </table>
                          </logic:present>
                      </td>
                  </tr>                      
              </tbody>
          </table>
          <br/>              
      </logic:present>
      
      <div id="cmd">
          <jsp:include page="/disability/kpi/cmdEvent.jsp"/>
      </div>
      <div id="alert">
          <jsp:include page="/admin/alert.jsp"/>
      </div> 
      <div id="listId">
          <jsp:include page="/disability/kpi/listEvent.jsp"/>
      </div>
    </div>
</div>  
</html:form>   


