<%@ include file="cabecalho.jsp"%>

<br />
<br />
<br />

<form id="formCadastroUsuario" action="ServletCadastroUsuario" method="post">

	<div id="site_content">
		<div class="content">
			<h1>Cadastro</h1>
			<div class="content_item">
				<div style="width: 320px;" class="form_settings_cadastro">
					<p>
						<span>Nome* </span>
						<input class="contact" value="${usuario.nome}" type="text" name="nome" id="nome" />
					</p>
					<p>
						<span>Data de Nascimento </span>
						<input class="contact" type="date" value="${usuario.dataString}" name="dataNascimento" id="dataNascimento" />
						<span style="color:red; font-size:12px;">O campo 'Data de Nascimento' deve estar no<br/>formato: dd/MM/yyyy</span> 
					</p>
					<p>
						<span>E-mail* </span>
						<input class="contact" value="${usuario.email}" type="email" name="email" id="email" />
						<span style="color:red; font-size:12px;">O campo 'E-mail' deve estar num formato válido</span> 
					</p>
					<p>
						<span>Localização </span>
						<input class="contact" value="${usuario.localizacao}" type="text" name="localizacao" id="localizacao" />
					</p>
					<p>
						<span>Senha* </span>
						<input class="contact" value="${usuario.senha}" type="password" name="senha" id="senha" />
						<span style="color:red; font-size:12px;">O campo 'Senha' deve conter no máximo 8 caracteres</span> 
					</p>
				    <p>
				    	<span>Confirmar Senha* </span>
				    	<input class="contact" value="${usuario.confSenha}" type="password" name="confsenha" id="confsenha" />
						<span style="color:red; font-size:12px;">Os campos 'Senha' e 'Confirmar Senha'
							devem ser iguais</span> 
				    </p>
					<br/>
					<p style="color:red; font-size:12px;">Os campos com * são obrigatórios</p> 
					
					<c:if test="${emailExistente == true}">
						<p style="color:red; font-size:12px;">Este Email já está cadastrado no AskTechForum.<br/>Tente outro ;)</p> 
					</c:if>

					<input class="submit" value="Cadastrar" type="submit" /> 
					<input class="submit" value="Voltar" type="button" onclick="voltar()" />
					<br />
					<br />
					<br />
				</div>	
			</div>
		</div>
	</div>
</form>

<script>
	function voltar() {
		history.back();
	}
</script>

<jsp:include page="rodape.jsp"></jsp:include>