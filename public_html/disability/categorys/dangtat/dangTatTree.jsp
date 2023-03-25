<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<script language=javascript>
    function getInforDangTat(id){
		post('disabilityFuntion',anchor + ':_LIST_DANGTAT:id:' + id);
		messageImg('right');
    }
	dLeft = new dTree('dLeft');
	dLeft.add(0,-1,'<font style="font-size:11px;"><bean:message key="dangtat.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforDangTat(0)");
	
	<logic:present name="BDangTats">
		<logic:notEmpty name="BDangTats">
			<logic:iterate id="bean" name="BDangTats" type="com.form.disability.categorys.FDangTat">
				dLeft.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInforDangTat(<%=bean.getId()%>)");
			</logic:iterate>  
		</logic:notEmpty>
	</logic:present>
    document.write(dLeft);
</script>

 
