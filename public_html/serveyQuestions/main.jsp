<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript">
function voteServey(location,obj){
    var checked=0;
    for (var i=0; i < obj.length; i++){
          if (obj[i].checked){
            checked = obj[i].value;
          }
    }
      if(checked>0){
         postAjax('serveyQuestions',location,anchor + ":_COUNT:questionId:"+ checked);
      }else{
         alert(<bean:message key="alert.chose.servey" bundle="<%=interfaces%>"/>);
      }
}
</script>
<div id="cssform" style="visibility: visible;"></div>
<div id="form" name="form" style="margin-bottom: 0px; margin-top: 0px;">
  <div class="box">
    <div class="box-outer">
      <div class="box-inner">
        <div class="box-top">
          <logic:present name="BTotals" >
          <logic:iterate name="BTotals" id="bean" indexId="i" type="com.form.servey.FServey">
          <div class="wp-polls" id="polls-9">
              <div class="question">
                <div class="question-outer">
                  <div class="question-inner">
                    <div class="question-top"><bean:write name="bean" property="name" /></div>
                  </div>
                </div>
              </div>
              <div class="wp-polls-ans polls-9-ans">
                <div class="answer">
                  <ul class="wp-polls-ul">
                    <logic:notEmpty name="bean" property="questions">
                        <logic:iterate name="bean" property="questions" id="beanQuestions" type="com.form.servey.FServeyQuestions">                       
                                <li><input type="radio" name="questionId<%=i%>" value="<%=beanQuestions.getQuestionId()%>"  /><span><%=beanQuestions.getQuestion()%></span></li>
                        </logic:iterate>
                    </logic:notEmpty>
                  </ul>
                </div>
                  <table width="100%"><tr><td align="left">
                  <%String postsition= "'contents"+ bean.getServeyId() + "'";%>
                    <%String onlick="voteServey("+ postsition +",this.form.questionId" + i +  ")";%>
                  <input style="border:0 none; padding:0" type="button" onclick="<%=onlick%>" onmouseout="this.className='buttonAsLink';" onmouseover="this.className='buttonAsLink_hover';" class="buttonAsLink" value="" name="vote"/>      
                  </td><td align="right">
                  <a class="modal-button" href="serveyQuestions<%=extention%>?<%=anchor%>=_VIEW&serveyId=<%=bean.getServeyId()%>" rel="{handler: 'iframe', size: {x:370, y: 220}}">
                        <bean:message key="command.result" bundle="<%=interfaces%>"/>
                        </a>
                        
                  
                  </td></tr></table>                  
              <span id="contents<%=bean.getServeyId()%>"></span>
              </div>
          </div>
        </logic:iterate>
        </logic:present>
</div>
</div></div>
</div>
</div>

     