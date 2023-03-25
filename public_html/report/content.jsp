<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">         
function checkIds(obj,ids) {
    getObj('buttonRestore').style.display = (obj.checked)?'block':'none';
    checkment(ids);
}
function checkment(ids){
for (i = 0; i < ids.length; i++){          
        if(ids[i].checked==true){
            getObj('buttonRestore').style.display ='block';
            break;
        }
    }
}
function checkAllIds(checkname,exby) {

   if (checkname!=null){
         if(checkname.length>0){
           getObj('buttonRestore').style.display = (exby.checked)?'block':'none';
           for (i = 0; i < checkname.length; i++){          
             checkname[i].checked = exby.checked;    
           }    
         }else{       
           checkname.checked = exby.checked;   
           getObj('buttonRestore').style.display = (exby.checked)?'block':'none';
         }
    }
}
</script>


<div id="mailcol">
        <div class="tabmenu" id="container-1" >
            <div style="clear:both"></div>
            <ul id="ui-tabs-nav">
            <li class="ui-tabs-selected"></li><li class=""></li><li class=""></li>
            </ul>
            <div id="fragment-1">
                <div class="listDocs" id="MainDirectory">
                  <jsp:include page="/report/list.jsp" />  
                </div>
              
            </div>
                  
        </div>
    </div>       


