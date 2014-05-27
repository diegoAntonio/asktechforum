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
		<c:if test="${not empty pergunta}">
			<c:forEach items="${pergunta}" var="pergunta">
				<c:if test="${not empty pergunta.titulo}" >
					<div class="content">
						<h1>
		
							<a  href="ServletConsultarRespostaPergunta?id=${pergunta.idPergunta}
								&autor=${pergunta.autor}
								&descricao=${pergunta.descricao}&titulo=${pergunta.titulo}">${pergunta.titulo}</a>
		
						</h1>
						<div class="content_item">
							<div style="width: 700px;" class="form_settings_cadastro">
															
								<p><c:out value="${pergunta.descricao}"></c:out></p>
							    <p><a href="ServletConsultarPerguntaPorTag?tag=${pergunta.tag}">${pergunta.tag}</a></p>
								<output style="font-size: 11px; position: right;">Por: <c:out value="${pergunta.autor}"></c:out></output>
								<p><output style="font-size: 9px; position: right;">Em: <c:out value="${pergunta.data}"></c:out> às <c:out value="${pergunta.hora}"></c:out></output><p>
								Respostas:<c:out value=" ${pergunta.qtdResposta}"></c:out>
								
		
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</c:if>
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