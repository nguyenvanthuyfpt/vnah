<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<% 
java.util.Iterator users= com.users.OnlineUsers.getAllUsers().values().iterator();
request.setAttribute("BListUserOnline",users);
long userId=0;int i=0;
%>
<div class="listUserOnLine" style="width:100%" >
<logic:present name="BListUserOnline" >
 <table width="100%" border="0" cellpadding="2" cellspacing="2">
   <logic:iterate name="BListUserOnline" id="bean" indexId="j" type="com.users.OnlineUser">
    <%if(bean.getId()!=userId){%>
        <logic:notEqual name="bean" property="fullName" value="" >
            <%userId=bean.getId();%>
            <tr><td>
            <span style="cursor:pointer;float:left">
            <span class="index"><%=++i%>.</span>
            <span  onclick="javascript:checkedInnerHtml();addthis_open(this,'<bean:write name="bean" property="fullName" />','','','');postAjax('directory','at_share',anchor + ':_SHOW:userId:<bean:write name="bean" property="id" />');"    > <bean:write name="bean" property="fullName"/></span>
            </span>
            <span style="float:right;font-weight: normal;font-size:10px;" align="right"  >
            <bean:write name="bean" property="timeStart" />
            </span>
            </td></tr>
        </logic:notEqual>
    <%}%>
   </logic:iterate>
</table>
</logic:present>
</div>
