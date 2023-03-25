<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforDoiTuong(id){
        post('disabilityFuntion',anchor + ':_LIST_DOITUONG:id:' + id);
        messageImg('right');
    }
    
    treeDoiTuong = new dTree('treeDoiTuong');
    treeDoiTuong.add(0,-1,'<font style="font-size:11px;"><bean:message key="doituong.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforDoiTuong(0)");

    <logic:present name="BDoiTuongs">
        <logic:notEmpty name="BDoiTuongs">
                <logic:iterate id="bean" name="BDoiTuongs" type="com.form.disability.categorys.FDoiTuong">
                        treeDoiTuong.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInforDoiTuong(<%=bean.getId()%>)");
                </logic:iterate> 
        </logic:notEmpty>
    </logic:present>		
    document.write(treeDoiTuong);
</script>
 
