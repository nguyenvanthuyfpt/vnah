<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<%  if(request.getSession().getAttribute("06.02") !=null){ %>  
<logic:notEqual name="06.02" value="2">                         
<%  int[] menuActiveTemp = (int[])request.getSession().getAttribute("menuActive");%>
<logic:equal name="06.02" value="1" >
                 <logic:present name="BAgendas" >
                 <logic:notEmpty name="BAgendas" >                 
                <div class="tblnews-calendar" align="left">
                <p class="link01 last">
                <% int dem =0; %>
                <logic:iterate name="BAgendas" id="bean" indexId="k" type="com.form.calendar.agenda.FAgenda">
                    <div class="mainCalendarTitle">
                    <bean:message key="label.module.calendar.what" bundle="<%=interfaces%>"/>
                    : <bean:write name="bean" property="what" /></div>
                    <div><bean:message key="label.module.calendar.time" bundle="<%=interfaces%>"/> : <bean:write name="bean" property="times" /> - <bean:write name="bean" property="toTimes" /> <bean:message key="label.module.calendar.timeevent" bundle="<%=interfaces%>"/> <bean:write name="bean" property="timeEvent" /></div>
                    <div><bean:message key="label.module.calendar.where" bundle="<%=interfaces%>"/> : <bean:write name="bean" property="where" /></div>
                </logic:iterate>
                </p>
                </div>
                </logic:notEmpty>
                </logic:present>           
    </logic:equal>
 </logic:notEqual>
                    <% } %>      
  
   