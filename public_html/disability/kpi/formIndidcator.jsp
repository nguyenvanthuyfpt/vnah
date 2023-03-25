<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInputData(objId, indId, type, year){
        //alert(year);
        post('disabilityFuntion',anchor + ':_PREPARED_CREATE_KPI:objId:' +objId+':indId:'+indId+':inputType:'+type+':yearReport:'+year);
        messageImg('right');
    }
</script>  

<html:form action="kpi" method="post">
<html:hidden name="kpi" property="objId"/>	
<div>
    <ul id="tree">
        <li class="">
            <div class="bgr5"><a href="#"><bean:message key="kpi.title.caption" bundle="<%=interfaces%>"/></a></div>
        </li>	
    </ul>
</div>
<br/>	  
<div class="content-calendar">
    <div class="padding-content">                
        <logic:notEmpty name="kpi" property="code">
        <div class="pd-5">
            <div class="obj">
                <b><bean:message key="common.label.year.report" bundle="<%=interfaces%>"/></b>
                <html:select styleClass="inputbox" name="kpi" property="yearReport" onchange="javascript:postAjax('kpi', 'id_obj_ind', anchor + ':_CHANGE_YEAR');" >
                    <html:option value="2016">2016</html:option>
                    <html:option value="2017">2017</html:option>
                    <html:option value="2018">2018</html:option>
                    <html:option value="2019">2019</html:option>
                    <html:option value="2020">2020</html:option>
                    <html:option value="2021">2021</html:option>
                    <html:option value="2022">2022</html:option>
                </html:select><br/><br/>
                <b><bean:write name="kpi" property="code" /></b> :
                <bean:write name="kpi" property="name" /><br/><br/>
                <i><bean:write name="kpi" property="description" /></i>
            </div>
        </div>
        <div id="id_obj_ind">
            <jsp:include page="/disability/kpi/listIndicator.jsp"/>
        </div>
        </logic:notEmpty>
        
        <logic:empty name="kpi" property="code">
            <jsp:include page="/disability/kpi/listIntro.jsp"/>
        </logic:empty>
    </div>
</div>
</html:form>