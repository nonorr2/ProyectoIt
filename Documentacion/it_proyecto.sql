-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-06-2018 a las 20:48:51
-- Versión del servidor: 10.1.26-MariaDB
-- Versión de PHP: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `it_proyecto`
--
CREATE DATABASE IF NOT EXISTS `it_proyecto` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `it_proyecto`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `chat`
--

DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) CHARACTER SET latin1 NOT NULL,
  `fecha_hora` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

DROP TABLE IF EXISTS `comentario`;
CREATE TABLE `comentario` (
  `id` int(11) NOT NULL,
  `fecha_hora_modificacion` datetime NOT NULL,
  `contenido` varchar(5000) CHARACTER SET latin1 NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_publicacion` int(11) NOT NULL,
  `fecha_hora_creacion` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje`
--

DROP TABLE IF EXISTS `mensaje`;
CREATE TABLE `mensaje` (
  `id` int(11) NOT NULL,
  `contenido` varchar(5000) CHARACTER SET latin1 NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_chat` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publicacion`
--

DROP TABLE IF EXISTS `publicacion`;
CREATE TABLE `publicacion` (
  `id` int(11) NOT NULL,
  `titulo` varchar(500) CHARACTER SET latin1 NOT NULL,
  `contenido` varchar(5000) CHARACTER SET latin1 NOT NULL,
  `fecha_hora_creacion` datetime NOT NULL,
  `fecha_hora_modificacion` datetime NOT NULL,
  `ruta` varchar(500) CHARACTER SET latin1 NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_tematica` int(11) NOT NULL,
  `foto` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `publicacion`
--

INSERT INTO `publicacion` (`id`, `titulo`, `contenido`, `fecha_hora_creacion`, `fecha_hora_modificacion`, `ruta`, `id_usuario`, `id_tematica`, `foto`) VALUES
(11, 'El Reino Caído', 'En esta ocasión, veremos a los dinosaurios fuera de su parque temático. Tras el caos creado por la fuga del inteligente Indominus Rex en el parque de Isla Nublar, en esta nueva entrega, por primera vez los dinosaurios se emplazarán fuera de las fronteras del parque de atracciones.', '2018-06-13 19:29:36', '2018-06-13 19:29:36', 'https://kinepolis.es', 16, 13, 'images/publicaciones/El_Reino_Caído.png'),
(12, 'Sergio Ramos y Demarco Flamenco', 'Nueva canción de La Selección Española! Sergio Ramos y Demarco Flamenco - Otra estrella en tu corazón (Videoclip Oficial) #VAMOSESPAÑA #otraestrellaentucorazón', '2018-06-13 19:33:02', '2018-06-13 19:33:02', 'https://www.youtube.com/watch?v=B663R7kgxEA', 16, 18, 'images/publicaciones/Sergio_Ramos_y_Demarco_Flamenco.png'),
(13, 'Julen Lopetegui ', '\"Estoy muy triste, pero tenemos un equipo magnífico y ojalá ganemos el Mundial\", respondió ante las preguntas de los medios presentes en el aeropuerto. No quiso decir nada más al respecto a pesar de la insistencia de los periodistas. En principio Lopetegui tomará un primer vuelo de Krasnodar a Moscú a las 19:00 horas y si llega al enlace a Madrid, llegaría a la capital de España sobre las 2 de la madrugada, hora española.', '2018-06-13 19:39:19', '2018-06-13 19:39:19', 'https://as.com/futbol/2018/06/13/seleccion/1528900798_070602.html', 17, 14, 'images/publicaciones/Julen_Lopetegui_.png'),
(14, 'Foto de Sevilla', 'Fotos de sevilla', '2018-06-13 19:50:13', '2018-06-13 19:50:13', 'https://www.google.es', 17, 15, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suscripcion`
--

DROP TABLE IF EXISTS `suscripcion`;
CREATE TABLE `suscripcion` (
  `fecha_hora` datetime NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_publicacion` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `suscripcion`
--

INSERT INTO `suscripcion` (`fecha_hora`, `id_usuario`, `id_publicacion`, `id`) VALUES
('2018-06-13 20:24:15', 16, 11, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tematica`
--

DROP TABLE IF EXISTS `tematica`;
CREATE TABLE `tematica` (
  `id` int(11) NOT NULL,
  `nombre` varchar(150) CHARACTER SET latin1 NOT NULL,
  `imagen` varchar(500) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tematica`
--

INSERT INTO `tematica` (`id`, `nombre`, `imagen`) VALUES
(12, 'Animales', 'images/tematicas/Animales.png'),
(13, 'Cine', 'images/tematicas/Cine.png'),
(14, 'Deportes', 'images/tematicas/Deportes.png'),
(15, 'Fotografias', 'images/tematicas/Fotografias.png'),
(16, 'Informática', 'images/tematicas/Informática.png'),
(17, 'Lectura', 'images/tematicas/Lectura.png'),
(18, 'Música', 'images/tematicas/Música.png'),
(19, 'Videojuegos', 'images/tematicas/Videojuegos.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) CHARACTER SET latin1 NOT NULL,
  `apellidos` varchar(100) CHARACTER SET latin1 NOT NULL,
  `nickname` varchar(50) CHARACTER SET latin1 NOT NULL,
  `password` varchar(500) CHARACTER SET latin1 NOT NULL,
  `email` varchar(100) CHARACTER SET latin1 NOT NULL,
  `tipo` tinyint(1) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `foto` varchar(100) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `nickname`, `password`, `email`, `tipo`, `fecha_nacimiento`, `foto`) VALUES
(1, 'Admin', 'Admin', 'admin', '$2a$12$nV1irJdczUDtCgYKYePUYOEla0F6y4mFcoQltPqBNhZ3A7fRD.c8S', 'admin@admin.com', 1, '2018-05-15', ''),
(16, 'Lucia', 'Martín Rodriguez', 'lucia', '$2a$12$RljN3/7zq9NCzEH.agRJJ.VRozxViW2WNakiGmE.7x2.4FGnxTx8u', 'lucia@gmail.com', 0, '1982-07-12', 'images/fotosPerfil/lucia.png'),
(17, 'Rafael', 'Alonso Becerra', 'rafa', '$2a$12$JBW5L7sG6Ts2MzIr1Qy8du51ckudSjw5XijbDVnuyOlEiIPzz4dEK', 'rafa@gmail.com', 0, '1979-04-18', 'images/fotosPerfil/rafa.png'),
(18, 'Alfonso', 'Galea ', 'alfonso', '$2a$12$tHffjsNmoAsWQGbZbiBCPO3oqyDYdHS.dPzvC7cbIsJiv.v0r8OJ2', 'alfonso@gmail.com', 0, '1990-03-12', 'images/fotosPerfil/alfonso.png'),
(19, 'Pedro', 'Zambrano', 'pedro', '$2a$12$SLyVn8N5V9uXVsl3d93ZaOhXnyF3cZdr9wiB.XyRDMc5WiCJlYrw6', 'pedro@gmail.com', 0, '1983-09-17', 'images/fotosPerfil/pedro.png'),
(20, 'Juan Antonio', 'Rodriguez', 'nono', '$2a$12$FUtwuU8wMXTuaArmvmcEZeLC9n9yoJWTrkeaPvM7HdIQn1hoZjqiK', 'nono@gmail.com', 0, '1993-01-04', 'images/fotosPerfil/nono.png'),
(21, 'Macarena', 'Domínguez', 'macarena', '$2a$12$W4owJLlSu/9VS9u.AzZmsu6VP4TQ8Jw0BSD8SuSezdJ2USBrcvZEi', 'macarena@gmail.com', 0, '1993-06-17', 'images/fotosPerfil/macarena.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_chat`
--

DROP TABLE IF EXISTS `usuario_chat`;
CREATE TABLE `usuario_chat` (
  `id_usuario` int(11) NOT NULL,
  `id_chat` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `voto_comentario`
--

DROP TABLE IF EXISTS `voto_comentario`;
CREATE TABLE `voto_comentario` (
  `tipo` tinyint(1) NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_comentario` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `voto_publicacion`
--

DROP TABLE IF EXISTS `voto_publicacion`;
CREATE TABLE `voto_publicacion` (
  `tipo` tinyint(1) NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_publicacion` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `voto_publicacion`
--

INSERT INTO `voto_publicacion` (`tipo`, `fecha_hora`, `id_usuario`, `id_publicacion`, `id`) VALUES
(1, '2018-06-13 20:24:41', 16, 11, 1),
(1, '2018-06-13 20:24:48', 16, 13, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `chat`
--
ALTER TABLE `chat`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_publicacion` (`id_publicacion`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_chat` (`id_chat`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `publicacion`
--
ALTER TABLE `publicacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_tematica` (`id_tematica`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `suscripcion`
--
ALTER TABLE `suscripcion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_publicacion` (`id_publicacion`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `tematica`
--
ALTER TABLE `tematica`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario_chat`
--
ALTER TABLE `usuario_chat`
  ADD PRIMARY KEY (`id_usuario`,`id_chat`),
  ADD KEY `id_chat` (`id_chat`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `voto_comentario`
--
ALTER TABLE `voto_comentario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_comentario` (`id_comentario`);

--
-- Indices de la tabla `voto_publicacion`
--
ALTER TABLE `voto_publicacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_publicacion` (`id_publicacion`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `chat`
--
ALTER TABLE `chat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `comentario`
--
ALTER TABLE `comentario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `publicacion`
--
ALTER TABLE `publicacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `suscripcion`
--
ALTER TABLE `suscripcion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tematica`
--
ALTER TABLE `tematica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `voto_comentario`
--
ALTER TABLE `voto_comentario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `voto_publicacion`
--
ALTER TABLE `voto_publicacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `comentario_ibfk_1` FOREIGN KEY (`id_publicacion`) REFERENCES `publicacion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comentario_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD CONSTRAINT `mensaje_ibfk_1` FOREIGN KEY (`id_chat`) REFERENCES `chat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mensaje_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `publicacion`
--
ALTER TABLE `publicacion`
  ADD CONSTRAINT `publicacion_ibfk_1` FOREIGN KEY (`id_tematica`) REFERENCES `tematica` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `publicacion_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `suscripcion`
--
ALTER TABLE `suscripcion`
  ADD CONSTRAINT `suscripcion_ibfk_1` FOREIGN KEY (`id_publicacion`) REFERENCES `publicacion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `suscripcion_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario_chat`
--
ALTER TABLE `usuario_chat`
  ADD CONSTRAINT `usuario_chat_ibfk_1` FOREIGN KEY (`id_chat`) REFERENCES `chat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuario_chat_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `voto_comentario`
--
ALTER TABLE `voto_comentario`
  ADD CONSTRAINT `voto_comentario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `voto_comentario_ibfk_2` FOREIGN KEY (`id_comentario`) REFERENCES `comentario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `voto_publicacion`
--
ALTER TABLE `voto_publicacion`
  ADD CONSTRAINT `voto_publicacion_ibfk_1` FOREIGN KEY (`id_publicacion`) REFERENCES `publicacion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `voto_publicacion_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
