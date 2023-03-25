<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%
    String params = anchor + ":_VIEW";
%>

<bean:define name="kpi" property="type"         id="type"       type="java.lang.Integer" />
<bean:define name="kpi" property="nktId"        id="nktId"      type="java.lang.Integer" />
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

<logic:present name="kpi">
<div class="pd-5">
    <div class="obj">        
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

<logic:equal value="1" name="kpi" property="typeSel">
    <jsp:include page="/disability/kpi/selectEvent.jsp"/>
</logic:equal>

<logic:equal value="2" name="kpi" property="typeSel">
<div id="div_person">
    <jsp:include page="/disability/kpi/selectPerson.jsp"/>
</div>
</logic:equal>
</logic:present>
