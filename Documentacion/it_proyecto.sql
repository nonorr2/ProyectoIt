-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-05-2018 a las 16:52:43
-- Versión del servidor: 10.1.30-MariaDB
-- Versión de PHP: 7.2.1

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

--
-- Volcado de datos para la tabla `chat`
--

INSERT INTO `chat` (`id`, `nombre`, `fecha_hora`) VALUES
(1, 'Perrors Flauta', '2018-05-02 12:00:00'),
(2, 'Cerveceros', '2018-05-01 13:00:00');

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

--
-- Volcado de datos para la tabla `comentario`
--

INSERT INTO `comentario` (`id`, `fecha_hora_modificacion`, `contenido`, `id_usuario`, `id_publicacion`, `fecha_hora_creacion`) VALUES
(1, '2018-05-02 08:00:00', 'Como es Alemania???', 4, 3, '0000-00-00 00:00:00'),
(2, '2018-05-02 13:00:00', 'Hala Madrid!!', 3, 1, '0000-00-00 00:00:00'),
(3, '2018-05-15 00:00:00', 'Hala madrid', 5, 1, '2018-05-15 00:00:00'),
(4, '2018-05-16 00:00:00', 'prueba', 2, 1, '2018-05-15 00:00:00');

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

--
-- Volcado de datos para la tabla `mensaje`
--

INSERT INTO `mensaje` (`id`, `contenido`, `fecha_hora`, `id_usuario`, `id_chat`, `estado`) VALUES
(1, 'Quedamos???', '2018-05-02 19:00:00', 3, 2, 0),
(2, 'Nono tonto', '2018-05-02 20:00:00', 4, 1, 0);

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
  `foto` varchar(100) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `publicacion`
--

INSERT INTO `publicacion` (`id`, `titulo`, `contenido`, `fecha_hora_creacion`, `fecha_hora_modificacion`, `ruta`, `id_usuario`, `id_tematica`, `foto`) VALUES
(1, 'Fútbol', 'Descripción fútbol', '2018-05-02 11:00:00', '2018-05-02 11:00:00', 'https://as.com/', 2, 3, 'images/prueba1.jpg'),
(2, 'Perros', 'Descripción perros', '2018-05-01 13:00:00', '2018-05-01 13:00:00', 'https://es.wikipedia.org/wiki/Canis_lupus_familiaris', 4, 2, 'images/prueba2.jpg'),
(3, 'Viajar a Alemania', 'Descripción del viaje', '2018-05-03 15:00:00', '2018-05-01 13:00:00', 'https://www.touristforum.net/es/baviera-alemania-viajes-en-coche-de-alquiler?gclid=EAIaIQobChMIj4rNp7rn2gIV8jLTCh1PjgtDEAAYASAAEgIoG_D_BwE', 3, 4, 'images/prueba3.jpg'),
(4, 'Informática', 'Descripción', '2018-04-04 17:00:00', '2018-04-04 17:00:00', 'https://es.wikipedia.org/wiki/Inform%C3%A1tica', 2, 2, 'images/prueba4.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suscripcion`
--

DROP TABLE IF EXISTS `suscripcion`;
CREATE TABLE `suscripcion` (
  `fecha_hora` datetime NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_publicacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `suscripcion`
--

INSERT INTO `suscripcion` (`fecha_hora`, `id_usuario`, `id_publicacion`) VALUES
('2018-05-03 08:00:00', 2, 1),
('2018-05-01 00:00:00', 3, 4),
('2018-05-02 20:00:00', 4, 1),
('2018-05-04 17:00:00', 4, 2);

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
(1, 'Animales', 'images/prueba1.jpg'),
(2, 'Tecnologia', 'images/prueba2.jpg'),
(3, 'Deportes', 'images/prueba3.jpg'),
(4, 'Viajes', 'images/prueba4.jpg');

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
  `foto` varchar(100) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `nickname`, `password`, `email`, `tipo`, `fecha_nacimiento`, `foto`) VALUES
(1, 'Admin', 'Admin', 'admin', '$2a$12$nV1irJdczUDtCgYKYePUYOEla0F6y4mFcoQltPqBNhZ3A7fRD.c8S', 'admin@admin.com', 1, '2018-05-15', ''),
(2, 'Nono', 'Rodriguez', 'nono', '1234', 'nono@nono.com', 0, '2018-05-23', ''),
(3, 'David', 'Ruiz', 'david', '1234', 'david@david.com', 0, '2018-05-10', ''),
(4, 'Lydia', 'Reina', 'lydia', '1234', 'lydia@lydia.com', 0, '2018-05-31', ''),
(5, 'Alba', 'Carrasco', 'alba', '1234', 'alba@gmail.com', 0, '2000-05-15', '');

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

--
-- Volcado de datos para la tabla `usuario_chat`
--

INSERT INTO `usuario_chat` (`id_usuario`, `id_chat`, `fecha`) VALUES
(2, 1, '2018-05-26 17:18:57'),
(2, 2, '2018-05-26 17:18:57'),
(3, 2, '2018-05-26 17:18:57'),
(4, 1, '2018-05-26 17:18:57');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `voto_comentario`
--

DROP TABLE IF EXISTS `voto_comentario`;
CREATE TABLE `voto_comentario` (
  `tipo` tinyint(1) NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_comentario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `voto_comentario`
--

INSERT INTO `voto_comentario` (`tipo`, `fecha_hora`, `id_usuario`, `id_comentario`) VALUES
(0, '2018-05-02 14:00:00', 2, 1),
(0, '2018-05-03 00:00:00', 2, 2),
(0, '2018-05-08 00:00:00', 3, 1),
(0, '2018-05-16 00:00:00', 4, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `voto_publicacion`
--

DROP TABLE IF EXISTS `voto_publicacion`;
CREATE TABLE `voto_publicacion` (
  `tipo` tinyint(1) NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_publicacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `voto_publicacion`
--

INSERT INTO `voto_publicacion` (`tipo`, `fecha_hora`, `id_usuario`, `id_publicacion`) VALUES
(0, '2017-12-28 06:09:00', 2, 4),
(0, '2018-05-02 00:00:00', 3, 1),
(0, '2018-05-02 14:00:00', 3, 4),
(0, '2017-12-28 05:00:00', 4, 1),
(0, '2018-05-02 20:00:00', 5, 1),
(0, '2017-12-29 10:00:00', 5, 4);

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
  ADD PRIMARY KEY (`id_usuario`,`id_publicacion`),
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
  ADD PRIMARY KEY (`id_usuario`,`id_comentario`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_comentario` (`id_comentario`);

--
-- Indices de la tabla `voto_publicacion`
--
ALTER TABLE `voto_publicacion`
  ADD PRIMARY KEY (`id_usuario`,`id_publicacion`),
  ADD KEY `id_publicacion` (`id_publicacion`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `chat`
--
ALTER TABLE `chat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `comentario`
--
ALTER TABLE `comentario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `publicacion`
--
ALTER TABLE `publicacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tematica`
--
ALTER TABLE `tematica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `comentario_ibfk_1` FOREIGN KEY (`id_publicacion`) REFERENCES `publicacion` (`id`),
  ADD CONSTRAINT `comentario_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD CONSTRAINT `mensaje_ibfk_1` FOREIGN KEY (`id_chat`) REFERENCES `chat` (`id`),
  ADD CONSTRAINT `mensaje_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `publicacion`
--
ALTER TABLE `publicacion`
  ADD CONSTRAINT `publicacion_ibfk_1` FOREIGN KEY (`id_tematica`) REFERENCES `tematica` (`id`),
  ADD CONSTRAINT `publicacion_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `suscripcion`
--
ALTER TABLE `suscripcion`
  ADD CONSTRAINT `suscripcion_ibfk_1` FOREIGN KEY (`id_publicacion`) REFERENCES `publicacion` (`id`),
  ADD CONSTRAINT `suscripcion_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `usuario_chat`
--
ALTER TABLE `usuario_chat`
  ADD CONSTRAINT `usuario_chat_ibfk_1` FOREIGN KEY (`id_chat`) REFERENCES `chat` (`id`),
  ADD CONSTRAINT `usuario_chat_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `voto_comentario`
--
ALTER TABLE `voto_comentario`
  ADD CONSTRAINT `voto_comentario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `voto_comentario_ibfk_2` FOREIGN KEY (`id_comentario`) REFERENCES `comentario` (`id`);

--
-- Filtros para la tabla `voto_publicacion`
--
ALTER TABLE `voto_publicacion`
  ADD CONSTRAINT `voto_publicacion_ibfk_1` FOREIGN KEY (`id_publicacion`) REFERENCES `publicacion` (`id`),
  ADD CONSTRAINT `voto_publicacion_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
