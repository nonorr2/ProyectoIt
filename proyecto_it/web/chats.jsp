<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="cabeceraLogado.jsp" %>

<s:div cssClass="banner-gen">
    <h1 class="text-banner">MIS CHATS</h1>
</s:div>

<s:div id="crearChat" cssClass="modal">
    <s:form theme="simple" cssClass="modal-content animate" action="chatsLogueado">
        <s:div cssClass="imgcontainer">
            <h2 class="titulo-popUp">Nuevo Chat</h2>
            <span onclick="document.getElementById('crearChat').style.display = 'none'" class="close" title="Close Modal">&times;</span>
        </s:div>
        <s:div cssClass="container-crearChat">
            <s:label>Nombre del chat</s:label>
            <s:textfield name="nombreChat" cssClass="form-control"/>
            <s:label>Usuario</s:label>
            <s:select name="usuario" list="usuarios" listKey="id" listValue="nickname" cssClass="form-control"/>
            <br />
            <s:submit name="btoCrear" cssClass="btn btn-primary" value="CREAR"/>
        </s:div>
    </s:form>
</s:div>
<s:div id="editarChat" cssClass="modal">
    <s:form theme="simple" cssClass="modal-content animate" action="chatsLogueado">
        <s:div cssClass="imgcontainer">
            <h2 class="titulo-popUp">Editar chat</h2>
            <span onclick="document.getElementById('editarChat').style.display = 'none'" class="close" title="Close Modal">&times;</span>
        </s:div>
        <s:div cssClass="container-crearChat">
            <s:hidden id="idChatEdit" name="idChatEdit"/>
            <s:label>Nombre del chat</s:label>
            <s:textfield name="nombreChat" cssClass="form-control"/>
            <br />
            <s:submit name="btoEditar" cssClass="btn btn-primary" value="EDITAR"/>
        </s:div>
    </s:form>
</s:div>

<s:div cssClass="container-fluid text-center">
    <s:div cssClass="contenedor-icono-add" onclick="document.getElementById('crearChat').style.display = 'block'">
        <img src="images/iconos/add.png" class="icono"/>
    </s:div>
    <s:div cssClass="row">
        <s:iterator var="chat" value="chats">
            <s:div cssClass="col-sm-4 sombreado">
                <s:form method="post" action="borrarChat">
                    <h4><s:property value="nombre" /></h4>
                    <p><s:property value="fechaHora" /></p>
                    <s:hidden name="idChat" value="%{id}" />
                    <img src="images/iconos/editar.png" id="<s:property value="id" />" class="icono" onclick="showEdit(this)"/>
                    <s:submit type="image" src="images/iconos/papelera.png" name="borrarChat" cssClass="icono"/>
                </s:form>
            </s:div>
        </s:iterator>
    </s:div>
</s:div>
<script>
    function showEdit(imagen) {
        var id = imagen.getAttribute("id");
        document.getElementById("idChatEdit").setAttribute("value", id);
        document.getElementById('editarChat').style.display = 'block';
    }
</script>

<%@include file="footer.jsp" %>
