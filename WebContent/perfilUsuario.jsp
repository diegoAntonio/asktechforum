<%@ include file="cabecalho.jsp"%>

<br />
<br />
<br />

<div id="site_content">
	<div class="content">
		<h1>Perfil de ${usuarioPerfil.nome}</h1>
		<div class="content_item">
			<div class="form_settings_perfil">
				<form id="formPerfilUsuario" action="ServletAlteracaoUsuario" method="post">
				
					<p>
						<span>Nome: </span>
						<label>${usuarioPerfil.nome}</label>
					</p>
					<p>
						<span>Data de Nascimento: </span>
						<label><fmt:formatDate pattern="dd/MM/yyyy" value="${usuarioPerfil.dataNascimento}"/></label>
					</p>
					<p>
						<span>E-mail: </span>
						<label>${usuarioPerfil.email}</label>
						<input type="hidden" name="pesquisaUsuarioEmail" id="pesquisaUsuarioEmail" value="${usuarioPerfil.email}" checked="checked" />
					</p>
					<p>
						<span>Localização: </span>
						<label>${usuarioPerfil.localizacao}</label>
					</p>
					<p>
						<span>Administrador </span>
						<c:if test="${usuarioPerfil.admin == false}"><label>Não</label></c:if>
						<c:if test="${usuarioPerfil.admin == true}"><label>Sim</label></c:if>
					</p>
				</form>
				
				<form id="formExclusaoPerfilUsuario" action="ServletExclusaoUsuario" method = "post">
					<input type="hidden" name="exclusaoUsuarioEmail" id="exclusaoUsuarioEmail" value="${usuarioPerfil.email}" checked="checked" />
				</form>
				
				<form action="index.jsp">
					<p>
						<%
						Usuario perfilUsuario = new Usuario(); 
						Usuario perfilUsuarioLogado = new Usuario(); 
          				perfilUsuario = (Usuario)session.getAttribute("usuarioPerfil"); 
						perfilUsuarioLogado = (Usuario)session.getAttribute("usuarioLogado"); 
          				Boolean saudacaoPerfil = (Boolean)session.getAttribute("saudacao"); 
          				Boolean flag = false;
          				
          				
          				if(saudacaoPerfil != null && saudacaoPerfil == true) {
							
							System.out.print("entrou");
							if(perfilUsuario != null && perfilUsuarioLogado != null) {
								flag = perfilUsuario.getEmail().equals(perfilUsuarioLogado.getEmail());
							}else if(perfilUsuario == null) {
								flag = true;
							}

							if(flag!= null && flag == true && perfilUsuarioLogado.isAdmin() == false) {
							%>
								<input value="Editar Perfil" type="button" class="submit" onclick="alterarUsuario()" />
								<input value="Excluir Cadastro" type="button" class="submit" onclick="excluirUsuario()" />
							<%
							} 
							%>
							
							<% 
							if(perfilUsuarioLogado.isAdmin() == true) {
							%>
								<input value="Editar Perfil" type="button" class="submit" onclick="alterarUsuario()" />
								<input value="Excluir Cadastro" type="button" class="submit" onclick="excluirUsuario()" />
							<%
					  		}
					  		%>
					  	<%
					  	}
					  	%>
					  	
						<button class="submit" >Voltar</button>
					</p> 
				</form>
			</div>
		</div>
	</div>
</div>

<br />
<br />
<br />

<script>
	function alterarUsuario() {
		var formulario;
		formulario = document.getElementById("formPerfilUsuario");
		formulario.submit();
	}
</script>

<script>
	function excluirUsuario() {
		alert("=( Que pena! Você está deixando o AskTechForum. Toda nossa comunidade está de luto por 3 dias. Nós esperamos que você retorne logo.");
		var formulario;
		formulario = document.getElementById("formExclusaoPerfilUsuario");
		formulario.submit();
	}
</script>

<jsp:include page="rodape.jsp"></jsp:include>