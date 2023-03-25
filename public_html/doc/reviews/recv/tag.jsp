<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>
<script language="javascript">
function mdotab(obj,params){
    if(obj.className=='tab'){
        for(i=0;i<obj.parentNode.cells.length-1;i++){
                obj.parentNode.cells[i].className='tab';
        }
        obj.className='tabactive';
        postAjax('docMainAssignRecv','docMain',anchor + ':' + params);
        messageImg('docMain')
    }    
}
</script>
<html:form action="docssend" method="post"/>
<html:form action="docMainAssignRecv" method="post"/>
<html:form action="dossiers" method="post" />
<bean:define name="docAssign" property="id" id="id" type="java.lang.Integer" />
<html:hidden name="docAssign" property="id" /> 

<table style="border-bottom:#CCDAE3 solid 1px;" width="100%" align="left" cellpadding="0" cellspacing="0"><tr><td valign="bottom">
                 <div class="d7v0" align="center">
                     <table border="0" cellpadding="0" cellspacing="0" >
                       <tr valign="bottom">  
                         <td id="_EMPLOYEE_LYLICH" nowrap="nowrap" class="tabactive" width="100px"  onclick="mdotab(this,'DOC_DETAILT:id:<%=id%>');">
                            <div>&nbsp;&nbsp;<bean:message key="docs.tab.header.infor" bundle="<%=interfaces%>"/>&nbsp;&nbsp;</div>
                        </td>
                        <td id="_EMPLOYEE_LYLICH" nowrap="nowrap" class="tab" width="100px"  onclick="mdotab(this,'_PREPARED_SAVE:id:<%=id%>');">
                            <div>&nbsp;&nbsp;<bean:message key="docs.tab.header.trailer" bundle="<%=interfaces%>"/>&nbsp;&nbsp;</div>
                        </td>
                        <td nowrap="nowrap" width="100%" align="left">&nbsp;
                                <logic:equal name="trackingInfor" value="0"><!--Check vao theo doi-->        
                                <Strong><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/> : </strong>
                                <span id="idDossiers">
                                <html:select name="docsrecv" property="dossierId" styleId="dossierId"  styleClass="fieldSelect" >
                                <html:option value="0"> <bean:message key="form.dossiers.select.cation" bundle="<%=interfaces%>"/> </html:option>        
                                <logic:present name="BDossiers">
                                <html:options collection="BDossiers" property="id" labelProperty="name"/>
                                </logic:present>
                                </html:select>
                                </span>
                                    <a class="modal-button" href="docsrecv<%=extention%>?<%=anchor%>=_CREATE_DOSS_RECV" rel="{handler: 'iframe', size: {x: 370, y: 260},bookmark:'postAjax(\'dossiers\',\'idDossiers\',anchor + \':_SAVE_NEW_RECV\')'}">   
                                    <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/i_13.gif" title="<bean:message key="dossiers.add.caption" bundle="<%=interfaces%>"/>">  
                                    </a>
                                <input type="button"   onclick="javascript:postAjax('docsrecv','docMain',anchor + ':_SAVE_DOC:dossierId_doc:' + getObj('dossierId').value )" value='<bean:message key="cmd.doc.update.dossier" bundle="<%=interfaces%>"/>' />              
                                </logic:equal>     
                        </td>                        
                       </tr>
                     </table>
                 </div>	
 </td></tr></table> 


