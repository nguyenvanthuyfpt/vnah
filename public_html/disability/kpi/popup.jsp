<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<table class="popupWin" cellpadding="0" cellspacing="0" align="center">
<!--<tr onmousedown="makeObjectToDrag('winPopup')" style="cursor:move;">
    <td class="tdheader" >
       <strong><bean:message key="report.select.fields" bundle="<%=interfaces%>"/></strong>
    </td>
    <td class="tdheader" width="10px" align="right">
        <a href="javascript:closeWindow()">[X]</a></td>
 </tr>-->
<tr>
    <td colspan="2">
      <div style="overflow-x:scroll;height:600px;">
          <table  class="pd-10" cellpadding="0" cellspacing="0" width="100%" border="0">
          <TBODY>
              <TR>               
                  <TH width="3%">#</TH>               
                  <TH width="12%"><bean:message key="common.label.location" bundle="<%=interfaces%>"/></TH>              
                  <TH width="12%"><bean:message key="common.label.name" bundle="<%=interfaces%>"/></TH>
                  <TH width="7%"><bean:message key="common.label.code" bundle="<%=interfaces%>"/></TH>
                  <TH width="8%"><bean:message key="common.label.birth" bundle="<%=interfaces%>"/></TH>
                  <TH width="8%"><bean:message key="common.label.sex" bundle="<%=interfaces%>"/></TH>
                  <TH width="10%"><bean:message key="common.label.tk.diadiem" bundle="<%=interfaces%>"/></TH>
                  <TH width="15%">S&#7889; nh&#224;</TH>
                  <TH>S&#7889 &#272;T NKT</TH>
                  <TH width="8%"><bean:message key="common.label.tk.hotro-gannhat" bundle="<%=interfaces%>"/></TH>
                  <!--
                  <TH width="10%"><bean:message key="common.label.re-examination" bundle="<%=interfaces%>"/></TH>              
                  <TH width="20%"><bean:message key="common.label.countdown" bundle="<%=interfaces%>"/></TH>
                  -->
              </TR>    
              <logic:present name="listCountDown"> 
                  <bean:define name="listCountDown" id="beans" type="com.form.FBeans" />
                  <%  int i = 1;
                  %>
                  <logic:iterate name="listCountDown" id="bean" type="com.form.disability.FDisability">
                      <tr class="<%=(i%2==0)?"content1":"content"%>" >
                          <td align="center"><span class="index"><%=i++%></span></td>
                          <td align="left"><bean:write name="bean" property="tinhName" /></td>                      
                          <td align="left"><bean:write name="bean" property="nkt" /></td>
                          <td align="left"><bean:write name="bean" property="ma" /></td>
                          <td align="center"><bean:write name="bean" property="ngaySinh" /></td>
                          <td align="left"><bean:write name="bean" property="gioiTinh" /></td>
                          <td align="left">
                                <logic:equal value="1" name="bean" property="tkDiaDiem">
                                    <bean:message key="common.tk.1" bundle="<%=interfaces%>"/>
                                </logic:equal>
                                <logic:equal value="2" name="bean" property="tkDiaDiem">
                                    <bean:message key="common.tk.2" bundle="<%=interfaces%>"/>
                                </logic:equal>
                                <logic:equal value="3" name="bean" property="tkDiaDiem">
                                    <bean:message key="common.tk.3" bundle="<%=interfaces%>"/>
                                </logic:equal>
                                <logic:equal value="4" name="bean" property="tkDiaDiem">
                                    <bean:message key="common.tk.4" bundle="<%=interfaces%>"/>
                                </logic:equal>
                          </td>
                          <td align="left"><bean:write name="bean" property="soNha" /></td>
                          <td align="left"><bean:write name="bean" property="phoneNumber" /></td>
                          <td align="left"><bean:write name="bean" property="lastDateSupport" /></td>
                          
                          <!--
                          <td align="left"><bean:write name="bean" property="gioiTinh" /></td>
                          <td align="right"><bean:write name="bean" property="day" /></td>
                          -->
                      </tr>
                  </logic:iterate>
                  
              </logic:present>
          </tbody>
          </table>
      </div>
  </td>
</tr>

<tr height="50px">
    <td align="center">        
        <span class="bt_left_Search">
            <span class="bt_right_Search">
                <span class="bt_center_Search">
                    <html:button   property="_REPORT" styleClass="button" onclick="javascript:post('kpi',anchor + ':_EXPORT_DATA:type:7');remove('kpi',anchor);closeWindow();">
                        <bean:message key="disability.search.form.report" bundle="<%=interfaces%>"/>
                    </html:button>
                </span>
            </span>
        </span>
    </td>
    <td align="center">        
        <span class="bt_left_Search">
            <span class="bt_right_Search">
                <span class="bt_center_Search">
                    <html:button   property="end" styleClass="button" onclick="closeWindow();">
                        <bean:message key="logout.caption" bundle="<%=interfaces%>"/>
                    </html:button>
                </span>
            </span>
        </span>
    </td>
</tr>
</table> 