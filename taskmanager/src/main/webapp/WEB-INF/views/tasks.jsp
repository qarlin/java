<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Task Browser</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>
	<div id="header">
		<div class="websiteNav">
			<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#"><span class="glyphicon glyphicon-home"></span> LOGO</a>

				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#"><span class="glyphicon glyphicon-briefcase"></span> TASK</a></li>
						<li><a href="#"><span class="glyphicon glyphicon-camera"></span> XXX</a></li>
						<li><a href="#"><span class="glyphicon glyphicon-adjust"></span> YYY</a></li>
					</ul>
					<p class="navbar-text navbar-right">
						<a class="navbar-link" href="?language=en">en</a>
						<a class="navbar-link" href="?language=es">es</a>
					</p>
				</div>
			</div>
			</nav>
		</div>
		<div class="pageNav">
			<nav class="navbar">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand" href="#">Task Manager</a>
					</div>
					<div class="collapse navbar-collapse">
						<div class="btn-group">
							<a href="${pageContext.request.contextPath}/tasks/add" class="btn btn-default navbar-btn"
								data-toggle="modal" data-target="#myModal">Add</a>
						</div>
						<form class="navbar-form navbar-right" method="POST" action="search">
							<div class="form-group">
								<input type="search" class="form-control" placeholder="Search">
							</div>
							<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
						</form>

					</div>
				</div>
			</nav>
		</div>
	</div>
	<div id="content">
		<div class="panel">
			<div class="panel-body">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th class="center">Id</th>
							<th>Name</th>
							<th class="center">Actions</th>
							<th class="center">Execution</th>
							<th class="center">Schedule</th>
							<th class="center">Delete</th>
							<th class="center">Run</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="task" items="${tasks}">
						<tr>
							<td class="center">${task.uid}</td>
							<td>
								<h4><a href="${pageContext.request.contextPath}/tasks/edit/${task.uid}" data-toggle="modal" data-target="#myModal">${task.name}</a></h4>
								<p> Created: ${task.creationDate} </p>
							</td>
							<td class="center">${task.status.description}</td>
							<td class="center">${fn:length(task.executionLogs)}</td>
							<td class="center">${fn:length(task.schedules)}</td>
							<td class="center"><a href="${pageContext.request.contextPath}/tasks/delete/${task.uid}">Delete</a></td>
							<td class="center"><a href="${pageContext.request.contextPath}/tasks/execute/${task.uid}">Run</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>

	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/lib/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/lib/underscore.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/lib/bootstrap.js"></script>
	
	<script type="text/javascript">
		var $modal = $('#myModal');
	 	var $linkModal = $('a[data-toggle="modal"]');
	 	 
		$linkModal.on('click', function(){
			var ref = $(this).attr('href');
		    $modal.load(ref, '', function(){
	    	 	 $modal.modal();
	   		});
		});
	</script>
</body>
</html>
