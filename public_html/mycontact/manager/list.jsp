 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BMycontacts" >
<table width="100%" cellpadding="0"  cellspacing="0" style="border-collapse: collapse"  >
<tr>
    <td width="33%" height="0"></td>
    <td width="33%"></td>
    <td width="33%"></td>
</tr>
<% int i=0,j=0;int stt=1;%>
<logic:iterate name="BMycontacts" id="bean" type="com.form.mycontact.FMycontact">
<%  j++;
if (j==1){%>
<tr>
<%}%>
    <td class="taskcontent" valign="top" style="padding-top:4px" >
        <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
        <td class="index" valign="top" align="center"><%=stt++%>.</td>
        <td valign="top">   
       
        <ul class="ulClassDoc" >            
            <logic:notEqual name="bean" property="fullName" value="" >                
                <li>                
                <a class="modal-button" href="formMyContact<%=extention%>?<%=anchor%>=_PREPARED_EDIT&id=<bean:write name="bean" property="id"/>" rel="{handler: 'iframe', size: {x: 300, y: 340},bookmark:'post(\'formMyContact\',anchor + \':_LIST_MYCONTACT\')'}"> 
                    <bean:write name="bean" property="fullName"/>
                </a>
                </li>
            </logic:notEqual>
            <logic:notEqual name="bean" property="address" value="" >
                <li><bean:write name="bean" property="address"/></li>
            </logic:notEqual>
              <logic:notEqual name="bean" property="phone" value="" >
                <li><bean:write name="bean" property="phone"/></li>
             </logic:notEqual>
        </ul>       
       </td>
        <td class="deleteInMyContact" valign="top"><img  style="cursor: pointer;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('formMyContact',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>')"></td>
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
