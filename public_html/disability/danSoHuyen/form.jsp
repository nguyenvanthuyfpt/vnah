<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<ul id="tree">
    <li>
        <div class="bgr1">
            <a href="#">
            Nh&#7853;p th&#244;ng tin Tuy&#7871;n Qu&#7853;n/Huy&#7879;n</a>
        </div>
    </li>
</ul>
<br/>

<div class="content-calendar">
    <div class="content-calendar-2">
        <table width="100%" cellpadding="0" cellspacing="0" border="0">
           <tr>
                <td align="left" colspan="2">
                    <bean:message key="disability.dansohuyen.tinh" bundle="<%=interfaces%>"/> :
                    <html:select styleClass="inputbox" name="danSoHuyen" property="id_tinh" onchange="optionChose(this.selectedIndex,this.value)">
                    <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
                    </html:select>
                    <span style="color:#005BCC">(<bean:write name="danSoHuyen" property="tinhName" />)</span>
                </td>
            </tr>
            <tr>
                <td align="left" colspan="2">
                    <bean:message key="disability.dansohuyen.kyBao" bundle="<%=interfaces%>"/> :
                    <html:select styleClass="inputbox" name="danSoHuyen" property="kyBao" style="width:30px">
                        <html:option value="1">1</html:option>
                        <html:option value="2">2</html:option>
                    </html:select>
                    <bean:message key="disability.dansohuyen.name" bundle="<%=interfaces%>"/> :
                    <html:text name="danSoHuyen" property="nam" style="width:40px" />
                </td>
            </tr>
            <tr>
                <td align="left"><bean:message key="disability.dansohuyen.tongxa" bundle="<%=interfaces%>"/> :<html:text name="danSoHuyen" property="tongXa" style="width:40px" /></td>
                <td align="left"><bean:message key="disability.dansohuyen.xcct" bundle="<%=interfaces%>"/> :<html:text name="danSoHuyen" property="xcct" style="width:40px" /></td>
            </tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue1" style="width:30px"  /> <bean:message key="disability.dansohuyen.param1" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue2" style="width:30px"  /> <bean:message key="disability.dansohuyen.param2" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue3" style="width:30px"  /><bean:message key="disability.dansohuyen.param3" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue4" style="width:30px"  /><bean:message key="disability.dansohuyen.param4" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue5" style="width:30px"  /><bean:message key="disability.dansohuyen.param5" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue6" style="width:30px"  /><bean:message key="disability.dansohuyen.param6" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue7" style="width:30px"  /><bean:message key="disability.dansohuyen.param7" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue8" style="width:30px"  /><bean:message key="disability.dansohuyen.param8" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue9" style="width:30px"  /><bean:message key="disability.dansohuyen.param9" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue10" style="width:30px"  /><bean:message key="disability.dansohuyen.param10" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue11" style="width:30px"  /><bean:message key="disability.dansohuyen.param11" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue12" style="width:30px"  /><bean:message key="disability.dansohuyen.param12" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue13" style="width:30px"  /><bean:message key="disability.dansohuyen.param13" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue14" style="width:30px"  /><bean:message key="disability.dansohuyen.param14" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue15" style="width:30px"  /><bean:message key="disability.dansohuyen.param15" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue16" style="width:30px"  /><bean:message key="disability.dansohuyen.param16" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue17" style="width:30px"  /><bean:message key="disability.dansohuyen.param17" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue18" style="width:30px"  /><bean:message key="disability.dansohuyen.param18" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue19" style="width:30px"  /><bean:message key="disability.dansohuyen.param19" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue20" style="width:30px"  /><bean:message key="disability.dansohuyen.param20" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue21" style="width:30px"  /><bean:message key="disability.dansohuyen.param21" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue22" style="width:30px"  /><bean:message key="disability.dansohuyen.param22" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue23" style="width:30px"  /><bean:message key="disability.dansohuyen.param23" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue24" style="width:30px"  /><bean:message key="disability.dansohuyen.param24" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue25" style="width:30px"  /><bean:message key="disability.dansohuyen.param25" bundle="<%=interfaces%>"/></td></tr>
           <tr><td align="left" colspan="2"><html:text name="danSoHuyen" property="paramValue26" style="width:30px"  /><bean:message key="disability.dansohuyen.param26" bundle="<%=interfaces%>"/></td></tr>
           <tr>
                <td colspan="2" align="left">
                    <html:button property="_CREATE" styleClass="button" onclick="post('danSoHuyen',anchor + ':_CREATE:id:0');" >
                        <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                    </html:button>
                    
                    <logic:notEqual name="danSoHuyen" property="id" value="0">                    
                    <bean:define  name="danSoHuyen" property="id" id="id" type="java.lang.Integer" /> 
                    <%String onclick="post('danSoHuyen',anchor + ':_EDIT:id:"+id+"')"; %>
                    <html:button property="_EDIT" styleClass="button" onclick="<%=onclick%>"   >                 
                        <bean:message key="action.update" bundle="<%=interfaces%>"/>
                    </html:button>
                    </logic:notEqual>               
                </td>
            </tr>
        </table>   
    </div>
</div> 
