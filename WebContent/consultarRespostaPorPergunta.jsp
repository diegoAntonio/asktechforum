<%@ include file="cabecalho.jsp"%>

<%
	String idPergunta = request.getParameter("idPergunta");
	String perguntaDescricao = request.getParameter("titulo");
	String perguntaAutor = request.getParameter("autor");
	String perguntaTitulo = request.getParameter("descricao");
	
	if(request.getAttribute("titulo") != null) {
		perguntaDescricao = (String)request.getAttribute("titulo");
		perguntaAutor = (String)request.getAttribute("autor");
		perguntaTitulo = (String)request.getAttribute("descricao");
	}
%>

<form id="formConsultarRespostaPorPergunta"
	action="ServletConsultarRespostaPergunta" method="post">
	
	<div id="site_content">	
			<div style="width: 1000px;" class="content">	
				<h1>
					<output name="titulo"><%=perguntaDescricao%></output>
					<output name="autor" style="font-size: 16px; float: right;">Por: <%=perguntaAutor%></output>
				</h1>
				&nbsp;&nbsp;&nbsp;<output name="descricao" style="margin-left: 5px;"><%=perguntaTitulo%></output>
				<br>
				<br>
			</div>
	</div>
	<div id="site_content">		
		<c:forEach items="${resposta}" var="resposta">
			<div class="content">
				<div class="content_item">
					<div  style="width: 900px;" class="form_settings_cadastro">
						<p>
						    <c:out value="${resposta.descricao}"></c:out>						
							<span style="float: right; font-weight:bold;">
							   	<%
						           	usuarioLogado = (Usuario)session.getAttribute("usuarioLogado");
						          	if(usuarioLogado != null) {
						         %>
										<a href="<%=getServletContext().getContextPath()%>/ServletCadastroResposta?idR=${resposta.idResposta}">
										<img style="width:40px;" src="images/like.gif"></a>
								
								<% }								
	 								if(idPergunta != null) {
		 								session.setAttribute("idPergunta", idPergunta);
		 							}
		 								session.setAttribute("isVotar", true);
										session.setAttribute("descricao", perguntaTitulo);
										session.setAttribute("autor", perguntaAutor);
										session.setAttribute("titulo", perguntaDescricao);
									%>
	 							<br>Votos: ${resposta.votos}
							</span>						
						<p>
							<output style="font-size: 11px; position: right;">Por: <c:out value="${resposta.nomeUsuario}"></c:out></output>
							<br>						
							<output style="font-size: 9px; position: right;">Em: <c:out value="${resposta.data}"></c:out> às <c:out value="${resposta.hora}"></c:out></output>
						</p>
					</div>
				</div>
			</div>
		</c:forEach>
		<a href="usuarioAutenticado/responder.jsp">
			<input class="submit" type="button" name="btn_responder" value="Responder" />
		</a>
	</div>
</form>
<form id= "formVotarResposta" action="ServletCadastroResposta" method="post">
</form>

<script>
	function submeterFormVotarResposta() {
		var formulario = document.getElementById("formVotarResposta");
		formulario.submit();
	}
</script>

<jsp:include page="rodape.jsp"></jsp:include>