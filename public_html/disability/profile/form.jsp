<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<html:form action="kpi" method="post">
<html:hidden name="kpi" property="nktId"/>

<div>
    <div align="left" class="fullName">
        <strong>H&#7885; v&#224; t&#234;n NKT: <bean:write name="kpi" property="disName"/></strong>            
    </div>
            
    <table cellpadding="2" width="100%" align="center"
           style="border-clilapse: clilapse" cellspacing="2" border="0">
        <tr>
            <td align="left" width="25%">
                <bean:message key="kpi.dis.profile.updateon" bundle="<%=interfaces%>"/>:
            </td>
            <td>
                <input type="text" name="pfCreateOn" id="pfCreateOn" 
                    onkeypress="return formatDate(event,this);" 
                    onblur="isDate(this);" style="width:80px;" 
                    class="textfield_date"
                    value="<bean:write name="kpi" property="pfCreateOn"/>" />						
                <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'pfCreateOn','dd/mm/yyyy');return false;">
            </td>
        </tr>
        
        <tr>
          <td align="left" width="30%">
              <bean:message key="kpi.dis.profile.updateby" bundle="<%=interfaces%>"/>:
          </td>
          <td>
              <html:text name="kpi" property="pfCreateBy" style="width:200px;" />
          </td>
      </tr>
        
        <tr>
            <td align="left" width="25%">               
                <bean:message key="kpi.dis.profile.status" bundle="<%=interfaces%>"/>:
            </td>
            <td>
                <logic:notEqual name="kpi" property="nktId" value="0">
                <html:select styleClass="combobox_w120" name="kpi" property="pfStatus">
                    <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <logic:equal value="1" name="kpi" property="statusId">  
                          <html:option value="0"><bean:message key="kpi.dis.profile.status.open" bundle="<%=interfaces%>" /></html:option>
                    </logic:equal>
                    <logic:equal value="0" name="kpi" property="statusId"> 
                          <html:option value="1"><bean:message key="kpi.dis.profile.status.close" bundle="<%=interfaces%>" /></html:option> 
                    </logic:equal>
                    <logic:equal value="-1" name="kpi" property="statusId">
                          <html:option value="0"><bean:message key="kpi.dis.profile.status.open" bundle="<%=interfaces%>" /></html:option>
                          <html:option value="1"><bean:message key="kpi.dis.profile.status.close" bundle="<%=interfaces%>" /></html:option> 
                    </logic:equal>
                </html:select>
                </logic:notEqual>
                <logic:equal name="kpi" property="nktId" value="0">
                <html:select styleClass="combobox_w120" name="kpi" property="pfStatus" disabled="true">
                    <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                </html:select>
                </logic:equal>
            </td>
        </tr>
        
        <tr>
            <td align="left" width="25%">
                <logic:equal value="1" name="kpi" property="statusId">                    
                    <bean:message key="kpi.dis.profile.reson.open" bundle="<%=interfaces%>"/>:
                </logic:equal>
                <logic:equal value="0" name="kpi" property="statusId">                    
                    <bean:message key="kpi.dis.profile.reson.close" bundle="<%=interfaces%>"/>:
                </logic:equal>
                 <logic:equal value="-1" name="kpi" property="statusId">                    
                    <bean:message key="kpi.dis.profile.reson" bundle="<%=interfaces%>"/>:
                </logic:equal>
            </td>
            <td>
                <logic:notEqual name="kpi" property="nktId" value="0">
                <html:select styleClass="combobox_w300" name="kpi" property="pfResonId">
                    <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <logic:present name="BResons">
                      <html:options collection="BResons" property="id" labelProperty="name"/>
                  </logic:present>
                </html:select>
                </logic:notEqual>
                
                <logic:equal name="kpi" property="nktId" value="0">
                    <html:select styleClass="combobox_w300" name="kpi" property="pfResonId" disabled="true">
                        <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    </html:select>
                </logic:equal>
            </td>
        </tr>
        
        <tr>
          <td align="left" width="25%">
              <bean:message key="kpi.dis.profile.assessment" bundle="<%=interfaces%>"/>:
          </td>
          <td>
              <html:textarea name="kpi" property="pfAssessment" rows="3" cols="55"/>
          </td>
      </tr>
    </table>
    <div class="bottom">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td>
                     <logic:notEqual name="kpi" property="nktId" value="0">
                     <%if(request.getSession().getAttribute("10.01")!=null){%>
                      <span class="bt_left_Search">
                        <span class="bt_right_Search">
                            <span class="bt_center_Search">                                 
                                  <html:button property="_EDIT" styleClass="button"
                                               onclick="postAjax('kpi','MainCate',anchor + ':_INSERT_PROFILE');">
                                      <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                                  </html:button>                                  
                              </span>
                          </span>
                      </span>
                      <%}%>
                    </logic:notEqual>
                    
                    <logic:notEqual name="kpi" property="pfId" value="0">
                    <%if(request.getSession().getAttribute("10.02")!=null){%>
                      <span class="bt_left_Search">
                        <span class="bt_right_Search">
                            <span class="bt_center_Search"> 
                              
                                  <bean:define name="kpi" property="pfId" id="pfId" type="java.lang.Integer"/>
                                  <%String onclick="postAjax('kpi','MainCate',anchor + ':_UPDATE_PROFILE:pfId:"+pfId+"');";%>
                                  <html:button property="_EDIT" styleClass="button" onclick="<%=onclick%>">
                                      <bean:message key="action.update" bundle="<%=interfaces%>"/>
                                  </html:button>                                 
                            </span>
                         </span>
                      </span>
                      <%}%>
                    </logic:notEqual>
                </td>
                <td>
                    <html:errors property="alert" bundle="<%=interfaces%>"/>
                </td>
            </tr>
        </table>
    </div>
</div>
</html:form>