<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
function checkHaveIdChecked(forward,reply){
        var checkEmp=document.createMessage.checkEmp;
        var id=getObj('id');
        if(id==null){
            if(typeof checkEmp.length !="undefined"){
                    for (i=0;i<checkEmp.length;i++){
                        if(checkEmp[i].checked){
                            post('createMessage',anchor + ':' + forward + ':id:0');
                            messageImg('right');
                            break;
                        }
                    }
               }else{
                        if(id.checked){
                            post('createMessage',anchor + ':' + forward + ':id:0');
                            messageImg('right');
                        }
               }
        }else{
                            post('createMessage',anchor + ':' + forward);
                            messageImg('right');
        }
               
}

function closeDetailEmail(){
    getObj('main_detail_id').innerHTML='';
    getObj('main_list_mail').style.display='block';
}

function getDetailShow(obj,type,id,idRec){
    for(i=0;i<obj.parentNode.parentNode.cells.length;i++){ 
        obj.parentNode.parentNode.cells[i].className='listmail-normal';
    }
    messageImg('main_detail_id');
    postAjax('messsagesList','main_detail_id',anchor + ':_VIEW:type:' + type + ':id:' + id + ':idRec:' + idRec)
    getObj('main_list_mail').style.display='none';
    getObj('main_detail_id').style.display='block';
}

function postTabMessages(obj,params){
        messageImg('right');
        post('messsagesList',anchor +  params);
        
}

function emptyFolder(){
if(confirm(unescape('<bean:message key="alert.mail.empty.questions.yes.no" bundle="<%=interfaces%>"/>'))){
    postAjax('createMessage','right',anchor + ':_DELETE_ALL');
    messageImg('right');
}
}           
</script>
<html:form action="messsagesList" method="POST" >
<div id="left">  
         <div class="ctn-left">
            <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic"><bean:message key="app.folder.messages" bundle="<%=interfaces%>"/></div></div>
              <div class="csstable">
              <table cellpadding="0" cellspacing="0" border="0">
                <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_47.gif" /></td>
                    <td bgcolor="#a0aec2" style="padding:0 7px"><span class="add">
                      <%if(request.getSession().getAttribute("03")!=null && request.getSession().getAttribute("03.02")!=null){%>
                        <a  href="#" onclick="post('messsagesListMain',anchor + ':_PREPARED_CREATE:id:0:app:0')"><img src="<%=contextPath%>/images/newImages/i_16.gif" align="left" /><b><bean:message key="mail.header.createMessage" bundle="<%=interfaces%>"/></b></a>
                      <%}%>
                    </span></td>
                    <td width="8"><img src="<%=contextPath%>/images/newImages/i_46.gif" width="8" /></td>
                </tr>
            </table></div>
<table cellpadding="0" cellspacing="0" width="100%">                
<tr><td colspan="2">
                <div class="status">
                <div class="inbox">
                        <p onmouseover="className='row1'" onmouseout="className='row2'"  onclick="postTabMessages(this,':_CREATE_SUCCESS:type:1');">

                                <logic:notEqual  name="createMessage" property="type" value="1" >
                                <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                                </logic:notEqual>

                                <logic:equal  name="createMessage" property="type" value="1" >
                                <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                                </logic:equal>
                                
                                <logic:equal  name="BAmount" property="amountRevUnRead" value="0">
                               <bean:message key="app.inbox.messages" bundle="<%=interfaces%>"/>(0)
                               </logic:equal>
                               
                               
                               <logic:notEqual  name="BAmount" property="amountRevUnRead" value="0">
                               <Strong>
                               <bean:message key="app.inbox.messages" bundle="<%=interfaces%>"/>
                               (<bean:write name="BAmount" property="amountRevUnRead" />)
                               </strong>
                               </logic:notEqual>
                               
                               
                        </p>
                  </div>
                  </div>
</td>
</tr>
<tr><td colspan="2">
<div class="status">
                <div class="inbox">
                        <p onmouseover="className='row1'" onmouseout="className='row2'"  onclick="postTabMessages(this,':_CREATE_SUCCESS:type:2');">
                                <logic:notEqual  name="createMessage" property="type" value="2" >
                                    <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                                </logic:notEqual>
                               <logic:equal  name="createMessage" property="type" value="2" >
                                    <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                               </logic:equal>
                               <bean:message key="app.send.item.messages" bundle="<%=interfaces%>"/>
                        </p>
                 </div>
                 </div>
</td>
</tr>
<tr><td colspan="2">
<div class="status">
                <div class="inbox" onmouseover="className='row1'" onmouseout="className='row2'">
                <table cellpadding="0" cellspacing="0" width="100%"><tr><td>
                        <span  onclick="postTabMessages(this,':_CREATE_SUCCESS:type:3');">
                                <logic:notEqual  name="createMessage" property="type" value="3" >
                                    <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                                </logic:notEqual>
                               <logic:equal  name="createMessage" property="type" value="3" >
                                    <img src="<%=contextPath%>/images/newImages/folderOpen.gif"/>
                               </logic:equal>
                        <bean:message key="app.delete.item.messages" bundle="<%=interfaces%>"/></span>
                        
                    </td>
                    <td width="10px"><img style="cursor:pointer;" title="<bean:message key="mail.delete.thung.giac.infor" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/trash_9x11.gif" onclick="emptyFolder();"/></td>
                </tr>
                </table>
</div>
</div>
</td>
</tr>
</table>
        <div class="csstable">
             <table cellpadding="0" cellspacing="0" border="0">
                <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_47.gif" /></td>
                    <td bgcolor="#a0aec2" style="padding:0 7px" nowrap><span class="add">
                        <Strong><bean:message key="app.folder.messages.employ.list" bundle="<%=interfaces%>"/></strong>
                    </span></td>
                    <td width="8"><img src="<%=contextPath%>/images/newImages/i_46.gif" width="8" /></td>
                    <td align="right" nowrap width="100%" style="padding-right:10px;"></td>
                </tr>
             </table>
        </div>
        
        <div class="status" id="tdMycontact">
                <jsp:include page="/messages/create/options/empDepartment.jsp"/>          
        </div>
        </div> 
  
  
</div>     
</html:form>