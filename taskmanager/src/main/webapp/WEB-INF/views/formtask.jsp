<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal-dialog">
	<div class="modal-content">
		<form:form class="form-horizontal" role="form" method="POST" modelAttribute="task" action="${formaction}">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">Create new Task</h4>
			</div>
			<div class="modal-body">
				<fieldset>
					<legend>Properties:</legend>
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label">
							<s:message code="formtask.name"/>
						</label>
						<div class="col-sm-10">
							<form:input path="name" class="form-control" id="inputNAme" />
						</div>
					</div>
					<div class="form-group">
						<label for="status" class="col-sm-2 control-label">
							<s:message code="formtask.status"/>
						</label>
						<div class="col-sm-10">
							<form:select path="status.code" class="form-control" id="code">
								<form:option value="">Select a Status</form:option>
          						<form:options items="${StatusTypes}" itemValue="code" itemLabel="description" />
							</form:select>
						</div>
					</div>
				</fieldset>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="submit" class="btn btn-primary">Save</button>
			</div>
		</form:form>
	</div>
</div>
	<!-- /.modal-content -->
