<%@ include file="cabecalho.jsp"%>
<div id="site_content">
	<div class="content">
		<h1>Login</h1>
		<div class="content_item">

			<div class="form_settings">
				<p>
					<span>E-mail</span><input class="contact" type="text"
						name="your_email" value="" />
				</p>
				<p>
					<span>Senha</span>
					<input class="contact textarea" name="your_message"/>
				</p>
				<h3>
					<a href="esqueciMinhaSenha.jsp">Esqueci minha senha</a>
				</h3>	
				<p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="contact_submitted" value="Entrar" /></p>
			</div>


		</div>
	</div>
</div>
<jsp:include page="rodape.jsp"></jsp:include>