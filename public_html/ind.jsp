
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
	<title>Ajax - dynamic list</title>
	
	<style type="text/css">
	
	/* START CSS NEEDED ONLY IN DEMO */
	html{
		height:100%;
	}
	body{
		background-color:#E2EBED;
		font-family: Trebuchet MS, Lucida Sans Unicode, Arial, sans-serif;	
		width:100%;
		height:100%;		
		margin:0px;
		text-align:center;
	}
	
	#mainContainer{
		width:660px;
		margin:0 auto;
		text-align:left;
		height:100%;
		background-color:#FFF;
		border-left:3px double #000;
		border-right:3px double #000;
	}
	#formContent{
		padding:5px;
	}
	/* END CSS ONLY NEEDED IN DEMO */
	
	
	/* Big box with list of options */
	#ajax_listOfOptions{
		position:absolute;	/* Never change this one */
		width:175px;	/* Width of box */
		height:250px;	/* Height of box */
		overflow:auto;	/* Scrolling features */
		border:1px solid #317082;	/* Dark green border */
		background-color:#FFF;	/* White background color */
		text-align:left;
		font-size:0.9em;
		z-index:100;
	}
	#ajax_listOfOptions div{	/* General rule for both .optionDiv and .optionDivSelected */
		margin:1px;		
		padding:1px;
		cursor:pointer;
		font-size:0.9em;
	}
	#ajax_listOfOptions .optionDiv{	/* Div for each item in list */
		
	}
	#ajax_listOfOptions .optionDivSelected{ /* Selected item in the list */
		background-color:#317082;
		color:#FFF;
	}
	#ajax_listOfOptions_iframe{
		background-color:#F00;
		position:absolute;
		z-index:5;
	}
	
	form{
		display:inline;
	}
	
	</style>
	<script type="text/javascript" src="js/ajax.js"></script>
	<script type="text/javascript" src="js/ajaxlib.js"></script>
        <script type="text/javascript" src="js/ajax-dynamic-list.js"></script>
</head>
<body>
<div id="mainContainer">
	<div id="header">
		
	</div>
	<div id="formContent">
		<form name="from" action="/from.html">
		<fieldset>
			<legend>AJAX(Asyncron Javascript And XML) - list options</legend>
			<table border="0">
				<tr>
					<td><label for="country">Country: </label></td>
					<td><input type="text" id="contentSearch" name="contentSearch" value="" onkeyup="ajax_showOptions(this,'demo','from','Anchor=_SEARCH_DOS_REFERENCE',event)">
					<input type="hidden" id="country_hidden" name="country_ID"><!-- THE ID OF the country will be inserted into this hidden input --></td>
				</tr>	
				<tr>
					<td><label for="otherField">Other info:</label></td>	
					<td><input type="text" id="otherField" name="otherField" value=""></td>	
				</tr>
				<tr>
					<td>Gender:</td>
					<td><select name="gender">
						<option value="F">Female</option>
						<option value="M">Male</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label for="country">Country2: </label></td>
					<td><input type="text" id="country2" name="country2" value="" onkeyup="ajax_showOptions(this,'getCountriesByLetters',event)"></td>
				</tr>	
			</table>		
			<p>Type in something in the textfield "country" or "country2"</p>
		</fieldset>	
		
		</form>
	</div>
</div>

</body>
</html>