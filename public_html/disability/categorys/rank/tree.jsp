<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforRank(id){
        post('disabilityFuntion',anchor + ':_LIST_RANK:id:' + id);
        messageImg('right');
    }    
    treeRank = new dTree('treeRank');
    treeRank.add(0,-1,'<font style="font-size:11px;"><bean:message key="rank.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforRank(0)");

    <logic:present name="menuRanks">
        <logic:notEmpty name="menuRanks">
                <logic:iterate id="bean" name="menuRanks" type="com.form.disability.categorys.FRank">
                        treeRank.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInforRank(<%=bean.getId()%>)");
                </logic:iterate> 
        </logic:notEmpty>
    </logic:present>
    document.write(treeRank);
</script>
 
