<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="docsrecv" method="post" />
<html:form action="docssend" method="post" />
<html:form action="forYou" method="post">
                        <div id="mailcol">
                            <div class="tabmenu" id="container-1" >
                                <div style="clear:both"></div>
                                <jsp:include page="/commons/menuDoc.jsp"><jsp:param name="optionmenu" value="6"/></jsp:include>
                                <div id="fragment-1">
                                    <div class="content-calendar">
<table  class="list-voffice"  cellpadding="5" cellspacing="0" border="0" width="100%" >
<tr>
                <th width="5%" nowrap><bean:message key="app.stt" bundle="<%=interfaces%>"/></th>
                <th width="10%" nowrap><bean:message key="forYou.label.dateCreate" bundle="<%=interfaces%>"/></th>
                <th width="25%" nowrap><bean:message key="forYou.label.boss" bundle="<%=interfaces%>"/></th>
                <th width="15%" nowrap><bean:message key="forYou.label.dateFrom" bundle="<%=interfaces%>"/></th>
                <th width="15%" nowrap><bean:message key="forYou.label.dateTo" bundle="<%=interfaces%>"/></th>
                <th width="15%" nowrap><bean:message key="forYou.label.problem" bundle="<%=interfaces%>"/></th>
                <th width="5%"   nowrap>&nbsp;#</th>
</tr>
<logic:present name="BForYous">
<bean:define name="BForYous" id="beans" type="com.form.FBeans"/>
<%  int i = 1;%>
<logic:iterate name="BForYous" id="bean" type="com.form.foryou.FForYou">   
    <tr class="<%=(i%2==0)?"content1":"content"%>" >
        <td align="center" ><span class="index"><%=i++%></span></td>
        <td>
                <bean:write name="bean" property="dateCreate" />
        </td>
        <td>
        
                                <logic:equal name="bean" property="status" value="1">            
                                    <Strong><bean:write name="bean" property="boss"/></strong>
                                </logic:equal>
                                <logic:notEqual name="bean" property="status" value="1">
                                    <s style="color:red;"><Strong><bean:write name="bean" property="boss"/></strong></s>
                                </logic:notEqual>
        
        </td>
        <td>
          <logic:equal name="bean" property="workflowId" value="1" >
                                     <bean:message key="doc.recv.caption" bundle="<%=interfaces%>"/>
                                  </logic:equal>
                                   <logic:equal name="bean" property="workflowId" value="2" >
                                            <bean:message key="doc.send.caption" bundle="<%=interfaces%>"/>
                                  </logic:equal>
        </td>
        <td><bean:write name="bean" property="dateFrom"/></td>
        <td><bean:write name="bean" property="dateTo"/></td>
        <td>
        <logic:notEqual name="bean" property="problem" value="">
                        
                                            <bean:write name="bean" property="problem"/>
                       
                        </logic:notEqual>
        </td>
        <td nowrap >
                        <img style="border:0px;cursor: pointer;" onmouseover="javascript:checkedInnerHtml();addthis_open(this,'Danh s&#225;ch c&#244;ng v&#259;n','','', '');postAjax('forYou','at_share',anchor + ':_VIEW_DOCS:id:<bean:write name="bean" property="id"/>:workflowId:<bean:write name="bean" property="workflowId"/>');"   height="20px" src="<%=contextPath%>/images/foryou.gif" >
                        <span id="viewdoc<%=i%>" style="display:none;"></span>
        </td>
    </tr>
</logic:iterate>
</logic:present>
</table>
<table width="100%">
    <tr>
        <td align="left">
        <Strong><bean:message key="page.caption.total" bundle="<%=interfaces%>"/> <bean:write name="beans" property="totalRows"/></strong></td>
        <td align="right">
         <%String params = anchor + ":_FORYOU_LIST";%>
        <jsp:include page="/paging.jsp">
            <jsp:param name="BEANS" value="BForYous"/>
             <jsp:param name="PARAMS" value="<%=params%>"/>
            <jsp:param name="FORM" value="forYou"/>
            <jsp:param name="METHOD" value="post"/>
        </jsp:include>
        </td>
    </tr>
</table>                                           
            </div>
        </div>
              
    </div>
</div> 
</div>
</html:form>
