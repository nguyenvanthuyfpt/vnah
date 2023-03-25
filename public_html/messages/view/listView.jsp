<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div align="right" style="line-height:18px;padding-right:10px; text-align: left;padding-top:3px;padding-bottom:3px;border:1px solid #CCCCCC" class="toolCmd"> 
<table  cellpadding="0" border="0" cellspacing="0" width="100%" >
<tr>
    <td align="left">
<table class="tableClassSearch" cellpadding="0" border="0" cellspacing="0" >
        <tr>
            <td><input class="inputClassSearch" type="text" onkeydown="if(event.keyCode==13){postAjax('createMessage','right',anchor + ':_PREPARED_SAVE');return false;}" onfocus="javascript:if(this.value == '') this.value='';" onblur="javascript:if(this.value=='') this.value='';" name="nameSearch" id="name" value=""/>
            </td>
            <td class="imgClassSearch" height="18px" width="20px" onclick="postAjax('createMessage','right',anchor + ':_PREPARED_SAVE')" >&nbsp;</td>
        </tr>
</table>
    </td>
    <td align="right">
<logic:notEmpty name="accountEmail" >
    <logic:notEqual name="accountEmail" property="userMail" value="">
        <a href="#" onclick="javascript:post('createMessage',anchor + ':_PREPARED_SEND_EMAIL:delete:1');messageImg('MainMessage');">
        <bean:message key="messages.tool.transfer.email" bundle="<%=interfaces%>"/> 
        </a>
    </logic:notEqual>
</logic:notEmpty>
    </td>
</tr>
</table>
</div>

<div id="main_list_mail"  >
    <logic:present name="messsagesList">
    <bean:define name="messsagesList" property="orderIndex" id="orderIndex" type="java.lang.Integer"/>
    <bean:define name="messsagesList" property="type" id="type" type="java.lang.Integer"/>
    <bean:define name="BMessages" id="beans" type="com.form.FBeans"/>
   <table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
        <TBODY>
            <TR>               
                    <TH   width="4%" nowrap align="center">
                        <input type="checkbox" name="checkAllEmp"  value="1" onclick="checkAll(document.createMessage.checkEmp,this)"/>
                    </TH>            
                    <TH  width="3%"><img src="<%=contextPath%>/images/mail-icon.gif" /></TH>
                    <TH  width="3%"><img src="<%=contextPath%>/images/attach.gif" border="0"/></TH>
                    <TH  align="right">
                    <logic:notEqual name="messsagesList" property="type" value="2"> 
                        <span style="cursor: pointer;"><bean:message key="messages.list.empSend" bundle="<%=interfaces%>"/> </span>
                    </logic:notEqual> 
                    <logic:equal name="messsagesList" property="type" value="2"> 
                        <span style="cursor: pointer;"><bean:message key="messages.list.empRecv" bundle="<%=interfaces%>"/> </span>
                    </logic:equal> 
                    </TH>
                    <TH  width="50%"><bean:message key="messages.list.title" bundle="<%=interfaces%>"/></TH>                                            
                    <TH   width="12%" nowrap>                                                        
                    <bean:message key="mail.list.dataRecv" bundle="<%=interfaces%>"/>
                   </TH>
            </TR>
            <logic:present name="BMessages"> 
            <%  int i = beans.getFirstRecord();%>
            <logic:iterate name="BMessages" id="bean" type="com.form.messages.create.FCreate">                                       
            <% String className=(bean.getReaded()==0?"listmail-bold":"listmail-normal");%>
            <tr class="<%=(i%2==0)?"content1":"content"%>"><%i++;%>
                <td  width="4%" nowrap align="center">
                   <input type="checkbox" name="checkEmp" value="<bean:write name="bean" property="id"/>" >
                </td>
                <td  nowrap align="center"><logic:equal name="bean" property="sendMail" value="1">
                <img style="cursor:pointer;" onmouseover="Tip('B&#7841;n c&#243; th&#432; &#273;i&#7879;n t&#7917;')" onmouseout="UnTip()" src="<%=contextPath%>/images/mail-icon.gif" /></logic:equal></td>
                 <td  nowrap align="center" class="<%=className%>" >
                         <logic:notEmpty name="bean" property="allFiles">
                            <% if (bean.getAllFiles().size()>1) {%>
                                <img style="cursor:pointer" src="<%=contextPath%>/images/attach.gif" onclick="checkedInnerHtml();addthis_open(this,'Files','','', '');postAjax('messsagesList','at_share',anchor + ':_FILEDINHKEM:id:<%=bean.getId()%>');" />
                            <%}else{%>
                                <logic:iterate name="bean" property="allFiles" id="beanFiles" type="com.form.messages.create.FCreate">    
                                <a href="javascript:post('messsagesList',anchor + ':_SAVE:fileId:<%=beanFiles.getFileId()%>');remove('messsagesList',anchor);remove('messsagesList','fileId')">
                                <img style="cursor:pointer" src="<%=contextPath%>/images/attach.gif" border="0"/>
                                </a>
                                </logic:iterate>
                           <%}%>
                         </logic:notEmpty>        
                </td>
               
                    <td  align="left" class="<%=className%>"  >
                    
                      <span style="cursor:pointer" onclick="Tip(getObj('empsRevId<%=i%>').innerHTML, CLOSEBTN, true, CLOSEBTNCOLORS, ['', '#66ff66', 'white', '#00cc00'], STICKY, true)">
                      <bean:write name="bean" property="userFullName"/>
                      </span>
                      
                      <span id="empsRevId<%=i%>" style="display:none">
                              <div>
                              <strong><bean:message key="messages.list.empSend" bundle="<%=interfaces%>"/>:</strong>
                                <bean:write name="bean" property="userFullName"/>
                              </div>
                              <div><Strong><bean:message key="messages.list.empRecv" bundle="<%=interfaces%>"/>:</Strong>
                              <logic:iterate name="bean" property="empsRev" id="bean1" indexId="j" type="com.form.messages.create.FCreate">   
                                        <bean:write name="bean1" property="userFullName"/>;
                                        <%if((j+1)%4==0){%>
                                        <br/>
                                        <%}%>
                              </logic:iterate>
                              </div>
                      </span>
                      
                    </td>
                <td  align="left" class="<%=className%>" >      
                
                            <span style="cursor:pointer"  onClick="getDetailShow(this,'<%=type%>','<%=bean.getId()%>','<%=bean.getIdRec()%>')">
                                <bean:write name="bean" property="name"/>
                            </span>                         
                </td>                    
                <td  nowrap class="<%=className%>"  ><bean:write name="bean" property="timeCreate"/></td>
            </tr>    
            <tr><td colspan="7" id="tdMessageDetail<%=bean.getId()%>"></td></tr>
            </logic:iterate>
            </logic:present>
        </tbody>
    </table>
    </logic:present>      
 <table width="100%">
<tr>
 <logic:present name="BMessages" >
    <td align="left" valign="middle"></td>
    </logic:present>
    <td align="right" valign="top">
    <div class="textnone-header">
      <% String params = anchor + ":_PREPARED_SAVE" ;%>
              <jsp:include page="/paging.jsp">
                <jsp:param name="BEANS" value="BMessages"/>
                <jsp:param name="FORM" value="createMessage"/>
                <jsp:param name="METHOD" value="postAjax"/>
                <jsp:param name="POSITION" value="right"/>
                 <jsp:param name="PARAMS" value="<%=params%>"/>
            </jsp:include>
    </div>
    </td>
</tr>
</table> 
</div>
<div id="main_detail_id"></div>
