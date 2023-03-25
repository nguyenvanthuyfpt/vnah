<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforTinh(id){
        post('disabilityFuntion',anchor + ':_LISTDISTRICT:id:' + id);
        messageImg('right');
    }
	
    dLeftTinh = new dTree('dLeftTinh');
    dLeftTinh.add(0,-1,'<font style="font-size:11px;"><bean:message key="location" bundle="<%=interfaces%>"/></font>',"javascript:getInforTinh(0)");
    <logic:present name="BTreeAllTinhs">
        <logic:notEmpty name="BTreeAllTinhs">
            <logic:iterate id="bean" name="BTreeAllTinhs" type="com.form.disability.categorys.FTinh">
                dLeftTinh.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName().replace("--- ","")%>',"javascript:getInforTinh(<%=bean.getId()%>)");
            </logic:iterate> 
        </logic:notEmpty>
    </logic:present>
    document.write(dLeftTinh);
</script>