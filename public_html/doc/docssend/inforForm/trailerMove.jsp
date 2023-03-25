<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<div class="tab-review">
<%int i=0;%>
<logic:present name="BdocsMove" >
  <logic:iterate name="BdocsMove" id="bean" type="com.form.doc.assign.FDocAssign">
  <%if(bean.getUsersAssign()==bean.getUsersRecv()){%>
               <ul style="height-line:25px" class="ulClass">
                <li > <strong><bean:write name="bean" property="nameStatus" /> : </strong> <bean:write name="bean" property="timeSend" /></li>
                 <li >
                  <bean:message key="form.docs.people.creator" bundle="<%=interfaces%>"/> : <strong><bean:write name="bean" property="sendUserName" /></strong>  
                           
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
                        <li > <strong><bean:write name="bean" property="nameStatus" /> : </strong> <bean:write name="bean" property="timeSend" /></li>
                         <li >
                          <logic:notEqual name="bean" property="views" value="1">
                          <bean:message key="messages.list.empSend" bundle="<%=interfaces%>"/> : <bean:write name="bean" property="sendUserName" /> 
                            <logic:notEqual name="bean" property="forYouId" value="0">
                           [ <a href="#" onmouseover="javascript:addthis_open(this,'N&#7897;i dung &#7911;y quy&#7873;n','','', '');postAjax('docssend','at_share',anchor + ':_TIP_FORYOU:forYouId:<bean:write name="bean" property="forYouId"/>');" onmouseout="addthis_close()" ><bean:message key="form.docs.forYouId" bundle="<%=interfaces%>"/></a> ]
                            </logic:notEqual>
                          </logic:notEqual>
                          
                          <logic:equal name="bean" property="views" value="1">
                          <bean:message key="messages.list.empSend" bundle="<%=interfaces%>"/> : <bean:write name="bean" property="sendUserName" />
                            <logic:notEqual name="bean" property="forYouId" value="0">
                                        [ <a href="#" onmouseover="javascript:checkedInnerHtml();addthis_open(this,'N&#7897;i dung &#7911;y quy&#7873;n','','', '');postAjax('docssend','at_share',anchor + ':_TIP_FORYOU:forYouId:<bean:write name="bean" property="forYouId"/>');"  ><bean:message key="form.docs.forYouId" bundle="<%=interfaces%>"/></a> ]
                            </logic:notEqual>
                          </logic:equal>
                          
                        </li>
                        
                        <li >
                     
                        <logic:notEqual name="bean" property="views" value="1">
                        <bean:message key="rules.recieverEmp.caption" bundle="<%=interfaces%>"/> : <strong><bean:write name="bean" property="recvUserName" /></strong> 
                          <logic:equal name="bean" property="readed" value="0">
                                <img  style="border:0px;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="doc.unReaded.caption" bundle="<%=interfaces%>"/>">
                          </logic:equal>
                           <logic:notEqual name="bean" property="readed" value="0">
                              <img  style="border:0px;" src="<%=contextPath%>/images/tick.png" title="<bean:message key="doc.readed.caption" bundle="<%=interfaces%>"/>">
                          </logic:notEqual>
                          </logic:notEqual>
                          
                          <logic:equal name="bean" property="views" value="1">
                              <bean:message key="rules.recieverEmp.caption" bundle="<%=interfaces%>"/> : <bean:write name="bean" property="recvUserName" />
                              <logic:equal name="bean" property="readed" value="0">
                                    <img  style="border:0px;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="doc.unReaded.caption" bundle="<%=interfaces%>"/>">
                              </logic:equal>
                               <logic:notEqual name="bean" property="readed" value="0">
                                  <img  style="border:0px;" src="<%=contextPath%>/images/tick.png" title="<bean:message key="doc.readed.caption" bundle="<%=interfaces%>"/>">
                              </logic:notEqual>
                          </logic:equal>
                        
                        <logic:notEmpty name="bean" property="recvUsers">
                         <logic:iterate name="bean" property="recvUsers" id="beanSub" type="com.form.doc.assign.FDocAssign">
                                                    <logic:notEqual name="beanSub" property="views" value="1">
                                                    , <strong><bean:write name="beanSub" property="recvUserName" /></strong> 
                                                    <logic:equal name="beanSub" property="readed" value="0">
                                                    <img  style="border:0px;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="doc.unReaded.caption" bundle="<%=interfaces%>"/>">
                                                    </logic:equal>
                                                    <logic:notEqual name="beanSub" property="readed" value="0">
                                                    <img  style="border:0px;" src="<%=contextPath%>/images/tick.png" title="<bean:message key="doc.readed.caption" bundle="<%=interfaces%>"/>">
                                                    </logic:notEqual>
                                                    </logic:notEqual>
                                                    <logic:equal name="beanSub" property="views" value="1">
                                                    , <bean:write name="beanSub" property="recvUserName" />
                                                    <logic:equal name="beanSub" property="readed" value="0">
                                                    <img  style="border:0px;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="doc.unReaded.caption" bundle="<%=interfaces%>"/>">
                                                    </logic:equal>
                                                    <logic:notEqual name="beanSub" property="readed" value="0">
                                                    <img  style="border:0px;" src="<%=contextPath%>/images/tick.png" title="<bean:message key="doc.readed.caption" bundle="<%=interfaces%>"/>">
                                                    </logic:notEqual>
                                                    </logic:equal>   
                         </logic:iterate>
                         </logic:notEmpty>
                                    
                          
                          
                        </li>
                    </ul>
    <%}%>
   </logic:iterate>
</logic:present>
</div>
         

            
            
  
 
 