<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
function inputInToCategery(){
//    if(getObj('cabinTypeInput_id').value>0){
        postAjax('cabinInput','right',anchor + ':_INPUT_OK');
//    }else{
//        alert(<bean:message key="alert.not.enough.input.cabin" bundle="<%=interfaces%>"/>);
//    }
}
</script>
<html:form action="cabinTypeSelect" method="post" />
<html:form action="cabin" method="post" >
<html:hidden name="cabin" property="type" />
<html:hidden name="cabin" property="cabinType_id" />
<bean:define name="BCabin" id="beans" type="com.form.FBeans"/>
<table width="100%" cellpadding="0" class="TITLECLASS" cellspacing="0"   >
    <tr>
        <td class="LEFT" width="98%" align="left">
            <Strong>
            <logic:equal name="cabin" property="type" value="3">
                <bean:message key="header.cabin.label.deps" bundle="<%=interfaces%>"/><bean:write name="cabin" property="rootPath"/>
            </logic:equal>        
            <logic:equal name="cabin" property="type" value="2">
                <bean:message key="header.cabin.label.shere" bundle="<%=interfaces%>"/><bean:write name="cabin" property="rootPath"/>
            </logic:equal>
            <logic:equal name="cabin" property="type" value="1">
                <bean:message key="header.cabin.label.private" bundle="<%=interfaces%>"/><bean:write name="cabin" property="rootPath"/>
            </logic:equal>
            <logic:equal name="cabin" property="type" value="0">
                <bean:message key="header.cabin.label.public" bundle="<%=interfaces%>"/><bean:write name="cabin" property="rootPath"/>
            </logic:equal>
            </Strong>
        <td  class="RIGHT">
        </td>
    </tr>
</table>


<div class="content-calendar">
<div class="toolCmd" id="toolSearch"  style="line-height:18px;height:25px;padding-right:8px;margin-right:8px;text-align:left;padding-top:3px"> 
<table width="100%" cellpadding="0" cellspacing="0" >               
<tr>
<td width="150px" nowrap>
<table class="tableClassSearch" cellpadding="0" border="0" cellspacing="0" >
        <tr>
            <td><input class="inputClassSearch" type="text" onkeydown="if(event.keyCode==13){postAjax('cabin','right',anchor + ':_SEARCH');messageImg('right');return false;}" onfocus="javascript:if(this.value == '') this.value='';" onblur="javascript:if(this.value=='') this.value='';" name="contentSearch" id="contentSearch" value=""/></td>
            <td class="imgClassSearch" height="18px" width="20px" onclick="postAjax('cabin','right',anchor + ':_SEARCH');messageImg('right')" >&nbsp;</td>
        </tr>
</table>    

</td>

                 <% String type_CABIN_DEPARTMENT = com.inf.cabin.IKeyCabin.CABIN_DEPARTMENT + "";  %>
                <logic:equal name="cabin" property="type" value="<%=type_CABIN_DEPARTMENT%>" >
                <%if(me.isRole(com.inf.IRoles.rCABIN_MUTI)){%>
                    <td align="center" nowrap>
                        <html:select name="cabin" property="departmentID" styleId="departmentID" onchange="postAjax('cabin','right',anchor + ':_SEARCH');messageImg('right')" styleClass="fieldSelect" style="width:200px;"  >
                        <logic:present name="BDepartments">
                        <html:options collection="BDepartments" property="id" labelProperty="name"/>
                        </logic:present>
                        </html:select>
                    </td>
                <%}%>
                </logic:equal>

<td align="right" nowrap>
                 <% String type_CABIN_PRIVATE = com.inf.cabin.IKeyCabin.CABIN_PRIVATE + "";  %>
                 <% String type_CABIN_SHARE_ = com.inf.cabin.IKeyCabin.CABIN_SHARE + "";  %>

                <logic:equal name="cabin" property="type" value="<%=type_CABIN_PRIVATE%>">
                                    <a href="javascript:post('cabin',anchor + ':_PREPARED_CREATE')" >
                                    <img style="border:0px" src="<%=contextPath%>/images/document_letter_upload.png" />
                                    <bean:message key="cmd.cabin.add.private" bundle="<%=interfaces%>"/>
                                    </a>
                </logic:equal>
                <%if(me.isRole(com.inf.IRoles.rCABIN_PUBLIC)){%>
                <% String type_rCABIN_PUBLIC = com.inf.cabin.IKeyCabin.CABIN_PUBLIC + "";  %>
                <logic:equal name="cabin" property="type" value="<%=type_rCABIN_PUBLIC%>">
                                    <a href="javascript:post('cabin',anchor + ':_PREPARED_CREATE')" >
                                    <img style="border:0px" src="<%=contextPath%>/images/document_letter_upload.png" />
                                    <bean:message key="cmd.cabin.add.public" bundle="<%=interfaces%>"/>
                                    </a>
                </logic:equal>
                <%}%>

                
                <%if(me.isRole(com.inf.IRoles.rCABIN_ONE)){%>
                <% String type_rCABIN_ONE = com.inf.cabin.IKeyCabin.CABIN_DEPARTMENT + ""; 
                String demartment=(int)me.getDepartmentID()+"";%>
                <logic:equal name="cabin" property="departmentID" value="<%=demartment%>">
                    <logic:equal name="cabin" property="type" value="<%=type_rCABIN_ONE%>">
                                    <a href="javascript:post('cabin',anchor + ':_PREPARED_CREATE')" >
                                    <img style="border:0px" src="<%=contextPath%>/images/document_letter_upload.png" />
                                    <bean:message key="cmd.cabin.add.dep" bundle="<%=interfaces%>"/>
                                    </a>
                    </logic:equal>
                </logic:equal>
                <%}%>
                <logic:equal name="cabin" property="type" value="<%=type_CABIN_SHARE_%>" >
                <bean:message key="cabin.creator.share" bundle="<%=interfaces%>"/> :
                        <logic:present name="BUserShares">
                                <logic:notEmpty name="BUserShares" >
                                        <html:select name="cabin" property="userId" styleId="userId" onchange="postAjax('cabin','right',anchor + ':_SEARCH');messageImg('right')" styleClass="fieldSelect" style="width:150px;"  >
                                        <html:options collection="BUserShares" property="userId" labelProperty="fullName"/>
                                        </html:select>
                                </logic:notEmpty>
                        </logic:present>
                        
                </logic:equal>
</td>
</tr>
</table>
</div>


<div class="padding-content" style="margin-right:5px">
        <div class="tabmenu" id="container-1" >
            <div id="fragment-1">
               <div class="content-calendar" id="MainMessage">      
               </div>
               <div class="content-calendar">      
                    <jsp:include page="/cabin/list.jsp" />
               </div>
            </div>
        </div>
</div>
</div>
</html:form>
