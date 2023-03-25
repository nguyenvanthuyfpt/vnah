<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
	
<table width="100%" cellspacing="2" cellpadding="4" border="0">    
<tr height="25">
	<td  nowrap colspan="4"><b>Ng&#432;&#7901;i ch&#259;m s&#243;c ch&#237;nh NKT:</b></td>
</tr>
	
<tr height="25">
	<td >H&#7885; v&#224; t&#234;n ng&#432;&#7901;i ch&#259;m s&#243;c:</td>
	<td align="left" ><html:text name="disability" property="tenChamSoc" style="width:120px" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/></td>
	<td >N&#259;m sinh:</td>
	<td align="left"><html:text name="disability" property="namSinhChamSoc" onblur="isYear(this);" style="width:120px;text-align:right;" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/></td>
</tr>

<tr height="25">
	<td nowrap><bean:message key="disability.form.label.chuho.quanHeNKT" bundle="<%=interfaces%>"/></td>
	<td align="left">
		<html:select styleClass="inputbox" name="disability" property="quanHeChamSoc" style="width:120px">
			<html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
			<html:option value="1">&#212;ng</html:option>
			<html:option value="2">B&#224;</html:option>
			<html:option value="3">C&#244;</html:option>
			<html:option value="4">D&#236;</html:option>
			<html:option value="5">Ch&#250;</html:option>
			<html:option value="6">B&#225;c</html:option>
			<html:option value="7">Anh</html:option>
			<html:option value="8">Ch&#7883;</html:option>
			<html:option value="9">Em</html:option>
			<html:option value="10">B&#7889;</html:option>
			<html:option value="11">M&#7865;</html:option>
			<html:option value="12">Ch&#7891;ng</html:option>
			<html:option value="13">V&#7907;</html:option>
			<html:option value="15">Con</html:option>
			<html:option value="16">Ch&#225;u</html:option>
			<html:option value="14">Kh&#225;c</html:option>
		</html:select>
	</td>
	<td>&#272;i&#7879;n tho&#7841;i li&#234;n l&#7841;c:</td>
	<td align="left"><html:text name="disability" property="sdtLienLac" style="width:120px;text-align:right;" 
        onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/></td>
</tr>
</table>
 
