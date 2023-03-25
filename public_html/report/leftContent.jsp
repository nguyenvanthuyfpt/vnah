<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
function postTabReport(forward){
     post('change',anchor + ':'+forward);
     messageImg('right');
}

function showuserindep(obj){
        if(getObj('userIdS')!=null){
            AddUser(getObj('userIdS'),obj);
        }else if(obj.selectedIndex>-1){

            document.reportsSelect.toPertion.value=obj.value;
            document.reportsSelect.userFullName.value=obj.options[obj.selectedIndex].text;
            post('reportsSelect',anchor + ':_PREPARED_CREATE:id:0');
            }else{
                return false;   
        }
}

</script>

<html:form action="reportsSelect" method="POST" >
<div id="left">  
         <div class="ctn-left">
            <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic"><bean:message key="menu.top.report.caption" bundle="<%=interfaces%>"/></div></div>
              <div class="csstable">
              <table cellpadding="0" cellspacing="0" border="0">
                <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_47.gif" /></td>
                    <td bgcolor="#a0aec2" style="padding:0 7px"><span class="add">
                      <a href="javascript:post('reportsSelect',anchor + ':_PREPARED_CREATE')"><img src="<%=contextPath%>/images/newImages/i_16.gif" align="left" /><b><bean:message key="action.insert" bundle="<%=interfaces%>"/></b></a>
                    </span></td>
                    <td width="8"><img src="<%=contextPath%>/images/newImages/i_46.gif" width="8" /></td>
                </tr>
            </table>
            </div>
                
                <%  if(request.getSession().getAttribute("08.01") !=null){ %>
                <div class="status">
                <div class="inbox">
                        <p onmouseover="className='row1'" onmouseout="className='row2'"  onclick="postTabReport('_REPORT_ALL:type:0');">
                               <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                               <bean:message key="menu.top.report.caption" bundle="<%=interfaces%>"/>
                        </p>
                </div>
                </div>
                <%}%>
                
                <%  if(request.getSession().getAttribute("08.02") !=null){ %>
                <div class="status">
                <div class="inbox">
                        <p onmouseover="className='row1'" onmouseout="className='row2'"  onclick="postTabReport('_REPORT_ALL:type:1');">
                        <img src="<%=contextPath%>/images/newImages/folder.gif"/>
                           <bean:message key="menu.top.recvReport.caption" bundle="<%=interfaces%>"/>
                        </p>
                 </div>
                 </div>
                 <%}%>
                 
        <div class="csstable">
             <table cellpadding="0" cellspacing="0" border="0">
                <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_47.gif" /></td>
                    <td bgcolor="#a0aec2" style="padding:0 7px" nowrap><span class="add">
                        <Strong>B&#225;o c&#225;o l&#227;nh &#273;&#7841;o</strong>
                    </span></td>
                    <td width="8"><img src="<%=contextPath%>/images/newImages/i_46.gif" width="8" /></td>
                    <td align="right" nowrap width="100%" style="padding-right:10px;"></td>
                </tr>
             </table>
        </div>
        
        <input type="hidden" name="toPertion" id="toPertion" value="" />
        <input type="hidden" name="userFullName" id="userFullName" value="" />
        <div class="status" id="tdMycontact">
               <jsp:include page="/report/userList.jsp" /> 
        </div>
        </div> 
</div>     
</html:form>