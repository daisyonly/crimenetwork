<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<title>SB Admin - Bootstrap Admin Template</title>
<style type="text/css">
.changeHeight {
	width: 100%;
	height: 100%;
}

.bemiddle {
	vertical-align: middle;
}

.checkbox{padding-left:20px;margin:6px 0}

.labelNomal {
	font-weight: normal;
}

.inlinehere {
	display: inline-block;
}

#mynetwork {
	width: 100%;
	height: 500pt;
}
</style>
<link href="lib/vis/vis.css" rel="stylesheet" type="text/css" />
<!-- Bootstrap Core CSS -->
<link href="lib/bootstrap/bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="lib/sb-admin.css" rel="stylesheet">

<link href="lib/build.css" rel="stylesheet" />

<!-- Custom Fonts -->
<link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet"
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
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-long-arrow-right"></i> 
								请输入：
							</h3>
						</div>
						<div class="panel-body changeHeight">
							<div class="form-group">
								<label>嫌疑人：</label> <input class="form-control">
							</div>
							<div class="form-group">
								<label>案件：</label> <input class="form-control">
							</div>
							<div class="form-group">
								<label>假币：</label> <input class="form-control">
							</div>

							<label>请选取关系：</label>
							

							<div class="table-responsive">
								<table class="table table-hover">

									<tbody class="bemiddle">
										<tr>
											<td>
												<div class="checkbox checkbox-primary">
													<input id="checkbox1" class="styled" type="checkbox">
													<label for="checkbox1"> 人 —— 案件 </label>
												</div>
											</td>
											<td>
												<div class="form-inline">
													<label class="labelNomal">层数：</label> <input
														class="form-control">
												</div>
											</td>

										</tr>
										<tr>
											<td>
												<div class="checkbox checkbox-primary">
													<input id="checkbox2" class="styled" type="checkbox"
														checked> <label for="checkbox2"> 人 —— 假币 </label>
												</div>
											</td>
											<td><div class="form-inline">
													<label class="labelNomal">层数：</label> <input
														class="form-control">
												</div></td>

										</tr>
										<tr>
											<td><div class="checkbox checkbox-primary">
													<input id="checkbox3" class="styled" type="checkbox">
													<label for="checkbox3"> 案件 —— 假币 </label>
												</div></td>
											<td><div class="form-inline">
													<label class="labelNomal">层数：</label> <input
														class="form-control">
												</div></td>

										</tr>
										<tr>
											<td><div class="checkbox checkbox-primary">
													<input id="checkbox4" class="styled" type="checkbox">
													<label for="checkbox4"> 人 —— 通讯</label>
												</div></td>
											<td><div class="form-inline">
													<label class="labelNomal">层数：</label> <input
														class="form-control">
												</div></td>

										</tr>

									</tbody>
								</table>
							</div>


							<div class="text-right">
								<button type="button" class="btn btn-primary">生成</button>
							</div>

						</div>
					</div>
				</div>
				<div class="col-lg-8">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-long-arrow-right"></i> 
								网络生成如下：
							</h3>
						</div>
						<div class="panel-body changeHeight">
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
