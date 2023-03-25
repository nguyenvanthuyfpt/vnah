<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<div class="content-calendar-2" align="left"><font style="font-size:13px;"><b>
	T&#236;m ki&#7871;m theo t&#236;nh tr&#7841;ng</b></font></div>	
<table class="tableForm" cellpadding="2" width="100%" align="center" style="border-clilapse: clilapse" cellspacing="2" border="0">    
<tr>
    <td align="right"><input type="checkbox" name="tt_phanLoaiIds" value="129" /></td>
    <td align="left">&#272;&#227; h&#242;a nh&#7853;p c&#7897;ng &#273;&#7891;ng</td>
</tr>

<tr>
    <td align="right"><input type="checkbox" name="tt_phanLoaiIds" value="131" /></td>
    <td align="left">Kh&#244;ng c&#243; k&#7871;t qu&#7843;, ch&#225;n n&#7843;n, b&#7887; cu&#7897;c</td>
</tr>			

<tr>
    <td align="right"><input type="checkbox" name="tt_phanLoaiIds" value="133" /></td>
    <td align="left">Chuy&#7875;n &#273;i n&#7899;i kh&#225;c</td>
</tr>			

<tr>
    <td align="right"><input type="checkbox" name="tt_phanLoaiIds" value="135" /></td>
    <td align="left">Ch&#7871;t</td>
</tr>		

<tr>
    <td align="right"><input type="checkbox" name="tt_phanLoaiIds" value="137" /></td>
    <td align="left">Kh&#225;c</td>
</tr>
</table>

<table class="tableForm" cellpadding="2" width="100%" align="center" style="border-clilapse: clilapse" cellspacing="2" border="0">
<tr>
    <td colspan="2" align="center">
        <div style="padding:16px 0">
            <span class="bt_left_Search">
                <span class="bt_right_Search">
                    <span class="bt_center_Search">
                        <html:submit property="_SEARCH_RESULT" styleClass="button">
                            <bean:message key="disability.search.form.name" bundle="<%=interfaces%>"/>
                        </html:submit>
                    </span>
                </span>
            </span>
            <span><img src="images/front/spacer.gif" width="7" height="1" /></span>
            <span class="bt_left_reset">
                <span class="bt_right_reset">
                    <span class="bt_center_reset">
                        <html:reset property="_RESET" styleClass="button">
                            <bean:message key="disability.search.button.reset" bundle="<%=interfaces%>"/>
                        </html:reset>            
                    </span>
                </span>
            </span>
        </div>     
    </td>
</tr>
</table>

<tr>
<td height="10px" bgcolor="White">&nbsp;
</td>
</tr>