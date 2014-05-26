<%@ include file="cabecalho.jsp"%>

<%
	String tag = request.getParameter("tag");
%>

<form id="formConsultarPerguntaPorTag" action="ServletConsultarPerguntaPorTag" method="post">
	<div id="site_content">
		<c:forEach items="${pergunta}" var="pergunta">
			<div class="content">
				<h1>
				<input type="hidden" value="${pergunta.idPergunta}" name="idPergunta" >
				<input type="hidden" value="${pergunta.autor}" name="autor" >
				<input type="hidden" value="${pergunta.descricao}" name="descricao" >
				<input type="hidden" value="${pergunta.titulo}" name="titulo" >
					<a  href="ServletConsultarRespostaPergunta?id=${pergunta.idPergunta}
						&autor=${pergunta.autor}
						&descricao=${pergunta.descricao}&titulo=${pergunta.titulo}">${pergunta.titulo}</a>

				</h1>
				<div class="content_item">
					<div class="form_settings_cadastro">
										
						<p><c:out value="${pergunta.descricao}"></c:out></p>
						<output style="font-size: 11px; position: right;">Por: <c:out value="${pergunta.autor}"></c:out></output>
						<p><output style="font-size: 9px; position: right;">Em: <c:out value="${pergunta.data}"></c:out> às <c:out value="${pergunta.hora}"></c:out></output><p>
						Respostas:<c:out value=" ${pergunta.qtdResposta}"></c:out>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</form>
<jsp:include page="rodape.jsp"></jsp:include>