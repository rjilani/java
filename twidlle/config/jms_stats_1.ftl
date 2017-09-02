<html>
<head>
  <style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;    
    
    background-color: #E6E6FA;
}
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script>
    
    $(function() { 

function groupTable($rows, startIndex, total){
if (total === 0){
return;
}
var i , currentIndex = startIndex, count=1, lst=[];
var tds = $rows.find('td:eq('+ currentIndex +')');
var ctrl = $(tds[0]);
lst.push($rows[0]);
for (i=1;i<=tds.length;i++){
if (ctrl.text() ==  $(tds[i]).text()){
count++;
$(tds[i]).addClass('deleted');
lst.push($rows[i]);
}
else{
if (count>1){
ctrl.attr('rowspan',count);
groupTable($(lst),startIndex+1,total-1)
}
count=1;
lst = [];
ctrl=$(tds[i]);
lst.push($rows[i]);
}
}
}
groupTable($('#myTable tr:has(td)'),0,3);
$('#myTable .deleted').remove();
});
    
</script>
</head>
<body>
  <h1>JMS Stats</h1>
 <table border="1" style="width:100%">
<thead>
<tr>
    <th>Host</th>
    <th>Attribute</th>
    <th>Value</th>
  </tr>
</thead>
<#list modelArray as model>
  <tr>
    <td>${model.host}</td>
    <td>${model.attribute}</td>
    <td>${model.value}</td>
  </tr>
 </#list>
</table> 

</body>
</html> 