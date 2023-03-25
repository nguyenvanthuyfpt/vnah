<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div id="tool" align="center">
<div class="tools" >
<span style="cursor: pointer;" onclick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key='list.module.of.user' bundle='<%=interfaces%>'/>','','','');postAjax('main','at_share',anchor + ':_SHOW_MODULE');">
    <img src="<%=contextPath%>/images/newImages/tool_1.gif" width="30px" height="30px" />
    </span>
    <br />
    <DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1;top:-100; padding-left:30px;"></DIV>
    <span >
<logic:present name="BMenus" >
<logic:iterate name="BMenus" id="bean" type="com.form.admin.menu.FMenu">    
    <% int block=0;%>
    <logic:equal name="bean" property="level" value="0">
            <logic:iterate name="bean" property="beansMenu" id="subBean" type="com.form.admin.menu.FMenu">
                <%if(block==0){%>
                <div align="center">
                    <img onclick="<%=subBean.getUrl()%>"  style="cursor:pointer;border:0px" onmouseover="Tip('<%=bean.getTitle()%>',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()" src="<%=contextPath%>/images/menuLeft/<%=subBean.getImages()%>" />
                </div>
                <%
                block=1;%>
                <%}%>
            </logic:iterate>
    </logic:equal>
</logic:iterate>
</logic:present>
 <div align="center">
 <img src="images/newImages/tool_13.gif" width="32px" border="0" />
 </div>
    </span>
</div></div>
                        

