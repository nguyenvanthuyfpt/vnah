<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
                    <div><Strong><bean:message key="mail.filter.subject" bundle="<%=interfaces%>"/></strong></div>
                    <div><html:text name="mailFilter" property="subject" style="width:150px" /></div>

                    <div><Strong><bean:message key="mail.filter.from" bundle="<%=interfaces%>"/></strong></div>
                    <div><html:text name="mailFilter" property="from" style="width:150px" /></div>
                    
          <div><html:radio name="mailFilter"  property="likeFrom" value="0" /><lable for="likeFrom"><Strong><bean:message key="mail.filter.move.to.spam" bundle="<%=interfaces%>"/></strong></lable></div>
          
          <div><html:radio name="mailFilter"  property="likeFrom" value="1" /><lable for="likeFrom"><Strong><bean:message key="mail.filter.delete.it" bundle="<%=interfaces%>"/></strong></lable></div>
                    <div><br></div>
                    <div>
          <html:button property="_CREATE" onclick="javascript:post('mailFilter',anchor + ':_CREATE')" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          
          <logic:notEqual name="mailFilter" property="id" value="0" >
                  <html:button property="_EDIT"  onclick="javascript:post('mailFilter',anchor + ':_EDIT')" styleClass="button">
                    <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
                  </html:button>
          </logic:notEqual>
          
          
          
                    </div>
