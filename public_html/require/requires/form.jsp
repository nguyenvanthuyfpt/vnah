<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/tinymce.js"></script>                
                <table class="tableForm" cellpadding="0" style="border-collapse: collapse" cellspacing="0" border="0" >    
                    <tr>
                        <td  nowrap="nowrap" align="right"><bean:message key="require.from.code.cation.endUser" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                        <td><html:text name="frmRequire" property="code" maxlength="50" /></td>
                        <td  nowrap="nowrap"><bean:message key="require.from.category.name.cation.endUser" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                        <td  align="left">
                                <html:select styleClass="inputbox" name="frmRequire" property="cateId">
                                    <logic:present name="BCatRequires">
                                        <html:options collection="BCatRequires" property="catId" labelProperty="name"/>          
                                    </logic:present>  
                                </html:select>
                        </td>
                    </tr>
                    <tr>
                           <td><bean:message key="require.from.name.cation.endUser" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td> 
                           <td colspan="3"><html:text name="frmRequire" property="name" maxlength="500" style="width:350px"/></td>                            
                    </tr>
                     <tr>          
                            <td align="right" nowrap="nowrap"><bean:message key="require.from.status.cation.endUser" bundle="<%=interfaces%>"/></td>
                            <td colspan="3">
                                <html:select styleClass="inputbox" name="frmRequire" property="rmStatus">
                                    <logic:present name="BRmStatuses">
                                        <html:options collection="BRmStatuses" property="statusId" labelProperty="name"/>          
                                    </logic:present> 
                                </html:select>
                          </td>
                    </tr>
                    <tr>
                        <td align="right" nowrap="nowrap"><bean:message key="require.from.datetimeto.cation" bundle="<%=interfaces%>"/></td>
                        <td  align="left"><html:text name="frmRequire" property="datetimto" maxlength="50" /><input type="image" src="<%=contextPath%>/images/ew_calendar.gif" alt='"/>' onClick="popUpCalendar(this,'datetimto','dd/mm/yyyy');return false;"></td>
                        <td  nowrap="nowrap"><bean:message key="require.from.datetimeFrom.cation" bundle="<%=interfaces%>"/></td>
                        <td  align="left"><html:text name="frmRequire" property="datetimFrom" maxlength="50" /><input type="image" src="<%=contextPath%>/images/ew_calendar.gif" alt='"/>' onClick="popUpCalendar(this,'datetimFrom','dd/mm/yyyy');return false;"></td>
                    </tr>
                     <tr>
                        <td  nowrap="nowrap"><bean:message key="require.from.timeto.cation" bundle="<%=interfaces%>"/></td>
                        <td  align="left"><html:text name="frmRequire" property="timto" maxlength="50" /></td>
                        <td  nowrap="nowrap"><bean:message key="require.from.timeFrom.cation" bundle="<%=interfaces%>"/></td>
                        <td  align="left"><html:text name="frmRequire" property="timFrom" maxlength="50" /></td>
                    </tr>
                    <tr>
                        <td align="right" nowrap="nowrap"><bean:message key="require.from.sunDay.cation" bundle="<%=interfaces%>"/></td>
                        <td  align="left" colspan="3">
                                <input type="checkbox" name="dayOfWeeks" value="8"/>
                                <bean:message key="require.from.dayOfWeek.cation" bundle="<%=interfaces%>"/> 2<input type="checkbox" name="dayOfWeeks" value="2"/>
                                <bean:message key="require.from.dayOfWeek.cation" bundle="<%=interfaces%>"/> 3<input type="checkbox" name="dayOfWeeks" value="3"/>
                                <bean:message key="require.from.dayOfWeek.cation" bundle="<%=interfaces%>"/> 4<input type="checkbox" name="dayOfWeeks" value="4"/>
                                <bean:message key="require.from.dayOfWeek.cation" bundle="<%=interfaces%>"/> 5<input type="checkbox" name="dayOfWeeks" value="5"/>
                                <bean:message key="require.from.dayOfWeek.cation" bundle="<%=interfaces%>"/> 6<input type="checkbox" name="dayOfWeeks" value="6"/>
                                <bean:message key="require.from.dayOfWeek.cation" bundle="<%=interfaces%>"/> 7<input type="checkbox" name="dayOfWeeks" value="7"/>
                        </td>
                       
                    </tr>
                    <tr>
                        <td  nowrap="nowrap" colspan="4"><bean:message key="require.from.content.cation.endUser" bundle="<%=interfaces%>"/></td>
                       
                    </tr>
                     <tr>
                        <td  nowrap="nowrap" colspan="4">
                                <textarea id="elm1" name="content" style="width:680px;height:200px">
                                        <bean:write name="frmRequire" property="content"/>
                                </textarea>   
                        </td>
                    </tr>
                    <tr>
                        <td  nowrap="nowrap" align="right" colspan="4"><bean:message key="require.from.repply.cation" bundle="<%=interfaces%>"/><html:checkbox name="frmRequire" property="repply" value="1"/></td>       
                    </tr>
                     <tr>
                        <td colspan="4" class="toolCmd" height="26px" style="padding-top:4px" align="left">
                             <jsp:include page="/require/requires/cmd.jsp" />   
                        </td>
                    </tr>
                </table>

