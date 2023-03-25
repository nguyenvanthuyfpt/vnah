<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<%
    String selIndIds = "";
    String checked = "";
%>
<bean:define name="object" property="selIndIds" id="indSelected" type="java.lang.String"/>
<%
    selIndIds = indSelected;    
%>

<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
  <tbody>
      <tr>
          <TH width="5%">&nbsp;</TH>
          <TH width="5%"><bean:message key="common.label.stt" bundle="<%=interfaces%>"/></TH>
          <TH width="10%"><bean:message key="common.label.code" bundle="<%=interfaces%>"/></TH>        
          <TH><bean:message key="common.label.name" bundle="<%=interfaces%>"/></TH>                              
      </tr>
      <logic:present name="listIndicators"> 
          <bean:define name="listIndicators" id="beans" type="com.form.FBeans" />
          <%  int i = beans.getFirstRecord();%>
          <logic:iterate name="listIndicators" id="bean" type="com.form.disability.categorys.FIndicator">
              <%  
                  String strClass = (i%2==0)? "content1":"content";
                  checked = selIndIds.indexOf(String.valueOf(bean.getId()))>-1?"checked='checked'":"";                                      
              %>
              <tr class="<%=strClass%>">
                  <td align="center">
                      <%=i++%>                                          
                  </td>
                  <td align="center">
                      <input type="checkbox" name="indIds"  value="<%=bean.getId()%>" <%=checked%>/>                                          
                  </td>
                  <td align="left"><bean:write name="bean" property="code" /></td>
                  <td align="left"><bean:write name="bean" property="name" /></td>                                      
              </tr>
          </logic:iterate>
      </logic:present>
  </tbody>
</table>