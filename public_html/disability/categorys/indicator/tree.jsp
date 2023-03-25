<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforIndicator(id){
        post('disabilityFuntion',anchor + ':_LIST_INDICATOR:id:' + id);
        messageImg('right');
    }    
    treeIndicator = new dTree('treeIndicator');
    treeIndicator.add(0,-1,'<font style="font-size:11px;"><bean:message key="indicator.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforIndicator(0)");

    <logic:present name="menuIndicators">
        <logic:notEmpty name="menuIndicators">
                <logic:iterate id="bean" name="menuIndicators" type="com.form.disability.categorys.FIndicator">
                        treeIndicator.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getCode()%>',"javascript:getInforIndicator(<%=bean.getId()%>)");
                </logic:iterate> 
        </logic:notEmpty>
    </logic:present>
    document.write(treeIndicator);
</script>
 
