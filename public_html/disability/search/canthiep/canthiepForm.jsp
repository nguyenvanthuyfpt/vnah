<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<div class="content-calendar-2" align="left"><font style="font-size:13px;"><b>
	T&#236;m ki&#7871;m theo can thi&#7879;p</b></font></div>	
<%	
	String checkBox="";
	String hotroIdsTemp="";
%>
<logic:notEmpty name="support" property="hotroIds">
	<bean:define name="support" property="hotroIds" id="hotroIds" type="java.lang.String" />
	<%hotroIdsTemp=hotroIds;%>
</logic:notEmpty>

<table class="tableForm" cellpadding="2" width="100%" align="center" style="border-clilapse: clilapse" cellspacing="2" border="0">    
<tr>
    <td width="20%">		
		L&#7921;a ch&#7885;n d&#7841;ng
	</td>
	<td>
		<html:select name="timkiem" property="ht_statusId" styleId="ht_statusId" >
			<html:option value="-1"> ---Ch&#7885;n--- </html:option>
			<html:option value="0"> Nhu c&#7847;u h&#7895; tr&#7907; </html:option>        
			<html:option value="1"> H&#7895; tr&#7907; &#273;&#227; nh&#7853;n </html:option>        
		</html:select>
    </td>
</tr>	
<tr>
	<td align="right"><input type="checkbox" name="ct_phanLoaiIds" value="291" /></td>
	<td align="left">Kh&#225;m s&#224;ng l&#7885;c, ph&#225;t hi&#7879;n s&#7899;m</td>
</tr>

<tr>
	<td align="right"><input type="checkbox" name="ct_phanLoaiIds" value="293" /></td>
	<td align="left">Can thi&#7879;p gi&#225;o d&#7909;c</td>
</tr>			

<tr>
	<td align="right"><input type="checkbox" name="ct_phanLoaiIds" value="295" /></td>
	<td align="left">Can thi&#7879;p y t&#7871;</td>
</tr>			

<tr>
	<td align="right"><input type="checkbox" name="ct_phanLoaiIds" value="297" /></td>
	<td align="left">Can thi&#7879;p kh&#225;c</td>
</tr>		
</table>

<table class="tableForm" cellpadding="2" width="100%" align="center" style="border-clilapse: clilapse" cellspacing="2" border="0">
   <tr>
		<td colspan="2" align="center"><div style="padding:16px 0">
            <span class="bt_left_Search"><span class="bt_right_Search"><span class="bt_center_Search">
                <html:submit property="_SEARCH_RESULT" styleClass="button">
					<bean:message key="disability.search.form.name" bundle="<%=interfaces%>"/>
                </html:submit>
            </span></span></span>
            <span><img src="images/front/spacer.gif" width="7" height="1" /></span>
            <span class="bt_left_reset"><span class="bt_right_reset"><span class="bt_center_reset">
                <html:reset property="_RESET" styleClass="button">
					<bean:message key="disability.search.button.reset" bundle="<%=interfaces%>"/>
                </html:reset>
            </span></span></span>
              </div>     
       </td>
</tr>
</table>

<tr>
<td height="10px" bgcolor="White">&nbsp;
</td>
</tr>