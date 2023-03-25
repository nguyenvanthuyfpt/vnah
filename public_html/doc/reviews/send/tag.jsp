<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>

<script language="javascript">
function mdotab(obj,params){
    if(obj.className=='tab'){
        for(i=0;i<obj.parentNode.cells.length-1;i++){
                obj.parentNode.cells[i].className='tab';
        }
        obj.className='tabactive';
        postAjax('docMainAssignSend','docMain',anchor + ':' + params);
        messageImg('docMain')
    }    
}
</script>
<bean:define name="docAssign" property="id" id="id" type="java.lang.Integer" />
<bean:define name="docAssign" property="type" id="type" type="java.lang.Integer" />
<bean:define name="docAssign" property="statusId" id="statusId" type="java.lang.Integer" />
<input type="hidden" name="docId" value="<%=id%>"/>
 <table style="border-bottom:#CCDAE3 solid 1px;" width="100%"  align="left" cellpadding="0" cellspacing="0">
 <tr>
 <td valign="bottom">
                 <div class="d7v0" align="center">
                     <table  cellpadding="0" cellspacing="0" width="100%">
                       <tr valign="bottom">  
                        <logic:equal name="docAssign" property="tabActive" value="1" > <!--access from addnew docs-->
                         <td id="_EMPLOYEE_LYLICH" nowrap="nowrap" class="tab" width="100px"  onclick="mdotab(this,'DOC_DETAILT:statusId:<%=statusId%>:id:<%=id%>:type:<%=type%>');">
                            <div>&nbsp;&nbsp;<bean:message key="docs.tab.header.infor" bundle="<%=interfaces%>"/>&nbsp;&nbsp;</div>
                        </td>
                        </logic:equal>
                        <logic:equal name="docAssign" property="tabActive" value="0"> <!--access from docscode on list docs-->
                         <td id="_EMPLOYEE_LYLICH" nowrap="nowrap" class="tabactive" width="100px"  onclick="mdotab(this,'DOC_DETAILT:statusId:<%=statusId%>:id:<%=id%>:type:<%=type%>');">
                            <div>&nbsp;&nbsp;<bean:message key="docs.tab.header.infor" bundle="<%=interfaces%>"/>&nbsp;&nbsp;</div>
                        </td>
                        </logic:equal>
                        <td id="_EMPLOYEE_LYLICH" nowrap="nowrap" class="tab" width="100px"  onclick="mdotab(this,'_PREPARED_SAVE:statusId:<%=statusId%>:id:<%=id%>:type:<%=type%>');">
                            <div>&nbsp;&nbsp;<bean:message key="docs.tab.header.trailer" bundle="<%=interfaces%>"/>&nbsp;&nbsp;</div>
                        </td>
                        <td nowrap="nowrap"  align="left">&nbsp;
                        <logic:equal name="trackingInfor" value="0">
                        
         <Strong><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/> :</strong>
         <span id="idDossiers">
        <html:select name="docssend" property="dossierId" styleId="dossierId" styleClass="fieldSelect" >
                <html:option value="0"> <bean:message key="form.dossiers.select.cation" bundle="<%=interfaces%>"/> </html:option>        
                <logic:present name="BDossiers">
                 <html:options collection="BDossiers" property="id" labelProperty="name"/>
                 </logic:present>
          </html:select>
          </span>
                <a class="modal-button" href="docssend<%=extention%>?<%=anchor%>=_CREATE_DOSS_SEND" rel="{handler: 'iframe', size: {x: 370, y: 260},bookmark:'postAjax(\'dossiers\',\'idDossiers\',anchor + \':_SAVE_NEW_SEND\')'}">   
                <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/i_13.gif" title="<bean:message key="dossiers.add.caption" bundle="<%=interfaces%>"/>">  
                </a>
                <input type="button"  onclick="javascript:postAjax('docssend','docMain',anchor + ':_SAVE_DOC:dossierId_doc:' + getObj('dossierId').value )" value='<bean:message key="cmd.doc.update.dossier" bundle="<%=interfaces%>"/>' />              
        
        </logic:equal>
                        </td>                        
                       </tr>
                     </table>
                 </div>	
 </td></tr></table> 


