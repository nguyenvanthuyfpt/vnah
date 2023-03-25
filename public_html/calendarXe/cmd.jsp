<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
             
             
             <logic:equal name="agenda" property="calendarType" value="1" > 
               <%if(me.isRole(com.inf.IRoles.rOFFICER) ||   me.isRole(com.inf.IRoles.rCALENDARDEP)){%>
                <% String funcAdd="javascript:if (checkData()){post('agenda',anchor + ':_SAVE')};";%>
                    <html:button property="addNew" onclick="<%=funcAdd%>" styleClass="button">
                      <bean:message key="action.save" bundle="<%=interfaces%>"/>
                    </html:button>
                 <% String funcDelete="javascript:post('agenda',anchor + ':_DELETE');";%>
                    <html:button property="delete" onclick="<%=funcDelete%>" styleClass="button">
                      <bean:message key="action.delete" bundle="<%=interfaces%>"/>
                    </html:button>
               <%}%> 
             </logic:equal>     
             
              <logic:notEqual name="agenda" property="calendarType" value="1" >               
                   <bean:define name="agenda" property="active" id="active" type="java.lang.Integer" />               
                   <logic:notEqual name="agenda" property="id" value="0" >
                       <% String funcActive="javascript:post('agenda',anchor + ':_ACTIVE:active:"+ (active==1?0:1) +"');";%>
                        <html:button property="delete" onclick="<%=funcActive%>" styleClass="button">
                          Public
                        </html:button>
                   </logic:notEqual> 
                    
                <% String funcAdd="javascript:if (checkData()){post('agenda',anchor + ':_SAVE')};";%>
                    <html:button property="addNew" onclick="<%=funcAdd%>" styleClass="button">
                      <bean:message key="action.save" bundle="<%=interfaces%>"/>
                    </html:button>
                 <% String funcDelete="javascript:post('agenda',anchor + ':_DELETE');";%>
                    <html:button property="delete" onclick="<%=funcDelete%>" styleClass="button">
                      <bean:message key="action.delete" bundle="<%=interfaces%>"/>
                    </html:button>               
             </logic:notEqual>                  
