<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
function mdotab(obj,params,position){
if(obj.className=='tab1'){
        for(i=0;i<obj.parentNode.childNodes.length;i++){
                if(obj.parentNode.childNodes[i].className=='tabactive1') obj.parentNode.childNodes[i].className='tab1';
        }
        obj.className='tabactive1';
        postAjax('docreport',position,anchor + ':' + params);
        messageImg(position)
    }
}

    function selectObjecOnlick(obj,type){
              if (getObj('type')!=null) getObj('type').value=0;                
              if((obj.form.fromDate.value!=null && obj.form.fromDate.value!='') || (obj.form.toDate.value!=null && obj.form.toDate.value!='')){
                    anchortemp = type==4?':_SEARCH':':_SELECT';
                    post('docreportLeft',anchor + anchortemp);
              }else{  return false;}
    }
    function onclickDiv(obj,objForm){
        if (getObj('type')!=null) getObj('type').value=0;       
        if((objForm.fromDate.value!=null && objForm.fromDate.value!='') || (objForm.toDate.value!=null && objForm.toDate.value!='')){
                post('docreportLeft',anchor+':_SELECT');
        }else{  return false;}
    }
    
    function chosehideshow(obj,type){
    obj.form.depId.style.display=(obj.value==0)?'block':'none';
    obj.form.userId.style.display=(obj.value==0)?'block':'none';
    }
    
    function checkAllDocIds(obj){
    var members=obj.form.docIds.value;
            var ids=obj.form.id;
            if(typeof ids.length !="undefined"){
                for (i=0;i<ids.length;i++){
                ids[i].checked=obj.checked;
                    if(obj.checked && members.indexOf(','+ids[i].value+',')<0){
                        if(members=='') members=',';
                        members+=ids[i].value +',';
                    }else{
                        if(members.indexOf(','+ids[i].value+',')>=0){
                        members=members.replace(','+ids[i].value+',',',');
                        }
                    }
                }
            }
 obj.form.docIds.value=members;
     }
     function clickDocId(obj){
         var members=obj.form.docIds.value;
         if(obj.checked){
             if(members.indexOf(','+obj.value+',')<0){
              if(members=='') members=',';
                        members+=obj.value+',';  
             }
         }else{
             if(members.indexOf(','+obj.value+',')>=0){
                    members=members.replace(','+obj.value+',',',');
             }        
         }
         obj.form.docIds.value=members;
     }
</script>
<div id="left">
    <html:form action="docreportLeft" method="post" >
     <div class="ctn-left">
        <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic"><bean:message key="menu.top.doc.caption" bundle="<%=interfaces%>"/></div></div>
         
      
    <div class="status">                                        
    <html:select name="docreport" style="width:100px" property="workflowId" onchange="selectObjecOnlick(this,0)">      
      <% if (Integer.parseInt(request.getSession().getAttribute(com.lib.AppConfigs.CHECK_OBSERVER_DOCSRECV).toString())==1 ||  (Integer.parseInt(request.getSession().getAttribute(com.lib.AppConfigs.CHECK_OBSERVER_DOCSRECV).toString())!=1 && request.getSession().getAttribute("01.01")!=null)){ %>
        <html:option value="1"><bean:message key="doc.docreport.form.docsrecv" bundle="<%=interfaces%>"/></html:option>
       <%}%>
        <% if (Integer.parseInt(request.getSession().getAttribute(com.lib.AppConfigs.CHECK_OBSERVER_DOCSSEND).toString())==1 || (Integer.parseInt(request.getSession().getAttribute(com.lib.AppConfigs.CHECK_OBSERVER_DOCSSEND).toString())!=1 && request.getSession().getAttribute("01.02")!=null)){ %>
        <html:option value="2"><bean:message key="doc.docreport.form.docssend" bundle="<%=interfaces%>"/></html:option>
        <%}%>
      </html:select>                            
</div>
 
 <logic:equal name="docreport" property="checkObserver" value="1" >
<div align="center"><hr /></div>
<div class="status">
<p><font style="color:#a0aec2">
    <b><bean:message key="doc.docreport.total.report" bundle="<%=interfaces%>"/></b></font>                                
    <html:checkbox name="docreport" property="totalReport" value="1" onclick="post('docreportLeft',anchor + ':_SELECT')"/>
 </p> 
 </div>
</logic:equal>

<div align="center"></div>
<div class="status">  
       <html:select name="docreport" style="width:100px" property="typeTotal" onchange="javascript:onclickDiv(this,document.docreportLeft)">      
            <html:option value="0"><bean:message key="doc.docreport.form.status" bundle="<%=interfaces%>"/></html:option>
            <html:option value="1"><bean:message key="doc.docreport.form.views" bundle="<%=interfaces%>"/></html:option>
            <html:option value="2"><bean:message key="doc.docreport.form.docType" bundle="<%=interfaces%>"/></html:option>
            <html:option value="3"><bean:message key="doc.docreport.form.branch" bundle="<%=interfaces%>"/></html:option>
     </html:select>
</div>


<div align="center"></div>
<div class="status">  
       <html:select name="docreport" style="width:100px" property="typeReport" onchange="javascript:onclickDiv(this,document.docreportLeft)">      
            <html:option value="0"><bean:message key="doc.docreport.form.onday" bundle="<%=interfaces%>"/></html:option>
            <html:option value="1"><bean:message key="doc.docreport.form.onweek" bundle="<%=interfaces%>"/></html:option>
            <html:option value="2"><bean:message key="doc.docreport.form.onmonth" bundle="<%=interfaces%>"/></html:option>
            <html:option value="3"><bean:message key="doc.docreport.form.onyear" bundle="<%=interfaces%>"/></html:option>
     </html:select>
</div>
<div align="center"></div>
<div class="status">  
    <p><font style="color:#a0aec2">
    <b> <bean:message key="doc.docreport.form.formdate" bundle="<%=interfaces%>"/></b></font>
    <div><html:text name="docreport" property="fromDate" style="width:60px" onkeydown="javascript:if(event.keyCode==13){selectObjecOnlick(this,4);return false;}"   />&nbsp;<img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'fromDate','dd/mm/yyyy');return false;"></div>
 </div>  
 <div align="center"></div>
 <div class="status">  
    <p><font style="color:#a0aec2">
    <b> <bean:message key="doc.docreport.form.todate" bundle="<%=interfaces%>"/></b></font>
    <div><html:text name="docreport" property="toDate" style="width:60px" onkeydown="javascript:if(event.keyCode==13){selectObjecOnlick(this,4);messageImg('right');return false;}" />&nbsp;<img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'toDate','dd/mm/yyyy');return false;"></div>          
     
</div>

<div align="center"><hr /></div>
<div class="status">  
       <logic:equal name="docreport" property="totalReport" value="0" >
         <logic:equal name="docreport" property="checkObserver" value="1" >                     
                <html:select styleClass="inputbox" style="width:140px"   name="docreport" property="depId" onchange="javascript:postAjax('docreport','usercombo',anchor+':_SHOW');"> 
                <html:option value="0"><bean:message key="problem.dep.select.caption" bundle="<%=interfaces%>"/></html:option>
                <logic:present name="BDepartments">
                <html:options collection="BDepartments" property="id" labelProperty="name"/>          
                </logic:present>
                </html:select>
           <span id="usercombo"><jsp:include page="/doc/docReport/user.jsp" /></span>
        </logic:equal>                
         </logic:equal> 
</div>
<div class="status">  
<logic:notEqual name="docreport" property="totalReport" value="1" >
     <jsp:include page="/doc/docReport/totals.jsp" />
</logic:notEqual>
</div>      
    </div>
    </html:form>
</div>      


