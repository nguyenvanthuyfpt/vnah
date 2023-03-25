<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforNguonHoTro(id){
        post('disabilityFuntion',anchor + ':_LIST_NGUONHOTRO:id:' + id);
        messageImg('right');
    }
    
    treeNguonhotro = new dTree('treeNguonhotro');
    treeNguonhotro.add(0,-1,'<font style="font-size:11px;"><bean:message key="nguonhotro.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforNguonHoTro(0)");

    <logic:present name="BNguonhotros">
        <logic:notEmpty name="BNguonhotros">
                <logic:iterate id="bean" name="BNguonhotros" type="com.form.disability.categorys.FNguonhotro">
                        treeNguonhotro.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInforNguonHoTro(<%=bean.getId()%>)");
                </logic:iterate> 
        </logic:notEmpty>
    </logic:present>		
    document.write(treeNguonhotro);
</script>
 
