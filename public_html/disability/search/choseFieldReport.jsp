<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript">

</script>

<table class="popupWin" cellpadding="0" cellspacing="0" width="600px" height="250px" align="center">
<tr onmousedown="makeObjectToDrag('winPopup')" style="cursor:move;">
    <td class="tdheader" >
       <strong><bean:message key="report.select.fields" bundle="<%=interfaces%>"/></strong>
    </td>
    <td class="tdheader" width="10px" align="right">
        <a href="javascript:closeWindow()">[X]</a></td>
 </tr>
 <tr height="25px" id="param">
    <td colspan="2">
        <table class="popupWinInner" width="100%" cellpadding="2" cellspacing="2" border="0"> 
        <tr><td colspan="5" align="left"><b>Th&#244;ng tin chung</b></td></tr>
        <tr>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="0" checked="checked">
                &nbsp;Tuy&#7871;n
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="1" checked="checked">
                &nbsp;Ng&#224;y m&#7903; h&#7891; s&#417;
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="2" checked="checked">
                &nbsp;S&#7889; th&#7913; t&#7921; NKT
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="3" checked="checked"> 
                &nbsp;H&#7885; v&#224; t&#234;n NKT
            </td> 
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="4" checked="checked">
                &nbsp;M&#227; s&#7889; NKT
            </td>                      
        </tr>
        <tr>
           <td nowrap align="left">
                <input type="checkbox" name="fields" value="5" checked="checked"> 
                &nbsp;Ng&#224;y sinh NKT
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="6" checked="checked">
                &nbsp;S&#7889; &#273;i&#7879;n tho&#7841;i NKT
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="7">
                &nbsp;D&#226;n t&#7897;c
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="8">
                &nbsp;Gi&#7899;i t&#237;nh NKT
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="9"> 
                &nbsp;N&#7841;n nh&#226;n da cam
            </td>
        </tr>
        <tr>    
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="10">
                &nbsp;Ngh&#7873; nghi&#7879;p NKT
            </td>
            <td  nowrap align="left">
                <input type="checkbox" name="fields" value="11"> 
                &nbsp;T&#236;nh tr&#7841;ng h&#7891; s&#417;
            </td>
            <td  nowrap align="left">
                <input type="checkbox" name="fields" value="12"> 
                &nbsp;Th&#7921;c hi&#7879;n b&#7903;i d&#7921; &#225;n
            </td>
            <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
            <td  nowrap align="left">
                <input type="checkbox" name="fields" value="13"> 
                &nbsp;H&#7885; v&#224; t&#234;n NCS
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="14">
                &nbsp;N&#259;m sinh NCS
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="15">
                &nbsp;Quan h&#7879; v&#7899;i NKT
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="16">
                &nbsp;&#272;i&#7879;n tho&#7841;i li&#234;n l&#7841;c
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="17">
                &nbsp;Gi&#7899;i t&#237;nh NCS
            </td>
        </tr>
        
        <tr>
            <td colspan="5" align="left"><br/><b>Ph&#226;n lo&#7841;i</b></td>
        </tr>
        <tr>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="18"> 
                D&#7841;ng t&#7853;t
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="19">
                &nbsp;Ng&#224;y c&#7853;p nh&#7853;t
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="20">
                &nbsp;Ng&#224;y t&#225;i kh&#225;m
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="21">
                &nbsp;&#272;&#7883;a &#273;i&#7875;m kh&#225;m
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="22">
                &nbsp;Th&#7901;i &#273;i&#7875;m m&#7855;c KT                
            </td>
        </tr>
        <tr>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="23"> 
                T&#236;nh tr&#7841;ng
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="24">
                &nbsp;Nguy&#234;n nh&#226;n
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="25">
                &nbsp;M&#7913;c &#273;&#7897;
            </td>
            <td colspan="2">&nbsp;</td>
        </tr>
        
        <tr>
            <td colspan="5" align="left"><br/><b>Nhu c&#7847;u/Cung c&#7845;p DVHT</b></td>
        </tr>        
        <tr>            
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="26">
                &nbsp;Nhu c&#7847;u h&#7895; tr&#7907;
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="27">
                &nbsp;Nhu c&#7847;u d&#7909;ng c&#7909; kh&#225;c
            </td>            
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="28">
                &nbsp;H&#7895; tr&#7907; &#273;&#227; nh&#7853;n
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="29">
                &nbsp;Th&#7901;i gian nh&#7853;n h&#7895; tr&#7907;
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="30">
                &nbsp;Ngu&#7891;n h&#7895; tr&#7907;
            </td>            
        </tr>
        
        <tr>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="31">
                &nbsp;Kh&#7843; n&#259;ng chi tr&#7843;
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="32">
                &nbsp;Th&#7867; BHYT
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="33">
                &nbsp;S&#7917; d&#7909;ng th&#7867; BHYT
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="34">
                &nbsp;S&#7917; d&#7909;ng th&#7867; PHCN
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="35">
                &nbsp;M&#7909;c ti&#234;u gia &#273;&#236;nh
            </td>            
        </tr>
        
        <tr>     
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="36">
                &nbsp;M&#7909;c ti&#234;u &#273;i&#7873;u tr&#7883;
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="37">
                &nbsp;Ch&#432;&#417;ng tr&#236;nh VLTL
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="38">
                &nbsp;Ch&#432;&#417;ng tr&#236;nh HDTL
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="39">
                &nbsp;Ch&#432;&#417;ng tr&#236;nh ANTL
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="40">
                &nbsp;M&#7913;c &#273;&#7897; &#272;L-PT
            </td>
                     
        </tr>
        
        <tr>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="41">
                &nbsp;M&#7913;c &#273;&#7897; h&#224;i l&#242;ng
            </td>   
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="42">
                &nbsp;H&#7895; tr&#7907; d&#7909;ng c&#7909; kh&#225;c
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="43">
                &nbsp;D&#7909;ng c&#7909; tr&#7907; gi&#250;p (M&#244; t&#7843;)
            </td>
            <td nowrap align="left">
                <input type="checkbox" name="fields" value="44">
                &nbsp;<bean:message key="disability.search.form.mobile" bundle="<%=interfaces%>"/>
            </td>
            <td>&nbsp;</td>
        </tr>
        </table>    
    </td>
</tr>

<tr>
    <td align="left" colspan="2">
        <br/>
        <input type="checkbox" name="checkall" id="checkall" onclick="toggleChk(this);">&nbsp;L&#7921;a ch&#7885;n t&#7845;t c&#7843;
    </td>
</tr>

<tr height="50px">
    <td align="center" colspan="2">
        <span class="bt_left_Search">
            <span class="bt_right_Search">
                <span class="bt_center_Search">
                    <html:button   property="_REPORT" styleClass="button" onclick="javascript:post('searchdispeople',anchor + ':_REPORT');remove('searchdispeople',anchor);closeWindow();">
                      <bean:message key="disability.search.form.report" bundle="<%=interfaces%>"/>
                    </html:button>
                </span>
            </span>
        </span>
        <span class="bt_left_Search">
            <span class="bt_right_Search">
                <span class="bt_center_Search">
                    <html:button   property="end" styleClass="button" onclick="closeWindow();">
                        <bean:message key="logout.caption" bundle="<%=interfaces%>"/>
                    </html:button>
                </span>
            </span>
        </span>
    </td>
</tr>
</table> 
