<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<h3> Shout dashboard </h3>
<acme:list2 readonly="true">
	<acme:list-column code="administrator.task.list.label.totalShouts" path="totalShout" width="14%"/>
	<acme:list-column code="administrator.task.list.label.trueShouts" path="trueShouts" width="14%"/>
	<acme:list-column code="administrator.task.list.label.ratioEUR" path="ratioEUR" width="14%"/>
	<acme:list-column code="administrator.task.list.label.avgEUR" path="avgCurrencyEUR" width="14%"/>	
	<acme:list-column code="administrator.task.list.label.avgUSD" path="avgCurrencyUSD" width="14%"/>	
	<acme:list-column code="administrator.task.list.label.eurDev" path="eurDev" width="14%"/>	
	<acme:list-column code="administrator.task.list.label.usdDev" path="usdDev" width="14%"/>	
</acme:list2>
<h3> Task dashboard </h3>
<acme:list2 readonly="true">
	<acme:list-column code="administrator.task.list.label.publicTasks" path="publicTasks" width="25%"/>
	<acme:list-column code="administrator.task.list.label.privateTasks" path="privateTasks" width="25%"/>
	<acme:list-column code="administrator.task.list.label.finishedTasks" path="finishedTasks" width="25%"/>	
	<acme:list-column code="administrator.task.list.label.nonFinishedTasks" path="nonFinishedTasks" width="25%"/>	
</acme:list2>

<acme:list2 readonly="true">	
	<acme:list-column code="administrator.task.list.label.averageWorkFlow" path="averageWorkFlow" width="25%"/>
	<acme:list-column code="administrator.task.list.label.deviationWorkFlow" path="deviationWorkFlow" width="25%"/>
	<acme:list-column code="administrator.task.list.label.maxWorkFlow" path="maxWorkFlow" width="25%"/>	
	<acme:list-column code="administrator.task.list.label.minWorkFlow" path="minWorkFlow" width="25%"/>	
</acme:list2>

<acme:list2 readonly="true">
	<acme:list-column code="administrator.task.list.label.averageExecutionPeriod" path="averageExecutionPeriod" width="25%"/>
	<acme:list-column code="administrator.task.list.label.deviationExecutionPeriod" path="deviationExecutionPeriod" width="25%"/>
	<acme:list-column code="administrator.task.list.label.maxExecutionPeriod" path="maxExecutionPeriod" width="25%"/>	
	<acme:list-column code="administrator.task.list.label.minExecutionPeriod" path="minExecutionPeriod" width="25%"/>
</acme:list2>


