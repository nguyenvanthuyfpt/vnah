<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="docsrecv" method="post" />
<html:form action="dossiers" method="post" />
<html:form action="classify" method="post" />
<html:form action="from" method="post" />
<script type="text/javascript">
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
    if(form.localCode.value=='' || form.docCode.value=='' || form.localDate.value=='' || form.docDate.value=='' || form.abstracts.value==''){
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
                                <div class="title clearfix"><img src="images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic"><bean:message key="menu.top.doc.caption" bundle="<%=interfaces%>"/></div></div>                                                                        
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
               <html:form action="docssend" method="post" enctype="multipart/form-data">   
                <html:hidden name="docssend" property="id" />
                <html:hidden name="docssend" property="statusId" />
                <html:hidden name="docssend" property="type" />
                <html:hidden name="docssend" property="changeId" />
                    <TR>
                           <TD class="ct-celendar" id="MainCate" valign="top">
                                   <jsp:include page="/doc/docssend/edit.jsp" />     
                            </td>
                    </tr>                                    
                  </html:form>  
                    </table>
                </div>
            </div>
       </div>
     </div>
</div>


