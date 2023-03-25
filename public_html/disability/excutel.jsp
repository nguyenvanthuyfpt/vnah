<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
	
<html:form action="disabilityTab" method="POST" />
<script type="text/javascript">   
    function checkSubmitRelative(form){
        if(form.ma.value=='' || form.nkt.value==''){
            alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
            return false;
        }
        return true;
    }

    function checkSubmitBKT(form){
        if(form.ma.value=='' || form.nkt.value=='' || form.ngaySinh.value=='' || form.dateLastUpdate.value==''){
            alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
            return false;
        }
        return true;
    }
    
    function checkSubmitReport(form){
        if(form.ma.value=='' || form.nkt.value=='' || form.ngaySinh.value=='' || form.dateLastUpdate.value==''){
            alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
            return false;
        }
        return true;
    }

    function checkSubmitSupport(form){
        if(form.hotroIds.value.indexOf('#0#'))
            if(form.hotroIds.value=='' || form.dateCreate.value==''){
                alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
                return false;
            }
        return true;
    }
    function postTab(obj,params){
        if(obj.className==''){
            for(i=0;i<obj.parentNode.parentNode.childNodes.length;i++){
                    if(obj.parentNode.parentNode.childNodes[i].className=='ui-tabs-selected') obj.parentNode.parentNode.childNodes[i].className='';
            }
            obj.parentNode.className='ui-tabs-selected';
            postAjax('disabilityTab','MainCate',anchor + ':' + params);
            messageImg('MainCate');
        }
    }
    
    function postTabSub(position,obj,params){
        if(obj.className==''){
            for(i=0;i<obj.parentNode.parentNode.childNodes.length;i++){
                    if(obj.parentNode.parentNode.childNodes[i].className=='ui-tabs-selected') obj.parentNode.parentNode.childNodes[i].className='';
            }
            obj.parentNode.className='ui-tabs-selected';
            postAjax('disabilityTab',position,anchor + ':' + params);
            messageImg('idrank');
        }
    }
</script>

<bean:define name="disability" property="id" id="id" type="java.lang.Integer" />

<div id="mailcol">
    <div class="tabmenu" id="container-1" >
        <div style="clear:both"></div>
            <ul id="ui-tabs-nav">
                <li class="ui-tabs-selected">
                    <a href="#" onclick="javascrip:postTab(this,'_INFORMATION:id:<%=id%>');" onmouseover="Tip('Th&#244;ng tin chung NKT',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"><bean:message key="disability.tab.thongtinchung" bundle="<%=interfaces%>"/></a></li>
                <li class="">
                    <a href="#" onclick="javascrip:postTab(this,'_PHANLOAI:id:<%=id%>');" onmouseover="Tip('Ph&#226;n lo&#7841;i khuy&#7871;t t&#7853;t NKT',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"><bean:message key="disability.tab.plkhuyettat" bundle="<%=interfaces%>"/></a></li>
                <li class="">
                    <a href="#" onclick="javascrip:postTab(this,'_HOTRO:id:<%=id%>:type:0');" onmouseover="Tip('Nhu c&#7847;u h&#7895; tr&#7907; NKT c&#7847;n',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"><bean:message key="disability.tab.nchotro" bundle="<%=interfaces%>"/></a></li>
                <li class="">
                    <a href="#" onclick="javascrip:postTab(this,'_HOTRO:id:<%=id%>:type:1');" onmouseover="Tip('H&#7895; tr&#7907; NKT &#273;&#227; nh&#7853;n ',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()">H&#7895; tr&#7907; &#273;&#227; nh&#7853;n</a></li>
                <li class="">
                    <a href="#" onclick="javascrip:postTab(this,'_TINHHINH:id:<%=id%>');" onmouseover="Tip('K&#7871;t qu&#7843; th&#7921;c hi&#7879;n k&#7871; ho&#7841;ch h&#7895; tr&#7907; c&#225; nh&#226;n',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()">K&#7871;t qu&#7843; &#273;&#225;nh gi&#225;</a></li>
                    <!--<a href="#" onclick="javascrip:postTab(this,'_TINHHINH:id:<%=id%>');"></a>-->
                    
                    
            </ul>
     </div>

    <div id="fragment-1" class="content-calendar-overflow">  
        <div class="listDocs" id="MainCate"> 
            <jsp:include page="/disability/form.jsp" />
        </div>
    </div>
</div> 
