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
            <span onclick="document.getElementById('crearChat').style.display = 'none'" class="close" title="Close Modal">&times;</span>
            <h2 class="titulo-popUp">Nuevo Chat</h2>
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
    <s:form theme="simple" cssClass="modal-content animate" action="editarChat">
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
                    <a onclick="showChat(<s:property value="id"/>)"><h4><s:property value="nombre" /></h4></a>
                    <p><s:property value="fechaHora" /></p>
                    <s:hidden name="idChat" value="%{id}" />
                    <img src="images/iconos/editar.png" id="<s:property value="id" />" class="icono" onclick="showEdit(this)"/>
                    <s:submit type="image" src="images/iconos/papelera.png" name="borrarChat" cssClass="icono"/>
                </s:form>
            </s:div>
        </s:iterator>
    </s:div>
</s:div>

<s:div id="chat" cssClass="chat">
</s:div>
<script>
    function showEdit(imagen) {
        var id = imagen.getAttribute("id");
        document.getElementById("idChatEdit").setAttribute("value", id);
        document.getElementById('editarChat').style.display = 'block';
    }

    function showChat(id_chat) {

        if (document.getElementById("id_chat_hidden") != null) {
            $("#chat").empty();
            clearInterval(interval);
        }

        var imagen_close = document.createElement("img");
        imagen_close.setAttribute("src", "images/iconos/close.png");
        imagen_close.setAttribute("class", "close_chat");
        imagen_close.setAttribute("onclick", "cerrarChat()");

        var contenedor_mensajes = document.createElement("div");
        contenedor_mensajes.setAttribute("class", "contenedor-mensajes");
        contenedor_mensajes.setAttribute("id", "mensajes");

        var contenedor_entrada = document.createElement("div");
        contenedor_entrada.setAttribute("id", "contenedor_entrada");

        var entrada = document.createElement("input");
        entrada.setAttribute("type", "text");
        entrada.setAttribute("id", "new_msj");
        entrada.setAttribute("class", "input-entrada-chat");

        var btn_envio = document.createElement("input");
        btn_envio.setAttribute("type", "button");
        btn_envio.setAttribute("value", "Enviar");
        btn_envio.setAttribute("onclick", "enviarMensaje()");
        btn_envio.setAttribute("class", "btn-entrada-chat");
        contenedor_entrada.appendChild(entrada);
        contenedor_entrada.appendChild(btn_envio);
        
        var datos_chat = document.createElement("div");
        datos_chat.setAttribute("id", "datos_chat");
        datos_chat.setAttribute("class", "datos-chat");

        var id_chat_hidden = document.createElement("input");
        id_chat_hidden.setAttribute("type", "hidden");
        id_chat_hidden.setAttribute("id", "id_chat_hidden");
        id_chat_hidden.setAttribute("value", id_chat);
        datos_chat.appendChild(id_chat_hidden);



        var contenedor_chat = document.getElementById("chat");
        contenedor_chat.appendChild(contenedor_mensajes);
        contenedor_chat.appendChild(contenedor_entrada);
        contenedor_chat.appendChild(datos_chat);
        contenedor_chat.appendChild(imagen_close);

        cargarMensajes(id_chat);

        interval = setInterval(function () {
            cargarMensajes(id_chat);
        }, 5000);
    }

    function cargarMensajes(id_chat) {
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", "http://localhost:8080/webService/webresources/ws.chat/" + id_chat, true);
        xhttp.setRequestHeader("Accept", "application/xml");
        xhttp.send();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                var chat = xhttp.responseXML;
                $("#datos_chat").append('<p class="texto_chat">' + $(chat).find("nombre")[0].textContent + '</p>');
                var xhttpMensajes = new XMLHttpRequest();
                xhttpMensajes.open("GET", "http://localhost:8080/webService/webresources/ws.mensaje/getMensajesChat/" + id_chat, true);
                xhttpMensajes.setRequestHeader("Accept", "application/json");
                xhttpMensajes.send();
                xhttpMensajes.onreadystatechange = function () {
                    if (xhttpMensajes.readyState == 4 && xhttpMensajes.status == 200) {
                        var mensajes = eval(xhttpMensajes.responseText);

                        var conversacion = document.getElementById("mensajes");
                        $(conversacion).empty();

                        for (var i in mensajes) {
                            if (mensajes[i]['idUsuario']['id'] == $.cookie("user")) {
                                if (mensajes[i]['estado'] == false) {
                                    $(conversacion).append('<div class="msj_prop"><img src="images/iconos/ocultar.png" class="icon_hide" onclick="ocultar(' + mensajes[i]['id'] + ')"><p>' + mensajes[i]['contenido'] + '</p></div>');
                                } else {
                                    $(conversacion).append('<div class="msj_prop"><p><i>EL TEXTO HA SIDO OCULTADO<i></p></div>');
                                }
                            } else {
                                if (mensajes[i]['estado'] == false) {
                                    $(conversacion).append('<div class="msj_noProp"><p>' + mensajes[i]['contenido'] + '</p></div>');
                                } else {
                                    $(conversacion).append('<div class="msj_noProp"><p><i>EL TEXTO HA SIDO OCULTADO<i></p></div>');
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    function cerrarChat() {
        $("#chat").empty();
        clearInterval(interval);
    }

    function enviarMensaje() {
        var mensaje = document.getElementById("new_msj").value;

//COGER EL CHAT
        var xhttpChat = new XMLHttpRequest();
        xhttpChat.open("GET", "http://localhost:8080/webService/webresources/ws.chat/" + $("#id_chat_hidden").val(), false);
        xhttpChat.setRequestHeader("Content-Type", "application/xml");
        xhttpChat.send();
        var chat = xhttpChat.responseText;

//COGER EL USUARIO
        var id_usuario = $.cookie('user');
        var xhttpUser = new XMLHttpRequest();
        xhttpUser.open("GET", "http://localhost:8080/webService/webresources/ws.usuario/" + id_usuario, false);
        xhttpUser.setRequestHeader("Content-Type", "application/xml");
        xhttpUser.send();
        var usuario = xhttpUser.responseXML;


        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "http://localhost:8080/webService/webresources/ws.mensaje", false);
        xhttp.setRequestHeader("Content-Type", "application/xml");

        var contenido = '<contenido>' + mensaje + '</contenido>';
        var estado = '<estado>false</estado>';
        var fechaHora = '<fechaHora>' + new Date().toISOString() + '</fechaHora>';
        var id = '<id></id>';
        var fechaHoraChat = '<fechaHora>' + $(chat).find("fechaHora")[0].textContent + '</fechaHora>';
        var idChat = '<id>' + $(chat).find("id")[0].textContent + '</id>';
        var nombreChat = '<nombre>' + $(chat).find("nombre")[0].textContent + '</nombre>';

        var apellidos = '<apellidos>' + $(usuario).find("apellidos")[0].textContent + '</apellidos>';
        var email = '<email>' + $(usuario).find("email")[0].textContent + '</email>';
        var fechaNacimiento = '<fechaNacimiento>' + $(usuario).find("fechaNacimiento")[0].textContent + '</fechaNacimiento>';
        var foto = '<foto>' + $(usuario).find("foto")[0].textContent + '</foto>';
        var id_user_xml = '<id>' + id_usuario + '</id>';
        var nickname = '<nickname>' + $(usuario).find("nickname")[0].textContent + '</nickname>';
        var nombre = '<nombre>' + $(usuario).find("nombre")[0].textContent + '</nombre>';
        var password = '<password>' + $(usuario).find("password")[0].textContent + '</password>';
        var tipo = '<tipo>' + $(usuario).find("tipo")[0].textContent + '</tipo>';
        var xmlMensaje = '<mensaje>' + contenido + estado + fechaHora + id + '<idChat>' + fechaHoraChat + idChat + nombreChat + '</idChat><idUsuario>' + apellidos + email + fechaNacimiento + foto + id_user_xml + nickname + nombre + password + tipo + '</idUsuario></mensaje>';
        xhttp.send(xmlMensaje);

        var conversacion = document.getElementById("mensajes");
        $(conversacion).append('<div class="msj_prop"><p>' + document.getElementById("new_msj").value + '</p></div>')

        document.getElementById("new_msj").value = "";
    }

    function ocultar(id_mensaje) {

//COGER EL MENSAJE
        var xhttpMensaje = new XMLHttpRequest();
        xhttpMensaje.open("GET", "http://localhost:8080/webService/webresources/ws.mensaje/" + id_mensaje, false);
        xhttpMensaje.setRequestHeader("Accept", "application/json");
        xhttpMensaje.send();
        var mensaje = xhttpMensaje.responseText;
        mensaje = JSON.parse(mensaje);
        mensaje['estado'] = true;

        var xhttpMensaje = new XMLHttpRequest();
        xhttpMensaje.open("PUT", "http://localhost:8080/webService/webresources/ws.mensaje/" + id_mensaje, false);
        xhttpMensaje.setRequestHeader("Content-Type", "application/json");
        xhttpMensaje.send(JSON.stringify(mensaje));
    }
</script>

<%@include file="footer.jsp" %>
