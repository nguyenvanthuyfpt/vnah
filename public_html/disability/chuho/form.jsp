<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
	
<table width="100%" cellspacing="2" cellpadding="4" border="0">    
<tr height="25">
    <td nowrap colspan="4"><b>Th&#244;ng tin v&#7873; h&#7897; gia &#273;&#236;nh:</b></td>
</tr>
	
<tr height="25">
	<td nowrap>H&#7885; v&#224; t&#234;n ch&#7911; h&#7897;:</td>
	<td align="left" ><html:text name="disability" property="tenChuHo" style="width:120px;" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/></td>
	<td nowrap>N&#259;m sinh:</td>
	<td align="left"><html:text name="disability" property="namSinhChuHo" onblur="isYear(this);" style="width:50px;text-align:right;" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/></td>
</tr>
	
<tr height="25">
	<td><bean:message key="disability.form.label.chuho.quanHeNKT" bundle="<%=interfaces%>"/></td>
	<td colspan="3">
            <html:select styleClass="inputbox" name="disability" property="quanHeChuHo" style="width:120px">
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <html:option value="1">B&#7889;</html:option>
                <html:option value="2">M&#7865;</html:option>
                <html:option value="6">V&#7907;</html:option>
                <html:option value="7">Ch&#7891;ng</html:option>
                <html:option value="8">Con</html:option>
                <html:option value="9">Ch&#225;u</html:option>
                <html:option value="3">Ng&#432;&#7901;i ch&#259;m s&#243;c</html:option>
                <html:option value="4">Ng&#432;&#7901;i khuy&#7871;t t&#7853;t</html:option>	
                <html:option value="5">Kh&#225;c</html:option>
            </html:select>
	</td>
</tr>

<tr height="25">
    <td nowrap>S&#7889; ng&#432;&#7901;i trong h&#7897;:</td>
    <td align="left"><html:text name="disability" property="songuoi_chuho" onblur="isNumber(this);" style="width:50px;text-align:right;" 
        onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
    <td nowrap>S&#7889; NKT trong h&#7897;:</td>
    <td align="left"><html:text name="disability" property="soNKT_chuho" onblur="isNumber(this);" style="width:50px;text-align:right;" 
        onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
</tr>
    

<tr height="25">
    <td nowrap><bean:message key="disability.form.label.dieuKienId" bundle="<%=interfaces%>"/>:</td>
    <td align="left">
		<html:select styleClass="inputbox" name="disability" property="dieuKienId">
			<html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
			<html:options collection="BDieuKiens" property="id" labelProperty="name"/>
		</html:select>
    </td>

    <td nowrap><bean:message key="disability.form.label.nguonNuocId" bundle="<%=interfaces%>"/>:</td>
    <td align="left" >
        <html:select styleClass="inputbox" name="disability" property="nguonNuocId">
            <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:option value="1"><bean:message key="disability.form.label.nguonNuocId.1" bundle="<%=interfaces%>"/></html:option>
            <html:option value="2"><bean:message key="disability.form.label.nguonNuocId.2" bundle="<%=interfaces%>"/></html:option>
            <html:option value="3"><bean:message key="disability.form.label.nguonNuocId.3" bundle="<%=interfaces%>"/></html:option>
            <html:option value="4"><bean:message key="disability.form.label.nguonNuocId.4" bundle="<%=interfaces%>"/></html:option>
            <html:option value="5"><bean:message key="disability.form.label.nguonNuocId.5" bundle="<%=interfaces%>"/></html:option>           
         </html:select>
     </td>
</tr>

<tr height="25">
	<td>Nh&#224; v&#7879; sinh:</td>
	<td colspan="3">
		<html:select styleClass="inputbox" name="disability" property="nhaVeSinhChuHo">
			<html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
			<html:option value="0">Kh&#244;ng</html:option>
			<html:option value="1">C&#243;</html:option>
		</html:select>
	</td>
</tr>

<tr height="25">
	<td nowrap>NKT c&#243; th&#7875; s&#7917; d&#7909;ng nh&#224; v&#7879; sinh:</td>
	<td align="left" colspan="3">
		<html:select styleClass="inputbox" name="disability" property="nhaVeSinhNKTChuHo">
			<html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
			<html:option value="0">Kh&#244;ng</html:option>
			<html:option value="1">C&#243;</html:option>
		</html:select>
	</td>
</tr>
	
<tr height="25">
    <td  nowrap><bean:message key="disability.form.label.NhaOId" bundle="<%=interfaces%>"/>:</td>
    <td  align="left" colspan="3">
        <html:select styleClass="inputbox" name="disability" property="nhaOId">
            <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:option value="1"><bean:message key="disability.form.label.NhaOId.1" bundle="<%=interfaces%>"/></html:option>
            <html:option value="2"><bean:message key="disability.form.label.NhaOId.2" bundle="<%=interfaces%>"/></html:option>
            <html:option value="3"><bean:message key="disability.form.label.NhaOId.3" bundle="<%=interfaces%>"/></html:option>
        </html:select>
    </td>
</tr>

</table>
 
