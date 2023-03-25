<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div id="left">
    <html:form action="docsrecv" method="post" >
     <div class="ctn-left">
        <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic"><bean:message key="menu.top.doc.caption" bundle="<%=interfaces%>"/></div></div>
         
        <div class="search"><b><a href="javascript:showSearchDoc()"><bean:message key="cmd.search" bundle="<%=interfaces%>"/></a></b></div>
        <div align="center"><hr /></div>
        
            <div class="status">                                        
                <jsp:include page="/doc/docsrecv/selectStatus.jsp" />                              
            </div>
        
            <div class="status">
                <jsp:include page="/doc/docsrecv/tranferType.jsp" />                              
            </div>
            
            <div align="center"><hr /></div>
            <div class="status">                                   
                 <jsp:include page="/doc/docsrecv/selectClassify.jsp" />
            </div>
            
            <div align="center"><hr /></div>
            <div class="status">
                <jsp:include page="/doc/docsrecv/selectDossier.jsp" />    
            </div>
    </div>
    </html:form>
</div>      