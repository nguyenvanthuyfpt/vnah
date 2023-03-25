<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
    function selectedSubmit(form,id,index){
         checkedAll(form.docBoss);
         checkedAll(form.docofficer);  
         checkedAll(form.priorities);  
         var  params = "";
         if (getObj('title')!=null ){ 
         
               if (!document.docrule.detail.checked){
                    params +=":detail:0" ;
               }
               
               if (!document.docrule.storeDrapt.checked){
                    params +=":storeDrapt:0" ;
               }
               
               if (!document.docrule.dossier.checked){
                    params +=":dossier:0" ;
               }
               
               if (!document.docrule.docReply.checked){
                    params +=":docReply:0" ;
               }
               if (!document.docrule.docTranfer.checked){
                    params +=":docTranfer:0" ;
               }
               if (!document.docrule.updateDraft.checked){
                    params +=":updateDraft:0" ;
               }
               if (!document.docrule.forYouAssign.checked){
                    params +=":forYouAssign:0" ;
               }
               if (!document.docrule.readOnly.checked){
                    params +=":readOnly:0" ;
               }
               if (!document.docrule.excuteNotView.checked){
                    params +=":excuteNotView:0" ;
               }
               if (!document.docrule.excuteGroup.checked){
                    params +=":excuteGroup:0" ;
               }
               if (!document.docrule.sendSms.checked){
                    params +=":sendSms:0" ;
               }
               if (!document.docrule.reply.checked){
                    params +=":reply:0" ;
               }
               if (!document.docrule.reviewfile.checked){
                    params +=":reviewfile:0" ;
               }
               if (!document.docrule.direct.checked){
                    params +=":direct:0" ;
               }
               if (!document.docrule.defineFileEmit.checked){
                    params +=":defineFileEmit:0" ;
               }
               if (!document.docrule.classify.checked){
                    params +=":classify:0" ;
               }
        }
         if (index==1){
            postAjax('docrule','formList',anchor + ':_CREATE:ruleId:' + id + ":deadline:"  + form.deadline1.value + ":unIncharge:" + form.unIncharge1.value + params);
         }else{
             postAjax('docrule','formList',anchor + ':_EDIT:ruleId:' + id + ":deadline:"  + form.deadline1.value + ":unIncharge:" + form.unIncharge1.value + params)
         }
    }    
</script>
<table width="100%" border="0px" cellpadding="0" cellspacing="0">
    <tr>
        <td valign="top" style="padding-left:4px">
            <table width="100%" class="adminheading">
                <tbody>
                    <tr>
                        <th class="app"><bean:message key="infor.doc.rule.caption" bundle="<%=interfaces%>"/></th>
                    </tr>
                </tbody>
            </table>
        </td>
    </tr>  
    <tr>
           <td valign="top">                                       
               <jsp:include page="/admin/doc/rules/tag.jsp"/>                                         
           </td>
     </tr>
    <TR>
        <TD id="MainDocRules"><jsp:include page="/admin/doc/rules/list.jsp"/></td>
    </tr>
</table>
