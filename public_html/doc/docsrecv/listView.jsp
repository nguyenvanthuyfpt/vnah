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

function wrongWayRecv(docId){         
      post('docReviewRecv',anchor + ':_CREATE_WRONG_WAY:id:' + docId);       
}

function insertReviewPost(id,forYouId,notReply){       
        var statusId= 0;        
        var objStatusId = document.docsrecv.statusId;
        if(typeof objStatusId !="undefined"){
            for (i=0;i<objStatusId.length;i++){
            if (objStatusId[i].checked==true)
                statusId=objStatusId[i]
            }
        }
        var dossierId=document.docsrecv.dossierId;
        var classifyId=document.docsrecv.classifyId;
        var taget = notReply!=1?":_CREATE_REVIEW_POST":":_CREATE_REVIEW_SELECT";
        if(document.docsrecv.views==null){
            var obServer=document.docsrecv.obServer;
            post('docReviewRecv',anchor + taget + ':id:' + id + ':statusId:' + statusId.value + ':obServer:' + obServer.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':classifyId:' + classifyId.value);
        }else{
             var views=document.docsrecv.views;
            post('docReviewRecv',anchor + taget + ':id:' + id + ':statusId:' + statusId.value + ':views:' + views.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':classifyId:' + classifyId.value);
        }
}

function excuteEndDoc(id,forYouId,store){       
       var statusId= 0;
        var objStatusId = document.docsrecv.statusId;
        if(typeof objStatusId !="undefined"){
            for (i=0;i<objStatusId.length;i++){
            if (objStatusId[i].checked==true)
                statusId=objStatusId[i]
            }
        }
        var dossierId=document.docsrecv.dossierId;
        var classifyId=document.docsrecv.classifyId;
        if(document.docsrecv.views==null){
            var obServer=document.docsrecv.obServer;
            post('docReviewRecv',anchor + ':_UPDATE_STATUS:storeId:' + store + ':id:' + id + ':statusId:' + statusId.value + ':obServer:' + obServer.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':classifyId:' + classifyId.value);
        }else{
             var views=document.docsrecv.views;
            post('docReviewRecv',anchor + ':_UPDATE_STATUS:storeId:' + store + ':id:' + id + ':statusId:' + statusId.value + ':views:' + views.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':classifyId:' + classifyId.value);
        }
}

function assignDirect(obj,id,forYouId){
    var statusId= 0;
    var objStatusId = document.docsrecv.statusId;
   if(typeof objStatusId !="undefined"){
        for (i=0;i<objStatusId.length;i++){
            if (objStatusId[i].checked==true)
            statusId=objStatusId[i]
        }
    }


var dossierId=document.docsrecv.dossierId;
var classifyId=document.docsrecv.classifyId;
if(document.docsrecv.views==null){
    var obServer=document.docsrecv.obServer;
    if(obj==null){
        post('docReviewRecv',anchor+':_ASSIGN_LIST_DOC:app:100:id:' + id + ':statusId:' + statusId.value + ':obServer:' + obServer.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':classifyId:' + classifyId.value );
    }else{
        post('docReviewRecv',anchor+':_ASSIGN_LIST_DOC:app:100:id:' + id + ':statusId:' + statusId.value + ':views:' + views.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':classifyId:' + classifyId.value);
    }
}else{
    var views=document.docsrecv.views;
     if(obj==null){
        post('docReviewRecv',anchor+':_ASSIGN_LIST_DOC:app:100:id:' + id + ':statusId:' + statusId.value + ':views:' + views.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':classifyId:' + classifyId.value);
    }else{
        post('docReviewRecv',anchor+':_ASSIGN_LIST_DOC:app:100:id:' + id + ':statusId:' + statusId.value + ':views:' + views.value + ':dossierId:' + dossierId.value + ':forYouId:' + forYouId + ':classifyId:' + classifyId.value);
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
        postAjax('docReviewRecv',position,anchor + ':' + params);
        messageImg(position)
    }
}
function showTag(showId,idDoc,foryouId){

        if (detailDoc!=null && detailDoc.innerHTML!='') {
            detailDoc.innerHTML='';                          
        }
        postAjax('docsrecv',showId,anchor +':_DETAIL:id:'+idDoc+':forYouId:'+ foryouId,'buildpopup()');
        messageImg(showId)       
        detailDoc=getObj(showId)
}


    function classify(docId){
       
        var obj = document.docRecvClassify.storeClassify ;
        var classifyId = 0;
         if(typeof objs !="undefined"){
            for (i=0;i<obj.length;i++){
                if (obj[i].checked) classifyId = obj[i].value;
            }
        }else {
            classifyId =  obj.value;
        }
        getObj("classifyName" + docId).innerHTML = getObj("name" + classifyId).innerHTML;        
        postAjax('docRecvClassify','erroralert',anchor +':_OK_UPDATE_CLASSIFY');
    }
function search(obj,params){
    if(event.keyCode==13){post('docReviewRecv',anchor+':_SEARCH');return false;}
}
</script>
<html:form action="dossiers" method="post" />
<html:form action="docssend" method="post" />

<div class="padding-content">
    <div id="mailcol">
        <div class="tabmenu" id="container-1" >
            <div style="clear:both"></div>
            <jsp:include page="/commons/menuDoc.jsp"><jsp:param name="optionmenu" value="0"/></jsp:include>
            <div id="fragment-1">
               <div class="content-calendar">      
                  <jsp:include page="/doc/docsrecv/list.jsp" />               
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
                            <jsp:param name="FORM" value="docsrecv"/>
                            <jsp:param name="METHOD" value="post"/>
                            </jsp:include>
                    </div>
                </div>
            </div>
                  
        </div>
    </div>               
</div>
