<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<bean:define id="strYearReport" name="kpi" property="yearReport" type="java.lang.Integer" />
<%
    String valYearReport = String.valueOf(strYearReport);
%>
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<tbody>
    <tr>
        <TH width="3%">&nbsp;</TH>
        <TH width="8%"><bean:message key="common.label.code" bundle="<%=interfaces%>"/></TH>
        <TH width="12%"><bean:message key="indicator.edit.type" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="indicator.edit.name" bundle="<%=interfaces%>"/></TH>
        <TH width="8%"><bean:message key="common.label.total.record" bundle="<%=interfaces%>"/></TH>
    </tr>
    
      
    <logic:present name="listIndicator">            
      <bean:define name="listIndicator" id="beans" type="com.form.FBeans" />
      <%  int i = beans.getFirstRecord(); 
          if (i>0) {
      %>
      
      <logic:iterate name="listIndicator" id="bean" type="com.form.disability.FObjectInd">              
      <tr class="<%=(i%2==0)?"content1":"content"%>">                  
          <td align="left"><%=i++%></td>
          <td align="left"><bean:write name="bean" property="code" /></td>
          <td>
            <logic:equal value="0" name="bean" property="type" >
                <bean:message key="indicator.edit.type.val" bundle="<%=interfaces%>"/>
            </logic:equal>
            <logic:equal value="1" name="bean" property="type" >
                <bean:message key="indicator.edit.type.dis" bundle="<%=interfaces%>"/>
            </logic:equal>
            <logic:equal value="2" name="bean" property="type" >
                <bean:message key="indicator.edit.type.per" bundle="<%=interfaces%>"/>
            </logic:equal>
            <logic:equal value="3" name="bean" property="type" >
                <bean:message key="indicator.edit.type.link" bundle="<%=interfaces%>"/>
            </logic:equal>
          </td>
          <td align="left">
              <logic:greaterThan value="0" name="bean" property="ins"> <!-- Enable Insert -->
                  <logic:notEqual value="3" name="bean" property="type">
                      <a href="#" onclick="getInputData('<%=bean.getObjId()%>','<%=bean.getIndId()%>', '<%=bean.getType()%>', '<%=valYearReport%>');">
                          <bean:write name="bean" property="name" />
                      </a>
                  </logic:notEqual>
                  <logic:equal value="3" name="bean" property="type">
                        <logic:equal value="SubIR2.3.PM2" name="bean" property="code">
                            <a href="#" onclick="getInputData('<%=bean.getObjId()%>','<%=bean.getIndId()%>', '<%=bean.getType()%>', '<%=valYearReport%>');">
                                <bean:write name="bean" property="name" />
                            </a>
                        </logic:equal>
                        <logic:notEqual value="SubIR2.3.PM2" name="bean" property="code">
                            <bean:write name="bean" property="name" />
                        </logic:notEqual>
                  </logic:equal>
              </logic:greaterThan>
               <logic:equal value="0" name="bean" property="ins">   <!-- Disable Insert -->
                    <bean:write name="bean" property="name" />
              </logic:equal>
          </td>
          <td align="right">
                <bean:write name="bean" property="total" />
          </td>
      </tr>
      </logic:iterate>
      <%} else { %>
      <td colspan="5" align="center">
          <span class="error"><bean:message key="indicator.object.no.data.configuration" bundle="<%=interfaces%>"/></span>
      </td>
      <%}%>
  </logic:present>
</tbody>
</table>