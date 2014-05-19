<%@ include file="cabecalho.jsp" %>

<br/>
<br/>
<br/>

<form id="formAlteracaoPerfilUsuario" action="ServletAlteracaoUsuario" method="post">

	<div id="site_content">
		<div class="content">
			<h1>Editar Perfil</h1>
			<div class="content_item">
				<div class="form_settings_cadastro">
					<p>
						<input type="hidden" name="alteracaoUsuarioId" id="alteracaoUsuarioId" value="${usuario.idUsuario}" checked="checked" />
						<span>Nome* </span>
						<input class="contact" value="${usuario.nome}" type="text" name="nome" id="nome" />
					</p>
					<p>
						<span>Data de Nascimento </span>
						<input class="contact" type="date" value="${usuario.dataNascimento}" name="dataNascimento" id="dataNascimento" />
					</p>
					<p>
						<span>E-mail* </span>
						<input class="contact" value="${usuario.email}" type="email" name="email" id="email" />
					</p>
					<p>
						<span>Localização </span>
						<input class="contact" value="${usuario.localizacao}" type="text" name="localizacao" id="localizacao" />
					</p>
					<c:if test="${usuario.admin == true}">
					<p>
						<input style="width:20px;" value="${usuario.admin}" type="checkbox" checked="checked" name="admin" id="admin">Administrador</input>
					</p>
					</c:if>
					<p style="color:red; font-size:12px;">Os campos com * são obrigatórios.</p> 
					<input class="submit" value="Salvar" type="submit" /> 
					<br />
					<br />
					<br />
				</div>	
			</div>
		</div>
	</div>
</form>
<br/>
<br/>
<br/>

<jsp:include page="rodape.jsp"></jsp:include>