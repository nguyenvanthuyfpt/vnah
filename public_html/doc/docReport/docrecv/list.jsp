<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%String members="";%>
<logic:notEmpty name="docreport" property="docIds" >
  <bean:define name="docreport" property="docIds" id="docIds" type="java.lang.String"/>
  <%members=docIds;%>
</logic:notEmpty>
<% String checkeds = ""; %>


<html:hidden name="docreport" property="docIds" />
<table  class="list-voffice" cellpadding="5" cellspacing="0" width="100%" border="0">
<tr>
  <th  style="cursor: pointer;" width="5%" ><bean:message key="app.stt" bundle="<%=interfaces%>"/></th>
  <th  style="cursor: pointer;" width="5%" ><img src="<%=contextPath%>/images/attach.gif" /></th>
  <th   style="cursor: pointer;" ><bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/></th>
  <th  style="cursor: pointer;" ><bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/></th>
  <th  style="cursor: pointer;" nowrap><bean:message key="form.docs.docDate" bundle="<%=interfaces%>"/></th>
  <th  style="cursor: pointer;" ><bean:message key="form.docs.fromId2" bundle="<%=interfaces%>"/></th>
  
</tr>

<logic:present name="BDocs" >
<bean:define name="BDocs" id="beans" type="com.form.FBeans"/>
<%  int i = beans.getFirstRecord();%>
<logic:iterate name="BDocs" id="bean" type="com.form.doc.docsrecv.FDocsrecv">
  <tr class="<%=(i%2==0)?"content1":"content"%>">
            <td  align="center" ><span class="index"><%=i++%></span></td>
             <td class="<%=(i%2>0?"tdcontent":"tdcontent1")%>" align="center">
              <%  if (bean.getAllFiles().size()>1) {%>
                        <img src="<%=contextPath%>/images/attach.gif"  onclick="checkedInnerHtml();addthis_open(this,'<bean:message key="doc.version.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('docsrecv','at_share',anchor + ':_FILEDINHKEM:id:<%=bean.getId()%>');" />
               <%}else{%>
                       <logic:iterate name="bean" property="allFiles" id="beanFiles" type="com.form.doc.docsrecv.FFilesRecv">  
                            <a href="javascript:post('docsrecv',anchor + ':_DOWNLOAD:fileId:<bean:write name="beanFiles" property="idFiles"/>');remove('docsrecv',anchor);remove('docsrecv','fileId');" >
                                <img src="<%=contextPath%>/images/attach.gif" border="0"/>
                           </a>
                        </logic:iterate>
               <%}%>
               
            </td>
            
            <td  valign="top" class="<%=(i%2>0?"tdcontent":"tdcontent1")%>" style="font-weight:<%=(bean.getReaded()==0?"bold":"normal")%>;" >
                    <bean:write name="bean" property="docCode"/>
            </td>
            <td class="<%=(i%2>0?"tdcontent":"tdcontent1")%>" style="font-weight:<%=(bean.getReaded()==0?"bold":"normal")%>;" >
                 <bean:write name="bean" property="abstracts"/>
            </td>
            <td class="<%=(i%2>0?"tdcontent":"tdcontent1")%>" style="font-weight:<%=(bean.getReaded()==0?"bold":"normal")%>;" >
                <bean:write name="bean" property="docDate"/>
            </td>
            <td class="<%=(i%2>0?"tdcontent":"tdcontent1")%>" style="font-weight:<%=(bean.getReaded()==0?"bold":"normal")%>;" >
                <bean:write name="bean" property="fromVnName"/>
            </td>           
  </tr>
</logic:iterate> 
</logic:present>
</table>
<div>
<div  style="float:left">
    <Strong><bean:message key="page.caption.total" bundle="<%=interfaces%>"/> 
    <bean:write name="beans" property="totalRows"/>
    </strong>
    </div>
    <div style="float:right">
    <%String params = anchor + ":_SELECT";%>
        <jsp:include page="/paging.jsp">
            <jsp:param name="BEANS" value="BDocs"/>
             <jsp:param name="PARAMS" value="<%=params%>"/>
            <jsp:param name="FORM" value="docreport"/>
            <jsp:param name="METHOD" value="post"/>
        </jsp:include>
        </div>
</div>