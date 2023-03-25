<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<html:form action="thongtinTuyen" method="post">

<ul id="tree">
    <li>
        <div class="bgr1"><a href="javascript:excutePostCategorys('_PREPARED_CREATE_INFO')">
        Nh&#7853;p th&#244;ng tin c&#225;c tuy&#7871;n</a></div>
    </li>
</ul>
<br/>
        
    <div class="content-calendar">	
        <div class="content-calendar-1">
            <table width="100%" class="tableForm" cellpadding="0" cellspacing="0" border="0" style="border:none;">
            <tr>
                <td>
                    <table width="100%">
                    <tr>
                        <td colspan="4" id="id_option_area">
                            <bean:message key="disability.dansohuyen.tinh" bundle="<%=interfaces%>"/>
                            <html:select styleClass="inputbox" name="thongtinTuyen" property="id_tinh" onchange="post('thongtinTuyen',anchor + ':_SELECT_IDTINH');">                                
                                <logic:present name="BTreeTinhs">
                                        <html:option value="0">-----<bean:message key="action.select" bundle="<%=interfaces%>"/>-------</html:option>
                                        <html:options collection="BTreeTinhs" property="id"  labelProperty="name" />
                                </logic:present>
                            </html:select>
                            <logic:notEmpty name="thongtinTuyen" property="tinhName">
                                <span style="color:#005BCC">(<bean:write name="thongtinTuyen" property="tinhName" />)</span>
                            </logic:notEmpty>
                        </td>
                    </tr>
                    
                     <tr>
                        <td  nowrap="nowrap">K&#7923;</td>
                        <td>
                            <html:select styleClass="inputbox" name="thongtinTuyen" property="kyBC" onchange="post('thongtinTuyen',anchor + ':_SELECT_IDTINH');">
                                    <html:option value="0">T&#7845;t c&#7843;</html:option>
                                    <html:option value="1">1 (6 th&#225;ng &#273;&#7847;u n&#259;m)</html:option>
                                    <html:option value="2">2 (6 th&#225;ng cu&#7889;i n&#259;m)</html:option>
                            </html:select>
                        </td>
                        <logic:greaterThan value="0" name="thongtinTuyen" property="kyBC">
                            <td  nowrap="nowrap">N&#259;m:</td>
                            <td>
                            <html:text name="thongtinTuyen" property="namBC" onblur="isYear(this);" style="width:70px;text-align:center;" />
                            </td>
                        </logic:greaterThan>
                        <logic:equal value="0" name="thongtinTuyen" property="kyBC">
                            <td colspan="2">&nbsp;</td>
                        </logic:equal>
                        </tr>
                    <tr>
                        <td>D&#226;n s&#7889;</td>
                        <td colspan="3"><html:text name="thongtinTuyen" property="totalPopulation" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:60px;text-align:right;"/></td>
                    </tr>
                    
                    <tr>
                        <td>Nam</td>
                        <td><html:text name="thongtinTuyen" property="totalMale" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:60px;text-align:right;"/></td>
                        <td>N&#7919;</td>
                        <td><html:text name="thongtinTuyen" property="totalFemale" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:60px;text-align:right;"/></td>
                    </tr>
                    
                    <tr>
                        <td>S&#7889; NKT n&#7919; c&#243; vi&#7879;c l&#224;m </td>
                        <td><html:text name="thongtinTuyen" property="femaleHasJob" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:60px;text-align:right;"/></td>		
                        <td>S&#7889; NKT c&#243; vi&#7879;c l&#224;m </td>
                        <td><html:text name="thongtinTuyen" property="totalHasJob" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:60px;text-align:right;"/></td>		
                    </tr>
                    
                    <tr>
                        <td>S&#7889; NKT n&#7919; th&#7845;t nghi&#7879;p / ch&#432;a c&#243; vi&#7879;c l&#224;m</td>
                        <td><html:text name="thongtinTuyen" property="femaleJobLess" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:60px;text-align:right;"/></td>		
                        <td>S&#7889; NKT th&#7845;t nghi&#7879;p / ch&#432;a c&#243; vi&#7879;c l&#224;m</td>
                        <td><html:text name="thongtinTuyen" property="totalJobLess" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:60px;text-align:right;"/></td>		
                    </tr>
                    </table>
                </td>
            </tr>
            
			
            <tr>
                <td align="left">                                    
                    <% if(request.getSession().getAttribute("06.01")!=null){%>
                    <html:button property="_CREATE" styleClass="button" onclick="post('thongtinTuyen',anchor + ':_CREATE:id:0');" >
                            <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                    </html:button>
                    <%}%>

                    <% if(request.getSession().getAttribute("06.02")!=null){%>
                    <logic:notEqual name="thongtinTuyen" property="id" value="0">
                            <bean:define  name="thongtinTuyen" property="id" id="id" type="java.lang.Integer" /> 
                            <% String onclick="post('thongtinTuyen',anchor + ':_EDIT:id:"+id+"')"; %>
                                    <html:button property="_EDIT" styleClass="button" onclick="<%=onclick%>">                 
                                    <bean:message key="action.update" bundle="<%=interfaces%>"/>
                                    </html:button>        
                    </logic:notEqual>
                        <%}%>
                </td>
            </tr>
            </table>
        </div>
		
        <!--<div style="height:20px">&nbsp;</div>	
        <div align="center" id="listId">
                <jsp:include page="/disability/thongtinTuyen/list.jsp"/>
        </div>-->  		
    </div>
</div>
</html:form>  
