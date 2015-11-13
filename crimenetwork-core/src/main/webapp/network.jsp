<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<title>SB Admin - Bootstrap Admin Template</title>
<style type="text/css">
.test1111 {
    transform:scale(1,2);
    display: block;
    height: 600px;
}
#mynetwork {
	width: 100%;
	height: 100%;
}
</style>
<!-- Bootstrap Core CSS -->
<link href="lib/bootstrap/bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="lib/sb-admin.css" rel="stylesheet">

<link href="lib/build.css" rel="stylesheet" />

<!-- Custom Fonts -->
<link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div id="page-wrapper">
		<div class="container-fluid">

			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Crime Network</h1>

				</div>
			</div>


			<div class="row">
				<div class="col-lg-4">
					<div class="panel panel-green">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-long-arrow-right"></i> Pie Chart Example with
								Tooltips
							</h3>
						</div>
						<div class="panel-body">
							<div class="checkbox">
								<input id="checkbox1" class="styled" type="checkbox"> <label
									for="checkbox1"> Default </label>
							</div>
							<div class="checkbox checkbox-primary">
								<input id="checkbox2" class="styled" type="checkbox" checked>
								<label for="checkbox2"> Primary </label>
							</div>
							<div class="checkbox checkbox-success">
								<input id="checkbox3" class="styled" type="checkbox"> <label
									for="checkbox3"> Success </label>
							</div>
							<div class="checkbox checkbox-info">
								<input id="checkbox4" class="styled" type="checkbox"> <label
									for="checkbox4"> Info </label>
							</div>
							<div class="checkbox checkbox-warning">
								<input id="checkbox5" type="checkbox" class="styled" checked>
								<label for="checkbox5"> Warning </label>
							</div>
							<div class="checkbox checkbox-danger">
								<input id="checkbox6" type="checkbox" class="styled" checked>
								<label for="checkbox6"> Check me out </label>
							</div>

							<div class="text-right">
								<button type="button" class="btn btn-primary">Primary</button>
							</div>

						</div>
					</div>
				</div>
				<div class="col-lg-8 test1111">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-long-arrow-right"></i> Multiple Axes Line Graph
								Example with Tooltips and Raw Data
							</h3>
						</div>
						<div class="panel-body">						 
							<div id="mynetwork"></div>						
						</div>
					</div>
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->

	</div>
	<!-- /#page-wrapper -->



	<!-- jQuery -->
	<script src="lib/jquery-1.11.3.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="lib/bootstrap/bootstrap.js"></script>
	<script src="lib/vis/vis.js"></script>
	<script src="js/network/crimenetwork.js"></script>


</body>

</html>
