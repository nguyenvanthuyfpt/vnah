<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<html:hidden name="frmRequire" property="rmStatus" />
<html:hidden name="frmRequire" property="rmId" />
<html:hidden name="frmRequire" property="obServer" />

<input type="hidden" name="secureId" value="<bean:write name="secureId" />" /> 
<span><jsp:include page="/require/requires/tag.jsp" /></span>
<span id="divReview"><jsp:include page="/require/requires/reviewList.jsp" /></span>  
<div style="padding-bottom:4px"><jsp:include page="/require/requires/toolcmd.jsp" /></div>


