<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
            
            function excureFlat(obj,mailId,path){
                    if(obj.id==0){
                        obj.src='/images/icon-star.png';obj.id=1;
                    }else{
                        obj.src='/images/icon-star-disable.png';obj.id=0;
                    }
                   // postAjax('sendMail','tempAjax',anchor +':_FLAGGED:flagged:'+obj.id+':mailId:'+ mailId);
            }
            </Script>
            
<script language="javascript">
function mdotab(obj,anchorObj){
    if(obj.className=='tab1'){
            for(i=0;i<obj.parentNode.childNodes.length;i++){
                    if(obj.parentNode.childNodes[i].className=='tabactive1') obj.parentNode.childNodes[i].className='tab1';
            }
            obj.className='tabactive1';
            postAjax('formMyContact','tdMycontact',anchor +':'+anchorObj+'');
        }
    }
    
    function emptyFolder(folderName){
            if(confirm(unescape('<bean:message key="alert.mail.empty.questions.yes.no" bundle="<%=interfaces%>"/>'))){
                post('control',anchor + ':_EMPTY_ALL:folderName:'+folderName);
            }
    }
    
    function checkInsertContact(obj){
        if(obj.fullName.value=='' || obj.pgroupId.value==''  || obj.icq.value==''){
            alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
            return false;
        }else{
            postAjax('openMycontact','tdMycontact',anchor + ':_CREATE_OPEN');addthis_close();
        }
    }
    function checkUpdateContact(obj){
        if(obj.fullName.value=='' || obj.pgroupId.value==''  || obj.icq.value==''){
            alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
            return false;
        }else{
            postAjax('openMycontact','tdMycontact',anchor + ':_EDIT_OPEN');addthis_close();
        }
    }
    
    function sendAddress(obj){
    var tem =getObj('tem')!=null?getObj('tem').value:0;
    var tempInputtext=(tem==0?'toAddress':'cc');
    var getFullName=obj.innerHTML;
    var getIcq=obj.id;
    if(getObj(tempInputtext)!=null){
        var getTo=getObj(tempInputtext).value;
        if(getFullName!=''){
                    if(getTo!=null && getTo!='' && getTo.substring(getTo.length-1,getTo.length)==';'){
                        if(getObj(tempInputtext).value.indexOf(getIcq)<0){
                            getObj(tempInputtext).value+=getIcq + ";";
                        }
                    }else{
                        if(getObj(tempInputtext).value.indexOf(getIcq)<0){
                            getObj(tempInputtext).value=getIcq + ";";
                        }
                    }    
            }else{
                     if(getObj(tempInputtext).value.indexOf(getIcq)<0){
                        getObj(tempInputtext).value=getIcq + ";";
                     }
            }
    }else{
           post('control',anchor +':_PREPARE_ADD_RESS:toAddress:'+getIcq+";");
           messageImg('right')
    }
  }
  
  
  
  
  function checkHaveIdChecked(forward){
        if(document.sendMail.mailIds!=null && document.sendMail.mailId==null){
            var id=document.sendMail.mailIds;
            if(typeof id.length !="undefined"){
                    for (i=0;i<id.length;i++){
                        if(id[i].checked){
//                            if(forward=='_DELETE'){
//                                postAjax('sendMail','divAlert',anchor + ':' + forward);
//                            }else{
                                post('sendMail',anchor + ':' + forward);
                                messageImg('right');
//                            }
                            break;
                        }
                    }
               }else{
                        if(id.checked){
//                            if(forward=='_DELETE'){
//                                postAjax('sendMail','divAlert',anchor + ':' + forward);
//                            }else{
                                post('sendMail',anchor + ':' + forward);
                                messageImg('right');
//                            }
                        }
               }
        }else{
//                        if(forward=='_DELETE'){
//                            postAjax('sendMail','divAlert',anchor + ':' + forward);
//                        }else{
                            post('sendMail',anchor + ':' + forward);
                            messageImg('right');
//                        }
        }
}

function unlinesDelete(forward){
       var obj = document.sendMail.mailIds;
        if(typeof obj !="undefined"){
          if(document.sendMail.mailId==null){              
               if(typeof obj.length !="undefined"){
                    for (i=0;i<obj.length;i++){
                        if(obj[i].checked){
                            obj[i].parentNode.parentNode.cells[3].innerHTML="<s>"+ obj[i].parentNode.parentNode.cells[3].innerHTML +"</s>";
                        }
                    }
               }else{
                        if(obj.checked){
                            var temValue=obj.parentNode.parentNode.cells[3].innerHTML.trim();
                            obj.parentNode.parentNode.cells[3].innerHTML="<s>"+ temValue +"</s>";
                        }
               }
            } 
     }else {
       //alert(getObj('mailIds').value);
     }
    checkHaveIdChecked(forward);
}
function checkAllMailId(obj){
    if(obj.form.mailIds!=null){
        var ids=obj.form.mailIds;
        if(typeof ids.length !="undefined"){
            for (i=0;i<ids.length;i++){
                ids[i].checked=obj.checked;
            }
       }else{
       ids.checked=obj.checked;
       }
   }else{return false;}
}

function getDetailShow(obj,mailId){
    for(i=0;i<obj.parentNode.parentNode.cells.length;i++){ 
        obj.parentNode.parentNode.cells[i].className='listmail-normal';
    }
    messageImg('main_detail_id');
    
    postAjax('sendMail','main_detail_id',anchor + ':_DETAIL_SEND:mailId:' + mailId)
    getObj('main_list_mail').style.display='none';
}
function closeDetailEmail(){
    getObj('main_detail_id').innerHTML='';
    getObj('main_list_mail').style.display='block';
}

function sendFastAll(){
    postAjax('sendMail','main_detail_id',anchor +':_SEND_PAST_REPLY_ALL')
    //getObj('main_list_mail').style.display='none';
}




</script>

<script language=javascript>
var Utf8 = {
 
	// public method for url encoding
	encode : function (string) {
		string = string.replace(/\r\n/g,"\n");
		var utftext = "";
 
		for (var n = 0; n < string.length; n++) {
 
			var c = string.charCodeAt(n);
 
			if (c < 128) {
				utftext += String.fromCharCode(c);
			}
			else if((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			}
			else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}
 
		}
 
		return utftext;
	},
 
	// public method for url decoding
	decode : function (utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;
 
		while ( i < utftext.length ) {
 
			c = utftext.charCodeAt(i);
 
			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			}
			else if((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i+1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			}
			else {
				c2 = utftext.charCodeAt(i+1);
				c3 = utftext.charCodeAt(i+2);
				string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
 
		}
 
		return string;
	}
 
}
function getInfor(ac){
      post('control',anchor + ':_INBOX:folderName:' + ac);
      messageImg('tdMainBody');
}
    function checkSubmit(form){
    if(form.fullName.value==''  ||  form.email.value==''){
        return false;
    }
    return true;
}
</script>
<script type="text/javascript" src="<%=contextPath%>/mail/js/searchEmail.js"></script>
<%
com.form.mail.FMail beanT=(com.form.mail.FMail)request.getSession().getAttribute("meEmail");
%>
<html:form action="registerMail" method="post" />
<div id="left">
     <div class="ctn-left">
        <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic">
        <bean:message key="mail.email.caption" bundle="<%=interfaces%>"/>
        </div></div>
         <html:form action="control" method="post" >
         <div class="csstable">
             <table cellpadding="0" cellspacing="0" border="0">
                <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_47.gif" /></td>
                    <td bgcolor="#a0aec2" style="padding:0 7px"><span class="add">
                            <a href="javascript:post('control',anchor +':_PREPARE_SEND');"><img src="<%=contextPath%>/images/newImages/i_16.gif" align="left" /><b><bean:message key="mail.header.createMessage" bundle="<%=interfaces%>"/></b></a>
                    </span></td>
                    <td width="8"><img src="<%=contextPath%>/images/newImages/i_46.gif" width="8" /></td>
                    <td style="padding-left:10px;"><a href="javascript:post('registerMail',anchor +':_SHOW');"><Strong><bean:message key="category.mailAccount.register" bundle="<%=interfaces%>"/></strong></a></td>
                </tr>
             </table>
        </div>
        
        <div class="status">
                    <html:select name="sendMail" property="accountId" styleId="accountId" onchange="post('loginEmail',anchor +':_LOGIN:accountId:'+ this.value)" style="width:160px;" styleClass="fieldSelect"  >
                        <logic:present name="BRegisters">  
                        <html:options collection="BRegisters" property="id" labelProperty="userMail"/>
                        </logic:present>
                    </html:select>
                    
                        <div class="inbox">
                        <table width="100%">
                        <logic:notEmpty name="BTotals">
                                <bean:define name="BTotals" id="bean" type="com.form.mail.FMail" />
                                <tr>
                                <td colspan="2">
                                <%if(bean.getTotalUnReadedMessage()>0){%>
                                   <p onmouseover="className='row1'" onmouseout="className='row2'"  onclick="javascript:getInfor('<%=com.inf.mail.IKeyMail.FOLDER_INBOX%>')">
                                           <Strong>
                                           <logic:notEqual name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_INBOX%>">
                                                <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                                           </logic:notEqual>
                                           <logic:equal name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_INBOX%>">
                                                <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                                           </logic:equal>
                                           <bean:message key="mail.tree.recvMessage" bundle="<%=interfaces%>"/> (<%=bean.getTotalUnReadedMessage()%>)</strong>
                                   </p>
                                <%}else{%>
                                   <p onmouseover="className='row1'" onmouseout="className='row2'" onclick="javascript:getInfor('<%=com.inf.mail.IKeyMail.FOLDER_INBOX%>')">
                                            <logic:notEqual name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_INBOX%>">
                                                <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                                            </logic:notEqual>
                                            <logic:equal name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_INBOX%>">
                                                <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                                            </logic:equal>
                                            <bean:message key="mail.tree.recvMessage" bundle="<%=interfaces%>"/> (<%=bean.getTotalUnReadedMessage()%>)
                                            
                                </p>
                                <%}%>
                                </td>
                                </tr>
                                
                                <tr>
                                <td colspan="2">
                                <p onmouseover="className='row1'" onmouseout="className='row2'" onclick="javascript:getInfor('<%=com.inf.mail.IKeyMail.FOLDER_SENT%>')">
                                   <logic:notEqual name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_SENT%>">
                                        <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                                   </logic:notEqual>
                                   <logic:equal name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_SENT%>">
                                        <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                                   </logic:equal>
                                    <bean:message key="mail.tree.sendMessage" bundle="<%=interfaces%>"/>
                                </p>
                                </td>
                                </tr>
                                
                                <tr>
                                <td>
                                    <p onmouseover="className='row1'" onmouseout="className='row2'" >
                                       <span onclick="javascript:getInfor('<%=com.inf.mail.IKeyMail.FOLDER_DRAFT%>')">
                                       <logic:notEqual name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_DRAFT%>">
                                            <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                                       </logic:notEqual>
                                       <logic:equal name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_DRAFT%>">
                                            <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                                       </logic:equal>
                                       <bean:message key="mail.tree.draftMessage" bundle="<%=interfaces%>"/>
                                       </span>
                                       <span>
                                        </span>
                                    </p>
                                </td>
                                <td>
                                    <img style="cursor:pointer;" title="<bean:message key="mail.delete.thu.giac.infor" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/trash_9x11.gif" onclick="emptyFolder('<%=com.inf.mail.IKeyMail.FOLDER_DRAFT%>')"  />
                                </td>
                                </tr>
                                <tr>
                                <td>
                                <p onmouseover="className='row1'" onmouseout="className='row2'" >
                                   <span onclick="javascript:getInfor('<%=com.inf.mail.IKeyMail.FOLDER_SPAM%>')">
                                   <logic:notEqual name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_SPAM%>">
                                        <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                                   </logic:notEqual>
                                   <logic:equal name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_SPAM%>">
                                        <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                                   </logic:equal>
                                       <bean:message key="mail.header.alert.message.spam" bundle="<%=interfaces%>"/>
                                   </span>
                                  </p>
                                 </td>
                                 <td><img style="cursor:pointer;" title="<bean:message key="mail.delete.thung.giac.infor" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/trash_9x11.gif" onclick="emptyFolder('<%=com.inf.mail.IKeyMail.FOLDER_SPAM%>');"/></td>
                                </tr>
                                
                                
                                <tr>
                                <td>
                                <p onmouseover="className='row1'" onmouseout="className='row2'" >
                                   <span onclick="javascript:getInfor('<%=com.inf.mail.IKeyMail.FOLDER_TRASH%>')">
                                   <logic:notEqual name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_TRASH%>">
                                        <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                                   </logic:notEqual>
                                   <logic:equal name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_TRASH%>">
                                        <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                                   </logic:equal>
                                   <bean:message key="mail.tree.deleteMessage" bundle="<%=interfaces%>"/>
                                   </span>
                                  </p>
                                 </td>
                                 <td><img style="cursor:pointer;" title="<bean:message key="mail.delete.thung.giac.infor" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/trash_9x11.gif" onclick="emptyFolder('<%=com.inf.mail.IKeyMail.FOLDER_TRASH%>');"/></td>
                                </tr>
                        </logic:notEmpty>                        
                        </table>
                        </div>
        </div>
        </html:form>
        
        <html:form action="formMyContact" method="post" >
        <input type="hidden" name="tem" id="tem" value="0"  />
        <div class="csstable">
             <table cellpadding="0" cellspacing="0" border="0">
                <tr>
                <td nowrap>
                
<table width="100%"><tr><td nowrap>
        <span class="tabactive1"  onclick="mdotab(this,'_LIST_MY_CONTACT');" >
            <bean:message key="cmd.mail.list.employer.private" bundle="<%=interfaces%>"/></span> | 
        <span class="tab1" onclick="mdotab(this,'_EMPLOYER');">
            <bean:message key="cmd.mail.list.employer.company" bundle="<%=interfaces%>"/>
        </span>
</td></tr>
</table>
                    <td align="right" nowrap width="100%" style="padding-right:10px;">
                     <img src="<%=contextPath%>/images/addGroup.gif" title="Th&#234;m nh&#243;m"  onclick="checkedInnerHtml();addthis_open(this,'<bean:message key="pgroup.pgroup.buttom.caption" bundle="<%=interfaces%>"/>','','','',1);postAjax('formMyContact','at_share',anchor + ':_VIEW_PGROUP:id:0');" />                            
                     <img src="<%=contextPath%>/images/addUser.gif" title="Th&#234;m &#273;&#7883;a ch&#7881;" onclick="checkedInnerHtml();addthis_open(this,'<bean:message key="mycontact.infor.caption" bundle="<%=interfaces%>"/>','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:id:0');" />                  
                     </td>
                </tr>
             </table>
        </div>
        <div class="status" id="tdMycontact">
            <jsp:include page="/mycontact/show.jsp" />
        </div>
        </html:form>
    </div>
</div>
