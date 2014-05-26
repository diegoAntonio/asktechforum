<%@ include file="cabecalho.jsp"%>

<%
	String tag = request.getParameter("tag");
%>




<form id="formConsultarPerguntaPorTodasTags" action="ServletConsultarPerguntaPorTag" method="post">
	<div id="site_content">
		<c:forEach items="${pergunta}" var="pergunta">
			<div class="content">
				<h1>

					<a  href="ServletConsultarRespostaPergunta?id=${pergunta.idPergunta}
						&autor=${pergunta.autor}
						&descricao=${pergunta.descricao}&titulo=${pergunta.titulo}">${pergunta.titulo}</a>

				</h1>
				<div class="content_item">
					<div class="form_settings_cadastro">
						<p>	<span style="font-size: 16px; ">${pergunta.descricao}</span></p>
						
						<p><a href="ServletConsultarPerguntaPorTag?tag=${pergunta.tag}">${pergunta.tag}</a></br>
							<span>Por: ${pergunta.autor}</span> 						
							<span>Respostas: ${pergunta.qtdResposta}</span></p>
						

					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</form>
<jsp:include page="rodape.jsp"></jsp:include>