<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforhotro(id){
      post('disabilityFuntion',anchor + ':_LIST_HOTRO:id:' + id);
      messageImg('right');
    }
    
	dLeftHt = new dTree('dLeftHt');
	dLeftHt.add(0,-1,'<font style="font-size:11px;"><bean:message key="hotro.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforhotro(0)");
      
	<logic:present name="BHotros">
		<logic:notEmpty name="BHotros">
			<logic:iterate id="bean" name="BHotros" type="com.form.disability.categorys.FHotro">
				dLeftHt.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInforhotro(<%=bean.getId()%>)");
			</logic:iterate> 
		</logic:notEmpty>
	</logic:present>
    document.write(dLeftHt);
</script>
 
