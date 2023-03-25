<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforDanToc(id){
      post('disabilityFuntion',anchor + ':_LIST_DANTOC:id:' + id);
      messageImg('right');
    }
      treeDantoc = new dTree('treeDantoc');
      treeDantoc.add(0,-1,'<font style="font-size:11px;"><bean:message key="dantoc.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforDanToc(0)");
      <logic:present name="BDantocs">
<logic:notEmpty name="BDantocs">
      <logic:iterate id="bean" name="BDantocs" type="com.form.disability.categorys.FDantoc">
          treeDantoc.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInforDanToc(<%=bean.getId()%>)");
     </logic:iterate> 
     </logic:notEmpty>
</logic:present>
    document.write(treeDantoc);
</script>
 
