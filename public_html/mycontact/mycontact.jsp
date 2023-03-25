<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="pgroup" method="post" />
<html:form action="formMyContact" method="post" >
<table width="100%" style="border-collapse: collapse"  cellpadding="0" cellspacing="0">
<tr><td  class="title-01" nowrap>   
   <a href="#" class="li-title-03">  
            <bean:message key="mycontact.list.caption" bundle="<%=interfaces%>"/>                    
   </a>
</td>
</tr>
</table>
 
<div class="divHeader" align="right"> 
<table width="100%">
    <tr>
         <td nowrap style="text-align:left"  class="BGSearch">                      
                   <html:select styleClass="inputbox"  name="formMyContact" property="pgroupId" onchange="post('formMyContact',anchor + ':_LIST_MYCONTACT')">                                     
                        <logic:present name="BPgroups">
                          <html:options collection="BPgroups" property="id" labelProperty="name"/>
                        </logic:present>
                    </html:select> 
         </td>
        <td align="right">
                
                <a class="modal-button" href="pgroup<%=extention%>?<%=anchor%>=_VIEW&id=0" rel="{handler: 'iframe', size: {x: 300, y: 150},bookmark:'post(\'formMyContact\',anchor + \':_LIST_MYCONTACT\')'}"> 
                    <html:button property="_PREPARED_CREATE" styleClass="button">
                         <bean:message key="pgroup.pgroup.buttom.caption" bundle="<%=interfaces%>"/>
                     </html:button>
                 </a>
                 <a class="modal-button" href="formMyContact<%=extention%>?<%=anchor%>=_PREPARED_CREATE&id=0" rel="{handler: 'iframe', size: {x: 320, y: 300},bookmark:'post(\'formMyContact\',anchor + \':_LIST_MYCONTACT\')'}"> 
                    <html:button property="_PREPARED_CREATE" styleClass="button">
                         <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                     </html:button>
                 </a>
        </td>
    </tr>
</table>


<logic:present name="BMycontacts" >
<table width="100%" cellpadding="0"  cellspacing="0" style="border-collapse: collapse"  >
<% int i=0,j=0;int stt=1;%>
<logic:iterate name="BMycontacts" id="bean" type="com.form.mycontact.FMycontact">
<%  j++;
if (j==1){%>
<tr>
<%}%>
    <td style="padding-top:10px" class="taskcontent">
        <table>
        <tr>
        <td class="index"><%=stt++%>.</td>
        <td>
        <ul class="ulClassDoc">
            <logic:notEqual name="bean" property="fullName" value="" >
                <li><bean:write name="bean" property="fullName"/></li>
            </logic:notEqual>
            <logic:notEqual name="bean" property="address" value="" >
                <li><bean:write name="bean" property="address"/></li>
            </logic:notEqual>
              <logic:notEqual name="bean" property="phone" value="" >
                <li><bean:write name="bean" property="phone"/></li>
            </logic:notEqual>
            <li>
                    <a class="modal-button" href="formMyContact<%=extention%>?<%=anchor%>=_PREPARED_EDIT&id=<bean:write name="bean" property="id"/>" rel="{handler: 'iframe', size: {x: 320, y: 300},bookmark:'post(\'formMyContact\',anchor + \':_LIST_MYCONTACT\')'}"> 
                    <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>"/>
                    </a>
                    <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('formMyContact',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>')">
            </li>
        </ul>    
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


 </div>
 </html:form>
