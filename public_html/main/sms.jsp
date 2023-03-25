<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="sendSms" method="post" >
<%  int[] menuActiveTemp = (int[])request.getSession().getAttribute("menuActive");%>
<div class="col1-ctn1 clearfix">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
                                    <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_29.gif" width="8" height="44" /></td>
                                        <td class="repeatbackground" align="left">
                                            <span class="li-title-sms">
                                               <bean:message key="key.sms.title.module" bundle="<%=interfaces%>"/>
                                            </span>
                                        </td>
                                        <td width="30px" class="repeatbackground" >
                                                
                                        </td>
                                        <td width="15" class="repeatbackground"></td>
                                        <td width="7"><img src="<%=contextPath%>/images/newImages/i_30.gif" width="7" height="44" /></td>
                                    </tr>
                                </table>
                                <div style="clear:both"></div>
                                <div class="line-bottom">
                                	<div class="line-left">
                                    	<div class="line-right" >
                                        <div class="ct">
                                            <div class="b-news" align="left"> 
                                            <table cellpadding="2" cellspacing="2" width="90%" class="form-sms">
                                                <tr>
                                                    <td width="70px"><bean:message key="key.sms.to" bundle="<%=interfaces%>"/> :</td><td><input type="text" name="to" id="to" style="width:250px;" /></td>
                                                </tr>
                                                <tr>
                                                    <td width="70px" valign="top"><bean:message key="key.sms.content" bundle="<%=interfaces%>"/> :</td><td><textarea  name="content" id="content" style="width:250px;" onkeyup="getObj('totchars').value=this.value.length" ></textarea></td>
                                                </tr>
                                                <tr>
                                                    <td width="70px"><bean:message key="key.sms.totchars" bundle="<%=interfaces%>"/> :</td><td><input type="text" disabled="" value="0" id="totchars" name="totchars"/></td>
                                                </tr>
                                                <tr>
                                                    <td align="left">
                                                            <% String sendSMS="javascript:postAjax('sendSms','14.01',anchor + ':_SEND_SMS');";%>
                                                            <html:button property="_SEND_SMS" onclick="<%=sendSMS%>" styleClass="button">
                                                            <bean:message key="key.sms.send.command" bundle="<%=interfaces%>"/>
                                                            </html:button>
                                                    </td>
                                                    <td align="left" >
                                                            <Span class="alerMsgSms"><logic:present name="alertSmsSend" >
                                                                <bean:message key="alert.sms.send.successfull" bundle="<%=interfaces%>"/>
                                                            </logic:present></span>
                                                    </td>
                                                </tr>
                                            </table>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
</html:form>