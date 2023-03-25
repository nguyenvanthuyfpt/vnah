<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<bean:define id="strYearReport" name="kpi" property="yearReport" type="java.lang.Integer" />
<%
    String valYearReport = String.valueOf(strYearReport);
%>
<script>
  $( function() {
    $( "#accordion" ).accordion();
  } );
</script>
  
<div id="accordion">
    <logic:present name="listIndicator">            
      <bean:define name="listIndicator" id="beans" type="com.form.FBeans" />
      <%  
          int i = beans.getFirstRecord();
      %>
      <logic:iterate name="listIndicator" id="bean" type="com.form.disability.categorys.FIndicator">
      <%
          int stt = i++;
      %>
      <h3><b><%=stt + ". " + bean.getCode()+"-"+bean.getName()%></b></h3>
      <div>
          <p>
              <b><bean:message key="indicator.edit.type" bundle="<%=interfaces%>"/></b>: 
              <logic:equal name="bean" property="dataType" value="0">
                  <bean:message key="indicator.edit.type.val" bundle="<%=interfaces%>"/><br/>
              </logic:equal>
              <logic:equal name="bean" property="dataType" value="1">
                  <bean:message key="indicator.edit.type.dis" bundle="<%=interfaces%>"/><br/>
              </logic:equal>
              <logic:equal name="bean" property="dataType" value="2">
                  <bean:message key="indicator.edit.type.per" bundle="<%=interfaces%>"/><br/>
              </logic:equal>
              <logic:equal name="bean" property="dataType" value="3">
                  <bean:message key="indicator.edit.type.link" bundle="<%=interfaces%>"/><br/>
              </logic:equal>
              <logic:notEqual name="bean" property="parentID" value="0">
              <b><bean:message key="indicator.edit.parent" bundle="<%=interfaces%>"/></b>:
              <%=bean.getNameParent()%><br/>
              </logic:notEqual>
              <b><bean:message key="indicator.edit.description" bundle="<%=interfaces%>"/></b>: 
              <%=bean.getDescription()%>
          </p>
      </div>
      </logic:iterate>
  </logic:present>
</div>