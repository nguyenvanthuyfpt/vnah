<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforDK(id){
        post('disabilityFuntion',anchor + ':_LISTCONDITION:id:' + id);
        messageImg('right');
    }
    dLeftDk = new dTree('dLeftDk');
    dLeftDk.add(0,-1,'<font style="font-size:11px;"><bean:message key="dieukien.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforDK(0)");
    
    <logic:present name="BTreeDieuKiens">
        <logic:notEmpty name="BTreeDieuKiens">
            <logic:iterate id="bean" name="BTreeDieuKiens" type="com.form.disability.categorys.FDieuKien">
                dLeftDk.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInforDK(<%=bean.getId()%>)");
            </logic:iterate> 
        </logic:notEmpty>
    </logic:present>
    document.write(dLeftDk);
</script>
 
