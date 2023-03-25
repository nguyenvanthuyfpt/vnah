<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%int j=0;int i=1;%>
<logic:present name="BMenus">
<table width="100%" cellpadding="2" cellspacing="2" border="0">
<logic:iterate name="BMenus" id="bean" type="com.form.admin.menu.FMenu" >    
<%i++;%>
          <logic:equal name="bean" property="level" value="0">
          <%if(i%2==0){%>
          <tr><td align="left">
                <img src="<%=contextPath%>/images/newImages/<%=bean.getImages()%>" />
                <Strong><bean:write name="bean" property="title" /></strong>
                <% int block=0;%>
                <logic:equal name="bean" property="level" value="0">
                <logic:iterate name="bean" property="beansMenu" id="subBean" type="com.form.admin.menu.FMenu">
                        <%if(block==0){%>
                <div style="padding-left:18px;">     
                        <a href="javascript:<bean:write name='subBean' property='url' />"><bean:write name="subBean" property="title" /></a>
                </div>        
                        <%block=1;%>
                        <%}%>
                </logic:iterate>
                </logic:equal>
                
          </td>
          <%}else{%>
          <td align="left">
                <img src="<%=contextPath%>/images/newImages/<%=bean.getImages()%>" />
                <Strong><bean:write name="bean" property="title" /></strong>
                <% int block=0;%>
                <logic:equal name="bean" property="level" value="0">
                <logic:iterate name="bean" property="beansMenu" id="subBean" type="com.form.admin.menu.FMenu">
                        <%if(block==0){%>
                        <div style="padding-left:18px;">
                        <a href="javascript:<bean:write name='subBean' property='url' />"><bean:write name="subBean" property="title" /></a>
                        </div>
                        <%block=1;%>
                        <%}%>
                </logic:iterate>
                </logic:equal>
                
          </td>
          </tr>
          <%}%>
          </logic:equal>
</logic:iterate>
</table>
</logic:present>