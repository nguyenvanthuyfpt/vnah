<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <div class="scroll" id="divListMycontact">
        <div style="padding-top:2px;padding-bottom:2px" align="left">
        <input type="text" class="InputSearch" id="fullName" name="fullName" style="width:150px;" onkeydown="if(event.keyCode==13){if (this.value.trim()!=''){postAjax('formMyContact','divListMycontact',anchor + ':_PREPARED_SAVE');return false;}else{postAjax('formMyContact','divListMycontact',anchor + ':_LIST_MYCONTACT_VIEW');return false;}}">
        </div>
        <div style="paddding:0px;margin:0px" align="left">
         <jsp:include page="/mycontact/listshow.jsp" />
         </div>
</div>