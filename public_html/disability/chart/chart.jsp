<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="exportdata" method="post">
<div class="padding-content">
<%
    String sub_func = (String)request.getAttribute("subanchor");
%>
	<ul id="tree">
		<li>
			<div class="bgr8"><a href="javascript:excutePostCategorys('_CHART')">Bi&#7875;u &#273;&#7891; th&#7889;ng k&#234;</a></div>
		</li>
	</ul>
	
	<br/>
	
   <table width="100%" class="tableForm" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <th>
            <div class="content-calendar-2" align="left">
                Bi&#7875;u &#273;&#7891; th&#7889;ng k&#234;
            </div>    
        </th>
    </tr>
    
    <tr>
        <td>
          <%
              if ("08.01".equals(sub_func)){
          %>
              <img src="chartDis" />
          <%
              } else if ("08.02".equals(sub_func)){
          %>
              <img src="chartPerson" />
          <% 
              } else if ("08.03".equals(sub_func)){
          %>
              <img src="chartHours" />
          <% 
              } else if ("08.04".equals(sub_func)){ 
          %>
              <img src="chartEvent" />
          <% } %>
        </td>
    </tr>   
</table>
     
</div>
</html:form>  
