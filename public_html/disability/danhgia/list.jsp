<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<br>
<html:form action="danhgia_nkt" method="POST" >	
<html:hidden name="danhgiaht" property="idNkt" styleId="idNkt" />

<div style="padding:5px;">
    <fieldset style="border:1px solid gray;">
		<legend><strong>&#272;&#225;nh gi&#225; k&#7871;t qu&#7843; 6 th&#225;ng</strong></legend>
		<table width="100%">
		<tr height="25">
			<td width="33%" align="center"><strong>PHCN v&#7873; Y t&#7871; - s&#7913;c kh&#7887;e</strong></td>
			<td width="34%" align="center"><strong>&#272;&#7901;i s&#7889;ng kinh t&#7871;, h&#242;a nh&#7853;p x&#227; h&#7897;i</strong></td>
			<td width="33%" align="center"><strong>Gi&#225;o d&#7909;c</strong></td>
		</tr>
		
		<tr height="25">
			<td style="padding-left:70px;"><html:radio name="danhgiaht" property="yteSucKhoe" value="1" /> Ti&#7871;n b&#7897; nhi&#7873;u</td>
			<td style="padding-left:70px;"><html:radio name="danhgiaht" property="kinhteXaHoi" value="1" /> Ti&#7871;n b&#7897; nhi&#7873;u</td>
			<td style="padding-left:70px;"><html:radio name="danhgiaht" property="giaoDuc" value="1" /> Ti&#7871;n b&#7897; nhi&#7873;u</td>
		</tr>
			
		<tr height="25">
			<td style="padding-left:70px;"><html:radio name="danhgiaht" property="yteSucKhoe" value="2" /> Ti&#7871;n b&#7897; &#237;t</td>
			<td style="padding-left:70px;"><html:radio name="danhgiaht" property="kinhteXaHoi" value="2" /> Ti&#7871;n b&#7897; &#237;t</td>
			<td style="padding-left:70px;"><html:radio name="danhgiaht" property="giaoDuc" value="2" /> Ti&#7871;n b&#7897; &#237;t</td>
		</tr>			
		
		<tr height="25">
			<td style="padding-left:70px;"><html:radio name="danhgiaht" property="yteSucKhoe" value="3" /> Kh&#244;ng ti&#7871;n b&#7897;</td>
			<td style="padding-left:70px;"><html:radio name="danhgiaht" property="kinhteXaHoi" value="3" /> Kh&#244;ng ti&#7871;n b&#7897;</td>
			<td style="padding-left:70px;"><html:radio name="danhgiaht" property="giaoDuc" value="3" /> Kh&#244;ng ti&#7871;n b&#7897;</td>
		</tr>
			
		<tr height="25">	
			<td style="padding-left:70px;"><html:radio name="danhgiaht" property="yteSucKhoe" value="4" /> X&#7845;u &#273;i</td>
			<td style="padding-left:70px;"><html:radio name="danhgiaht" property="kinhteXaHoi" value="4" /> X&#7845;u &#273;i</td>
			<td style="padding-left:70px;"><html:radio name="danhgiaht" property="giaoDuc" value="4" /> X&#7845;u &#273;i</td>
		</tr>	
		</table>
	</fieldset>	
</div>	

<br/>

<div>
	<table class="tableForm" cellpadding="2" width="100%" align="center" style="border-clilapse: clilapse" cellspacing="2" border="0">    
	<tr>
		<td>
			Ng&#224;y &#273;&#225;nh gi&#225;
			<html:text name="danhgiaht" property="dateCreate" styleId="dateCreate" style="width:80px" />
			<img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'dateCreate','dd/mm/yyyy');return false;">
			
			K&#7923; &#273;&#225;nh gi&#225;:
			<html:select styleClass="inputbox" name="danhgiaht" property="kyDanhGia" onchange="postAjax('danhgia_nkt','idrank',anchor + ':_LIST');">
				<html:option value="1">K&#7923; 1</html:option>
				<html:option value="2">K&#7923; 2</html:option>
			</html:select>
			
			N&#259;m &#273;&#225;nh gi&#225;:
			<html:text name="danhgiaht" property="namDanhGia" style="width:30px" onkeydown="if(event.keyCode==13){postAjax('danhgia_nkt','idrank',anchor + ':_LIST');return false;}" />
		</td>
	</tr>
	</table>
</div>

<div class="buttom">	
	<table cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td>	
			<logic:notEqual name="danhgiaht" property="idNkt" value="0">
				<html:button property="_CREATE" styleClass="button" onclick="postAjax('danhgia_nkt','danhgia_nkt',anchor + ':_CREATE');" >
					<bean:message key="action.insert" bundle="<%=interfaces%>"/>
				</html:button>
	
				<logic:notEqual name="danhgiaht" property="id" value="0" >
	                <bean:define name="danhgiaht" property="id" id="id" type="java.lang.Integer" />
	                <%	String onclick="postAjax('danhgia_nkt','idrank',anchor + ':_UPDATE:id:"+id+"');";	%>
                    <html:button property="_EDIT" styleClass="button" onclick="<%=onclick%>"   >                 
                    	<bean:message key="action.update" bundle="<%=interfaces%>"/>
                    </html:button>        
                </logic:notEqual>
			</logic:notEqual>
		</td>
		<td>
	        <html:errors property="alert" bundle="<%=interfaces%>" />
	    </td>
	</tr>
	</table>
</div>

	
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<TBODY>
<TR>               
	<TH width="10px" nowrap align="center">#</TH>            
	<TH nowrap >Ng&#224;y &#273;&#225;nh gi&#225;</TH>
	<TH nowrap >PHCN v&#7873; Y t&#7871; - s&#7913;c kh&#7887;e</TH>
	<TH nowrap >&#272;&#7901;i s&#7889;ng kinh t&#7871;, h&#242;a nh&#7853;p x&#227; h&#7897;i</TH>
	<TH nowrap >Gi&#225;o d&#7909;c</TH>
	<TH width="50px" nowrap>#</TH>
</TR>

<logic:present name="BDanhGias"> 
	<%  int i =1;%>
	<logic:iterate name="BDanhGias" id="bean" type="com.form.disability.FDanhGia">                                       
	    <tr class="<%=(i%2==0)?"content1":"content"%>">
		    <td  nowrap align="center"><span class="index"><%=i++%></span></td>
		    <td  nowrap align="center">
	            <bean:write name="bean" property="dateCreate" />
		    </td>
	
		    <td  nowrap align="center">
		        <logic:equal name="bean" property="yteSucKhoe" value="1">Ti&#7871;n b&#7897; nhi&#7873;u</logic:equal>
		        <logic:equal name="bean" property="yteSucKhoe" value="2">Ti&#7871;n b&#7897; &#237;t</logic:equal>
		        <logic:equal name="bean" property="yteSucKhoe" value="3">Kh&#244;ng ti&#7871;n b&#7897;</logic:equal>
		        <logic:equal name="bean" property="yteSucKhoe" value="4">X&#7845;u &#273;i</logic:equal>
		    </td>
		    	
		    <td  nowrap align="center">
		        <logic:equal name="bean" property="kinhteXaHoi" value="1">Ti&#7871;n b&#7897; nhi&#7873;u</logic:equal>
		        <logic:equal name="bean" property="kinhteXaHoi" value="2">Ti&#7871;n b&#7897; &#237;t</logic:equal>
		        <logic:equal name="bean" property="kinhteXaHoi" value="3">Kh&#244;ng ti&#7871;n b&#7897;</logic:equal>
		        <logic:equal name="bean" property="kinhteXaHoi" value="4">X&#7845;u &#273;i</logic:equal>
		    </td>
		    <td  nowrap align="center">
		        <logic:equal name="bean" property="giaoDuc" value="1">Ti&#7871;n b&#7897; nhi&#7873;u</logic:equal>
		        <logic:equal name="bean" property="giaoDuc" value="2">Ti&#7871;n b&#7897; &#237;t</logic:equal>
		        <logic:equal name="bean" property="giaoDuc" value="3">Kh&#244;ng ti&#7871;n b&#7897;</logic:equal>
		        <logic:equal name="bean" property="giaoDuc" value="4">X&#7845;u &#273;i</logic:equal>
		    </td>
		    <td  nowrap width="50px">
				<img style="cursor:pointer;" src="<%=contextPath%>/images/editdraft.png" 
					title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" 
					onClick="javascript:postAjax('danhgia_nkt','idrank',anchor + ':_DETAIL:id:<%=bean.getId()%>')">
				
				<img style="cursor:pointer;" src="<%=contextPath%>/images/delete.png" 
					title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
					onClick="javascript:if(messageDelete())postAjax('danhgia_nkt','idrank',anchor + ':_DELETE_DISABILITY:id:<%=bean.getId()%>')">
		    </td>
	    </tr>
	</logic:iterate>
</logic:present>
</tbody>
</table>
</html:form>
 
