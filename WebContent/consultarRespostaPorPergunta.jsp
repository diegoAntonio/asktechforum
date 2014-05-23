<%@ include file="cabecalho.jsp"%>

<br />
<br />
<br />


<%
	String idPergunta = request.getParameter("id");
	String perguntaDescricao = request.getParameter("titulo");
	String perguntaAutor = request.getParameter("autor");
%>


<form id="formConsultarRespostaPorPergunta"
	action="ServletConsultarRespostaPergunta" method="post">

	<div id="site_content">

		<div class="content">
			<h1>
				<c:out value="${descricao}"></c:out>
				<c:out value="${autor}"></c:out>
			</h1>
			<c:forEach items="${resposta}" var="resposta">
				<div class="content_item">
					<div class="form_settings_cadastro">
						<p>
							<c:out value="${resposta.descricao}"></c:out>
							
						</p>
						<p>
							<c:out value="${resposta.data}"></c:out>
							
						</p>
						<p>
							<c:out value="${resposta.hora}"></c:out>
							
						</p>
						<br /> <br /> <br />
					</div>
					<br /> 
				</div>
			</c:forEach>
		</div>

	</div>
</form>
<jsp:include page="rodape.jsp"></jsp:include>