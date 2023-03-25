<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%
    java.util.Map<String, Object> mapObject = (java.util.Map<String, Object>) request.getAttribute("mapRanks");    
%>

<html:hidden name="kpi" property="nktId"/>
<bean:define name="kpi" property="createDate" id="rankCreate" type="java.lang.String" />
<bean:define name="kpi" property="hdrCreateDate" id="hdrRankCreate" type="java.lang.String" />
<bean:define name="kpi" property="rankInitDate" id="rankInitDate" type="java.lang.String" />
<bean:define name="kpi" property="dtlId" id="curDtlId" type="java.lang.Integer" />
<bean:define name="kpi" property="curResult" id="curRankResult" type="java.lang.Integer" />
<bean:define name="kpi" property="rankNum" id="rankNum" type="java.lang.Integer" />
<bean:define name="kpi" property="rankHas" id="rankHas" type="java.lang.String" />
<bean:define name="kpi" property="nktId" id="curNktId" type="java.lang.Integer"/>

<script type="text/javascript">
  function checkedResult(obj) {
      alert(obj.value);
  }
</script>
<%  
    String hdrCreateDate = hdrRankCreate;
    String rankCreateDate = rankCreate;
    String initDate = rankInitDate;
    int rankCurDtlId = curDtlId;
    int rankCurResult = curRankResult;
    int disId = curNktId;
    String rankHasCheck = rankHas;
%>

<div align="left" class="disName">
    <strong>H&#7885; v&#224; t&#234;n NKT: <bean:write name="kpi" property="disName"/></strong>            
</div>

<div class="rank">
  <span>
      <bean:message key="common.label.rank.note" bundle="<%=interfaces%>"/>
  </span>
  <ul>
      <li><bean:message key="common.label.rank.contraint" bundle="<%=interfaces%>"/></li>
      <logic:notEqual name="kpi" property="notifyInit" value="">
          <li><bean:write name="kpi" property="notifyInit" />, <a href="#" onClick="postAjax('kpi','MainCate', anchor + ':_PRE_CRUD_RANK:reRank:0')">th&#7921;c hi&#7879;n &#273;&#225;nh gi&#225;</a></li>
      </logic:notEqual>
      <logic:notEqual name="kpi" property="notifyNext" value="">
          <li><bean:write name="kpi" property="notifyNext" />, <a href="#" onClick="postAjax('kpi','MainCate', anchor + ':_PRE_CRUD_RE_RANK:reRank:1')">th&#7921;c hi&#7879;n t&#225;i &#273;&#225;nh gi&#225;</a></li>
      </logic:notEqual>
  </ul>
</div>

<div>
    <logic:notEmpty name="ListRanks">
		<table class="list-voffice" cellpadding="2" width="100%" align="center" cellspacing="2" border="0">    
    <TR>               
        <TH width="10px" align="center">#</TH>            
        <TH>L&#7847;n &#273;&#225;nh gi&#225;</TH>
        <TH>Ng&#224;y &#273;&#225;nh gi&#225;</TH>
        <TH>S&#7889; ch&#7881; ti&#234;u &#273;&#227; &#272;G</TH>
        <TH>T&#7927; l&#7879; ti&#7871;n b&#7897;</TH>
        <TH></TH>
    </TR>  
    <logic:present name="ListRanks">
      <bean:define name="ListRanks" id="beans" type="com.form.FBeans" />        
        <%  
            int subSTT = 1;
            int STT = 1;
            String strSTT = "";
        %>
        <logic:iterate id="bean" name="ListRanks" type= "com.form.disability.categorys.FRank">
         <%  
              int i = beans.getFirstRecord();
              int size = beans.size();              
         %>
        <tr class="<%=(subSTT%2==0)?"content":"content1"%>">
          <td width="5%"><%=STT++%></td>
          <%
              int num = bean.getRankNum();
              String lblNumber = "&#272;&#225;nh gi&#225; ban &#273;&#7847;u";
              if (num>1){
                  lblNumber = "L&#7847;n " + String.valueOf(num-1);
              }
              String styleClass = "";
              if(hdrCreateDate.equals(bean.getRankDate())){
                  styleClass = "font-weight:bold;color:red;";
              }
              int reRank = (num>1)?1:0;             
          %>
          <td><a href="#">
                <span style="<%=styleClass%>"><%=lblNumber%></span></a>
          </td>
          <td><a href="#">
                  <span style="<%=styleClass%>"><%=bean.getRankDate()%></span>
              </a>
          </td>
          <td><span style="<%=styleClass%>"><%=bean.getNumRanked()%></span></td>
          <td><span style="<%=styleClass%>"><%=bean.getPercent()%></span></td> 
          <td>
              <img style="border:0px" src="<%=contextPath%>/images/view_rank.png" title="Xem chi ti&#7871;t l&#7847;n &#273;&#225;nh gi&#225;" 
                      onClick="postAjax('kpi','MainCate',anchor + ':_PRE_CRUD_RANK:createDate:<%=bean.getRankDate()%>:reRank:<%=reRank%>:hdrCreateDate:<%=bean.getRankDate()%>');">
                      
              <img style="border:0px" src="<%=contextPath%>/images/view_ranks.png" title="&#272;&#7889;i chi&#7871;u v&#7899;i &#273;&#225;nh gi&#225; ban &#273;&#7847;u" 
                      onClick="postAjax('kpi','MainCate', anchor + ':_DETAIL_RANK:rankCreateDate:<%=bean.getRankDate()%>:hdrCreateDate:<%=bean.getRankDate()%>:rankNum:<%=num-1%>');">
              
              <%if((size==1) && "32/32".equals(bean.getNumRanked())){%>
              <img style="border:0px" src="<%=contextPath%>/images/add.png" title="T&#225;i &#273;&#225;nh gi&#225;" 
                      onClick="postAjax('kpi','MainCate', anchor + ':_PRE_CRUD_RE_RANK:reRank:1')">
              <%} else if (num==size){ %>
              <img style="border:0px" src="<%=contextPath%>/images/add.png" title="T&#225;i &#273;&#225;nh gi&#225;" 
                      onClick="postAjax('kpi','MainCate', anchor + ':_PRE_CRUD_RE_RANK:reRank:1')">
              <%}%>
          </td>
        </tr>
        </logic:iterate>
    </logic:present>
  </table>
  </logic:notEmpty>
</div>

<logic:equal name="kpi" property="mode" value="CRUD_RANK">
    <table width="100%" class="tableForm">
    <logic:greaterThan value="0" name="kpi" property="rankId">
    <tr>
        <td colspan="2"><b><bean:write name="kpi" property="breadcrumb" /></b></td>
    </tr>
    </logic:greaterThan>
    
    <tr>
        <td align="left" width="25%">Ng&#224;y &#273;&#225;nh gi&#225;</td>
        <td>
            <input type="text" name="rankCreateDate" id="rankCreateDate" 
                onkeypress="return formatDate(event,this);" 
                onblur="isDate(this);" style="width:80px;" 
                class="textfield_date"
                value="<bean:write name="kpi" property="rankCreateDate"/>" />						
            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'rankCreateDate','dd/mm/yyyy');return false;">
        </td>
    </tr>
    
    <!--<tr>
        <td><bean:message key="common.label.rank.name-parent" bundle="<%=interfaces%>"/></td>
        <td>
             <html:select name="kpi" property="parRankId" onchange="postAjax('kpi','MainCate',anchor + ':_CRUD_RANK_CHANGE_OPTION:type:1');">
                <logic:present name="BComboRank">
                    <html:options collection="BComboRank" property="id" labelProperty="name"/>
                </logic:present>
              </html:select>
        </td>
    </tr>-->
    </table>
    
    <div id="alert">
        <jsp:include page="/admin/alert.jsp"/>
    </div>
    
    <div>
        <table class="list-voffice" cellpadding="2" width="100%" align="center" cellspacing="2" border="0">    
          <TR>               
              <TH width="10px" align="center">#</TH>            
              <TH>Ch&#7881; ti&#234;u &#272;G</TH>
              <TH width="25px">C&#243;/Kh&#244;ng &#272;G</TH>
              <!--<TH width="25px">C&#243; &#272;G</TH>-->
              <TH width="30px">&#272;i&#7875;m 0</TH>
              <TH width="30px">&#272;i&#7875;m 1</TH>
              <TH width="30px">&#272;i&#7875;m 2</TH>
              <TH width="30px">&#272;i&#7875;m 3</TH>
              <TH width="30px">&#272;i&#7875;m 4</TH>
              <TH width="30px">Nhu c&#7847;u</TH>
              <TH width="30px">H&#7895; tr&#7907;</TH>
              <TH width="20px"></TH>
          </TR>
        
          <logic:present name="BRanks">
            <bean:define name="BRanks" id="beans" type="com.form.FBeans" />
            <logic:notEmpty name="BRanks">
            <%  
                int inc = 0;
                int subSTT = 1;
                int STT = 1;
                String strSTT = "";
                String chkCheckbox = "";
                String checked = "";
            %>
            <logic:iterate id="bean" name="BRanks" type= "com.form.disability.categorys.FRank">
             <%  
                 int i = beans.getFirstRecord();                 
                 String rankResult = "";
                 String disabled = (bean.getDtlId()==0)?"":"disabled";
                 String[] arrIndex = {"I","II","III","IV"};
             %>
            <tr class="<%="1".equals(bean.getRsHasSP())? "ind_indicator":""%>">
              <td width="5%">
                  <%
                      int currParentId = bean.getParentID();
                      if (currParentId==0) {
                         strSTT = String.valueOf(STT);
                         STT++;
                         subSTT = 1;
                      } else {
                         strSTT = "" + subSTT;
                         subSTT++;
                      }
                  %>
                  
                  <%if(bean.getParentID()==0){%>
                    <strong><%=arrIndex[inc++]%></strong>
                  <%}else{%>
                      <%=strSTT%>
                  <%}%>
              </td>
              
              <%if(bean.getParentID()==0){%>
                  <td colspan="10"><strong><%=bean.getName()%></strong></td>
              <%}else{%>
                  <td><%=bean.getName()%></td>
              <%}%>
              
               <%
                    if (bean.getParentID()>0){
                    if (bean.getDtlId()==0 || (bean.getDtlId()==rankCurDtlId)){     
               %>           
              <td>
                  <% if (bean.getPrevHasSP()==1) {%>
                  <select name="rankHas" onchange="postAjax('kpi','MainCate',anchor + ':_PRE_CRUD_RANK:dtlId:<%=bean.getDtlId()%>:rankId:<%=bean.getId()%>');">
                      <option value="0_<%=bean.getId()%>" <%=(("0_" + bean.getId()).equals(rankHasCheck))?"selected":""%>>Kh&#244;ng</option>
                      <option value="1_<%=bean.getId()%>" <%=(("1_" + bean.getId()).equals(rankHasCheck))?"selected":""%>>C&#243;</option>
                  </select>                  
                  <%}%>
              </td>             
              <%} else {%>
              <td>
                  <img style="border:0px" title="<%=(bean.getHasRK()==1)?"C&#243; &#272;G":"Kh&#244;ng &#272;G"%>" 
                      src="<%=contextPath%>/images/<%=(bean.getHasRK()==1)?"checked":"unchecked"%>.png" /></td>             
              <%}%>
              
              <%
                  rankResult = bean.getRsInit();
              %>
              <td>
                  <% if (bean.getDtlId()==0 || (bean.getDtlId()==rankCurDtlId)){ 
                        disabled = (("1_" + bean.getId()).equals(rankHasCheck))?"":"disabled";
                  %>
                      <input type="checkbox" name="rankResult" value="0" <%=("0".equals(rankResult))?"checked":""%> <%=disabled%> />
                  <%} else {%>                      
                      <img style="border:0px" src="<%=contextPath%>/images/<%=("0".equals(rankResult))?"checked":"unchecked"%>.png" title="0"/>
                  <%}%>
              </td>
              
               <td>
                  <% if (bean.getDtlId()==0 || (bean.getDtlId()==rankCurDtlId)){%>
                      <input type="checkbox" name="rankResult" value="1" <%=("1".equals(rankResult))?"checked":""%> <%=disabled%> />
                  <%} else {%>                      
                      <img style="border:0px" src="<%=contextPath%>/images/<%=("1".equals(rankResult))?"checked":"unchecked"%>.png" title="1"/>
                  <%}%>
              </td>
              
               <td>
                  <% if (bean.getDtlId()==0 || (bean.getDtlId()==rankCurDtlId)){%>
                      <input type="checkbox" name="rankResult" value="2" <%=("2".equals(rankResult))?"checked":""%> <%=disabled%> />
                  <%} else {%>                      
                      <img style="border:0px" src="<%=contextPath%>/images/<%=("2".equals(rankResult))?"checked":"unchecked"%>.png" title="2"/>
                  <%}%>
              </td>
              
               <td>
                  <% if (bean.getDtlId()==0 || (bean.getDtlId()==rankCurDtlId)){%>
                      <input type="checkbox" name="rankResult" value="3" <%=("3".equals(rankResult))?"checked":""%> <%=disabled%> />
                  <%} else {%>                      
                      <img style="border:0px" src="<%=contextPath%>/images/<%=("3".equals(rankResult))?"checked":"unchecked"%>.png" title="3"/>
                  <%}%>
              </td>
              
               <td>
                  <% if (bean.getDtlId()==0 || (bean.getDtlId()==rankCurDtlId)){%>
                      <input type="checkbox" name="rankResult" value="4" <%=("4".equals(rankResult))?"checked":""%> <%=disabled%> />
                  <%} else {%>                      
                      <img style="border:0px" src="<%=contextPath%>/images/<%=("4".equals(rankResult))?"checked":"unchecked"%>.png" title="4"/>
                  <%}%>
              </td>
              
              <%
                  chkCheckbox = ("1".equals(bean.getRsHasSP()))?"checked":"";
              %>
              <td>
                  <% if (bean.getDtlId()==0 || (bean.getDtlId()==rankCurDtlId)){%>
                      <input type="checkbox" name="rankHasRQ" value="1" <%=(bean.getHasRQ()==1)?"checked":""%> disabled />
                  <%} else {%>                      
                      <img style="border:0px" src="<%=contextPath%>/images/<%=(bean.getHasRQ()==1)?"checked":"unchecked"%>.png" 
                        title="<%=(bean.getHasRQ()==1)?"C&#243; nhu c&#7847;u":"Kh&#244;ng c&#243; nhu c&#7847;u"%>" />
                  <%}%>
              </td>
              <td>
                  <% if (bean.getDtlId()==0 || (bean.getDtlId()==rankCurDtlId)){%>
                      <input type="checkbox" name="rankHasSP" value="1" <%="1".equals(bean.getRsHasSP())?"checked":""%> <%=disabled%> />
                  <%} else {%>                      
                      <img style="border:0px" src="<%=contextPath%>/images/<%=("1".equals(bean.getRsHasSP())?"checked":"unchecked")%>.png" 
                        title="<%=("1".equals(bean.getRsHasSP())?"C&#243; h&#7895; tr&#7907;":"Kh&#244;ng h&#7895; tr&#7907;")%>" />
                  <%}%>
              </td>
              
              <td>
                  <% if (bean.getDtlId()==0 && bean.getPrevHasSP()==1){%>
                  <img style="border:0px" src="<%=contextPath%>/images/add.png" title="Th&#234;m m&#7899;i" onClick="postAjax('kpi','MainCate',anchor + ':_INSERT_RANK:rankId:<%=bean.getId()%>');">
                  <% } else if ((bean.getDtlId()==rankCurDtlId)){%>
                  <!--<img style="border:0px" src="<%=contextPath%>/images/save.png" title="C&#7853;p nh&#7853;t" onClick="postAjax('kpi','MainCate',anchor + ':_UPDATE_RANK:dtlId:<%=bean.getDtlId()%>:rankId:<%=bean.getId()%>');">-->
                  <% } %>
                  
                  <% if (bean.getHasDel()==0 && bean.getDtlId()>0){ %>
                  <!--<img style="border:0px" src="<%=contextPath%>/images/editdraft.png" title="Ch&#7881;nh s&#7917;a" onClick="postAjax('kpi','MainCate',anchor + ':_PRE_EDIT_RANK:dtlId:<%=bean.getDtlId()%>:rankId:<%=bean.getId()%>:rankHasRK:<%=bean.getHasRK()%>:rankHasSP:<%=bean.getHasSP()%>:curRankHas:<%=bean.getHasRK()+"_"+bean.getId()%>');">-->
                  <img style="border:0px" src="<%=contextPath%>/images/delete.png" title="X&#243;a" onClick="postAjax('kpi','MainCate',anchor + ':_DELETE_RANK:dtlId:<%=bean.getDtlId()%>:createDate:<%=bean.getRankDate()%>');">
                  <%}%>
              </td>
              <%}%>
            </tr>
            </logic:iterate>
            </logic:notEmpty>
          </logic:present>
        </table>
    </div>
    <div id="alert">
        <jsp:include page="/admin/alert.jsp"/>
    </div>
</logic:equal>


<logic:equal name="kpi" property="mode" value="VIEW_RANK">
<bean:define name="kpi" property="rankNum" id="rankNum" type="java.lang.Integer" />
<%
    int num = rankNum;
    java.util.Map<String, String> mapValue = null;
    if (mapObject!=null){
        mapValue = (java.util.Map<String, String>)mapObject.get(rankCreateDate);       
    }
%>

<table width="100%" class="tableForm">
    <logic:greaterThan value="0" name="kpi" property="rankId">
    <tr>
        <td colspan="2"><b><bean:write name="kpi" property="breadcrumb" /></b></td>
    </tr>
    </logic:greaterThan>    
    
    <!--<tr>
        <td><bean:message key="common.label.rank.name-parent" bundle="<%=interfaces%>"/></td>
        <td>
             <html:select name="kpi" property="parRankId" onchange="postAjax('kpi','MainCate',anchor + ':_VIEW_RANK_CHANGE_OPTION:type:1');">
                <logic:present name="BComboRank">
                    <html:options collection="BComboRank" property="id" labelProperty="name"/>
                </logic:present>
              </html:select>
        </td>
    </tr>-->
</table>

<div id="alert">
    <jsp:include page="/admin/alert.jsp"/>
</div>


<div style="overflow-x:scroll;height:450px;">
  <table class="list-voffice" cellpadding="2" width="100%" align="center" cellspacing="2" border="0">    
    <TR>               
        <TH width="10px" align="center">#</TH>            
        <TH>Ch&#7881; ti&#234;u &#272;G</TH>
        <TH width="25px">&#272;G Ban &#273;&#7847;u</TH>
        <TH width="30px">Nhu c&#7847;u</TH>
        <TH width="30px">H&#7895; tr&#7907;</TH>
        <% if(num>0){ %>
        <TH width="25px">L&#7847;n <%=num%></TH>
        <% } %>
        <!--<TH width="25px"></TH>-->
    </TR>
    
    <tbody >
    <logic:present name="BRanks">
      <bean:define name="BRanks" id="beans" type="com.form.FBeans" />
      <logic:notEmpty name="BRanks">
      <%  
          int subSTT = 1;
          int STT = 1;
          String strSTT = "";
          String[] arrIndex = {"I","II","III","IV"};
          int inc = 0;
      %>
      <logic:iterate id="bean" name="BRanks" type= "com.form.disability.categorys.FRank">
       <%  int i = beans.getFirstRecord();%>
      <tr class="<%="1".equals(bean.getRsHasSP())? "ind_indicator":""%>">
        <td width="5%">
            <%
                int currParentId = bean.getParentID();
                if (currParentId==0) {
                   strSTT = String.valueOf(STT);
                   STT++;
                   subSTT = 1;
                } else {
                   strSTT = "" + subSTT;
                   subSTT++;
                }
            %>          
            <%if(bean.getParentID()==0){%>
              <strong><%=arrIndex[inc++]%></strong>
            <%}else{%>
                <%=strSTT%>
            <%}%>
        </td>
        
        <%if(bean.getParentID()==0){%>
            <td colspan="5"><strong><%=bean.getName()%></strong></td>
        <%}else{%>
            <td><%=bean.getName()%></td>
        <%}%>
        
        <% if (bean.getParentID()>0){%>
        <td><a href="#" title="<%=bean.getCreateDate()%>"><%=(bean.getRsInit()!=null)?bean.getRsInit():""%></a></td>
        <td>
            <img style="border:0px" src="<%=contextPath%>/images/<%=(bean.getHasRQ()==1)?"checked":"unchecked"%>.png" 
              title="<%=(bean.getHasRQ()==1)?"C&#243; nhu c&#7847;u":"Kh&#244;ng c&#243; nhu c&#7847;u"%>" />
        </td>
        <td>          
            <img style="border:0px" src="<%=contextPath%>/images/<%=("1".equals(bean.getRsHasSP()))?"checked":"unchecked"%>.png" 
              title="<%=("1".equals(bean.getRsHasSP()))?"C&#243; h&#7895; tr&#7907;":"Kh&#244;ng h&#7895; tr&#7907;"%>" />
        </td>
         <% if(num>0){ %>
        <td>
            <a href="#" title="<%=rankCreateDate%>">
               <%=(mapValue!=null && mapValue.size()>0 && mapValue.get(String.valueOf(bean.getId()))!=null)? mapValue.get(String.valueOf(bean.getId())):"" %>
            </a>
        </td>
        <% } %>
        <!--<td>
            <% if (bean.getDtlId()>0){%>            
            <img style="border:0px" src="<%=contextPath%>/images/editdraft.png" title="Ch&#7881;nh s&#7917;a" 
                onClick="postAjax('kpi','MainCate',anchor + ':_PRE_EDIT_RANK:dtlId:<%=bean.getDtlId()%>:createDate:<%=rankCreateDate%>:rankId:<%=bean.getId()%>:rankHasRK:<%=bean.getHasRK()%>:rankHasSP:<%=bean.getHasSP()%>:curRankHas:<%=bean.getHasRK()+"_"+bean.getId()%>');">
            <%}else{%>
            <img style="border:0px" src="<%=contextPath%>/images/add.png" 
                 title="Th&#234;m m&#7899;i" 
                 onClick="postAjax('kpi','MainCate',anchor + ':_PRE_CRUD_RANK');">
            <%}%>
        </td>-->
        <%}%>
      </tr>
      </logic:iterate>
      </logic:notEmpty>
    </logic:present>
  </table>
  </div>
</logic:equal>