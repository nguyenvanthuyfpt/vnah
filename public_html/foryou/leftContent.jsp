<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div id="left">
    <div class="ctn-left">
        <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic"><bean:message key="menu.top.doc.caption" bundle="<%=interfaces%>"/></div></div>
        <div class="csstable"><table cellpadding="0" cellspacing="0" border="0">
            <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_47.gif" /></td>
                <td bgcolor="#a0aec2" style="padding:0 7px"><span class="add">
                  <a class="modal-button" href="forYou<%=extention%>?<%=anchor%>=_FORYOU_CREATE&id=0" rel="{handler: 'iframe', size: {x: 460, y: 310}}"><img src="<%=contextPath%>/images/newImages/i_16.gif" align="left" /><b><bean:message key="privilege.insert" bundle="<%=interfaces%>"/></b></a>
                </span></td>
                <td width="8"><img src="<%=contextPath%>/images/newImages/i_46.gif" width="8" /></td>
            </tr>
        </table></div>
        <div class="status">
        <p class="left-p-title"><bean:message key="cmd.search" bundle="<%=interfaces%>"/></p>
        <p><input class="InputSearch" id="contentSearch" name="contentSearch"  onkeydown="if(event.keyCode==13){post('from',anchor + ':_SEARCH');messageImg('iddonvi');return false;}"  onFocus="this.value=''" type="text" >
        </div>
        <div align="center"><hr /></div>
        </div>
</div>                    
    