<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInfordanhgia(id){
      post('disabilityFuntion',anchor + ':_LIST_DANHGIA:id:' + id);
      messageImg('right');
    }
    
	dLeftTt = new dTree('dLeftTt');
	dLeftTt.add(0,-1,'<font style="font-size:11px;"><bean:message key="danhgia.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInfordanhgia(0)");

	<logic:present name="BDanhgias">
		<logic:notEmpty name="BDanhgias">
			<logic:iterate id="bean" name="BDanhgias" type="com.form.disability.categorys.FDanhgia">
				dLeftTt.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInfordanhgia(<%=bean.getId()%>)");
			</logic:iterate> 
		</logic:notEmpty>
	</logic:present>
    document.write(dLeftTt);
</script>
 
