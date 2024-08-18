<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

        <h2>Livros</h2>
        <div class="col-8 panel body">
            <table id="datatable" class="table table-striped table-bordered basic-datatable">
                <thead>
                    <tr>
                        <th align="left">ID</th>
                        <th align="left">Titulo</th>
                        <th align="left">Isbn</th>
                        <th align="left">Numero de paginas</th>
                        <th align="left">Tipo da Capa</th>
                        <th align="left">Autor</th>
                        <th align="left">Editora</th>
                        <th align="right"></th>
                        <th align="right"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="livro" items="${livros}">
                        <tr>
                            <td align="left">${livro.idLivro}</td>
                            <td align="left">${livro.titulo}</td>
                            <td align="left">${livro.isbn}</td>
                            <td align="left">${livro.numPaginas}</td>
                            <td align="left">${livro.tipoCapa}</td>
                            <td align="left">${livro.autor.nomeAutor}</td>
                            <td align="left">${livro.editora.nomeEditora}</td>
                            <td align="center">
                                <a href="${pageContext.request.contextPath}/LivroExcluir?idLivro=${livro.idLivro}">
                                </a>
                            </td>
                            <td align="center">
                                <a href="${pageContext.request.contextPath}/LivroCarregar?idLivro=${livro.idLivro}">
                                    <button class="btn btn-group-lg btn-success">Alterar</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>>
                </tbody>
            </table>
        </div>
        <div align="center">
            <a href="${pageContext.request.contextPath}/LivroNovo">Novo</a>
            <a href="index.jsp">Voltar à Página inicial</a>
        </div>
            
   <script>
    $(document).ready(function (){
        console.log('entrei ready');
        //carregamo a database
        //$("#datable").DataTable({});
        $('#datatable').dataTable({
            "oLanguge":{
                "sProcessing":"Processando...",
                "sLengthMenu":"Mostrar _MENU_ registros",
                "sZeroRecords":"Nenhum registro encontrado",
                "sInfo":"Mostrando de _START_ ate _END_ de _TOTAL_ registros",
                "sInfoEmpty":"Mostrando de 0 ate 0 de 0 registros",
                "sInfoFiltered":"",
                "sInfoPostFix":"",
                "sSearch":"Buscar",
                "sUrl":"",
                "oPaginate":{
                    "sFirst": "Primeiro",
                    "sPrevius": "Anterior",
                    "sNext": "Seguinte",
                    "sLast": "Ultimo"
                }
            }
        });
    });

</script>

<%@include file="/footer.jsp" %>
