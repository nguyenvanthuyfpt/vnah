<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<script type="text/javascript"> 
    function getInforIndicatorKpi(id,type,year){
        if (type=='0') {
            post('disabilityFuntion',anchor + ':_PREPARED_SELECT_KPI:objId:' + id+':yearReport:'+year);            
        } else {            
            post('disabilityFuntion',anchor + ':_PREPARED_CREATE_KPI:objId:' + id+':indId:0');
        }
        messageImg('right');
    }
    
    function checkSubmit(form){
        if(form.eventId.value==''){
            alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
            return false;
        }
        return true;
    }
    
    function enableElement(objHS, obj, checked) {
        if (checked) {
            postAjax('kpi','MainCate',anchor + ':_CHANGE_COMMUNE:rptHuongCanThiep:1');
        } else {
            postAjax('kpi','MainCate',anchor + ':_CHANGE_COMMUNE:rptHuongCanThiep:0');
        }     
    }
    
    function hideshowTxt(id, value) {
      if (value) {
          document.getElementsByName('rptCanThiep').value = "";
          getObj(id).style.display = (value) ? '' : 'none';
      }
      else {
          document.getElementsByName('rptCanThiep').value = "";
          var display = getObj(id).style.display;
          getObj(id).style.display = (display == '' || display == '') ? 'none' : '';
      }
  }
</script>

<bean:define name="kpi" property="type"         id="type"       type="java.lang.Integer" />
<bean:define name="kpi" property="lvl"          id="lvl"        type="java.lang.Integer" />
<bean:define name="kpi" property="objId"        id="objId"      type="java.lang.Integer" />
<bean:define name="kpi" property="indId"        id="indId"      type="java.lang.Integer" />
<bean:define name="kpi" property="yearReport"   id="year"       type="java.lang.Integer" />

<%
    String level = "100"; 
    String strType = String.valueOf(type);
    String strYear = String.valueOf(year); 
    boolean readonly = "3".equals(strType)?true:false; 
    
%>

<div class="content-calendar">
    <div class="padding-content">     
        <logic:present name="kpi">  
            <div class="pd-5">
                <div class="obj">
                    <b><bean:message key="common.label.year.report" bundle="<%=interfaces%>"/></b>
                    <html:select styleClass="inputbox" styleId="yearReport" name="kpi" property="yearReport">
                        <html:option value="2016">2016</html:option>
                        <html:option value="2017">2017</html:option>
                        <html:option value="2018">2018</html:option>
                        <html:option value="2019">2019</html:option>
                        <html:option value="2020">2020</html:option>                        
                         <html:option value="2021">2021</html:option>
                         <html:option value="2022">2022</html:option>
                         <html:option value="2023">2023</html:option>
                         <html:option value="2024">2024</html:option>
                         <html:option value="2025">2025</html:option>
                    </html:select><br/><br/>
                    
                    <b><bean:write name="kpi" property="objCode" /></b> :
                    <%if(!"1".equals(strType)){%>
                    <a href="#" onclick="getInforIndicatorKpi('<%=objId%>','0','<%=strYear%>');">
                        <bean:write name="kpi" property="objName" />
                    </a>
                    <%}else{%>
                        <bean:write name="kpi" property="objName" />  
                    <%}%>
                    <br/><br/><i><bean:write name="kpi" property="objDesc" /></i>
                </div>
                <%if(!"1".equals(strType)){%>
                <div class="ind"><b><bean:write name="kpi" property="code" /></b> : 
                    <bean:write name="kpi" property="name" />
                </div>
                <%}%>
            </div>
                                    
            <!-- INPUT VALUE -->
            <logic:equal value="0" name="kpi" property="type">
                <jsp:include page="/disability/kpi/inputValue.jsp"/>
            </logic:equal>
            
            <!-- INPUT DISABILITY -->
            <logic:equal value="1" name="kpi" property="type">
                <jsp:include page="/disability/kpi/inputDis.jsp"/>
            </logic:equal>
            
            <!-- INPUT PERSON -->
            <%if("2.3".indexOf(strType)>-1){%>
                <jsp:include page="/disability/kpi/inputPerson.jsp"/>                         
            <%}%>
        </logic:present>          
   </div>   
</div>

