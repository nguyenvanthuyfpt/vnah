<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>

<script type="text/javascript">
   $( function() {
      //$("#huongcanthiep").attr("display","none");
   }
</script>
<html:form action="kpi" method="post">
<html:hidden name="kpi" property="nktId"/>
<bean:define name="kpi" property="rptHuongCanThiep"     id="hasCanThiep"       type="java.lang.Integer" />

<%
    String ct = String.valueOf(hasCanThiep);
%>
<div>
    <div align="left" class="fullName">
        <strong>H&#7885; v&#224; t&#234;n NKT: <bean:write name="kpi" property="disName"/></strong>            
    </div>
    
    <table cellpadding="2" width="100%" align="center"
         style="border-clilapse: clilapse" cellspacing="2" border="0">
      <tr>
          <td align="left" width="30%">
              <bean:message key="common.label.createdate" bundle="<%=interfaces%>"/>:
          </td>
          <td>
              <input type="text" name="rptCreateDate" id="rptCreateDate" 
                  onkeypress="return formatDate(event,this);" 
                  onblur="isDate(this);" style="width:80px;" 
                  class="textfield_date"
                  value="<bean:write name="kpi" property="rptCreateDate"/>" />						
              <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'rptCreateDate','dd/mm/yyyy');return false;">
          </td>
      </tr>
      <tr>
          <td align="left" width="30%">
              <bean:message key="kpi.dis.commune.rpt0" bundle="<%=interfaces%>"/>:
          </td>
          <td>
              <html:text name="kpi" property="rptCreateBy" style="width:200px;" />
          </td>
      </tr>
      
      <tr>
          <td align="left" width="30%">
              <bean:message key="kpi.dis.commune.rpt1" bundle="<%=interfaces%>"/>:
          </td>
          <td>
              <html:checkbox name="kpi" property="rptP1" value="1"/>
          </td>
      </tr>
      
       <tr>
          <td align="left" width="30%">
              <bean:message key="kpi.dis.commune.rpt2" bundle="<%=interfaces%>"/>:
          </td>
          <td>
              <html:checkbox name="kpi" property="rptP2" value="1"/>
          </td>
      </tr>
      
       <tr>
          <td align="left" width="30%">
              <bean:message key="kpi.dis.commune.rpt3" bundle="<%=interfaces%>"/>:
          </td>
          <td>
              <html:checkbox name="kpi" property="rptP3" value="1"/>
          </td>
      </tr>
      
       <tr>
          <td align="left" width="30%">
              <bean:message key="kpi.dis.commune.rpt4" bundle="<%=interfaces%>"/>:
          </td>
          <td>
              <html:checkbox name="kpi" property="rptP4" value="1"/>
          </td>
      </tr>
      
       <tr>
          <td align="left" width="30%">
              <bean:message key="kpi.dis.commune.rpt5" bundle="<%=interfaces%>"/>:
          </td>
          <td>
              <html:select styleClass="combobox_w120" name="kpi" property="rptP5">
                  <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="1"><bean:message key="kpi.dis.commune.opt1" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="2"><bean:message key="kpi.dis.commune.opt2" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="3"><bean:message key="kpi.dis.commune.opt3" bundle="<%=interfaces%>"/></html:option>                      
              </html:select>
          </td>
      </tr>
      
      <tr>
          <td align="left" width="30%">
              <bean:message key="kpi.dis.commune.rpt6" bundle="<%=interfaces%>"/>:
          </td>
          <td>
              <html:checkbox name="kpi" property="rptNcs" value="1"/>
          </td>
      </tr>
      
      <tr>
          <td align="left" width="30%">
              H&#432;&#7899;ng can thi&#7879;p:
          </td>
          <td>
              <html:checkbox name="kpi" property="rptHuongCanThiep" value="1" onchange="enableElement('huongcanthiep',this,this.checked);"/>
          </td>
      </tr>
      <tr id="huongcanthiep" <%= "0".equals(ct)? "style='display:none'":""%>>
          <td align="left" width="30%">
              C&#7909; th&#7875; v&#7873; h&#432;&#7899;ng can thi&#7879;p:
          </td>
          <td>
              <html:textarea name="kpi" property="rptCanThiep" rows="3" cols="55"/>
          </td>
      </tr>
      
      <tr>
          <td align="left" width="30%">
              Ng&#224;y d&#7921; ki&#7871;n h&#7895; tr&#7907;:
          </td>
          <td>
              <input type="text" name="rptHtroDKien" id="rptHtroDKien" 
                  onkeypress="return formatDate(event,this);" 
                  onblur="isDate(this);" style="width:80px;" 
                  class="textfield_date"
                  value="<bean:write name="kpi" property="rptHtroDKien"/>" />						
              <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'rptHtroDKien','dd/mm/yyyy');return false;">
          </td>
      </tr>
      </table>
    
    <div class="bottom">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td>
                     <logic:notEqual name="kpi" property="nktId" value="0">
                     <logic:equal name="kpi" property="rptId" value="0">
                      <%if(request.getSession().getAttribute("10.02")!=null){%>
                      <span class="bt_left_Search">
                        <span class="bt_right_Search">
                            <span class="bt_center_Search">                                 
                                  <html:button property="_EDIT" styleClass="button"
                                               onclick="postAjax('kpi','MainCate',anchor + ':_INSERT_COMMUNE');">
                                      <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                                  </html:button>                                  
                              </span>
                          </span>
                      </span>
                      <%}%>
                    </logic:equal>
                    </logic:notEqual>
                    
                    <logic:notEqual name="kpi" property="rptId" value="0">
                      <%if(request.getSession().getAttribute("10.03")!=null){%>
                      <span class="bt_left_Search">
                        <span class="bt_right_Search">
                            <span class="bt_center_Search">                               
                                  <bean:define name="kpi" property="rptId" id="rptId" type="java.lang.Integer"/>
                                  <%String onclick="postAjax('kpi','MainCate',anchor + ':_UPDATE_COMMUNE:rptId:"+rptId+"');";%>
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