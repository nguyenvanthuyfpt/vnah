<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/tinymce.js"></script>
<html:form action="broadcast" method="post" >
<html:hidden name="broadcast" property="broadcastId" />
<html:hidden name="broadcast" property="orders" />
<body onLoad="if(parent.SqueezeBox.presets.target==0){parent.SqueezeBox.close()};">
<table  style="margin-left:20px" cellpadding="2" cellspacing="2">
    <tr><td colspan="2"><br></td></tr>
    <tr>
        <td><bean:message key="broadcast.title.caption" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td>
        <html:text name="broadcast" property="title" style="width:400px" size="20"/>
        </td>
    </tr>
   
    <tr>   
        <td valign="top" ><bean:message key="broadcast.comtent.introtext.caption" bundle="<%=interfaces%>"/></td>
        <td>
            <textarea id="elm1" name="elm1" style="width:400px;height:80px">            
               <bean:write name="broadcast"  property="content" />                   
            </textarea>
        </td>
    </tr>
    <tr>   
        <td valign="top" ><bean:message key="broadcast.comtent.fulltext.caption" bundle="<%=interfaces%>"/></td>
        <td>
            <textarea id="fulltext" name="fulltext" style="width:400px;height:200px">            
               <bean:write name="broadcast"  property="fulltext" />                   
            </textarea>
        </td>
    </tr>
    <tr>
        <td></td>       
        <td><html:checkbox name="broadcast" property="special" value="1"/><bean:message key="broadcast.special.caption" bundle="<%=interfaces%>"/></td>
    </tr>
    <%if(me.isRole(com.inf.IRoles.rBROADCAST)){%>
    <tr>
        <td colspan="2" align="right">       
            <logic:equal name="broadcast" property="broadcastId" value="0" >
                <html:button property="_PREPARED_CREATE" styleClass="button" onclick="post('broadcast',anchor + ':_CREATE');parent.SqueezeBox.presets.target=0;">
                        <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                </html:button>
            </logic:equal> 
            <logic:notEqual name="broadcast" property="broadcastId" value="0" >
             <html:button property="_PREPARED_CREATE" styleClass="button" onclick="post('broadcast',anchor + ':_EDIT');parent.SqueezeBox.presets.target=0;">
                    <bean:message key="action.update" bundle="<%=interfaces%>"/>
            </html:button>
           </logic:notEqual> 
        </td>
    </tr>
    <%}%>
</table>
</body>
</html:form>