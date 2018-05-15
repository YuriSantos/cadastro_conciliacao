<%--
  Created by IntelliJ IDEA.
  User: Yuri
  Date: 28/11/2017
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="${request.contextPath}/top-menu"/>
<c:import url="${request.contextPath}/left-menu"/>

<div class="row">
    <div class="col-xs-12">
        <div class="box box-primary">


            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Nome</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${varas}" var="vara">
                    <tr>
                        <td>${vara.nome}</td>
                        <td class="col-xs-1">
                            <a href="${vara.id}/atualizar" class="fa fa-2x fa-edit"></a>

                        </td>
                        <td class="col-xs-1">
                            <a href="${vara.id}/deletar" onclick="return confirm('Deseja excluir o registro?')" class="fa fa-2x fa-trash"></a>

                        </td>
                    </tr>
                </c:forEach>

                </tbody>

            </table>

        </div>
    </div>
</div>


<c:import url="${request.contextPath}/rodape"/>