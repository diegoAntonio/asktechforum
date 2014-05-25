<%@ include file="/cabecalho.jsp"%>

<br />
<br />
<br />

<form id="formconsultaPerguntaPorTag" action="ServletCadastroPergunta" method="post">

	<div id="site_content">
		<div class="content">
			<h1>Responder</h1>
			<div class="content_item">
				<div class="form_settings_cadastro">
					<p>
						<span>Descrição </span>
						<textarea class="contact"  name="descricao" rows="8" cols="20" > ${resposta.descricao} </textarea>
					</p>
					
					<p style="color:red; font-size:12px;">O campo Descrição é obrigatório.</p> 
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