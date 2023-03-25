<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforLydo(id){
      post('disabilityFuntion',anchor + ':_LIST_QUANHE:id:' + id);
      messageImg('right');
    }
	treeQuanhe = new dTree('treeQuanhe');
	treeQuanhe.add(0,-1,'<font style="font-size:11px;"><bean:message key="lydo.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforLydo(0)");
	<logic:present name="BLydos">
		<logic:notEmpty name="BLydos">
			<logic:iterate id="bean" name="BLydos" type="com.form.disability.categorys.FQuanhe">
				treeQuanhe.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInforLydo(<%=bean.getId()%>)");
			</logic:iterate> 
		</logic:notEmpty>
	</logic:present>
    document.write(treeQuanhe);
</script>
 
