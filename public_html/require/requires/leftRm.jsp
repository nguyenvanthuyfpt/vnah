<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div id="left">
    <html:form action="frmRequireEmp" method="post" >
     <div class="ctn-left">
        <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic"><bean:message key="require.manager.cation.endUser" bundle="<%=interfaces%>"/></div></div>               
        
        <div class="csstable">
        <table cellpadding="0" cellspacing="0" border="0">
                <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_47.gif" /></td>
                    <td bgcolor="#a0aec2" style="padding:0 7px"><span class="add">                      
                      <a  href="#" onclick="post('main',anchor + ':_MANAGER_REQUIRE')"><img src="<%=contextPath%>/images/newImages/i_16.gif" align="left" /><b><bean:message key="privilege.insert" bundle="<%=interfaces%>"/></b></a>                      
                    </span></td>
                    <td width="8"><img src="<%=contextPath%>/images/newImages/i_46.gif" width="8" /></td>
                </tr>
            </table>
        </div>             

        <div class="status">                                        
            <jsp:include page="/require/requires/excuteStatus.jsp" />                              
        </div>
         <div align="center"><hr /></div>  
    </div>
    </html:form>
</div>      