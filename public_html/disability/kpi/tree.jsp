<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInforIndicatorKpi(id,type){       
        if (type=='0') {
            post('disabilityFuntion',anchor + ':_PREPARED_SELECT_KPI:objId:' + id+':yearReport:'+year_proj);
        } else {
            post('disabilityFuntion',anchor + ':_PREPARED_CREATE_MENU_KPI:objId:' + id+':indId:0:dtlId:0:yearReport:'+year_proj);
        }
        messageImg('right');
    }
    treeIndicatorKpi = new dTree('treeIndicatorKpi');
    treeIndicatorKpi.add(0,-1,'<font style="font-size:11px;"><bean:message key="indicator.title.caption" bundle="<%=interfaces%>"/></font>',"javascript:getInforIndicatorKpi(0)");

    <logic:present name="menuObjectInput">
        <logic:notEmpty name="menuObjectInput">
                <logic:iterate id="bean" name="menuObjectInput" type="com.form.disability.categorys.FObject">
                        treeIndicatorKpi.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInforIndicatorKpi(<%=bean.getId()%>,<%=bean.getType()%>)");
                </logic:iterate> 
        </logic:notEmpty>
    </logic:present>
    document.write(treeIndicatorKpi);
</script>

 
