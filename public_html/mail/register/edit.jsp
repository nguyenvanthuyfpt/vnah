<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
                    <div><Strong><bean:message key="category.mailAccount.userMail" bundle="<%=interfaces%>"/></strong><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></div>
                    <div><html:text name="registerMail" property="userMail" style="width:150px" /></div>

                    <div><Strong><bean:message key="category.mailAccount.passMail" bundle="<%=interfaces%>"/></strong><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></div>
                    <div>
                    <input name="passMail" id="passMail" type="password" value="<bean:write name="registerMail" property="passMail" />" style="width:150px" >
                    </div>

                    <div><Strong><bean:message key="category.mailAccount.serverMail" bundle="<%=interfaces%>"/></strong><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></div>
                    <div><html:text name="registerMail" property="serverMail" style="width:150px" /></div>
                    
                    <div>
                    <Strong>
                    <html:checkbox name="registerMail" property="sercure" styleId="sercureId" value="1" /><label for="sercureId"><bean:message key="category.mailAccount.sercure" bundle="<%=interfaces%>"/></label></strong></div>
                    
                    <div><Strong><bean:message key="category.mailAccount.status" bundle="<%=interfaces%>"/></strong></div>
                    <div>
                        <html:select name="registerMail" property="status" styleId="status" styleClass="fieldSelect"  >
                        <html:option value="0"> <bean:message key="mailAccount.status.0" bundle="<%=interfaces%>"/> </html:option>        
                        <html:option value="1"> <bean:message key="mailAccount.status.1" bundle="<%=interfaces%>"/> </html:option>        
                        </html:select>
                    </div>
                    
                    <div><br></div>
                    <div>
          <html:button property="_CREATE" onclick="javascript:post('registerMail',anchor + ':_CREATE')" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          
          <logic:notEqual name="registerMail" property="id" value="0" >
                  <html:button property="_EDIT"  onclick="javascript:post('registerMail',anchor + ':_EDIT')" styleClass="button">
                    <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
                  </html:button>
          </logic:notEqual>
          
                    </div>
