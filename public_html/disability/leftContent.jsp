<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
    function excutePost(anchorName){
        postAjax('disabilityFuntion','right',anchor + ':'+ anchorName);
        messageImg('right');
    }
    
    function excutePostCategorys(anchorName,funcName){
        if (funcName=='_PREPARED_SELECT_KPI'){
            post('disabilityFuntion',anchor + ':'+ anchorName+':func:'+funcName+':yearReport:'+year_proj+':objId:0');
        } else {
            post('disabilityFuntion',anchor + ':'+ anchorName+':func:'+funcName+':yearReport:'+year_proj);
        }        
        messageImg('right');
    }
    
    function optionChose(index,idTinh){    
        post('disabilityFuntion',anchor +':_VIEW_OPTION:level:'+index+':tinhId:'+idTinh);
        messageImg('right');
    }
    
    function selectArea(index, idTinh, form){
        post(''+form+'',anchor +':_VIEW_OPTION_INFO:level:'+index+':tinhId:'+idTinh);
        messageImg('right');
    }	

    /*window.onload=function(){        
        hideshow('div_search','true');
        hideshow('div_report','true');
    }*/
</script>

<html:form action="dis_detail" method="post"/>
<%
    String func = (String)request.getAttribute("anchor");
    String sub_func = (String)request.getAttribute("subanchor");
    String div_report = (String)request.getAttribute("div_report");
    String div_search = (String)request.getAttribute("div_search");
%>
<ul id="tree">	
    <li>
        <div class="bgr1">    		 		
            <a href="javascript:excutePostCategorys('_PREPARED_SELECT_KPI','_PREPARED_SELECT_KPI')">
                <bean:message key="common.label.function.input" bundle="<%=interfaces%>"/></a></div>                
                <ul <%="01".equals(func)?"":"style='display:none;'"%>>                  
                    <jsp:include page="/disability/kpi/tree.jsp" /> 	    
                </ul>
        </li>    
	
    			
    <li>
    	<div class="bgr2">
            <a href="javascript:excutePostCategorys('_SEARCH')">
                <bean:message key="common.label.function.search" bundle="<%=interfaces%>"/></a></div>
                <ul <%="02".equals(func)?"":"style='display:none;'"%> id="searchId">
                    <li><a <%="02.01".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_SEARCH')" >
                        <bean:message key="common.label.function.search.param" bundle="<%=interfaces%>"/></a></li>
                </ul>        
    </li>
    
    <li>
        <div class="bgr9">
            <a href="javascript:excutePostCategorys('_REPORT_KPI','_REPORT_EXPORT_2020');">
                <bean:message key="common.label.function.report.export" bundle="<%=interfaces%>" /></a></div>
                <ul <%="03".equals(func)?"":"style='display:none;'"%> id="searchId">                   
                    <li><a <%="03.02".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_REPORT_KPI','_REPORT_EXPORT_2020');" >
                        <bean:message key="common.label.function.report.export.2020" bundle="<%=interfaces%>" /></a></li>
                </ul> 
    </li>

    <li>
        <div class="bgr10">
            <a href="javascript:excutePostCategorys('_IMPORT_DATA','_IMPORT_DATA');">
                <bean:message key="common.label.function.import" bundle="<%=interfaces%>" /></a></div>
    </li>
    
    <li>
        <div class="bgr7">
            <a title="B&#225;o c&#225;o" href="javascript:excutePostCategorys('_REPORT_KPI','_REPORT_INDICATOR')">
                <bean:message key="common.label.function.report" bundle="<%=interfaces%>"/></a></div> 
            <ul <%="04".equals(func)?"":"style='display:none;'"%>>                
                <li><a <%="04.01".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_REPORT_KPI','_REPORT_INDICATOR');">
                    <bean:message key="common.label.function.report.indicator" bundle="<%=interfaces%>"/></a></li>
                    
                <!--<li><a <%="04.02".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_REPORT_KPI','_REPORT_OBJECT');">
                    <bean:message key="common.label.function.report.object" bundle="<%=interfaces%>"/></a></li>-->
                                    
               <li><a <%="04.04".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_REPORT_KPI','_REPORT_SUPPORT');">
                    <bean:message key="common.label.function.report.support" bundle="<%=interfaces%>"/></a></li>
                
                <li><a <%="04.05".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_REPORT_KPI','_REPORT_COMMUNE');">
                    <bean:message key="common.label.function.report.commune" bundle="<%=interfaces%>" /></a></li>

                <li><a <%="04.06".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_REPORT_KPI','_REPORT_EXPORT');" >
                    <bean:message key="common.label.function.report.support-list" bundle="<%=interfaces%>" /></a></li>
                    
                <li><a <%="04.07".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_REPORT_KPI','_REPORT_SUPPORT_LIST');" >
                    <bean:message key="common.label.function.report.dis-support-list" bundle="<%=interfaces%>" /></a></li>
                
                <li><a <%="04.08".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_REPORT_KPI','_REPORT_HOMEVISIT_LIST');" >
                    <bean:message key="common.label.function.report.dis-homevisit-list" bundle="<%=interfaces%>" /></a></li>
                                                  
                <!--<li><a <%="04.06".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_REPORT_KPI','_REPORT_EXPORT');">
                    <bean:message key="common.label.function.report.export" bundle="<%=interfaces%>" /></a></li>-->
                    
                
                <!--<li><a <%="04.04".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_REPORT_COMMUNE','_REPORT_COMMUNE');">
                    T&#7893;ng h&#7907;p theo C&#225;c Tuy&#7871;n</a></li>-->

                <!--<li><a <%="04.04".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_REPORT_COLLECT_DATA','_REPORT_COMMUNE');">
                    T&#7893;ng h&#7907;p nhu c&#7847;u NKT</a></li>-->
                 
                <!--<li><a <%="04.05".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_REPORT_STATISTICS_DATA','_REPORT_COMMUNE');" 
                    title="Ph&#7909; l&#7909;c 2: Danh s&#225;ch NKT h&#432;&#7903;ng l&#7907;i &#273;&#432;&#7907;c th&#7889;ng k&#234; trong th&#225;ng">
                    Ph&#7909; l&#7909;c 2: Danh s&#225;ch NKT h&#432;&#7903;ng l&#7907;i &#273;&#432;&#7907;c th&#7889;ng k&#234;</a></li>-->
                    
                <!--<li><a <%="04.06".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_REPORT_ANALYSIS_DATA','_REPORT_COMMUNE');" title="Ph&#226;n t&#237;ch s&#7889; li&#7879;u NKT theo Tuy&#7871;n">
                    Ph&#226;n t&#237;ch s&#7889; li&#7879;u NKT</a></li>-->
            </ul>
    </li>
    
    <li>
    	<div class="bgr8">
            <a href="javascript:excutePostCategorys('_CHART','_CHART_DIS')">
                <bean:message key="common.label.function.chart" bundle="<%=interfaces%>"/></a></div>           
            <ul <%="08".equals(func)?"":"style='display:none;'"%>>                
                <li><a <%="08.01".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_CHART','_CHART_DIS');">
                    <bean:message key="common.label.function.chart.dis" bundle="<%=interfaces%>"/></a></li>
                
                <li><a <%="08.02".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_CHART','_CHART_PERSON');">
                    <bean:message key="common.label.function.chart.person" bundle="<%=interfaces%>"/></a></li>
                
                <li><a <%="08.03".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_CHART','_CHART_HOURS');">
                    <bean:message key="common.label.function.chart.hours" bundle="<%=interfaces%>"/></a></li>
                
                <li><a <%="08.04".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_CHART','_CHART_EVENT');">
                    <bean:message key="common.label.function.chart.event" bundle="<%=interfaces%>"/></a></li>
                
                <!--<li><a <%="08.05".equals(sub_func)?"class='select'":""%> href="javascript:excutePostCategorys('_REPORT_KPI','_REPORT_INDICATOR');">
                    <bean:message key="common.label.function.report.indicator" bundle="<%=interfaces%>"/></a></li>-->
            </ul>            
    </li>
    			
    <%if(me.isRole(com.inf.IRoles.rDIS_CATEGORY)){%>
    <li class=""><div class="bgr6"><a href="#"><bean:message key="menu.left.category" bundle="<%=interfaces%>"/></a></div>
        <div>
            <div class="tdTree" align="left"><jsp:include page="/disability/categorys/tinh/tinhTree.jsp" /></div>
            <div class="tdTree" align="left"><jsp:include page="/disability/categorys/object/tree.jsp" /></div>
            <div class="tdTree" align="left"><jsp:include page="/disability/categorys/indicator/tree.jsp" /></div>
            <div class="tdTree" align="left"><jsp:include page="/disability/categorys/rank/tree.jsp" /></div>
            <div class="tdTree" align="left"><jsp:include page="/disability/categorys/event/tree.jsp" /></div>
            <div class="tdTree" align="left"><jsp:include page="/disability/categorys/dangtat/dangTatTree.jsp" /></div>
            <div class="tdTree" align="left"><jsp:include page="/disability/categorys/dieukien/dieuKienTree.jsp" /></div>
            <div class="tdTree" align="left"><jsp:include page="/disability/categorys/hotro/hotroTree.jsp" /></div>
            <!--<div class="tdTree" align="left"><jsp:include page="/disability/categorys/danhgia/danhgiaTree.jsp" /></div>-->
            <div class="tdTree" align="left"><jsp:include page="/disability/categorys/nguonhotro/tree.jsp" /></div>
            <div class="tdTree" align="left"><jsp:include page="/disability/categorys/nguyennhan/tree.jsp" /></div>
            <div class="tdTree" align="left"><jsp:include page="/disability/categorys/dantoc/tree.jsp" /></div>
            <div class="tdTree" align="left"><jsp:include page="/disability/categorys/mucdo/tree.jsp" /></div>
            <!--<div class="tdTree" align="left"><jsp:include page="/disability/categorys/doituong/tree.jsp" /></div>-->
        </div>
    </li>
    <%}%>
</ul> 
