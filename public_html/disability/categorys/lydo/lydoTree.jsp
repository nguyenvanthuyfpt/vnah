<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforLydo(id){
      post('disabilityFuntion',anchor + ':_LIST_LYDO:id:' + id);
      messageImg('right');
    }
      treeLydo = new dTree('treeLydo');
      treeLydo.add(0,-1,'<font style="font-size:11px;"><bean:message key="lydo.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforLydo(0)");
      <logic:present name="BLydos">
<logic:notEmpty name="BLydos">
      <logic:iterate id="bean" name="BLydos" type="com.form.disability.categorys.FLydo">
          treeLydo.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInforLydo(<%=bean.getId()%>)");
     </logic:iterate> 
     </logic:notEmpty>
</logic:present>
    document.write(treeLydo);
</script>
 
