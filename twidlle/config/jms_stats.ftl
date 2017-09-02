<html>
<head>
  <link rel="stylesheet" href="http://cdn.datatables.net/1.10.3/css/jquery.dataTables.min.css">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://cdn.datatables.net/1.10.3/js/jquery.dataTables.min.js"></script>

        <script>
            $(document).ready(function () {
                $('#myTable').dataTable({
                    "lengthMenu": [[10, 20, 30,40, -1], [10, 20, 30,40, "All"]]
                });
            });
</script>
</head>
<body>
  <h1>JMX Stats</h1>
 <table id="myTable" class="display" border="1" style="width:100%">
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