<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table class="popupWin" cellpadding="0" cellspacing="0" width="400px" height="150px" align="center">


<tr onmousedown="makeObjectToDrag('winPopup')" style="cursor:move;">
    <td class="tdheader" width="200px">
        <strong><bean:message key="common.danhgia.hotro" bundle="<%=interfaces%>"/></strong>
    </td>
    <td class="tdheader" width="10px" align="right">
        <a href="javascript:closeWindow()">[X]</a>
    </td>
</tr>

<tr>
    <td colspan="2">
        <b><bean:write name="kpi" property="supportName" /></b>
        <!--<bean:write name="kpi" property="createDate" />
        <bean:write name="kpi" property="supportId" />-->
    </td>    
<tr>

<td align="left"><bean:message key="common.danhgia.luachon" bundle="<%=interfaces%>"/>:</td>
<td>
    <html:select name="kpi" property="mdoHlong" styleClass="combobox_w100">
        <html:option value="-1">--L&#7921;a ch&#7885;n--</html:option>
        <html:option value="1">1-R&#7845;t kh&#244;ng h&#224;i l&#242;ng</html:option>
        <html:option value="2">2-Kh&#244;ng h&#224;i l&#242;ng</html:option>
        <html:option value="3">3-B&#236;nh th&#432;&#7901;ng</html:option>
        <html:option value="4">4-H&#224;i l&#242;ng</html:option>
        <html:option value="5">5-R&#7845;t h&#224;i l&#242;ng</html:option>
    </html:select>  
</td>
 
<tr height="50px">
    <td align="center" colspan="2">
        <span class="bt_left_Search">
            <span class="bt_right_Search">
                <span class="bt_center_Search">
                    <html:button   property="_REPORT" styleClass="button" onclick="javascript:post('kpi',anchor + ':_RANK');closeWindow();">
                      <bean:message key="btn.rank" bundle="<%=interfaces%>"/>
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
