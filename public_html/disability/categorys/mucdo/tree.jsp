<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforMucdo(id){
        post('disabilityFuntion',anchor + ':_LIST_MUCDO:id:' + id);
        messageImg('right');
    }
        
    treeMucdo = new dTree('treeMucdo');
    treeMucdo.add(0,-1,'<font style="font-size:11px;"><bean:message key="mucdo.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforMucdo(0)");
    
    <logic:present name="BMucdos">
        <logic:notEmpty name="BMucdos">
            <logic:iterate id="bean" name="BMucdos" type="com.form.disability.categorys.FMucdo">
                treeMucdo.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInforMucdo(<%=bean.getId()%>)");
            </logic:iterate> 
        </logic:notEmpty>
    </logic:present>
    document.write(treeMucdo);
</script>
 
