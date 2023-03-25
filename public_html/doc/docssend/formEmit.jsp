<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
function checkSubmit(form){    
    if(form.localCode.value=='' || (form.docCode && (form.docCode.value=='' || form.docDate.value==''))){
        alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
    }else{
          post('docssend',anchor + ':_EDIT_EMIT');
          parent.SqueezeBox.presets.target=0;        
   }
}
</script>
<body onLoad="if(parent.SqueezeBox.presets.target==0){parent.SqueezeBox.close()};">
<html:form action="from" method="post" />
<html:form action="docsrecv" method="post" />
<html:form action="docsrecvMain" method="post" />
<html:form action="docssend" method="post" >
<table cellpadding="0" cellspacing="0" border="0" width="99%" align="left" >
        <tr><td width="8" ><img src="<%=contextPath%>/images/newImages/i_19.gif" width="8" height="43" /></td>
            <td class="sharebackground" width="100%">
            <Strong><bean:message key="docs.docssend.form.caption" bundle="<%=interfaces%>"/></strong>
            </td>
            <td width="10"><img src="<%=contextPath%>/images/newImages/i_20.gif" width="10" height="43" /></td>
        </tr>
        <tr>
            <td colspan="4" align="center">        

<table class="tableForm" cellspacing="0" cellpadding="0" border="0" align="center" style="border-collapse: collapse;">
<tr>
    <td nowrap ><bean:message key="form.docs.formId" bundle="<%=interfaces%>"/></td>
    <td nowrap>
        <html:select name="docssend" property="formId" styleClass="fieldSelect" >
        <logic:present name="BForms">
        <html:options collection="BForms" property="id" labelProperty="name"/>
        </logic:present>
        </html:select>
    </td>
    <td nowrap> <bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/></td>
    <td id="idDossiers">
            <html:select name="docssend" property="dossierId" styleClass="fieldSelect" >
            <html:option value="0"> <bean:message key="form.dossiers.select.cation" bundle="<%=interfaces%>"/> </html:option>        
            <logic:present name="BDossiers">
            <html:options collection="BDossiers" property="id" labelProperty="name"/>
            </logic:present>
            </html:select>
    </td>
</tr>
<tr>
        <td nowrap height="20px"><bean:message key="form.docs.send.localCode" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td nowrap><html:text name="docssend" property="localCode" style="width:145px" maxlength="50"   /></td>
        <td nowrap><bean:message key="form.docs.send.localDate" bundle="<%=interfaces%>"/></td>
        <td nowrap><input type="text" name="localDate" id="localDate" style="width:80px" value="<bean:write name="docssend" property="localDate"/>"/></td>
</tr>
<tr>
        
        <td nowrap>
                <logic:equal name="<%=com.lib.AppConfigs.CHECK_CREATOR_DOCSSEND%>" value="1" >                
                <logic:notEqual parameter="<%=anchor%>" value="_PREPARED_CREATE">
                <bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/>
                <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                </logic:notEqual>
                </logic:equal>  
        </td>
        <td nowrap>
                <logic:equal name="<%=com.lib.AppConfigs.CHECK_CREATOR_DOCSSEND%>" value="1" >
                <logic:notEqual parameter="<%=anchor%>" value="_PREPARED_CREATE">
                <html:text name="docssend" property="docCode" style="width:145px" maxlength="100"/>   
                </logic:notEqual>
                </logic:equal>    
        </td>
        <td nowrap>
            <logic:equal name="<%=com.lib.AppConfigs.CHECK_CREATOR_DOCSSEND%>" value="1" >
            <logic:notEqual parameter="<%=anchor%>" value="_PREPARED_CREATE">
            <bean:message key="form.docs.docDate" bundle="<%=interfaces%>"/>
            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
            </logic:notEqual>
            </logic:equal>
        </td>
        <td nowrap>
            <logic:equal name="<%=com.lib.AppConfigs.CHECK_CREATOR_DOCSSEND%>" value="1" >
            <logic:notEqual parameter="<%=anchor%>" value="_PREPARED_CREATE">
            <input type="text" name="docDate" style="width:80px" id="docDate" value="<bean:write name="docssend" property="docDate"/>"/>
            </logic:notEqual>
            </logic:equal>
        </td>
</tr>
<tr>
    <td nowrap><bean:message key="form.docs.signer" bundle="<%=interfaces%>"/></td>
    <td nowrap><html:text name="docssend" property="signer" styleId="signer"  maxlength="100" />
    <input type="button" value="..." onclick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="doc.sign.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('docssend','at_share',anchor + ':_VIEW_LIST_TILE')" />
    </td>
    <td nowrap><bean:message key="form.docs.docsTypeId" bundle="<%=interfaces%>"/></td>
    <td nowrap>
    <html:select name="docssend" property="docsTypeId" styleClass="fieldSelect" >
        <logic:present name="BDocTypes">
        <html:options collection="BDocTypes" property="idDocType" labelProperty="nameDocType"/>
        </logic:present>
        </html:select>     
    </td>
</tr>
<tr>
    <td nowrap><bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
    <td colspan="3" ><html:textarea name="docssend" property="abstracts" style="width:450px;height:60px;"   /></td>
</tr>
<tr>
    <td align="left"     style="vertical-align:top;" >
        <bean:message key="doc.addNew.docReference.cmd.caption" bundle="<%=interfaces%>"/>
    </td>
    <td align="left"     style="vertical-align:top;" >
    <jsp:include page="/doc/docssend/reference.jsp" />
        
    </td>
    <td    style="vertical-align:top;" align="left" >
    <bean:message key="form.docs.fromId.to" bundle="<%=interfaces%>"/>
    </td>
    <td    style="vertical-align:top;" align="left" >
        <jsp:include page="/doc/docssend/fromForm.jsp" />
    </td>
  </tr>
  <tr>
  <td align="left" colspan="2"   style="vertical-align:top;" >
            <logic:notEmpty name="BDocsReference">
            <logic:iterate name="BDocsReference" id="bean" type="com.form.doc.docsrecv.FDocsrecv">                       
            <div>  <input type="checkBox" checked name="docsId" id="docsId" value="<%=bean.getId()%>">
                                 <Strong><bean:write name="bean" property="docCode" /></strong>:<bean:write name="bean" property="abstracts" />
            </div>
            </logic:iterate>
            </logic:notEmpty>
            <div id='doc_1'></div>
  </td>
    <td colspan="2"   style="vertical-align:top;" align="left" >
                <logic:notEmpty name="BFroms">
                <logic:iterate name="BFroms" id="bean" type="com.form.doc.from.FFrom">                       
                <div>  <Strong><input type="checkBox" checked name="fromsId" id="fromsId" value="<%=bean.getId()%>"><bean:write name="bean" property="code" /></strong>:<bean:write name="bean" property="vnName" />
                </div>
                </logic:iterate>
                </logic:notEmpty>
                <div id='from_1'></div>
    </td>
   </tr>
   
<logic:notEmpty name="docssend" property="allFiles">
<tr>
    <td valign="top"><bean:message key="doc.file.caption" bundle="<%=interfaces%>"/></td>
    <td colspan="3">
    
<div style="overflow:auto;height:85px; width:450px;" >
<ol class="calassFile">
            <logic:iterate name="docssend" property="allFiles" id="beanFiles" type="com.form.doc.docssend.FFilesSend">   
            <li>
                 <a href="javascript:post('docssend',anchor + ':_DOWNLOAD:fileId:<bean:write name="beanFiles" property="idFiles"/>');remove('docssend',anchor);remove('docssend','fileId');" >
                     <logic:equal name="beanFiles" property="description" value="">
                    <bean:write name="beanFiles" property="fileName" />
                    </logic:equal>
                    <logic:notEqual name="beanFiles" property="description" value="">
                    <bean:write name="beanFiles" property="description" />
                    </logic:notEqual>
                    (<bean:write name="beanFiles" property="numberReadedFile" />)
                     (<bean:write name="beanFiles" property="createTimeName" />)   - <span class="version"> (V<bean:write name="beanFiles" property="version" />) </span>     
                 </a>  
             </li>
            </logic:iterate>
     </ol>
</div>
    </td>

</tr>
</logic:notEmpty>
<tr>
    <td colspan="4" style="text-align: center;"  align="center" height="30px;">
            <html:button property="_EDIT" styleClass="button"  onclick="javascript:checkSubmit(this.form)" >
            <bean:message key="cmd.docssend.docssend" bundle="<%=interfaces%>"/>
            </html:button>
    </td>
</tr>
</table>
</td>
</tr>
</table>
<html:hidden name="docssend" property="id" />
<html:hidden name="docssend" property="secureId" />
<html:hidden name="docssend" property="storeAgeId" />
<html:hidden name="docssend" property="expressId" />
<html:hidden name="docssend" property="viaId" />
<html:hidden name="docssend" property="statusId" />
<html:hidden name="docssend" property="address" />
<html:hidden name="docssend" property="deadLine" />
<html:hidden name="docssend" property="classifyId" />
<html:hidden name="docssend" property="branchId" />
<html:hidden name="docssend" property="numberVersion" />
<html:hidden name="docssend" property="numberPage" />
<html:hidden name="docssend" property="referentId" />
       
</html:form>    
</body>
