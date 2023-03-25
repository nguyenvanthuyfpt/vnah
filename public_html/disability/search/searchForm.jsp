<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<html:form action="searchPanel"  method="post"/>
<html:form action="kpi_detail" method="POST" />
<html:form action="searchdispeople" method="post">
<%
    String dataType = "2"; 
%>
<script type="text/javascript">
function toggleChk(source) {
    checkboxes = document.getElementsByName('fields');
    for(var i in checkboxes)
        checkboxes[i].checked = source.checked;
}
function searchKeyPress(e) {
    e = e || window.event;
    if (e.keyCode == 13)
    {
        getSearchForm();
        return false;
    }
    return true;
}

function onChangeDateType(){
    postAjax('searchdispeople','id_parameter_search',anchor + ':_SELECT_DATATYPE');
    $(".khung-div-search").hide();
}

function cancelSearch(div){
    $("#"+div).hide();
    $("."+div).attr('checked',false);
    resetEleDiv(div)
}

function cancelAll(){
    resetEleDiv('id_parameter_search');

    cancelSearch('tddangtat');
    resetEleDiv('tddangtat');
    
    cancelSearch('tdnhucau');  
    resetEleDiv('tdnhucau');
    
    cancelSearch('tdhotro'); 
    resetEleDiv('tdhotro');    
}
</script>
<div id="divSearchResult" style="display:none"></div>

<div id="divSearchForm">
  <ul id="tree">
      <li>
          <div class="bgr2"><a href="javascript:excutePostCategorys('_SEARCH')">
              <bean:message key="common.label.function.search" bundle="<%=interfaces%>"/></a></div>
      </li>
  </ul>
	
  <br/>
    
  <div class="content-div">
      <table cellpadding="4" cellspacing="4" border="0" width="100%" class="pd-5">
      <tr id="id_parameter_search">                  
          <jsp:include page="/disability/search/searchParam.jsp"/>
      </tr>
  
      <tr>
          <td colspan="4" align="center">
              <div style="padding:16px 0">
                  <span class="bt_left_Search">
                          <span class="bt_right_Search">
                              <span class="bt_center_Search">
                                  <html:button property="_SEARCH_RESULT" styleClass="button" onclick="getSearchForm();">
                                      <bean:message key="btn.search" bundle="<%=interfaces%>"/>
                                  </html:button>
                              </span>
                          </span>
                  </span>
                  <span><img src="images/front/spacer.gif" width="7" height="1" /></span>
                  <span class="bt_left_reset">
                      <span class="bt_right_reset">
                          <span class="bt_center_reset">
                              <html:button property="_RESET" styleClass="button" onclick="cancelAll();">
                                  <bean:message key="btn.cancel" bundle="<%=interfaces%>"/>
                              </html:button>
                          </span>
                      </span>
                  </span>
              </div>     
          </td>
      </tr>
  </table>
  </div>
        

  <% if ("2".equals(dataType)) {%>
  <div class="khung-div-search">
      <div id="tddangtat"></div>    
      <div id="tdnhucau"></div>
      <div id="tdhotro"></div>
      <div id="tdketqua"></div>
  </div>
  <%}%>

<!--<a href="#" id="back-to-top" title="Back to top">&uarr;</a>-->

</div>
</html:form>