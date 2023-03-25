<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present parameter="BEANS">
<%String BS = request.getParameter("BEANS");%>
<logic:present name="<%=BS%>">
<bean:define name="<%=BS%>" id="BEANS" type="com.form.FBeans"/>
<%
    String FORM = request.getParameter("FORM");
    String PARAMS = request.getParameter("PARAMS");
    if(PARAMS==null){
        PARAMS = "";
    }else{
        PARAMS = PARAMS + ":";
    }
    String METHOD = request.getParameter("METHOD");
    String POSITION = request.getParameter("POSITION");
    if(POSITION==null){
        POSITION="";
    }else{
        POSITION=",'" + POSITION + "'";    
    }
%>
<logic:greaterThan name="BEANS" property="pagesCount" value="1">
<%
int pagesCount = BEANS.getPagesCount();
int totals=BEANS.getTotalRows();
int pageCurrent = BEANS.getPageCurrent();
%>
<div>    
<table width="200px" cellspacing="0" cellpadding="0" border="0" class="contentDoc">
  <tbody>
        <tr style="height: 100%;">   
    <Td width="5%" nowrap>
    <%if(pageCurrent>1){%>
        <img style="cursor: pointer" src="<%=contextPath%>/images/mail/paging/page-first.gif" onclick="<%=METHOD%>('<%=FORM%>'<%=POSITION%>,'<%=PARAMS%>pageIndex:1')">
    <%}else{%>
        <img src="<%=contextPath%>/images/mail/paging/page-first-disabled.gif">
    <%}%>
    <%if(BEANS.havePrevPage()){%>
        <img style="cursor: pointer" src="<%=contextPath%>/images/mail/paging/page-prev.gif"  onclick="<%=METHOD%>('<%=FORM%>'<%=POSITION%>,'<%=PARAMS%>pageIndex:<%=(pageCurrent-1)%>')">
    <%}else{%>
        <img src="<%=contextPath%>/images/mail/paging/page-prev-disabled.gif">
    <%}%>
   </td> 
   <Td >             
                <div/>
            </td>
    <Td width="88%" align="center">
            <bean:message key="page.caption" bundle="<%=interfaces%>"/>
            <span style="font-family:Tahoma;font-size:11px;width:15px;text-align: right;"><Strong><%=pageCurrent%></Strong></span>
            <bean:message key="page.separate" bundle="<%=interfaces%>"/><%=pagesCount%>
     </Td> 
        <Td>             
             <div/>
            </td>
      <Td width="5%" nowrap>
    <%if(BEANS.haveNextPage()){%>
        <img style="cursor: pointer" src="<%=contextPath%>/images/mail/paging/page-next.gif"  onclick="<%=METHOD%>('<%=FORM%>'<%=POSITION%>,'<%=PARAMS%>pageIndex:<%=(pageCurrent+1)%>')">
    <%}else{%>
        <img src="<%=contextPath%>/images/mail/paging/page-next-disabled.gif">
    <%}%>
    <%if(pageCurrent<pagesCount){%>
        <img style="cursor: pointer" src="<%=contextPath%>/images/mail/paging/page-last.gif" onclick="<%=METHOD%>('<%=FORM%>'<%=POSITION%>,'<%=PARAMS%>pageIndex:<%=(pagesCount)%>')">
    <%}else{%>
        <img src="<%=contextPath%>/images/mail/paging/page-last-disabled.gif">
    <%}%>
</Td></TR>
</TBODY></TABLE>
</div>
</logic:greaterThan>
</logic:present>
</logic:present>
