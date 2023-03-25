  <%@ include file="/commons/tags.jsp"%>
  <%@ include file="/commons/params.jsp"%>
<div class="tab-review">
<%int i=0;%>
<logic:present name="BRmTrailer" >
  <logic:iterate name="BRmTrailer" id="bean" type="com.form.require.requires.FRequire">
      <%if(bean.getUserSend()==bean.getUserRecv()){%>
        <ul style="height-line:25px" class="ulClass">
                <li> <strong><bean:write name="bean" property="rmStatusName" /> : </strong> <bean:write name="bean" property="timeCreate" /></li>                 
                <li>
                      <bean:message key="form.docs.people.creator" bundle="<%=interfaces%>"/> : <strong><bean:write name="bean" property="userSendName" /></strong>                             
                       <logic:equal name="bean" property="readed" value="0">
                            <img  style="border:0px;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="doc.unReaded.caption" bundle="<%=interfaces%>"/>">
                      </logic:equal>                          
                      <logic:notEqual name="bean" property="readed" value="0">
                          <img  style="border:0px;" src="<%=contextPath%>/images/tick.png" title="<bean:message key="doc.readed.caption" bundle="<%=interfaces%>"/>">
                      </logic:notEqual>
                </li>
        </ul>
        <%}else{%>
                <ul  style="height-line:25px;border:0"  class="ulClass">
                        <li > <strong><bean:write name="bean" property="rmStatusName" /> : </strong> <bean:write name="bean" property="timeCreate" /></li>
                         <li>
                          <bean:message key="messages.list.empSend" bundle="<%=interfaces%>"/> : <bean:write name="bean" property="userSendName" />                          
                        </li>                        
                        <li>
                              <bean:message key="rules.recieverEmp.caption" bundle="<%=interfaces%>"/> : <bean:write name="bean" property="userRecvName" />
                              <logic:equal name="bean" property="readed" value="0">
                                    <img  style="border:0px;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="doc.unReaded.caption" bundle="<%=interfaces%>"/>">
                              </logic:equal>
                               <logic:notEqual name="bean" property="readed" value="0">
                                  <img  style="border:0px;" src="<%=contextPath%>/images/tick.png" title="<bean:message key="doc.readed.caption" bundle="<%=interfaces%>"/>">
                              </logic:notEqual>
                             <logic:notEmpty name="bean" property="recvUsers">
                         <logic:iterate name="bean" property="recvUsers" id="beanSub" type="com.form.require.requires.FRequire">
                                                   
                                    ,<bean:write name="beanSub" property="userRecvName" />
                                    <logic:equal name="beanSub" property="readed" value="0">
                                    <img  style="border:0px;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="doc.unReaded.caption" bundle="<%=interfaces%>"/>">
                                    </logic:equal>
                                    <logic:notEqual name="beanSub" property="readed" value="0">
                                    <img  style="border:0px;" src="<%=contextPath%>/images/tick.png" title="<bean:message key="doc.readed.caption" bundle="<%=interfaces%>"/>">
                                    </logic:notEqual>
                                                    
                         </logic:iterate>
                         </logic:notEmpty>
                       
                                    
                          
                          
                        </li>
                    </ul>
        <%}%>
  </logic:iterate>
</logic:present>
</div>