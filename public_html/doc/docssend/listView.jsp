<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<Script type="text/javascript">
var detailDoc=null;
function validateReview(form){
if(form.title.value==''){
    alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
    return false;
}
return true;
}

function wrongWaySend(docId){         
      post('docReviewSend',anchor + ':_CREATE_WRONG_WAY:id:' + docId);       
}

function insertReviewPost(id,forYouId,notReply){
        
        var statusId= 0;
        var objStatusId = document.docssend.statusId;
        if(typeof objStatusId !="undefined"){
        for (i=0;i<objStatusId.length;i++){
            if (objStatusId[i].checked==true)
                statusId=objStatusId[i]
            }
        }
        
        var dossierId=document.docssend.dossierId;
        var classifyId=document.docssend.classifyId;
          var taget = notReply!=1?":_CREATE_REVIEW_POST":":_CREATE_REVIEW_SELECT";
        if(document.docssend.views==null){
            var obServer=document.docssend.obServer;
            post('docReviewSend',anchor+ taget + ':id:' + id + ':statusId:' + statusId.value + ':obServer:' + obServer.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':membersFile:' + empDep + ':classifyId:' + classifyId.value);
        }else{
            var views=document.docssend.views;
             post('docReviewSend',anchor+ taget + ':id:' + id + ':statusId:' + statusId.value + ':views:' + views.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':membersFile:' + empDep + ':classifyId:' + classifyId.value);
        }
}

function excuteEndDoc(id,forYouId,store){       
        
        var statusId= 0;
        var objStatusId = document.docssend.statusId;
        if(typeof objStatusId !="undefined"){
            for (i=0;i<objStatusId.length;i++){
                if (objStatusId[i].checked==true)
                    statusId=objStatusId[i]
                }
        }
        var dossierId=document.docssend.dossierId;
        var classifyId=document.docssend.classifyId;
        if(document.docssend.views==null){
            var obServer=document.docssend.obServer;
            post('docReviewSend',anchor + ':_UPDATE_STATUS:storeId:' + store + ':id:' + id + ':statusId:' + statusId.value + ':obServer:' + obServer.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':classifyId:' + classifyId.value);
        }else{
             var views=document.docssend.views;
            post('docReviewSend',anchor + ':_UPDATE_STATUS:storeId:' + store + ':id:' + id + ':statusId:' + statusId.value + ':views:' + views.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':classifyId:' + classifyId.value);
        }
        
}
function assignDirect(obj,id,forYouId){
     var statusId= 0;
        var objStatusId = document.docssend.statusId;
       if(typeof objStatusId !="undefined"){ 
        for (i=0;i<objStatusId.length;i++){
            if (objStatusId[i].checked==true)
            statusId=objStatusId[i]
        }
        }
    var dossierId=document.docssend.dossierId;
    var classifyId=document.docssend.classifyId;
if(document.docssend.views==null){
    var obServer=document.docssend.obServer;
    if(obj==null){
        post('docReviewSend',anchor+':_ASSIGN_LIST_DOC:app:100:departmentId:0:id:' + id + ':statusId:' + statusId.value + ':obServer:' + obServer.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':membersFile:' + parent.empDep + ':classifyId:' + classifyId.value);
    }else{
        post('docReviewSend',anchor+':_ASSIGN_LIST_DOC:app:100:departmentId:' + obj.value +':id:' + id + ':statusId:' + statusId.value + ':views:' + views.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':membersFile:' + parent.empDep + ':classifyId:' + classifyId.value);
    }
}else{
    var views=document.docssend.views;
     if(obj==null){
        post('docReviewSend',anchor+':_ASSIGN_LIST_DOC:app:100:departmentId:0:id:' + id + ':statusId:' + statusId.value + ':views:' + views.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':membersFile:' + parent.empDep + ':classifyId:' + classifyId.value);
    }else{
        post('docReviewSend',anchor+':_ASSIGN_LIST_DOC:app:100:departmentId:' + obj.value +':id:' + id + ':statusId:' + statusId.value + ':views:' + views.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':membersFile:' + parent.empDep + ':classifyId:' + classifyId.value);
    }
}
}

function showSearchDoc(){
        for(i=1;i<=6;i++){
            document.getElementById('trSearch'+i).className=document.getElementById('trSearch'+i).className=='tdSearchshow'?'tdSearchHidden':'tdSearchshow';
        }
}

function mdotab(obj,params,position){
checkedInnerHtml();
if(obj.className=='tab1'){
        for(i=0;i<obj.parentNode.childNodes.length;i++){
                if(obj.parentNode.childNodes[i].className=='tabactive1') obj.parentNode.childNodes[i].className='tab1';
        }
        obj.className='tabactive1';
        postAjax('docReviewSend',position,anchor + ':' + params);
        messageImg(position)
    }
}
function showTag(showId,idDoc,forYouId){
  
        if (detailDoc!=null && detailDoc.innerHTML!='') {
            detailDoc.innerHTML='';                          
        }
      
        postAjax('docssend',showId,anchor +':_DETAIL:id:'+idDoc+':forYouId:'+ forYouId,'buildpopup()');  
        messageImg(showId)
        detailDoc=getObj(showId)

}

function classify(docId){
       
        var obj = document.docSendClassify.storeClassify ;
        var classifyId = 0;
        for (i=0;i<obj.length;i++){
            if (obj[i].checked) classifyId = obj[i].value;
        }
        getObj("classifyName" + docId).innerHTML = getObj("name" + classifyId).innerHTML;        
        postAjax('docSendClassify','erroralert',anchor +':_OK_UPDATE_CLASSIFY');
    }
function postTab(obj,params){
if(obj.className==''){
        for(i=0;i<obj.parentNode.parentNode.childNodes.length;i++){
                if(obj.parentNode.parentNode.childNodes[i].className=='ui-tabs-selected') obj.parentNode.parentNode.childNodes[i].className='';
        }
        obj.parentNode.className='ui-tabs-selected';
        post('change',anchor + ':' + params);
    }
}
</script>
<html:form action="dossiers" method="post" />
<div class="padding-content">
<div id="mailcol">
    <div class="tabmenu" id="container-1" >
        <div style="clear:both"></div>
          <jsp:include page="/commons/menuDoc.jsp"><jsp:param name="optionmenu" value="1"/></jsp:include>          
             <div class="content-calendar">          
              <jsp:include page="/doc/docssend/list.jsp" />          
                    <div  style="float:left"><Strong>
                        <bean:message key="page.caption.total" bundle="<%=interfaces%>"/> 
                        <bean:define name="BSearch" id="beans" type="com.form.FBeans"/>
                        <bean:write name="beans" property="totalRows"/></strong>
                    </div>
                    <div style="float:right">
                            <%String params = anchor + ":_VIEW";%>
                            <jsp:include page="/paging.jsp">
                            <jsp:param name="BEANS" value="BSearch"/>
                            <jsp:param name="PARAMS" value="<%=params%>"/>
                            <jsp:param name="FORM" value="docssend"/>
                            <jsp:param name="METHOD" value="post"/>
                            </jsp:include>
                    </div>
            </div>
        </div>      
</div>         
</div>
