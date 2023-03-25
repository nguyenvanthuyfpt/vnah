 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BDirectories" >
<table width="100%" cellpadding="0"  cellspacing="0" style="border-collapse: collapse"  >
<tr>
    <td width="33%" height="0"></td>
    <td width="33%"></td>
    <td width="33%"></td>
</tr>
<% int i=0,j=0;int stt=1;%>
<logic:iterate name="BDirectories" id="bean" type="com.form.admin.users.FUser">     
<%  j++;
if (j==1){%>
<tr>
<%}%>
    <td>
        <table width="100%" cellpadding="5" cellspacing="5" >
        <tr>
        <td class="index" valign="top" align="left"><%=stt++%>.</td>
        <td valign="top" align="left">
        <div style="padding-left:10px">
        
            <logic:notEqual name="bean" property="fullName" value="" >
                <div><span style="cursor:pointer" onclick="javascript:checkedInnerHtml();addthis_open(this,'<bean:write name="bean" property="fullName" />','','','');postAjax('directory','at_share',anchor + ':_SHOW:userId:<bean:write name="bean" property="id" />');"    > <bean:write name="bean" property="fullName"/></span></div>
            </logic:notEqual>
            <logic:notEqual name="bean" property="address" value="" >
                <div><bean:write name="bean" property="address"/></div>
            </logic:notEqual>
              <logic:notEqual name="bean" property="phone" value="" >
                <div><bean:write name="bean" property="phone"/></div>
            </logic:notEqual>
        
        </div>
        </td>
     
        </tr>
        </table>
    </td>

 
     
 <%if (j==3){%>
 
 <%j=0;i++;%>
</tr>
<%}%>
</logic:iterate>

 <%if(3-j!=0){%>
        <td colspan="<%=3-j%>"></td>
        </tr>
        <%}%>
</table>
</logic:present>
