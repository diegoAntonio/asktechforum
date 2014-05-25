<%@ include file="cabecalho.jsp" %>

<br/><br/>
<div>

<h1>Esqueceu Senha?</h1>
<form action="ServletRecuperarSenha" method="post">
Insira seu e-mail e encaminharemos a você a sua senha:
<input type="text" name="email" /><br />
<input type="submit" />
</form>

</div>


<br/><br/>



<jsp:include page="rodape.jsp"></jsp:include>