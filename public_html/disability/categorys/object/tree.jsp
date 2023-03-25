<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforObject(id){
        post('disabilityFuntion',anchor + ':_LIST_OBJECT:id:' + id);
        messageImg('right');
    }    
    treeObject = new dTree('treeObject');
    treeObject.add(0,-1,'<font style="font-size:11px;"><bean:message key="object.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforObject(0)");

    <logic:present name="menuObjects">
        <logic:notEmpty name="menuObjects">
                <logic:iterate id="bean" name="menuObjects" type="com.form.disability.categorys.FObject">
                        treeObject.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInforObject(<%=bean.getId()%>)");
                </logic:iterate> 
        </logic:notEmpty>
    </logic:present>
    document.write(treeObject);
</script>
 
