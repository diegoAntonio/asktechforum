<%@ include file="cabecalho.jsp"%>

<br />
<br />
<br />

<form id="formConsultarPerguntaPorTag" action="ServletConsultarPerguntaPorTag" method="post">

	<div id="site_content">
		<div class="content">
			<h1>Cadastre-se</h1>
			<div class="content_item">
				<div class="form_settings_cadastro">
					<p>
						<span>Pergunta* </span>
						<input class="contact"  value="${pergunta.titulo}" type="text" name="titulo" id="titulo" />
					</p>
					<p>
						<span>Descricao </span>
						<textarea class="contact"  name="descricao" rows="8" cols="20" > ${pergunta.descricao} </textarea>
					</p>
					<p>
						<span>Tags relacionadas. separe por espaços* </span>
						<input class="contact" value="${pergunta.tag}" type="text" name="tag" id="tag" />
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
<jsp:include page="rodape.jsp"></jsp:include>