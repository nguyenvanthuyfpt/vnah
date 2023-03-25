<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

  <div class="content-calendar-2">
<table class="tableForm" cellpadding="0" width="100%" align="center" style="border-collapse: collapse" cellspacing="0" border="0">    
      <tr>
                <td rowspan="6" width="96px">
                    <img src='people/' width="104px" height="156px" id="imgPerson">
                <img style="border:1px solid red;" width="96px" height="100px" src="<%=contextPath%>/images/members/memersss.jpg " >
                </td>
                <td  nowrap="nowrap" width="120px">T&#7881;nh, Tp:</td>
                <td  align="left" >
                        <SELECT NAME="tinhtp" style="width:120px" >
                                <OPTION>&#272;&#224; n&#7861;ng</OPTION>
                                <OPTION>S&#243;c ch&#259;ng</OPTION>
                                <OPTION>B&#7871;n tre</OPTION>
                                <OPTION>Qu&#7843;ng ng&#227;i</OPTION>
                                <OPTION>H&#242;a b&#236;nh</OPTION>
                        </SELECT>
                 </td>
                <td nowrap="nowrap" width="120px">H&#7885; v&#224; t&#234;n:</td>
                <td align="left"  nowrap>
                  <input type="text" name="tinhtp" id="tinhtp" style="width:120px" />
                </td>
      </tr>
    <tr>
                <td  nowrap="nowrap" width="120px">Qu&#7853;n, Huy&#7879;n:</td>
                <td  align="left" >
                        <SELECT NAME="tinhtp" style="width:120px" >
                                <OPTION>Huy&#7879;n 1</OPTION>
                                <OPTION>Huy&#7879;n 2</OPTION>
                                <OPTION>Huy&#7879;n 3</OPTION>
                                <OPTION>Huy&#7879;n 4</OPTION>
                                <OPTION>Huy&#7879;n 5</OPTION>
                        </SELECT>
                 </td>
                <td nowrap="nowrap" width="120px">CMND:</td>
                <td align="left"  nowrap>
                  <input type="text" name="tinhtp" id="tinhtp" style="width:120px" />
                </td>
      </tr>
       <tr>
                <td  nowrap="nowrap" width="120px">X&#227;, Ph&#432;&#7901;ng:</td>
                <td  align="left" >
                        <SELECT NAME="tinhtp" style="width:120px" >
                                <OPTION>X&#227; 1</OPTION>
                                <OPTION>X&#227; 2</OPTION>
                                <OPTION>X&#227; 3</OPTION>
                                <OPTION>X&#227; 4</OPTION>
                                <OPTION>X&#227; 5</OPTION>
                        </SELECT>
                 </td>
                <td nowrap="nowrap" width="120px">Ng&#224;y sinh:</td>
                <td align="left"  nowrap>
                  <input type="text" name="tinhtp" id="tinhtp" style="width:120px" />
                </td>
      </tr>
      <tr>
                <td  nowrap="nowrap" width="120px">L&#224;ng, B&#7843;n, Th&#244;n ..:</td>
                <td  align="left" >
                        <SELECT NAME="tinhtp" style="width:120px" >
                                <OPTION>L&#224;ng 1</OPTION>
                                <OPTION>L&#224;ng 2</OPTION>
                                <OPTION>L&#224;ng 3</OPTION>
                                <OPTION>L&#224;ng 4</OPTION>
                                <OPTION>L&#224;ng 5</OPTION>
                        </SELECT>
                 </td>
                <td nowrap="nowrap" width="120px">Gi&#7899;i t&#237;nh:</td>
                <td align="left"  nowrap>
                  <input type="radio" name="sex" value="0">
                  Nam
                  <input type="radio" name="sex" value="1">
                  N&#7919;
                </td>
      </tr>
      
       <tr>
                <td  nowrap="nowrap" width="120px">S&#7889; nh&#224;, Ph&#7889;:</td>
                <td  align="left"  colspan="3" >
                                  <input type="text" name="tinhtp" id="tinhtp" style="width:320px" />
                 </td>
      </tr>
      <tr>
                <td  nowrap="nowrap" width="120px">&#7842;nh:</td>
                <td  align="left"  colspan="3">
                    <input type="file" name="upFile" value="" onchange="alert(this.value)" class="fieldText">
                </td>
      </tr>
</table>  

<br>

 
<table class="tableForm" cellpadding="0" width="100%" align="center" style="border-collapse: collapse" cellspacing="0" border="0">    

  <tr>
                <td  nowrap="nowrap" width="120px" valign="top">Chu&#7849;n &#273;o&#225;n:</td>
                <td  align="left" >
                        <textarea name="chuandoan" id="chuandoan" style="width:530px;height:50px"></textarea>
                 </td>
      </tr>
<tr>
                <td  nowrap="nowrap" width="120px">L&#253; do:</td>
                <td  align="left" >
                        <SELECT NAME="tinhtp" style="width:120px" >
                                <OPTION>L&#253; do 1</OPTION>
                                <OPTION>L&#253; do 2</OPTION>
                                <OPTION>L&#253; do 3</OPTION>
                                <OPTION>L&#253; do 4</OPTION>
                                <OPTION>L&#253; do 5</OPTION>
                        </SELECT>
                 </td>
      </tr>
      <tr>
      <td align="center">
            <html:button property="_EDIT" styleClass="button" onclick="post('disability',anchor + ':_SEARCH_RESULT');"   >                 
            C&#7853;p nh&#7853;t th&#244;ng tin
            </html:button>        
      </td>
      </tr>
</table>
</div>

 
