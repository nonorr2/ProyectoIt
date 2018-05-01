<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Bootstrap Example</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            .banner-index{
                width: 100%;
                height: 650px;
                margin: 0px;
                background: url(images/banner_index.png) no-repeat center top;
            }

            .div-icono{
                width: 100%;
                margin: 0px;
                height: 150px;
                background: no-repeat center center;
            }

            .text-banner{
                font-size: 30px;
                color: white;
                z-index: 1;
                float: top;
                text-align: center;
                position: relative;
                top: 30%;
            }

        </style>
    </head>
    <body>
        <div class="banner-index">
            <p class="text-banner">Habla de lo que quieras</p>
            <br>
            <p class="text-banner">SIN CENSURA</p>
        </div>
        <div class="container-fluid text-center">
            <h2>TEMAS MÁS ACTIVOS</h2>
            <br>
            <div class="row">
                <div class="col-sm-3">
                    <div class="div-icono" style=" background-image: url(images/prueba1.jpg)"></div>
                    <h4>POWER</h4>
                    <p>Lorem ipsum dolor sit amet..</p>
                </div>
                <div class="col-sm-3">
                    <div class="div-icono" style=" background-image: url(images/prueba2.jpg)"></div>
                    <h4>LOVE</h4>
                    <p>Lorem ipsum dolor sit amet..</p>
                </div>
                <div class="col-sm-3">
                    <div class="div-icono" style=" background-image: url(images/prueba3.jpg)"></div>
                    <h4>JOB DONE</h4>
                    <p>Lorem ipsum dolor sit amet..</p>
                </div>
                <div class="col-sm-3">
                    <div class="div-icono" style=" background-image: url(images/prueba4.jpg)"></div>
                    <h4>JOB DONE</h4>
                    <p>Lorem ipsum dolor sit amet..</p>
                </div>
            </div>
            <br><br>
            <div class="row">
                <div class="col-sm-3">
                    <div class="div-icono" style=" background-image: url(images/prueba3.jpg)"></div>
                    <h4>POWER</h4>
                    <p>Lorem ipsum dolor sit amet..</p>
                </div>
                <div class="col-sm-3">
                    <div class="div-icono" style=" background-image: url(images/prueba4.jpg)"></div>
                    <h4>LOVE</h4>
                    <p>Lorem ipsum dolor sit amet..</p>
                </div>
                <div class="col-sm-3">
                    <div class="div-icono" style=" background-image: url(images/prueba1.jpg)"></div>
                    <h4>JOB DONE</h4>
                    <p>Lorem ipsum dolor sit amet..</p>
                </div>
                <div class="col-sm-3">
                    <div class="div-icono" style=" background-image: url(images/prueba2.jpg)"></div>
                    <h4>JOB DONE</h4>
                    <p>Lorem ipsum dolor sit amet..</p>
                </div>
            </div>
        </div>
    </body>
</html>