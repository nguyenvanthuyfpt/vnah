<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforNguyenNhan(id){
        post('disabilityFuntion',anchor + ':_LIST_NGUYENNHAN:id:' + id);
        messageImg('right');
    }
    
    treeDonvi = new dTree('treeDonvi');
    treeDonvi.add(0,-1,'<font style="font-size:11px;"><bean:message key="donvi.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforNguyenNhan(0)");

    <logic:present name="BNguyennhans">
        <logic:notEmpty name="BNguyennhans">
                <logic:iterate id="bean" name="BNguyennhans" type="com.form.disability.categorys.FNguyennhan">
                        treeDonvi.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInforNguyenNhan(<%=bean.getId()%>)");
                </logic:iterate> 
        </logic:notEmpty>
    </logic:present>		
    document.write(treeDonvi);
</script>
 
