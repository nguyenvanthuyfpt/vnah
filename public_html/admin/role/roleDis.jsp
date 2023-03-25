<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
	
<style type="text/css">
	@import url(<%=contextPath%>/styles/checkboxtree/checktree.css);
</style> 
	
<script language="javascript" src="<%=contextPath%>/js/checkboxtree/jquery-latest.js"></script>
<script language="javascript" src="<%=contextPath%>/js/checkboxtree/jquery.js"></script>
<script>
	
jQuery(document).ready(function(){
	jQuery("#checkchildren").checkboxTree({
			collapsedarrow: "<%=contextPath%>/js/checkboxtree/img-arrow-collapsed.gif",
			expandedarrow: "<%=contextPath%>/js/checkboxtree/img-arrow-expanded.gif",
			blankarrow: "<%=contextPath%>/js/checkboxtree/img-arrow-blank.gif",
			checkchildren: true
	});
	jQuery("#dontcheckchildren").checkboxTree({
			collapsedarrow: "<%=contextPath%>/js/checkboxtree/img-arrow-collapsed.gif",
			expandedarrow: "<%=contextPath%>/js/checkboxtree/img-arrow-expanded.gif",
			blankarrow: "<%=contextPath%>/js/checkboxtree/img-arrow-blank.gif",
			checkchildren: false
	});
	jQuery("#docheckchildren").checkboxTree({
			collapsedarrow: "<%=contextPath%>/images/checkboxtree/img-arrow-collapsed.gif",
			expandedarrow: "<%=contextPath%>/images/checkboxtree/img-arrow-expanded.gif",
			blankarrow: "<%=contextPath%>/images/checkboxtree/img-arrow-blank.gif",
			checkchildren: true,
			checkparents: false
	});
	jQuery("#dontcheckchildrenparents").checkboxTree({
			collapsedarrow: "<%=contextPath%>/js/checkboxtree/img-arrow-collapsed.gif",
			expandedarrow: "<%=contextPath%>/js/checkboxtree/img-arrow-expanded.gif",
			blankarrow: "<%=contextPath%>/js/checkboxtree/img-arrow-blank.gif",
			checkchildren: false,
			checkparents: false
	});
});

</script>

<bean:define name="contentTreeTinhs" id="contentTree" type="java.lang.String"/>
<%=contentTree.replaceAll("-","")%>