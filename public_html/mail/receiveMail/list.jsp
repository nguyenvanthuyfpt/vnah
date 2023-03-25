<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="sendMail" method="post" enctype="multipart/form-data" >
<html:hidden name="sendMail" property="folderName" />
<html:hidden name="sendMail" property="accountId" />
<input type="hidden" name="tempAjax" id="tempAjax" />
<div class="padding-content">
    <div id="mailcol">
        <div class="tabmenu" id="container-1" >
            <div style="clear:both"></div>
            <ul id="ui-tabs-nav" style="padding-top:10px;padding-right:10px;">
            <jsp:include page="/mail/receiveMail/tools.jsp" />
            </ul>
            <div id="fragment-1">
               <div class="content-calendar">

<div align="right" style="line-height:18px;padding-right:10px; text-align: left;padding-top:3px;padding-bottom:3px;border:1px solid #CCCCCC" class="toolCmd"> 
        <table  cellpadding="0" border="0" cellspacing="0" width="100%" >
        <tr>
        <td align="left">
                <table class="tableClassSearch" cellpadding="0" border="0" cellspacing="0" >
                        <tr>
                            <td><input class="inputClassSearch" type="text" onkeydown="if(event.keyCode==13){post('sendMail',anchor + ':_SEARCH');return false;}" onfocus="javascript:if(this.value == '') this.value='';" onblur="javascript:if(this.value=='') this.value='';" name="searchContent" id="searchContent" value=""/>
                            </td>
                            <td class="imgClassSearch" height="18px" width="20px" onclick="post('sendMail',anchor + ':_SEARCH')" >&nbsp;</td>
                        </tr>
                </table>
        </td>
        <td  align="right" >
                    <A class="#" href="javascript:checkHaveIdChecked('_REVIEW_THIS_MAIL');">
                        <bean:message key="mail.list.caption.readed" bundle="<%=interfaces%>"/>
                       
                    </A>
                    |
                    <A class="#" href="javascript:checkHaveIdChecked('_TRANSFER_MESSAGE');">
                    <bean:message key="mail.header.transfer.in.message" bundle="<%=interfaces%>"/>
                    </A>
                    
                    <logic:equal name="BCheckRulesCreatorDataRecv" value="1">
                    |
                    <A  href="javascript:checkHaveIdChecked('_TRANSFER_RECV');"><bean:message key="mail.header.transfer.in.docsrecv" bundle="<%=interfaces%>"/></A>
                    </logic:equal>
        </td>
        </tr>
        </table>
</div>
               <div  id="main_detail_id"></div>
               <div id="main_list_mail">      
<table width="100%"   style="border-collapse: collapse"  cellpadding="0" cellspacing="0"  class="list-voffice">
    <tr>
        <th align="left" width="2px" nowrap><input type="checkBox" name="checkAll" id="checkAll" value="0" onclick="checkAllMailId(this);"></th>
        <th align="left" width="2px" nowrap>
        <img src="<%=contextPath%>/images/icon-star.png" />
        </th>
        <th width="130px">
        
            <logic:notEqual name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_SENT%>">    
                <bean:message key="mail.list.createUser" bundle="<%=interfaces%>"/>
            </logic:notEqual>
            <logic:equal name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_SENT%>">    
                <bean:message key="mail.list.userRecv" bundle="<%=interfaces%>"/>
            </logic:equal>
        </th>
        <th align="left" width="2px" nowrap>
        <img src="<%=contextPath%>/images/attach.gif" />
        </th>
        <th>
        <bean:message key="mail.list.subject" bundle="<%=interfaces%>"/>
        </th>
        <th><bean:message key="mail.list.dataRecv" bundle="<%=interfaces%>"/></th>
        <th nowrap ><bean:message key="mail.list.space" bundle="<%=interfaces%>"/></th>
    </tr>
<logic:present name="BInBoxs">
<logic:iterate name="BInBoxs" indexId="i" id="bean" type="com.form.mail.FMail">
<%javax.mail.internet.InternetAddress[] tosTem=(javax.mail.internet.InternetAddress[])bean.getTos();%>
<%javax.mail.internet.InternetAddress[] ccsTem=(javax.mail.internet.InternetAddress[])bean.getCcs();%>
<% String className=(bean.getReaded()==0?"listmail-bold":"listmail-normal");%>
<%String address="";%>
<logic:notEqual name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_SENT%>">    
     <%address=bean.getFrom();%>
</logic:notEqual>

<logic:equal name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_SENT%>">    
    <%
    if(tosTem!=null){
        address=tosTem[0].getAddress()==null?"":tosTem[0].getAddress();
    }
    %>
</logic:equal>
            
    <tr class="<%=(i%2==0)?"content1":"content"%>" >
        <td width="2px" nowrap align="left"><input type="checkBox" name="mailIds" id="mailIds" value="<%=bean.getMailId()%>" ></td>
        <td width="2px" nowrap class="<%=className%>" align="left">
            <%String[] starFlat={"icon-star-disable.png","icon-star.png"};%>
            <img src="<%=contextPath%>/images/<%=starFlat[bean.getFlagged()]%>" id="<%=bean.getFlagged()%>" onclick="javascript:excureFlat(this,<%=bean.getMailId()%>,'<%=contextPath%>')" />
        </td>
        <td class="<%=className%>" nowrap title='<%=address%>'  >

                <div style="display:none" id="content_ccs_tos<%=i%>">
                <Strong><bean:message key="mail.list.createUser" bundle="<%=interfaces%>"/>:</strong>
                <%
                String nameAddress=bean.getPersonal() +"&lt;"+ bean.getFrom() +"&gt;" ;%>
                <%=nameAddress%>
                <img src="<%=contextPath%>/images/addUser.gif" onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key='mail.header.address.addnew' bundle='<%=interfaces%>' />','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:email:<%=nameAddress%>');" />
                <%if(tosTem!=null && tosTem.length>0){%>
                <div>
                        <Strong><bean:message key="mail.list.userRecv" bundle="<%=interfaces%>"/>:</strong>
                        <%for( int j=0; j<tosTem.length; j++ ){
                           String personali=tosTem[j].getPersonal()==null?"":tosTem[j].getPersonal();%>
                           <%= personali + "&lt;" + tosTem[j].getAddress() + "&gt;"%>
                           <%String email ="&lt;" + tosTem[j].getAddress() + "&gt;";%>
                           <img src="<%=contextPath%>/images/addUser.gif" onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key='mail.header.address.addnew' bundle='<%=interfaces%>' />','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:email:<%=email%>');" />
                           <br>
                        <%}%>
                </div>
                <%}%>

                 <%if(ccsTem!=null && ccsTem.length>0){%>
                 <div>
                       <Strong><bean:message key="mail.list.cc" bundle="<%=interfaces%>"/></strong>:
                       <%for( int j=0; j<ccsTem.length; j++ ){
                            String personali=ccsTem[j].getPersonal()==null?"":ccsTem[j].getPersonal();%>
                            <%= personali + "&lt;" + ccsTem[j].getAddress() + "&gt;"%>
                            <%String email = "&lt;" + ccsTem[j].getAddress() +"&gt;";%>
                            <img src="<%=contextPath%>/images/addUser.gif" onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key='mail.header.address.addnew' bundle='<%=interfaces%>' />','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:email:<%=email%>');" />
                            <br>
                        <%}%>
                </div>
                <%}%>
                
            </div>

        <span onclick="Tip(getObj('content_ccs_tos<%=i%>').innerHTML, CLOSEBTN, true, CLOSEBTNCOLORS, ['', '#66ff66', 'white', '#00cc00'], STICKY, true)">
            <logic:notEqual name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_SENT%>">                    
                <bean:write name="bean" property="personal"  />
            </logic:notEqual>                
            <logic:equal name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_SENT%>">
                <bean:write name="bean" property="to"  />
            </logic:equal>
        </span>
        </td>
        <td width="2px" nowrap class="<%=className%>" align="left">
            <logic:equal name="bean" property="checkHaveFile" value="1">
                <img src="<%=contextPath%>/images/attach.gif"/>
            </logic:equal>
        </td>
        <td class="<%=className%>" >
        <a style="cursor:pointer" onclick="getDetailShow(this,<%=bean.getMailId()%>)">

<logic:equal name="bean" property="checkIsTo" value="1">
        <img src="<%=contextPath%>/images/i_06.gif"/>
</logic:equal>
            <logic:notEqual name="bean" property="subject" value="">
                <bean:write name="bean" property="subject" />
            </logic:notEqual>
            <logic:equal name="bean" property="subject" value="">
                <bean:message key="mail.list.no.subject" bundle="<%=interfaces%>"/>
            </logic:equal>
        </a></td>
        <td class="<%=className%>" width="100px" ><bean:write name="bean" property="dateSend" /></td>
        <td class="<%=className%>" width="90px" ><bean:write name="bean" property="fileSize" /></td>
    </tr>
</logic:iterate>
</logic:present> 
</table>

<div class="textnone-header">
<%String params = anchor + ":_INBOX";%>
            <jsp:include page="/mail/paging.jsp">
            <jsp:param name="BEANS" value="BInBoxs"/>
            <jsp:param name="PARAMS" value="<%=params%>"/>
            <jsp:param name="FORM" value="sendMail"/>
            <jsp:param name="METHOD" value="post"/>
            </jsp:include>
</div>

<logic:empty name="BInBoxs">
<DIV align="center" class="notDataMessage">
        <bean:message key="mail.list.messages.notdata" bundle="<%=interfaces%>"/>
</div>
</logic:empty>
                    
             </div>
    </div>
    </div>
    </div>
</div>
</div>
</html:form>