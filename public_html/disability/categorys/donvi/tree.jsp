<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforNguyenNhan(id){
		post('disabilityFuntion',anchor + ':_LIST_DONVI:id:' + id);
		messageImg('right');
    }
    
	treeDonvi = new dTree('treeDonvi');
	treeDonvi.add(0,-1,'<font style="font-size:11px;"><bean:message key="donvi.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforNguyenNhan(0)");

	<logic:present name="BDonvis">
		<logic:notEmpty name="BDonvis">
			<logic:iterate id="bean" name="BDonvis" type="com.form.disability.categorys.FDonvi">
				treeDonvi.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInforNguyenNhan(<%=bean.getId()%>)");
			</logic:iterate> 
		</logic:notEmpty>
	</logic:present>
				
    document.write(treeDonvi);
</script>
 
