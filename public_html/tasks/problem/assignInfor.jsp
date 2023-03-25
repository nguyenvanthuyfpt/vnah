  <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<tr>
    <td colspan="7" valign="top">
        <table width="100%" cellpadding="0" cellspacing="0">
             <tr>   
                    <td ><strong><bean:message key="doc.assigned.title.caption" bundle="<%=interfaces%>"/> </strong></td>
             </tr>
               <% int j=0;%> 
              <logic:iterate name="bean" property="asigns" id="bieanAssign" type="com.form.tasks.problem.FProblem">  
                    <tr>   
                        <td>
                                 <ul class="ulClass">
                                    <li><bean:message key="app.emp.excute.caption" bundle="<%=interfaces%>"/></li>
                                    <li><bean:write name="bieanAssign" property="workerName"/></li>
                                    <li><bean:message key="doc.view.title.caption" bundle="<%=interfaces%>"/></li>
                                    <li>
                                            <logic:equal name="bieanAssign" property="readed" value="1">                                            
                                                <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/tick.png" title="">
                                             </logic:equal>
                                             <logic:notEqual name="bieanAssign" property="readed" value="1">
                                                <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/un_tick.png" title="">
                                             </logic:notEqual>
                                    </li>
                                 </ul>
                                  <ul class="ulClass">
                                    <li><bean:message key="doc.reci.title.caption" bundle="<%=interfaces%>"/></li>
                                    <li>
                                        <logic:equal name="bieanAssign" property="accepted" value="1">                                            
                                <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/tick.png" title="">
                             </logic:equal>
                             <logic:notEqual name="bieanAssign" property="accepted" value="1">
                                <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/un_tick.png" title="">
                             </logic:notEqual>
                                    </li>
                                    <li><bean:message key="problem.list.complate" bundle="<%=interfaces%>"/></li>
                                    <li>
                                            <bean:write name="bieanAssign" property="complete"/> %
                                    </li>
                                 </ul>
                        </td>
                    </tr>
              </logic:iterate>          
        </table>
    </td>
</tr>

        
 
