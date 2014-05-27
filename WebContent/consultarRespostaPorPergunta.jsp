<%@ include file="cabecalho.jsp"%>

<%
	String idPergunta = request.getParameter("id");
	String perguntaDescricao = request.getParameter("titulo");
	String perguntaAutor = request.getParameter("autor");
%>


<form id="formConsultarRespostaPorPergunta"
	action="ServletConsultarRespostaPergunta" method="post">
	
	<div id="site_content">	
			<div style="width: 1000px;" class="content">	
				<h1>
					<c:out value="${titulo}"></c:out>
					<output style="font-size: 16px; float: right;">Por: <c:out value="${autor}"></c:out> </output>
				</h1>
				<p style="margin-left: 5px;"><c:out value="  ${descricao}"></c:out></p>
			</div>
	</div>
		<div id="site_content">		
			<c:forEach items="${resposta}" var="resposta">
				<div class="content">
					<div class="content_item">
						<div  style="width: 900px;" class="form_settings_cadastro">
							<p><c:out value="${resposta.descricao}"></c:out></p>
							<output style="font-size: 11px; position: right;">Por: <c:out value="${resposta.nomeUsuario}"></c:out></output>
							<p><output style="font-size: 9px; position: right;">Em: <c:out value="${resposta.data}"></c:out> às <c:out value="${resposta.hora}"></c:out></output><p>
							
						</div>
						<br /> 
					</div>
				</div>
			</c:forEach>
		</div>
		<p style="padding-top: 15px; margin-left:180px;"><span>&nbsp;</span>
			<a href="usuarioAutenticado/responder.jsp">
			<input class="submit" type="button" name="btn_entrar" value="Responder" />
			</a>
		</p>
	</a>
</form>
<jsp:include page="rodape.jsp"></jsp:include>