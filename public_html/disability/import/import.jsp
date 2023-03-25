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
                </html:select>
            </td>
        </tr>
        
        <tr>
            <td>&#272;&#7885;c File d&#7919; li&#7879;u t&#7915; d&#242;ng                
                <select name="rowBegin" style="width:40px;">
                    <%for(int i=0;i<=10;i++){%>
                        <option value="<%=i%>" <%=rowBegin==i?"selected":""%>><%=i+1%></option>
                    <%}%>
                </select>
            </td>
        </tr>
        
	<tr height="30">
            <td nowrap>
                <input type="file" name="upFile" style="width:220px;"/>
                <% String funcAdd="javascript:post('exportExcel',anchor + ':_SWAP')";%>
                <html:button property="addNew" onclick="<%=funcAdd%>" styleClass="button">
                    N&#7841;p d&#7919; li&#7879;u
                </html:button>			
            </td>
	</tr>
	
	<tr height="30"><td>&nbsp;</td></tr>
		
	<logic:present name="BDatas" >
	<tr>
	    <td nowrap>
	        <% String funcApply="javascript:post('exportExcel',anchor + ':_APPLY')";%>	    
	        <html:button property="addNew" onclick="<%=funcApply%>" styleClass="button">
                    N&#7841;p d&#7919; li&#7879;u v&#224;o h&#7879; th&#7889;ng				
	        </html:button>
                    N&#7841;p d&#7919; li&#7879;u v&#224;o Table n&#224;o:
		
	        <html:select styleClass="inputbox" name="BImport" property="tableName" >	            
	            <html:options collection="BTables" property="tableName" labelProperty="tableName"/>
	        </html:select>
	    </td>
	</tr>
				
	<tr>
		<td style="padding:10px 0px 10px 10px;">
			<table border="1" width="60%" class="tableForm">
			<tbody>
			<tr height="30px;">
				<th colspan="3" align="left">Thu&#7897;c t&#237;nh c&#7911;a b&#7843;ng d&#7919; li&#7879;u</th>
			</tr>
			
			
                        <bean:define name="BDatas" id="bean" type="com.form.disability.FImport" />
			<%
                            if(bean.getDataSheet0()!=null && bean.getDataSheet0().length>0){
                                int row = bean.getDataSheet0().length;                                
				for(int i=0;i<1;i++){
                                    for(int j=0;j<bean.getDataSheet0().length;j++){
                                            //int[] arrDataType = new int[bean.getData().length];
                                            //arrDataType[j] = (bean.getTypeData() != null) ? bean.getTypeData()[j] : 0;
						
			%>
					
			    <tr height="30px;">
			        <td width="10px"><%=j+1%></td>
			        <td>
                                    <input type="text" readonly="true" size="40" id="nameField" name="nameField" value="<%=bean.getDataSheet0()[j][0]%>">
                                </td>
			        <td width="150px">
                                    <select name="typeData" id="typeData">
                                        <option value="varchar"   <%=map_table.get(bean.getDataSheet0()[j][0]).equals("varchar")?"selected='selected'":""%>>String</option>
                                        <option value="int4"      <%=map_table.get(bean.getDataSheet0()[j][0]).equals("int4")?"selected='selected'":""%>>Integer</option>
                                        <option value="int8"      <%=map_table.get(bean.getDataSheet0()[j][0]).equals("int8")?"selected='selected'":""%>>Bigint</option>
                                        <option value="bigserial" <%=map_table.get(bean.getDataSheet0()[j][0]).equals("bigserial")?"selected='selected'":""%>>Bigserial</option>
                                        <option value="timestamp" <%=map_table.get(bean.getDataSheet0()[j][0]).equals("timestamp")?"selected='selected'":""%>>DateTime</option>                                            
                                    </select>
			        </td>
			    </tr>
			    	
			<%	}}}	%>
			</tbody>
			</table>
	    </td>
	</tr>
	</logic:present>
	</tbody>
	</table>
	
	<br/>
</div>
</html:form> 
