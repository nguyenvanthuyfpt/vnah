<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
        <td valign="top">
                <logic:present name="BGroups">
                      <html:select styleClass="inputbox"  name="docAssign" property="groupId" onchange="javascript:postAjax('docAssignRecv','mainUsers',anchor+':_SHOW_GROUP')">                         
                            <html:options collection="BGroups" property="id" labelProperty="name"/>                                          
                    </html:select> 
                </logic:present>
        </td>
    </tr>
 
    <tr>
        <td valign="top" id="mainUsers">
            <jsp:include page="/doc/assign/recv/userList.jsp"/>
        </td>
    </tr>
    
 </table>
 
