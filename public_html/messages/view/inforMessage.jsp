<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<logic:present name="BMessage" >
<html:hidden name="BMessage" property="id" styleId="id" />
 <table width="100%" style="border-collapse:collapse" cellpadding="0" border="0" cellspacing="0"  class="inforDetail" >
                    <tr>
                        <td valign="top" nowrap><Strong><bean:message key="mail.list.createUser" bundle="<%=interfaces%>"/>:</strong></td>
                        <td width="100%">
                        <span style="float:left">
                        <bean:write name="BMessage" property="userFullName" />
                        </span>
                        <span style="float:right">
                        
                        <bean:write name="BMessage" property="timeCreate" />
                        
                         <html:button property="send_reply_all" onclick="javascript:closeDetailEmail()" styleClass="button">
                            <bean:message key="mail.detail.theEnd" bundle="<%=interfaces%>"/>
                         </html:button>
                        </span>
                        </td>
                    </tr>
                    
                    <logic:notEmpty name="BMessage" property="empsRev">
                    
                    <tr>
                        <td valign="top" nowrap><Strong><bean:message key="rules.recieverEmp.caption" bundle="<%=interfaces%>"/>:</Strong></td>
                        <td>
                        <%com.form.messages.create.FCreate beanRev1=(com.form.messages.create.FCreate)request.getAttribute("BMessage");
                        if(beanRev1.getEmpsRev().size()>0){
                        com.form.messages.create.FCreate beanRevT=(com.form.messages.create.FCreate)beanRev1.getEmpsRev().get(0);
                        %>
                                    <%=beanRevT.getUserFullName()%>
                        <%
                        }
                        if(beanRev1.getEmpsRev().size()>1){
                            %>
                                <a style="color:#003399" href="javascript:hideshow('idCc')" >...</a>
                            <%
                        }
                        %>                           
                        

                        
                        </td>
                    </tr>
                    
                    <tr>
                        <td colspan="2">
                        <div id="idCc" style="display:none" class="cc-mail-detail">
                           <logic:iterate name="BMessage" id="beanRev" property="empsRev" indexId="j" type="com.form.messages.create.FCreate" >   
                             <%=j+1%>.<bean:write name="beanRev" property="userFullName" />;
                           </logic:iterate>
                        </div>   
                        </td>
                    </tr>
                    
                    </logic:notEmpty>
                    
                    
                    <tr>
                        <td valign="top" nowrap><bean:message key="mail.list.subject" bundle="<%=interfaces%>"/>:</td>
                        <td>
                        
                            <logic:equal name="BMessage" property="name" value="" >
                                <bean:message key="mail.list.no.subject" bundle="<%=interfaces%>"/>
                            </logic:equal>
                            <logic:notEqual name="BMessage" property="name" value="" >
                                <Strong><bean:write name="BMessage" property="name" /></strong>
                            </logic:notEqual>
                        
                        
                        </td>
                    </tr>
                    <logic:notEmpty name="BMessage" property="allFiles">
                    <tr>
                        <td colspan="2" >
                            <img src="<%=contextPath%>/images/attach.gif" />
                            <logic:iterate name="BMessage" property="allFiles" indexId="indexId" id="beanFiles" type="com.form.messages.create.FCreate">                       
                                    <%=++indexId%>.<a href="javascript:post('messsagesList',anchor + ':_SAVE:fileId:<%=beanFiles.getFileId()%>');remove('messsagesList',anchor);remove('messsagesList','fileId')">
                                   <bean:write name="beanFiles" property="readName" />
                                    </a>   
                            </logic:iterate>
                        </td>
                    </tr>
                    </logic:notEmpty>
                    </table>
                <logic:notEqual name="BMessage" property="fulltext" value="">            
                    <table width="100%" style="border-collapse:collapse;margin-right:15px;" cellpadding="0" cellspacing="0"    >        
                    <tr><td valign="top" >
                    <div class="content-mail-detail" id="mailContent" align="left" >
                                    <bean:define name="BMessage" property="fulltext" id="fulltext" type="java.lang.String"/>
                                    <%=fulltext%>
                    </div>
                                </td>
                                </tr>
                            </table>
                </logic:notEqual>
                    
                    <table width="100%" style="border-collapse:collapse" cellpadding="0" cellspacing="0" border="0"  class="inforDetail" >
                    <tr>
                            <td align="left" style="text-align:left" >
                                 <Strong><bean:message key="mail.header.replyMessage.fast" bundle="<%=interfaces%>"/></strong>
                            </td>
                            <td style="text-align:right" >
                                 <label for="utf8"><bean:message key="mail.list.decode.utf8" bundle="<%=interfaces%>"/></label>
                                 <input type="checkbox"  name="utf8" id="utf8" onclick="javascript:getObj('mailContent').innerHTML=this.checked?Utf8.decode(getObj('mailContent').innerHTML):Utf8.encode(getObj('mailContent').innerHTML)" />
                            </td>
                    </tr>
                    <tr>
                        <td colspan="2"  align="left" style="padding-bottom:10px;" >
                        
                        <table border="0" cellpadding="0" cellspacing="0" ><tr><td align="left" width="width:400px;" >
                        <textarea   name="contentFast"  id="contentFast" onkeyup="if(this.value!=''){getObj('cmd_show').style.display='block';}else{getObj('cmd_show').style.display='none'}"  style="width:440px;height:50px;border:1px solid #c2c2c2;"></textarea> 
                        </td>
                        <td id="cmd_show" style="display:none;text-align:left;vertical-align: bottom;" align="left" >
                            <% String onClickSendFast = "postAjax('createMessage','right',anchor + ':_SEND_PAST_REPLY')"; %>
                            <html:button property="send_reply" onclick="<%=onClickSendFast%>" styleClass="button" >
                            <bean:message key="mail.header.replyMessage" bundle="<%=interfaces%>"/>
                            </html:button>
                            
                            <% String onClickRepFast = "postAjax('createMessage','right',anchor + ':_SEND_PAST_REPLY_ALL')"; %>
                            <html:button property="send_reply_all" onclick="<%=onClickRepFast%>" styleClass="button">
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
</logic:present>                 