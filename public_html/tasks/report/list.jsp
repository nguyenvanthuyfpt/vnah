 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <logic:present name="BReports">         
           <logic:iterate name="BReports" id="bean1" type="com.form.tasks.report.FReport">   
            <ul style="line-height:20px" class="ulClass" >
               <li>
                   <strong> <bean:message key="report.timeReport.date.caption" bundle="<%=interfaces%>"/> : </strong> <bean:write name="bean1" property="timeReport"/>
               </li>
               <li>
                <strong> <bean:message key="problem.complate" bundle="<%=interfaces%>"/> : </strong> <bean:write name="bean1" property="complate"/> %
                   <logic:notEqual name="bean1" property="fileName" value="">                   
                     <a href="javascript:post('report',anchor + ':_SAVE:id:<%=bean1.getId()%>');remove('report',anchor)">  <img border="0" src="<%=contextPath%>/images/attach.gif"></a>
                  </logic:notEqual>  
               </li>
               <li>
                    <strong><bean:message key="report.name.caption" bundle="<%=interfaces%>"/> : </strong><bean:write name="bean1" property="report"/>
               </li>
            </ul>
            </logic:iterate>
 </logic:present>
  
  
