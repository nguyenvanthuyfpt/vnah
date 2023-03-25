<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div class="ct-celendar">  
 <table class="tableForm" style="border-collapse: collapse"  width="100%"  cellpadding="0" cellspacing="0">
    <tr>
              <td  ><bean:message key="form.searchDocs.type" bundle="<%=interfaces%>"/></td>
            <td  >
            <html:select name="docsSearch" property="type" styleClass="fieldSelect" style="width:150px;" >
                    <html:option value="0"><bean:message key="form.docs.type.header.recv" bundle="<%=interfaces%>"/></html:option>
                    <html:option value="1"><bean:message key="form.docs.type.header.send" bundle="<%=interfaces%>"/></html:option>
                    <html:option value="2"><bean:message key="form.docs.type.header.send.dt" bundle="<%=interfaces%>"/></html:option>
            </html:select>
            </td>
            <td  ><bean:message key="form.docs.formId" bundle="<%=interfaces%>"/></td>
            <td  >
            <html:select name="docsSearch" property="formId" styleClass="fieldSelect" >
                    <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <logic:present name="BForms">
                        <html:options collection="BForms" property="id" labelProperty="name"/>
                    </logic:present>
            </html:select>
            </td>
    </tr>
     <tr>
            <td  ><bean:message key="form.docs.localCode" bundle="<%=interfaces%>"/></td>
            <td  ><html:text name="docsSearch" property="localCode" /></td>
            <td  ><bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/></td>
            <td  ><html:text name="docsSearch" property="docCode" /></td>
    </tr>
    <tr>
            <td  ><bean:message key="form.docs.localDateFrom" bundle="<%=interfaces%>"/></td>
            <td  ><input type="text" name="localDateFrom" id="localDateFrom" value="<bean:write name="docsSearch" property="localDateFrom"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'localDateFrom','dd/mm/yyyy');return false;"></td>
            <td  ><bean:message key="form.docs.localDateTo" bundle="<%=interfaces%>"/></td>
            <td  ><input type="text" name="localDateTo" id="localDateTo" value="<bean:write name="docsSearch" property="localDateTo"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'localDateTo','dd/mm/yyyy');return false;"></td>
    </tr>
    <tr>
            <td  ><bean:message key="form.docs.docDateFrom" bundle="<%=interfaces%>"/></td>
            <td  ><input type="text" name="docDateFrom" id="docDateFrom" value="<bean:write name="docsSearch" property="docDateFrom"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'docDateFrom','dd/mm/yyyy');return false;"></td>
            <td  ><bean:message key="form.docs.docDateTo" bundle="<%=interfaces%>"/></td>
            <td  ><input type="text" name="docDateTo" id="docDateTo" value="<bean:write name="docsSearch" property="docDateTo"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'docDateTo','dd/mm/yyyy');return false;"></td>
    </tr>
    <tr>
            <td  ><bean:message key="form.docs.timeCreateFrom" bundle="<%=interfaces%>"/></td>
            <td  ><input type="text" name="timeCreateFrom" id="timeCreateFrom" value="<bean:write name="docsSearch" property="timeCreateFrom"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'timeCreateFrom','dd/mm/yyyy');return false;"></td>
            <td  ><bean:message key="form.docs.timeCreateTo" bundle="<%=interfaces%>"/></td>
            <td  ><input type="text" name="timeCreateTo" id="timeCreateTo" value="<bean:write name="docsSearch" property="timeCreateTo"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'timeCreateTo','dd/mm/yyyy');return false;"></td>
    </tr>
    
     <tr>
            <td  ><bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/></td>
            <td   colspan="3"><html:text name="docsSearch" property="abstracts" style="width:500px;" />
            </td>
    </tr>
    <tr>
            <td  ><bean:message key="form.docs.issue" bundle="<%=interfaces%>"/></td>
            <td   colspan="3" ><html:text name="docsSearch" property="issue" style="width:500px;" /></td>
    </tr>
     <tr>
            <td  ><bean:message key="form.docs.statusId" bundle="<%=interfaces%>"/></td>
            <td    colspan="3" align="left">
            <html:select name="docsSearch" property="statusId" styleClass="fieldSelect" >
              <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>          
                        <logic:present name="BStatus">
                        <html:options collection="BStatus" property="id" labelProperty="name"/>
                        </logic:present>
                </html:select>
            </td>
            
            
    </tr>
    <tr>
            <td  ><bean:message key="form.docs.fromId" bundle="<%=interfaces%>"/></td>
            <td  >
               <html:select name="docsSearch" property="fromId" styleClass="fieldSelect" >
                <logic:present name="BFroms">
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                        <html:options collection="BFroms" property="id" labelProperty="vnName"/>
                    </logic:present>
          </html:select> 
            </td>
            <td  ><bean:message key="form.docs.storeAgeId" bundle="<%=interfaces%>"/></td>
            <td  >
                <html:select name="docsSearch" property="storeAgeId" styleClass="fieldSelect" >
                        <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                        <logic:present name="BDeps">
                        <html:options collection="BDeps" property="id" labelProperty="name"/>
                        </logic:present>
                </html:select>            
            </td>
    </tr>
     <tr>
            <td  ><bean:message key="form.docs.secureId" bundle="<%=interfaces%>"/></td>
            <td  >
             <html:select name="docsSearch" property="secureId" styleClass="fieldSelect" >
                 <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <logic:present name="BSecures">
                 <html:options collection="BSecures" property="id" labelProperty="name"/>
                 </logic:present>
          </html:select>
            </td>
            <td  ><bean:message key="form.docs.signer" bundle="<%=interfaces%>"/></td>
            <td  > <html:text name="docsSearch" property="signer" /> </td>
    </tr>
     <tr>
            <td  ><bean:message key="form.docs.expressId" bundle="<%=interfaces%>"/></td>
            <td  >
                    <html:select name="docsSearch" property="expressId" styleClass="fieldSelect" >
                    <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <logic:present name="BExpresss">
                    <html:options collection="BExpresss" property="id" labelProperty="name"/>
                    </logic:present>
                    </html:select>
            </td>
            <td  ><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/></td>
            <td  >
                    <html:select name="docsSearch" property="dossierId" styleClass="fieldSelect" >
                    <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <logic:present name="BDossiers">
                    <html:options collection="BDossiers" property="id" labelProperty="name"/>
                    </logic:present>
                    </html:select>
            </td>
    </tr>
    <tr>
            <td  ><bean:message key="form.docs.docsTypeId" bundle="<%=interfaces%>"/></td>
            <td  >
                    <html:select name="docsSearch" property="docsTypeId" styleClass="fieldSelect" >
                    <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <logic:present name="BDocTypes">
                    <html:options collection="BDocTypes" property="idDocType" labelProperty="nameDocType"/>
                    </logic:present>
                    </html:select>
            </td>
            <td  ><bean:message key="form.docs.viaId" bundle="<%=interfaces%>"/></td>
            <td  >
                    <html:select name="docsSearch" property="viaId" styleClass="fieldSelect" >
                    <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <logic:present name="BVias">
                    <html:options collection="BVias" property="id" labelProperty="name"/>
                    </logic:present>
                    </html:select>
            </td>
    </tr>
     <tr>
            <td  ><bean:message key="form.docs.deadLineFrom" bundle="<%=interfaces%>"/></td>
            <td  ><input type="text" name="deadLineFrom" id="deadLineFrom" value="<bean:write name="docsSearch" property="deadLineFrom"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'deadLineFrom','dd/mm/yyyy');return false;"></td>
            <td  ><bean:message key="form.docs.deadLineTo" bundle="<%=interfaces%>"/></td>
            <td  ><input type="text" name="deadLineTo" id="deadLineTo" value="<bean:write name="docsSearch" property="deadLineTo"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'deadLineTo','dd/mm/yyyy');return false;"></td>
    </tr>
</table>
<table width="100%" cellpadding="0" cellspacing="0" style="padding-top:10px;margin-top:10px;">
    <tr>
        <td align="center" height="26px"><jsp:include page="/doc/docsSearch/cmd.jsp" /></td>
    </tr>
</table>
</div>