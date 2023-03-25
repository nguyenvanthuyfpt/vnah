<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<html:form action="formMyContact" onsubmit="post('formMyContact',anchor + ':_LIST_MYCONTACT');">
 <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
    <td valign="top" align="left" nowrap width="200px" class="BGSearch">   
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                <tbody><tr><td align="left" valign="middle">
                <input  class="InputSearch" id="fullName" name="fullName" type="text" ></td>
                <td align="left" valign="middle" width="60">
                <input class="Button_Search" value="<bean:message key="categories.cmd.search" bundle="<%=interfaces%>"/>" type="submit" >
                </td>
                </tr>
                </tbody></table>
        </td>
    <td  align="left">
                    <html:select styleClass="inputbox"  name="formMyContact" property="pgroupId" onchange="post('formMyContact',anchor + ':_LIST_MYCONTACT');">                                     
                    <logic:present name="BPgroups">
                    <html:options collection="BPgroups" property="id" labelProperty="name"/>
                    </logic:present>
                    </html:select> 
    </td>
    <td align="right">
    
                <a class="modal-button" href="pgroup<%=extention%>?<%=anchor%>=_PREPARED_CREATE&id=0" rel="{handler: 'iframe', size: {x: 260, y:150},bookmark:'if(SqueezeBox.presets.target==0){post(\'formMyContact\',anchor + \':_LIST_MYCONTACT\');}'}"> 
                <html:button property="_PREPARED_CREATE" styleClass="button">
                <bean:message key="pgroup.pgroup.buttom.caption" bundle="<%=interfaces%>"/>
                </html:button>
                </a>
                 <a class="modal-button" href="formMyContact<%=extention%>?<%=anchor%>=_PREPARED_CREATE&id=0" rel="{handler: 'iframe', size: {x: 300, y: 350},bookmark:'if(SqueezeBox.presets.target==0){post(\'formMyContact\',anchor + \':_LIST_MYCONTACT\');}'}"> 
                    <html:button property="_PREPARED_CREATE" styleClass="button">
                         <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                     </html:button>
                 </a>
    
    </td>
        
    </tr>
</table>
</html:form>