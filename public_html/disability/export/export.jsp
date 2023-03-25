<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="exportdata" method="post">
<div class="padding-content">
	
	<ul id="tree">
		<li>
			<div class="bgr9"><a href="javascript:excutePostCategorys('_EXPORT_DATA')">Xu&#7845;t d&#7919; li&#7879;u</a></div>
		</li>
	</ul>
	
	<br/>
	
   <table width="100%" class="tableForm" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <th>
            <div class="content-calendar-2" align="left">
            	Xu&#7845;t d&#7919; li&#7879;u ng&#432;&#7901;i khuy&#7871;t t&#7853;t
            </div>    
        </th>
    </tr>
    
    <!--<tr>
        <td align="left">
            Ch&#7885;n d&#7919; li&#7879;u k&#7871;t xu&#7845;t
            <html:select styleClass="inputbox" name="exportdata" property="exportType" onchange="post('exportdata',anchor + ':_SELECT_EXPORTTYPE');">
                <html:option value="1"><bean:message key="disability.export.style.1" bundle="<%=interfaces%>"/></html:option>
                <html:option value="2"><bean:message key="disability.export.style.2" bundle="<%=interfaces%>"/></html:option>
            </html:select>
        </td>
    </tr>
    
    <logic:equal name="exportdata" property="exportType" value="1">	
    <tr>
        <td align="left" >
            Ch&#7885;n danh m&#7909;c
            <html:select styleClass="inputbox" name="exportdata" property="table">
                <html:option value=""><bean:message key="common.action.select" bundle="<%=interfaces%>"/></html:option>
                <html:option value="dr_area"><bean:message key="disability.export.table.tinh" bundle="<%=interfaces%>"/></html:option>
                <html:option value="dr_dangtat"><bean:message key="disability.export.table.dangtat" bundle="<%=interfaces%>"/></html:option>
            </html:select>
        </td>
    </tr>
    </logic:equal>-->
    
    <logic:equal name="exportdata" property="exportType" value="2">	
    <tr>
        <td align="left" >
            Ch&#7885;n Tuy&#7871;n
            <html:select styleClass="inputbox" name="exportdata" property="tinh_id">
            <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
            </html:select>            
        </td>
    </tr>
    </logic:equal>
    
    <tr>
        <td align="left">
            <html:button property="_CREATE" styleClass="button" onclick="post('exportdata',anchor + ':_EXPORT_DATA');" >Xu&#7845;t excel</html:button>
        </td>
    </tr>
 
    <tr>
        <td height="20px"></td>
    </tr>
</table>
     
</div>
</html:form>  
