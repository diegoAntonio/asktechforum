<%@ include file="cabecalho.jsp"%>

<br />
<br />
<br />


<%
	String tag = request.getParameter("tag");
%>


<form id="formConsultarPerguntaPorTag" action="ServletConsultarPerguntaPorTag" method="post">
	<div id="site_content">
		<c:forEach items="${pergunta}" var="pergunta">
			<div class="content">
				<h1>
					
					<a  href="ServletConsultarRespostaPergunta?id=${pergunta.idPergunta}
						&autor=${pergunta.autor}
						&descricao=${pergunta.descricao}">${pergunta.descricao}</a>
				
				</h1>
				<div class="content_item">
					<div class="form_settings_cadastro">
						<p>
							<span>Autor </span> <input type="text" name="usuarioRadio"
								id="usuario" value="${pergunta.autor}" />
						</p>
						<p>
							<span>Quantidade </span> <input type="text" name="usuarioRadio"
								id="usuario" value="${pergunta.qtdResposta}" />
						</p>


						<br /> <br /> <br />
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</form>
<jsp:include page="rodape.jsp"></jsp:include>