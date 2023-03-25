<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BMessages">
<bean:define name="createMessage" property="type" id="type" type="java.lang.Integer"/>
<bean:define name="BMessages" id="beans" type="com.form.FBeans"/>
<logic:greaterThan name="beans" property="pagesCount" value="1">
<%
int pagesCount = beans.getPagesCount();
int pageCurrent = beans.getPageCurrent();
%>
<TABLE >
  <TBODY>
  <TR>
    <td style="width:50px;padding-top:6px">
    <%if(pageCurrent>1){%>
        <img style="cursor: pointer" src="<%=contextPath%>/images/record-first.png" onclick="navigator(':pageIndex:1:type:<%=type%>')">
    <%}else{%>
        <img src="<%=contextPath%>/images/record-first-g.png">
    <%}%>
    <%if(beans.havePrevPage()){%>
        <img style="cursor: pointer" src="<%=contextPath%>/images/record-prev.png"  onclick="navigator(':pageIndex:<%=(pageCurrent-1)%>:type:<%=type%>')" >
    <%}else{%>
        <img src="<%=contextPath%>/images/record-prev-g.png">
    <%}%>
        </td>
        <td style="padding-top:0px;width:110px;font-family:Tahoma;font-size:11px;font-weight: normal;"><bean:message key="page.caption" bundle="<%=interfaces%>"/>
            <input onkeydown="if(event.keyCode==13){navigator(this.value);return false;}" type="text" name="selectPage" value="<%=pageCurrent%>" style="font-family:Tahoma;font-size:11px;width:25px;text-align: right;">
            <bean:message key="page.separate" bundle="<%=interfaces%>"/><%=pagesCount%>
       </td> 
     <td style="width:100px;padding-top:6px">
    <%if(beans.haveNextPage()){%>
        <img style="cursor: pointer" src="<%=contextPath%>/images/record-next.png"  onclick="navigator(':pageIndex:<%=(pageCurrent+1)%>:type:<%=type%>')">
    <%}else{%>
        <img src="<%=contextPath%>/images/record-next-g.png">
    <%}%>
        <%if(pageCurrent<pagesCount){%>
        <img style="cursor: pointer" src="<%=contextPath%>/images/record-last.png" onclick="navigator(':pageIndex:<%=(pagesCount)%>:type:<%=type%>')">
    <%}else{%>
        <img src="<%=contextPath%>/images/record-last-g.png">
    <%}%>
</td></TR>
</TBODY></TABLE>
</logic:greaterThan>
</logic:present>



