<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">

</script>
<%!
	private static int getRowIndex(int curPage, int rowTotal, int rowPerPage, Boolean fl){
		int pageTotal = rowTotal / rowPerPage;
		if (pageTotal * rowPerPage < rowTotal)	pageTotal++;

		int startIndexRow = 1;
		int finishIndexRow = rowTotal;
	
		if (curPage <= 1) {
			finishIndexRow = rowPerPage;
		} else {
			startIndexRow = ((curPage - 1) * rowPerPage) + 1;
			if (curPage != pageTotal) {
				finishIndexRow = curPage * rowPerPage;
			}			
		}

		if (fl.booleanValue()) {
			return startIndexRow; 
		} else {
			return finishIndexRow;
		}
	}
%>
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
int pageCurrent = BEANS.getPageCurrent();

int nextPage = pageCurrent + 1;
int prePage = pageCurrent - 1;

int rowPerPage = 10;
int pageTotal = pagesCount / rowPerPage;
if (pageTotal * rowPerPage < pagesCount)
	pageTotal++;

String toolTipNext =
	"Hi&#7875;n th&#7883; k&#7871;t qu&#7843; t&#7915;&nbsp;"
		+ getRowIndex(pageCurrent + 1, pagesCount, rowPerPage, Boolean.TRUE)
		+ "&nbsp;&#273;&#7871;n&nbsp;"
		+ getRowIndex(pageCurrent + 1, pagesCount, rowPerPage, Boolean.FALSE)
		+ "/"
		+ pagesCount
    + " trang";

String toolTipPre =
	"Hi&#7875;n th&#7883; k&#7871;t qu&#7843; t&#7915;&nbsp;"
		+ getRowIndex(pageCurrent - 1, pagesCount, rowPerPage, Boolean.TRUE)
		+ "&nbsp;&#273;&#7871;n&nbsp;"
		+ getRowIndex(pageCurrent - 1, pagesCount, rowPerPage, Boolean.FALSE)
		+ "/"
		+ pagesCount
    + " trang";

String toolTipFirst =
	"Trang &#273;&#7847;u t&#7915;&nbsp;"
		+ getRowIndex(1, pagesCount, rowPerPage, Boolean.TRUE)
		+ "&nbsp;&#273;&#7871;n&nbsp;"
		+ getRowIndex(1, pagesCount, rowPerPage, Boolean.FALSE)
		+ "/"
		+ pagesCount
    + " trang";

String toolTipLast =
	"Trang cu&#7889;i t&#7915;&nbsp;"
		+ getRowIndex(pageTotal, pagesCount, rowPerPage, Boolean.TRUE)
		+ "&nbsp;&#273;&#7871;n&nbsp;"
		+ getRowIndex(pageTotal, pagesCount, rowPerPage, Boolean.FALSE)
		+ "/"
		+ pagesCount 
    + " trang";	
	
%>
<TABLE style="table-layout:fixed;border:0px" align="right" border="0">
  <TBODY>
  <TR>
    <TD style="width:50px;padding-top:6px">
    <%if(pageCurrent>1){%>
        <img style="cursor: pointer" title="<%=toolTipFirst%>" src="<%=contextPath%>/images/record-first.png" onclick="<%=METHOD%>('<%=FORM%>'<%=POSITION%>,'<%=PARAMS%>pageIndex:1')">
    <%}else{%>
        <img src="<%=contextPath%>/images/record-first-g.png">
    <%}%>
    <%if(BEANS.havePrevPage()){%>
        <img style="cursor: pointer" title="<%=toolTipPre%>" src="<%=contextPath%>/images/record-prev.png"  onclick="<%=METHOD%>('<%=FORM%>'<%=POSITION%>,'<%=PARAMS%>pageIndex:<%=(pageCurrent-1)%>')">
    <%}else{%>
        <img src="<%=contextPath%>/images/record-prev-g.png">
    <%}%>
    </TD>
    <TD style="padding-top:0px;width:100px;font-family:Tahoma;font-size:11px;font-weight: normal;">
            <bean:message key="page.caption" bundle="<%=interfaces%>"/>
            <input onkeydown="if(event.keyCode==13){<%=METHOD%>('<%=FORM%>'<%=POSITION%>,'<%=PARAMS%>pageIndex:' + this.value);return false;}" type="text" name="selectPage" value="<%=pageCurrent%>" style="font-family:Tahoma;font-size:11px;width:30px;text-align: right;">
            <bean:message key="page.separate" bundle="<%=interfaces%>"/><%=pagesCount%>
     </TD> 
     <TD style="width:50px;padding-top:6px">
    <%if(BEANS.haveNextPage()){%>
        <img style="cursor: pointer" title="<%=toolTipNext%>" src="<%=contextPath%>/images/record-next.png"  onclick="<%=METHOD%>('<%=FORM%>'<%=POSITION%>,'<%=PARAMS%>pageIndex:<%=(pageCurrent+1)%>')">
    <%}else{%>
        <img src="<%=contextPath%>/images/record-next-g.png">
    <%}%>
    <%if(pageCurrent<pagesCount){%>
        <img style="cursor: pointer" title="<%=toolTipLast%>" src="<%=contextPath%>/images/record-last.png" onclick="<%=METHOD%>('<%=FORM%>'<%=POSITION%>,'<%=PARAMS%>pageIndex:<%=(pagesCount)%>')">
    <%}else{%>
        <img src="<%=contextPath%>/images/record-last-g.png">
    <%}%>
</TD></TR>
</TBODY></TABLE>
</logic:greaterThan>
</logic:present>
</logic:present>
