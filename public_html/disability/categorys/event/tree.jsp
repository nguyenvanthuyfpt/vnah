<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforEvent(id, code){
        post('disabilityFuntion',anchor + ':_LIST_EVENT:id:' + id + ':code:' + code);
        messageImg('right');
    }    
    treeEvent = new dTree('treeEvent');
    treeEvent.add(0,-1,'<font style="font-size:11px;"><bean:message key="event.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforEvent(0)");

    <logic:present name="menuEvents">
        <logic:notEmpty name="menuEvents">
                <logic:iterate id="bean" name="menuEvents" type="com.form.disability.categorys.FEvent">
                        treeEvent.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName() %>',"javascript:getInforEvent('<%=bean.getId()%>','<%=bean.getCode()%>')");
                </logic:iterate> 
        </logic:notEmpty>
    </logic:present>
    document.write(treeEvent);
</script>
 
