<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<%@ page import="java.util.*" %>

<html:form action="exportExcel" method="post" enctype="multipart/form-data" >
<%
    Map<String, String> map_table = new HashMap<String, String>();
    map_table = (Map<String, String>)request.getAttribute("map_columns");    
%>
<div id="divSearchResult" style="display:none"></div>
		
<div class="padding-content">
	<ul id="tree">
		<li>
			<div class="bgr8"><a href="javascript:excutePostCategorys('_IMPORT_DATA')">N&#7841;p d&#7919; li&#7879;u</a></div>
		</li>
	</ul>
	
	<br/>	
	<bean:define name="beanImport" id="beanImport" type="com.form.disability.FImport" />
        <%
            int rowBegin = beanImport.getRowBegin();
            String tableName = "";
            tableName = beanImport.getTableName();
        %>
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="tableForm">
	<tbody>
	<tr>
      <th>
          <div align="left" class="content-calendar-2">&#272;&#432;a d&#7919; li&#7879;u v&#224;o h&#7879; th&#7889;ng</div>    
      </th>
	</tr>
        
  <tr>
      <td align="left">
          Ch&#7885;n d&#7919; li&#7879;u
          <html:select styleClass="inputbox" name="beanImport" property="typeImport">
              <html:option value="1"><bean:message key="disability.export.style.1" bundle="<%=interfaces%>"/></html:option>
              <html:option value="2"><bean:message key="disability.export.style.2" bundle="<%=interfaces%>"/></html:option>
              <html:option value="3"><bean:message key="disability.export.style.3" bundle="<%=interfaces%>"/></html:option>
              <html:option value="4"><bean:message key="disability.export.style.4" bundle="<%=interfaces%>"/></html:option>
          </html:select>
      </td>
  </tr>
  
  <logic:equal name="beanImport" property="hasReadFile" value="0">
   <tr>
      <td>&#272;&#7885;c File d&#7919; li&#7879;u t&#7915; d&#242;ng                
          <select name="rowBegin" style="width:40px;">
              <%for(int i=1;i<=10;i++){%>
                  <option value="<%=i%>" <%=rowBegin==i?"selected":""%>><%=i%></option>
              <%}%>
          </select>
      </td>
  </tr>
	<tr>
      <td>
          <input type="file" name="upFile" style="width:220px;"/>
          <!--<% String funcAdd="javascript:post('exportExcel',anchor + ':_SWAP')";%>
          <html:button property="addNew" onclick="<%=funcAdd%>" styleClass="button">
              N&#7841;p d&#7919; li&#7879;u
          </html:button>-->
          
          <span class="bt_left_Search">
                  <span class="bt_right_Search">
                      <span class="bt_center_Search">
                          <html:button property="_SEARCH_RESULT" styleClass="button" 
                              onclick="javascript:post('exportExcel',anchor + ':_SWAP')">
                              <bean:message key="btn.import.read" bundle="<%=interfaces%>"/>
                          </html:button>
                      </span>
                  </span>
            </span>
          
      </td>
	</tr>
	</logic:equal>
  	
	<logic:equal name="beanImport" property="hasReadFile" value="1">
    <logic:equal name="beanImport" property="typeImport" value="4">
    <tr>
       <td><bean:message key="object.title.caption" bundle="<%=interfaces%>"/>       
          <html:select styleClass="combobox_w400" name="beanImport" property="objId">
              <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
              <html:options collection="optObjects" property="id" labelProperty="name"/>
          </html:select>
       </td>
    </tr>
    <tr>
        <td>
            <bean:message key="common.label.event.name" bundle="<%=interfaces%>"/>
            <html:select styleClass="combobox_w400" name="beanImport" property="eventId">
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <html:options collection="events" property="eventId" labelProperty="activity"/>
            </html:select>
         </td>
    </tr>
    </logic:equal>
    <tr>
        <td>
            <!--<% String funcApply="javascript:post('exportExcel',anchor + ':_APPLY')";%>	    
            <html:button property="addNew" onclick="<%=funcApply%>" styleClass="button">
                      N&#7841;p d&#7919; li&#7879;u v&#224;o h&#7879; th&#7889;ng				
            </html:button>-->
            <span class="bt_left_Search">
                  <span class="bt_right_Search">
                      <span class="bt_center_Search">
                          <html:button property="_SEARCH_RESULT" styleClass="button" 
                              onclick="javascript:post('exportExcel',anchor + ':_APPLY')">
                              <bean:message key="btn.import.upload" bundle="<%=interfaces%>"/>
                          </html:button>
                      </span>
                  </span>
            </span>
        </td>
    </tr>
  </logic:equal>
  
  <tr>
      <td>
          <div id="alert">
              &nbsp;&nbsp;&nbsp;&nbsp;<i><bean:write name="beanImport" property="importMsg" /></i>
          </div>
      </td>
  <tr>
  
	</tbody>
	</table>
	<br/>
	<br/>
</div>
</html:form> 
