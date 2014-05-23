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
				
			</h1>
			<c:forEach items="${resposta}" var="resposta">
				<div class="content_item">
					<div class="form_settings_cadastro">
						<p>
							<span>Autor </span> <input type="text" "
								id="autorResposta" value="${resposta.autor}" />
						</p>
						<p>
							<span>Resposta </span> <input type="text" 
								id="descricaoResposta" value="${resposta.decricao}" />
						</p>
						<p>
							<span>Data </span> <input type="datetime"
								id="descricaoData" value="${resposta.data}" />
						</p>
						<p>
							<span>Hora </span> <input type="datetime"
								id="descricaoHora" value="${resposta.hora}" />
						</p>
						<br /> <br /> <br />
					</div>
				</div>
			</c:forEach>
		</div>

	</div>
</form>
<jsp:include page="rodape.jsp"></jsp:include>