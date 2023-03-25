<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BTotalsStatus" >

<div><jsp:include page="/doc/docReport/totalStatus.jsp" /></div>
</logic:present>

<logic:present name="BTotalsTransfer" >
<div><jsp:include page="/doc/docReport/totalTransfer.jsp" /></div>
</logic:present>

<logic:present name="BTotalsDocType" >
<div><jsp:include page="/doc/docReport/totalDocType.jsp" /></div>
</logic:present>

<logic:present name="BTotalsBranch" >
<div><jsp:include page="/doc/docReport/totalBranch.jsp" /></div>
</logic:present>