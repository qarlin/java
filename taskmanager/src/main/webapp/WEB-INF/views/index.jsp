<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Task Browser</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/app.css">
</head>
<body>
	<div id="nav"></div>
	<div id="browser">
		<section class="browser-header">
			<nav class="navbar">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand" href="#">Task Manager</a>
					</div>
					<div class="collapse navbar-collapse">
						<div class="btn-group">
							<a href="addTask.jsp" class="btn btn-default navbar-btn"
								data-toggle="modal" data-target="#myModal">Add</a> 
						</div>
						<form class="navbar-form navbar-right">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Search">
							</div>
							<button type="submit" class="btn btn-default">Submit</button>
						</form>
					</div>
				</div>
			</nav>
		</section>
		<section class="browser-body">
			<div>
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th class="center">tit1</th>
							<th>tit2</th>
							<th class="center">tit3</th>
							<th class="center">tit4</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="center"><h4>
									<span class="glyphicon glyphicon-adjust"></span>
								</h4></td>
							<td>
								<h4>ACTIVITY</h4>
								<p>
									277 Calories burned (6.1 Cal/Min) <br />3,500 steps taken
								</p>
							</td>
							<td class="center">
								<h4>18 MINUTES</h4>
								<p>4:37pm - 5.22pm</p>
							</td>
							<td class="center">Center</td>
						</tr>
						<tr>
							<td><h4>
									<span class="glyphicon glyphicon-adjust"></span>
								</h4></td>
							<td>
								<h4>ACTIVITY</h4>
								<p>
									277 Calories burned (6.1 Cal/Min) <br />3,500 steps taken
								</p>
							</td>
							<td>
								<h4>18 MINUTES</h4>
								<p>4:37pm - 5.22pm</p>
							</td>
							<td class="center">Center</td>
						</tr>
					</tbody>
				</table>
			</div>
		</section>
		<section class="browser-footer"></section>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>

	<script type="text/javascript" src="js/lib/jquery.js"></script>
	<script type="text/javascript" src="js/lib/underscore.js"></script>
	<script type="text/javascript" src="js/lib/bootstrap.js"></script>
</body>
</html>
