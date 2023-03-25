<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<ul id="tree">
    <li>
        <div class="bgr1">
            <a href="#">
            Nh&#7853;p th&#244;ng tin Tuy&#7871;n T&#7881;nh / Th&#224;nh Ph&#7889;</a>
        </div>
    </li>
</ul>
<br/>

<div class="content-calendar">
    <div class="content-calendar-2">
    <table width="100%" cellpadding="0" cellspacing="0" border="0">
       <tr>
                <td align="left" colspan="2">
                        <bean:message key="disability.dansotinh.tinh" bundle="<%=interfaces%>"/> :
                        <html:select styleClass="inputbox" name="danSoTinh" property="id_tinh" onchange="optionChose(this.selectedIndex,this.value)">
                            <html:options collection="BTreeTinhs" property="id" labelName="level" labelProperty="name" />
                        </html:select>
                        <span style="color:#005BCC">(<bean:write name="danSoTinh" property="tinhName" />)</span>
                </td>
       </tr>
       <tr>
                <td align="left" colspan="2">
                        <bean:message key="disability.dansotinh.kyBao" bundle="<%=interfaces%>"/> :
                        <html:select styleClass="inputbox" name="danSoTinh" property="kyBao" style="width:30px">
                        <html:option value="1">1</html:option>
                        <html:option value="2">2</html:option>
                        </html:select>
                        <bean:message key="disability.dansotinh.nam" bundle="<%=interfaces%>"/> :
                        <html:text name="danSoTinh" property="nam" onblur="isYear(this);" style="width:40px" />
                </td>
       </tr>   
       <tr>
                <td align="left"><bean:message key="disability.dansotinh.tongHuyen" bundle="<%=interfaces%>"/> :<html:text name="danSoTinh" property="tongHuyen" onblur="isNumber(this);" style="width:40px;text-align:right;" /></td>
                <td align="left"><bean:message key="disability.dansotinh.huyenCct" bundle="<%=interfaces%>"/> :<html:text name="danSoTinh" property="huyenCct" onblur="isNumber(this);" style="width:40px;text-align:right;" /></td>
       </tr>
       <tr>
                <td align="left"><bean:message key="disability.dansotinh.tongXa" bundle="<%=interfaces%>"/> :<html:text name="danSoTinh" property="tongXa" onblur="isNumber(this);" style="width:40px;text-align:right;" /></td>
                <td align="left"><bean:message key="disability.dansotinh.soXaCoCt" bundle="<%=interfaces%>"/> :<html:text name="danSoTinh" property="soXaCoCt" onblur="isNumber(this);" style="width:40px;text-align:right;" /></td>
       </tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue1" onblur="isNumber(this);" style="width:20px" /> <bean:message key="disability.dansotinh.param1" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue2" onblur="isNumber(this);" style="width:20px"  /> <bean:message key="disability.dansotinh.param2" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue3" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param3" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue4" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param4" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue5" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param5" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue6" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param6" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue7" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param7" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue8" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param8" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue9" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param9" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue10" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param10" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue11" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param11" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue12" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param12" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue13" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param13" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue14" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param14" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue15" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param15" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue16" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param16" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue17" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param17" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue18" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param18" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue19" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param19" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue20" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param20" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue21" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param21" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue22" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param22" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue23" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param23" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue24" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param24" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue25" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param25" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue26" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param26" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue27" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param27" bundle="<%=interfaces%>"/></td></tr>
       <tr><td align="left" colspan="2"><html:text name="danSoTinh" property="paramValue28" onblur="isNumber(this);" style="width:20px"  /><bean:message key="disability.dansotinh.param28" bundle="<%=interfaces%>"/></td></tr>
       <tr>
                <td colspan="2" align="left">
        <html:button property="_CREATE" styleClass="button" onclick="post('danSoTinh',anchor + ':_CREATE');" >
                <bean:message key="action.insert" bundle="<%=interfaces%>"/>
         </html:button>
        <logic:notEqual name="danSoTinh" property="id" value="0">
        <bean:define  name="danSoTinh" property="id" id="id" type="java.lang.Integer" /> 
        <%String onclick="post('danSoTinh',anchor + ':_EDIT:id:"+id+"')"; %>
                <html:button property="_EDIT" styleClass="button" onclick="<%=onclick%>"   >                 
                <bean:message key="action.update" bundle="<%=interfaces%>"/>
                </html:button>        
        </logic:notEqual>
                </td>
       </tr>
    </table>
    </div>
</div>
 
