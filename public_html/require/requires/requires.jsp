  <%@ include file="/commons/tags.jsp"%>
  <%@ include file="/commons/params.jsp"%>
<div class="padding-content">
<div id="mailcol">     
    <div class="tabmenu" id="container-1" >
            <div style="clear:both"></div>            
            <jsp:include page="/require/requires/menu.jsp"><jsp:param name="optionmenu" value="0"/></jsp:include>                              
    </div>   
     <div id="fragment-1">                                         
               <div class="content-calendar"> 
                <table width="100%" border="0px" cellpadding="0" cellspacing="0">  
              <html:form action="frmRequire" method="post" enctype="multipart/form-data">                           
                <html:hidden name="frmRequire" property="surcureId" /> 
                    <TR>
                           <TD class="ct-celendar" id="MainCate" valign="top">
                                  <jsp:include page="/require/requires/form.jsp" />   
                            </td>
                    </tr>                                    
                  </html:form>  
                    </table>
                </div>
            </div>
        </div>
     </div>