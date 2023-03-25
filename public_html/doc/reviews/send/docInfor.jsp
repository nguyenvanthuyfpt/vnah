<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="docssend" >
<bean:define name="docssend" id="bean" type="com.form.doc.docssend.FDocssend" />
<div class="ct-celendar">  
<table style="border-collapse: collapse;line-height:20px"  cellpadding="0" cellspacing="3" align="left" width="100%" border="0">
     <tr>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/>: </strong></td>
        <td valign="top" colspan="3"><bean:write name="docssend" property="abstracts"/></td>
    </tr>
     
     <logic:notEmpty name="docssend" property="allFiles">
    <tr>
        <td nowrap valign="top" >
            <Strong><bean:message key="docs.download" bundle="<%=interfaces%>"/> :</strong>
        </td>
        <td valign="top" colspan="3" align="left">
            <ol style="line-height:20px;">
            <% String fileName = "";int id= 0,dem = 0; %>  
            
            <logic:iterate name="docssend" property="allFiles" id="beanFiles" type="com.form.doc.docssend.FFilesSend">   
             <% if (!fileName.equals(beanFiles.getFileName())){  fileName = beanFiles.getFileName(); id = beanFiles.getIdFiles();dem =0; %>
            <li>
            
            <A href="javascript:post('docssend',anchor + ':_DOWNLOAD_DRAFT:fileId:<bean:write name="beanFiles" property="idFiles"/>:id:<bean:write name="docssend" property="id"/>');remove('docssend',anchor);remove('docssend','fileId');remove('docssend','id');" >
                 <logic:equal name="beanFiles" property="description" value="">
                    <bean:write name="beanFiles" property="fileName" />
                    </logic:equal>
                    <logic:notEqual name="beanFiles" property="description" value="">
                    <bean:write name="beanFiles" property="description" />
                    </logic:notEqual>
                                    (<bean:write name="beanFiles" property="numberReadedFile" />)-
                - <span class="version"> (V<bean:write name="beanFiles" property="version" />) </span>
            </a>
             <%}else{ dem ++;
                    if (dem==1){
              %>
                <logic:equal name="docAssign" property="type" value="2">
                <logic:equal name="trackingInfor" value="0">
                <img  style="border:0px;" title='<bean:message key="system.action.delete" bundle="<%=interfaces%>"/>' src="<%=contextPath%>/images/delete.png" onclick="javascript:post('docssend',anchor + ':_DELETE_FILE_INFOR:fileId:<bean:write name="beanFiles" property="idFiles"/>')">
                </logic:equal>
                </logic:equal>
                <input  style="border:0px;cursor: pointer;"  type="button"   value=" + "  onclick="checkedInnerHtml();addthis_open(this,'<bean:message key="doc.version.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('docssend','at_share',anchor + ':_FILEDINHKEM_IN_INFOR:id:<bean:write name="docssend" property="id"/>:fileId:<%=id%>');" />
                    </li>
               <%}}%>
            </logic:iterate>
            </ol>
        </td>
    </tr>
    </logic:notEmpty>
    <tr>
        <td nowrap valign="top"  ><Strong><bean:message key="form.docs.creator" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"  ><bean:write name="docssend" property="creator"/></td>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docssend" property="docCode"/></td>        
    </tr>
    <tr>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.docsTypeId" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docssend" property="docTypeName"/></td>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.localCode" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docssend" property="localCode"/></td>
    </tr>
    
     <tr>
        <td nowrap valign="top" ><Strong><bean:message key="form.docs.signer" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"  ><bean:write name="docssend" property="signer"/></td>
        
        
        <td nowrap valign="top"><Strong><bean:message key="form.docs.timeCreate" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docssend" property="timeCreate"/></td>
    </tr>
     <tr>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.viaId" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docssend" property="viaName"/></td>
        
        
        <td nowrap valign="top"><Strong><bean:message key="form.docs.localDate" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docssend" property="localDate"/></td>
    </tr>
     
     <tr>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.formId" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docssend" property="formName"/></td>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top">
        <bean:write name="docssend" property="dossiersName"/>
        </td>
    </tr>
    
      <tr>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.expressId" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docssend" property="expressName"/></td>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.secureId" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docssend" property="secureName"/></td>
    </tr>
    
   
     
    <tr>
        <td nowrap valign="top"  ><Strong><bean:message key="form.docs.statusId" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top" align="left" colspan="2" ><bean:write name="docssend" property="statusName"/></td>
        <td nowrap valign="top" align="left" >
        
        </td>
    </tr>
   
     
   
    
    
    </table>
    </div>
</logic:present>
