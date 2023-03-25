var theme="flick";
var tab_arr=new Array("0","0","0","0","0","0","0","0","0");
var tab=0;
var lov=1;
var dnl=false;
var sys_date=new Date();
var pdate=check_time(sys_date.getDate());
var pmonth=check_time(sys_date.getMonth()+1);
var pmonth_before=check_time(sys_date.getMonth());
var pyear=sys_date.getFullYear();

var year_proj = pyear;
var dtPrev = new Date();
dtPrev.setFullYear(pyear, 8, 30);
if (sys_date>dtPrev) {
  year_proj = pyear+1;
} 

var pquarter=1;
if(pmonth>=4 && pmonth<=6)			pquarter = 2;
else if(pmonth>=7 && pmonth<=9) 	pquarter =3;
else if(pmonth>=10 && pmonth<=12)	pquarter =4;

var perior_year=(pyear-1);
var ptime=pdate+"/"+pmonth+"/"+pyear;

var pperior_before=(parseInt(pmonth)==1) ? "12/" + perior_year : pmonth_before + "/" +  pyear;
var pma_cqt="",pten_cqt="",plevel="",pma_tinh="";
var filterNumber=/^([-]?[0-9]*|[-]?\d*[\.]\d{1}?\d*)$/;
var filterInt=/^[-]?\d+$/;
var filterTime=/^([0-1][0-9]|[2][0-3]):([0-5][0-9])$/;
var filterMonth=/^([0][1-9]|[1][0-2])\/([2][0][0-1][0-9])$/;
var filterYear= /^([1-2][0-9][0-9][0-9])$/;
var filterTin=/^[0-9]{0,10}\-{0,1}[0-9]{0,3}$/;
//var filterTax=/^[0-9]{10}(\-[0-9]{3}?)$/;

var pmonth_print=get_month_print(sys_date.getMonth()+1);
var pyear_print=((parseInt(pmonth_print)>10) ? perior_year:pyear);
var pquarter_print=((parseInt(pmonth_print)>10) ? 4:pquarter);

var ptu_ky=pmonth_print+"/"+pyear_print;
var pden_ky=pmonth_print+"/"+pyear_print;

var do_dai = 280; /* chieu rong cua grid tinh_cqt */

function check_time(i){
 if (i<10) i="0"+i;
 return i;
}

function get_month_print(i){	
	if(i==1) 		i=11;
	else if(i==2)	i=12;
	else 			i=i-2;	
	if(i<10)	i="0"+i;
	return i;
}

function go_url(url) {
	window.location = url;
}

function trim(str){
  str=str.replace(/^\s*|\s*$/g,"");
  str=str.replace(/&nbsp;/g," ");
  return str;
}

function isEmpty(val){
	return(val==null|| trim(val)=="");
}

function isRequired(ctr){
  if (isEmpty(ctr.val())) 
  {
     alert("Tr??ng b?t bu?c ph?i nh?p giá tr? !");
     setTimeout(function(){ctr.focus();ctr.select();},10);
     return false;
  }
  return true;
}

function isSelected(ctr,val){
  if (ctr.val()==val) 
  {
     alert("Tr??ng b?t bu?c ph?i nh?p giá tr? !");
     setTimeout(function(){ctr.focus();ctr.select();},10);
     return false;
  }
  return true;
}

function LeapYear(intY) 
{
    if (intY % 100 == 0)
    {
      if (intY % 400 == 0)   return true;
    }
    else
    {
      if ((intY % 4) == 0) return true;
    }
    return false;
}
function validDate(strD,strM,strY)
{
    var intY=parseInt(strY,10);
    if (isNaN(intY)) return false;
    var intD=parseInt(strD,10);
    if (isNaN(intD)) return false;
    if (intD<1)      return false;
    var intM=parseInt(strM,10);
    if (isNaN(intM))      return false;
    if (intM>12||intM<1)  return false;
    if ((intM == 1 || intM == 3 || intM == 5 || intM == 7 || intM == 8 || intM == 10 || intM == 12) && (intD > 31)) return false;
    if ((intM == 4 || intM == 6 || intM == 9 || intM == 11) && (intD > 30)) return false;
    if (intM == 2) 
    {
     if (LeapYear(intY))
     {
      if (intD>29) return false;
     }
     else
     {
      if (intD>28) return false;
     }
    }
    return true;
}

function d2n(str)
{
   var arr=str.split("/");
   return parseInt(arr[2]+arr[1]+arr[0]);
}
function m2n(str)
{
   var arr=str.split("/");
   return parseInt(arr[1]+arr[0]);
}

function check_key(evt,key,func,param)
{
	  var key_code=evt.which || evt.keyCode;
	  if (key_code==key) 
	  {	  
		  evt.preventDefault();
		  func(param);
	  }  
}
function check_esc(event)
{
  var key_code=event.which || event.keyCode;
  if (key_code==27) 
  {	  
	  event.preventDefault();
	  go_url('index');
  }  
}

function toggle(id,imgid){
	var td=$("#"+id);
	if (td.css('display')=='none') $("#"+imgid).attr("src","image/minus.gif");
	else					 	   $("#"+imgid).attr("src","image/plus.gif");
	td.toggle();
}
function compare_year(fy,ty){
  var tu=fy.val();
  var den=ty.val();
  if (isEmpty(tu)||isEmpty(den)) return true;
  if (parseInt(tu)>parseInt(den)) 
  {
	  alert("T? n?m ph?i nh? h?n ho?c b?ng ??n n?m!"); 
	  setTimeout(function(){fy.focus();fy.select();},10);
	  return false;
  } 
  return true;
}
function compare_date(fd,td){
	var tu=fd.val();
	var den=td.val();
	if (isEmpty(tu)||isEmpty(den)) return true;
	if (d2n(tu)>d2n(den)){
	  alert("T? ngày ph?i nh? h?n ho?c b?ng ??n ngày!");
	  setTimeout(function(){fd.focus();fd.select();},10);
	  return false;
	} 
	return true;
}


function compareDate(fd,td){
	var tu=fd.val();
	var den=td.val();
	if (isEmpty(tu)||isEmpty(den)) return true;
	if (d2n(tu)>d2n(den)){
	  alert("T? ngày ph?i nh? h?n ho?c b?ng ??n ngày!");
	  setTimeout(function(){fd.focus();fd.select();},10);
	  return false;
	} 
	return true;
}

function compare_month(fm,tm){
	var tu=fm.val();
	var den=tm.val();
	if (isEmpty(tu)||isEmpty(den)) return true;
	if (m2n(tu)>m2n(den)){
	  alert("T? k? ph?i nh? h?n ho?c b?ng ??n k?!");
	  setTimeout(function(){fm.focus();fm.select();},10);
	  return false;
	} 
	return true;
}

function compare_month_report(fm,tm){	
	if (isEmpty(fm)||isEmpty(tm)) return true;
	if (m2n(fm)>=m2n(tm)){
	  alert("K? báo cáo ph?i nh? h?n k? hi?n t?i!");	      
	  return false;
	} 
	return true;
}

function compareDate(fd,td)
{
  if (d2n(fd)>d2n(td)) 
  {
	  alert("Tá»« ngÃ y pháº£i nhá»? hÆ¡n hoáº·c báº±ng Ä‘áº¿n ngÃ y!"); 
	  return false;
  } 
  return true;
}

function compareYear(ctr1,ctr2) {
	var str1 = ctr1.val();
	var str2 = ctr2.val();
	if (str1.substr(str1.length - 4, str1.length) != str2.substr(str2.length - 4, str2.length)) {
		alert('Chá»‰ cho phÃ©p tra cá»©u dá»¯ liá»‡u trong má»™t nÄƒm!');
		setTimeout(function(){ctr1.focus();ctr1.select();},10);
		return false;
	}
	return true;
}

function toNumber(pNumber)
{ 
	var s =	new String(pNumber);
	s = s.replace(/ /g,'');
	return s;
}

function toNumberComma(pNumber)
{ 
	var s =	new String(pNumber);
	s = s.replace(/,/g,'');
	return s;
}

function addSeparators(input){
	var nStr = input.value.replace(/,/g,'');
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(nStr)) {
		nStr = nStr.replace(rgx, '$1' + ',' + '$2');
	}
	input.value = nStr;
}

function formatInt(evt)
{
    //var key_code=(evt.which)?evt.which:(window.event?window.event.keyCode:0);
    var key_code=evt.which||evt.keyCode;
    return (key_code<=31||(key_code>=48&&key_code<=57));
}
function formatNumber(evt)
{
	var key_code=evt.which||evt.keyCode;
	return (key_code<=31||key_code==46||(key_code>=48&&key_code<=57));
}
function formatDate(evt,ctrName) 
{
     var key_code=evt.which||evt.keyCode;
     if (!(key_code<=31||(key_code>=48&&key_code<=57)))  return false;
     var ctr=eval(ctrName);
     var val=ctr.value;
     var len=val.length;
     if ((len==2||len==5) && key_code!=8) ctr.value=val+"/";
     return true; 
}

function formatMonth(evt,ctrName) 
{
  var key_code=evt.which||evt.keyCode;	
  if (!(key_code<=31||(key_code>=48&&key_code<=57)))  return false;
  var ctr=eval(ctrName);
  var val=ctr.value;
  if (val.length==2 && key_code!=8 ) ctr.value=val+"/";
  return true;
}

function isInt(ctrName) 
{
	var ctr=eval(ctrName);
	var val=ctr.value;
	if (isEmpty(val))  return true;
	if (!filterInt.test(val)) 
	{
		alert('Ph?i nh?p s? nguyên!');
		setTimeout(function(){ctr.focus();ctr.select();},10);
		return  false;
	}
	return  true;
}

function isIntComma(ctrName) 
{
	var ctr=eval(ctrName);
	var val=ctr.value.split(',').join('');
	if (isEmpty(val))  return true;
	if (!filterInt.test(val)) 
	{
		alert('Ph?i nh?p s? nguyên!');
		setTimeout(function(){ctr.focus();ctr.select();},10);
		return  false;
	}
	return  true;
}

function isNumber(ctrName) 
{
	var ctr=eval(ctrName);
	var val=ctr.value;
	if (isEmpty(val))  return true;
	if (!filterNumber.test(val)) 
	{
		alert('Ph?i nh?p ki?u s?!');
		setTimeout(function(){ctr.focus();ctr.select();},10);
		return  false;
	}
	return  true;
}
function isMonth(ctrName)
{
  var ctr=eval(ctrName);
  var val=ctr.value;
  if (isEmpty(val)) return true;
  var arr=val.split("/");
  if (arr.length!=2)
  {
     alert("Ph?i nh?p ki?u tháng (mm/yy)!");
     setTimeout(function(){ctr.focus();ctr.select();},10);
     return false;
  }
  var mm=arr[0];
  var yy=arr[1];
  if  (mm.length<2)  mm="0"+mm;
  if  (yy.length==2) yy="20"+yy;
  var int_mm=parseInt(mm,10);
  if (yy.length!=4){
	 alert("Ph?i nh?p ki?u tháng (mm/yy)!");
     setTimeout(function(){ctr.focus();ctr.select();},10);
     return false;
  }
  if (int_mm>12||int_mm<1)
  {
    alert("Giá tr? tr??ng này không h?p l?!");
    setTimeout(function(){ctr.focus();ctr.select();},10);
    return false;
  }
  var int_yy=parseInt(yy,10);
  if (int_yy<2006)
  {
    alert("Ph?i nh?p n?m l?n h?n 2005!");
    setTimeout(function(){ctr.focus();ctr.select();},10);
    return false;
  }
  ctr.value=mm+"/"+yy;
  return true;
}

function isYear(ctrName){
  var ctr=eval(ctrName);
  var val=ctr.value;
  if (isEmpty(val)) return true;
  if (val.length==2) val="19"+val;
  var int_yy=parseInt(val,10);
  if (!filterYear.test(int_yy))
  {
    alert("Ph?i nh?p n?m theo ??nh d?ng (yyyy)!");
    setTimeout(function(){ctr.focus();ctr.select();},10);
    return false;
  }
  ctr.value=val;
  return true;
}

function isDate(ctrName)
{
   var ctr=eval(ctrName);
   var strD=ctr.value;
   var len=strD.length;
   if (len<1) return true;
   if (len!=8 && len!=10)
   {
	   alert("Ph?i nh?p ki?u ngày (dd/mm/yy)!");
	   setTimeout(function(){ctr.focus();ctr.select();},10);
	   return false;
   }
   var arr=strD.split("/");
   if (arr.length!=3)
   {
      alert("Ph?i nh?p ki?u ngày (dd/mm/yy)!");
      setTimeout(function(){ctr.focus();ctr.select();},10);
      return false;
   }
   strD=arr[0];
   var strM=arr[1];
   var strY=arr[2];
   if (strY.length==2) strY="20"+strY;
   if (!validDate(strD,strM,strY))
   {
      alert("Giá tr? tr??ng này không h?p l?!");
      setTimeout(function(){ctr.focus();ctr.select();},10);
      return false;
   }
   ctr.value=strD+"/"+strM+"/"+strY;
   return true;
}
function is_date(ctr){
	var strD=ctr.val();
	var len=strD.length;
    if (len<1)  return true;
	if (len!=8 && len!=10) 
	{
		alert("Ph?i nh?p ki?u ngày (dd/mm/yy)!");
		setTimeout(function(){ctr.focus();ctr.select();},10);
	    return false;
	}	
	var strM,strY;
	var arr=strD.split("/");
	if (arr.length!=3)     
	{
		alert("Ph?i nh?p ki?u ngày (dd/mm/yy)!");
		setTimeout(function(){ctr.focus();ctr.select();},10);
	    return false;
	}	
    strD=arr[0];
    strM=arr[1];
    strY=arr[2];
	if (strY.length==2) strY="20"+strY;
	if (!validDate(strD,strM,strY))
	{
    	alert("Ph?i nh?p ki?u ngày (dd/mm/yy)!");
    	setTimeout(function(){ctr.focus();ctr.select();},10);
	    return false;
	}
	return true;
}


function get_last_day_of_month(val){
	 if (isEmpty(val)) return "";
	 var arr=val.split("/");
	 if (arr.length!=2) return "";
	 return days_in_month(arr[0],arr[1])+"/"+val;
}

function days_in_month(month, year) {
	var m = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
	if (month != 2)    return m[month - 1];
	if (year % 4 != 0) return m[1];
	if (year % 100 == 0 && year % 400 != 0) return m[1];
	return m[1] + 1;
}