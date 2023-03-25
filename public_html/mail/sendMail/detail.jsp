<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <table width="100%" style="border-collapse:collapse" cellpadding="0" border="0" cellspacing="0"  class="inforDetail" >
                    <tr>
                        <td valign="top" nowrap><Strong><bean:message key="mail.list.createUser" bundle="<%=interfaces%>"/>:</strong></td>
                        <td width="100%">
                        <span style="float:left">
                        <bean:write name="sendMail" property="from" />
                        <img title="<bean:message key='mail.header.address.addnew' bundle='<%=interfaces%>'/>" src="<%=contextPath%>/images/addUser.gif" onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key='mail.header.address.addnew' bundle='<%=interfaces%>' />','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:email:<bean:write name="sendMail" property="from" />');"/>
                        </span>
                        <span style="float:right">
                        <bean:write name="sendMail" property="dateSend" />
                         <html:button property="send_reply_all" onclick="javascript:closeDetailEmail()" styleClass="button">
                            <bean:message key="mail.detail.theEnd" bundle="<%=interfaces%>"/>
                         </html:button>
                        </span>
                        </td>
                    </tr>
                    
                    <logic:notEmpty name="sendMail" property="tos">
                    <logic:notEqual name="sendMail" property="tos" value="">
                    <tr>
                        <td valign="top" nowrap><Strong><bean:message key="mail.list.userRecv" bundle="<%=interfaces%>"/>:</Strong></td>
                        <td>
                            <bean:define name="sendMail" property="tos" id="tos" />
                            <%javax.mail.internet.InternetAddress[] tosTem=(javax.mail.internet.InternetAddress[])tos;%>
                                <%String personal1=tosTem[0].getPersonal()==null?"":tosTem[0].getPersonal().replaceAll("'"," ");%>
                                <%=  personal1 + "&lt;" + tosTem[0].getAddress() + "&gt;"%>
                                <img title="<bean:message key='mail.header.address.addnew' bundle='<%=interfaces%>'/>" src="<%=contextPath%>/images/addUser.gif" onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key='mail.header.address.addnew' bundle='<%=interfaces%>' />','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:email:<%=personal1 + "<" + tosTem[0].getAddress() + ">"%>');"/>
                                <%if(tosTem.length>1){%>
                                     <a style="color:#003399" href="javascript:hideshow('idTo')" >...</a>
                                <%}%>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div id="idTo" style="display:none" class="cc-mail-detail">
                                <%for( int i=1; i<tosTem.length; i++ ){%>
                                    <%String personali=tosTem[i].getPersonal()==null?"":tosTem[i].getPersonal().replaceAll("'"," ");%>
                                    <%= personali + "&lt;" + tosTem[i].getAddress() + "&gt;" + ";"%>
                                    <img title="<bean:message key='mail.header.address.addnew' bundle='<%=interfaces%>' />" src="<%=contextPath%>/images/addUser.gif" onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key='mail.header.address.addnew' bundle='<%=interfaces%>' />','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:email:<%=  personali + "<" + tosTem[i].getAddress() + ">"%>');"/>
                                <%}%>
                            </div>
                        </td>
                    </tr>
                    </logic:notEqual>
                    </logic:notEmpty>
                    
                    <logic:notEmpty name="sendMail" property="ccs">
                    <logic:notEqual name="sendMail" property="ccs" value="">
                    <tr>
                        <td valign="top" nowrap><Strong><bean:message key="mail.list.cc" bundle="<%=interfaces%>"/>:</Strong></td>
                        <td>
                            <bean:define name="sendMail" property="ccs" id="ccs" />
                            <%javax.mail.internet.InternetAddress[] ccsTem=(javax.mail.internet.InternetAddress[])ccs;%>
                            <%String personal1=ccsTem[0].getPersonal()==null?"":ccsTem[0].getPersonal().replaceAll("'"," ");%>
                            <%=  personal1 + "&lt;" + ccsTem[0].getAddress() + "&gt;"%>
                            <img title="<bean:message key='mail.header.address.addnew' bundle='<%=interfaces%>'/>" src="<%=contextPath%>/images/addUser.gif" onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key='mail.header.address.addnew' bundle='<%=interfaces%>' />','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:email:<%=  personal1 + "<" + ccsTem[0].getAddress() + ">"%>');"/>
                            <%if(ccsTem.length>1){%>
                                 <a style="color:#003399" href="javascript:hideshow('idCc')" >...</a>
                            <%}%>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div id="idCc" style="display:none" class="cc-mail-detail">
                                <%for( int i=1; i<ccsTem.length; i++ ){%>
                                    <%String personali=ccsTem[i].getPersonal()==null?"":ccsTem[i].getPersonal().replaceAll("'"," ");%>
                                    <%= personali + " &lt;" + ccsTem[i].getAddress() + "&gt;"%>
                                    <img title="<bean:message key='mail.header.address.addnew' bundle='<%=interfaces%>'/>" src="<%=contextPath%>/images/addUser.gif" onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key='mail.header.address.addnew' bundle='<%=interfaces%>' />','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:email:<%=  personali + "<" + ccsTem[i].getAddress() + ">"%>');"/>
                                <%}%>
                            </div>
                        </td>
                    </tr>
                    </logic:notEqual>
                    </logic:notEmpty>
                    
                    <tr>
                        <td valign="top" nowrap><bean:message key="mail.list.subject" bundle="<%=interfaces%>"/>:</td>
                        <td>
                        
                        
                        <logic:equal name="sendMail" property="subject" value="" >
                            <bean:message key="mail.list.no.subject" bundle="<%=interfaces%>"/>
                        </logic:equal>
                        
                        <logic:notEqual name="sendMail" property="subject" value="" >
                            <Strong><bean:write name="sendMail" property="subject" /></strong>
                        </logic:notEqual>
                        
                        </td>
                    </tr>
                    <logic:notEmpty name="sendMail" property="allFiles">
                    <tr>
                        <td colspan="2" >
                            <img src="<%=contextPath%>/images/attach.gif" />
                            <%int i1=0;%>
                            <logic:iterate name="sendMail" property="allFiles" indexId="indexId" id="beanFiles" type="com.form.mail.FMail">                       
                                    <%=++i1%>.<A style="color:#003399" href="javascript:post('sendMail',anchor + ':_DOWNLOAD:fileId:<%=beanFiles.getFileId()%>');remove('sendMail',anchor);remove('sendMail','fileId');" ><bean:write name="beanFiles" property="fileName" /></a>  
                            </logic:iterate>
                        </td>
                    </tr>
                    </logic:notEmpty>
                    </table>
                <logic:notEqual name="sendMail" property="content" value="">            
                    <table width="100%" style="border-collapse:collapse;margin-right:15px;" cellpadding="0" cellspacing="0"    >        
                    <tr><td valign="top" id="mailContent" align="left"  >
                    <div class="content-mail-detail">
                                    <bean:define name="sendMail" property="content" id="content" type="java.lang.String" />                                     
                                    <%=content%>
                    </div>
                                </td>
                                </tr>
                            </table>
                </logic:notEqual>
          
                
                    
                    <table width="100%" style="border-collapse:collapse" cellpadding="0" cellspacing="0" border="0"  class="inforDetail" >
                    <tr>
                        <td align="left" style="text-align:left">
                        <Strong><bean:message key="mail.header.replyMessage.fast" bundle="<%=interfaces%>"/></strong>
                        </td>
                        <td align="right" style="text-align:right">
                            <label for="utf8"><bean:message key="mail.list.decode.utf8" bundle="<%=interfaces%>"/></label>
                            <input type="checkbox"  name="utf8" id="utf8" onclick="javascript:getObj('mailContent').innerHTML=this.checked?Utf8.decode(getObj('mailContent').innerHTML):Utf8.encode(getObj('mailContent').innerHTML)" />
                        </td>
                    </tr>
                    
                    <tr>
                        <td colspan="2"  align="left" style="padding-bottom:10px;" >
                        <table border="0" cellpadding="0" cellspacing="0" ><tr><td align="left" width="width:400px;" valign="top">
                        
                        <textarea   name="content"  id="content" onkeyup="if(this.value!=''){getObj('cmd_show').style.display='block';}else{getObj('cmd_show').style.display='none'}"  style="width:440px;height:80px;border:1px solid #c2c2c2;"></textarea> 
                        
                        </td>
                        <td id="cmd_show" style="display:none;text-align:left;vertical-align:top;" align="left" >
                            <html:button property="send_reply" onclick="javascript:post('sendMail',anchor +':_SEND_PAST_REPLY');" styleClass="button" >
                            <bean:message key="mail.header.replyMessage" bundle="<%=interfaces%>"/>
                            </html:button>
                            
                            <html:button property="send_reply_all" onclick="javascript:sendFastAll()" styleClass="button">
                            <bean:message key="mail.header.replyAllMessage" bundle="<%=interfaces%>"/>
                            </html:button>
                        </td>
                        </tr>
                        </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <span id="divAlert">
                            <html:errors property="alertError" bundle="<%=interfaces%>" />
                            </span>
                            <script language="javascript">
                            setTimeout("clear()",4500);
                            </script>
                        </td>
                    </tr>
                    </table>
                    <input type="hidden" name="subject" id="subject" value="Re: <bean:write name='sendMail' property='subject' />" />
                    <html:hidden name="sendMail" property="mailId" />
                    <html:hidden name="sendMail" property="cc" />
                    <input type="hidden" name="to" id="to" value="<bean:write name='sendMail' property='from' />" />


