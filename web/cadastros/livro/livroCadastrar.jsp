<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<form name="cadastrarlivro" action="LivroCadastrar" method="POST">
    <table align="center" border="0">
        <thead>
            <tr>
                <th colspan="2" align="center">
                    Cadastro de Livro</th>
            </tr>
            <tr>
                <th colspan="2" align="center">$mensagem</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>ID: </td>
                <td><input type="text" name="idlivro" value="${livro.idLivro}" readonly="readonly"></td></tr>
            <tr>
                <td>Titulo: </td>
                <td><input  type="text" name="titulo" id="titulo" value="${livro.titulo}" size="50" maxlength="50"/></td>
            </tr>
            <tr>
                <td>Isbn: </td>
                <td><input  type="text" name="isbn" id="isbn" value="${livro.isbn}" size="50" maxlength="50"/></td>
            </tr>
            <tr>
                <td>Numero de Páginas: </td>
                <td><input  type="text" name="numpaginas" id="numpaginas" value="${livro.numPaginas}" size="50" maxlength="50"/></td>
            </tr>
            <tr>
                <td>Tipo da Capa: </td>
                <td><input  type="text" name="tipocapa" id="tipocapa" value="${livro.tipoCapa}" size="50" maxlength="50"/></td>
            </tr>
            <tr>
                <td>Autor: </td>
                <td>
                    <select name="idautor" id="idautor">
                        <option value="">Selecione</option>
                        <c:forEach var="autor" items="${autores}">
                        <option value="${autor.idAutor}" ${livro.autor.idAutor == autor.idAutor ? "selected" : ""}>
                            ${autor.nomeAutor}
                        </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Editora: </td>
                <td>
                    <select name="ideditora" id="ideditora">
                        <option value="">Selecione</option>
                        <c:forEach var="editora" items="${editoras}">
                        <option value="${editora.idEditora}" ${livro.editora.idEditora == editora.idEditora ? "selected" : ""}>
                            ${editora.nomeEditora}
                        </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" name="cadastrar" id="cadastrar" value="Cadastrar"/>
                    <input type="reset" name="limpar" id="limpar" value="limpar"/>
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2"><h5><a href="index.jsp">Voltar à Página Inicial</a></h5></td>
            </tr>
        </tbody>
    </table>
</form>
<%@include file="/footer.jsp" %>   