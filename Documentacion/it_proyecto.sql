-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-06-2018 a las 20:03:08
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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

CREATE TABLE `chat` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) CHARACTER SET latin1 NOT NULL,
  `fecha_hora` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `chat`
--

INSERT INTO `chat` (`id`, `nombre`, `fecha_hora`) VALUES
(1, 'Perros flautas', '2018-05-02 12:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

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
(2, '2018-05-02 13:00:00', 'Hala Madrid!!', 3, 1, '2018-05-15 00:00:00'),
(4, '2018-05-16 00:00:00', 'prueba', 2, 1, '2018-05-15 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje`
--

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
(4, 'tu mas', '2018-06-04 00:00:00', 2, 1, 1),
(5, 'fea', '2018-06-04 00:00:00', 2, 1, 0),
(6, 'perra', '2018-06-04 00:00:00', 2, 1, 0),
(7, 'zorra', '2018-06-04 00:00:00', 2, 1, 0),
(8, 'mamona', '2018-06-04 00:00:00', 2, 1, 0),
(9, ' bb', '2018-06-04 00:00:00', 2, 1, 0),
(10, 'zdvdvs', '2018-05-02 20:00:00', 2, 1, 0),
(11, 'dfb', '2018-06-04 19:26:21', 2, 1, 0),
(12, 'antonioooo', '2018-06-04 19:30:16', 2, 1, 0),
(13, 'ppppprrrr', '2018-06-04 19:31:12', 2, 1, 0),
(14, 'dtfhbfhb', '2018-06-04 19:31:38', 2, 1, 0),
(15, 'mamona', '2018-06-04 19:33:27', 2, 1, 1),
(17, 'perrrrrrooo', '2018-06-04 20:17:38', 2, 1, 0),
(18, 'sdvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv', '2018-06-04 20:17:53', 2, 1, 1),
(19, 'sdvv svsrvsr vsrvwrv rgsvwevswe vr', '2018-06-04 20:19:27', 2, 1, 0),
(20, 'njdhdjbhf rfsbrb wjrbfebf wejb', '2018-06-04 20:19:35', 2, 1, 1),
(21, 'de mentra', '2018-06-04 20:20:31', 2, 1, 0),
(22, 'antonio', '2018-06-04 20:21:36', 2, 1, 1),
(27, 'perra', '2018-06-05 11:56:36', 2, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publicacion`
--

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
(1, 'Fútbol', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sit amet ipsum sit amet erat sollicitudin cursus eu in erat. Pellentesque in sapien vitae ante pharetra maximus at vel tortor. Fusce fringilla ex at augue porttitor bibendum. Mauris dapibus ipsum eu gravida scelerisque. Ut viverra cursus rutrum. Sed eget vulputate ex, nec pellentesque nunc. Cras ut malesuada ligula, a laoreet libero. Morbi dignissim ante et ligula finibus, sit amet fringilla lectus molestie.\r\n\r\nAenean varius malesuada turpis ac vehicula. Phasellus aliquam purus quis accumsan eleifend. Maecenas eu sem ut neque imperdiet posuere nec at velit. Sed fermentum erat sapien, id rutrum nisl vulputate et. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed tempor, lacus non venenatis ultricies, ligula diam vehicula nunc, vel euismod eros odio et felis. In laoreet gravida hendrerit. Maecenas tristique sapien purus, sit amet lacinia quam tempor eu.', '2018-05-02 11:00:00', '2018-05-02 11:00:00', 'https://as.com/', 2, 3, 'images/publicaciones/futbol.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suscripcion`
--

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
('2018-05-03 08:00:00', 2, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tematica`
--

CREATE TABLE `tematica` (
  `id` int(11) NOT NULL,
  `nombre` varchar(150) CHARACTER SET latin1 NOT NULL,
  `imagen` varchar(500) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tematica`
--

INSERT INTO `tematica` (`id`, `nombre`, `imagen`) VALUES
(1, 'Animales', 'images/tematicas/prueba1.jpg'),
(2, 'Tecnologia', 'images/tematicas/prueba2.jpg'),
(3, 'Deportes', 'images/tematicas/prueba3.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

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
(2, 'Nono', 'Rodriguez', 'nono', '$2a$12$nV1irJdczUDtCgYKYePUYOEla0F6y4mFcoQltPqBNhZ3A7fRD.c8S', 'nono@nono.com', 0, '2018-05-23', 'images/fotosPerfil/prueba2.jpg'),
(3, 'David', 'Ruiz', 'david', '$2a$12$nV1irJdczUDtCgYKYePUYOEla0F6y4mFcoQltPqBNhZ3A7fRD.c8S', 'david@david.com', 0, '2018-05-10', 'images/fotosPerfil/prueba1.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_chat`
--

CREATE TABLE `usuario_chat` (
  `id_usuario` int(11) NOT NULL,
  `id_chat` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario_chat`
--

INSERT INTO `usuario_chat` (`id_usuario`, `id_chat`, `fecha`) VALUES
(2, 1, '2018-05-26 17:18:57');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `voto_comentario`
--

CREATE TABLE `voto_comentario` (
  `tipo` tinyint(1) NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_comentario` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `voto_comentario`
--

INSERT INTO `voto_comentario` (`tipo`, `fecha_hora`, `id_usuario`, `id_comentario`, `id`) VALUES
(1, '2018-06-05 17:35:46', 2, 2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `voto_publicacion`
--

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
(0, '2018-05-02 00:00:00', 3, 1, 2);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT de la tabla `publicacion`
--
ALTER TABLE `publicacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `suscripcion`
--
ALTER TABLE `suscripcion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `tematica`
--
ALTER TABLE `tematica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `voto_comentario`
--
ALTER TABLE `voto_comentario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `voto_publicacion`
--
ALTER TABLE `voto_publicacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
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
  ADD CONSTRAINT `mensaje_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
