<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforDungcu(id){
      post('disabilityFuntion',anchor + ':_LIST_DUNGCU:id:' + id);
      messageImg('right');
    }
      dLeftDc = new dTree('dLeftDc');
      dLeftDc.add(0,-1,'<font style="font-size:11px;"><bean:message key="dungcu.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforDungcu(0)");
      <logic:present name="BDungcus">
<logic:notEmpty name="BDungcus">
      <logic:iterate id="bean" name="BDungcus" type="com.form.disability.categorys.FDungcu">
          dLeftDc.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInforDungcu(<%=bean.getId()%>)");
     </logic:iterate> 
     </logic:notEmpty>
</logic:present>
    document.write(dLeftDc);
</script>
 
