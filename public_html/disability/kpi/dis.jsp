<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<bean:define name="kpi" property="totalDis"     id="totalCountDown"       type="java.lang.Integer" />
<bean:define name="kpi" property="totalDis"     id="totalCountDownLate"   type="java.lang.Integer" />
<bean:define name="kpi" property="nktId"        id="nktId"                type="java.lang.Integer" />
<%  
    String strNkt = String.valueOf(nktId);
    String countDown = String.valueOf(totalCountDown);
    String countDownLate = String.valueOf(totalCountDownLate);
    //out.println(strNkt);
%>

<logic:present name="listCountDown" >
<div class="ind">
    Hi&#7879;n c&#243; <b><%=countDown%></b> NKT &#273;&#7871;n h&#7865;n t&#225;i kh&#225;m t&#237;nh t&#7915; ng&#224;y hi&#7879;n t&#7841;i, danh s&#225;ch 
    <a href="javascript:openWindow('kpi',anchor +':_POPUP_DIS_COUNTDOWN','','',1050,600);remove('kpi',anchor);"><u>chi ti&#7871;t</u></a>
</div>
<!--showWindow(winName, w, h)
<div class="ind">
    Hi&#7879;n c&#243; <b><%=countDownLate%></b> NKT qu&#225; h&#7841;n t&#225;i kh&#225;m t&#237;nh t&#7915; ng&#224;y hi&#7879;n t&#7841;i, danh s&#225;ch <a href="javascript:openWindow('kpi',anchor +':_POPUP_DIS_COUNTDOWN');remove('kpi',anchor);"><u>chi ti&#7871;t</u></a>
</div>-->
</logic:present>  

<div id="mailcol">
  <div class="tabmenu" id="container-1" >
      <div style="clear:both"></div>
          <ul id="ui-tabs-nav">
              <li id="li_1" class="ui-tabs-selected" >                                        
                  <a href="#" onclick="javascrip:postTab(this,'_INFORMATION');" >
                      <bean:message key="disability.tab.thongtinchung" bundle="<%=interfaces%>"/></a></li>
              <li id="li_2">
                  <a href="#" onclick="javascrip:postTab(this,'_PHANLOAI');">
                      <bean:message key="disability.tab.plkhuyettat" bundle="<%=interfaces%>"/></a></li>
              <li id="li_3">
                  <a href="#" onclick="javascrip:postTab(this,'_HOTRO:tabId:0');">
                      <bean:message key="disability.tab.nchotro" bundle="<%=interfaces%>"/></a></li>
              <li id="li_4">
                  <a href="#" onclick="javascrip:postTab(this,'_HOTRO:tabId:1');">
                      <bean:message key="disability.tab.hotrodn" bundle="<%=interfaces%>"/></a></li>
              <li id="li_5">
                  <a href="#" onclick="javascrip:postTab(this,'_COMMUNE');">
                      <bean:message key="disability.tab.commune" bundle="<%=interfaces%>"/></a>
                  </li>
              <li id="li_6">
                  <a href="#" onclick="javascrip:postTab(this,'_RANK');">
                      <bean:message key="disability.tab.kqphcn" bundle="<%=interfaces%>"/></a></li>                      
              <li id="li_7">
                  <a href="#" onclick="javascrip:postTab(this,'_PROFILE');">
                      <bean:message key="disability.tab.profile" bundle="<%=interfaces%>"/></a></li>
          </ul>
   </div>
  
   <div id="fragment-1" class="content-calendar-overflow">  
        <div class="listDocs" id="MainCate"> 
            <jsp:include page="/disability/kpi/formDis.jsp" />
        </div>
    </div>
</div>