<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
	
<div class="padding-content">
  <logic:notEqual name="timkiem" property="typeResult" value="0" >
      <div class="width:15px">
          </div> 
          <div align="center"><font style="font-size:15px;"><b><bean:message key="disability.search.form.inforarea" bundle="<%=interfaces%>"/></b></font></div>
              <center>
                  <div class="content-calendar-2" style="width:400px">
                      <jsp:include page="/disability/search/inforArea.jsp" />  
                  </div> 
              </center>
          <div class="width:15px">
      </div> 
  </logic:notEqual>
		
	<logic:equal name="timkiem" property="typeResult" value="0" >
      <div class="content-calendar-2" >
          <a href="javascript:getSearchForm();">
          <bean:message key="disability.search.form.tkt" bundle="<%=interfaces%>"/>
          </a> 
          | <a href="#" onclick="return createListReport();">
          <bean:message key="disability.search.form.lapds" bundle="<%=interfaces%>"/>
          </a> 
          | <a href="#">
          <bean:message key="disability.search.form.lapds" bundle="<%=interfaces%>"/>
          </a> 
      </div>
	</logic:equal>

	<div class="content-calendar">                                            
	    <jsp:include page="/disability/search/listPeople.jsp" />  
	</div> 
</div>

 
