<%@ include file="cabecalho.jsp"%>

<%
String tag = request.getParameter("tag");
%>

<%
if((Boolean)session.getAttribute("stop")) {
%>
	<body onload="carregarTag()" >    	
		<form id="formTagAll" action="ServletConsultarPerguntaPorTag" method="post">
			<input type="hidden" name="tag" id="tag" value="all" checked="checked" />
		</form>
	</body>
<%
}
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

<script>
	function carregarTag() {
		var formulario;
		formulario = document.getElementById("formTagAll");
		formulario.submit();
	}
</script>

<jsp:include page="rodape.jsp"></jsp:include>