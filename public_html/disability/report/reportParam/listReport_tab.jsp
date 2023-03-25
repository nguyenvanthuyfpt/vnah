<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">    
    function excuteReport(anchorName){
        post('reporttotal',anchor + ':'+ anchorName);
    }
    
    function postTab(obj,params){
        if(obj.className==''){
            for(i=0;i<obj.parentNode.parentNode.childNodes.length;i++){
                if(obj.parentNode.parentNode.childNodes[i].className=='ui-tabs-selected') 
                    obj.parentNode.parentNode.childNodes[i].className='';
        }
            obj.parentNode.className='ui-tabs-selected';            
            postAjax('reporttab', 'MainCate', anchor + ':' + params);
            messageImg('MainCate');
        }
    }
</script>
<html:form action="reporttab" method="post">
<div class="padding-content">	
    <ul id="tree">
        <li>
            <div class="bgr7"><a href="javascript:excutePostCategorys('_REPORT_PARAM')">
                    B&#225;o c&#225;o t&#7893;ng h&#7907;p theo tham s&#7889;</a></div>		
        </li>
    </ul>
    
    <br/>
    <table width="100%" class="tableForm" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <th>
            <div class="content-calendar-2" align="left">
            	T&#7893;ng h&#7907;p theo tham s&#7889;
            </div>    
        </th>
    </tr>
    <tr>
        <td align="left" id="id_option_area">
            <jsp:include page="/disability/report/reportParam/option_area.jsp" />
        </td>
    </tr>
    
    <tr>
        <td>
            <div id="mailcol">
                <div class="tabmenu" id="container-1" >
                    <div style="clear:both"></div>
                    <ul id="ui-tabs-nav">
                        <li class="ui-tabs-selected">
                            <a href="#" onclick="javascrip:postTab(this,'_REPORT_DETAIL');">
                                <bean:message key="disability.tab.report.detail" bundle="<%=interfaces%>"/></a></li>
                        <li class="">
                            <a href="#" onclick="javascrip:postTab(this,'_REPORT_OVERVIEW');">
                                <bean:message key="disability.tab.report.overview" bundle="<%=interfaces%>"/></a></li>                                       
                    </ul>
                </div>
                <div id="fragment-1" class="content-report">  
                    <div class="listDocs" id="MainCate">
                        <jsp:include page="/disability/report/reportParam/form.jsp" />
                    </div>
                </div>
            </div>                
        </td>
    </tr>
    
     <!--<tr>
        <td align="left">
            <html:button property="_CREATE" styleClass="button" onclick="excuteReport('_REPORT_PARAM');">
                Xu&#7845;t excel
            </html:button>
        </td>
    </tr>-->
    	
    <tr>
        <td height="20px"></td>
    </tr>  
    </table>
</div>
</html:form>  
