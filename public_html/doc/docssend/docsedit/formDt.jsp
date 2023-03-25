<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
function onchangeClassify(obj){
        if(obj.value!=0){
        var numberDay=0;
        var arrayString=obj.form.arrayClassify.value;
        var member = arrayString.split(",");
        for (i=0;i<member.length;i++){
            if(member[i].split("#")[0]==obj.value){
                  numberDay=member[i].split("#")[1];
            }
        }
        postAjax('classify','IDdeadLine',anchor + ':_ADD_DEADLINE:numberDay:'+ numberDay +':localDate:'+obj.form.localDate.value);
        }else{
            obj.form.deadLine.value='';
        }
}

function referent(){
    document.getElementById('idReference').style.display=document.getElementById('idReference').style.display=='none'?'block':'none';  
}

function checkSubmit(form){    
    if(form.localCode.value=='' || form.abstracts.value=='' || (form.docCode && (form.docCode.value=='' || form.docDate.value==''))){
        alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
        return false;
    }
    return true;
}
</script>
<div class="padding-content">
<div id="mailcol">     
    <div class="tabmenu" id="container-1" >
            <div style="clear:both"></div>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="top" width="140px">
                            <div class="ctn-left">
                                <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic"><bean:message key="menu.top.doc.caption" bundle="<%=interfaces%>"/></div></div>                                                                        
                            </div>  
                    </td>
                    <td valign="top" >
                        <jsp:include page="/commons/menuDoc.jsp"><jsp:param name="optionmenu" value="1"/></jsp:include>
                    </td>
                </tr>
            </table>            
    </div>   
     <div id="fragment-1">                                  
          <div  style="text-align:center">
               <div class="content-calendar"> 
                <table width="100%" border="0px" cellpadding="0" cellspacing="0">                             
                    <TR>
                         <td width="200px" valign="top" align="left" style="padding-right: 8px" >
                             <jsp:include page="/doc/docssend/inforForm/listStatus.jsp" />   
                             <jsp:include page="/doc/docsrecv/inforForm/listTrailer.jsp" />
                         </td>
                         <td class="ct-celendar" id="MainCate" style="padding-right: 20px" valign="top">
                              <jsp:include page="/doc/docssend/docsedit/edit.jsp"/>                                 
                          </td>
                    </tr>                                    
                  
                    </table>
                </div>
            </div>
</div>
     </div>
 </div>


