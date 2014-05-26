<%@ include file="/cabecalho.jsp"%>

<br />
<br />
<br />

<form id="formconsultaPerguntaPorTag" action="<%=getServletContext().getContextPath()%>/ServletCadastroPergunta" method="post">

	<div id="site_content">
		<div class="content">
			<h1>Faça a sua pergunta</h1>
			<div class="content_item">
				<div style="width: 300px;" class="form_settings_cadastro">
					<p>
						<span>Pergunta* </span>
						<input class="contact"  value="${pergunta.titulo}" type="text" name="titulo" id="titulo" />
					</p>
					<p>
						<span>Descrição* </span>
						<textarea class="contact"  name="descricao" rows="8" cols="20" > ${pergunta.descricao} </textarea>
					</p>
					<p>
						<span>Assuntos relacionados*</span>
						<input class="contact" value="${pergunta.tag}" type="text" name="tag" id="tag" />
						<p style="color:#A4A4A4; font-size:10px;">.</p> 
					</p>
					
					<p>
					
						
						<c:if test="${empty erroCadastroPergunta}">
						<% session.setAttribute("erroCadastroPergunta", ""); %>
						</c:if>
						<p style="font-size: 12px; color: red;" id= msg><%=session.getAttribute("erroCadastroPergunta")%></p>
					
					</p>
					
					
					<p style="color:red; font-size:12px;">Os campos com * são obrigatórios.</p> 
					<input class="submit" value="Cadastrar" type="submit" /> 
					<br />
					<br />
					<br />
				</div>	
			</div>
		</div>
	</div>
</form>
<jsp:include page="/rodape.jsp"></jsp:include>